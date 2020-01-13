package com.forms.batch.job.unit.participant.returnorrefund;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.FIToFIPaymentStatusReportV08;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.GroupHeader531;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.OriginalGroupHeader71;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.PaymentTransaction801;
import com.forms.ffp.bussiness.iclfps.pacs002.FFPVO_Pacs002;
import com.forms.ffp.bussiness.iclfps.pacs002.FFPVO_Pacs002_TxInfAndSts;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPStatus;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.PatternUtil;

public class ReturnOrRefundOutwardBatchResponseFileReader extends BatchBaseJob {

	private static String filePath = null;
	private static String fileNamePattern = null;
	private int BATCH_SIZE = 1000;

	@Override
	public void init() throws BatchJobException
	{
		try
		{
			batchLogger.info("init Return/Refound outward response config started");

			filePath = this.batchData + this.actionElement.element("parameters").elementText("local-file-path");
			fileNamePattern = this.actionElement.element("parameters").elementText("filename-pattern");

			PatternUtil patternUtil = new PatternUtil(this.batchAcDate);
			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("YYYYMMDD", this.batchAcDate.replaceAll("-", ""));
			replaceMap.put("clearingcode", this.clearingCode);
			fileNamePattern = patternUtil.patternReplace(replaceMap, fileNamePattern);

			batchLogger.info(String.format("Return/Refound outward batch response file filePath[%s]", filePath));
			batchLogger.info(String.format("Read inward batch date : %s", this.batchAcDate));
			batchLogger.info("init Return/Refound outward response config started end");
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			batchLogger.info("init error ,please check! ");
			throw new BatchJobException(ex);
		}
	}
	
	@Override
	public boolean execute() throws BatchJobException {
		Date loc_startTime = Calendar.getInstance().getTime();
		batchLogger.info("read Return/Refound outward batch response file started at" + loc_startTime);

		try
		{
			File workingPath = new File(filePath);
			// final String filePattern = this.fileNamePattern;
			// 1.Get all files in this batch time
			File[] readfiles = workingPath.listFiles(new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					return name.matches(fileNamePattern);
				}
			});

			if (readfiles != null)
			{
				// query processed file list
				String loc_processedSql = "SELECT FILE_NAME FROM TB_BH_PROCESSED_FILE ORDER BY FILE_NAME";
				List<Object[]> loc_tmpList = EntityManager.queryArrayList(loc_processedSql);
				String[] processedFileList = new String[loc_tmpList.size()];
				if (loc_tmpList != null)
				{
					for (int i = 0; i < loc_tmpList.size(); i++)
						processedFileList[i] = (String) (loc_tmpList.get(i)[0]);
				}

				List<File> loc_fileList = new ArrayList<>();
				for (File loc_tmpFile : readfiles)
				{
					String loc_filename = loc_tmpFile.getName();
					if (Arrays.binarySearch(processedFileList, loc_filename) >= 0)
					{
						batchLogger.warn(loc_filename + " processed!!!");
					} else
					{
						loc_fileList.add(loc_tmpFile);
					}
				}

				if (loc_fileList.size() > 0)
				{
					// DELETE DATA FROM TMP TABLE
					String loc_deleteSql = "DELETE FROM TB_BH_OUTWARD_FPSPCRR WHERE FILE_NAME IN(";
					for (int index = 0; index < loc_fileList.size(); index++)
					{
						loc_deleteSql = loc_deleteSql + "'" + loc_fileList.get(index).getName() + "'";
						if (index < loc_fileList.size() - 1)
							loc_deleteSql = loc_deleteSql + ",";
						else
							loc_deleteSql = loc_deleteSql + ")";
					}
					EntityManager.update(loc_deleteSql);

					Map<String, ISO20022BusinessDataV01> map = this.readFile(loc_fileList);
					this.processor(map);

					String loc_sql = "INSERT INTO TB_BH_PROCESSED_FILE(FILE_NAME, PROCESSED_TS) VALUES (?,?)";
					// insert processed file & delete files
					for (File tmp : loc_fileList)
					{
						EntityManager.update(loc_sql, tmp.getName(), loc_startTime);
						tmp.delete();
					}
				}
			}

			Date loc_endTime = Calendar.getInstance().getTime();
			batchLogger.info("read Return/Refound outward batch response file end at" + loc_endTime);
			batchLogger.info("read Return/Refound outward batch response file use " + (loc_endTime.getTime() - loc_startTime.getTime()) / 1000);
			return true;
		}
		catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}

	public void processor(Map<String, ISO20022BusinessDataV01> busiDataMap) throws BatchJobException
	{
		batchLogger.info("Started processor Return/Refound outward batch response file");

		Iterator<String> fileKeyInfoItr = busiDataMap.keySet().iterator();

		try
		{
			while (fileKeyInfoItr.hasNext())
			{
				String key = fileKeyInfoItr.next();
				String fileName = key.substring(0, key.lastIndexOf("-"));
				String batchId = key.substring(key.lastIndexOf("-") + 1);

				ISO20022BusinessDataV01 businessDataV01 = busiDataMap.get(key);

				// 4.Parse pacs002 of valid file
				FFPVO_Pacs002 pacs002 = parse002(businessDataV01, fileName);

				// 5.Persistent file data
				persistentFileData(pacs002, fileName, batchId);
			}

			batchLogger.info("Ended processor Return/Refound outward batch response file");
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			batchLogger.error("Process inward file error", ex);
			throw new BatchJobException(ex);
		}
	}
	
	private Map<String, ISO20022BusinessDataV01> readFile(List<File> fileList) throws BatchJobException
	{
		batchLogger.info("Started read Return/Refound outward batch response file");
		Map<String, ISO20022BusinessDataV01> txMap = new HashMap<String, ISO20022BusinessDataV01>();
		if (fileList != null)
		{
			for (File xmlFile : fileList)
			{
				try
				{
					FpsMessageEnvelope result = FFPHkiclMessageConverter.parseObject(xmlFile);
					String btchId = result.getBtchInf().getBtchId();
					String key = xmlFile.getName() + "-" + btchId;
					
					String msgCount = result.getNbOfMsgs();
					List<ISO20022BusinessDataV01> bizData = result.getFpsPylds().getBizData();

					// 1.check number of message count is valid
					// RULE: NbOfMsgs = BizData.size()
					if (msgCount.compareTo(String.valueOf(bizData.size())) != 0)
					{
						batchLogger.info(String.format("The file contains invalid message counts:FileName[%s]-NoOfMsgs[%s]-ActualMsgsCount[%s]", xmlFile, msgCount, String.valueOf(bizData.size())));
						continue;
					}
					
					Object document = bizData.get(0).getContent().get(1).getValue();
					if (document instanceof com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document)
					{
						com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document doc = (com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document) document;
						List<PaymentTransaction801> txInfAndSts = doc.getFIToFIPmtStsRpt().getTxInfAndSts();

						// 2.check group status with transaction data status
						// rule1: group status = "RJCT" ,then transaction status
						// must be "RJCT"
						// rule2: group status = "ACSC" ,then transaction status
						// must be "ACSC"
						// rule3: group status = "PART" ,then ignore transaction
						// status check
						OriginalGroupHeader71 rigGrpInf = doc.getFIToFIPmtStsRpt().getOrgnlGrpInfAndSts();
						String grpSts = rigGrpInf.getGrpSts() != null ? rigGrpInf.getGrpSts().value() : null;
						List<String> addInf = rigGrpInf.getStsRsnInf() != null ? rigGrpInf.getStsRsnInf().getAddtlInf() : null;
						StringBuilder reason = new StringBuilder();
						if (addInf != null)
						{
							for (String str : addInf)
							{
								reason.append(str);
							}
						}
						String grpRejCode = rigGrpInf.getStsRsnInf() != null && rigGrpInf.getStsRsnInf().getRsn() != null ? rigGrpInf.getStsRsnInf().getRsn().getPrtry() : null;
						if (grpSts != null)
						{
							if (FFPStatus.GROUP_STATUS.PARTIALLY_ACCEPTED.getCode().equals(grpSts))
							{
								// NOT check group status with all
								// tx_status:PART
								batchLogger.info(String.format("The Return/Refund outward batch response file[%s] group status is : %s", xmlFile.getName(), grpSts));
							} 
							else
							{
								if (!checkStatus(grpSts, txInfAndSts))
								{
									StringBuilder sbd = new StringBuilder(String.format("The Return/Refound outward batch response file[%s] group status[%s] not match with all transaction status", xmlFile.getName(), grpSts));
									if (FFPStatus.GROUP_STATUS.ALL_REJECTED.getCode().equals(grpSts))
										sbd.append(" ").append(String.format("And group reject code = %s, group reject reason = %s", grpRejCode, reason.toString()));
									batchLogger.info(sbd.toString());

									continue;// when file status not match, skip this one
								}
							}
						}
						// 3.Put valid file data
						txMap.put(key, bizData.get(0));
					} 
					else
					{
						batchLogger.info(String.format("Error document type of file[FileName:%s]", xmlFile.getName()));
						continue;
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
					batchLogger.error(String.format("Error on processing the file[fileName:%s]", xmlFile.getName()), e);
					throw new BatchJobException(e);
				}
			}
		}

		batchLogger.info("Ended read Return/Refound outward batch response file");
		return txMap;
	}
	
	private boolean checkStatus(String groupSts, List<PaymentTransaction801> txInfAndSts)
	{
		boolean allStsValid = true;
		for (PaymentTransaction801 payTx : txInfAndSts)
		{
			if (payTx.getTxSts().value().compareTo(groupSts) != 0)
			{
				allStsValid = false;
				break;
			}
		}

		return allStsValid;
	}

	public void persistentFileData(FFPVO_Pacs002 pacs002, String fileName, String batchId) throws BatchJobException
	{
		String sql = "INSERT INTO TB_BH_OUTWARD_FPSPCRR(ID, BATCH_ID, STATUS, FILE_NAME, MSG_ID, MSG_CREATE_TS, ORG_MSG_ID, ORG_MSG_NM_ID, GRP_STS, GRP_REJ_CODE, "
				+ 	 "GRP_REJ_RSN, ORG_END_TO_END_ID, ORG_TRANSACTION_ID, TX_STAT, TX_REJ_CODE, TX_ADD_INFO, TX_ACCEPT_TS, TX_CLR_SYS_REF, ORG_INTR_BANK_SETT_AMT, "
				+ 	 "ORG_INTR_BANK_SETT_CUR, ORG_INTR_BANK_SETT_DATE, BIZ_SVC_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			List<FFPVO_Pacs002_TxInfAndSts> txInfList = pacs002.getTxInfList();
			String bizSvc = pacs002.getBizSvc();
			if (txInfList != null && txInfList.size() > 0)
			{
				int count;
				Object[] res = EntityManager.queryArray("SELECT ID FROM TB_BH_OUTWARD_FPSPCRR ORDER BY ID DESC");//Get max ID	
				if(res == null) count = 0;
				else count = (int)res[0];
				
				int batFlag = 0;
				Object[][] batchObj = new Object[BATCH_SIZE][1];
				for (FFPVO_Pacs002_TxInfAndSts txInfo : txInfList)
				{
					batFlag+=1;
					List<Object> params_list = new ArrayList<Object>();
					params_list.add(++count);
					params_list.add(batchId);
					params_list.add(FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode());
					params_list.add(fileName);
					params_list.add(pacs002.getMsgId());
					params_list.add(FFPDateUtils.convertStringToDate(pacs002.getCreDtTm(), FFPDateUtils.HK_TIMESTAMP_FORMAT1));
					params_list.add(pacs002.getOrgnlMsgId());
					params_list.add(pacs002.getOrgnlMsgNmId());
					params_list.add(pacs002.getGrpSts());
					params_list.add(pacs002.getGrpStsRsnCode());

					StringBuilder reason = new StringBuilder();
					List<String> reasonList = pacs002.getGrpStsAddtlInfList();
					if (reasonList != null)
					{
						for (int i=1; i<=reasonList.size(); i++)
						{
							reason.append(reasonList.get(i-1));
							if(i<reasonList.size()) reason.append(",");
						}
					}
					params_list.add(reason.toString().trim().length() > 0 ? reason.toString().trim() : null);
					params_list.add(txInfo.getOrgnlEndToEndId());
					params_list.add(txInfo.getOrgnlTxId());
					params_list.add(txInfo.getTxSts());
					params_list.add(txInfo.getTxStsRsnCode());
					StringBuilder addInfo = new StringBuilder();
					List<String> addInfoList = txInfo.getTxStsAddtlInf();
					if (addInfoList != null)
					{
						for (int j=1; j<addInfoList.size(); j++)
						{
							addInfo.append(addInfoList.get(j-1));
							if(j<addInfoList.size()) addInfo.append(",");
						}
					}
					params_list.add(addInfo.toString().trim().length() > 0 ? addInfo.toString().trim() : null);
					params_list.add(txInfo.getAccptncDtTm());
					params_list.add(txInfo.getClrSysRef());
					params_list.add(txInfo.getSettlementAmount());
					params_list.add(txInfo.getSettlementCurrency());
					params_list.add(txInfo.getSettlementDate());
					params_list.add(bizSvc);

					batchObj[batFlag - 1] = params_list.toArray();
					if(batFlag % BATCH_SIZE == 0)
					{
						EntityManager.batch(sql, batchObj);
						batchObj = new Object[BATCH_SIZE][1];
						batFlag = 0;
					}
				}
				
				if(batFlag > 0)
				{
					Object[][] remObj = new Object[batFlag][1];
					System.arraycopy(batchObj, 0, remObj, 0, batFlag);
					EntityManager.batch(sql, remObj);
				}
			}
		}
		catch (Exception ex)
		{
			batchLogger.error(String.format("Temporarily persistent batch file[%s] data error", fileName), ex);
			throw new BatchJobException(ex);
		}
	}
	
	private FFPVO_Pacs002 parse002(ISO20022BusinessDataV01 bizData, String fileName) throws Exception
	{
		FFPVO_Pacs002 pacs002MsgVo = new FFPVO_Pacs002();
		BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01) bizData.getContent().get(0).getValue();
		pacs002MsgVo.setBizSvc(head.getBizSvc().value());
		
		Object document = bizData.getContent().get(1).getValue();
		com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document pacs002 = (com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document) document;
		FIToFIPaymentStatusReportV08 fiToFIPmtStsRpt = pacs002.getFIToFIPmtStsRpt();
		GroupHeader531 grpHdr = fiToFIPmtStsRpt.getGrpHdr();
		if (grpHdr != null)
		{
			pacs002MsgVo.setMsgId(grpHdr.getMsgId());
			pacs002MsgVo.setCreDtTm(grpHdr.getCreDtTm().toString());
		}
		OriginalGroupHeader71 orgnlGrpInfAndSts = fiToFIPmtStsRpt.getOrgnlGrpInfAndSts();
		if (orgnlGrpInfAndSts != null)
		{
			pacs002MsgVo.setOrgnlMsgId(orgnlGrpInfAndSts.getOrgnlMsgId());
			pacs002MsgVo.setOrgnlMsgNmId(orgnlGrpInfAndSts.getOrgnlMsgNmId());
			if (orgnlGrpInfAndSts.getGrpSts() != null)
			{

				pacs002MsgVo.setGrpSts(orgnlGrpInfAndSts.getGrpSts().value());
			}
			if (orgnlGrpInfAndSts.getStsRsnInf() != null)
			{
				pacs002MsgVo.setGrpStsRsnCode(orgnlGrpInfAndSts.getStsRsnInf().getRsn().getPrtry());
				pacs002MsgVo.setGrpStsAddtlInfList(orgnlGrpInfAndSts.getStsRsnInf().getAddtlInf());

			}
		}

		List<PaymentTransaction801> txInfAndSts = fiToFIPmtStsRpt.getTxInfAndSts();
		List<FFPVO_Pacs002_TxInfAndSts> txInfList = new ArrayList<FFPVO_Pacs002_TxInfAndSts>();
		if (txInfAndSts != null && txInfAndSts.size() > 0)
		{
			for (PaymentTransaction801 paymentTransaction801 : txInfAndSts)
			{
				FFPVO_Pacs002_TxInfAndSts txInf = new FFPVO_Pacs002_TxInfAndSts();
				txInf.setOrgnlEndToEndId(paymentTransaction801.getOrgnlEndToEndId());
				txInf.setOrgnlTxId(paymentTransaction801.getOrgnlTxId());
				txInf.setTxSts(paymentTransaction801.getTxSts().value());
				if (paymentTransaction801.getStsRsnInf() != null)
				{
					txInf.setTxStsRsnCode(paymentTransaction801.getStsRsnInf().getRsn() != null ? paymentTransaction801.getStsRsnInf().getRsn().getPrtry() : null);
					txInf.setTxStsAddtlInf(paymentTransaction801.getStsRsnInf().getAddtlInf());
				}
				txInf.setClrSysRef(paymentTransaction801.getClrSysRef());
				
				//TX_STATUS = ACSC, Original Transaction Reference exist
				if(paymentTransaction801.getOrgnlTxRef() != null && FFPConstantsTxJnl.TX_STATUS.TX_STAT_ACSC.getStatus().equals(paymentTransaction801.getTxSts().value()))
				{
					txInf.setSettlementAmount(paymentTransaction801.getOrgnlTxRef().getIntrBkSttlmAmt().getValue());
					txInf.setSettlementCurrency(paymentTransaction801.getOrgnlTxRef().getIntrBkSttlmAmt().getCcy().value());
					txInf.setSettlementDate(paymentTransaction801.getOrgnlTxRef().getIntrBkSttlmDt().toGregorianCalendar().getTime());
				}
				
				txInfList.add(txInf);
			}
		}

		pacs002MsgVo.setTxInfList(txInfList);
		return pacs002MsgVo;
	}
}
