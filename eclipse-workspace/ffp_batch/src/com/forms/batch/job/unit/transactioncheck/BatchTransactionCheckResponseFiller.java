package com.forms.batch.job.unit.transactioncheck;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class BatchTransactionCheckResponseFiller extends BatchBaseJob {
	private static Connection conn = null;

	public void init() throws BatchJobException {

	}

	@Override
	public boolean execute() throws BatchJobException {
		// TODO Auto-generated method stub
		try {
			if (conn == null) {
				conn = ConnectionManager.getInstance().getConnection();
				this.batchLogger.info("Database Is Connected");
			}

			fillRespMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.batchLogger.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				this.batchLogger.error(e1);
				throw new BatchJobException("Fail To Rollback In BatchTransactionCheckResponseFiller!");
			}
			throw new BatchJobException("Error Occurred In BatchTransactionCheckProcessor!");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					this.batchLogger.error(e.getMessage());
					throw new BatchJobException("Fail To Close Connection To DB In BatchTransactionCheckReader!");
				}
				this.batchLogger.info("Database Is Closed");
			}
		}

		return true;

	}

	private void fillRespMessage() throws Exception {
		Date checkStartTime = Calendar.getInstance().getTime();
		this.batchLogger
				.info(String.format("Start to Fill The Response Data In jnlaction Table At %s", checkStartTime));
		conn.setAutoCommit(false);
		String insert_res_jnlaction_compl_sql = "INSERT INTO tb_tx_jnlaction ( "
				+ "JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_PROCE_TS, MSG_COMPL_TS, REF_MSG_ID, IS_AUTOCHECK "
				+ ") SELECT " + "B.JNL_NO AS JNL_NO, A.RESPONSE_REF_NO AS MSG_ID, ?, "
				+ "A.RESPONSE_ID AS MSG_SYSTEMID, A.MESSAGE_TYPE AS MSG_TYPE, ?, "
				+ "A.RESPONSE_MESSAGE_CODE AS MSG_CODE, A.RESPONSE_MESSAGE AS MSG_RESULT, now(), now(), "
				+ "A.REQUEST_REF_NO AS REF_MSG_ID, ? FROM tb_tx_check_temp A "
				+ "LEFT JOIN tb_tx_jnlaction B ON A.REQUEST_REF_NO = B.MSG_ID AND A.MESSAGE_TYPE = B.MSG_TYPE "
				+ "LEFT JOIN tb_tx_jnlaction C ON B.MSG_ID = C.REF_MSG_ID WHERE A.RESPONSE_STATUS = ? "
				+ "AND A.CHECK_STATUS = ? AND B.MSG_STATUS = ? AND C.JNL_NO IS NULL; ";

		ArrayList<Object> insert_res_jnlaction_compl_list = new ArrayList<>();
		insert_res_jnlaction_compl_list.add("I");
		insert_res_jnlaction_compl_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
		insert_res_jnlaction_compl_list.add("N");
		insert_res_jnlaction_compl_list.add("N");
		insert_res_jnlaction_compl_list.add("I");
		insert_res_jnlaction_compl_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		EntityManager.update(conn, insert_res_jnlaction_compl_sql, insert_res_jnlaction_compl_list.toArray());

		String insert_res_jnlaction_rejct_sql = "INSERT INTO tb_tx_jnlaction ( "
				+ "JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_PROCE_TS, MSG_COMPL_TS, REF_MSG_ID, IS_AUTOCHECK "
				+ ") SELECT B.JNL_NO AS JNL_NO, A.RESPONSE_REF_NO AS MSG_ID, ?, "
				+ "A.RESPONSE_ID AS MSG_SYSTEMID, A.MESSAGE_TYPE AS MSG_TYPE, ?, "
				+ "A.RESPONSE_MESSAGE_CODE AS MSG_CODE, A.RESPONSE_MESSAGE AS MSG_RESULT, now(), now(), "
				+ "A.REQUEST_REF_NO AS REF_MSG_ID, ? FROM tb_tx_check_temp A "
				+ "LEFT JOIN tb_tx_jnlaction B ON A.REQUEST_REF_NO = B.MSG_ID AND A.MESSAGE_TYPE = B.MSG_TYPE "
				+ "LEFT JOIN tb_tx_jnlaction C ON B.MSG_ID = C.REF_MSG_ID WHERE A.RESPONSE_STATUS = ? "
				+ "AND A.CHECK_STATUS = ? AND B.MSG_STATUS = ? AND C.JNL_NO IS NULL; ";

		ArrayList<Object> insert_res_jnlaction_rejct_list = new ArrayList<>();
		insert_res_jnlaction_rejct_list.add("I");
		insert_res_jnlaction_rejct_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
		insert_res_jnlaction_rejct_list.add("N");
		insert_res_jnlaction_rejct_list.add("E");
		insert_res_jnlaction_rejct_list.add("I");
		insert_res_jnlaction_rejct_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		EntityManager.update(conn, insert_res_jnlaction_rejct_sql, insert_res_jnlaction_rejct_list.toArray());

		String update_res_jnl_compl_sql = "UPDATE tb_tx_jnl C SET TX_STAT = ( SELECT ? "
				+ "FROM tb_tx_jnlaction A INNER JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID "
				+ "AND B.MSG_STATUS = ? WHERE A.MSG_STATUS = ? AND A.JNL_NO = C.JNL_NO ), TX_REJ_CODE = '', TX_REJ_REASON = ''"
				+ "WHERE JNL_NO IN ( SELECT A.JNL_NO FROM tb_tx_jnlaction A "
				+ "INNER JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID AND B.MSG_STATUS = ? "
				+ "WHERE A.MSG_STATUS = ? ); ";

		ArrayList<Object> update_res_jnl_compl_list = new ArrayList<>();
		update_res_jnl_compl_list.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
		update_res_jnl_compl_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
		update_res_jnl_compl_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		update_res_jnl_compl_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
		update_res_jnl_compl_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());

		EntityManager.update(conn, update_res_jnl_compl_sql, update_res_jnl_compl_list.toArray());

		String update_res_jnl_rej_timeout_sql = "UPDATE tb_tx_jnl C SET TX_STAT = ( SELECT ? "
				+ "FROM tb_tx_jnlaction A LEFT JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID "
				+ "WHERE A.MSG_STATUS = ? AND B.MSG_STATUS = ? ORDER BY A.MSG_CREAT_TS DESC "
				+ "LIMIT 1 ), TX_REJ_CODE = ( SELECT B.MSG_CODE FROM tb_tx_jnlaction A "
				+ "LEFT JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID WHERE A.MSG_STATUS = ? "
				+ "AND B.MSG_STATUS = ? ORDER BY A.MSG_CREAT_TS DESC LIMIT 1 ), TX_REJ_REASON = ( "
				+ "SELECT B.MSG_RESULT FROM tb_tx_jnlaction A "
				+ "LEFT JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID WHERE A.MSG_STATUS = ? "
				+ "AND B.MSG_STATUS = ? ORDER BY A.MSG_CREAT_TS DESC LIMIT 1 ) "
				+ "WHERE JNL_NO NOT IN ( SELECT A.JNL_NO FROM tb_tx_jnlaction A "
				+ "INNER JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID AND B.MSG_STATUS = ? "
				+ "WHERE A.MSG_STATUS = ? ) AND TX_STAT = ?; ";

		ArrayList<Object> update_res_jnl_rej_timeout_list = new ArrayList<>();
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_AGENTREJCT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		update_res_jnl_rej_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());

		EntityManager.update(conn, update_res_jnl_rej_timeout_sql, update_res_jnl_rej_timeout_list.toArray());

		String update_req_jnlaction_timeout_sql = "UPDATE tb_tx_jnlaction SET MSG_STATUS = ?, MSG_COMPL_TS = now() "
				+ "WHERE MSG_ID IN ( SELECT MSG_ID FROM (SELECT A.* FROM tb_tx_jnlaction A "
				+ "INNER JOIN tb_tx_jnlaction B ON A.MSG_ID = B.REF_MSG_ID WHERE A.MSG_STATUS = ? ) AS C ) "
				+ "AND MSG_STATUS = ?; ";

		ArrayList<Object> update_req_jnlaction_timeout_list = new ArrayList<>();
		update_req_jnlaction_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
		update_req_jnlaction_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
		update_req_jnlaction_timeout_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());

		EntityManager.update(conn, update_req_jnlaction_timeout_sql, update_req_jnlaction_timeout_list.toArray());

		conn.commit();
		conn.setAutoCommit(true);

		Date checkEndTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Finish Filling The Response Data In jnlaction Table At %s", checkEndTime));
		this.batchLogger.info(String.format("Filling Data Use %.3f Seconds",
				(double) (checkEndTime.getTime() - checkStartTime.getTime()) / 1000));
	}
}
