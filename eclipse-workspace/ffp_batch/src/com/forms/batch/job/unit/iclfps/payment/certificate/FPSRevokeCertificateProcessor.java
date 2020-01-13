package com.forms.batch.job.unit.iclfps.payment.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class FPSRevokeCertificateProcessor extends BatchBaseJob {
	private static Connection conn = null;

	public void init() throws BatchJobException {

	}

	@Override
	public boolean execute() throws BatchJobException {
		try {
			conn = ConnectionManager.getInstance().getConnection();
			List<Map<String, Object>> queryMapList = queryFromTempTable();

			if (queryMapList != null && queryMapList.size() > 0) {
				updateRevokedTime(queryMapList);
			} else {
				this.batchLogger.info("No Date Found!");
			}

		} catch (BatchFrameworkException e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In FPSRevokeCertificateProcessor!");

		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In FPSRevokeCertificateProcessor!");

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					this.batchLogger.error(e.getMessage());
					throw new BatchJobException("Error Occurred In Closing The Database!");
				}
			}
		}

		return true;
	}

	private List<Map<String, Object>> queryFromTempTable() throws Exception {
		String query_ceri_temp_sql = "SELECT * FROM tb_bh_revoke_ceri WHERE STATUS = ? ORDER BY BATCH_ID ASC, RANKING ASC; ";

		ArrayList<String> query_ceri_temp_list = new ArrayList<>();
		query_ceri_temp_list.add("I");

		return EntityManager.queryMapList(conn, query_ceri_temp_sql, query_ceri_temp_list.toArray());

	}

	private void updateRevokedTime(List<Map<String, Object>> queryMapList) {

		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			this.batchLogger.error(e.getMessage());
		}

		for (int i = 0; i < queryMapList.size(); i++) {
			try {

				// update tb_dt_ecert
				String update_ecert_sql = "UPDATE tb_dt_ecert SET REVOKED_TS = ? WHERE ECERT_KEY = ?; ";
				ArrayList<Object> update_ecert_list = new ArrayList<>();
				update_ecert_list.add((Date) queryMapList.get(i).get("REVOKED_TS"));
				update_ecert_list.add((String) queryMapList.get(i).get("ECERT_KEY"));

				int affectedLineNum = EntityManager.update(conn, update_ecert_sql, update_ecert_list.toArray());

				// update tb_dt_ceri_temp
				String update_ceri_temp_sql = "UPDATE tb_bh_revoke_ceri SET STATUS = ? WHERE CID = ?; ";
				ArrayList<String> update_ceri_temp_list = new ArrayList<>();

				if (affectedLineNum == 1) {
					update_ceri_temp_list.add("F");
				} else if (affectedLineNum == 0) {
					update_ceri_temp_list.add("E");
				} else {
					update_ceri_temp_list.add("F");
					this.batchLogger.warn("More Than 1 Certificates Affected!");
				}
				
				update_ceri_temp_list.add((String) queryMapList.get(i).get("CID"));

				EntityManager.update(conn, update_ceri_temp_sql, update_ceri_temp_list.toArray());

			} catch (Exception e) {
				this.batchLogger.error(e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException e1) {
					this.batchLogger.error(e1.getMessage());
				}
			}
		}

		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			this.batchLogger.error(e.getMessage());
		}
	}

}
