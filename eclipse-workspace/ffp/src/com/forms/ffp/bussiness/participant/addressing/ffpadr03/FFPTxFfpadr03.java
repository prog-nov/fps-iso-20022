package com.forms.ffp.bussiness.participant.addressing.ffpadr03;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr03.FFPADR03;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.bussiness.participant.addressing.ffpadr04.FFPTxFfpadr04;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.FFPBaseResp;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;
import com.forms.ffp.persistents.service.addressing.FFPDaoService_A110;

@Component("FFPAGENT.FFPADR03")
@Scope("prototype")
public class FFPTxFfpadr03 extends FFPTxBase {
	
	private Logger logger = LoggerFactory.getLogger(FFPTxFfpadr03.class);

	@Resource(name = "FFPDaoService_A110")
	private FFPDaoService_A110 adrDao;

	@Override
	public void perform() throws Exception {
		if ("FFPAGENT.FFPADR03".equals(serviceName)) {
			FFPVOFfpadr03 adr03 = (FFPVOFfpadr03) txVo;
			FFPJbA110 a110 = adr03.getA110();
			FFPMsgAdr03_fpsadrs006 adr006 = new FFPMsgAdr03_fpsadrs006(a110);

			String jnlNo = a110.getTxJnl().getJnlNo();
			Date locdate = new Date();

			/**
			 * FFPTxJnlAction FFP TO ICL
			 */

			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(jnlNo, adr006.getMsgID(),
					FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.RELATION_SYSTEM_HKICL, adr006.getMsgTypeName(),
					FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), locdate, locdate, locdate,
					adr03.getMsgId());

			a110.getJnlActionList().add(jnlAction);
			try {
				/**
				 * SEND MSG TO ICL
				 * 
				 */
				FFPBaseResp respAdr001 = FFPAdaptorMgr.getInstance().execute(adr006);

				FFPSendMessageResp resp = (FFPSendMessageResp) respAdr001;
				jnlAction.setMsgStatus(resp.getMessageStatus());
				if (FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(resp.getMessageStatus())) {
					a110.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_PDNG.getStatus());
				} else {
					a110.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
					jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
				}

			} catch (Exception e) {
				logger.warn("FFPTxFfpadr03",e);
			} finally {
				adrDao.updateJnlStat(a110);
			}

			FFPMsgFpsadrs007_Adr03 msg = null;

			String flag = null;
			long startTime = System.currentTimeMillis();
			long endTime = startTime;
			FFPTxJnl txjnl = null;
			while ((endTime - startTime) <= 60 * 1000) {
				txjnl = adrDao.inqueryTXJNL(jnlNo);
				flag = txjnl.getTxStat();
				if (FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus().equals(flag)) {
					List<FFPJbA110dtl> a110dtlList = adrDao.inqueryAllDtlFrA110Dtl(jnlNo);
					a110.setAdrList(a110dtlList);
					a110.setTxJnl(txjnl);
					msg = new FFPMsgFpsadrs007_Adr03(a110);
					break;
				}else if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus().equals(flag)){
					a110.setTxJnl(txjnl);
					msg = new FFPMsgFpsadrs007_Adr03(a110);
					break;
				}
				Thread.sleep(3000);
				endTime = System.currentTimeMillis();
			}

			/**
			 * TIME OUT
			 */
			if (null == msg) {
				a110.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
				msg = new FFPMsgFpsadrs007_Adr03(a110);
			}

			try {
				/**
				 * DISPOSE MSG
				 */
				FFPBaseResp response = FFPAdaptorMgr.getInstance().execute(msg,
						adr03.getParticipantWrapper().getSocket());

				/**
				 * FFP TO AGENT
				 */
				FFPTxJnlAction jnlAction1 = FFPJnlUtils.getInstance().newJnlAction(jnlNo, msg.getResRefNo(),
						FFPConstants.MSG_DIRECTION_OUTWARD, msg.getResponseID(), msg.getMsgType(),
						FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), locdate, locdate, locdate,
						adr006.getMsgID());

				a110.getJnlActionList().add(jnlAction1);

				if (response != null) {
					FFPSendTcpMessageResp msgRespFromAgent = (FFPSendTcpMessageResp) response;
					if (msgRespFromAgent.isTimeOut()) {// TIME OUT
						jnlAction1.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
						a110.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
					} else {

						// a110.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
					}
				} else {
				}
			} catch (Exception e) {
				e.printStackTrace();
				a110.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_ERROR.getStatus());
			} finally {
				adrDao.updateJnlStat(a110);
			}

		}

	}

	@Override
	public boolean validate() throws Exception {
		FFPVOFfpadr03 adr03 = (FFPVOFfpadr03)txVo;
		if(null == adr03.getA110()){
			return false;
		}
		return true;
	}

	public void parseParticipantData(FFPParticipantMessageWrapper wrapper) throws Exception {
		if ("FFPAGENT.FFPADR03".equals(serviceName)) {

			ROOT reqRoot = wrapper.getRequestRoot();
			FFPADR03 adr03 = (FFPADR03) reqRoot.getBODY();
			FFPVOFfpadr03 vo = new FFPVOFfpadr03();
			vo.setParticipantWrapper(wrapper);
			txVo = vo;
			FFPJbA110 a110 = new FFPJbA110();
			vo.setMsgId(reqRoot.getHEAD().getRequestRefno());
			a110.setSrcRefNm(adr03.getSrcRefNm());
			a110.setAdrReqId(FFPIDUtils.getAdrReqId());
			a110.setProxyId(adr03.getProxyId());
			a110.setProxyIdTp(adr03.getProxyIdTp());
			vo.setA110(a110);
			insertOrgnlMsg(reqRoot.getHEAD(), adr03.getSrcRefNm(), a110);
		}
	}

	/**
	 * RECORD FFPADR03 request MESSAGE IN DB
	 * 
	 * @param head
	 * @param srcRefNm
	 * @param a110
	 * @return
	 * @throws Exception
	 */
	public int insertOrgnlMsg(HEAD head, String srcRefNm, FFPJbA110 a110) throws Exception {
		String jnlNo = FFPIDUtils.getJnlNo();
		Date locdate = new Date();

		/**
		 * txJnl
		 */
		FFPTxJnl txjnl = new FFPTxJnl();
		txjnl.setTxStat(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus());
		txjnl.setCreateTs(locdate);
		txjnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_A110.getCode());
		txjnl.setJnlNo(jnlNo);
		txjnl.setTxSrc(FFPConstants.TX_SOURCE_AGENT);
		txjnl.setSrcRefNm(srcRefNm);
		txjnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);

		/**
		 * txJnlAction AGENT TO FFP
		 */
		List<FFPTxJnlAction> listAc = a110.getJnlActionList();
		listAc.add(FFPJnlUtils.getInstance().newJnlAction(jnlNo, head.getRequestRefno(),
				FFPConstants.MSG_DIRECTION_INWARD, head.getRequestID(), head.getMessageType(),
				FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), locdate, locdate, locdate, null));

		a110.setTxJnl(txjnl);
		int result = adrDao.sInsert(a110);
		return result;
	}

}
