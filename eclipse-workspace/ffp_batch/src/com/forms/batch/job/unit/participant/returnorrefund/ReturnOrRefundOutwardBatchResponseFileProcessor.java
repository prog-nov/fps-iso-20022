package com.forms.batch.job.unit.participant.returnorrefund;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.forms.batch.job.unit.participant.message.ffprro01.FFPBatchMsg_RRO01;
import com.forms.batch.util.CuttoffUtils;
import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.bussiness.iclfps.pacs002.FFPVO_Pacs002_TxInfAndSts;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPStatus;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.FFPBaseResp;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class ReturnOrRefundOutwardBatchResponseFileProcessor extends BatchBaseJob {

	public void init() {
		// TBC
	}

	@Override
	public boolean execute() throws BatchJobException {
		try {
			this.processor();
			return true;
		} catch (Exception ip_e) {
			throw new BatchJobException(ip_e);
		}
	}

	private void processor() throws Exception {
		Connection con = null;

		try {
			con = ConnectionManager.getInstance().getConnection();

			boolean agentCutOff = CuttoffUtils.isCutoff("FFP");
			if (agentCutOff) {
				batchLogger.info("FFP Agent CUT OFF right now!");
				return;
			}

			List<FFPVO_Pacs002_TxInfAndSts> tepDataList = getTempData();

			processResponseTxData(con, tepDataList);
		} catch (Exception ex) {
			ex.printStackTrace();
			batchLogger.error("Process Return/Refund file error", ex);
			throw new BatchJobException(ex);
		} finally {
			if (con != null)
				con.close();
		}
	}

	// get data from temp
	private List<FFPVO_Pacs002_TxInfAndSts> getTempData() throws BatchJobException {
		List<FFPVO_Pacs002_TxInfAndSts> txInfList = new ArrayList<FFPVO_Pacs002_TxInfAndSts>();

		try {
			String sql_search = "SELECT * FROM TB_BH_OUTWARD_FPSPCRR WHERE STATUS = ?";
			// String getSrcRefNmSql = "SELECT SRC_REF_NM FROM TB_TX_P300DAT A
			// LEFT JOIN TB_TX_JNL B ON A.JNL_NO = B.JNL_NO WHERE
			// B.TRANSACTION_ID = ? AND B.END_TO_END_ID = ? ";

			List<Object> search_param_list = new ArrayList<Object>();
			search_param_list.add(FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode());

			List<Map<String, Object>> queryMapList = EntityManager.queryMapList(sql_search,
					search_param_list.toArray());
			if (queryMapList != null && queryMapList.size() > 0) {
				for (Map<String, Object> map : queryMapList) {
					FFPVO_Pacs002_TxInfAndSts toAgent = new FFPVO_Pacs002_TxInfAndSts();
					toAgent.setOrgnlTxId(
							map.get("ORG_TRANSACTION_ID") != null ? (String) (map.get("ORG_TRANSACTION_ID")) : null);
					toAgent.setOrgnlEndToEndId(
							map.get("ORG_END_TO_END_ID") != null ? (String) (map.get("ORG_END_TO_END_ID")) : null);
					toAgent.setTxSts(map.get("TX_STAT") != null ? (String) (map.get("TX_STAT")) : null);
					toAgent.setTxStsRsnCode(map.get("TX_REJ_CODE") != null ? (String) (map.get("TX_REJ_CODE")) : null);
					List<String> reason = new ArrayList<String>();
					if (map.get("TX_ADD_INFO") != null) {
						reason.add((String) (map.get("TX_ADD_INFO")));
					}
					toAgent.setTxStsAddtlInfList(reason.size() > 0 ? reason : null);
					toAgent.setClrSysRef(
							map.get("TX_CLR_SYS_REF") != null ? (String) (map.get("TX_CLR_SYS_REF")) : null);

					toAgent.setTempBatchId((int) map.get("ID"));// Temp table
																// records id
					txInfList.add(toAgent);
				}
			} else {
				batchLogger.info("TEMP TABLE  EMPTY ! ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			batchLogger.error(
					String.format("Failed to get file data from temporary table with batchDate[%s]", this.batchAcDate),
					ex);
			throw new BatchJobException(ex);
		}
		return txInfList;
	}

	private void processResponseTxData(Connection conn, List<FFPVO_Pacs002_TxInfAndSts> tepDataList)
	{
		Date loc_startTime = Calendar.getInstance().getTime();
		batchLogger.info("Process outward batch Return/Refund response datas started at" + loc_startTime);
		
		for (FFPVO_Pacs002_TxInfAndSts tx_pacs002 : tepDataList)
		{
			try
			{
				//Add parameters for avoid get credit transfer data
				String sql_query = "SELECT JNL.JNL_NO, JNL.SRC_REF_NM, ACTION.MSG_ID FROM TB_TX_JNL JNL JOIN TB_TX_JNLACTION ACTION ON JNL.JNL_NO = ACTION.JNL_NO "
						+ 	 	   "WHERE JNL.TRANSACTION_ID = ? AND JNL.END_TO_END_ID = ? AND (JNL.FPS_REF_NUM IS NULL OR JNL.FPS_REF_NUM ='') AND JNL.TX_CODE = ? AND ACTION.MSG_TYPE = ?";
				
				List<Object> listData = new ArrayList<Object>();
				listData.add(tx_pacs002.getOrgnlTxId());
				listData.add(tx_pacs002.getOrgnlEndToEndId());
				listData.add(FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode());
				listData.add(FFPJaxbConstants.JAXB_MSG_TYPE_FFPRRO01);
				
				//a.Query transaction original data from FFP database
				List<Map<String, Object>> result = EntityManager.queryMapList(conn, sql_query, listData.toArray());
				if(result == null || result.size() < 1)
				{
					batchLogger.info(String.format("FFP can not find the transaction info with TX_ID[%s] and END_TO_END_ID[%s]", tx_pacs002.getOrgnlTxId(), tx_pacs002.getOrgnlEndToEndId()));
					//update TB_BH_OUTWARD_FPSPCRR.status = E cause FFP didn't find data
					EntityManager.update(conn, String.format("UPDATE TB_BH_OUTWARD_FPSPCRR SET STATUS = '%s' WHERE ID = %s AND STATUS = '%s'", FFPStatus.TEMP_CREDIT_STATUS.INWARD_ERROR.getCode(), 
							tx_pacs002.getTempBatchId(), FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode()));
					continue;
				}
				
				Map<String, Object> map = result.get(0);//just one result
				String jnlNo = (String)map.get("JNL_NO");
				String srcRefNo = (String)map.get("SRC_REF_NM");
				String relateReqRefNo = (String)map.get("MSG_ID");
				String resRefNo = FFPIDUtils.getRefno();//for subsequently persistent of action
				FFPJbP300 p300 = new FFPJbP300();
				p300.setSrcRefNm(srcRefNo);
				tx_pacs002.setP300Jb(p300);
				String rejCd = tx_pacs002.getTxStsRsnCode();
				String addInfo = tx_pacs002.getTxStsAddtlInfList() != null ? tx_pacs002.getTxStsAddtlInfList().get(0) : null;
				String fpsRef = tx_pacs002.getClrSysRef();
				int tempId = tx_pacs002.getTempBatchId();
				
				//b.Update tb_tx_jnl.status = pacs002.status, fpsRefNo = 'fps_ref_no', tb_tx_jnlaction.msg_status = 'MSNY', tb_bh_outward_fpspcrr.status = 'F'
				updateTxStatus(conn, jnlNo, relateReqRefNo, tx_pacs002.getTxSts(), fpsRef, rejCd, addInfo, tempId);
				
				//c.Check Agent cut-off or not
				boolean agentCutOff = CuttoffUtils.isCutoff(FFPConstants.CUTOFF_TYPE_FFP);
				if(agentCutOff)
				{
					batchLogger.info(String.format("FFP Agent CUT OFF right now in processing transaction data[TX_ID:%s]!", tx_pacs002.getOrgnlTxId()));
					continue;
				}
				
				//d.Add action flow for response message to FFP Agent
				insertAcitonFlow(conn, jnlNo, resRefNo, FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.MSG_CODE_AGENT, FFPJaxbConstants.JAXB_MSG_TYPE_FFPRRO01,
						FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), null, null, new Date(), relateReqRefNo, FFPConstants.IS_AUTO_CHECK_FALG_N);
				
				//e.Send response to FFP Agent
				FFPBatchMsg_RRO01 rro01 = new FFPBatchMsg_RRO01(tx_pacs002, relateReqRefNo, resRefNo);
				FFPBaseResp response = FFPAdaptorMgr.getInstance().execute(rro01);
				if(response != null)
				{
					FFPSendTcpMessageResp resp = (FFPSendTcpMessageResp)response;
					//f.Timeout
					if(resp.isTimeOut())
					{
						updateResultSts(conn, FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus(), jnlNo, resRefNo, null);
					}
					else
					{
						//g.Successful
						String ack = resp.getRespMessage();
						if(ack != null && ack.trim().toUpperCase().equals(FFPConstants.SEND_TYPE_ACK))
						{
							updateResultSts(conn, FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), jnlNo, resRefNo, new Date());
						}
						else
						{
							//TODO
						}
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				batchLogger.info(String.format("Error on processing Return/Refund transaction data[TX_ID:%s]!", tx_pacs002.getOrgnlTxId()));
			}
		}	
		
		Date loc_endTime = Calendar.getInstance().getTime();
		batchLogger.info("Process outward batch Return/Refund response datas started at end at" + loc_endTime);
		batchLogger.info("Process outward batch Return/Refund response datas use " + (loc_endTime.getTime() - loc_startTime.getTime()) / 1000);

	}
	
	private void updateResultSts(Connection conn, String action_status, String jnlNo, String msgId, Date comDate) throws Exception
	{
		//String sql_jnl = "UPDATE TB_TX_JNL SET TX_STAT = ?, LAST_UPDATE_TS = ? WHERE JNL_NO = ?";
		String sql_action = "UPDATE TB_TX_JNLACTION SET MSG_STATUS = ?, MSG_COMPL_TS = ?, REF_MSG_ID = ? WHERE JNL_NO = ? AND MSG_ID = ?";
		try
		{
			List<Object> params_action = new ArrayList<Object>();
			params_action.add(action_status);
			params_action.add(comDate);
			params_action.add(jnlNo);
			params_action.add(msgId);
			EntityManager.update(conn, sql_action, params_action.toArray());
		}
		catch(Exception ex)
		{
			batchLogger.error(String.format("Update ACTION_STATUS[%s] with JNL_NO[%s] and MSG_ID[%s] failed", action_status, jnlNo, msgId), ex);
			ex.printStackTrace();
			throw new BatchJobException(ex);
		}
	}
	
	private void insertAcitonFlow(Connection conn, String jnlNo, String msgId, String msgDirection, String msgSystemId, String msgType, String msgStatus, 
			String msgCode, String msgResult, Date comDate, String refMsgId, String is_check) throws BatchJobException
	{
		String sql = "INSERT INTO TB_TX_JNLACTION(JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_CREAT_TS, "
				+ 	 "MSG_PROCE_TS, MSG_COMPL_TS, REF_MSG_ID, IS_AUTOCHECK) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
			List<Object> list = new ArrayList<Object>();
			list.add(jnlNo);								//JNL_NO
			list.add(msgId);								//MSG_ID
			list.add(msgDirection);							//MSG_DIRECTION
			list.add(msgSystemId);							//MSG_SYSTEMID
			list.add(msgType);								//MSG_TYPE
			list.add(msgStatus);							//MSG_STATUS
			list.add(msgCode);								//MSG_CODE
			list.add(msgResult);							//MSG_RESULT
			list.add(new java.sql.Timestamp(new Date().getTime()));//MSG_CREAT_TS
			list.add(new java.sql.Timestamp(new Date().getTime()));//MSG_PROCE_TS
			list.add(comDate);								//MSG_COMPL_TS
			list.add(refMsgId);								//REF_MSG_ID
			list.add(is_check);								//IS_AUTOCHECK
			
			EntityManager.update(conn, sql, list.toArray());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			batchLogger.error("Error on adding action flow", ex);
			throw new BatchJobException(ex);
		}
	}
	
	private void updateTxStatus(Connection con, String jnlNo, String msgId, String txSts, String fpsRef, String rejCd, String rejMsg, int temId) throws Exception
	{
		String update_jnl = "UPDATE TB_TX_JNL SET TX_STAT = ?, FPS_REF_NUM = ?, TX_REJ_CODE = ?, TX_REJ_REASON = ?, LAST_UPDATE_TS = ? WHERE JNL_NO = ?";
		String update_temp = "UPDATE TB_BH_OUTWARD_FPSPCRR SET STATUS = ? WHERE ID = ? AND STATUS = ?";
		String update_action = "UPDATE TB_TX_JNLACTION SET MSG_STATUS = ?, MSG_COMPL_TS = ? WHERE JNL_NO = ? AND MSG_ID = ?";
		try
		{
			con.setAutoCommit(false);
			
			List<Object> params_jnl = new ArrayList<Object>();
			List<Object> params_temp = new ArrayList<Object>();
			List<Object> params_action = new ArrayList<Object>();
			params_jnl.add(txSts);
			params_jnl.add(fpsRef);
			params_jnl.add(rejCd);
			params_jnl.add(rejMsg);
			params_jnl.add(new Date());
			params_jnl.add(jnlNo);
			
			params_temp.add(FFPStatus.TEMP_CREDIT_STATUS.INWARD_FINISH.getCode());
			params_temp.add(temId);
			params_temp.add(FFPStatus.TEMP_CREDIT_STATUS.INWARD_INITATE.getCode());
			
			params_action.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
			params_action.add(new Date());
			params_action.add(jnlNo);
			params_action.add(msgId);
			
			EntityManager.update(con, update_jnl, params_jnl.toArray());
			EntityManager.update(con, update_temp, params_temp.toArray());
			EntityManager.update(con, update_action, params_action.toArray());
			
			con.commit();
			con.setAutoCommit(true);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			batchLogger.error("Update transaction status error", ex);
			if(con != null)
				con.rollback();
			throw new BatchJobException(ex);
		}
	}
}
