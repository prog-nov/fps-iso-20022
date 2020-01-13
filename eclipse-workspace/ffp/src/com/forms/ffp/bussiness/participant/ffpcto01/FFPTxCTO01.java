package com.forms.ffp.bussiness.participant.ffpcto01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcto01.FFPCTO01;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.config.runtime.FFPRuntimeConstants;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsPurposeCode;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.payment.credittransfer.FFPIDaoService_P100;

@Component("FFPAGENT.FFPCTO01")
@Scope("prototype")
public class FFPTxCTO01 extends FFPTxBase
{
	private Logger logger = LoggerFactory.getLogger(FFPTxCTO01.class);
	
	@Resource(name = "FFPDaoService_P100")
	private FFPIDaoService_P100 daoService;
	
	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl jnlDao;
	
	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction txJnlActionService;
	
	public void perform() throws Exception
	{
		if ("FFPAGENT.FFPCTO01".equals(this.serviceName))
		{
			FFPVO_CTO01 loc_jb = (FFPVO_CTO01)txVo;
			FFPJbP100 loc_p100 = loc_jb.getP100Jb();
			List<FFPTxJnlAction> jnlActionList = loc_p100.getJnlActionList();
			if(FFPConstantsServiceCode.FFPAGENT_SERVICECODE_C1.equals(loc_p100.getSrvcMode())
					|| FFPConstantsServiceCode.FFPAGENT_SERVICECODE_C2.equals(loc_p100.getSrvcMode()))
			{
				FFPMsgCTO01_Pacs008 pacs008 = new FFPMsgCTO01_Pacs008(loc_p100);
				
				FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(loc_p100.getTxJnl().getJnlNo(),
						pacs008.getMsgID(), FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.RELATION_SYSTEM_HKICL, pacs008.getMsgTypeName(),
						FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), new Date(), null, null, null);
				jnlActionList.add(jnlAction);
				
				FFPSendMessageResp resp = FFPAdaptorMgr.getInstance().execute(pacs008);
				jnlAction.setMsgStatus(resp.getMessageStatus());
				jnlAction.setMsgProceTs(new Date());
				jnlAction.setMsgComplTs(new Date());
				if(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(resp.getMessageStatus()))
				{
					loc_p100.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus());
					loc_p100.getTxJnl().setLastUpdateTs(new Date());
					this.daoService.updateJnlStat(loc_p100);
				}
				else
				{
					loc_p100.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_SRJCT.getStatus());
					loc_p100.getTxJnl().setTxRejCode("FFPP10002");
					loc_p100.getTxJnl().setTxRejReason("CALL FPS ERROR!");
					loc_p100.getTxJnl().setLastUpdateTs(new Date());
					this.daoService.updateJnlStat(loc_p100);
					
					FFPMsgCTO01_CTO01 msg = new FFPMsgCTO01_CTO01(loc_p100, "FFPP10002", "CALL FPS ERROR!");
					FFPTxJnlAction jnlAction2 = FFPJnlUtils.getInstance().newJnlAction(loc_p100.getTxJnl().getJnlNo(),
							msg.getReqRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD, msg.getResponseID(),
							msg.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), msg.getCreateDate(),
							null, null, msg.getReqRefNo());
					loc_p100.getJnlActionList().add(jnlAction2);
					try
					{
						FFPAdaptorMgr.getInstance().execute(msg);
						jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
						daoService.updateJnlStat(loc_p100);
					}
					catch(Exception ip_e)
					{
						jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
						daoService.updateJnlStat(loc_p100);
						throw ip_e;
					}
				}
			}
			else
			{
				// Nothing to do
			}
		}
	}

	public boolean validate() throws Exception
	{
		try
		{
			String rejCode = null;
			String rejReason = null;
			
			FFPVO_CTO01 loc_vo = (FFPVO_CTO01)txVo;
			String loc_srcRefNm = loc_vo.getP100Jb().getTxJnl().getSrcRefNm();
			List<FFPTxJnl> jnlList = jnlDao.inquiryBySrcRefNm(loc_srcRefNm);
			if(jnlList.size() > 1)
			{
				rejCode = "FFPP10001";
				rejReason = "Duplicated Source reference Number";
				validateFail(rejCode, rejReason);
				return false;
			}
		}
		catch(Exception ip_e)
		{
			logger.warn("FFPTxCTO01", ip_e);
			throw ip_e;
		}
		
		return true;
	}
	
	private void validateFail(String rejCode, String rejReason) throws Exception
	{
		FFPVO_CTO01 loc_vo = (FFPVO_CTO01)txVo;
		FFPMsgCTO01_CTO01 msg = new FFPMsgCTO01_CTO01(loc_vo.getP100Jb(), rejCode, rejReason);
		loc_vo.getP100Jb().getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_SRJCT.getStatus());
		loc_vo.getP100Jb().getTxJnl().setTxRejCode(rejCode);
		loc_vo.getP100Jb().getTxJnl().setTxRejReason(rejReason);
		FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(loc_vo.getP100Jb().getTxJnl().getJnlNo(),
				msg.getResRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD, msg.getRequestID(),
				msg.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), msg.getCreateDate(),
				null, null, loc_vo.getP100Jb().getJnlActionList().get(0).getMsgId());
		loc_vo.getP100Jb().getJnlActionList().add(jnlAction);
		try
		{
			FFPAdaptorMgr.getInstance().execute(msg);
			jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
			jnlAction.setMsgProceTs(new Date());
			jnlAction.setMsgComplTs(new Date());
			daoService.updateJnlStat(loc_vo.getP100Jb());
		}
		catch(Exception ip_e)
		{
			jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
			jnlAction.setMsgProceTs(new Date());
			jnlAction.setMsgComplTs(new Date());
			daoService.updateJnlStat(loc_vo.getP100Jb());
			throw ip_e;
		}
	}

	public void parseParticipantData(FFPParticipantMessageWrapper wrapper) throws Exception
	{
		ROOT root = wrapper.getRequestRoot();
		
		if ("FFPAGENT.FFPCTO01".equals(this.serviceName))
		{
			txVo = new FFPVO_CTO01();
			txVo.setParticipantWrapper(wrapper);
			HEAD head = root.getHEAD();
			FFPCTO01 msg = (FFPCTO01) root.getBODY();
			
			FFPJbP100 loc_jb = new FFPJbP100();
			loc_jb.setJnlNo(FFPIDUtils.getJnlNo());
			loc_jb.setSrvcMode(msg.getSrvcMode());
			loc_jb.setPymtCatPrps(msg.getPymtCatPrps());
			loc_jb.setAccountVerification(msg.getAcctVerf());
			loc_jb.setSettlementDate(FFPDateUtils.convertStringToDate(msg.getSettlDate(), FFPDateUtils.INT_DATE_FORMAT));
			loc_jb.setSettlementCurrency(msg.getSettlCcy());
			loc_jb.setSettlementAmount(msg.getSettlAmt());
			loc_jb.setInstructedCurrency(msg.getInstrCcy());
			loc_jb.setInstructedAmount(msg.getInstrAmt());
			
			if(msg.getChrgrsAgent() != null)
			{
				loc_jb.setChargersAgentId(msg.getChrgrsAgent().getID());
				loc_jb.setChargersAgentBic(msg.getChrgrsAgent().getBICCODE());
				loc_jb.setChargersCurrency(msg.getChrgrsCcy());
				loc_jb.setChargersAmount(msg.getChrgrsAmt());
			}
			
			loc_jb.setDebtorAgentBic(null);
			loc_jb.setDebtorAgentId(FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID);
			
			loc_jb.setDebtorName(msg.getDbtrNm());
			loc_jb.setDebtorOrgIdAnyBIC(msg.getDbtrOrgIdAnyBIC());
			loc_jb.setDebtorOrgIdOthrId(msg.getDbtrOrgIdOthrId());
			loc_jb.setDebtorOrgIdOthrIdSchmeNm(msg.getDbtrOrgIdOthrIdSchmeNm());
			loc_jb.setDebtorOrgIdOthrIssr(msg.getDbtrOrgIdOthrIssr());
			loc_jb.setDebtorPrvtIdOthrId(msg.getDbtrPrvtIdOthrId());
			loc_jb.setDebtorPrvtIdOthrIdSchmeNm(msg.getDbtrPrvtIdOthrIdSchmeNm());
			loc_jb.setDebtorPrvtIdOthrIssr(msg.getDbtrPrvtIdOthrIssr());
			loc_jb.setDebtorContPhone(msg.getDbtrContPhone());
			loc_jb.setDebtorContEmailAddr(msg.getDbtrContEmailAddr());
			
			loc_jb.setDebtorAccountNumber(msg.getDbtrAcNo());
			loc_jb.setDebtorAccountNumberType(msg.getDbtrAcTp());
			
			loc_jb.setCreditorName(msg.getCdtrNm());
			loc_jb.setCreditorOrgIdAnyBIC(msg.getCdtrOrgIdAnyBIC());
			loc_jb.setCreditorOrgIdOthrId(msg.getCdtrOrgIdOthrId());
			loc_jb.setCreditorOrgIdOthrIdSchmeNm(msg.getCdtrOrgIdOthrIdSchmeNm());
			loc_jb.setCreditorOrgIdOthrIssr(msg.getCdtrOrgIdOthrIssr());
			loc_jb.setCreditorPrvtIdOthrId(msg.getCdtrPrvtIdOthrId());
			loc_jb.setCreditorPrvtIdOthrIdSchmeNm(msg.getCdtrPrvtIdOthrIdSchmeNm());
			loc_jb.setCreditorPrvtIdOthrIssr(msg.getCdtrPrvtIdOthrIssr());
			loc_jb.setCdtrContPhone(msg.getCdtrContPhone());
			loc_jb.setCdtrContEmailAddr(msg.getCdtrContEmailAddr());
			
			loc_jb.setCreditorAccountNumber(msg.getCdtrAcNo());
			loc_jb.setCreditorAccountNumberType(msg.getCdtrAcTp());
			
			loc_jb.setCreditorAgentId(msg.getCdtrAgent().getID());
			loc_jb.setCreditorAgentBic(msg.getCdtrAgent().getBICCODE());
			
			loc_jb.setRemittanceInformation(msg.getRemInfo());
			
			loc_jb.setPaymentPurposeType(msg.getPytPurpType());
			if (FFPConstantsPurposeCode.PURPOSE_TYPE_CODE.equals(msg.getPytPurpType()))
			{
				loc_jb.setPaymentPurposeCd(msg.getPytPurp());
			} else if (FFPConstantsPurposeCode.PURPOSE_TYPE_OTHER.equals(msg.getPytPurpType()))
			{
				loc_jb.setPaymentPurposeProprietary(msg.getPytPurp());
			}
			
			FFPTxJnl txJnl = new FFPTxJnl();
			
			txJnl.setJnlNo(loc_jb.getJnlNo());
			txJnl.setSrcRefNm(msg.getSrcRefNm());
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
			txJnl.setTxSrc(FFPConstants.TX_SOURCE_FFPAGENT);
			txJnl.setTransactionId(FFPIDUtils.getTransactionId());
			txJnl.setEndToEndId(FFPIDUtils.getEndToEndId());
			txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_P100.getCode());
			txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
			Date loc_date = new Date();
			txJnl.setCreateTs(loc_date);
			txJnl.setLastUpdateTs(loc_date);
			loc_jb.setTxJnl(txJnl);
			
			List<FFPTxJnlAction> jnlActionList = new ArrayList<>();
			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(txJnl.getJnlNo(),
					head.getRequestRefno(), FFPConstants.MSG_DIRECTION_INWARD, head.getRequestID(), head.getMessageType(),
					FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(),
					FFPDateUtils.convertStringToDate(head.getTransactionDate() + head.getTransactionTime(), FFPDateUtils.INT_TIMESTAMP_FORMAT),
					loc_date, loc_date, null);
			
			jnlActionList.add(jnlAction);
			loc_jb.setJnlActionList(jnlActionList);
			
			FFPVO_CTO01 loc_vo = (FFPVO_CTO01)txVo;
			loc_vo.setP100Jb(loc_jb);
			// Save 
			this.daoService.sInsert(loc_jb);
			
			try
			{
				FFPMsgCTO01_Reply reply = new FFPMsgCTO01_Reply(FFPConstants.SEND_TYPE_ACK);
				FFPAdaptorMgr.getInstance().execute(reply, wrapper.getSocket());
				jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
				txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_PDNG.getStatus());
				daoService.updateJnlStat(loc_jb);
			}
			catch(Exception ip_e)
			{
				jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
				txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_SRJCT.getStatus());
				daoService.updateJnlStat(loc_jb);
				throw ip_e;
			}
		}
	}

}
