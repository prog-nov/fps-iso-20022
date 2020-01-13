 package com.forms.batch.job.unit.iclfps.payment.credittransfer;
 
 import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forms.batch.job.unit.iclfps.payment.message.FFPBatchMsg_CTI01;
import com.forms.batch.util.CuttoffUtils;
import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPStatus;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.FFPBaseResp;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;
 
 public class PaymentConfirmationInwardProcessor extends BatchBaseJob
 {
   private static final String RESPONSE_HEAD_STATUS_N = "N";
   private static final String RESPONSE_HEAD_STATUS_E = "E";
   private static final String MESSAGE_DIRECTION_INWARD = "I";
   private static final String MESSAGE_DIRECTION_OUTWARD = "O";
 
   public void init()
   {
   }
 
   public boolean execute()
     throws BatchJobException
   {
     try
     {
    	 processor();
    	 return true;
     }
     catch (Exception ip_e)
     {
    	 throw new BatchJobException(ip_e);
     }
   }
 
   private void processor() throws Exception
   {
	   this.batchLogger.info("Started processor inward batch file data");
 
	   FFPAdaptorMgr ffpMgr = FFPAdaptorMgr.getInstance();
	   Connection con = null;
	   
	   try
	   {
		   con = ConnectionManager.getInstance().getConnection();
 
		   boolean agentCutOff = CuttoffUtils.isCutoff("FFP");
		   if (agentCutOff)
		   {
			   this.batchLogger.info("FFP Agent CUT OFF right now!");
			   return;
		   }
		   
		   List<FFPJbP110> FFPJbP110List = getTempData(con);
		   
		   processTxData(con, FFPJbP110List, ffpMgr);
	   }
	   catch (Exception ex)
	   {
		   throw new BatchJobException(ex);
	   }
	   finally
	   {
		   if (con != null)
			   con.close();
	   }
	   if (con != null)
		   con.close();
   	}
 
   private void processTxData(Connection conn, List<FFPJbP110> FFPJbP110List, FFPAdaptorMgr ffpMgr)
   {
	   this.batchLogger.info("Start to process inward credit transfer transaction data.");
	   for (FFPJbP110 singleP110Info : FFPJbP110List)
	   {
		   try
		   {
			   String ffpJnlNo = FFPIDUtils.getJnlNo();
 
			   savePacs008Data(conn, singleP110Info, ffpJnlNo);
 
			   String reqRefNo = FFPIDUtils.getRefno();
			   FFPBatchMsg_CTI01 cti01 = new FFPBatchMsg_CTI01(singleP110Info);
			   cti01.setReqRefNo(reqRefNo);
			   boolean agentCutOff = CuttoffUtils.isCutoff("FFP");
			   if (agentCutOff)
			   {
				   this.batchLogger.info(String.format("FFP Agent CUT OFF right now in processing transaction data[TX_ID:%s]!", new Object[] { singleP110Info.getTxJnl().getTransactionId() }));
				   continue;
			   }
 
			   insertAcitonFlow(conn, ffpJnlNo, reqRefNo, MESSAGE_DIRECTION_OUTWARD, FFPConstants.MSG_CODE_AGENT, FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01, 
					   FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), null, null, null, null, "N");
 
			   FFPBaseResp result = ffpMgr.execute(cti01);
 
			   if (result != null)
			   {
				   FFPSendTcpMessageResp cti01_reply = (FFPSendTcpMessageResp)result;
				   if (cti01_reply.isTimeOut())
				   {
					   this.batchLogger.info(String.format("Update FFP Transaction status[%s] and Action request status[%s] with FFP_JNL_NM[%s] and Source Reference number[%s] and Request_MSG_ID[%s]", new Object[] { 
							   FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus(), ffpJnlNo, singleP110Info.getSrcRefNm(), reqRefNo }));
					   updateResultSts(conn, FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus(), null, null, FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus(), 
							   null, null, null, ffpJnlNo, reqRefNo, singleP110Info.getSrcRefNm());
				   }
				   else
				   {
					   cti01.unmarshalResponseMsg(cti01_reply.getRespMessage());
					   String resultSts;
					   String res_action_sts;
					   if (RESPONSE_HEAD_STATUS_E.compareTo(cti01.getResponseSts()) == 0)
					   {
						   resultSts = FFPConstantsTxJnl.TX_STATUS.TX_STAT_REJCT.getStatus();
						   res_action_sts = FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus();
					   }
					   else if (RESPONSE_HEAD_STATUS_N.compareTo(cti01.getResponseSts()) == 0)
					   {
						   resultSts = ("S".equals(cti01.getRsltCd())) ? FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus() : FFPConstantsTxJnl.TX_STATUS.TX_STAT_REJCT.getStatus();
						   res_action_sts = ("S".equals(cti01.getRsltCd())) ? FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus() : FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus();
					   }
					   else
					   {
						   this.batchLogger.warn(String.format("Invalid response head status[%s] with resultCode[%s] from FFP Agent.", new Object[] { cti01.getResponseSts(), cti01.getRsltCd() }));
						   throw new BatchJobException(String.format("Invalid response head status[%s] with resultCode[%s] from FFP Agent.", new Object[] { cti01.getResponseSts(), cti01.getRsltCd() }));
					   }
 
					   this.batchLogger.info(String.format("Update FFP Transaction Final status[%s] and Action status[%s] with Credit Transfer Status[%s] and Source Reference number[%s]", new Object[] { 
							   resultSts, FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), cti01.getRsltCd(), singleP110Info.getSrcRefNm() }));
					   updateResultSts(conn, resultSts, cti01.getRejCd(), cti01.getRejMsg(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), null, null, new Date(), 
							   ffpJnlNo, reqRefNo, singleP110Info.getSrcRefNm());
 
					   insertAcitonFlow(conn, ffpJnlNo, cti01.getResRefNo(), MESSAGE_DIRECTION_INWARD, FFPConstants.MSG_CODE_AGENT, FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01, 
							   res_action_sts, cti01.getResponseMsgCode(), cti01.getResponseMsg(), new Date(), reqRefNo, "N");
				   }
			   }
		   }
		   catch (Exception ex)
		   {
			   ex.printStackTrace();
			   this.batchLogger.error(String.format("Error on processing transaction data[TX_ID:%s]", new Object[] { singleP110Info.getTxJnl().getTransactionId() }), ex);
		   }
	   }
	   this.batchLogger.info("End to process inward credit transfer transaction data.");
   }
 
   private void insertAcitonFlow(Connection conn, String jnlNo, String msgId, String msgDirection, String msgSystemId, String msgType, String msgStatus, String msgCode, String msgResult, Date comDate, String refMsgId, String is_check)
     throws BatchJobException
   {
	   String sql = "INSERT INTO TB_TX_JNLACTION(JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_CREAT_TS, MSG_PROCE_TS, MSG_COMPL_TS, REF_MSG_ID, IS_AUTOCHECK) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	   try
	   {
		   List<Object> list = new ArrayList<Object>();
		   list.add(jnlNo);
		   list.add(msgId);
		   list.add(msgDirection);
		   list.add(msgSystemId);
		   list.add(msgType);
		   list.add(msgStatus);
		   list.add(msgCode);
		   list.add(msgResult);
		   list.add(new Timestamp(new Date().getTime()));
		   list.add(new Timestamp(new Date().getTime()));
		   list.add(comDate);
		   list.add(refMsgId);
		   list.add(is_check);
 
		   EntityManager.update(conn, sql, list.toArray());
	   }
	   catch (Exception ex)
	   {
		   ex.printStackTrace();
		   this.batchLogger.error("Error on adding action flow", ex);
		   throw new BatchJobException(ex);
	   }
   }
 
   private void updateResultSts(Connection conn, String tx_status, String tx_rejCd, String tx_rejRsn, String action_msgStatus, String action_msgCode, String action_msgResult, Date action_msgCompleDate, String jnlNo, String action_msgId, String srcRefNm)
     throws Exception
   {
	   String jnl_sql = "UPDATE TB_TX_JNL SET TX_STAT = ?, TX_REJ_CODE = ?, TX_REJ_REASON = ?, LAST_UPDATE_TS = ? WHERE JNL_NO = ? AND SRC_REF_NM = ?";
	   String action_sql = "UPDATE TB_TX_JNLACTION SET MSG_STATUS = ?, MSG_CODE = ?, MSG_RESULT = ?, MSG_COMPL_TS = ? WHERE JNL_NO = ? AND MSG_ID = ?";
    	try
    	{
    		conn.setAutoCommit(false);
 
    		List<Object> jnl_params = new ArrayList<Object>();
    		List<Object> action_params = new ArrayList<Object>();
    		jnl_params.add(tx_status);
    		jnl_params.add(tx_rejCd);
    		jnl_params.add(tx_rejRsn);
    		jnl_params.add(new Timestamp(new Date().getTime()));
    		jnl_params.add(jnlNo);
    		jnl_params.add(srcRefNm);
 
    		action_params.add(action_msgStatus);
    		action_params.add(action_msgCode);
    		action_params.add(action_msgResult);
    		action_params.add(action_msgCompleDate);
    		action_params.add(jnlNo);
    		action_params.add(action_msgId);
    		EntityManager.update(conn, jnl_sql, jnl_params.toArray());
    		EntityManager.update(conn, action_sql, action_params.toArray());
 
    		conn.commit();
    		conn.setAutoCommit(true);
    	}
    	catch (Exception ex)
    	{
    		this.batchLogger.error(String.format("Update TX_STATUS[%s] and MSG_STATUS[%s] with JNL_NO[%s] and MSG_ID failed", new Object[] { tx_status, action_msgStatus, jnlNo, action_msgId }), ex);
    		ex.printStackTrace();
    		if (conn != null)
    		{
    			conn.rollback();
    		}
    		throw new BatchJobException(ex);
    	}
   	}
 
   private List<FFPJbP110> getTempData(Connection con) throws Exception
   {
	   String sql = "SELECT * FROM TB_BH_INWARD_FPSPYCI WHERE STATUS = ?";
	   PreparedStatement pstm = null;
	   ResultSet rs = null;
	   List<FFPJbP110> list = new ArrayList<FFPJbP110>();
	   try
	   {
		   pstm = con.prepareStatement(sql);
 
		   pstm.setString(1, FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode());
 
		   rs = pstm.executeQuery();
		   while (rs.next())
		   {
			   FFPJbP110 ctInfo = new FFPJbP110();
			   FFPTxJnl txJnl = new FFPTxJnl();
			   txJnl.setTransactionId(rs.getString("TRANSACTION_ID"));
		       txJnl.setEndToEndId(rs.getString("END_TO_END_ID"));
		       txJnl.setFpsRefNm(rs.getString("FPS_REF"));
 
		       ctInfo.setTxJnl(txJnl);
		       ctInfo.setSrcRefNm(rs.getString("ID"));
		       ctInfo.setPymtCatPrps(rs.getString("CATEGORY_PURPOSE"));
		       ctInfo.setAccountVerification(rs.getString("ACCT_VERF"));
		       ctInfo.setSettlementAmount(new BigDecimal(rs.getString("SETTLEMENT_AMT")));
		       ctInfo.setSettlementCurrency(rs.getString("SETTLEMENT_CUR"));
		       ctInfo.setSettlementDate(rs.getDate("SETTLEMENT_DATE"));
		       ctInfo.setInstructedCurrency(rs.getString("INSTRUCTED_CUR"));
		       ctInfo.setInstructedAmount((rs.getString("INSTRUCTED_AMT") != null) ? new BigDecimal(rs.getString("INSTRUCTED_AMT")) : null);
		       ctInfo.setChargersAgentId(rs.getString("CHG_AGT_ID"));
		       ctInfo.setChargersAgentBic(rs.getString("CHG_AGT_BIC"));
		       ctInfo.setChargersCurrency(rs.getString("CHG_CUR"));
		       ctInfo.setChargersAmount((rs.getString("CHG_AMT") != null) ? new BigDecimal(rs.getString("CHG_AMT")) : null);
		       ctInfo.setDebtorName(rs.getString("DEBTOR_NAME"));
		       ctInfo.setDbtrContPhone(rs.getString("DEBTOR_CONT_PHONE"));		//fix prd log 20190820
		       ctInfo.setDbtrContEmailAddr(rs.getString("DEBTOR_CONT_EMADDR"));	//fix prd log 20190820
		       ctInfo.setDebtorAccountNumber(rs.getString("DEBTOR_ACCTNO"));
		       ctInfo.setDebtorAccountNumberType(rs.getString("DEBTOR_ACCTNO_TYPE"));
		       ctInfo.setDebtorAgentId(rs.getString("DEBTOR_AGT_ID"));
		       ctInfo.setDebtorAgentBic(rs.getString("DEBTOR_AGT_BIC"));
		       ctInfo.setCreditorName(rs.getString("CREDITOR_NAME"));
		       ctInfo.setCreditorAccountNumber(rs.getString("CREDITOR_ACCTNO"));
		       ctInfo.setCreditorAccountNumberType(rs.getString("CREDITOR_ACCTNO_TYPE"));
		       ctInfo.setCreditorAgentId(rs.getString("CREDITOR_AGT_ID"));
		       ctInfo.setCreditorAgentBic(rs.getString("CREDITOR_AGT_BIC"));
		       ctInfo.setPaymentPurposeType(rs.getString("PURPOSE_TYPE"));
		       ctInfo.setPaymentPurposeCd(rs.getString("PURPOSE_CODE"));
		       ctInfo.setRemittanceInformation(rs.getString("REMIT_INFO"));
		       ctInfo.setSrvcMode(rs.getString("BIZ_SVC_TYPE"));
 
		       list.add(ctInfo);
		   }
	   }
	   catch (Exception ex)
	   {
		   ex.printStackTrace();
		   this.batchLogger.error(String.format("Failed to get file data from temporary table with batchDate[%s]", new Object[] { this.batchAcDate }), ex);
 
		   throw new BatchJobException(ex);
	   }
	   finally
	   {
		   if (rs != null) rs.close();
		   if (pstm != null) pstm.close();
	   }
	   return list;
   }
 
   private void savePacs008Data(Connection conn, FFPJbP110 txInf, String ffp_jnl_no) throws Exception
   {
	   PreparedStatement pstm = null;
	   PreparedStatement pstm2 = null;
	   PreparedStatement pstm3 = null;
	   String jnl_sql = "INSERT INTO TB_TX_JNL(JNL_NO, SRC_REF_NM, TX_STAT, TX_CODE, TX_SRC, TX_MODE, TRANSACTION_ID, END_TO_END_ID, TX_FILE_NAME, FPS_REF_NUM, RESEND_COUNT, TX_REJ_CODE, TX_REJ_REASON, CREATE_TS, LAST_UPDATE_TS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
	   String p100_sql = "INSERT INTO TB_TX_P110DAT(JNL_NO, CATEGORY_PURPOSE, ACCT_VERF, SETTLEMENT_CUR, SETTLEMENT_AMT, SETTLEMENT_DATE, INSTRUCTED_CUR, INSTRUCTED_AMT, CHG_AGT_ID, CHG_AGT_BIC, CHG_CUR, CHG_AMT, DEBTOR_NAME, DEBTOR_ACCTNO, DEBTOR_ACCTNO_TYPE, DEBTOR_AGT_ID, DEBTOR_AGT_BIC, DEBTOR_CONT_PHONE, DEBTOR_CONT_EMADDR, CREDITOR_NAME, CREDITOR_ACCTNO, CREDITOR_ACCTNO_TYPE, CREDITOR_AGT_ID, CREDITOR_AGT_BIC, PURPOSE_TYPE, PURPOSE_CODE, PURPOSE_OTHER, REMIT_INFO, SRVC_MODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
	   String inward_sql = "UPDATE TB_BH_INWARD_FPSPYCI SET STATUS = ?, LAST_MODI_DATE = ? WHERE ID = ? AND STATUS = ?";
	   try
	   {
		   conn.setAutoCommit(false);
 
		   pstm = conn.prepareStatement(jnl_sql);
		   pstm2 = conn.prepareStatement(p100_sql);
		   pstm3 = conn.prepareStatement(inward_sql);
		   int i = 1;
		   String ref_no = FFPIDUtils.getSrcRefNm();
		   String inward_id = txInf.getSrcRefNm();
 
		   FFPTxJnl txJnl = txInf.getTxJnl();
 
		   txInf.setSrcRefNm(ref_no);
		   pstm.setString(1, ffp_jnl_no);
		   pstm.setString(2, ref_no);
		   pstm.setString(3, FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
		   pstm.setString(4, FFPConstantsTxJnl.TX_CODE.TX_CODE_P110.getCode());
		   pstm.setString(5, "FFP");
		   pstm.setString(6, "BTCH");
		   pstm.setString(7, txJnl.getTransactionId());
		   pstm.setString(8, txJnl.getEndToEndId());
		   pstm.setString(9, null);
		   pstm.setString(10, txJnl.getFpsRefNm());
		   pstm.setInt(11, 0);
		   pstm.setString(12, null);
		   pstm.setString(13, null);
		   pstm.setTimestamp(14, new Timestamp(new Date().getTime()));
		   pstm.setTimestamp(15, new Timestamp(new Date().getTime()));
 
		   pstm2.setString(i++, ffp_jnl_no);
 
		   pstm2.setString(i++, txInf.getPymtCatPrps());
		   pstm2.setString(i++, txInf.getAccountVerification());
		   pstm2.setString(i++, txInf.getSettlementCurrency());
		   pstm2.setString(i++, (txInf.getSettlementAmount() != null) ? String.valueOf(txInf.getSettlementAmount()) : null);
		   pstm2.setTimestamp(i++, new Timestamp(txInf.getSettlementDate().getTime()));
		   pstm2.setString(i++, txInf.getInstructedCurrency());
		   pstm2.setString(i++, (txInf.getInstructedAmount() != null) ? String.valueOf(txInf.getInstructedAmount()) : null);
		   pstm2.setString(i++, null);
		   pstm2.setString(i++, null);
		   pstm2.setString(i++, null);
		   pstm2.setString(i++, null);
		   pstm2.setString(i++, txInf.getDebtorName());
		   pstm2.setString(i++, txInf.getDebtorAccountNumber());
		   pstm2.setString(i++, txInf.getDebtorAccountNumberType());
		   pstm2.setString(i++, txInf.getDebtorAgentId());
		   pstm2.setString(i++, txInf.getDebtorAgentBic());
		   
		   pstm2.setString(i++, txInf.getDbtrContPhone());
		   pstm2.setString(i++, txInf.getDbtrContEmailAddr());
		   
	       pstm2.setString(i++, txInf.getCreditorName());
	       pstm2.setString(i++, txInf.getCreditorAccountNumber());
	       pstm2.setString(i++, txInf.getCreditorAccountNumberType());
	       pstm2.setString(i++, txInf.getCreditorAgentId());
	       pstm2.setString(i++, txInf.getCreditorAgentBic());
	       pstm2.setString(i++, txInf.getPaymentPurposeType());
	       pstm2.setString(i++, txInf.getPaymentPurposeCd());
	       pstm2.setString(i++, txInf.getPaymentPurposeProprietary());
	       pstm2.setString(i++, txInf.getRemittanceInformation());
	       pstm2.setString(i++, txInf.getSrvcMode());
 
	       pstm3.setString(1, FFPStatus.TEMP_CREDIT_STATUS.INWARD_FINISH.getCode());
	       pstm3.setTimestamp(2, new Timestamp(new Date().getTime()));
	       pstm3.setString(3, inward_id);
	       pstm3.setString(4, FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode());
 
	       pstm.executeUpdate();
	       pstm2.executeUpdate();
	       pstm3.executeUpdate();
 
	       conn.commit();
	       conn.setAutoCommit(true);
	   }
	   catch (Exception ex)
	   {
		   ex.printStackTrace();
		   this.batchLogger.error("Copy credit transfer data from temporary table of SQL execute batch failed", ex);
		   if (conn != null)
		   {
			   conn.rollback();
		   }
		   throw new BatchJobException(ex);
	   }
	   finally
	   {
		   if (pstm != null) pstm.close();
		   if (pstm2 != null) pstm2.close();
		   if (pstm3 != null) pstm3.close();
	   }
   	}
 }

