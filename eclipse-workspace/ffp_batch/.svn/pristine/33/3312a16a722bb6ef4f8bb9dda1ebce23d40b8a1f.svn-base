package com.forms.batch.job.unit.addresssevice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class AddressStatusUpdateNotificationBatchProcessor extends BatchBaseJob {
	private static String STS_CNCL = "CNCL";
	private static String STS_AMND = "AMND";
	private static String STS_NWRG = "NWRG";

	private static Connection conn = null;

	public void init() throws BatchJobException {

	}

	@Override
	public boolean execute() throws BatchJobException {
		try {
			if (conn == null) {
				conn = ConnectionManager.getInstance().getConnection();
			}

			cancelledByAnotherParticipant();
			ChangeInDefaultIndicator();
			NewRegistrationWithAnotherParticipant();

		} catch (BatchFrameworkException e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In AddressStatusUpdateNotificationBatchProcessor!");
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

	// CNCL
	private void cancelledByAnotherParticipant() {

	}

	// AMND
	private void ChangeInDefaultIndicator() {
		Date amndStartTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Start To AMND At %s", amndStartTime));

		try {
			String query_adri_temp_sql = "SELECT * FROM tb_tx_fpsadri_temp WHERE STATUS = ? AND STS = ? ORDER BY STS_UPD_TM ASC;";
			ArrayList<String> query_adri_temp_list = new ArrayList<>();

			query_adri_temp_list.add("I");
			query_adri_temp_list.add(STS_AMND);

			List<Map<String, Object>> query_adri_temp_mapList = EntityManager.queryMapList(conn, query_adri_temp_sql,
					query_adri_temp_list.toArray());

			if (query_adri_temp_mapList != null && query_adri_temp_mapList.size() > 0) {

				for (int i = 0; i < query_adri_temp_mapList.size(); i++) {

					try {
						conn.setAutoCommit(false);
						String jnlNo = FFPIDUtils.getJnlNo();

						// insert new message into tb_tx_jnl
						String insert_jnl_sql = "INSERT INTO tb_tx_jnl (JNL_NO, TX_STAT, TX_CODE, TX_SRC, TX_MODE, CREATE_TS, LAST_UPDATE_TS) "
								+ "(SELECT ?,?,?,?,?,NOW(),NOW() FROM tb_dt_addressing WHERE CUS_ID = ? AND PROXY_ID = ? AND PROXY_ID_TP = ?); ";
						ArrayList<String> insert_jnl_list = new ArrayList<>();
						insert_jnl_list.add(jnlNo);
						insert_jnl_list.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
						insert_jnl_list.add(FFPConstantsTxJnl.TX_CODE.TX_CODE_A200.getCode());
						insert_jnl_list.add((String) (query_adri_temp_mapList.get(i).get("FR")));
						insert_jnl_list.add(FFPConstants.RUNNING_MODE_BATCH);
						insert_jnl_list.add((String) (query_adri_temp_mapList.get(i).get("CUS_ID")));
						insert_jnl_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_ID")));
						insert_jnl_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_TYPE")));

						EntityManager.update(conn, insert_jnl_sql, insert_jnl_list.toArray());

						// insert new message flow into tb_tx_jnlaction
						String insert_jnlaction_sql = "INSERT INTO tb_tx_jnlaction(JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CREAT_TS, MSG_PROCE_TS, MSG_COMPL_TS) "
								+ "(SELECT ?,?,?,?,?,?,NOW(),NOW(),NOW() FROM tb_dt_addressing WHERE CUS_ID = ? AND PROXY_ID = ? AND PROXY_ID_TP = ?); ";
						ArrayList<String> insert_jnlaction_list = new ArrayList<>();
						insert_jnlaction_list.add(jnlNo);
						insert_jnlaction_list.add((String) (query_adri_temp_mapList.get(i).get("BIZ_MSG_IDR")));
						insert_jnlaction_list.add(FFPConstants.MSG_DIRECTION_INWARD);
						insert_jnlaction_list.add("ICL");
						insert_jnlaction_list.add((String) (query_adri_temp_mapList.get(i).get("MSG_DEF_IDR")));
						insert_jnlaction_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
						insert_jnlaction_list.add((String) (query_adri_temp_mapList.get(i).get("CUS_ID")));
						insert_jnlaction_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_ID")));
						insert_jnlaction_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_TYPE")));

						EntityManager.update(conn, insert_jnlaction_sql, insert_jnlaction_list.toArray());

						// update tb_dt_addressing DFLT & LSTUP_JNL
						String update_address_sql = "UPDATE tb_dt_addressing SET DFLT = ?, LSTUP_JNL = ? WHERE CUS_ID = ? AND PROXY_ID = ? AND PROXY_ID_TP = ?; ";
						ArrayList<String> update_address_list = new ArrayList<>();
						update_address_list.add((String) (query_adri_temp_mapList.get(i).get("DFLT")));
						update_address_list.add(jnlNo);
						update_address_list.add((String) (query_adri_temp_mapList.get(i).get("CUS_ID")));
						update_address_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_ID")));
						update_address_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_TYPE")));

						EntityManager.update(conn, update_address_sql, update_address_list.toArray());
						
						// update tb_tx_fpsadri_temp STATUS success
						String update_fpsadri_temp_success_sql = "UPDATE tb_tx_fpsadri_temp SET `STATUS` = ? WHERE TID = ? AND EXISTS(SELECT 1 FROM tb_dt_addressing WHERE CUS_ID = ? AND PROXY_ID = ? AND PROXY_ID_TP = ?); ";
						ArrayList<String> update_fpsadri_temp_success_list = new ArrayList<>();
						update_fpsadri_temp_success_list.add("F");
						update_fpsadri_temp_success_list.add((String) (query_adri_temp_mapList.get(i).get("TID")));
						update_fpsadri_temp_success_list.add((String) (query_adri_temp_mapList.get(i).get("CUS_ID")));
						update_fpsadri_temp_success_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_ID")));
						update_fpsadri_temp_success_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_TYPE")));

						EntityManager.update(conn, update_fpsadri_temp_success_sql, update_fpsadri_temp_success_list.toArray());
						
						// update tb_tx_fpsadri_temp STATUS fail
						String update_fpsadri_temp_fail_sql = "UPDATE tb_tx_fpsadri_temp SET `STATUS` = ? WHERE TID = ? AND NOT EXISTS(SELECT 1 FROM tb_dt_addressing WHERE CUS_ID = ? AND PROXY_ID = ? AND PROXY_ID_TP = ?); ";
						ArrayList<String> update_fpsadri_temp_fail_list = new ArrayList<>();
						update_fpsadri_temp_fail_list.add("E");
						update_fpsadri_temp_fail_list.add((String) (query_adri_temp_mapList.get(i).get("TID")));
						update_fpsadri_temp_fail_list.add((String) (query_adri_temp_mapList.get(i).get("CUS_ID")));
						update_fpsadri_temp_fail_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_ID")));
						update_fpsadri_temp_fail_list.add((String) (query_adri_temp_mapList.get(i).get("PROXY_TYPE")));

						EntityManager.update(conn, update_fpsadri_temp_fail_sql, update_fpsadri_temp_fail_list.toArray());

						conn.commit();
						conn.setAutoCommit(true);
						
					} catch (SQLException e) {
						this.batchLogger.error(e.getMessage());
						try {
							conn.rollback();
						} catch (SQLException e1) {
							this.batchLogger.error(e1.getMessage());
						}
					} catch (Exception e) {
						this.batchLogger.error(e.getMessage());
						try {
							conn.rollback();
						} catch (SQLException e1) {
							this.batchLogger.error(e1.getMessage());
						}
					}
				}
			}

		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
		}

		Date amndEndTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Finish AMND At %s", amndEndTime));
		this.batchLogger.info(String.format("AMND Use %.3f Seconds",
				(double) (amndEndTime.getTime() - amndStartTime.getTime()) / 1000));

	}

	// NWRG
	private void NewRegistrationWithAnotherParticipant() {

	}
}
