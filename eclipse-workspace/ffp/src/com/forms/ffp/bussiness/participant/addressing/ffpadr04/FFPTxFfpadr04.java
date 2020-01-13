package com.forms.ffp.bussiness.participant.addressing.ffpadr04;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr04.ADDRESSINGSCHEMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr04.FFPADR04;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.bussiness.iclfps.pacs004.FFPTxpacs004;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPConstantsTxJnl.TX_STATUS;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.FFPBaseResp;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnlAction;
import com.forms.ffp.persistents.service.addressing.FFPDaoService_A120;
import com.forms.ffp.persistents.service.addressing.FFPDaoService_A120dtl;

@Component("FFPAGENT.FFPADR04")
@Scope("prototype")
public class FFPTxFfpadr04 extends FFPTxBase {
	
	private Logger logger = LoggerFactory.getLogger(FFPTxFfpadr04.class);

	@Resource(name = "FFPDaoService_A120")
	private FFPDaoService_A120 a120service;

	@Resource(name = "FFPIDao_TxJnlAction")
	private FFPIDao_TxJnlAction actionDao;

	@Resource(name = "FFPDaoService_A120dtl")
	private FFPDaoService_A120dtl a120dtlservice;

	@Override
	public void perform() throws Exception {
		if ("FFPAGENT.FFPADR04".equals(serviceName)) {
			FFPVOFfpadr04 adr04 = (FFPVOFfpadr04) txVo;
			FFPMsgAdr04_fpsadrs008 adrs008 = new FFPMsgAdr04_fpsadrs008(adr04);
			Date locdate = new Date();

			/**
			 * FFPTxJnlAction FFP TO ICL
			 */
			FFPJbBase base = new FFPJbBase();
			String jnlNo = adr04.getList().get(0).getJnlNo();
			FFPTxJnl txjnl = a120service.iqueryTXJNL(jnlNo);
			base.setTxJnl(txjnl);

			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(jnlNo, adrs008.getMsgID(),
					FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.RELATION_SYSTEM_HKICL, adrs008.getMsgTypeName(),
					FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), locdate, locdate, locdate,
					adr04.getMsgID());
			

			try {
				// send msg to ICL
				FFPBaseResp respAdr001 = FFPAdaptorMgr.getInstance().execute(adrs008);
				
				base.getJnlActionList().add(jnlAction);
				FFPSendMessageResp resp = (FFPSendMessageResp) respAdr001;
				jnlAction.setMsgStatus(resp.getMessageStatus());
				if (FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(resp.getMessageStatus())) {
					txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_PDNG.getStatus());
				} else {
					jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
					txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
				}

			} catch (Exception e) {
				// TODO
			} finally {
				a120service.updateJnlStat(base);
			}

			FFPMsgFpsadrs009_Adr04 msg = null;

			String flag;
			long startTime = System.currentTimeMillis();
			long endTime = startTime;
			
			while ((endTime - startTime) <= 60 * 1000) {
				flag = a120service.iqueryTXJNL(jnlNo).getTxStat();
				if (FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus().equals(flag)) {
					List<FFPJbA120dtl> a120dtlList = a120dtlservice.inqueryAllDtlFrA120Dtl(jnlNo);
					msg = new FFPMsgFpsadrs009_Adr04(a120dtlList, null);
					break;
				}
				Thread.sleep(3000);
				endTime = System.currentTimeMillis();
			}
			FFPTxJnlAction jnlAction1 = null;

			/**
			 * TIME OUT
			 */
			if (null == msg) {
				msg = new FFPMsgFpsadrs009_Adr04(null, adr04.getList());
				txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
			}

			/**
			 * update txjnl status
			 */
			txjnl = a120service.iqueryTXJNL(jnlNo);
			txjnl.setLastUpdateTs(locdate);
			base.setTxJnl(txjnl);

			try {
				FFPBaseResp response = FFPAdaptorMgr.getInstance().execute(msg,
						adr04.getParticipantWrapper().getSocket());
				/**
				 * FFP TO AGENT
				 */
				jnlAction1 = FFPJnlUtils.getInstance().newJnlAction(jnlNo, msg.getResRefNo(),
						FFPConstants.MSG_DIRECTION_OUTWARD, msg.getResponseID(), msg.getMsgType(),
						FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), locdate, locdate, locdate,
						adr04.getMsgID());

				base.getJnlActionList().add(jnlAction1);

				if (response != null) {
					FFPSendTcpMessageResp msgRespFromAgent = (FFPSendTcpMessageResp) response;
					if (msgRespFromAgent.isTimeOut()) {
						jnlAction1.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
						txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
					} else {// TIME OUT
							// TXJNL STATUS TIEM OUT??

					}
				}

			} catch (Exception e) {
				logger.warn("FFPTxFfpadr04", e);
			} finally {
				a120service.updateJnlStat(base);
			}
		}
	}

	@Override
	public boolean validate() throws Exception {
		// TODO Auto-generated method stub
		return true;

	}

	public void parseParticipantData(FFPParticipantMessageWrapper wrapper) throws Exception {
		if ("FFPAGENT.FFPADR04".equals(serviceName)) {
			ROOT reqRoot = wrapper.getRequestRoot();
			FFPVOFfpadr04 vo = new FFPVOFfpadr04();
			txVo = vo;
			vo.setParticipantWrapper(wrapper);
			FFPADR04 adr04 = (FFPADR04) reqRoot.getBODY();
			List<FFPJbA120> list = new ArrayList<>();
			vo.setMsgID(reqRoot.getHEAD().getRequestRefno());
			List<ADDRESSINGSCHEMETYPE> adrLi = adr04.getAdrSchme();
			FFPJbA120 a120 = null;
			String jnlNo = FFPIDUtils.getJnlNo();
			String srcRefNm = adr04.getSrcRefNm();
			String adrReqId = FFPIDUtils.getAdrReqId();
			for (ADDRESSINGSCHEMETYPE c : adrLi) {
				a120 = new FFPJbA120();
				a120.setJnlNo(jnlNo);
				a120.setSrcRefNm(srcRefNm);
				a120.setAdrReqId(adrReqId);
				a120.setProxyId(c.getProxyId());
				a120.setProxyIdTp(c.getProxyIdTp());
				a120.setPurpCd(c.getPurpCd());
				a120.setMmbId(c.getMmbId());
				list.add(a120);
			}
			vo.setList(list);
			insertADR04MSGTODB(reqRoot.getHEAD(), vo, jnlNo);
		}
	}

	/**
	 * insert adr004 msg into db
	 * 
	 * @param head
	 * @param a120
	 * @param jnlNo
	 * @return
	 * @throws Exception
	 */
	private int insertADR04MSGTODB(HEAD head, FFPVOFfpadr04 vo, String jnlNo) throws Exception {
		Date locdate = new Date();
		FFPJbA120 a120 = vo.getList().get(0);
		/**
		 * txJnl
		 */
		FFPTxJnl txjnl = new FFPTxJnl();
		txjnl.setTxStat(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus());
		txjnl.setCreateTs(locdate);
		txjnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_A120.getCode());
		txjnl.setJnlNo(jnlNo);
		txjnl.setTxSrc(FFPConstants.TX_SOURCE_AGENT);
		txjnl.setSrcRefNm(a120.getSrcRefNm());
		txjnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);

		/**
		 * txJnlAction AGENT TO FFP
		 */
		FFPJbBase base = new FFPJbBase();
		List<FFPTxJnlAction> listAc = base.getJnlActionList();
		listAc.add(FFPJnlUtils.getInstance().newJnlAction(jnlNo, head.getRequestRefno(),
				FFPConstants.MSG_DIRECTION_INWARD, head.getRequestID(), head.getMessageType(),
				FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), locdate, locdate, locdate, null));

		base.setTxJnl(txjnl);
		a120service.updateJnlStat(base);
		int re = a120service.insertA120Msg(vo.getList());
		return re;
	}

}
