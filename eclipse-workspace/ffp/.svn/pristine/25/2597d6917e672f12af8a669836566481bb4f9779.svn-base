package com.forms.ffp.bussiness.iclfps.pacs003;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.DirectDebitTransactionInformation211;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FIToFICustomerDirectDebitV07;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GenericOrganisationIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GroupHeader501;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsPurposeCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPConstantsTxJnl.MSG_STATUS;
import com.forms.ffp.core.define.FFPConstantsTxJnl.TX_CODE;
import com.forms.ffp.core.define.FFPConstantsTxJnl.TX_STATUS;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP200;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.payment.directdebit.FFPIDaoService_P200;

@Component("ICL.pacs.003.001.07")
@Scope("prototype")
public class FFPTxPacs003 extends FFPTxBase
{
	private Logger _logger = LoggerFactory.getLogger(FFPTxPacs003.class);
	
	@Resource(name = "FFPDaoService_P200")
	private FFPIDaoService_P200 serviceP200;

	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;
	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction txJnlActionService;

	@Override
	public void perform() throws Exception
	{

		_logger.info(" perform() METHOD START !!! ");
		if ("ICL.pacs.003.001.07".equals(this.serviceName))
		{
			performP200();
		}
	}

	@Override
	public boolean validate()
	{
		return true;
	}

	@Override
	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		try
		{

			_logger.info(" parseISO20022BizData(ISO20022BusinessDataV01 bizData)  METHOD START !!! ");

			if ("ICL.pacs.003.001.07".equals(this.serviceName))
			{
				txVo = new FFPVO_Pacs003();
				parseISO20022BizDataHead(bizData);

				FFPVO_Pacs003 pacs003 = (FFPVO_Pacs003) txVo;
				// document get(1) not get(0)
				Document doc = (Document) bizData.getContent().get(1).getValue();

				FIToFICustomerDirectDebitV07 fiToFICstmrDrctDbt = doc.getFIToFICstmrDrctDbt();// fiToFIPmtStsRpt
				GroupHeader501 grpHdr = fiToFICstmrDrctDbt.getGrpHdr();
				if (grpHdr != null)
				{
					pacs003.setMsgId(grpHdr.getMsgId());
					if (grpHdr.getCreDtTm() != null)
					{

						pacs003.setCreDtTm(grpHdr.getCreDtTm().toString());
					}
					if (grpHdr.getSttlmInf() != null)
					{
						if (grpHdr.getSttlmInf().getSttlmMtd() != null)
						{

							pacs003.setSttlmMtd(grpHdr.getSttlmInf().getSttlmMtd().value());
						}
						if (grpHdr.getSttlmInf().getClrSys() != null && grpHdr.getSttlmInf().getClrSys().getPrtry() != null)
						{

							pacs003.setPrtry(grpHdr.getSttlmInf().getClrSys().getPrtry().value());
						}
					}
				}
				// pacs003.setBizSvc(FFPConstantsServiceCode.FFPAGENT_SERVICECODE_D1);
				DirectDebitTransactionInformation211 DbtTxInf = fiToFICstmrDrctDbt.getDrctDbtTxInf();
				if (DbtTxInf != null)
				{
					FFPVO_Pacs003_DrctDbtTxInf txInf = new FFPVO_Pacs003_DrctDbtTxInf();
					txInf.setPmtIdEndToEndId(DbtTxInf.getPmtId().getEndToEndId());
					txInf.setPmtIdTxId(DbtTxInf.getPmtId().getTxId());
					txInf.setClrSysRef(DbtTxInf.getPmtId().getClrSysRef());
					
					txInf.setPmtTpInfCtgyPrtry(DbtTxInf.getPmtTpInf().getCtgyPurp().getPrtry().value());
					
					txInf.setIntrBkSttlmAmt(DbtTxInf.getIntrBkSttlmAmt().getValue());
					txInf.setIntrBkSttlmCurrency(DbtTxInf.getIntrBkSttlmAmt().getCcy().value());
					txInf.setIntrBkSttlmDt(DbtTxInf.getIntrBkSttlmDt().toGregorianCalendar().getTime());
					
					if (DbtTxInf.getInstdAmt() != null)
					{
						txInf.setInstdAmt(DbtTxInf.getInstdAmt().getValue());
						txInf.setInstdCurrency(DbtTxInf.getInstdAmt().getCcy().value());
					}
					
					if (DbtTxInf.getChrgBr() != null)
					{
						txInf.setChrgBr(DbtTxInf.getChrgBr().value());
					}
					
					if(DbtTxInf.getChrgsInf() != null)
					{
						txInf.setChrgAmount(DbtTxInf.getChrgsInf().getAmt().getValue());
						txInf.setChrgCcy(DbtTxInf.getChrgsInf().getAmt().getCcy().value());
						txInf.setChrgAgentId(DbtTxInf.getChrgsInf().getAgt().getFinInstnId().getClrSysMmbId().getMmbId());
						txInf.setChrgAgentBic(DbtTxInf.getChrgsInf().getAgt().getFinInstnId().getBICFI());
					}
					
					if (DbtTxInf.getDrctDbtTx() != null && DbtTxInf.getDrctDbtTx().getMndtRltdInf() != null)
					{
						txInf.setDrctDbtTxRltId(DbtTxInf.getDrctDbtTx().getMndtRltdInf().getMndtId());
					}
					
					txInf.setCdtrNm(DbtTxInf.getCdtr().getNm());
					if (DbtTxInf.getCdtr().getId() != null)
					{
						if (DbtTxInf.getCdtr().getId().getOrgId() != null)
						{
							txInf.setCdtrOrgIdAnyBIC(DbtTxInf.getCdtr().getId().getOrgId().getAnyBIC());
							GenericOrganisationIdentification11 other = DbtTxInf.getCdtr().getId().getOrgId().getOthr();
							if (other != null)
							{
								txInf.setCdtrOrgIdOthrId(other.getId());
								txInf.setCdtrOrgIdOthrIdSchmeNm(other.getSchmeNm().getCd().value());
								txInf.setCdtrOrgIdOthrIssr(other.getIssr());
							}
						}
						else if(DbtTxInf.getCdtr().getId().getPrvtId() != null)
						{
							txInf.setCdtrPrvtIdOthrId(DbtTxInf.getCdtr().getId().getPrvtId().getOthr().getId());
							txInf.setCdtrPrvtIdOthrIdSchmeNm(DbtTxInf.getCdtr().getId().getPrvtId().getOthr().getSchmeNm().getCd().value());
							txInf.setCdtrPrvtIdOthrIssr(DbtTxInf.getCdtr().getId().getPrvtId().getOthr().getIssr());
						}
					}
					
					if(DbtTxInf.getCdtr().getCtctDtls() != null)
					{
						txInf.setCdtrContPhone(DbtTxInf.getCdtr().getCtctDtls().getMobNb());
						txInf.setCdtrContEmailAddr(DbtTxInf.getCdtr().getCtctDtls().getEmailAdr());
					}
					
					txInf.setCdtrAcctIdOthId(DbtTxInf.getCdtrAcct().getId().getOthr().getId());
					txInf.setCdtrAcctIdOthSchPrtry(DbtTxInf.getCdtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
					txInf.setCdtrAgtFiClrMmbId(DbtTxInf.getCdtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
					txInf.setCdtrAgtBic(DbtTxInf.getCdtrAgt().getFinInstnId().getBICFI());
					
					txInf.setDbtrNm(DbtTxInf.getDbtr().getNm());
					if (DbtTxInf.getDbtr().getId() != null)
					{
						if (DbtTxInf.getDbtr().getId().getOrgId() != null)
						{
							txInf.setDbtrOrgIdAnyBIC(DbtTxInf.getDbtr().getId().getOrgId().getAnyBIC());
							GenericOrganisationIdentification11 other = DbtTxInf.getDbtr().getId().getOrgId().getOthr();
							if (other != null)
							{
								txInf.setDbtrOrgIdOthrId(other.getId());
								txInf.setDbtrOrgIdOthrIdSchmeNm(other.getSchmeNm().getCd().value());
								txInf.setDbtrOrgIdOthrIssr(other.getIssr());
							}
						}
						else if(DbtTxInf.getDbtr().getId().getPrvtId() != null)
						{
							txInf.setDbtrPrvtIdOthrId(DbtTxInf.getDbtr().getId().getPrvtId().getOthr().getId());
							txInf.setDbtrPrvtIdOthrIdSchmeNm(DbtTxInf.getDbtr().getId().getPrvtId().getOthr().getSchmeNm().getCd().value());
							txInf.setDbtrPrvtIdOthrIssr(DbtTxInf.getDbtr().getId().getPrvtId().getOthr().getIssr());
						}
					}
					if (DbtTxInf.getDbtr().getCtctDtls() != null)
					{
						txInf.setDbtrContPhone(DbtTxInf.getDbtr().getCtctDtls().getMobNb());
						txInf.setDbtrContEmailAddr(DbtTxInf.getDbtr().getCtctDtls().getEmailAdr());
					}
					
					txInf.setDbtrAcctIdOthId(DbtTxInf.getDbtrAcct().getId().getOthr().getId());
					txInf.setDbtrAcctIdOthSchPrtry(DbtTxInf.getDbtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
					txInf.setDbtrAgtFiClrMmbId(DbtTxInf.getDbtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
					txInf.setDbtrAgtBic(DbtTxInf.getDbtrAgt().getFinInstnId().getBICFI());
					
					if(DbtTxInf.getPurp() != null)
					{
						if(DbtTxInf.getPurp().getCd() != null)
						{
							txInf.setPaymentPurposeType(FFPConstantsPurposeCode.PURPOSE_TYPE_CODE);
							txInf.setPaymentPurposeCd(DbtTxInf.getPurp().getCd());
						}
						else if(DbtTxInf.getPurp().getPrtry() != null)
						{
							txInf.setPaymentPurposeType(FFPConstantsPurposeCode.PURPOSE_TYPE_CODE);
							txInf.setPaymentPurposeProprietary(DbtTxInf.getPurp().getPrtry());
						}
					}
					
					if(DbtTxInf.getRmtInf() != null)
						txInf.setRemittanceInformation(DbtTxInf.getRmtInf().getUstrd());
					
					// for DrctDbtTxInf
					pacs003.setDrctDbtTxInf(txInf);
					insertTxAndActionAndP200(pacs003);
				}
			}
			_logger.info(" parseISO20022BizData(ISO20022BusinessDataV01 bizData)  METHOD END !!! ");

		} catch (Exception e)
		{
			_logger.error(" parseISO20022BizData(ISO20022BusinessDataV01 bizData)  METHOD ERROR !!! ", e);
			throw e;
		}

	}

	private void insertTxAndActionAndP200(FFPVO_Pacs003 pacs003) throws Exception
	{
		_logger.info(" insertTxAndActionAndP200(FFPVO_Pacs003 pacs003) start ");

		FFPJbP200 P200 = new FFPJbP200();
		P200.setJnlNo(FFPIDUtils.getJnlNo());
		P200.setSrcRefNm(FFPIDUtils.getSrcRefNm());
		
		FFPTxJnl txJnl = new FFPTxJnl();
		txJnl.setJnlNo(P200.getJnlNo());
		txJnl.setSrcRefNm(P200.getSrcRefNm());
		txJnl.setTxStat(TX_STATUS.TX_STAT_CREAT.getStatus());
		txJnl.setTxCode(TX_CODE.TX_CODE_P200.getCode());
		txJnl.setTxSrc(FFPConstants.TX_SOURCE_HKICL);
		txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
		txJnl.setTransactionId(pacs003.getDrctDbtTxInf().getPmtIdTxId());
		txJnl.setEndToEndId(pacs003.getDrctDbtTxInf().getPmtIdEndToEndId());
		txJnl.setFpsRefNm(pacs003.getDrctDbtTxInf().getClrSysRef());
		P200.setTxJnl(txJnl);

		P200.setPymtCatPrps(pacs003.getPrtry());
		P200.setSettlementDate(pacs003.getDrctDbtTxInf().getIntrBkSttlmDt());
		P200.setSettlementCurrency(pacs003.getDrctDbtTxInf().getIntrBkSttlmCurrency());
		P200.setSettlementAmount(pacs003.getDrctDbtTxInf().getIntrBkSttlmAmt());
		P200.setInstructedAmount(pacs003.getDrctDbtTxInf().getInstdAmt());
		P200.setInstructedCurrency(pacs003.getDrctDbtTxInf().getInstdCurrency());
		P200.setChargersAgentId(pacs003.getDrctDbtTxInf().getChrgAgentId());
		P200.setChargersAgentBic(pacs003.getDrctDbtTxInf().getChrgAgentBic());
		P200.setChargersCurrency(pacs003.getDrctDbtTxInf().getChrgCcy());
		P200.setChargersAmount(pacs003.getDrctDbtTxInf().getChrgAmount());
		
		P200.setCreditorName(pacs003.getDrctDbtTxInf().getCdtrNm());
		P200.setCreditorOrgIdAnyBIC(pacs003.getDrctDbtTxInf().getCdtrOrgIdAnyBIC());
		P200.setCreditorOrgIdOthrId(pacs003.getDrctDbtTxInf().getCdtrOrgIdOthrId());
		P200.setCreditorOrgIdOthrIdSchmeNm(pacs003.getDrctDbtTxInf().getCdtrOrgIdOthrIdSchmeNm());
		P200.setCreditorOrgIdOthrIssr(pacs003.getDrctDbtTxInf().getCdtrOrgIdOthrIssr());
		P200.setCreditorPrvtIdOthrId(pacs003.getDrctDbtTxInf().getCdtrPrvtIdOthrId());
		P200.setCreditorPrvtIdOthrIdSchmeNm(pacs003.getDrctDbtTxInf().getCdtrPrvtIdOthrIdSchmeNm());
		P200.setCreditorPrvtIdOthrIssr(pacs003.getDrctDbtTxInf().getCdtrPrvtIdOthrIssr());
		P200.setCreditorContPhone(pacs003.getDrctDbtTxInf().getCdtrContPhone());
		P200.setCreditorContEmailAddr(pacs003.getDrctDbtTxInf().getCdtrContEmailAddr());
		P200.setCreditorAccountNumber(pacs003.getDrctDbtTxInf().getCdtrAcctIdOthId());
		P200.setCreditorAccountNumberType(pacs003.getDrctDbtTxInf().getCdtrAcctIdOthSchPrtry());
		P200.setCreditorAgentId(pacs003.getDrctDbtTxInf().getCdtrAgtFiClrMmbId());
		P200.setCreditorAgentBic(pacs003.getDrctDbtTxInf().getCdtrAgtBic());
		
		P200.setDebtorName(pacs003.getDrctDbtTxInf().getDbtrNm());
		P200.setDebtorOrgIdAnyBIC(pacs003.getDrctDbtTxInf().getDbtrOrgIdAnyBIC());
		P200.setDebtorOrgIdOthrId(pacs003.getDrctDbtTxInf().getDbtrOrgIdOthrId());
		P200.setDebtorOrgIdOthrIdSchmeNm(pacs003.getDrctDbtTxInf().getDbtrOrgIdOthrIdSchmeNm());
		P200.setDebtorOrgIdOthrIssr(pacs003.getDrctDbtTxInf().getDbtrOrgIdOthrIssr());
		P200.setDebtorPrvtIdOthrId(pacs003.getDrctDbtTxInf().getDbtrPrvtIdOthrId());
		P200.setDebtorPrvtIdOthrIdSchmeNm(pacs003.getDrctDbtTxInf().getDbtrPrvtIdOthrIdSchmeNm());
		P200.setDebtorPrvtIdOthrIssr(pacs003.getDrctDbtTxInf().getDbtrPrvtIdOthrIssr());
		P200.setDebtorContPhone(pacs003.getDrctDbtTxInf().getDbtrContPhone());
		P200.setDebtorContEmailAddr(pacs003.getDrctDbtTxInf().getDbtrContEmailAddr());
		P200.setDebtorAccountNumber(pacs003.getDrctDbtTxInf().getDbtrAcctIdOthId());
		P200.setDebtorAccountNumberType(pacs003.getDrctDbtTxInf().getDbtrAcctIdOthSchPrtry());
		P200.setDebtorAgentId(pacs003.getDrctDbtTxInf().getDbtrAgtFiClrMmbId());
		P200.setDebtorAgentBic(pacs003.getDrctDbtTxInf().getDbtrAgtBic());
		P200.setDebtorContPhone(pacs003.getDrctDbtTxInf().getDbtrContPhone());
		
		P200.setPaymentPurposeType(pacs003.getDrctDbtTxInf().getPaymentPurposeType());
		P200.setPaymentPurposeCd(pacs003.getDrctDbtTxInf().getPaymentPurposeCd());
		P200.setPaymentPurposeProprietary(pacs003.getDrctDbtTxInf().getPaymentPurposeProprietary());
		
		FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(P200.getTxJnl().getJnlNo(), pacs003.getMsgId(), FFPConstants.MSG_DIRECTION_INWARD, FFPConstants.TX_SOURCE_HKICL, pacs003.getMsgDefIdr(),
				MSG_STATUS.MSG_STAT_MSYNC.getStatus(), pacs003.getCreateDate(), new Date(), new Date(), null);
		P200.getJnlActionList().add(jnlAction);
		pacs003.setP200Jb(P200);
		serviceP200.sInsert(P200);
		_logger.info(" insertTxAndActionAndP200(FFPVO_Pacs003 pacs003) end ");

	}

	private void performP200() throws Exception
	{
		FFPVO_Pacs003 loc_pacs003 = (FFPVO_Pacs003) txVo;
		loc_pacs003.setMsgDefIdr(FFPJaxbConstants.JAXB_MSG_TYPE_FFPDDI02);
		FFPJbP200 loc_jb = loc_pacs003.getP200Jb();
		
		FFPMsgPacs003_DDI02 loc_msg = new FFPMsgPacs003_DDI02(loc_pacs003);

		FFPTxJnlAction jnlAction_before = FFPJnlUtils.getInstance().newJnlAction(
				loc_jb.getTxJnl().getJnlNo(), loc_msg.getReqRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD,
				loc_msg.getResponseID(), loc_msg.getMsgType(), MSG_STATUS.MSG_STAT_CREAT.getStatus(),
				loc_msg.getCreateTs(), new Date(), null, null);
		loc_jb.getJnlActionList().add(jnlAction_before);
		
		try {
			FFPSendTcpMessageResp msgResp = FFPAdaptorMgr.getInstance().execute(loc_msg);
			jnlAction_before.setMsgComplTs(new Date());
			if (msgResp.isTimeOut())
			{
				jnlAction_before.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
				jnlAction_before.setMsgComplTs(new Date());
				return;
			}
			else
			{
				jnlAction_before.setMsgStatus(MSG_STATUS.MSG_STAT_MSYNC.getStatus());
				
				FFPTxJnlAction jnlAction_after = FFPJnlUtils.getInstance().newJnlAction(
						loc_jb.getTxJnl().getJnlNo(), loc_msg.getResRefNo(), FFPConstants.MSG_DIRECTION_INWARD,
						loc_msg.getResponseID(), loc_msg.getMsgType(), MSG_STATUS.MSG_STAT_MSYNC.getStatus(),
						loc_msg.getResponseMsgCode(), loc_msg.getResponseMsg(),
						loc_msg.getRespMsgCreateTs(), new Date(), new Date(), loc_msg.getReqRefNo(), null);
				loc_jb.getJnlActionList().add(jnlAction_after);
				
				loc_msg.unmarshalResponseMsg(msgResp.getRespMessage());
				FFPVO_Pacs003_DDI02REPLY ddi02Reply = loc_pacs003.getDdi02Reply();
				
				//update txJnl
				FFPTxJnl txJnl = loc_jb.getTxJnl();
				txJnl.setTxStat(TX_STATUS.TX_STAT_PDNG.getStatus());
				txJnl.setLastUpdateTs(new Date());
				if ("R".equals(ddi02Reply.getRsltCd()))
				{
					txJnl.setTxStat(TX_STATUS.TX_STAT_AGENTREJCT.getStatus());
					txJnl.setTxRejCode(ddi02Reply.getRejCd());
					txJnl.setTxRejReason(ddi02Reply.getRejMsg());
				}
				
				FFPMsgPacs003_Pacs002 pacs002 = new FFPMsgPacs003_Pacs002(loc_pacs003);
				FFPTxJnlAction sendToICLAction = FFPJnlUtils.getInstance().newJnlAction(
						loc_jb.getTxJnl().getJnlNo(), pacs002.getMsgID(), FFPConstants.MSG_DIRECTION_OUTWARD, 
						FFPConstants.RELATION_SYSTEM_HKICL, pacs002.getMsgTypeName(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), 
						pacs002.getCreDt(), new Date(), null, loc_pacs003.getMsgId());
				loc_jb.getJnlActionList().add(sendToICLAction);
				
				FFPSendMessageResp msgRespFromICL = FFPAdaptorMgr.getInstance().execute(pacs002);
				sendToICLAction.setMsgStatus(msgRespFromICL.getMessageStatus());
				sendToICLAction.setMsgComplTs(new Date());
				if(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(msgRespFromICL.getMessageStatus()))
				{
					loc_jb.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus());
				}
			}
		} catch (Exception ip_e) {
			_logger.warn("FFPTxPacs008", ip_e);
			throw ip_e;
		} finally {
			serviceP200.updateJnlStat(loc_jb);
		}
	}
}
