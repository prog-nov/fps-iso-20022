package com.forms.batch.job.unit.participant.receivefile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.batch.util.MessageValidation;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcto01.FFPCTO01;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpddo01.FFPDDO01;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsPurposeCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.log.BatchLogger;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.CommonAPI;

public class FFPReceiveFileServerThread implements Runnable {
	private BatchLogger _logger = BatchLogger.getLogger("BATCH_LISTENER_SESSION",
			String.valueOf(Thread.currentThread().getId()), FFPReceiveFileServerThread.class);
	private int SO_TIME_OUT = 60000;
	private String jnlNo;
	private String transactionId;
	private String end2endId;
	private Timestamp createTs;
	private String curClearingCode;
	Socket socket = null;// this thread socket

	public FFPReceiveFileServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		init();

		ROOT root = null;
		try {
			// communication with the client,
			// get the InputStream,
			// read the information provied by the client.
			socket.setSoTimeout(SO_TIME_OUT);
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = bis.read(buffer)) != -1) {
				// loop read
				bo.write(buffer, 0, length);
			}
			byte[] databytes = bo.toByteArray();
			bo.close();

			String receiveStr = new String(databytes, CommonAPI.ENCODING_UTF_8);
			_logger.info("Requst msg:" + receiveStr);

			socket.shutdownInput();
			String xml = "";
			try {
				root = FFPParticipantMessageConverter.parseXml2RequestObject(receiveStr.toString());
				xml = executeMessage(root);
				sendSyncMessage(xml);
			} catch (Exception ex) {
				_logger.error("Error on processing FFP Agent request message", ex);
				sendSyncMessage(ex.getMessage());
				throw new Exception("Error on processing FFP Agent request message", ex);
			}
			socket.close();
		} catch (Exception e) {
			_logger.error(e);
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void init() {
		jnlNo = FFPIDUtils.getJnlNo();
		transactionId = FFPIDUtils.getTransactionId();
		end2endId = FFPIDUtils.getEndToEndId();
		createTs = FFPDateUtils.getTimestamp(new Date());
		try {
			this.curClearingCode = BatchEnvBuilder.getInstance().getEnvList().get(CommonAPI.ENV_CLEARINGCODE);
		} catch (Exception e) {
			_logger.error("getClearingCode()", e);
		}
		// seqNo = FFPIDUtils.getSeq();
	}

	public void sendSyncMessage(String str) {
		try {
			OutputStream os = socket.getOutputStream();
			os.write(str.getBytes(CommonAPI.ENCODING_UTF_8));
			os.flush();
			socket.shutdownOutput();
		} catch (Exception ip_e) {
			_logger.error(ip_e);
		}
	}

	public HashMap<String, String> save(ROOT root) throws Exception {
		HashMap<String, String> resMap = new HashMap<>();
		resMap.put("result", "1");
		resMap.put("msg", "");
		HEAD head = root.getHEAD();
		BODY body = root.getBODY();

		String txState = FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus();
		String txCode = "";
		String txMode = "BTCH";
		String txSrc = FFPConstants.MSG_TYPE_AGENT;
		Timestamp lastUpdateTs = createTs;
		String msgFrom = head.getRequestID();
		String msgType = head.getMessageType();
		String msgStatus = FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus();
		Timestamp msgCreateData = createTs;
		String srcRefNm = null;

		Connection loc_conn = null;
		String sql_jnl = "INSERT INTO TB_TX_JNL(JNL_NO, SRC_REF_NM, TX_STAT, TX_CODE, TX_SRC, TX_MODE, TRANSACTION_ID, END_TO_END_ID, CREATE_TS, LAST_UPDATE_TS) VALUES(?,?,?,?,?,?,?,?,?,?) ";

		String sql_action = "INSERT INTO TB_TX_JNLACTION(JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_CREAT_TS, "
				+ "MSG_PROCE_TS, MSG_COMPL_TS) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			loc_conn = ConnectionManager.getInstance().getConnection();
			loc_conn.setAutoCommit(false);
			Map<String, List<Object>> exeMap = new HashMap<String, List<Object>>();

			if (body instanceof FFPCTO01) {
				FFPCTO01 ffpcto01 = (FFPCTO01) body;

				String servcMode = ffpcto01.getSrvcMode();
				txCode = FFPConstantsTxJnl.TX_CODE.TX_CODE_P100.getCode();
				srcRefNm = ffpcto01.getSrcRefNm();

				HashMap<String, String> ctoValidRes = cto01MsgValidation(ffpcto01);
				if (ctoValidRes.get("result").equals("0")) {
					resMap.put("result", "0");
					resMap.put("msg", ctoValidRes.get("msg"));
					resMap.put("transactionDate", head.getTransactionDate());
					resMap.put("transactionTime", head.getTransactionTime());
					resMap.put("reqRefNo", head.getRequestRefno());
					resMap.put("srcRefNm", srcRefNm);
					resMap.put("transactionId", transactionId);
					resMap.put("jnlNo", jnlNo);
				} else if (ctoValidRes.get("result").equals("0")) {
					resMap.put("result", "1");
					resMap.put("msg", "");
				}

				List<Object> params = new ArrayList<Object>();
				params.add(jnlNo);
				params.add(ffpcto01.getPymtCatPrps());
				params.add(ffpcto01.getAcctVerf());
				params.add(ffpcto01.getSettlCcy());
				params.add(ffpcto01.getSettlAmt());
				params.add(ffpcto01.getSettlDate());
				params.add(ffpcto01.getInstrCcy());
				params.add(ffpcto01.getInstrAmt());
				params.add(ffpcto01.getChrgrsAgent() != null ? ffpcto01.getChrgrsAgent().getID() : null);
				params.add(ffpcto01.getChrgrsAgent() != null ? ffpcto01.getChrgrsAgent().getBICCODE() : null);
				params.add(ffpcto01.getChrgrsCcy());
				params.add(ffpcto01.getChrgrsAmt());
				params.add(ffpcto01.getDbtrNm());
				params.add(ffpcto01.getDbtrOrgIdAnyBIC());
				params.add(ffpcto01.getDbtrOrgIdOthrId());
				params.add(ffpcto01.getDbtrOrgIdOthrIdSchmeNm());
				params.add(ffpcto01.getDbtrOrgIdOthrIssr());
				params.add(ffpcto01.getDbtrPrvtIdOthrId());
				params.add(ffpcto01.getDbtrPrvtIdOthrIdSchmeNm());
				params.add(ffpcto01.getDbtrPrvtIdOthrIssr());
				params.add(ffpcto01.getDbtrContPhone());
				params.add(ffpcto01.getDbtrContEmailAddr());
				params.add(ffpcto01.getDbtrAcNo());
				params.add(ffpcto01.getDbtrAcTp());
				params.add(this.curClearingCode);// DEBTOR_AGT_ID
				params.add(null);// DEBTOR_AGT_BIC

				params.add(ffpcto01.getCdtrNm());
				params.add(ffpcto01.getCdtrOrgIdAnyBIC());
				params.add(ffpcto01.getCdtrOrgIdOthrId());
				params.add(ffpcto01.getCdtrOrgIdOthrIdSchmeNm());
				params.add(ffpcto01.getCdtrOrgIdOthrIssr());
				params.add(ffpcto01.getCdtrPrvtIdOthrId());
				params.add(ffpcto01.getCdtrPrvtIdOthrIdSchmeNm());
				params.add(ffpcto01.getCdtrPrvtIdOthrIssr());
				params.add(ffpcto01.getCdtrContPhone());
				params.add(ffpcto01.getCdtrContEmailAddr());
				params.add(ffpcto01.getCdtrAcNo());
				params.add(ffpcto01.getCdtrAcTp());
				params.add(ffpcto01.getCdtrAgent().getID());
				params.add(ffpcto01.getCdtrAgent().getBICCODE());
				params.add(ffpcto01.getPytPurpType());// PURPOSE_TYPE
				params.add(FFPConstantsPurposeCode.PURPOSE_TYPE_CODE.equals(ffpcto01.getPytPurpType())
						? ffpcto01.getPytPurp() : null);// PURPOSE_CODE
				params.add(FFPConstantsPurposeCode.PURPOSE_TYPE_OTHER.equals(ffpcto01.getPytPurpType())
						? ffpcto01.getPytPurp() : null);// PURPOSE_OTHER
				params.add(ffpcto01.getRemInfo());// REMIT_INFO
				params.add(servcMode);//

				StringBuffer sql_p100dat = new StringBuffer();
				sql_p100dat.append("INSERT INTO TB_TX_P100DAT( ");
				sql_p100dat.append("JNL_NO,				CATEGORY_PURPOSE,	 ACCT_VERF,	");
				sql_p100dat.append("SETTLEMENT_CUR,		SETTLEMENT_AMT,		SETTLEMENT_DATE, ");
				sql_p100dat.append("INSTRUCTED_CUR,		INSTRUCTED_AMT,		CHG_AGT_ID, ");
				sql_p100dat.append("CHG_AGT_BIC,		CHG_CUR,			CHG_AMT, ");
				sql_p100dat.append("DEBTOR_NAME,		DEBTOR_ORGID_ANYBIC,	DEBTOR_ORGID_OTHRID, ");
				sql_p100dat.append("DEBTOR_ORGID_OTHRID_SCHMENM,	DEBTOR_ORGID_OTHR_ISSR,	DEBTOR_PRVTID_OTHRID, ");
				sql_p100dat.append("DEBTOR_PRVTID_OTHRID_SCHMENM,	DEBTOR_PRVTID_OTHR_ISSR,	DEBTOR_CONT_PHONE, ");
				sql_p100dat.append("DEBTOR_CONT_EMADDR,		DEBTOR_ACCTNO,		DEBTOR_ACCTNO_TYPE, ");
				sql_p100dat.append("DEBTOR_AGT_ID,		DEBTOR_AGT_BIC,		CREDITOR_NAME, ");
				sql_p100dat.append("CREDITOR_ORGID_ANYBIC,	CREDITOR_ORGID_OTHRID,	CREDITOR_ORGID_OTHRID_SCHMENM, ");
				sql_p100dat.append(
						"CREDITOR_ORGID_OTHR_ISSR,	CREDITOR_PRVTID_OTHRID,		CREDITOR_PRVTID_OTHRID_SCHMENM, ");
				sql_p100dat.append("CREDITOR_PRVTID_OTHR_ISSR,	CREDITOR_CONT_PHONE,	CREDITOR_CONT_EMADDR, ");
				sql_p100dat.append("CREDITOR_ACCTNO,	CREDITOR_ACCTNO_TYPE,	CREDITOR_AGT_ID, ");
				sql_p100dat.append("CREDITOR_AGT_BIC,	PURPOSE_TYPE, 		PURPOSE_CODE, ");
				sql_p100dat.append("PURPOSE_OTHER,		REMIT_INFO,			SRVC_MODE ");
				sql_p100dat.append(")VALUES ( ");
				sql_p100dat.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
				sql_p100dat.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
				sql_p100dat.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
				sql_p100dat.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
				sql_p100dat.append("?, ?, ?, ?, ?");
				sql_p100dat.append(")");

				exeMap.put(sql_p100dat.toString(), params);
			} else if (body instanceof FFPDDO01) {
				FFPDDO01 ffpddo01 = (FFPDDO01) body;
				String servcMode = ffpddo01.getSrvcMode();
				txCode = FFPConstantsTxJnl.TX_CODE.TX_CODE_P210.getCode();
				srcRefNm = ffpddo01.getSrcRefNm();

				List<Object> params = new ArrayList<Object>();
				params.add(jnlNo); // JNL_NO
				params.add(ffpddo01.getPymtCatPrps()); // CATEGORY_PURPOSE
				params.add(ffpddo01.getSettlCcy()); // SETTLEMENT_CUR
				params.add(ffpddo01.getSettlAmt()); // SETTLEMENT_AMT
				params.add(ffpddo01.getSettlDate()); // SETTLEMENT_DATE
				params.add(ffpddo01.getInstrCcy()); // INSTRUCTED_CUR
				params.add(ffpddo01.getInstrAmt()); // INSTRUCTED_AMT
				params.add(ffpddo01.getChrgrsAgent() != null ? ffpddo01.getChrgrsAgent().getID() : null);// CHG_AGT_ID
				params.add(ffpddo01.getChrgrsAgent() != null ? ffpddo01.getChrgrsAgent().getBICCODE() : null);// CHG_AGT_BIC
				params.add(ffpddo01.getChrgrsCcy()); // CHG_CUR
				params.add(ffpddo01.getChrgrsAmt()); // CHG_AMT
				params.add(ffpddo01.getDrctDbtTxMndtId());// DRCTDBT_MNDTID
				params.add(ffpddo01.getDbtrNm()); // DEBTOR_NAME
				params.add(ffpddo01.getDbtrOrgIdAnyBIC()); // DEBTOR_ORGID_ANYBIC
				params.add(ffpddo01.getDbtrOrgIdOthrId()); // DEBTOR_ORGID_OTHRID
				params.add(ffpddo01.getDbtrOrgIdOthrIdSchmeNm()); // DEBTOR_ORGID_OTHRID_SCHMENM
				params.add(ffpddo01.getDbtrOrgIdOthrIssr()); // DEBTOR_ORGID_OTHR_ISSR
				params.add(ffpddo01.getDbtrPrvtIdOthrId()); // DEBTOR_PRVTID_OTHRID
				params.add(ffpddo01.getDbtrPrvtIdOthrIdSchmeNm()); // DEBTOR_PRVTID_OTHRID_SCHMENM
				params.add(ffpddo01.getDbtrPrvtIdOthrIssr()); // DEBTOR_PRVTID_OTHR_ISSR
				params.add(ffpddo01.getDbtrContPhone()); // DEBTOR_CONT_PHONE
				params.add(ffpddo01.getDbtrContEmailAddr()); // DEBTOR_CONT_EMADDR
				params.add(ffpddo01.getDbtrAcNo()); // DEBTOR_ACCTNO
				params.add(ffpddo01.getDbtrAcTp()); // DEBTOR_ACCTNO_TYPE
				params.add(ffpddo01.getDbtrAgent().getID());// DEBTOR_AGT_ID
				params.add(ffpddo01.getDbtrAgent().getBICCODE());// DEBTOR_AGT_BIC
				params.add(ffpddo01.getCdtrNm()); // CREDITOR_NAME
				params.add(ffpddo01.getCdtrOrgIdAnyBIC()); // CREDITOR_ORGID_ANYBIC
				params.add(ffpddo01.getCdtrOrgIdOthrId()); // CREDITOR_ORGID_OTHRID
				params.add(ffpddo01.getCdtrOrgIdOthrIdSchmeNm()); // CREDITOR_ORGID_OTHRID_SCHMENM
				params.add(ffpddo01.getCdtrOrgIdOthrIssr()); // CREDITOR_ORGID_OTHR_ISSR
				params.add(ffpddo01.getCdtrPrvtIdOthrId()); // CREDITOR_PRVTID_OTHRID
				params.add(ffpddo01.getCdtrPrvtIdOthrIdSchmeNm()); // CREDITOR_PRVTID_OTHRID_SCHMENM
				params.add(ffpddo01.getCdtrPrvtIdOthrIssr()); // CREDITOR_PRVTID_OTHR_ISSR
				params.add(ffpddo01.getCdtrContPhone()); // CREDITOR_CONT_PHONE
				params.add(ffpddo01.getCdtrContEmailAddr()); // CREDITOR_CONT_EMADDR
				params.add(ffpddo01.getCdtrAcNo()); // CREDITOR_ACCTNO
				params.add(ffpddo01.getCdtrAcTp()); // CREDITOR_ACCTNO_TYPE
				params.add(ffpddo01.getCdtrAgent() != null ? ffpddo01.getCdtrAgent().getID() : null); // CREDITOR_AGT_ID
				params.add(ffpddo01.getCdtrAgent() != null ? ffpddo01.getCdtrAgent().getBICCODE() : null); // CREDITOR_AGT_BIC
				params.add(ffpddo01.getPytPurpType());// PURPOSE_TYPE
				params.add(FFPConstantsPurposeCode.PURPOSE_TYPE_CODE.equals(ffpddo01.getPytPurpType())
						? ffpddo01.getPytPurp() : null);// PURPOSE_CODE
				params.add(FFPConstantsPurposeCode.PURPOSE_TYPE_OTHER.equals(ffpddo01.getPytPurpType())
						? ffpddo01.getPytPurp() : null);// PURPOSE_OTHER
				params.add(ffpddo01.getRemInfo());// REMIT_INFO
				params.add(servcMode);// SRVC_MODE

				StringBuffer sql_p210dat = new StringBuffer();
				sql_p210dat.append("INSERT INTO TB_TX_P210DAT(						");
				sql_p210dat.append("JNL_NO,					CATEGORY_PURPOSE,			SETTLEMENT_CUR, ");
				sql_p210dat.append("SETTLEMENT_AMT,			SETTLEMENT_DATE,			INSTRUCTED_CUR,	");
				sql_p210dat.append("INSTRUCTED_AMT,			CHG_AGT_ID,					CHG_AGT_BIC,	");
				sql_p210dat.append("CHG_CUR,				CHG_AMT,            		DRCTDBT_MNDTID, ");
				sql_p210dat.append("DEBTOR_NAME,			DEBTOR_ORGID_ANYBIC,	DEBTOR_ORGID_OTHRID, ");
				sql_p210dat.append("DEBTOR_ORGID_OTHRID_SCHMENM,	DEBTOR_ORGID_OTHR_ISSR,	DEBTOR_PRVTID_OTHRID, ");
				sql_p210dat.append("DEBTOR_PRVTID_OTHRID_SCHMENM,	DEBTOR_PRVTID_OTHR_ISSR,	DEBTOR_CONT_PHONE, ");
				sql_p210dat.append("DEBTOR_CONT_EMADDR,		DEBTOR_ACCTNO,				DEBTOR_ACCTNO_TYPE, ");
				sql_p210dat.append("DEBTOR_AGT_ID,			DEBTOR_AGT_BIC,				CREDITOR_NAME, ");
				sql_p210dat
						.append("CREDITOR_ORGID_ANYBIC,	CREDITOR_ORGID_OTHRID,		CREDITOR_ORGID_OTHRID_SCHMENM, ");
				sql_p210dat
						.append("CREDITOR_ORGID_OTHR_ISSR,	CREDITOR_PRVTID_OTHRID,	CREDITOR_PRVTID_OTHRID_SCHMENM, ");
				sql_p210dat.append("CREDITOR_PRVTID_OTHR_ISSR,	CREDITOR_CONT_PHONE,	CREDITOR_CONT_EMADDR, ");
				sql_p210dat.append("CREDITOR_ACCTNO,		CREDITOR_ACCTNO_TYPE,		CREDITOR_AGT_ID, ");
				sql_p210dat.append("CREDITOR_AGT_BIC,		PURPOSE_TYPE, 				PURPOSE_CODE, ");
				sql_p210dat.append("PURPOSE_OTHER,			REMIT_INFO,				SRVC_MODE");
				sql_p210dat.append(") VALUES( ");
				sql_p210dat.append("?,?,?,?,?,?,?,?,?,?,");
				sql_p210dat.append("?,?,?,?,?,?,?,?,?,?,");
				sql_p210dat.append("?,?,?,?,?,?,?,?,?,?,");
				sql_p210dat.append("?,?,?,?,?,?,?,?,?,?,");
				sql_p210dat.append("?,?,?,?,?");
				sql_p210dat.append(")");

				exeMap.put(sql_p210dat.toString(), params);
			} else {
				// throw exception
				_logger.warn("Invalid request message from FFP Agent, please check the original message info");
				throw new Exception("Invalid request message from FFP Agent, please check the original message info");
			}

			EntityManager.update(loc_conn, sql_jnl, jnlNo, srcRefNm, txState, txCode, txSrc, txMode, transactionId,
					end2endId, createTs, lastUpdateTs);
			EntityManager.update(loc_conn, sql_action, jnlNo, head.getRequestRefno(), FFPConstants.MSG_DIRECTION_INWARD,
					msgFrom, msgType, msgStatus, null, null, msgCreateData, null, null);

			for (Map.Entry<String, List<Object>> entry : exeMap.entrySet()) {
				EntityManager.update(loc_conn, entry.getKey(), entry.getValue().toArray());
				break;
			}

			loc_conn.commit();
			loc_conn.setAutoCommit(true);

		} catch (Exception e) {
			_logger.error("FFP had saved transaction information error", e);
			if (loc_conn != null)
				loc_conn.rollback();
			throw e;
		} finally {
			try {
				if (loc_conn != null)
					loc_conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resMap;
	}

	// execute xmlMessage
	public String executeMessage(ROOT reqRoot) throws Exception {
		String xml = "NACK";
		HEAD reqHead = reqRoot.getHEAD();
		BODY reqbody = reqRoot.getBODY();
		if (reqbody instanceof FFPCTO01) {
			FFPCTO01 ffpcto01 = (FFPCTO01) reqbody;
			String servcMode = ffpcto01.getSrvcMode();
			switch (servcMode) {
			case "C1":
				com.forms.ffp.adaptor.jaxb.participant.response.ROOT root = new com.forms.ffp.adaptor.jaxb.participant.response.ROOT();
				com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head = new com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD();
				com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01 body = new com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01();
				head.setRequestID(reqHead.getRequestID());
				head.setTransactionDate(reqHead.getTransactionDate());
				head.setTransactionTime(reqHead.getTransactionTime());
				head.setRequestRefno(reqHead.getRequestRefno());
				// head.setAccountingDate("");
				head.setResponseID(reqHead.getResponseID());
				head.setMessageType(reqHead.getMessageType());
				// head.setSystemRefno("");
				// head.setSystemBeginTIme("");
				// head.setSystemEndTIme("");
				head.setResponseRefno(FFPIDUtils.getRefno());
				// head.setResponseBeginTime("");
				// head.setResponseEndTime("");
				head.setResponseStatus("E");
				head.setFinalNode("2");
				// head.setSystemMessageCode("");
				// head.setResponseMessageCode("");
				// head.setResponseMessage("");
				body.setSrcRefNm(ffpcto01.getSrcRefNm());
				body.setFFPTransactionId(transactionId);
				body.setRsltCd("R");
				body.setRejCd("FFPCTO01001");
				body.setRejMsg("servcMode C1 not support in batch mode");
				root.setHEAD(head);
				root.setBODY(body);
				xml = FFPParticipantMessageConverter.packageReponseObject2Xml(root);
				break;
			case "C2":
				HashMap<String, String> resMapC2 = save(reqRoot);
				if (resMapC2.get("result").equals("1")) {
					xml = "ACK";
				} else if (resMapC2.get("result").equals("0")) {
					xml = generatecto01ResRejMessage(resMapC2);
				}
				break;
			case "C3":
				HashMap<String, String> resMapC3 = save(reqRoot);
				if (resMapC3.get("result").equals("1")) {
					xml = "ACK";
				} else if (resMapC3.get("result").equals("0")) {
					xml = generatecto01ResRejMessage(resMapC3);
				}
				break;
			default:
				xml = "NACK";
				break;
			}
		}
		if (reqbody instanceof FFPDDO01) {
			save(reqRoot);
			xml = "ACK";
		}
		return xml;
	}

	private HashMap<String, String> cto01MsgValidation(FFPCTO01 ffpcto01) {
		HashMap<String, String> resMap = new HashMap<>();

		HashMap<String, String> vawtDbtr = MessageValidation.validateAccountWithType(ffpcto01.getDbtrAcNo(),
				ffpcto01.getDbtrAcTp());
		HashMap<String, String> vawtCdtr = MessageValidation.validateAccountWithType(ffpcto01.getCdtrAcNo(),
				ffpcto01.getCdtrAcTp());
		HashMap<String, String> cirDbtr = MessageValidation.custIdRequired(ffpcto01.getDbtrAcTp(),
				ffpcto01.getDbtrOrgIdOthrId(), ffpcto01.getDbtrPrvtIdOthrId());
		HashMap<String, String> cirCdtr = MessageValidation.custIdRequired(ffpcto01.getCdtrAcTp(),
				ffpcto01.getCdtrOrgIdOthrId(), ffpcto01.getCdtrPrvtIdOthrId());
		HashMap<String, String> dmDbtr = MessageValidation.doMix(ffpcto01.getDbtrOrgIdOthrId(),
				ffpcto01.getDbtrPrvtIdOthrId());
		HashMap<String, String> dmCdtr = MessageValidation.doMix(ffpcto01.getCdtrOrgIdOthrId(),
				ffpcto01.getCdtrPrvtIdOthrId());

		if (vawtDbtr.get("result").equals("0")) {

			resMap.put("result", "0");
			resMap.put("msg", vawtDbtr.get("msg"));

		} else if (vawtCdtr.get("result").equals("0")) {

			resMap.put("result", "0");
			resMap.put("msg", vawtCdtr.get("msg"));

		} else if (cirDbtr.get("result").equals("0")) {

			resMap.put("result", "0");
			resMap.put("msg", cirDbtr.get("msg"));

		} else if (cirCdtr.get("result").equals("0")) {

			resMap.put("result", "0");
			resMap.put("msg", cirCdtr.get("msg"));

		} else if (dmDbtr.get("result").equals("0")) {

			resMap.put("result", "0");
			resMap.put("msg", dmDbtr.get("msg"));

		} else if (dmCdtr.get("result").equals("0")) {

			resMap.put("result", "0");
			resMap.put("msg", dmCdtr.get("msg"));

		} else {

			resMap.put("result", "1");
			resMap.put("msg", "");

		}

		return resMap;
	}

	private String generatecto01ResRejMessage(HashMap<String, String> params) throws Exception {

		String rejMsg = "";
		com.forms.ffp.adaptor.jaxb.participant.response.ROOT root = new com.forms.ffp.adaptor.jaxb.participant.response.ROOT();

		com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head = new com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD();

		head.setRequestID(FFPConstants.MSG_CODE_AGENT);
		head.setTransactionDate(params.get("transactionDate"));
		head.setTransactionTime(params.get("transactionTime"));
		head.setRequestRefno(params.get("reqRefNo"));
		head.setResponseID(FFPConstants.MSG_CODE_FFP);
		head.setMessageType("FFPCTO01");
		String resRefNo = FFPIDUtils.getRefno();
		head.setResponseRefno(resRefNo);
		head.setResponseBeginTime(FFPDateUtils.convertDateToString(new Date(), FFPDateUtils.INT_TIME_FORMAT));
		head.setResponseEndTime(FFPDateUtils.convertDateToString(new Date(), FFPDateUtils.INT_TIME_FORMAT));
		head.setResponseStatus("N");
		head.setFinalNode("2");
		head.setResponseMessageCode("E");
		head.setResponseMessage(MessageValidation.getRejMsgByCode(params.get("msg")));

		com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01 body = new com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01();
		body.setSrcRefNm(params.get("srcRefNm"));
		body.setFFPTransactionId(params.get("transactionId"));
		body.setRsltCd("R");
		body.setRejCd(params.get("msg"));
		body.setRejMsg(MessageValidation.getRejMsgByCode(params.get("msg")));

		root.setHEAD(head);
		root.setBODY(body);

		rejMsg = FFPParticipantMessageConverter.packageReponseObject2Xml(root);

		updateRejMsg(jnlNo, params.get("reqRefNo"), resRefNo, params.get("msg"),
				MessageValidation.getRejMsgByCode(params.get("msg")));
		return rejMsg;
	}

	private void updateRejMsg(String jnlNo, String reqRefNo, String resRefNo, String rejCode, String rejMsg) {
		Connection loc_conn = null;
		try {
			loc_conn = ConnectionManager.getInstance().getConnection();
			loc_conn.setAutoCommit(false);

			String update_jnl_sql = "UPDATE tb_tx_jnl SET TX_STAT = ?, LAST_UPDATE_TS = NOW()  WHERE JNL_NO = ? ";
			ArrayList<String> update_jnl_list = new ArrayList<>();
			update_jnl_list.add(FFPConstantsTxJnl.TX_STATUS.TX_STAT_AGENTREJCT.getStatus());
			update_jnl_list.add(jnlNo);
			EntityManager.update(loc_conn, update_jnl_sql, update_jnl_list.toArray());

			String update_jnlaction_sql = "UPDATE tb_tx_jnlaction SET MSG_STATUS = ?, MSG_COMPL_TS = NOW() WHERE JNL_NO = ? AND MSG_ID = ? ";
			ArrayList<String> update_jnlaction_list = new ArrayList<>();
			update_jnlaction_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
			update_jnlaction_list.add(jnlNo);
			update_jnlaction_list.add(reqRefNo);
			EntityManager.update(loc_conn, update_jnlaction_sql, update_jnlaction_list.toArray());

			String insert_jnlaction_sql = "INSERT INTO tb_tx_jnlaction(JNL_NO, MSG_ID, MSG_DIRECTION, MSG_SYSTEMID, MSG_TYPE, MSG_STATUS, MSG_CODE, MSG_RESULT, MSG_CREAT_TS, MSG_PROCE_TS, MSG_COMPL_TS, REF_MSG_ID) VALUES (?,?,?,?,?,?,?,?,NOW(),NOW(),NOW(),?);";
			ArrayList<String> insert_jnlaction_list = new ArrayList<>();
			insert_jnlaction_list.add(jnlNo);
			insert_jnlaction_list.add(resRefNo);
			insert_jnlaction_list.add("O");
			insert_jnlaction_list.add(FFPConstants.MSG_CODE_AGENT);
			insert_jnlaction_list.add("FFPCTO01");
			insert_jnlaction_list.add(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
			insert_jnlaction_list.add(rejCode);
			insert_jnlaction_list.add(rejMsg);
			insert_jnlaction_list.add(reqRefNo);
			EntityManager.update(loc_conn, insert_jnlaction_sql, insert_jnlaction_list.toArray());

			loc_conn.commit();
			loc_conn.setAutoCommit(true);
		} catch (BatchFrameworkException e) {
			_logger.error(e.getMessage());
		} catch (SQLException e) {
			_logger.error(e.getMessage());
			if (loc_conn != null) {
				try {
					loc_conn.rollback();
				} catch (SQLException e1) {
					_logger.error(e1.getMessage());
				}
			}
		} catch (Exception e) {
			_logger.error(e.getMessage());
			if (loc_conn != null) {
				try {
					loc_conn.rollback();
				} catch (SQLException e1) {
					_logger.error(e1.getMessage());
				}
			}
		} finally {
			if (loc_conn != null) {
				try {
					loc_conn.close();
				} catch (SQLException e) {
					_logger.error(e.getMessage());
				}
			}
		}
	}
}