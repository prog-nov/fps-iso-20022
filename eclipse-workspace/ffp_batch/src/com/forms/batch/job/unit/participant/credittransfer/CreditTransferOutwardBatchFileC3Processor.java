package com.forms.batch.job.unit.participant.credittransfer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.batch.job.unit.participant.message.ffpcto01.FFPMsgCTO01_MutiPacs008;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class CreditTransferOutwardBatchFileC3Processor extends BatchBaseJob {
	private static String MESSAGE_MODE = FFPConstantsServiceCode.FFPAGENT_SERVICECODE_C3;
	private static String MESSAGE_RECORD_TYPE = "CTO_C3";

	private static int eachMessageMaxSize;
	private static int eachMessageItemMaxNum;
	private static String batchSubmissionDirectoryPath;

	private static ArrayList<String> messageFileList;
	public static final String DEFAULT_ENCODING = "UTF-8";
	private static Connection loc_conn = null;

	@Override
	public boolean execute() throws BatchJobException {
		try {
			if (loc_conn == null) {
				loc_conn = ConnectionManager.getInstance().getConnection();
				this.batchLogger.info("Database is connected");
			}

			String interval = this.actionElement.element("parameters").element("parameter")
					.elementText("interval-minute");
			if (getExcuteFlag(interval)) {

				eachMessageMaxSize = Integer.parseInt(
						this.actionElement.element("parameters").element("parameter").elementText("eachMessageMaxSize"))
						* 1024 * 1024;
				eachMessageItemMaxNum = Integer.parseInt(this.actionElement.element("parameters").element("parameter")
						.elementText("eachMessageItemMaxNum"));
				batchSubmissionDirectoryPath = this.batchData + this.actionElement.element("parameters")
						.element("parameter").elementText("batchSubmissionDirectoryPath");

				List<Map<String, Object>> queryResult = getQueryResultFromDB();

				if (queryResult == null || queryResult.size() == 0) {
					this.batchLogger.info("No C3 Data Found!");
				} else {
					List<FFPJbP100> C3List = getPacs008Dat(queryResult);
					if ((C3List != null) && (C3List.size() != 0)) {
						generateMulti008Message(C3List);
					}
				}
			}

		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In CreditTransferOutwardBatchFileC3Processor!");
		} finally {
			if (loc_conn != null) {
				try {
					loc_conn.close();
					this.batchLogger.info("Database Is Closed");
				} catch (SQLException e) {
					this.batchLogger.error(e.getMessage());
					throw new BatchJobException("Error Occurred In Closing The Database!");
				}
			}
		}
		return true;
	}

	public void init() throws BatchJobException {

	}

	private boolean getExcuteFlag(String interval) throws Exception {
		String sql = "SELECT * FROM tb_bh_generated_file WHERE TIMESTAMPDIFF(MINUTE,GENERATE_TS,NOW()) < ? AND FILE_TYPE = ?; ";
		ArrayList<String> list = new ArrayList<>();
		list.add(interval);
		list.add(MESSAGE_RECORD_TYPE);
		List<Map<String, Object>> listResult = EntityManager.queryMapList(loc_conn, sql, list.toArray());
		if (listResult != null && listResult.size() > 0) {
			return false;
		}
		return true;
	}

	private List<Map<String, Object>> getQueryResultFromDB() {
		List<Map<String, Object>> listResult = null;
		try {
			String sql = "SELECT A.TRANSACTION_ID,A.END_TO_END_ID,B.* "
					+ "FROM tb_tx_jnl A LEFT JOIN tb_tx_p100dat B ON A.JNL_NO = B.JNL_NO "
					+ "WHERE A.TX_STAT = ? AND A.TX_CODE = ? AND A.TX_SRC = ? AND A.TX_MODE = ? AND B.SRVC_MODE = ?";

			ArrayList<String> list = new ArrayList<>();
			list.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
			list.add(FFPConstantsTxJnl.TX_CODE.TX_CODE_P100.getCode());
			list.add(FFPConstants.TX_SOURCE_AGENT);
			list.add(FFPConstants.RUNNING_MODE_BATCH);
			list.add(MESSAGE_MODE);
			listResult = EntityManager.queryMapList(loc_conn, sql, list.toArray());
			if (listResult.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}

		return listResult;
	}

	private List<FFPJbP100> getPacs008Dat(List<Map<String, Object>> dataMapList) throws Exception {

		List<FFPJbP100> C3List = new ArrayList<FFPJbP100>();

		for (Map<String, Object> object : dataMapList) {
			FFPJbP100 fb = new FFPJbP100();
			FFPTxJnl txJnl = new FFPTxJnl();
			txJnl.setJnlNo((String) object.get("JNL_NO"));
			txJnl.setEndToEndId((String) object.get("END_TO_END_ID"));
			txJnl.setTransactionId((String) object.get("TRANSACTION_ID"));
			txJnl.setSrcRefNm((String) object.get("SRC_REF_NM"));
			fb.setTxJnl(txJnl);
			fb.setSrvcMode(FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC03);
			fb.setPymtCatPrps((String) object.get("CATEGORY_PURPOSE"));
			fb.setAccountVerification((String) object.get("ACCT_VERF"));
			fb.setSettlementDate((Date) object.get("SETTLEMENT_DATE"));
			fb.setSettlementCurrency((String) object.get("SETTLEMENT_CUR"));
			fb.setSettlementAmount(new BigDecimal((String) object.get("SETTLEMENT_AMT")));
			fb.setInstructedCurrency((String) object.get("INSTRUCTED_CUR"));
			fb.setInstructedAmount(new BigDecimal((String) object.get("INSTRUCTED_AMT")));
			fb.setChargersAgentId((String) object.get("CHG_AGT_ID"));
			fb.setChargersAgentBic((String) object.get("CHG_AGT_BIC"));
			fb.setChargersCurrency((String) object.get("CHG_CUR"));
			fb.setChargersAmount(new BigDecimal((String) object.get("CHG_AMT")));
			
			fb.setDebtorName((String) object.get("DEBTOR_NAME"));
			fb.setDebtorOrgIdAnyBIC((String) object.get("DEBTOR_ORGID_ANYBIC"));
			fb.setDebtorOrgIdOthrId((String) object.get("DEBTOR_ORGID_OTHRID"));
			fb.setDebtorOrgIdOthrIdSchmeNm((String) object.get("DEBTOR_ORGID_OTHRID_SCHMENM"));
			fb.setDebtorOrgIdOthrIssr((String) object.get("DEBTOR_ORGID_OTHR_ISSR"));
			fb.setDebtorPrvtIdOthrId((String) object.get("DEBTOR_PRVTID_OTHRID"));
			fb.setDebtorPrvtIdOthrIdSchmeNm((String) object.get("DEBTOR_PRVTID_OTHRID_SCHMENM"));
			fb.setDebtorPrvtIdOthrIssr((String) object.get("DEBTOR_PRVTID_OTHR_ISSR"));

			fb.setDebtorContPhone((String) object.get("DEBTOR_AGT_BIC"));
			fb.setDebtorContEmailAddr((String) object.get("DEBTOR_AGT_BIC"));
			fb.setDebtorAccountNumber((String) object.get("DEBTOR_ACCTNO"));
			fb.setDebtorAccountNumberType((String) object.get("DEBTOR_ACCTNO_TYPE"));
			fb.setDebtorAgentId((String) object.get("DEBTOR_AGT_ID"));
			fb.setDebtorAgentBic((String) object.get("DEBTOR_AGT_BIC"));

			fb.setCreditorName((String) object.get("CREDITOR_NAME"));
			fb.setCreditorOrgIdAnyBIC((String) object.get("CREDITOR_ORGID_ANYBIC"));
			fb.setCreditorOrgIdOthrId((String) object.get("CREDITOR_ORGID_OTHRID"));
			fb.setCreditorOrgIdOthrIdSchmeNm((String) object.get("CREDITOR_ORGID_OTHRID_SCHMENM"));
			fb.setCreditorOrgIdOthrIssr((String) object.get("CREDITOR_ORGID_OTHR_ISSR"));
			fb.setCreditorPrvtIdOthrId((String) object.get("CREDITOR_PRVTID_OTHRID"));
			fb.setCreditorPrvtIdOthrIdSchmeNm((String) object.get("CREDITOR_PRVTID_OTHRID_SCHMENM"));
			fb.setCreditorPrvtIdOthrIssr((String) object.get("CREDITOR_PRVTID_OTHR_ISSR"));

			fb.setCreditorAccountNumber((String) object.get("CREDITOR_ACCTNO"));
			fb.setCreditorAccountNumberType((String) object.get("CREDITOR_ACCTNO_TYPE"));
			fb.setCreditorAgentId((String) object.get("CREDITOR_AGT_ID"));
			fb.setCreditorAgentBic((String) object.get("CREDITOR_AGT_BIC"));
			fb.setCdtrContPhone((String) object.get("CREDITOR_CONT_PHONE"));
			fb.setCdtrContEmailAddr((String) object.get("CREDITOR_CONT_EMADDR"));

			fb.setPaymentPurposeType((String) object.get("PURPOSE_TYPE"));
			fb.setPaymentPurposeCd((String) object.get("PURPOSE_CODE"));
			fb.setPaymentPurposeProprietary((String) object.get("PURPOSE_OTHER"));
			fb.setRemittanceInformation((String) object.get("REMIT_INFO"));

			C3List.add(fb);

		}
		this.batchLogger.info(String.format("Finding %d C3 Data!", C3List.size()));
		return C3List;
	}

	private void generateMulti008Message(List<FFPJbP100> multi008List) throws Exception {
		Date generateStartTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format(
				"Start to Generate Messsage In CreditTransferOutwardBatchFileC3Processor At %s", generateStartTime));
		ArrayList<Integer> resList = null;
		try {
			resList = packToMulti008Message(multi008List);
		} catch (Exception e) {
			throw new Exception("Fail To Calculate The Multi008 Message Size!");
		}

		int start = 0;
		for (int i = 0; i < resList.size(); i++) {
			ArrayList<String> jnlNoList = new ArrayList<>();
			try {
				SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");
				String batchId = "FPSPCRO" + clearingCode + dateForm.format(new Date()) + getBatSubSeqNum();
				HashMap<String, String> btchMap = new HashMap<>();
				btchMap.put("BtchId", batchId);
				// btchMap.put("NbOfFls", resList.size() + "");
				btchMap.put("NbOfFls", "1");
				btchMap.put("NbOfMsgs", "1");
				btchMap.put("NbOfTxs", (resList.get(i) - start) + "");
				// btchMap.put("FlSeqNo", (i + 1) + "");
				btchMap.put("FlSeqNo", "1");

				FFPMsgCTO01_MutiPacs008 multi008 = new FFPMsgCTO01_MutiPacs008(
						multi008List.subList(start, resList.get(i)), btchMap);
				String mesText = multi008.parseHkiclMessage();

				String fileName = writeMulti008ToFile(mesText, batchId);

				for (int j = start; j < resList.get(i); j++) {
					jnlNoList.add(multi008List.get(j).getTxJnl().getJnlNo());
				}

				if (fileName != null) {
					updateJnlTable(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus(), fileName,
							jnlNoList.toArray(new String[jnlNoList.size()]));
					insertGeneratiedFileTable(fileName);
				} else {
					for (int j = start; j < resList.get(i); j++) {
						updateJnlTable(FFPConstantsTxJnl.TX_STATUS.TX_STAT_ERROR.getStatus(), fileName,
								jnlNoList.toArray(new String[jnlNoList.size()]));
					}
				}

				this.batchLogger.info(String.format("Generate A PACS008_PAYC03 Message, fileName=%s", fileName));

			} catch (Exception e) {
				updateJnlTable(FFPConstantsTxJnl.TX_STATUS.TX_STAT_ERROR.getStatus(), null,
						jnlNoList.toArray(new String[jnlNoList.size()]));
				this.batchLogger.error(e.getMessage());
				this.batchLogger.error("Some Error Occurred In Generating PACS008_PAYC03 Message!");
			}
			start = resList.get(i);
		}

		Date generateEndTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format(
				"Finish Generating Messsage In CreditTransferOutwardBatchFileC3Processor At %s", generateEndTime));
		this.batchLogger.info(String.format("Generating All pacs.008_C3 Messages Uses %.3f Seconds",
				(double) (generateEndTime.getTime() - generateStartTime.getTime()) / 1000));
	}

	/*
	 * Firstly, try to use eachMesItemMaxNum buzData to generate a message and
	 * calculate the size of it. If it is less than or equal to eachMesMaxSize,
	 * then take it and store the end position of the message lists. If it is
	 * more than eachMesMaxSize, then use formula
	 * (end-start)/mesTextSize*eachMesMaxSize to calculate how many buzData the
	 * message which size is eachMesMaxSize has. Use the result
	 * tempEachMesItemNum to try to generate the message and calculate the size
	 * again. Cycle the steps above until the size of new message is less than
	 * or equal to eachMesMaxSize.
	 */

	public ArrayList<Integer> packToMulti008Message(List<FFPJbP100> list) throws Exception {
		messageFileList = new ArrayList<>();
		ArrayList<Integer> resList = new ArrayList<>();
		HashMap<String, String> btchMap = new HashMap<>();
		btchMap.put("BtchId", "FPSPCRO000000000000000");
		btchMap.put("NbOfMsgs", "1");
		btchMap.put("FlSeqNo", "1");
		btchMap.put("NbOfFls", "1");
		btchMap.put("NbOfTxs", "1");
		if (list != null) {
			boolean isFin = false;
			int eachMesMaxSize = eachMessageMaxSize;
			int eachMesItemMaxNum = eachMessageItemMaxNum;
			int start = 0;
			int totalListSize = list.size();
			String mesText = "";
			int tempEachMesItemNum = eachMesItemMaxNum; // temporary item
														// number，item<=eachMesItemMaxNum
			int end = start + tempEachMesItemNum;
			this.batchLogger.info("Start To Calculate PACS008_PAYC03 Message!");
			while (!isFin) {
				if (totalListSize <= end) {
					// package to message，totalListSize
					end = totalListSize;
				}
				// serialize list.subList(start, end);
				FFPMsgCTO01_MutiPacs008 multi008_1 = new FFPMsgCTO01_MutiPacs008(list.subList(start, end), btchMap);
				int mesTextSize = 0;

				mesText = multi008_1.parseHkiclMessage();
				this.batchLogger.info("Start To Calculate PACS008_PAYC03 Message!");
				mesTextSize = mesText.getBytes(DEFAULT_ENCODING).length;
				while (mesTextSize > eachMesMaxSize) {
					tempEachMesItemNum = (int) Math
							.floor(((double) (end - start)) / ((double) mesTextSize) * eachMesMaxSize);
					end = start + tempEachMesItemNum;

					FFPMsgCTO01_MutiPacs008 multi008_2 = new FFPMsgCTO01_MutiPacs008(list.subList(start, end), btchMap);
					mesText = multi008_2.parseHkiclMessage();
					mesTextSize = mesText.getBytes(DEFAULT_ENCODING).length;
				}

				this.batchLogger.info(
						String.format("Calculate A PACS008_PAYC03 Message Size: messageItemNum=%d messageSize=%d ",
								(end - start), mesTextSize));
				resList.add(end);
				messageFileList.add(mesText);
				// next message
				start = end;
				end = start + eachMesItemMaxNum;

				if (totalListSize <= start) {

					isFin = true;
				}
			}
			this.batchLogger.info("Finish Calculating PACS008_PAYC03 Message");
		}

		return resList;
	}

	private String writeMulti008ToFile(String message, String batchId) throws Exception {
		String fileName = batchId + ".xml";
		String path = batchSubmissionDirectoryPath + fileName;

		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File(path)), DEFAULT_ENCODING));

		bw.write(message);
		bw.flush();
		this.batchLogger.info(String.format("Success To Write PACS008_PAYC03 File %s", path));

		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {
				this.batchLogger.error(e.getMessage());
			}

		}

		return fileName;
	}

	private void updateJnlTable(String state, String fileName, String[] jnlNoArr) {
		try {
			loc_conn.setAutoCommit(false);
			for (int i = 0; i < jnlNoArr.length; i++) {
				String sql = "UPDATE tb_tx_jnl SET TX_STAT = ?, TX_FILE_NAME = ?, LAST_UPDATE_TS = ? WHERE JNL_NO  = ? ";
				Timestamp lastUpdateTs = new Timestamp(new Date().getTime());
				ArrayList<Object> list = new ArrayList<>();
				list.add(state);
				list.add(fileName);
				list.add(lastUpdateTs);
				list.add(jnlNoArr[i]);

				EntityManager.update(loc_conn, sql, list.toArray());
			}
			loc_conn.commit();
			loc_conn.setAutoCommit(true);
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			try {
				loc_conn.rollback();
			} catch (SQLException e1) {
				this.batchLogger.error(e1.getMessage());
			}
		}
	}

	private void insertGeneratiedFileTable(String fileName) {
		try {
			String sql = "INSERT INTO tb_bh_generated_file(FILE_NAME, FILE_TYPE, GENERATE_TS) VALUES(?,?,?) ";
			ArrayList<Object> list = new ArrayList<>();
			list.add(fileName);
			list.add(MESSAGE_RECORD_TYPE);
			list.add(new Date());

			EntityManager.update(loc_conn, sql, list.toArray());
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}
	}

	public synchronized static String getBatSubSeqNum() throws Exception {
		String batSubSeqNum = "";
		String query_Sql = "SELECT ffp.get_fpspcro_trans_num() as SEQNUM ";
		Map<String, Object> queryMap = null;

		queryMap = EntityManager.queryMap(loc_conn, query_Sql);
		if (queryMap != null && !queryMap.isEmpty()) {
			batSubSeqNum = (String) queryMap.get("SEQNUM");
		}

		return batSubSeqNum;
	}

}