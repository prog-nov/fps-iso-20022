package com.forms.batch.job.unit.participant.returnorrefund;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.batch.job.unit.participant.message.ffpcto01.FFPMsgCTO01_Pacs004;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class ReturnOrRefundOutwardBatchFileProcessor extends BatchBaseJob {
	private static String MESSAGE_RECORD_TYPE = "RTN";
	private static String batSubSeqNum = "";// Message Sequence Number nnnnnnnn

	private static int eachMessageMaxSize;
	private static int eachMessageItemMaxNum;
	private static String batchSubmissionDirectoryPath;

	private static ArrayList<String> messageFileList;
	public static final String OUTWARD_BATCH_FILE_NAME = "outwardBatch";
	public static final String DEFAULT_ENCODING = "UTF-8";
	private static Connection loc_conn = null;

	@Override
	public void init() throws BatchJobException {
		try {
			eachMessageMaxSize = Integer.parseInt(
					this.actionElement.element("parameters").element("parameter").elementText("eachMessageMaxSize"))
					* 1024 * 1024;
			eachMessageItemMaxNum = Integer.parseInt(
					this.actionElement.element("parameters").element("parameter").elementText("eachMessageItemMaxNum"));
			batchSubmissionDirectoryPath = this.batchData + this.actionElement.element("parameters")
					.element("parameter").elementText("batchSubmissionDirectoryPath");
			this.batchLogger.info(String.format(
					"Read Parameters From file, eachMessageMaxSize=%s, eachMessageItemMaxNum=%s, batchSubmissionDirectoryPath=%s, clearingCode=%s",
					eachMessageMaxSize, eachMessageItemMaxNum, batchSubmissionDirectoryPath,
					this.clearingCode));
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}
	}

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
				save300Dat();
				getPacs004Dat();
			}
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		} finally{
			if(loc_conn != null){
				try {
					loc_conn.close();
					this.batchLogger.info("Database Is Closed");
				} catch (SQLException e) {
					this.batchLogger.error(e.getMessage());
				}
			}
		}
		return true;
	}
	
	private boolean getExcuteFlag(String interval) throws Exception {
		String sql = "SELECT * FROM tb_bh_generated_file WHERE DATE_SUB(NOW(),INTERVAL ? MINUTE) < GENERATE_TS AND FILE_TYPE = ? ;";
		ArrayList<String> list = new ArrayList<>();
		list.add(interval);
		list.add(MESSAGE_RECORD_TYPE);
		List<Map<String, Object>> listResult = EntityManager.queryMapList(loc_conn, sql, list.toArray());
		if (listResult != null && listResult.size() > 0) {
			return false;
		}
		return true;
	}

	private List<Map<String, Object>> getQueryResult() {
		List<Map<String, Object>> listResult = null;
		try {

			String sql = "SELECT A.TRANSACTION_ID,A.END_TO_END_ID,B.* "
					+ "FROM tb_tx_jnl A LEFT JOIN tb_tx_p300dat B ON A.JNL_NO = B.JNL_NO " + "WHERE "
					+ "A.TX_STAT = ? AND " + "A.TX_CODE = ? AND " + "A.TX_SRC = ? AND " + "A.TX_MODE = ?";

			ArrayList<String> list = new ArrayList<>();
			list.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
			list.add(FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode());
			list.add(FFPConstants.TX_SOURCE_FFP);
			list.add(FFPConstants.RUNNING_MODE_BATCH);
			listResult = EntityManager.queryMapList(loc_conn, sql, list.toArray());

			if(listResult.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}

		return listResult;
	}

	
	
	private List<Map<String, Object>> getDatFromP110() {
		List<Map<String, Object>> listResult = null;
		try{
			String query_110dat_sql = "SELECT A.JNL_NO, B.SETTLEMENT_CUR, B.SETTLEMENT_AMT, B.SETTLEMENT_DATE, B.INSTRUCTED_CUR, B.INSTRUCTED_AMT,"
					+ "B.CHG_AGT_BIC, B.CHG_AGT_ID, B.CHG_CUR, B.CHG_AMT, A.TX_REJ_CODE, A.TX_REJ_REASON, B.CATEGORY_PURPOSE,"
					+ "B.DEBTOR_NAME, B.DEBTOR_ACCTNO, B.DEBTOR_ACCTNO_TYPE, B.DEBTOR_AGT_ID, B.DEBTOR_AGT_BIC,"
					+ "B.CREDITOR_NAME, B.CREDITOR_ACCTNO, B.CREDITOR_ACCTNO_TYPE, B.CREDITOR_AGT_ID, B.CREDITOR_AGT_BIC "
					+ "FROM TB_TX_JNL A LEFT JOIN tb_tx_p110dat B ON A.JNL_NO = B.JNL_NO WHERE A.TX_STAT = ? AND A.TX_CODE = ? AND A.TX_SRC = ? AND A.TX_MODE = ? AND A.TX_REJ_CODE = ? ";
			
			ArrayList<String> list = new ArrayList<>();
			list.add("REJCT");
			list.add(FFPConstantsTxJnl.TX_CODE.TX_CODE_P110.getCode());
			list.add(FFPConstants.TX_SOURCE_AGENT);
			list.add(FFPConstants.RUNNING_MODE_BATCH);
			list.add("P110024"); ///return rejcode
			
			listResult = EntityManager.queryMapList(loc_conn, query_110dat_sql, list.toArray());
			
			if(listResult.size() == 0) {
				return null;
			}
		}	catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}

		return listResult;
		
	}
	
	private void save300Dat() throws BatchJobException {
		this.batchLogger.info("save p300dat starting");
		List<Map<String, Object>> queryResult = getDatFromP110();
		if (queryResult == null) {
			throw new BatchJobException("No Data Is Found");
		}
		insertAndUpdateTable(queryResult);
	}
	
	/*
	 * insert jnl,jnlaction,p300dat table
	 * update origin jnl
	 */
	private void insertAndUpdateTable(List<Map<String, Object>> list) {
		String jnlSql = "";
		//String actionSql = "";
		String datSql = "";	
		String jnlUpSql = "";
		for(Map<String, Object> datMap : list){
			try {
				loc_conn.setAutoCommit(false);
				ArrayList<Object> jnlList = new ArrayList<>();
				//ArrayList<Object> actionList = new ArrayList<>();
				ArrayList<Object> datList = new ArrayList<>();
				ArrayList<Object> upList = new ArrayList<>();
				
				String jnlNo = FFPIDUtils.getJnlNo();
				String transactionId = FFPIDUtils.getTransactionId();
				String end2endId = FFPIDUtils.getEndToEndId();
				Timestamp createTs = FFPDateUtils.getTimestamp(new Date());
				
				jnlSql = "INSERT INTO TB_TX_JNL(JNL_NO, SRC_REF_NM, TX_STAT, TX_CODE, TX_SRC, TX_MODE, TRANSACTION_ID, END_TO_END_ID, JNL_REF, CREATE_TS, LAST_UPDATE_TS) VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
				//actionSql = "INSERT INTO TB_TX_JNLACTION(JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_CREAT_TS, "	+ 
			    //        "MSG_PROCE_TS, MSG_COMPL_TS) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				datSql = "INSERT INTO TB_TX_P300DAT(JNL_NO,RETURN_ID,RETURN_SETTLEMENT_CUR,RETURN_SETTLEMENT_AMT,"
						+ "RETURN_SETTLEMENT_DATE, RETURN_INSTRUCTED_CUR, RETURN_INSTRUCTED_AMT,"
						+ "RETURN_CHG_AGT_ID,RETURN_CHG_AGT_BIC,RETURN_CHG_CUR,RETURN_CHG_AMT,RETURN_CODE,"
						+ "RETURN_REASON,BIZ_SVC_TYPE,ORIG_SETTLEMENT_AMT,ORIG_SETTLEMENT_CUR,ORIG_SETTLEMENT_DATE,"
						+ "ORIG_CATEGORY_PURPOSE,ORIG_MANDATE_INFO,ORIG_REM_INFO,ORIG_DEBTOR_NAME,ORIG_DEBTOR_ACCTNO,"
						+ "ORIG_DEBTOR_ACCTNO_TYPE,ORIG_DEBTOR_AGT_ID,ORIG_DEBTOR_AGT_BIC,ORIG_CREDITOR_NAME,ORIG_CREDITOR_ACCTNO,"
						+ "ORIG_CREDITOR_ACCTNO_TYPE,ORIG_CREDITOR_AGT_ID,ORIG_CREDITOR_AGT_BIC) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				jnlUpSql = "UPDATE TB_TX_JNL SET TX_STAT = ? WHERE JNL_NO = ?";
				
				////////////////////////////jnl param///////////////////////
				jnlList.add(jnlNo);
				jnlList.add("");
				jnlList.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
				jnlList.add(FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode());
				jnlList.add(FFPConstants.TX_SOURCE_FFP);
				jnlList.add(FFPConstants.RUNNING_MODE_BATCH);
				jnlList.add(transactionId);
				jnlList.add(end2endId);
				jnlList.add(datMap.get("JNL_NO"));
				jnlList.add(createTs);
				jnlList.add(createTs);
				
				////////////////////////////p300dat param//////////////////////
				datList.add(jnlNo);
				datList.add(transactionId);
				datList.add(datMap.get("SETTLEMENT_CUR"));
				datList.add(datMap.get("SETTLEMENT_AMT"));
				datList.add(datMap.get("SETTLEMENT_DATE"));
				datList.add(datMap.get("INSTRUCTED_CUR"));
				datList.add(datMap.get("INSTRUCTED_AMT"));
				datList.add(datMap.get("CHG_AGT_BIC"));
				datList.add(datMap.get("CHG_AGT_ID"));
				datList.add(datMap.get("CHG_CUR"));
				datList.add(datMap.get("CHG_AMT"));
				datList.add(datMap.get("TX_REJ_CODE"));
				datList.add(datMap.get("TX_REJ_REASON"));
				datList.add(FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYR01);
				
				datList.add(datMap.get("SETTLEMENT_AMT"));
				datList.add(datMap.get("SETTLEMENT_CUR"));
				datList.add(datMap.get("SETTLEMENT_DATE"));
				datList.add(datMap.get("CATEGORY_PURPOSE"));
				datList.add(""); //ORIG_MANDATE_INFO
				datList.add("Credit Transfer"); //ORIG_REM_INFO
				datList.add(datMap.get("DEBTOR_NAME"));
				datList.add(datMap.get("DEBTOR_ACCTNO"));
				datList.add(datMap.get("DEBTOR_ACCTNO_TYPE"));
				datList.add(datMap.get("DEBTOR_AGT_ID"));
				datList.add(datMap.get("DEBTOR_AGT_BIC"));
				
				datList.add(datMap.get("CREDITOR_NAME"));
				datList.add(datMap.get("CREDITOR_ACCTNO"));
				datList.add(datMap.get("CREDITOR_ACCTNO_TYPE"));
				datList.add(datMap.get("CREDITOR_AGT_ID"));
				datList.add(datMap.get("CREDITOR_AGT_BIC"));
				
				//////////////////////////upJnl param//////////////////////////////
				upList.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_RETURN.getStatus());
				upList.add(datMap.get("JNL_NO"));
				
				//////////////////////////exc sql//////////////////////////////////
				EntityManager.update(loc_conn, jnlSql, jnlList.toArray());
				//EntityManager.update(loc_conn, actionSql, actionList.toArray());
				EntityManager.update(loc_conn, datSql, datList.toArray());
				EntityManager.update(loc_conn, jnlUpSql, upList.toArray());
				
				loc_conn.commit();
				loc_conn.setAutoCommit(true);
				this.batchLogger.info("insertAndUpdateTable end");
			} catch (Exception e) {
				this.batchLogger.error(e.getMessage());
			}
		}
		
	}
	
	private void getPacs004Dat() throws BatchJobException {
		List<Map<String, Object>> queryResult = getQueryResult();
		if (queryResult == null) {
			throw new BatchJobException("No Data Is Found");
		}
		List<FFPJbP300> r3List = new ArrayList<FFPJbP300>();
		try {
			for (Map<String, Object> object : queryResult) {
				FFPJbP300 fb = new FFPJbP300();
				FFPTxJnl txJnl = new FFPTxJnl();
				txJnl.setJnlNo((String) object.get("JNL_NO"));
				txJnl.setEndToEndId((String) object.get("END_TO_END_ID"));
				txJnl.setTransactionId((String) object.get("TRANSACTION_ID"));
				fb.setTxJnl(txJnl);
				fb.setJnlRef((String) object.get("JNL_REF"));
				fb.setReturnId((String) object.get("RETURN_ID"));
				fb.setRetIntSetCur((String) object.get("RETURN_SETTLEMENT_CUR"));
				fb.setRetIntSetAmt(new BigDecimal((String) object.get("RETURN_SETTLEMENT_AMT")));
				fb.setSettlementDate((Date) object.get("RETURN_SETTLEMENT_DATE"));
				fb.setRetInsCur((String) object.get("RETURN_INSTRUCTED_CUR"));
				fb.setRetInsAmt(new BigDecimal((String) object.get("RETURN_INSTRUCTED_AMT")));
				fb.setChgAgtID((String) object.get("RETURN_CHG_AGT_ID"));
				fb.setChgAgtBIC((String) object.get("RETURN_CHG_AGT_BIC"));
				fb.setChargersCurrency((String) object.get("RETURN_CHG_CUR"));
				fb.setChargersAmount(new BigDecimal((String) object.get("RETURN_CHG_AMT")));
				fb.setReasonCode((String) object.get("RETURN_REASON"));
				fb.setSrvcMode((String) object.get("BIZ_SVC_TYPE"));
				fb.setOrgnlInterbankSettAmt(new BigDecimal((String) object.get("ORIG_SETTLEMENT_AMT")));
				fb.setOrgnlInterbankSettCcy((String) object.get("ORIG_SETTLEMENT_CUR"));
				fb.setOrgnlInterbankSettDate((Date) object.get("ORIG_SETTLEMENT_DATE"));
				fb.setOrgnlCatgyPurp((String) object.get("ORIG_CATEGORY_PURPOSE"));
				fb.setOrgnlMandateInfo((String) object.get("ORIG_MANDATE_INFO"));
				fb.setOrgnlRemtInfo((String) object.get("ORIG_REM_INFO"));
				fb.setOrgnlDbtrNm((String) object.get("ORIG_DEBTOR_NAME"));
				fb.setOrgnlDbtrAcctNo((String) object.get("ORIG_DEBTOR_ACCTNO"));
				fb.setOrgnlDbtrAcctNoTp((String) object.get("ORIG_DEBTOR_ACCTNO_TYPE"));
				fb.setOrgnlDbtrAgtId((String) object.get("ORIG_DEBTOR_AGT_ID"));
				fb.setOrgnlDbtrAgtBIC((String) object.get("ORIG_DEBTOR_AGT_BIC"));
				fb.setOrgnlDbtrPhNo("");
				fb.setOrgnlDbtrEmAddr("");
				
				fb.setOrgnlCdtrNm((String) object.get("ORIG_CREDITOR_NAME"));
				fb.setOrgnlCdtrAcctNo((String) object.get("ORIG_CREDITOR_ACCTNO"));
				fb.setOrgnlCdtrAcctNoTp((String) object.get("ORIG_CREDITOR_ACCTNO_TYPE"));
				fb.setOrgnlCdtrAgtId((String) object.get("ORIG_CREDITOR_AGT_ID"));
				fb.setOrgnlCdtrAgtBIC((String) object.get("ORIG_CREDITOR_AGT_BIC"));
				fb.setOrgnlCdtrPhNo("");
				fb.setOrgnlCdtrEmAddr("");
				r3List.add(fb);
			}
			if ((r3List != null) && (r3List.size() != 0)) {
				generateMulti004Message(r3List);

			}
			
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}
		
	}

	private void generateMulti004Message(List<FFPJbP300> multi004List) {
		ArrayList<Integer> resList = packToMulti004Message(multi004List);

		int start = 0;
		for (int i = 0; i < resList.size(); i++) {
			try {
				SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");
				String batchId = "FPSPCRO" + clearingCode + dateForm.format(new Date()) + getBatSubSeqNum();
				HashMap<String, String> btchMap = new HashMap<>();
				btchMap.put("BtchId", batchId);
				btchMap.put("NbOfFls", "1");
				btchMap.put("NbOfMsgs", "1");
				btchMap.put("NbOfTxs", (resList.get(i) - start) + "");
				btchMap.put("FlSeqNo", "1");

				FFPMsgCTO01_Pacs004 multi004 = new FFPMsgCTO01_Pacs004(multi004List.subList(start, resList.get(i)),
						btchMap);
				String mesText = multi004.parseHkiclMessage();
				
				this.batchLogger.info("Message : " + mesText);
				String fileName = writeMulti004ToFile(mesText, batchId);
				if (fileName != null) {
					for (int j = start; j < resList.get(i); j++) {
						updateJnlTable(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus(), fileName,
								multi004List.get(j).getTxJnl().getJnlNo());
					}
					insertGeneratiedFileTable(fileName);
				} else {
					for (int j = start; j < resList.get(i); j++) {
						updateJnlTable(FFPConstantsTxJnl.TX_STATUS.TX_STAT_ERROR.getStatus(), fileName,
								multi004List.get(j).getTxJnl().getJnlNo());
					}
				}

			} catch (Exception e) {
				for (int j = start; j < resList.get(i); j++) {
					updateJnlTable(FFPConstantsTxJnl.TX_STATUS.TX_STAT_ERROR.getStatus(), null,
							multi004List.get(j).getTxJnl().getJnlNo());
				}
				this.batchLogger.error("Fail To Generate PACS004_PAYR01 Message" + e.getMessage());
			}
			start = resList.get(i);
		}
	}

	public ArrayList<Integer> packToMulti004Message(List<FFPJbP300> list) {
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
			this.batchLogger.info("Start To Calculate PACS004_PAYR01 Message");
			while (!isFin) {
				if (totalListSize <= end) {
					// package to message，totalListSize
					end = totalListSize;
				}
				// serialize list.subList(start, end);
				FFPMsgCTO01_Pacs004 multi004_1 = new FFPMsgCTO01_Pacs004(list.subList(start, end), btchMap);
				int mesTextSize = 0;
				try {
					mesText = multi004_1.parseHkiclMessage();
					mesTextSize = mesText.getBytes(DEFAULT_ENCODING).length;
					while (mesTextSize > eachMesMaxSize) {
						tempEachMesItemNum = (int) Math
								.floor(((double) (end - start)) / ((double) mesTextSize) * eachMesMaxSize);
						end = start + tempEachMesItemNum;

						FFPMsgCTO01_Pacs004 multi004_2 = new FFPMsgCTO01_Pacs004(list.subList(start, end), btchMap);
						mesText = multi004_2.parseHkiclMessage();
						mesTextSize = mesText.getBytes(DEFAULT_ENCODING).length;
					}
				} catch (Exception e) {
					this.batchLogger.error(e.getMessage());
				}

				this.batchLogger.info(
						String.format("Calculate A PACS004_PAYR01 Message Size: messageItemNum=%d messageSize=%d ",
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
			this.batchLogger.info("Finish Calculate PACS004_PAYR01 Message");
		}

		return resList;
	}

	private String writeMulti004ToFile(String message, String batchId) {
		BufferedWriter bw = null;
		String fileName = batchId + ".xml";
		String path = batchSubmissionDirectoryPath + fileName;

		this.batchLogger.info(String.format("Start To Write PACS004_PAYR01 File %s", path));

		try {

			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), DEFAULT_ENCODING));
			bw.write(message);
			bw.flush();
			this.batchLogger.info(String.format("Finish Writing PACS004_PAYR01 File %s", path));
			return fileName;
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			return null;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					this.batchLogger.error(e.getMessage());
				}
			}
		}
	}

	private void updateJnlTable(String state, String fileName, String jnlNo) {
		try {
			String sql = "UPDATE tb_tx_jnl SET TX_STAT = ?, TX_FILE_NAME = ?, LAST_UPDATE_TS = ? WHERE JNL_NO  = ? ";
			Timestamp lastUpdateTs = new Timestamp(new Date().getTime());
			ArrayList<Object> list = new ArrayList<>();
			list.add(state);
			list.add(fileName);
			list.add(lastUpdateTs);
			list.add(jnlNo);

			EntityManager.update(loc_conn, sql, list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			this.batchLogger.error(e.getMessage());
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

	public synchronized static String getBatSubSeqNum() throws Exception{
		String query_Sql = "SELECT ffp.get_fpspcro_trans_num() as SEQNUM ";
		Map<String, Object> queryMap = null;
			queryMap = EntityManager.queryMap(loc_conn, query_Sql);
			if (queryMap != null && !queryMap.isEmpty()) {
				batSubSeqNum = (String) queryMap.get("SEQNUM");
			}
		return batSubSeqNum;
	}

}
