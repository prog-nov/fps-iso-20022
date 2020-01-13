package com.forms.batch.job.unit.iclfps.payment.credittransfer;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.AccountNotification121;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.BankToCustomerDebitCreditNotificationV06;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.ChargesRecord21;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.EntryTransaction81;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.TransactionAgents31;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.BatchInformation;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.OriginalTransactionReference241;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.PaymentTransaction761;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CashAccount241;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CreditTransferTransaction251;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.GenericOrganisationIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.GenericPersonIdentification11;
import com.forms.ffp.bussiness.iclfps.camt054.FFPVOCamt054;
import com.forms.ffp.bussiness.iclfps.pacs004.FFPVO_Pacs004;
import com.forms.ffp.bussiness.iclfps.pacs004.FFPVO_Pacs004_TxInf;
import com.forms.ffp.bussiness.iclfps.pacs008.FFPVO_Pacs008;
import com.forms.ffp.bussiness.iclfps.pacs008.FFPVO_Pacs008_CdtTrfTxInf;
import com.forms.ffp.core.define.FFPStatus;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.dt.FFPDtFpsPaymentNotification;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.PatternUtil;

public class PaymentInwardFileReader extends BatchBaseJob
{
	private int BATCH_SIZE = 1000;
	private String filePath = null;
	private String fileNamePattern = null;
	
	public void init() throws BatchJobException
	{
		try
		{
			filePath = this.batchData + this.actionElement.element("parameters").elementText("local-file-path");
			fileNamePattern = this.actionElement.element("parameters").elementText("filename-pattern");
			
			PatternUtil patternUtil = new PatternUtil(this.batchAcDate);
			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("YYYYMMDD", this.batchAcDate.replaceAll("-", ""));
			replaceMap.put("clearingcode", this.clearingCode);
			fileNamePattern = patternUtil.patternReplace(replaceMap, fileNamePattern);
		}
		catch(Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}

	@Override
	public boolean execute() throws BatchJobException
	{
		Date loc_startTime = Calendar.getInstance().getTime();
		batchLogger.info("read inward batch started at" + loc_startTime);
		batchLogger.info(String.format("Read inward batch date : %s", this.batchAcDate));
		batchLogger.info(String.format("Read inward batch file path : %s", filePath));
		try
		{
			File workingPath = new File(filePath);
//			final String filePattern = this.fileNamePattern;
			//1.Get all files in this batch time
			File[] readfiles = workingPath.listFiles(new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					return name.matches(fileNamePattern);
				}
			});
			
			if(readfiles != null)
			{
				// query processed file list
				String loc_processedSql = "SELECT FILE_NAME FROM TB_BH_PROCESSED_FILE ORDER BY FILE_NAME";
				List<Object[]> loc_tmpList = EntityManager.queryArrayList(loc_processedSql);
				String[] processedFileList = new String[loc_tmpList.size()];
				if(loc_tmpList != null)
				{
					for(int i = 0; i < loc_tmpList.size(); i ++)
						processedFileList[i] = (String)(loc_tmpList.get(i)[0]);
				}
				
				List<File> loc_fileList = new ArrayList<>();
				for(File loc_tmpFile : readfiles)
				{
					String loc_filename = loc_tmpFile.getName();
					if(Arrays.binarySearch(processedFileList, loc_filename) >= 0)
					{
						batchLogger.warn(loc_filename + " processed!!!");
					}
					else
					{
						loc_fileList.add(loc_tmpFile);
					}
				}
				
				if(loc_fileList.size() > 0)
				{
					// DELETE DATA FROM TMP TABLE
					String loc_deleteSql = "WHERE FILE_NAME IN(";
					for(int index = 0; index < loc_fileList.size(); index ++)
					{
						loc_deleteSql = loc_deleteSql + "'" + loc_fileList.get(index).getName() + "'" ;
						if(index < loc_fileList.size() - 1)
							loc_deleteSql = loc_deleteSql + ",";
						else
							loc_deleteSql = loc_deleteSql + ")";
					}
					EntityManager.update(new StringBuilder("DELETE FROM TB_BH_INWARD_FPSPYCI ").append(loc_deleteSql).toString());
					EntityManager.update(new StringBuilder("DELETE FROM TB_BH_INWARD_RETURN_FPSPYCI ").append(loc_deleteSql).toString());
					EntityManager.update(new StringBuilder("DELETE FROM TB_BH_INWARD_NOT_FPSPYCI ").append(loc_deleteSql).toString());
					
					Map<String, List<ISO20022BusinessDataV01>> map = this.readFile(loc_fileList);
					this.processor(map);
					
					String loc_sql = "INSERT INTO TB_BH_PROCESSED_FILE(FILE_NAME, PROCESSED_TS) VALUES (?,?)";
					// insert processed file & delete files
					for(File tmp : loc_fileList)
					{
						EntityManager.update(loc_sql, tmp.getName(), loc_startTime);
						tmp.delete();
					}
				}
			}
			
			Date loc_endTime = Calendar.getInstance().getTime();
			batchLogger.info("read inward batch end at" + loc_endTime);
			batchLogger.info("read inward batch file use " + (loc_endTime.getTime() - loc_startTime.getTime()) / 1000);
			return true;
		}
		catch(Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}
	
	private void processor(Map<String, List<ISO20022BusinessDataV01>> busiDataMap) throws Exception
	{
		batchLogger.info("Started processor inward batch file");
		
		//List<com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document> pacs008list = new ArrayList<com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document>();
		Iterator<String> fileKeyInfoItr = busiDataMap.keySet().iterator();
		
		//Connection con = null;
		//String batchId = null;//each batch time have the same batch id in all batch files
		
		try
		{
			//Get FFP Batch DB configuration, DB manager
			//con = ConnectionManager.getInstance().getConnection();
			
			while(fileKeyInfoItr.hasNext())
			{
				String fileKeyInfo = fileKeyInfoItr.next();
				
				String fileName = fileKeyInfo.substring(0, fileKeyInfo.lastIndexOf("-"));
				String batchId = fileKeyInfo.substring(fileKeyInfo.lastIndexOf("-") + 1);
				List<ISO20022BusinessDataV01> busiData = busiDataMap.get(fileKeyInfo);
				
				for(ISO20022BusinessDataV01 data : busiData)
				{
					BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01)data.getContent().get(0).getValue();
					String messageType = head.getMsgDefIdr();
					String busiSvrType = head.getBizSvc().value();
					//pacs.008.001.06
					if(FFPJaxbConstants.JAXB_MSG_TYPE_PACS_008.compareTo(messageType) == 0) 
					{
						batchLogger.info(String.format("Process inward credit transfer transaction data of file[File Name:%s]", fileName));
						com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document doc008 = 
												(com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document)data.getContent().get(1).getValue();
						
						//pacs008list.add(doc008);
						
						//4a.parse xml
						FFPVO_Pacs008 pacs008 = parse008(doc008);
						
						//5b.persistent in temp table
						persistentFileData(pacs008, fileName, batchId, busiSvrType);
					}
					//pacs.004.001.07
					else if(FFPJaxbConstants.JAXB_MSG_TYPE_PACS_004.compareTo(messageType) == 0)
					{
						batchLogger.info(String.format("Process inward return or refund transaction data of file[File Name:%s]",fileName));
						com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.Document doc004 = 
												(com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.Document)data.getContent().get(1).getValue();
						
						//4b.parse 004 xml
						FFPVO_Pacs004 pacs004 = parse004(doc004);
						
						//5b.persistent return/refund file data
						persistentRtnFileData(pacs004, fileName, batchId, busiSvrType);
					}
					//camt.054.001.06
					else if(FFPJaxbConstants.JAXB_MSG_TYPE_CAMT_054.compareTo(messageType) == 0)
					{
						batchLogger.info(String.format("Process inward payement notification data of file[File Name:%s]", fileName));
						com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.Document doc054 = 
												(com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.Document)data.getContent().get(1).getValue();
						
						//4c.parse 054 xml
						FFPVOCamt054 camt054 = parse054(doc054);
						
						//5c.persistent notification file data
						persitentNotFileData(camt054, fileName, batchId, busiSvrType);
					}
				}
			}
			
			//check FFP Agent status, CUT OFF/ON
			batchLogger.info("Ended processor inward batch file");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			batchLogger.error("Process inward file error", ex);
			throw new BatchJobException(ex);
		}
	}
	
	private void persitentNotFileData(FFPVOCamt054 camt054, String fileName, String batchId, String busiSvrType) throws Exception
	{
		String sql = "INSERT INTO TB_BH_INWARD_NOT_FPSPYCI("								  																		  +
								  "ID, BATCH_ID, STATUS, FILE_NAME, MSG_ID, MSG_CREATE_TS, NOT_ID, NOT_CREATE_TS, NOT_ACCT_ID, NOT_ACCT_TYPE, "  				 	  +
								  "NOT_ENTRY_AMT, NOT_ENTRY_CUR, NOT_ENTRY_CDT_DBT_IND, NOT_ENTRY_STATUS, NOT_BANK_TX_CODE, NOT_ENTRY_DTL_END_TO_END_ID, " 			  +
								  "NOT_ENTRY_DTL_TX_ID, NOT_ENTRY_DTL_MANT_ID, NOT_ENTRY_DTL_CLS_REF, NOT_ENTRY_DTL_AMT, NOT_ENTRY_DTL_CUR, "						  +
								  "NOT_ENTRY_DTL_CDT_DBT_IND, NOT_ENTRY_DTL_CHG_AMT, NOT_ENTRY_DTL_CHG_CUR, NOT_ENTRY_DTL_CHG_BEAR, NOT_ENTRY_DTL_CHG_AGT_BIC, " 	  + 
								  "NOT_ENTRY_DTL_CHG_AGT_ID, RLTD_PTIES_DBTR_NAME, RLTD_PTIES_DBTR_ORGID_BIC, RLTD_PTIES_DBTR_ORGID_OTH_ID, " 						  +
								  "RLTD_PTIES_DBTR_ORGID_OTH_SCHME, RLTD_PTIES_DBTR_ORGID_OTH_ISS, RLTD_PTIES_DBTR_PRVTID_OTH_ID, RLTD_PTIES_DBTR_PRVTID_OTH_SCHME, " +
								  "RLTD_PTIES_DBTR_PRVTID_OTH_ISS, RLTD_PTIES_DBTR_CONT_MOBILE, RLTD_PTIES_DBTR_CONT_EMAIL, RLTD_PTIES_DBTR_ACCT_ID, " 				  + 
								  "RLTD_PTIES_DBTR_ACCT_SCHME, RLTD_PTIES_CDTR_NAME, RLTD_PTIES_CDTR_ORGID_BIC, RLTD_PTIES_CDTR_ORGID_OTH_ID, " 					  + 
								  "RLTD_PTIES_CDTR_ORGID_OTH_SCHME, RLTD_PTIES_CDTR_ORGID_OTH_ISS, RLTD_PTIES_CDTR_PRVTID_OTH_ID, RLTD_PTIES_CDTR_PRVTID_OTH_SCHME, " +
								  "RLTD_PTIES_CDTR_PRVTID_OTH_ISS, RLTD_PTIES_CDTR_CONT_MOBILE, RLTD_PTIES_CDTR_CONT_EMAIL, RLTD_PTIES_CDTR_ACCT_ID, " 				  + 
								  "RLTD_PTIES_CDTR_ACCT_SCHME, RLTD_DBTR_AGT_BIC, RLTD_DBTR_AGT_ID, RLTD_CDTR_AGT_BIC, RLTD_CDTR_AGT_ID, PURP_CODE, PURP_OTHER, " 	  + 
								  "REMI_INFO, RLTD_INTER_SETTLE_DATE, RLTD_TX_DATE_TIME, RETURN_INFO_CODE, RETURN_INFO_RSN, BIZ_SVC_TYPE, MSG_INST_DATE) "							  +
					  "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try
		{
			String msgId = camt054.getMsgId();
			Date cratTs = camt054.getMsgCreateTs();
			
			List<FFPDtFpsPaymentNotification> camt054NotInf = camt054.getNotifList();
			if(camt054NotInf != null && camt054NotInf.size() > 0)
			{
				int count;
				Object[] res = EntityManager.queryArray("SELECT ID FROM TB_BH_INWARD_NOT_FPSPYCI ORDER BY ID DESC");//Get max ID	
				if(res == null) count = 0;
				else count = (int)res[0];
				
				int batFlag = 0;
				Object[][] batchObj = new Object[BATCH_SIZE][1];
				for(FFPDtFpsPaymentNotification not : camt054NotInf)
				{
					batFlag+=1;
					List<Object> paramList = new ArrayList<Object>();
					paramList.add(++count);
					paramList.add(batchId);
					paramList.add(FFPStatus.TEMP_NOT_STATUS.INWARD_INITATE.getCode());
					paramList.add(fileName);
					paramList.add(msgId);
					paramList.add(cratTs);
					paramList.add(not.getNtfctnId());
					paramList.add(not.getNtfctnCreateTs());
					paramList.add(not.getNtfctnAcctId());
					paramList.add(not.getNtfctnAcctType());
					paramList.add(not.getNtryAmt());
					paramList.add(not.getNtryAmtCcy());
					paramList.add(not.getNtryCdtDbtInd());
					paramList.add(not.getNtryStatus());
					paramList.add(not.getNtryBankTransCode());
					paramList.add(not.getNtryDetailEndToEndId());
					paramList.add(not.getNtryDetailTxId());
					paramList.add(not.getNtryDetailMandateId());
					paramList.add(not.getNtryDetailClrSysRef());
					paramList.add(not.getNtryDetailAmt());
					paramList.add(not.getNtryDetailAmtCcy());
					paramList.add(not.getNtryDetailCdtDbtInd());
					paramList.add(not.getNtryDetailChrgsAmt());
					paramList.add(not.getNtryDetailChrgsAmtCcy());
					paramList.add(not.getNtryDetailChrgsBr());
					paramList.add(not.getNtryDetailChrgsAgtBic());
					paramList.add(not.getNtryDetailChrgsAgtMmbId());
					paramList.add(not.getRltdPtiesDbtrName());
					paramList.add(not.getRltdPtiesDbtrOrgIdBIC());
					paramList.add(not.getRltdPtiesDbtrOrgIdOthrId());
					paramList.add(not.getRltdPtiesDbtrOrgIdOthrSchme());
					paramList.add(not.getRltdPtiesDbtrOrgIdOthrIssr());
					paramList.add(not.getRltdPtiesDbtrPrvtIdOthrId());
					paramList.add(not.getRltdPtiesDbtrPrvtIdOthrSchme());
					paramList.add(not.getRltdPtiesDbtrPrvtIdOthrIssr());
					paramList.add(not.getRltdPtiesDbtrContactMobile());
					paramList.add(not.getRltdPtiesDbtrContactEmail());
					paramList.add(not.getRltdPtiesDbtrAcctId());
					paramList.add(not.getRltdPtiesDbtrAcctScheme());
					paramList.add(not.getRltdPtiesCdtrName());
					paramList.add(not.getRltdPtiesCdtrOrgIdBIC());
					paramList.add(not.getRltdPtiesCdtrOrgIdOthrId());
					paramList.add(not.getRltdPtiesCdtrOrgIdOthrSchme());
					paramList.add(not.getRltdPtiesCdtrOrgIdOthrIssr());
					paramList.add(not.getRltdPtiesCdtrPrvtIdOthrId());
					paramList.add(not.getRltdPtiesCdtrPrvtIdOthrSchme());
					paramList.add(not.getRltdPtiesCdtrPrvtIdOthrIssr());
					paramList.add(not.getRltdPtiesCdtrContactMobile());
					paramList.add(not.getRltdPtiesCdtrContactEmail());
					paramList.add(not.getRltdPtiesCdtrAcctId());
					paramList.add(not.getRltdPtiesCdtrAcctScheme());
					paramList.add(not.getRelatedAgentsDbtrBIC());
					paramList.add(not.getRelatedAgentsDbtrMmbId());
					paramList.add(not.getRelatedAgentsCdtrBIC());
					paramList.add(not.getRelatedAgentsCdtrMmbId());
					paramList.add(not.getPurpCode());
					paramList.add(not.getPurpOther());
					paramList.add(not.getRemitInfUstrd());
					paramList.add(not.getRelatedDatesIntrSettlDate());
					paramList.add(not.getRelatedDatesTransTs());
					paramList.add(not.getReturnInfRsn());
					paramList.add(not.getReturnInfMsg());
					paramList.add(busiSvrType);
					paramList.add(new Date());
					
					batchObj[batFlag - 1] = paramList.toArray();
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
		catch(Exception ex)
		{
			batchLogger.error(String.format("Temporarily persistent notification batch file[%s] data error", fileName), ex);
			throw ex;
		}
	}
	
	
	private void persistentRtnFileData(FFPVO_Pacs004 pacs004, String fileName, String batchId, String busiSvrType) throws Exception
	{
		//SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		
		String sql = "INSERT INTO TB_BH_INWARD_RETURN_FPSPYCI(ID, RETURN_ID, BATCH_ID, STATUS, FILE_NAME, SETTLEMENT_METHOD, CLEARING_SYSTEM, RETURN_SETTLEMENT_CUR, "
				+	 "RETURN_SETTLEMENT_AMT, RETURN_SETTLEMENT_DATE, RETURN_INSTRUCTED_CUR, RETURN_INSTRUCTED_AMT, RETURN_CHG_AGT_ID, RETURN_CHG_AGT_BIC, RETURN_CHG_CUR, "
				+ 	 "RETURN_CHG_AMT, RETURN_CODE, RETURN_REASON, BIZ_SVC_TYPE, ORIG_INSTRUCTION_ID, ORIG_END_TO_END_ID, ORIG_TRANSACTION_ID, ORIG_FPS_REF, ORIG_SETTLEMENT_AMT, "
				+ 	 "ORIG_SETTLEMENT_CUR, ORIG_SETTLEMENT_DATE, ORIG_CATEGORY_PURPOSE, ORIG_MANDATE_INFO, ORIG_REM_INFO, ORIG_DEBTOR_NAME, ORIG_DEBTOR_ACCTNO, ORIG_DEBTOR_ACCTNO_TYPE, "
				+ 	 "ORIG_DEBTOR_AGT_ID, ORIG_DEBTOR_AGT_BIC, ORIG_CREDITOR_NAME, ORIG_CREDITOR_ACCTNO, ORIG_CREDITOR_ACCTNO_TYPE, ORIG_CREDITOR_AGT_ID, ORIG_CREDITOR_AGT_BIC, "
				+ 	 "MSG_INST_DATE, LAST_MODI_DATE) "
				+ 	 "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try
		{
			String setMethod = pacs004.getSttlmMtd();
			String clrSys = pacs004.getClrSys();
			List<FFPVO_Pacs004_TxInf> pacs004TxInf = pacs004.getTxinf();
			if(pacs004TxInf != null && pacs004TxInf.size() > 0)
			{
				int count;
				Object[] res = EntityManager.queryArray("SELECT ID FROM TB_BH_INWARD_RETURN_FPSPYCI ORDER BY ID DESC");//Get max ID	
				if(res == null) count = 0;
				else count = (int)res[0];
		
				int batFlag = 0;
				Object[][] batchObj = new Object[BATCH_SIZE][1];
				for(FFPVO_Pacs004_TxInf txInf : pacs004TxInf)
				{
					batFlag+=1;
					List<Object> paramList = new ArrayList<Object>();
					FFPJbP300 p300 = txInf.getP300();
					FFPTxJnl txJnl = p300.getTxJnl(); 
					
					paramList.add(++count);
					paramList.add(p300.getReturnId());
					paramList.add(batchId);
					paramList.add(FFPStatus.TEMP_RETURN_REFUND_STATUS.INWARD_INITATE.getCode());
					paramList.add(fileName);
					paramList.add(setMethod);
					paramList.add(clrSys);
					paramList.add(p300.getRetIntSetCur());
					paramList.add(p300.getRetIntSetAmt());
					paramList.add(p300.getSettlementDate());
					paramList.add(p300.getRetInsCur());
					paramList.add(p300.getRetInsAmt());
					paramList.add(p300.getChgAgtID());
					paramList.add(p300.getChgAgtBIC());
					paramList.add(p300.getChargersCurrency());
					paramList.add(p300.getChargersAmount());
					paramList.add(p300.getReasonCode());
					
					StringBuilder reason = new StringBuilder();
					reason.append(p300.getAdditionalInformation());
//					List<String> reasonList = txInf.getRtrAddtlInf();
//					if (reasonList != null)
//					{
//						for (int i=1; i<=reasonList.size(); i++)
//						{
//							reason.append(reasonList.get(i-1));
//							if(i<reasonList.size()) reason.append(",");
//						}
//					}
					paramList.add(reason.toString().trim().length() > 0 ? reason.toString().trim() : null);
					//paramList.add(null);
					paramList.add(busiSvrType);
					paramList.add("");
					paramList.add(txJnl.getEndToEndId());
					paramList.add(txJnl.getTransactionId());
					paramList.add(txJnl.getFpsRefNm()); 
					paramList.add(p300.getOrgnlInterbankSettAmt());
					paramList.add(p300.getOrgnlInterbankSettCcy());
					paramList.add(p300.getOrgnlInterbankSettDate());
					paramList.add(p300.getOrgnlCatgyPurp());
					paramList.add(p300.getOrgnlMandateInfo());
					paramList.add(p300.getOrgnlRemtInfo());
					paramList.add(p300.getOrgnlDbtrNm());
					paramList.add(p300.getOrgnlDbtrAcctNo());
					paramList.add(p300.getOrgnlDbtrAcctNoTp());
					paramList.add(p300.getOrgnlDbtrAgtId());
					paramList.add(p300.getOrgnlDbtrAgtBIC());
					paramList.add(p300.getOrgnlCdtrNm());
					paramList.add(p300.getOrgnlCdtrAcctNo());
					paramList.add(p300.getOrgnlCdtrAcctNoTp());
					paramList.add(p300.getOrgnlCdtrAgtId());
					paramList.add(p300.getOrgnlCdtrAgtBIC());
					paramList.add(new java.sql.Date(new Date().getTime()));
					paramList.add(new java.sql.Timestamp(new Date().getTime()));
					
					batchObj[batFlag - 1] = paramList.toArray();
					if(batFlag % BATCH_SIZE == 0)
					{
						EntityManager.batch(sql, batchObj);
						batchObj = new Object[BATCH_SIZE][1];
						batFlag = 0;
					}
					
					//EntityManager.update(sql, paramList.toArray());
				}
				if(batFlag > 0)
				{
					Object[][] remObj = new Object[batFlag][1];
					System.arraycopy(batchObj, 0, remObj, 0, batFlag);
					EntityManager.batch(sql, remObj);
				}
			}
		}
		catch(Exception ex)
		{
			batchLogger.error(String.format("Temporarily persistent return or refund batch file[%s] data error", fileName), ex);
			throw ex;
		}
	}
	
	//save file datas to temp table for re-run while exception
	public void persistentFileData(FFPVO_Pacs008 pacs008, String fileName, String batchId, String busiSvrType) throws Exception
	{
		String sql = "INSERT INTO TB_BH_INWARD_FPSPYCI(ID, BATCH_ID, STATUS, FILE_NAME, TRANSACTION_ID, END_TO_END_ID, FPS_REF, INSTRUCTION_ID, CATEGORY_PURPOSE, "
				+ "ACCT_VERF, SETTLEMENT_CUR, SETTLEMENT_AMT, SETTLEMENT_DATE, INSTRUCTED_CUR, INSTRUCTED_AMT, CHG_AGT_ID, CHG_AGT_BIC, CHG_CUR, CHG_AMT, "
				+ "DEBTOR_NAME, DEBTOR_ORGID_ANYBIC, DEBTOR_ORGID_OTHRID, DEBTOR_ORGID_OTHRID_SCHMENM, DEBTOR_ORGID_OTHR_ISSR, DEBTOR_PRVTID_OTHRID, "
				+ "DEBTOR_PRVTID_OTHRID_SCHMENM, DEBTOR_PRVTID_OTHR_ISSR, DEBTOR_CONT_PHONE, DEBTOR_CONT_EMADDR, DEBTOR_ACCTNO, DEBTOR_ACCTNO_TYPE, DEBTOR_AGT_ID, "
				+ "DEBTOR_AGT_BIC, CREDITOR_NAME, CREDITOR_ORGID_ANYBIC, CREDITOR_ORGID_OTHRID, CREDITOR_ORGID_OTHRID_SCHMENM, CREDITOR_ORGID_OTHR_ISSR, "
				+ "CREDITOR_PRVTID_OTHRID, CREDITOR_PRVTID_OTHRID_SCHMENM, CREDITOR_PRVTID_OTHR_ISSR, CREDITOR_CONT_PHONE, CREDITOR_CONT_EMADDR, CREDITOR_ACCTNO, "
				+ "CREDITOR_ACCTNO_TYPE, CREDITOR_AGT_ID, CREDITOR_AGT_BIC, PURPOSE_TYPE, PURPOSE_CODE, PURPOSE_OTHER, REMIT_INFO, MSG_INST_DATE, LAST_MODI_DATE, BIZ_SVC_TYPE) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			List<FFPVO_Pacs008_CdtTrfTxInf> txInfList = pacs008.getCdtTrfTxInfList();
			if(txInfList != null && txInfList.size() > 0)
			{
				int count;
				Object[] res = EntityManager.queryArray("SELECT ID FROM TB_BH_INWARD_FPSPYCI ORDER BY ID DESC");//Get max ID	
				if(res == null) count = 0;
				else count = (int)res[0];
				
				int batFlag = 0;
				Object[][] batchObj = new Object[BATCH_SIZE][1];
				for(FFPVO_Pacs008_CdtTrfTxInf txInf : txInfList)
				{
					batFlag+=1;
					List<Object> paramList = new ArrayList<Object>();
					paramList.add(++count);
					paramList.add(batchId);
					paramList.add(FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode());
					paramList.add(fileName);
					paramList.add(txInf.getTxId());
					paramList.add(txInf.getEndToEndId());
					paramList.add(txInf.getClrSysRef());
					paramList.add(txInf.getInstrId());//Instruction ID
					paramList.add(txInf.getCtgyPurp());
					paramList.add(txInf.getLclInstrm());
					paramList.add(txInf.getIntrBkSttlmAmtCcy());
					paramList.add(txInf.getIntrBkSttlmAmt());
					paramList.add(txInf.getIntrBkSttlmDt());
					paramList.add(txInf.getInstdAmtCcy());
					paramList.add(txInf.getInstdAmt());
//					List<ChargeInfo> chargeList = txInf.getChargeInfo();
					
//					if(chargeList != null)
//					{
//						ChargeInfo chrg = chargeList.get(0);
						paramList.add(txInf.getChrgAgentId());//charge info
						paramList.add(txInf.getChrgAgentBic());
						paramList.add(txInf.getChrgCcy());
						paramList.add(txInf.getChrgAmount());
//					}
//					else
//					{
//						paramList.add(null);//charge info
//						paramList.add(null);
//						paramList.add(null);
//						paramList.add(null);
//					}
					paramList.add(txInf.getDbtrNm());
					paramList.add(txInf.getDbtrOrgIdAnyBIC());//DEBTOR_ORGID_ANYBIC
					paramList.add(txInf.getDbtrOrgIdOthrId());//DEBTOR_ORGID_OTHRID
					paramList.add(txInf.getDbtrOrgIdOthrIdSchmeNm());//DEBTOR_ORGID_OTHRID_SCHMENM
					paramList.add(txInf.getDbtrOrgIdOthrIssr());//DEBTOR_ORGID_OTHR_ISSR
					paramList.add(txInf.getDbtrPrvtIdOthrId());//DEBTOR_PRVTID_OTHRID
					paramList.add(txInf.getDbtrPrvtIdOthrIdSchmeNm());//DEBTOR_PRVTID_OTHRID_SCHMENM
					paramList.add(txInf.getDbtrPrvtIdOthrIssr());//DEBTOR_PRVTID_OTHR_ISSR
					paramList.add(txInf.getDbtrMobNb());//DEBTOR_CONT_PHONE
					paramList.add(txInf.getDbtrEmailAdr());//DEBTOR_CONT_EMADDR
					paramList.add(txInf.getDbtrAcctId());
					paramList.add(txInf.getDbtrAccSchmeNm());
					paramList.add(txInf.getDbtrAgClrSysMmbId());
					paramList.add(txInf.getDbtrAgBICFI());
					paramList.add(txInf.getCdtrNm());
					paramList.add(txInf.getCdtrOrgIdAnyBIC());//CREDITOR_ORGID_ANYBIC
					paramList.add(txInf.getCdtrOrgIdOthrId());//CREDITOR_ORGID_OTHRID
					paramList.add(txInf.getCdtrOrgIdOthrIdSchmeNm());//CREDITOR_ORGID_OTHRID_SCHMENM
					paramList.add(txInf.getCdtrOrgIdOthrIssr());//CREDITOR_ORGID_OTHR_ISSR
					paramList.add(txInf.getCdtrPrvtIdOthrId());//CREDITOR_PRVTID_OTHRID
					paramList.add(txInf.getCdtrPrvtIdOthrIdSchmeNm());//CREDITOR_PRVTID_OTHRID_SCHMENM
					paramList.add(txInf.getCdtrPrvtIdOthrIssr());//CREDITOR_PRVTID_OTHR_ISSR
					paramList.add(txInf.getCdtrMobNb());//CREDITOR_CONT_PHONE
					paramList.add(txInf.getCdtrEmailAdr());//CREDITOR_CONT_EMADDR
					paramList.add(txInf.getCdtrAcctId());
					paramList.add(txInf.getCdtrAcctSchmeNm());
					paramList.add(txInf.getCdtrAgtClrSysMmbId());
					paramList.add(txInf.getCdtrAgtBICFI());
					paramList.add(null);						//purpose type
					paramList.add(txInf.getPurpCd());			//purpose code
					paramList.add(txInf.getPurpPrtry());		//purpose other
//					StringBuilder remInfo = new StringBuilder();
//					List<String> remInfoList = txInf.getRmtInf();
//					if (remInfoList != null)
//					{
//						for (int i=1; i<=remInfoList.size(); i++)
//						{
//							remInfo.append(remInfoList.get(i-1));
//							if(i<remInfoList.size()) remInfo.append(",");
//						}
//					}
//					paramList.add(remInfo.toString().trim().length() > 0 ? remInfo.toString().trim() : null);	//remittance info
					paramList.add(txInf.getRmtInf());	//remittance info
					
					paramList.add(new java.sql.Date(new Date().getTime()));
					paramList.add(new java.sql.Timestamp(new Date().getTime()));
					paramList.add(busiSvrType);
					
					batchObj[batFlag - 1] = paramList.toArray();
					if(batFlag % BATCH_SIZE == 0)
					{
						EntityManager.batch(sql, batchObj);
						batchObj = new Object[BATCH_SIZE][1];
						batFlag = 0;
					}
					
					//EntityManager.update(sql, paramList.toArray());
				}
				if(batFlag > 0)
				{
					Object[][] remObj = new Object[batFlag][1];
					System.arraycopy(batchObj, 0, remObj, 0, batFlag);
					EntityManager.batch(sql, remObj);
				}
			}
		}
		catch(Exception ex)
		{
			batchLogger.error(String.format("Temporarily persistent batch file[%s] data error", fileName), ex);
			throw ex;
		}
	}
	
	private FFPVO_Pacs008 parse008(com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document pacs008) throws Exception 
	{
		FFPVO_Pacs008 pacs008MsgVo = new FFPVO_Pacs008();
		pacs008MsgVo.setMsgId(pacs008.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());
		pacs008MsgVo.setCreDtTm(pacs008.getFIToFICstmrCdtTrf().getGrpHdr().getCreDtTm().toGregorianCalendar().getTime());
		String numberOfTx = pacs008.getFIToFICstmrCdtTrf().getGrpHdr().getNbOfTxs();
		pacs008MsgVo.setNbOfTxs(numberOfTx);
		pacs008MsgVo.setSttlmMtd(pacs008.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().getSttlmMtd().value());
		pacs008MsgVo.setClrSys(pacs008.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().getClrSys().getPrtry().value());
		List<CreditTransferTransaction251> list = pacs008.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
		List<FFPVO_Pacs008_CdtTrfTxInf> pacs008TxInf = new ArrayList<FFPVO_Pacs008_CdtTrfTxInf>();
		pacs008MsgVo.setCdtTrfTxInfList(pacs008TxInf);
			
		//check message number
		/*if(numberOfTx.compareTo(String.valueOf(list.size())) != 0)
		{
			//logger
			//TBC, throw exception,the file data is invalid
			batchLogger.info(String.format("The numberOfTx[%s] not match with the actual Transaction messages[%s] in the bizData of file[fileName:%s]", 
														numberOfTx, list.size(), fileName));
			throw new Exception("The numberOfTx not match with the actual Transaction messages in the file");
		}*/
			
		for(CreditTransferTransaction251 ct251 : list)
		{
			FFPVO_Pacs008_CdtTrfTxInf cdtTxInfo = new FFPVO_Pacs008_CdtTrfTxInf();
			pacs008TxInf.add(cdtTxInfo);
			cdtTxInfo.setInstrId(ct251.getPmtId().getInstrId());
			cdtTxInfo.setEndToEndId(ct251.getPmtId().getEndToEndId());
			cdtTxInfo.setTxId(ct251.getPmtId().getTxId());
			cdtTxInfo.setClrSysRef(ct251.getPmtId().getClrSysRef());
			//payment type information
			if(ct251.getPmtTpInf() != null && ct251.getPmtTpInf().getLclInstrm() != null)
			{
				cdtTxInfo.setLclInstrm(ct251.getPmtTpInf().getLclInstrm().getPrtry().value()); //FPS skip this verification for received mode is batch
				if(ct251.getPmtTpInf().getCtgyPurp() != null)
				{
					cdtTxInfo.setCtgyPurp(ct251.getPmtTpInf().getCtgyPurp().getPrtry().value());
				}
			}
				
			cdtTxInfo.setIntrBkSttlmAmt(String.valueOf(ct251.getIntrBkSttlmAmt().getValue()));
			cdtTxInfo.setIntrBkSttlmAmtCcy(ct251.getIntrBkSttlmAmt().getCcy().value());
				
			if(ct251.getIntrBkSttlmDt() != null)
			{
				cdtTxInfo.setIntrBkSttlmDt(ct251.getIntrBkSttlmDt().toGregorianCalendar().getTime());
			}
			if(ct251.getSttlmTmIndctn() != null && ct251.getSttlmTmIndctn().getCdtDtTm() != null)
			{
//				cdtTxInfo.setCdtDtTm(ct251.getSttlmTmIndctn().getCdtDtTm().toGregorianCalendar().getTime());
			}
			if(ct251.getInstdAmt() != null)
			{
				cdtTxInfo.setInstdAmt(String.valueOf(ct251.getInstdAmt().getValue()));
				cdtTxInfo.setInstdAmtCcy(ct251.getInstdAmt().getCcy().value());
			}
				
			//charge info.
			cdtTxInfo.setChrgBr(ct251.getChrgBr().value());
			//TBC
			if(ct251.getChrgsInf() != null)
			{
//				ChargeInfo chrgInfo = cdtTxInfo.new ChargeInfo();
//				List<ChargeInfo> chargeInfoList = new ArrayList<ChargeInfo>();
				if(ct251.getChrgsInf().getAmt() != null)
				{
					cdtTxInfo.setChrgAmount(ct251.getChrgsInf().getAmt().getValue());
					cdtTxInfo.setChrgCcy(ct251.getChrgsInf().getAmt().getCcy().value());
				}
				cdtTxInfo.setChrgAgentBic(ct251.getChrgsInf().getAgt().getFinInstnId().getBICFI());
				cdtTxInfo.setChrgAgentId(ct251.getChrgsInf().getAgt().getFinInstnId() != null ? ct251.getChrgsInf().getAgt().getFinInstnId().getClrSysMmbId().getMmbId() : null);
////				chargeInfoList.add(chrgInfo);
//				cdtTxInfo.setChargeInfo(chrgInfo);
			}
				
			//Debtor
			cdtTxInfo.setDbtrNm(ct251.getDbtr().getNm());
			if(ct251.getDbtr().getId() != null)
			{
				if(ct251.getDbtr().getId().getOrgId() != null)
				{
					cdtTxInfo.setDbtrOrgIdAnyBIC(ct251.getDbtr().getId().getOrgId().getAnyBIC());
					GenericOrganisationIdentification11 other = ct251.getDbtr().getId().getOrgId().getOthr();
					if(other != null) //0..*,maybe a collection
					{
//						DbtrOrgIdOth dbtrOrgOth = cdtTxInfo.new DbtrOrgIdOth();
//						List<DbtrOrgIdOth> dbtrOrgOthList = new ArrayList<DbtrOrgIdOth>();
//						
//						dbtrOrgOth.setDbtrOrgIdSchmeNm(other.getSchmeNm() != null ? other.getSchmeNm().getCd().value() : null);
//						dbtrOrgOth.setDbtrOrgIdIssr(other.getIssr() != null ? other.getIssr() : null);
////						dbtrOrgOthList.add(dbtrOrgOth);
//						cdtTxInfo.setDbtrOrgIdOth(dbtrOrgOth);
						cdtTxInfo.setDbtrOrgIdOthrId(other.getId());
						cdtTxInfo.setDbtrOrgIdOthrIdSchmeNm(other.getSchmeNm() != null ? other.getSchmeNm().getCd().value() : null);
						cdtTxInfo.setDbtrOrgIdOthrIssr(other.getIssr() != null ? other.getIssr() : null);
					}
				}
					
				if(ct251.getDbtr().getId().getPrvtId() != null)
				{
					GenericPersonIdentification11 prvtOther = ct251.getDbtr().getId().getPrvtId().getOthr();
					if(prvtOther != null)
					{
//						DbtrPrvtIdOth dbtrPrvtOth = cdtTxInfo.new DbtrPrvtIdOth();
//						List<DbtrPrvtIdOth> dbtrPrvtOthList = new ArrayList<DbtrPrvtIdOth>();
//						dbtrPrvtOth.setDbtrPrvtIdId(prvtOther.getId());
//						dbtrPrvtOth.setDbtrPrvtIdSchmeNm(prvtOther.getSchmeNm() != null ? prvtOther.getSchmeNm().getCd().value() : null);
//						dbtrPrvtOth.setDbtrPrvtIdIssr(prvtOther.getIssr() != null ? prvtOther.getIssr() : null);
//						dbtrPrvtOthList.add(dbtrPrvtOth);
//						cdtTxInfo.setDbtrPrvtIdOth(dbtrPrvtOth);
						cdtTxInfo.setDbtrPrvtIdOthrId(prvtOther.getId());
						cdtTxInfo.setDbtrPrvtIdOthrIdSchmeNm(prvtOther.getSchmeNm() != null ? prvtOther.getSchmeNm().getCd().value() : null);
						cdtTxInfo.setDbtrPrvtIdOthrIssr(prvtOther.getIssr());
					}
				}
			}
				
			cdtTxInfo.setDbtrMobNb(ct251.getDbtr().getCtctDtls() != null ? ct251.getDbtr().getCtctDtls().getMobNb() : null);
			cdtTxInfo.setDbtrEmailAdr(ct251.getDbtr().getCtctDtls() != null ? ct251.getDbtr().getCtctDtls().getEmailAdr() : null);
				
			//Debtor Account and Debtor Agent
			CashAccount241 dbtAcc = ct251.getDbtrAcct();
			BranchAndFinancialInstitutionIdentification51 dbtAgt = ct251.getDbtrAgt();
			if(dbtAcc != null)
			{
				cdtTxInfo.setDbtrAcctId(dbtAcc.getId().getOthr().getId());
				cdtTxInfo.setDbtrAccSchmeNm(dbtAcc.getId().getOthr().getSchmeNm() != null ? dbtAcc.getId().getOthr().getSchmeNm().getPrtry().value() : null);
			}
				
			if(dbtAgt != null)
			{
				cdtTxInfo.setDbtrAgBICFI(dbtAgt.getFinInstnId().getBICFI());
				cdtTxInfo.setDbtrAgClrSysMmbId(dbtAgt.getFinInstnId().getClrSysMmbId() != null ? dbtAgt.getFinInstnId().getClrSysMmbId().getMmbId() : null);
			}
				
			//Creditor Agent
			BranchAndFinancialInstitutionIdentification51 cdtrAgt = ct251.getCdtrAgt();
			if(cdtrAgt != null)
			{
				cdtTxInfo.setCdtrAgtBICFI(cdtrAgt.getFinInstnId().getBICFI());
				cdtTxInfo.setCdtrAgtClrSysMmbId(cdtrAgt.getFinInstnId().getClrSysMmbId() != null ? cdtrAgt.getFinInstnId().getClrSysMmbId().getMmbId() : null);
			}
			//Creditor
			cdtTxInfo.setCdtrNm(ct251.getCdtr().getNm());
			if(ct251.getCdtr().getId() != null)
			{
				if(ct251.getCdtr().getId().getOrgId() != null)
				{
					cdtTxInfo.setCdtrOrgIdAnyBIC(ct251.getCdtr().getId().getOrgId().getAnyBIC());
					GenericOrganisationIdentification11 other = ct251.getCdtr().getId().getOrgId().getOthr();
					if(other != null) //0..*,maybe a collection
					{
//						CdtrOrgIdOth cdtrOrgOth = cdtTxInfo.new CdtrOrgIdOth();
//						List<CdtrOrgIdOth> cdtrOrgOthList = new ArrayList<CdtrOrgIdOth>();
//						cdtrOrgOth.setCdtrOrgIdId(other.getId());
//						cdtrOrgOth.setCdtrOrgIdSchmeNm(other.getSchmeNm() != null ? other.getSchmeNm().getCd().value() : null);
//						cdtrOrgOth.setCdtrOrgIdIssr(other.getIssr() != null ? other.getIssr() : null);
//						cdtrOrgOthList.add(cdtrOrgOth);
//						cdtTxInfo.setCdtrOrgIdOth(cdtrOrgOth);
						cdtTxInfo.setCdtrOrgIdOthrId(other.getId());
						cdtTxInfo.setCdtrOrgIdOthrIdSchmeNm(other.getSchmeNm() != null ? other.getSchmeNm().getCd().value() : null);
						cdtTxInfo.setCdtrOrgIdOthrIssr(other.getIssr());
					}
				}
				
				if(ct251.getCdtr().getId().getPrvtId() != null)
				{
					GenericPersonIdentification11 prvtOther = ct251.getCdtr().getId().getPrvtId().getOthr();
					if(prvtOther != null)
					{
//						CdtrPrvtIdOth cdtrPrvtOth = cdtTxInfo.new CdtrPrvtIdOth();
//						List<CdtrPrvtIdOth> cdtrPrvtOthList = new ArrayList<CdtrPrvtIdOth>();
//						cdtrPrvtOth.setCdtrPrvtIdId(prvtOther.getId());
//						cdtrPrvtOth.setCdtrPrvtIdSchmeNm(prvtOther.getSchmeNm() != null ? prvtOther.getSchmeNm().getCd().value() : null);
//						cdtrPrvtOth.setCdtrPrvtIdIssr(prvtOther.getIssr() != null ? prvtOther.getIssr() : null);
//						cdtrPrvtOthList.add(cdtrPrvtOth);
//						cdtTxInfo.setCdtrPrvtIdOth(cdtrPrvtOth);
						cdtTxInfo.setCdtrPrvtIdOthrId(prvtOther.getId());
						cdtTxInfo.setCdtrPrvtIdOthrIdSchmeNm(prvtOther.getSchmeNm() != null ? prvtOther.getSchmeNm().getCd().value() : null);
						cdtTxInfo.setCdtrPrvtIdOthrIssr(prvtOther.getIssr());				
					}
				}
			}
				
			cdtTxInfo.setCdtrMobNb(ct251.getCdtr().getCtctDtls() != null ? ct251.getCdtr().getCtctDtls().getMobNb() : null);
			cdtTxInfo.setCdtrEmailAdr(ct251.getCdtr().getCtctDtls() != null ? ct251.getCdtr().getCtctDtls().getEmailAdr() : null);
				
			//Creditor Account
			CashAccount241 cbtrAcc = ct251.getCdtrAcct();
			if(cbtrAcc != null)
			{
				cdtTxInfo.setCdtrAcctId(cbtrAcc.getId().getOthr().getId());
				cdtTxInfo.setCdtrAcctSchmeNm(cbtrAcc.getId().getOthr().getSchmeNm() !=null ? cbtrAcc.getId().getOthr().getSchmeNm().getPrtry().value() : null);
			}
				
			//purpose
			if(ct251.getPurp() != null)
			{
				cdtTxInfo.setPurpCd(ct251.getPurp().getCd());
				cdtTxInfo.setPurpPrtry(ct251.getPurp().getPrtry());
			}
				
			//Remittance information
			if(ct251.getRmtInf() != null && ct251.getRmtInf().getUstrd() != null)
			{
			
				cdtTxInfo.setRmtInf(ct251.getRmtInf().getUstrd());
			}
		}
			
		return pacs008MsgVo;
	}
	
	private Map<String, List<ISO20022BusinessDataV01>> readFile(List<File> fileList) throws Exception
	{
		//fileName:FPSPYCIkkkYYYYMMDDhhmmnnnn.xml
		//content:credit transfer(pacs008),return/refund(pacs004),notification(camt054)
		batchLogger.info("Started read inward batch file");
		Map<String, List<ISO20022BusinessDataV01>> validBusData = new HashMap<String, List<ISO20022BusinessDataV01>>();
		if(fileList != null)
		{
			//2. validate number of files in this batch time
			Map<String, FpsMessageEnvelope> validFiles = validateInwardFiles(fileList);
			
			Iterator<String> fileNameIt = validFiles.keySet().iterator();
			while(fileNameIt.hasNext())
			{
				String fileName = fileNameIt.next();
				FpsMessageEnvelope xmlFileData = validFiles.get(fileName);
				String batchId = xmlFileData.getBtchInf().getBtchId();
				String fileName_batchId = String.format("%s-%s", fileName, batchId);
				//3.validate message counts are correctly with each valid files
				String msgCount = xmlFileData.getNbOfMsgs();//get BizData count of each file
				List<ISO20022BusinessDataV01> bisDataList = xmlFileData.getFpsPylds().getBizData();
				if(msgCount.compareTo(String.valueOf(bisDataList.size())) != 0)
				{
					batchLogger.info(String.format("The file contains invalid message counts:FileName[%s]-NoOfMsgs[%s]-ActualMsgsCount[%s]", 
														fileName, msgCount, String.valueOf(bisDataList.size())));
				}
				else
				{
					validBusData.put(fileName_batchId, bisDataList);
				}
			}
			
			batchLogger.info("Ended read inward batch file");
		}
		return validBusData;
	}
	
	//Remove invalid files which batchId & numberOfFile not match with the actual number of file.
	public Map<String, FpsMessageEnvelope> validateInwardFiles(List<File> fileList) throws Exception
	{
		if(fileList.size() == 0) return new HashMap<String, FpsMessageEnvelope>();
		Map<String, FpsMessageEnvelope> validFileData = new HashMap<String, FpsMessageEnvelope>();
		Map<String, FpsMessageEnvelope> mapFile = new HashMap<String, FpsMessageEnvelope>();
		List<String> keyCount = new ArrayList<String>();
		
		for(File xmlFile : fileList)
		{
			try
			{
				FpsMessageEnvelope result = FFPHkiclMessageConverter.parseObject(xmlFile);
				
				BatchInformation batchInfo = result.getBtchInf();
				String batchID = batchInfo.getBtchId();
				String numberOfFile = batchInfo.getNbOfFls();
				//check batchId & numberOfFile, maybe numberOfFile>1, so more than one file contains the same batch ID.
				String keyFileIdStr = String.format("BatchId[%s]-NumberOfFile[%s]-fileName[%s]", batchID, numberOfFile, xmlFile.getName());
				String keyCountStr = String.format("BatchId[%s]-NumberOfFile[%s]", batchID, numberOfFile);
				batchLogger.info(String.format("File info:%s", keyFileIdStr));
				if(Integer.valueOf(numberOfFile) > 1)
				{
					mapFile.put(keyFileIdStr, result);
					keyCount.add(keyCountStr);
				}
				else
				{
					//add a single batch file
					validFileData.put(xmlFile.getName(), result);
				}
			}
			catch(Exception ex)
			{
				batchLogger.error(String.format("Error on parsing file[fileName:%s]",xmlFile.getName()), ex);
				throw ex;
			}
			
		}
		
		Comparator<String> comp = new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareTo(arg1);
			}
		}; 
		
		Collections.sort(keyCount, comp);
		
		//Calculate count
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for(String str : keyCount)
		{
			if(countMap.containsKey(str)) countMap.put(str, countMap.get(str) + 1);
			else countMap.put(str, 1);
		}
		
		//Remove invalid file
		List<String> validKey = new ArrayList<String>();
		Iterator<String> iteStr = countMap.keySet().iterator();
		while(iteStr.hasNext())
		{
			String countKey = iteStr.next();
			Integer countValue = countMap.get(countKey);
			//whether count matches file No. or not
			if(countKey.substring(countKey.lastIndexOf("[") + 1, countKey.lastIndexOf("]")).compareTo(String.valueOf(countValue)) != 0)
			{
				//Not match
				batchLogger.info(String.format("The files contain %s not matched with actual number of files!", countKey));
			}
			else
			{
				validKey.add(countKey);//add matched key info. of files
			}
		}
		
		
		Iterator<String> iter = mapFile.keySet().iterator();
		while(iter.hasNext())
		{
			String key = iter.next();
			if(validKey.contains(key.substring(0, key.lastIndexOf("-"))))
			{
				validFileData.put(key.substring(key.lastIndexOf("[") + 1, key.lastIndexOf("]")), mapFile.get(key));
			}
		}
		
		//Validate sign digital certificate
		/*for(File certFile : fileList)
		{
			if(validFileData.containsKey(certFile.getName()))
			{
				if(!FFPCheckSignDigitalCertsUtils.checkFPSSignDigitalCertificate(certFile))
				{
					batchLogger.warn(String.format("Invalid sign digital certificate of inward credit transfer file[file_name:%s]", certFile.getName()));
					
					validFileData.remove(certFile.getName());
					continue;
				}
			}
		}*/
		
		return validFileData;
	}
	
	private FFPVO_Pacs004 parse004(com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.Document pacs004)
	{
		FFPVO_Pacs004 pacs004MsgVo = new FFPVO_Pacs004();
		pacs004MsgVo.setMsgId(pacs004.getPmtRtr().getGrpHdr().getMsgId());
		pacs004MsgVo.setCreDtTm(pacs004.getPmtRtr().getGrpHdr().getCreDtTm().toString());
		String numberOfTx = pacs004.getPmtRtr().getGrpHdr().getNbOfTxs();
		pacs004MsgVo.setNbOfTxs(numberOfTx);
		pacs004MsgVo.setSttlmMtd(pacs004.getPmtRtr().getGrpHdr().getSttlmInf().getSttlmMtd().value());
		pacs004MsgVo.setClrSys(pacs004.getPmtRtr().getGrpHdr().getSttlmInf().getClrSys().getPrtry().value());
		
		List<FFPVO_Pacs004_TxInf> pacs004TxInf = new ArrayList<FFPVO_Pacs004_TxInf>();
		pacs004MsgVo.setTxinf(pacs004TxInf);
		List<PaymentTransaction761> list = pacs004.getPmtRtr().getTxInf();
		
		for(PaymentTransaction761 tx761 : list)
		{
			FFPVO_Pacs004_TxInf returnInfo = new FFPVO_Pacs004_TxInf();
			FFPJbP300 p300 = new FFPJbP300();
			FFPTxJnl txJnl = new FFPTxJnl();
			
			p300.setReturnId(tx761.getRtrId());
			//p300
			txJnl.setEndToEndId(tx761.getOrgnlEndToEndId());
			txJnl.setTransactionId(tx761.getOrgnlTxId());
			txJnl.setFpsRefNm(tx761.getOrgnlClrSysRef());
			p300.setTxJnl(txJnl);
			
			p300.setRetIntSetAmt(new BigDecimal(String.valueOf(tx761.getRtrdIntrBkSttlmAmt().getValue())));
			p300.setRetIntSetCur(tx761.getRtrdIntrBkSttlmAmt().getCcy().value());
			p300.setSettlementDate(tx761.getIntrBkSttlmDt() != null ? tx761.getIntrBkSttlmDt().toGregorianCalendar().getTime() : null);
			
//			returnInfo.setRtrId(tx761.getRtrId());
//			returnInfo.setOrgnlInstrId(tx761.getOrgnlInstrId());//
//			returnInfo.setOrgnlEndToEndId(tx761.getOrgnlEndToEndId());
//			returnInfo.setOrgnlTxId(tx761.getOrgnlTxId());
//			returnInfo.setOrgnlClrSysRef(tx761.getOrgnlClrSysRef());
//			returnInfo.setRtrdIntrBkSttlmAmt(String.valueOf(tx761.getRtrdIntrBkSttlmAmt().getValue()));
//			returnInfo.setRtrdIntrBkSttlmCcy(tx761.getRtrdIntrBkSttlmAmt().getCcy().value());
//			returnInfo.setIntrBkSttlmDt(tx761.getIntrBkSttlmDt() != null ? tx761.getIntrBkSttlmDt().toString() : null);
			if(tx761.getRtrdInstdAmt() != null)
			{
				p300.setRetInsAmt(new BigDecimal(String.valueOf(tx761.getRtrdInstdAmt().getValue())));
				p300.setRetInsCur(tx761.getRtrdInstdAmt().getCcy().value());
				//returnInfo.setRtrdInstdAmt(String.valueOf(tx761.getRtrdInstdAmt().getValue()));
				//returnInfo.setRtrdInstdCcy(tx761.getRtrdInstdAmt().getCcy().value());
			}
			
			
			//returnInfo.setChrgBr(tx761.getChrgBr() != null ? tx761.getChrgBr().value() : null);
			//TBC
			if(tx761.getChrgsInf() != null)
			{
				if(tx761.getChrgsInf().getAmt() != null)
				{
					p300.setChargersAmount(new BigDecimal(String.valueOf(tx761.getChrgsInf().getAmt().getValue())));
					p300.setChargersCurrency(tx761.getChrgsInf().getAmt().getCcy().value());
					//returnInfo.setChrgsInfAmt(String.valueOf(tx761.getChrgsInf().getAmt().getValue()));
					//returnInfo.setChrgsInfCcy(tx761.getChrgsInf().getAmt().getCcy().value());
				}
				p300.setChgAgtBIC(tx761.getChrgsInf().getAgt().getFinInstnId().getBICFI());
				p300.setChgAgtID(tx761.getChrgsInf().getAgt().getFinInstnId() != null ? tx761.getChrgsInf().getAgt().getFinInstnId().getClrSysMmbId().getMmbId() : null);
				//returnInfo.setChrgsInfAgtBICFI(tx761.getChrgsInf().getAgt().getFinInstnId().getBICFI());
				//returnInfo.setChrgsInfAgtClrSysId(tx761.getChrgsInf().getAgt().getFinInstnId() != null ? tx761.getChrgsInf().getAgt().getFinInstnId().getClrSysMmbId().getMmbId() : null);
			}
			
			if(tx761.getRtrRsnInf() != null)
			{
				p300.setReasonCode(tx761.getRtrRsnInf().getRsn() != null ? tx761.getRtrRsnInf().getRsn().getPrtry() : null);

				StringBuffer reason = new StringBuffer();
				List<String> reasonList = tx761.getRtrRsnInf().getAddtlInf();
				if (reasonList != null)
				{
					for (int i=1; i<=reasonList.size(); i++)
					{
						reason.append(reasonList.get(i-1));
						if(i<reasonList.size()) reason.append(",");
					}
				}
				p300.setAdditionalInformation(reason.toString().trim().length() > 0 ? reason.toString().trim() : null);
				//returnInfo.setRtrRsnReturnCode(tx761.getRtrRsnInf().getRsn() != null ? tx761.getRtrRsnInf().getRsn().getPrtry() : null);
				//returnInfo.setRtrAddtlInf(tx761.getRtrRsnInf().getAddtlInf());
			}
			
			OriginalTransactionReference241 orglTxRef = tx761.getOrgnlTxRef();
			if(orglTxRef != null) 
			{
				p300.setOrgnlInterbankSettAmt(new BigDecimal(String.valueOf(orglTxRef.getIntrBkSttlmAmt().getValue())));
				p300.setOrgnlInterbankSettCcy(orglTxRef.getIntrBkSttlmAmt().getCcy().value());
				p300.setOrgnlInterbankSettDate(orglTxRef.getIntrBkSttlmDt() != null ? orglTxRef.getIntrBkSttlmDt().toGregorianCalendar().getTime() : null);
				p300.setOrgnlCatgyPurp(orglTxRef.getPmtTpInf().getCtgyPurp().getPrtry().value());
				p300.setOrgnlMandateInfo(orglTxRef.getMndtRltdInf() != null ? orglTxRef.getMndtRltdInf().getMndtId() : null);
				p300.setOrgnlRemtInfo(orglTxRef.getRmtInf() != null ? orglTxRef.getRmtInf().getUstrd() : null);
				
				p300.setOrgnlDbtrNm(orglTxRef.getDbtr().getNm());
				p300.setOrgnlDbtrAcctNo(orglTxRef.getDbtrAcct().getId().getOthr().getId());
				p300.setOrgnlDbtrAcctNoTp(orglTxRef.getDbtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
				p300.setOrgnlDbtrAgtBIC(orglTxRef.getDbtrAgt().getFinInstnId().getBICFI());
				p300.setOrgnlDbtrAgtId(orglTxRef.getDbtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
				
				p300.setOrgnlCdtrNm(orglTxRef.getCdtr().getNm());
				p300.setOrgnlCdtrAcctNo(orglTxRef.getCdtrAcct().getId().getOthr().getId());
				p300.setOrgnlCdtrAcctNoTp(orglTxRef.getCdtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
				p300.setOrgnlCdtrAgtBIC(orglTxRef.getCdtrAgt().getFinInstnId().getBICFI());
				p300.setOrgnlCdtrAgtId(orglTxRef.getCdtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
//				returnInfo.setOrgnIntrBkSttlAmt(String.valueOf(orglTxRef.getIntrBkSttlmAmt().getValue()));
//				returnInfo.setOrgnIntrBkSttlCcy(orglTxRef.getIntrBkSttlmAmt().getCcy().value());
//				returnInfo.setOrgnIntrBkSttlDt(orglTxRef.getIntrBkSttlmDt() != null ? orglTxRef.getIntrBkSttlmDt().toString() : null);
//				returnInfo.setOrgnCatyPurp(orglTxRef.getPmtTpInf().getCtgyPurp().getPrtry().value());
//				returnInfo.setOrgnMandateInfo(orglTxRef.getMndtRltdInf() != null ? orglTxRef.getMndtRltdInf().getMndtId() : null);
//				returnInfo.setOrgnRemtInfo(orglTxRef.getRmtInf() != null ? orglTxRef.getRmtInf().getUstrd() : null);
//				returnInfo.setOrgnDbtrNm(orglTxRef.getDbtr().getNm());
//				returnInfo.setOrgnDbtrAccNo(orglTxRef.getDbtrAcct().getId().getOthr().getId());
//				returnInfo.setOrgnDbtrAccType(orglTxRef.getDbtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
//				returnInfo.setOrgnDbtrAgtBic(orglTxRef.getDbtrAgt().getFinInstnId().getBICFI());
//				returnInfo.setOrgnDbtrAgtId(orglTxRef.getDbtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
//				
//				returnInfo.setOrgnCdtrNm(orglTxRef.getCdtr().getNm());
//				returnInfo.setOrgnCdtrAccNo(orglTxRef.getCdtrAcct().getId().getOthr().getId());
//				returnInfo.setOrgnCdtrAccType(orglTxRef.getCdtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
//				returnInfo.setOrgnCdtrAgtBic(orglTxRef.getCdtrAgt().getFinInstnId().getBICFI());
//				returnInfo.setOrgnCdtrAgtId(orglTxRef.getCdtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
			}
			returnInfo.setP300(p300);
			pacs004TxInf.add(returnInfo);
		}
		
		return pacs004MsgVo;
	}
	
	private FFPVOCamt054 parse054(com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.Document doc054)
	{
		FFPVOCamt054 camt054MsgVo = new FFPVOCamt054();
		
		BankToCustomerDebitCreditNotificationV06  noti = doc054.getBkToCstmrDbtCdtNtfctn();
		
		camt054MsgVo.setMsgId(noti.getGrpHdr().getMsgId());
		camt054MsgVo.setMsgCreateTs(noti.getGrpHdr().getCreDtTm().toGregorianCalendar().getTime());
		
		if(noti.getNtfctn() != null)
		{
			for(AccountNotification121 acctNoti : noti.getNtfctn())
			{
				FFPDtFpsPaymentNotification ntfctn = new FFPDtFpsPaymentNotification();
				ntfctn.setMsgId(noti.getGrpHdr().getMsgId());
				ntfctn.setMsgCreateTs(noti.getGrpHdr().getCreDtTm().toGregorianCalendar().getTime());
				
				ntfctn.setNtfctnId(acctNoti.getId());
				ntfctn.setNtfctnCreateTs(acctNoti.getCreDtTm().toGregorianCalendar().getTime());
				ntfctn.setNtfctnAcctId(acctNoti.getAcct().getId().getOthr().getId());
				ntfctn.setNtfctnAcctType(acctNoti.getAcct().getTp().getCd().value());
				
				ntfctn.setNtryAmt(acctNoti.getNtry().getAmt().getValue());
				ntfctn.setNtryAmtCcy(acctNoti.getNtry().getAmt().getCcy().value());
				ntfctn.setNtryCdtDbtInd(acctNoti.getNtry().getCdtDbtInd().value());
				ntfctn.setNtryStatus(acctNoti.getNtry().getSts().value());
				ntfctn.setNtryBankTransCode(acctNoti.getNtry().getBkTxCd().getPrtry().getCd().value());
				
				EntryTransaction81 transDetail = acctNoti.getNtry().getNtryDtls().getTxDtls();
				ntfctn.setNtryDetailEndToEndId(transDetail.getRefs().getEndToEndId());
				ntfctn.setNtryDetailTxId(transDetail.getRefs().getTxId());
				ntfctn.setNtryDetailMandateId(transDetail.getRefs().getMndtId());
				ntfctn.setNtryDetailClrSysRef(transDetail.getRefs().getClrSysRef());
				
				ntfctn.setNtryDetailAmt(transDetail.getAmt().getValue());
				ntfctn.setNtryDetailAmtCcy(transDetail.getAmt().getCcy().value());
				ntfctn.setNtryDetailCdtDbtInd(transDetail.getCdtDbtInd().value());
				
				if(transDetail.getChrgs() != null)
				{
					ChargesRecord21 chargesRecord = transDetail.getChrgs().getRcrd();
					ntfctn.setNtryDetailChrgsAmt(chargesRecord.getAmt().getValue());
					ntfctn.setNtryDetailChrgsAmtCcy(chargesRecord.getAmt().getCcy().value());
					ntfctn.setNtryDetailChrgsBr(chargesRecord.getBr().value());
					if(chargesRecord.getAgt() != null)
					{
						ntfctn.setNtryDetailChrgsAgtBic(chargesRecord.getAgt().getFinInstnId().getBICFI());
						ntfctn.setNtryDetailChrgsAgtMmbId(chargesRecord.getAgt().getFinInstnId().getClrSysMmbId().getMmbId());
					}
				}
				
				if(transDetail.getRltdPties() != null)
				{
					// Debit
					ntfctn.setRltdPtiesDbtrName(transDetail.getRltdPties().getDbtr().getNm());
					if(transDetail.getRltdPties().getDbtr().getId() != null)
					{
						if(transDetail.getRltdPties().getDbtr().getId().getOrgId() != null)
						{
							ntfctn.setRltdPtiesDbtrOrgIdBIC(transDetail.getRltdPties().getDbtr().getId().getOrgId().getAnyBIC());
							if(transDetail.getRltdPties().getDbtr().getId().getOrgId().getOthr() != null)
							{
								ntfctn.setRltdPtiesDbtrOrgIdOthrId(transDetail.getRltdPties().getDbtr().getId().getOrgId().getOthr().getId());
								ntfctn.setRltdPtiesDbtrOrgIdOthrSchme(transDetail.getRltdPties().getDbtr().getId().getOrgId().getOthr().getSchmeNm().getCd().value());
								ntfctn.setRltdPtiesDbtrOrgIdOthrIssr(transDetail.getRltdPties().getDbtr().getId().getOrgId().getOthr().getIssr());
							}
						}
						else if(transDetail.getRltdPties().getDbtr().getId().getPrvtId() != null)
						{
							ntfctn.setRltdPtiesDbtrPrvtIdOthrId(transDetail.getRltdPties().getDbtr().getId().getPrvtId().getOthr().getId());
							ntfctn.setRltdPtiesDbtrPrvtIdOthrSchme(transDetail.getRltdPties().getDbtr().getId().getPrvtId().getOthr().getSchmeNm().getCd().value());
							ntfctn.setRltdPtiesDbtrPrvtIdOthrIssr(transDetail.getRltdPties().getDbtr().getId().getPrvtId().getOthr().getIssr());
						}
					}
					
					if(transDetail.getRltdPties().getDbtr().getCtctDtls() != null)
					{
						ntfctn.setRltdPtiesDbtrContactMobile(transDetail.getRltdPties().getDbtr().getCtctDtls().getMobNb());
						ntfctn.setRltdPtiesDbtrContactEmail(transDetail.getRltdPties().getDbtr().getCtctDtls().getEmailAdr());
					}
					
					if(transDetail.getRltdPties().getDbtrAcct() != null)
					{
						ntfctn.setRltdPtiesDbtrAcctId(transDetail.getRltdPties().getDbtrAcct().getId().getOthr().getId());
						ntfctn.setRltdPtiesDbtrAcctScheme(transDetail.getRltdPties().getDbtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
					}
					
					// Credit
					ntfctn.setRltdPtiesCdtrName(transDetail.getRltdPties().getCdtr().getNm());
					if(transDetail.getRltdPties().getCdtr().getId() != null)
					{
						if(transDetail.getRltdPties().getCdtr().getId().getOrgId() != null)
						{
							ntfctn.setRltdPtiesCdtrOrgIdBIC(transDetail.getRltdPties().getCdtr().getId().getOrgId().getAnyBIC());
							if(transDetail.getRltdPties().getCdtr().getId().getOrgId().getOthr() != null)
							{
								ntfctn.setRltdPtiesCdtrOrgIdOthrId(transDetail.getRltdPties().getCdtr().getId().getOrgId().getOthr().getId());
								ntfctn.setRltdPtiesCdtrOrgIdOthrSchme(transDetail.getRltdPties().getCdtr().getId().getOrgId().getOthr().getSchmeNm().getCd().value());
								ntfctn.setRltdPtiesCdtrOrgIdOthrIssr(transDetail.getRltdPties().getCdtr().getId().getOrgId().getOthr().getIssr());
							}
						}
						else if(transDetail.getRltdPties().getCdtr().getId().getPrvtId() != null)
						{
							ntfctn.setRltdPtiesCdtrPrvtIdOthrId(transDetail.getRltdPties().getCdtr().getId().getPrvtId().getOthr().getId());
							ntfctn.setRltdPtiesCdtrPrvtIdOthrSchme(transDetail.getRltdPties().getCdtr().getId().getPrvtId().getOthr().getSchmeNm().getCd().value());
							ntfctn.setRltdPtiesCdtrPrvtIdOthrIssr(transDetail.getRltdPties().getCdtr().getId().getPrvtId().getOthr().getIssr());
						}
					}
					
					if(transDetail.getRltdPties().getCdtr().getCtctDtls() != null)
					{
						ntfctn.setRltdPtiesCdtrContactMobile(transDetail.getRltdPties().getCdtr().getCtctDtls().getMobNb());
						ntfctn.setRltdPtiesCdtrContactEmail(transDetail.getRltdPties().getCdtr().getCtctDtls().getEmailAdr());
					}
					
					if(transDetail.getRltdPties().getCdtrAcct() != null)
					{
						ntfctn.setRltdPtiesCdtrAcctId(transDetail.getRltdPties().getCdtrAcct().getId().getOthr().getId());
						ntfctn.setRltdPtiesCdtrAcctScheme(transDetail.getRltdPties().getCdtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
					}
				}
				
				TransactionAgents31 transAgent = transDetail.getRltdAgts();
				ntfctn.setRelatedAgentsDbtrBIC(transAgent.getDbtrAgt().getFinInstnId().getBICFI());
				ntfctn.setRelatedAgentsDbtrMmbId(transAgent.getDbtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
				ntfctn.setRelatedAgentsCdtrBIC(transAgent.getCdtrAgt().getFinInstnId().getBICFI());
				ntfctn.setRelatedAgentsCdtrMmbId(transAgent.getCdtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
				
				if(transDetail.getPurp() != null)
				{
					ntfctn.setPurpCode(transDetail.getPurp().getCd());
					ntfctn.setPurpOther(transDetail.getPurp().getPrtry());
				}
			
				if(transDetail.getRmtInf() != null)
				{
					ntfctn.setRemitInfUstrd(transDetail.getRmtInf().getUstrd());
				}
				
				if(transDetail.getRltdDts().getIntrBkSttlmDt() != null)
					ntfctn.setRelatedDatesIntrSettlDate(transDetail.getRltdDts().getIntrBkSttlmDt().toGregorianCalendar().getTime());
				if(transDetail.getRltdDts().getTxDtTm() != null)
					ntfctn.setRelatedDatesTransTs(transDetail.getRltdDts().getTxDtTm().toGregorianCalendar().getTime());
				
				if(transDetail.getRtrInf() != null)
				{
					ntfctn.setReturnInfRsn(transDetail.getRtrInf().getRsn().getPrtry());
					StringBuffer loc_sb = new StringBuffer();
					for(int loc_i = 0; loc_i < transDetail.getRtrInf().getAddtlInf().size(); loc_i++)
					{
						loc_sb.append(loc_i == 0 ? transDetail.getRtrInf().getAddtlInf().get(loc_i) : "<BR>"+transDetail.getRtrInf().getAddtlInf().get(loc_i));
					}
					ntfctn.setReturnInfMsg(loc_sb.toString());
				}
				
				camt054MsgVo.getNotifList().add(ntfctn);
			}
		}
		return camt054MsgVo;
	}
}
