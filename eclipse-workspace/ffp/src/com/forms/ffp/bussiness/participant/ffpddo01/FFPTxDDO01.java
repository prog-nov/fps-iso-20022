package com.forms.ffp.bussiness.participant.ffpddo01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpddo01.FFPDDO01;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsPurposeCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.payment.directdebit.FFPIDaoService_P210;

@Component("FFPAGENT.FFPDDO01")
@Scope("prototype")
public class FFPTxDDO01 extends FFPTxBase
{

	private Logger logger = LoggerFactory.getLogger(FFPTxDDO01.class);

	@Resource(name = "FFPDaoService_P210")
	private FFPIDaoService_P210 daoService;

	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl jnlDao;

	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;

	@Override
	public void perform() throws Exception
	{

		if ("FFPAGENT.FFPDDO01".equals(this.serviceName))
		{
			FFPVO_DDO01 loc_vo = (FFPVO_DDO01) txVo;
			FFPJbP210 loc_p210 = loc_vo.getP210Jb();
			FFPMsgDDO01_Pacs003 pacs003 = new FFPMsgDDO01_Pacs003(loc_p210);
			Date loc_date = new Date();
			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(loc_p210.getTxJnl().getJnlNo(), pacs003.getMsgID(), FFPConstants.MSG_DIRECTION_OUTWARD,
					FFPConstants.RELATION_SYSTEM_HKICL, pacs003.getMsgTypeName(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), loc_date, loc_date, null, null);

			FFPSendMessageResp resp = (FFPSendMessageResp) FFPAdaptorMgr.getInstance().execute(pacs003);
			jnlAction.setMsgStatus(resp.getMessageStatus());
			jnlAction.setMsgComplTs(new Date());
			loc_p210.getJnlActionList().add(jnlAction);

			if (FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(resp.getMessageStatus()))
			{
				loc_p210.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus());
				loc_p210.getTxJnl().setLastUpdateTs(jnlAction.getMsgComplTs());
				txJnlService.updateJnlStat(loc_p210);
			} else if (FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus().equals(resp.getMessageStatus()))
			{
				loc_p210.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus());
				loc_p210.getTxJnl().setTxRejCode("FFPP21002");
				loc_p210.getTxJnl().setTxRejReason("CALL FPS ERROR!");
				loc_p210.getTxJnl().setLastUpdateTs(jnlAction.getMsgComplTs());
				txJnlService.updateJnlStat(loc_p210);
			}

			long curTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - curTime) / 1000 < 20)
			{
				Thread.sleep(1000);
				Object loc_obj = txJnlService.inquiryByJnlNo(jnlAction.getJnlNo());
				FFPJbP210 loc_jb = (FFPJbP210) loc_obj;

				if (FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus().equals(loc_jb.getTxJnl().getTxStat())
						|| FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus().equals(loc_jb.getTxJnl().getTxStat()))
				{
					FFPMsgDDO01_DDO01 msg = new FFPMsgDDO01_DDO01(loc_jb, loc_jb.getTxJnl().getTxRejCode(), loc_jb.getTxJnl().getTxRejReason());
					FFPTxJnlAction jnlAction2 = FFPJnlUtils.getInstance().newJnlAction(loc_jb.getTxJnl().getJnlNo(), msg.getResRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD, msg.getRequestID(),
							msg.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), msg.getCreateDate(), new Date(), null, loc_jb.getJnlActionList().get(0).getMsgId());
					loc_jb.getJnlActionList().add(jnlAction2);
					try
					{
						FFPAdaptorMgr.getInstance().execute(msg, loc_vo.getParticipantWrapper().getSocket());
						jnlAction2.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
						jnlAction2.setMsgComplTs(new Date());
					} catch (Exception ip_e)
					{
						jnlAction2.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
						jnlAction2.setMsgComplTs(new Date());
						logger.warn("FFPTxDDO01", ip_e);
					}
					jnlAction.setMsgComplTs(new Date());
					txJnlService.updateJnlStat(loc_jb);
					break;
				}
			}
		}

	}

	@Override
	public boolean validate() throws Exception
	{
		try
		{
			String rejCode = null;
			String rejReason = null;
			
			FFPVO_DDO01 loc_vo = (FFPVO_DDO01) txVo;
			String loc_srcRefNm = loc_vo.getP210Jb().getTxJnl().getSrcRefNm();
			List<FFPTxJnl> jnlList = jnlDao.inquiryBySrcRefNm(loc_srcRefNm);
			if(jnlList.size() > 1)
			{
				rejCode = "FFPP21001";
				rejReason = "Duplicated Source reference Number";
				validateFail(rejCode, rejReason);
				return false;
			}
		} catch (Exception ip_e)
		{
			logger.warn("FFPTxCTO01", ip_e);
			throw ip_e;
		}
		
		return true;
	}

	private void validateFail(String rejCode, String rejReason) throws Exception
	{
		FFPVO_DDO01 loc_vo = (FFPVO_DDO01) txVo;
		FFPMsgDDO01_DDO01 msg = new FFPMsgDDO01_DDO01(loc_vo.getP210Jb(), rejCode, rejReason);
		loc_vo.getP210Jb().getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_SRJCT.getStatus());
		loc_vo.getP210Jb().getTxJnl().setTxRejCode(rejCode);
		loc_vo.getP210Jb().getTxJnl().setTxRejReason(rejReason);
		FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(loc_vo.getP210Jb().getTxJnl().getJnlNo(),
				msg.getResRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD, msg.getRequestID(),
				msg.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), msg.getCreateDate(),
				null, null, loc_vo.getP210Jb().getJnlActionList().get(0).getMsgId());
		loc_vo.getP210Jb().getJnlActionList().add(jnlAction);
		try
		{
			FFPAdaptorMgr.getInstance().execute(msg, loc_vo.getParticipantWrapper().getSocket());
			jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
			jnlAction.setMsgProceTs(new Date());
			jnlAction.setMsgComplTs(new Date());
			daoService.updateJnlStat(loc_vo.getP210Jb());
		}
		catch(Exception ip_e)
		{
			jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
			jnlAction.setMsgProceTs(new Date());
			jnlAction.setMsgComplTs(new Date());
			daoService.updateJnlStat(loc_vo.getP210Jb());
			throw ip_e;
		}
	}

	public void parseParticipantData(FFPParticipantMessageWrapper wrapper) throws Exception
	{
		ROOT root = wrapper.getRequestRoot();
		
		if ("FFPAGENT.FFPDDO01".equals(this.serviceName))
		{
			txVo = new FFPVO_DDO01();
			txVo.setParticipantWrapper(wrapper);
			HEAD head = root.getHEAD();
			FFPDDO01 msg = (FFPDDO01) root.getBODY();
			
			FFPJbP210 loc_jb = new FFPJbP210();
			loc_jb.setJnlNo(FFPIDUtils.getJnlNo());
			loc_jb.setSrcRefNm(msg.getSrcRefNm());
			loc_jb.setSrvcMode(msg.getSrvcMode());
			loc_jb.setPymtCatPrps(msg.getPymtCatPrps());
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
			
			loc_jb.setDrctDbtTxMndtId(msg.getDrctDbtTxMndtId());
			
			loc_jb.setCreditorName(msg.getCdtrNm());
			loc_jb.setCreditorOrgIdAnyBIC(msg.getCdtrOrgIdAnyBIC());
			loc_jb.setCreditorOrgIdOthrId(msg.getCdtrOrgIdOthrId());
			loc_jb.setCreditorOrgIdOthrIdSchmeNm(msg.getCdtrOrgIdOthrIdSchmeNm());
			loc_jb.setCreditorOrgIdOthrIssr(msg.getCdtrOrgIdOthrIssr());
			loc_jb.setCreditorPrvtIdOthrId(msg.getCdtrPrvtIdOthrId());
			loc_jb.setCreditorPrvtIdOthrIdSchmeNm(msg.getCdtrPrvtIdOthrIdSchmeNm());
			loc_jb.setCreditorPrvtIdOthrIssr(msg.getCdtrPrvtIdOthrIssr());
			loc_jb.setCreditorContPhone(msg.getCdtrContPhone());
			loc_jb.setCreditorContEmailAddr(msg.getCdtrContEmailAddr());
			loc_jb.setCreditorAccountNumber(msg.getCdtrAcNo());
			loc_jb.setCreditorAccountNumberType(msg.getCdtrAcTp());
			loc_jb.setCreditorAgentId(msg.getCdtrAgent().getID());
			loc_jb.setCreditorAgentBic(msg.getCdtrAgent().getBICCODE());
			
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

			loc_jb.setDebtorAgentBic(msg.getDbtrAgent().getBICCODE());
			loc_jb.setDebtorAgentId(msg.getDbtrAgent().getID());
			
			loc_jb.setPaymentPurposeType(msg.getPytPurpType());
			if (FFPConstantsPurposeCode.PURPOSE_TYPE_CODE.equals(msg.getPytPurpType()))
			{
				loc_jb.setPaymentPurposeCd(msg.getPytPurp());
			} else if (FFPConstantsPurposeCode.PURPOSE_TYPE_OTHER.equals(msg.getPytPurpType()))
			{
				loc_jb.setPaymentPurposeProprietary(msg.getPytPurp());
			}
			
			loc_jb.setRemittanceInformation(msg.getRemInfo());

			FFPTxJnl txJnl = new FFPTxJnl();
			txJnl.setSrcRefNm(msg.getSrcRefNm());
			txJnl.setJnlNo(loc_jb.getJnlNo());
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
			txJnl.setTxSrc(FFPConstants.TX_SOURCE_FFPAGENT);
			txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
			txJnl.setTransactionId(FFPIDUtils.getTransactionId());
			txJnl.setEndToEndId(FFPIDUtils.getEndToEndId());
			txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_P210.getCode());
			Date loc_date = new Date();
			txJnl.setCreateTs(loc_date);
			txJnl.setLastUpdateTs(loc_date);
			loc_jb.setTxJnl(txJnl);

			List<FFPTxJnlAction> jnlActionList = new ArrayList<>();
			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(txJnl.getJnlNo(), head.getRequestRefno(), FFPConstants.MSG_DIRECTION_INWARD, head.getRequestID(), head.getMessageType(),
					FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), FFPDateUtils.convertStringToDate(head.getTransactionDate() + head.getTransactionTime(), FFPDateUtils.INT_TIMESTAMP_FORMAT),
					loc_date, loc_date, null);
			jnlActionList.add(jnlAction);
			loc_jb.setJnlActionList(jnlActionList);

			FFPVO_DDO01 loc_vo = (FFPVO_DDO01) txVo;
			loc_vo.setP210Jb(loc_jb);
			// Save
			this.daoService.sInsert(loc_jb);
		}
	}

}
