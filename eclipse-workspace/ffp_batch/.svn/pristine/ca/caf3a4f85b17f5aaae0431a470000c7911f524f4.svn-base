package com.forms.batch.job.unit.participant.rejectedmessage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.forms.ffp.core.utils.FFPEmailUtils;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class RejectedMessageOutwardBatchResponseProcessor extends BatchBaseJob {
	private static final String EMAIL_ADMI_SUBJECT = "ERROR:Some Rejected Message From FPS Are Received!";

	public void init() {

	}

	@Override
	public boolean execute() throws BatchJobException {

		List<Map<String, Object>> resMapList = getDataFromDB();

		if (resMapList != null && resMapList.size() > 0) {
			processRejMsg(resMapList);
		} else {
			this.batchLogger.info("No Data Found!");
		}

		return true;
	}

	private List<Map<String, Object>> getDataFromDB() throws BatchJobException {

		Connection conn = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();

			String query_fpserri_temp_sql = "SELECT * FROM tb_tx_fpserri_temp WHERE STATUS = ?";
			ArrayList<String> query_fpserri_temp_list = new ArrayList<>();
			query_fpserri_temp_list.add("I");

			return EntityManager.queryMapList(conn, query_fpserri_temp_sql, query_fpserri_temp_list.toArray());
		} catch (BatchFrameworkException e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException(
					"BatchFrameworkException Occurred In RejectedMessageOutwardBatchResponseProcessor!");
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In RejectedMessageOutwardBatchResponseProcessor!");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				this.batchLogger.error(e.getMessage());
				throw new BatchJobException("Error Occurred In Closing The Database!");
			}
		}

	}

	private void processRejMsg(List<Map<String, Object>> resMapList) throws BatchJobException {
		String subject = EMAIL_ADMI_SUBJECT;
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < resMapList.size(); i++) {
			content.append("Message ");
			content.append(i + 1);
			content.append("<br />");
			content.append("File Name: ");
			content.append((String) resMapList.get(i).get("FILE_NAME"));
			content.append("<br />");
			content.append("Message ID Of the Original Rejected Message: ");
			content.append((String) resMapList.get(i).get("REF"));
			content.append("<br />");
			content.append("Message Reject Code: ");
			content.append((String) resMapList.get(i).get("RJCTG_PTY_RSN"));
			content.append("<br />");
			content.append("Message Creation Time: ");
			content.append((Date) resMapList.get(i).get("RJCTN_DT_TM"));
			content.append("<br />");
			content.append("Message Reject Reason: ");
			content.append((String) resMapList.get(i).get("RSN_DESC"));
			content.append("<br />");
			content.append("The Original Rejected Message: ");
			content.append((String) resMapList.get(i).get("ADDTL_DATA"));
			content.append("<br />");
			content.append("<br />");
		}
		//System.out.println(content.toString());
		FFPEmailUtils.sendMaintainEmail(subject, content.toString());
		updateTempTableStatus(resMapList);
	}

	private void updateTempTableStatus(List<Map<String, Object>> resMapList) throws BatchJobException {
		Connection conn = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			String update_fpserri_temp_sql = "UPDATE tb_tx_fpserri_temp SET STATUS = ? WHERE AID = ?";
			for (int i = 0; i < resMapList.size(); i++) {
				ArrayList<String> update_fpserri_temp_list = new ArrayList<>();
				update_fpserri_temp_list.add("F");
				update_fpserri_temp_list.add((String) resMapList.get(i).get("AID"));

				EntityManager.update(conn, update_fpserri_temp_sql, update_fpserri_temp_list.toArray());
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (BatchFrameworkException e) {
			this.batchLogger.error(e.getMessage());
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				this.batchLogger.error(e1.getMessage());
				throw new BatchJobException("Error Occurred In Rolling Back!");
			}
			throw new BatchJobException(
					"BatchFrameworkException Occurred In RejectedMessageOutwardBatchResponseProcessor!");
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				this.batchLogger.error(e1.getMessage());
				throw new BatchJobException("Error Occurred In Rolling Back!");
			}
			throw new BatchJobException("Error Occurred In RejectedMessageOutwardBatchResponseProcessor!");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				this.batchLogger.error(e.getMessage());
				throw new BatchJobException("Error Occurred In Closing The Database!");
			}
		}
	}
}
