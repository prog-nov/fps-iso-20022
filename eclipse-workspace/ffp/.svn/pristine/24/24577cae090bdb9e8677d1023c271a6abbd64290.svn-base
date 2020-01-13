package com.forms.ffp.bussiness.iclfps.pacs004;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.OriginalTransactionReference241;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_004_001_07.PaymentTransaction761;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.payment.returnrefund.FFPDaoService_P300;

@Component("ICL.pacs.004.001.07")
@Scope("prototype")
public class FFPTxpacs004 extends FFPTxBase
{

	private Logger logger = LoggerFactory.getLogger(FFPTxpacs004.class);

	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction txJnlActionService;

	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl jnlDao;

	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;

	@Resource(name = "FFPDaoService_P300")
	private FFPDaoService_P300 dao300;

	@Override
	public void perform() throws Exception
	{
		if ("ICL.pacs.004.001.07".equals(this.serviceName))
		{

			FFPVO_Pacs004 p004 = (FFPVO_Pacs004) this.txVo;

			List<FFPVO_Pacs004_TxInf> txInflist = p004.getTxinf();
			for (FFPVO_Pacs004_TxInf txInf : txInflist)
			{

				FFPJbP300 p300 = txInf.getP300();

				FFPTxJnl txJnl = new FFPTxJnl();
				txJnl.setJnlNo(FFPIDUtils.getJnlNo());
				txJnl.setSrcRefNm(FFPIDUtils.getSrcRefNm());
				txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
				txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode());
				txJnl.setTxSrc(FFPConstants.TX_SOURCE_HKICL);
				txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
				txJnl.setTransactionId(null);
				txJnl.setEndToEndId(null);
				txJnl.setFpsRefNm(p300.getReturnId());
				txJnl.setCreateTs(new Date());
				txJnl.setLastUpdateTs(new Date());
				FFPTxJnl orgnlTxjnl = jnlDao.inquiryById(p300.getOrgnlTxId(), p300.getOrgnlEndToEndId());
				if (null != orgnlTxjnl)
					txJnl.setJnlRef(orgnlTxjnl.getJnlNo());
				p300.setTxJnl(txJnl);

				FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(txJnl.getJnlNo(), p004.getBizMsgIdr(), FFPConstants.MSG_DIRECTION_INWARD,
						p004.getFromClrSysMmbId() != null ? p004.getFromClrSysMmbId() : p004.getFromBic(), p004.getMsgDefIdr(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(),
						p004.getCreateDate(), new Date(), new Date(), null);
				p300.getJnlActionList().add(jnlAction);
				dao300.sInsert(p300);

				Date loc_date = new Date();
				FFPMsgPacs004_RRI01 pr = new FFPMsgPacs004_RRI01(txInf);
				FFPTxJnlAction jnlAction2 = FFPJnlUtils.getInstance().newJnlAction(p300.getTxJnl().getJnlNo(), pr.getReqRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD, pr.getResponseID(),
						pr.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), loc_date, loc_date, null, null);
				p300.getJnlActionList().add(jnlAction2);

				try
				{
					FFPSendTcpMessageResp msgResp = FFPAdaptorMgr.getInstance().execute(pr);
					if (!msgResp.isTimeOut())
					{
						jnlAction2.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
						jnlAction2.setMsgComplTs(new Date());
						pr.unmarshalResponseMsg(msgResp.getRespMessage());

						loc_date = new Date();
						FFPTxJnlAction jnlAction3 = FFPJnlUtils.getInstance().newJnlAction(p300.getTxJnl().getJnlNo(), pr.getResRefNo(), FFPConstants.MSG_DIRECTION_INWARD, pr.getResponseID(),
								pr.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), loc_date, loc_date, loc_date, null);
						p300.getJnlActionList().add(jnlAction3);

						if ("A".equals(txInf.getReply().getRsltCd()))
						{
							p300.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
							p300.getTxJnl().setLastUpdateTs(loc_date);
						} else if ("R".equals(txInf.getReply().getRsltCd()))
						{// RJCT
							p300.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_AGENTREJCT.getStatus());
							p300.getTxJnl().setTxRejCode(txInf.getReply().getRejCd());
							p300.getTxJnl().setTxRejReason(txInf.getReply().getRejMsg());
							p300.getTxJnl().setLastUpdateTs(loc_date);
						}
					} else
					{
						jnlAction2.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
						jnlAction2.setMsgComplTs(new Date());
					}
				} catch (Exception ip_e)
				{
					logger.warn("FFPTxpacs004", ip_e);
				} finally
				{
					txJnlService.updateJnlStat(p300);
				}
			}
		}

	}

	@Override
	public boolean validate() throws Exception
	{
		FFPVO_Pacs004 p004 = (FFPVO_Pacs004) this.txVo;
		if (null == p004.getTxinf())
			return false;
		return true;
	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		if ("ICL.pacs.004.001.07".equals(this.serviceName))
		{
			txVo = new FFPVO_Pacs004();

			parseISO20022BizDataHead(bizData);

			FFPVO_Pacs004 p004 = (FFPVO_Pacs004) this.txVo;

			Document doc = (Document) bizData.getContent().get(1).getValue();

			List<FFPVO_Pacs004_TxInf> list = getFFPVO_Pacs004_TxInfList(doc);

			p004.setTxinf(list);

		}
	}

	private List<FFPVO_Pacs004_TxInf> getFFPVO_Pacs004_TxInfList(Document doc)
	{
		List<PaymentTransaction761> list = doc.getPmtRtr().getTxInf();

		List<FFPVO_Pacs004_TxInf> listTxinf = new ArrayList<FFPVO_Pacs004_TxInf>();

		for (PaymentTransaction761 pt761 : list)
		{
			FFPVO_Pacs004_TxInf txinf = new FFPVO_Pacs004_TxInf();
			FFPJbP300 p300 = new FFPJbP300();

			p300.setReturnId(pt761.getRtrId());
			p300.setOrgnlInstrId(pt761.getOrgnlInstrId());
			p300.setOrgnlEndToEndId(pt761.getOrgnlEndToEndId());
			p300.setOrgnlTxId(pt761.getOrgnlTxId());
			p300.setOrgnlClrSysRef(pt761.getOrgnlClrSysRef());

			p300.setRetIntSetAmt(pt761.getRtrdIntrBkSttlmAmt().getValue());
			p300.setRetIntSetCur(pt761.getRtrdIntrBkSttlmAmt().getCcy().value());

			p300.setSettlementDate(pt761.getIntrBkSttlmDt().toGregorianCalendar().getTime());

			if (null != pt761.getRtrdInstdAmt())
			{
				p300.setRetInsCur(pt761.getRtrdInstdAmt().getCcy().value());
				p300.setRetInsAmt(pt761.getRtrdInstdAmt().getValue());
			}

			p300.setChrgBr(pt761.getChrgBr().value());
			if (null != pt761.getChrgsInf())
			{
				p300.setChargersAmount(pt761.getChrgsInf().getAmt().getValue());
				p300.setChargersCurrency(pt761.getChrgsInf().getAmt().getCcy().value());
				p300.setChgAgtID(pt761.getChrgsInf().getAgt().getFinInstnId().getClrSysMmbId().getMmbId());
				p300.setChgAgtBIC(pt761.getChrgsInf().getAgt().getFinInstnId().getBICFI());
			}

			p300.setReasonCode(pt761.getRtrRsnInf().getRsn().getPrtry());
			if (null != pt761.getRtrRsnInf().getAddtlInf())
			{
				StringBuilder sb = new StringBuilder();
				for (String str : pt761.getRtrRsnInf().getAddtlInf())
					sb.append(str);
				p300.setAdditionalInformation(sb.toString());
			}

			/**
			 * OriginalTransaction Reference
			 */
			OriginalTransactionReference241 otr = pt761.getOrgnlTxRef();
			p300.setOrgnlInterbankSettAmt(otr.getIntrBkSttlmAmt().getValue());
			p300.setOrgnlInterbankSettCcy(otr.getIntrBkSttlmAmt().getCcy().value());
			p300.setOrgnlInterbankSettDate(otr.getIntrBkSttlmDt().toGregorianCalendar().getTime());

			// PymtCatPrps
			if (null != otr.getPmtTpInf())
				p300.setOrgnlCatgyPurp(otr.getPmtTpInf().getCtgyPurp().getPrtry().value());

			if (null != otr.getMndtRltdInf())
				p300.setOrgnlMandateInfo(otr.getMndtRltdInf().getMndtId());

			// reminfo
			if (null != otr.getRmtInf())
				p300.setOrgnlRemtInfo(otr.getRmtInf().getUstrd());

			// debtor information
			p300.setOrgnlDbtrNm(otr.getDbtr().getNm());
			if (null != otr.getDbtr().getId())
			{
				if (null != otr.getDbtr().getId().getOrgId())
				{
					p300.setOrgnlDbtrOrgIdAnyBIC(otr.getDbtr().getId().getOrgId().getAnyBIC());
					if (null != otr.getDbtr().getId().getOrgId().getOthr())
					{
						p300.setOrgnlDbtrOrgIdOthrId(otr.getDbtr().getId().getOrgId().getOthr().getId());
						p300.setOrgnlDbtrOrgIdOthrIdSchmeNm(otr.getDbtr().getId().getOrgId().getOthr().getSchmeNm().getCd().value());
						p300.setOrgnlDbtrOrgIdOthrIssr(otr.getDbtr().getId().getOrgId().getOthr().getIssr());
					}
				}
				if (null != otr.getDbtr().getId().getPrvtId())
				{
					p300.setOrgnlDbtrPrvtIdOthrId(otr.getDbtr().getId().getPrvtId().getOthr().getId());
					;
					p300.setOrgnlDbtrPrvtIdOthrIdSchmeNm(otr.getDbtr().getId().getPrvtId().getOthr().getSchmeNm().getCd().value());
					;
					p300.setOrgnlDbtrPrvtIdOthrIssr(otr.getDbtr().getId().getPrvtId().getOthr().getIssr());
				}
			}

			if (null != otr.getDbtr().getCtctDtls())
			{
				p300.setOrgnlDbtrPhNo(otr.getDbtr().getCtctDtls().getMobNb());
				p300.setOrgnlDbtrEmAddr(otr.getDbtr().getCtctDtls().getEmailAdr());
			}

			// debtorAccount information
			p300.setOrgnlDbtrAcctNoTp(otr.getDbtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());
			p300.setOrgnlDbtrAcctNo(otr.getDbtrAcct().getId().getOthr().getId());

			// debtorAgent information
			p300.setOrgnlDbtrAgtBIC(otr.getDbtrAgt().getFinInstnId().getBICFI());
			p300.setOrgnlDbtrAgtId(otr.getDbtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());

			// creditorAgent information
			p300.setOrgnlCdtrAgtBIC(otr.getCdtrAgt().getFinInstnId().getBICFI());
			p300.setOrgnlCdtrAgtId(otr.getCdtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());

			// creditor information
			p300.setOrgnlCdtrNm(otr.getCdtr().getNm());
			if (null != otr.getCdtr().getId())
			{
				if (null != otr.getCdtr().getId().getOrgId())
				{
					p300.setOrgnlCdtrOrgIdAnyBIC(otr.getCdtr().getId().getOrgId().getAnyBIC());
					if (null != otr.getCdtr().getId().getOrgId().getOthr())
					{
						p300.setOrgnlCdtrOrgIdOthrId(otr.getCdtr().getId().getOrgId().getOthr().getId());
						p300.setOrgnlCdtrOrgIdOthrIdSchmeNm(otr.getCdtr().getId().getOrgId().getOthr().getSchmeNm().getCd().value());
						p300.setOrgnlCdtrOrgIdOthrIssr(otr.getCdtr().getId().getOrgId().getOthr().getIssr());
					}
				}
				if (null != otr.getCdtr().getId().getPrvtId())
				{
					p300.setOrgnlCdtrPrvtIdOthrId(otr.getCdtr().getId().getPrvtId().getOthr().getId());
					;
					p300.setOrgnlCdtrPrvtIdOthrIdSchmeNm(otr.getCdtr().getId().getPrvtId().getOthr().getSchmeNm().getCd().value());
					;
					p300.setOrgnlCdtrPrvtIdOthrIssr(otr.getCdtr().getId().getPrvtId().getOthr().getIssr());
				}
			}

			if (null != otr.getCdtr().getCtctDtls())
			{
				p300.setOrgnlCdtrPhNo(otr.getCdtr().getCtctDtls().getMobNb());
				p300.setOrgnlCdtrEmAddr(otr.getCdtr().getCtctDtls().getEmailAdr());
			}

			// creditorAcount information
			p300.setOrgnlCdtrAcctNo(otr.getCdtrAcct().getId().getOthr().getId());
			p300.setOrgnlCdtrAcctNoTp(otr.getCdtrAcct().getId().getOthr().getSchmeNm().getPrtry().value());

			txinf.setP300(p300);
			listTxinf.add(txinf);
		}
		return listTxinf;
	}

}
