package com.forms.ffp.webapp.cashmanagement.return_refund.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_054_001_06.ChargeBearerType1Code1;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.payment.returnrefund.FFPDaoService_P300;
import com.forms.ffp.webapp.cashmanagement.return_refund.bean.FFPVO_Pacs004_TxInf;
import com.forms.ffp.webapp.cashmanagement.return_refund.form.DoReturnRefundForm;

@Component("ReturnRefund_Pacs004Service")
@Scope("prototype")
public class ReturnRefund_Pacs004Service {
	
	private Logger logger = LoggerFactory.getLogger(ReturnRefund_Pacs004Service.class);
	

	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction txJnlActionService;

	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;

	@Resource(name = "FFPDaoService_P300")
	private FFPDaoService_P300 dao300;
	
	@Resource(name = "FFPVO_Pacs004_TxInf")
	private FFPVO_Pacs004_TxInf txinf;
	
	public void performMsg(DoReturnRefundForm form) throws Exception {
		if (null == form)
			return;
		
//		txinf = new FFPVO_Pacs004_TxInf();
		FFPJbP300 p300 = null;
		FFPTxJnl orglTxjnl = null;
		Object obj = txJnlService.inquiryByJnlNo(form.getJnlNo());
		if(obj == null)
		{
			return;
		}
		else if(obj instanceof FFPJbP110)
		{
			FFPJbP110 loc_p110 = (FFPJbP110)obj;
			orglTxjnl = loc_p110.getTxJnl();
			p300 = createP300Jb(loc_p110);
		}else if(obj instanceof FFPJbP210)
		{
			FFPJbP210 loc_p210 = (FFPJbP210)obj;
			orglTxjnl = loc_p210.getTxJnl();
			p300 = createP300Jb(loc_p210);
		}
		
		p300.setReasonCode(form.getRetCode());
		p300.setAdditionalInformation(form.getRetReason());
		txinf.setP300(p300);
		
		if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus().equals(orglTxjnl.getTxStat()))
		{
			boolean agent = callFFPRRO01(p300, orglTxjnl);
			if(agent)
			{
				callFPS_pacs004(p300, orglTxjnl);
			}
		}
		else if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_RETURN.getStatus().equals(orglTxjnl.getTxStat()))
		{
			callFPS_pacs004(p300, orglTxjnl);
		}
		else if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_AGENTREJCT.getStatus().equals(orglTxjnl.getTxStat()))
		{
			callFPS_pacs004(p300, orglTxjnl);
		}
		
		
	}
	
	private boolean callFFPRRO01(FFPJbP300 p300, FFPTxJnl orglTxjnl)
	{
		try
		{
			ReturnRefund_RRO01 pr = new ReturnRefund_RRO01(txinf);
			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(p300.getTxJnl().getJnlNo(),
					pr.getReqRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD, pr.getResponseID(), pr.getMsgType(),
					FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), new Date(), new Date(), null, null);
			p300.getJnlActionList().add(jnlAction);
			FFPSendTcpMessageResp msgResp = FFPAdaptorMgr.getInstance().execute(pr);
			jnlAction.setMsgComplTs(new Date());
			if(!msgResp.isTimeOut())
			{
				jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
				pr.unmarshalResponseMsg(msgResp.getRespMessage());
				FFPTxJnlAction jnlAction1 = FFPJnlUtils.getInstance().newJnlAction(p300.getTxJnl().getJnlNo(),
						pr.getResRefNo(), FFPConstants.MSG_DIRECTION_INWARD, pr.getResponseID(), pr.getMsgType(),
						FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), pr.getRejCd(), pr.getRejMsg(),
						pr.getRespMsgCreateTs(), new Date(), new Date(), pr.getReqRefNo(), null);
				p300.getJnlActionList().add(jnlAction1);
				
				if ("A".equals(txinf.getReply().getRsltCd())) {
					orglTxjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_REFUND.getStatus());
					orglTxjnl.setLastUpdateTs(new Date());
					txJnlService.updateJnl(orglTxjnl);
					p300.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_PDNG.getStatus());
					p300.getTxJnl().setLastUpdateTs(new Date());
					txJnlService.updateJnlStat(p300);
					return true;
				} else {// RJCT
					p300.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_AGENTREJCT.getStatus());
					p300.getTxJnl().setTxRejCode(txinf.getReply().getRejCd());
					p300.getTxJnl().setTxRejCode(txinf.getReply().getRejMsg());
					p300.getTxJnl().setLastUpdateTs(new Date());
					txJnlService.updateJnlStat(p300);
					return false;
				}
			}
			else
			{
				jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
				txJnlService.updateJnlStat(p300);
				return false;
			}
		}
		catch(Exception ip_e)
		{
			logger.warn("ReturnRefund_Pacs004Service", ip_e);
			return false;
		}
	}
	
	private boolean callFPS_pacs004(FFPJbP300 p300, FFPTxJnl orglTxjnl)
	{
		try
		{
			ReturnRefund_pacs004 pacs004 = new ReturnRefund_pacs004(p300);
			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(p300.getTxJnl().getJnlNo(), pacs004.getMsgID(),
					FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.TX_SOURCE_HKICL, pacs004.getMsgTypeName(),
					FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), pacs004.getCreDt(), new Date(), new Date(),
					null);
			p300.getJnlActionList().add(jnlAction);
			FFPSendMessageResp msgResp = FFPAdaptorMgr.getInstance().execute(pacs004);
			jnlAction.setMsgComplTs(new Date());
			if(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(msgResp.getMessageStatus()))
			{
				if(!FFPConstantsTxJnl.TX_STATUS.TX_STAT_REFUND.getStatus().equals(orglTxjnl.getTxStat())){
					orglTxjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_RETURN.getStatus());
					orglTxjnl.setLastUpdateTs(new Date());
					txJnlService.updateJnl(orglTxjnl);
				}
				
				jnlAction.setMsgComplTs(new Date());
				jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
				p300.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_PDNG.getStatus());
				txJnlService.updateJnlStat(p300);
				return true;
				
			}
			else
			{
				jnlAction.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
				txJnlService.updateJnlStat(p300);
				return false;
			}
		}
		catch(Exception ip_e)
		{
			logger.warn("ReturnRefund_Pacs004Service", ip_e);
			return false;
		}
	}
	
	private FFPJbP300 createP300Jb(FFPJbP110 p110) {

		FFPJbP300 p300=new FFPJbP300();
		
		FFPTxJnl txJnl = new FFPTxJnl();
		txJnl.setJnlNo(FFPIDUtils.getJnlNo());
		txJnl.setSrcRefNm(FFPIDUtils.getSrcRefNm());
		txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
		txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode());
		txJnl.setTxSrc(FFPConstants.TX_SOURCE_FFP);
		txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
		txJnl.setTransactionId(null);
		txJnl.setEndToEndId(null);
		txJnl.setFpsRefNm(null);
		txJnl.setCreateTs(new Date());
		txJnl.setLastUpdateTs(new Date());
		p300.setTxJnl(txJnl);
		
		p300.setReturnId(FFPIDUtils.getTransactionId());
		p300.setOrgnlEndToEndId(p110.getTxJnl().getEndToEndId());
		p300.setOrgnlTxId(p110.getTxJnl().getTransactionId());
		p300.setOrgnlClrSysRef(p110.getTxJnl().getFpsRefNm());
		
		p300.setRetIntSetAmt(p110.getSettlementAmount());
		p300.setRetIntSetCur(p110.getSettlementCurrency());
		p300.setSettlementDate(p110.getSettlementDate());
		
		p300.setRetInsAmt(p110.getSettlementAmount());
		p300.setRetInsCur(p110.getSettlementCurrency());
		/**
		 * interbank 
		 */
		p300.setOrgnlInterbankSettAmt(p110.getSettlementAmount());
		p300.setOrgnlInterbankSettCcy(p110.getSettlementCurrency());
		p300.setOrgnlInterbankSettDate(p110.getSettlementDate());
		
		p300.setOrgnlCatgyPurp(p110.getPymtCatPrps());
		p300.setOrgnlMandateInfo(null);
		p300.setOrgnlRemtInfo(p110.getRemittanceInformation());
		p300.setChrgBr(ChargeBearerType1Code1.SLEV.value());
		
		/**
		 * debtor information
		 */
		p300.setOrgnlDbtrNm(p110.getDebtorName());
		p300.setOrgnlDbtrPhNo(p110.getDebtorContPhone());
		p300.setOrgnlDbtrEmAddr(p110.getDebtorContEmailAddr());
		p300.setOrgnlDbtrAcctNo(p110.getDebtorAccountNumber());
		p300.setOrgnlDbtrAcctNoTp(p110.getDebtorAccountNumberType());
		p300.setOrgnlDbtrAgtBIC(p110.getDebtorAgentBic());
		p300.setOrgnlDbtrAgtId(p110.getDebtorAgentId());
		
		p300.setOrgnlDbtrOrgIdAnyBIC(p110.getDebtorOrgIdAnyBIC());
		p300.setOrgnlDbtrOrgIdOthrId(p110.getDebtorOrgIdOthrId());
		p300.setOrgnlDbtrOrgIdOthrIdSchmeNm(p110.getDebtorOrgIdOthrIdSchmeNm());
		p300.setOrgnlDbtrOrgIdOthrIssr(p110.getDebtorOrgIdOthrIssr());
		p300.setOrgnlDbtrPrvtIdOthrId(p110.getDebtorPrvtIdOthrId());
		p300.setOrgnlDbtrPrvtIdOthrIdSchmeNm(p110.getDebtorPrvtIdOthrIdSchmeNm());
		p300.setOrgnlDbtrPrvtIdOthrIssr(p110.getDebtorPrvtIdOthrIssr());
		
		/**
		 * creditor information
		 */
		p300.setOrgnlCdtrNm(p110.getCreditorName());
		p300.setOrgnlCdtrPhNo(p110.getCreditorContPhone());
		p300.setOrgnlCdtrEmAddr(p110.getCreditorContEmailAddr());
		p300.setOrgnlCdtrAcctNo(p110.getCreditorAccountNumber());
		p300.setOrgnlCdtrAcctNoTp(p110.getCreditorAccountNumberType());
		p300.setOrgnlCdtrAgtBIC(p110.getCreditorAgentBic());
		p300.setOrgnlCdtrAgtId(p110.getCreditorAgentId());
		
		p300.setOrgnlCdtrOrgIdAnyBIC(p110.getCreditorOrgIdAnyBIC());
		p300.setOrgnlCdtrOrgIdOthrId(p110.getCreditorOrgIdOthrId());
		p300.setOrgnlCdtrOrgIdOthrIdSchmeNm(p110.getCreditorOrgIdOthrIdSchmeNm());
		p300.setOrgnlCdtrOrgIdOthrIssr(p110.getCreditorOrgIdOthrIssr());
		p300.setOrgnlCdtrPrvtIdOthrId(p110.getCreditorPrvtIdOthrId());
		p300.setOrgnlCdtrPrvtIdOthrIdSchmeNm(p110.getCreditorPrvtIdOthrIdSchmeNm());
		p300.setOrgnlCdtrPrvtIdOthrIssr(p110.getCreditorPrvtIdOthrIssr());
		
		return p300;
	}

	private FFPJbP300 createP300Jb(FFPJbP210 p210) {

		FFPJbP300 p300=new FFPJbP300();
		
		FFPTxJnl txJnl = new FFPTxJnl();
		txJnl.setJnlNo(FFPIDUtils.getJnlNo());
		txJnl.setSrcRefNm(FFPIDUtils.getSrcRefNm());
		txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
		txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode());
		txJnl.setTxSrc(FFPConstants.TX_SOURCE_FFP);
		txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
		txJnl.setTransactionId(null);
		txJnl.setEndToEndId(null);
		txJnl.setFpsRefNm(null);
		txJnl.setCreateTs(new Date());
		txJnl.setLastUpdateTs(new Date());
		p300.setTxJnl(txJnl);
		
		
		p300.setReturnId(FFPIDUtils.getTransactionId());
		p300.setOrgnlEndToEndId(p210.getTxJnl().getEndToEndId());
		p300.setOrgnlTxId(p210.getTxJnl().getTransactionId());
		p300.setOrgnlClrSysRef(p210.getTxJnl().getFpsRefNm());
		
		p300.setRetIntSetAmt(p210.getSettlementAmount());
		p300.setRetIntSetCur(p210.getSettlementCurrency());
		p300.setSettlementDate(p210.getSettlementDate());
		p300.setRetInsAmt(p210.getSettlementAmount());
		p300.setRetInsCur(p210.getSettlementCurrency());
		
		/**
		 * interbank 
		 */
		p300.setOrgnlInterbankSettAmt(p210.getSettlementAmount());
		p300.setOrgnlInterbankSettCcy(p210.getSettlementCurrency());
		p300.setOrgnlInterbankSettDate(p210.getSettlementDate());
		
		p300.setOrgnlCatgyPurp(p210.getPymtCatPrps());
		p300.setOrgnlMandateInfo(null);
		p300.setOrgnlRemtInfo(p210.getRemittanceInformation());
		p300.setChrgBr(ChargeBearerType1Code1.SLEV.value());
		
		/**
		 * debtor information
		 */
		p300.setOrgnlDbtrNm(p210.getDebtorName());
		p300.setOrgnlDbtrPhNo(p210.getDebtorContPhone());
		p300.setOrgnlDbtrEmAddr(p210.getDebtorContEmailAddr());
		p300.setOrgnlDbtrAcctNo(p210.getDebtorAccountNumber());
		p300.setOrgnlDbtrAcctNoTp(p210.getDebtorAccountNumberType());
		p300.setOrgnlDbtrAgtBIC(p210.getDebtorAgentBic());
		p300.setOrgnlDbtrAgtId(p210.getDebtorAgentId());
		
		p300.setOrgnlDbtrOrgIdAnyBIC(p210.getDebtorOrgIdAnyBIC());
		p300.setOrgnlDbtrOrgIdOthrId(p210.getDebtorOrgIdOthrId());
		p300.setOrgnlDbtrOrgIdOthrIdSchmeNm(p210.getDebtorOrgIdOthrIdSchmeNm());
		p300.setOrgnlDbtrOrgIdOthrIssr(p210.getDebtorOrgIdOthrIssr());
		p300.setOrgnlDbtrPrvtIdOthrId(p210.getDebtorPrvtIdOthrId());
		p300.setOrgnlDbtrPrvtIdOthrIdSchmeNm(p210.getDebtorPrvtIdOthrIdSchmeNm());
		p300.setOrgnlDbtrPrvtIdOthrIssr(p210.getDebtorPrvtIdOthrIssr());
		
		/**
		 * creditor information
		 */
		p300.setOrgnlCdtrNm(p210.getCreditorName());
		p300.setOrgnlCdtrPhNo(p210.getCreditorContPhone());
		p300.setOrgnlCdtrEmAddr(p210.getCreditorContEmailAddr());
		p300.setOrgnlCdtrAcctNo(p210.getCreditorAccountNumber());
		p300.setOrgnlCdtrAcctNoTp(p210.getCreditorAccountNumberType());
		p300.setOrgnlCdtrAgtBIC(p210.getCreditorAgentBic());
		p300.setOrgnlCdtrAgtId(p210.getCreditorAgentId());
		
		p300.setOrgnlCdtrOrgIdAnyBIC(p210.getCreditorOrgIdAnyBIC());
		p300.setOrgnlCdtrOrgIdOthrId(p210.getCreditorOrgIdOthrId());
		p300.setOrgnlCdtrOrgIdOthrIdSchmeNm(p210.getCreditorOrgIdOthrIdSchmeNm());
		p300.setOrgnlCdtrOrgIdOthrIssr(p210.getCreditorOrgIdOthrIssr());
		p300.setOrgnlCdtrPrvtIdOthrId(p210.getCreditorPrvtIdOthrId());
		p300.setOrgnlCdtrPrvtIdOthrIdSchmeNm(p210.getCreditorPrvtIdOthrIdSchmeNm());
		p300.setOrgnlCdtrPrvtIdOthrIssr(p210.getCreditorPrvtIdOthrIssr());
		
		return p300;
	}
}
