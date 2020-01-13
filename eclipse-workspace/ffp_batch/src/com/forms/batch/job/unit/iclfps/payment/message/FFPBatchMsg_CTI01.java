package com.forms.batch.job.unit.iclfps.payment.message;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcti01.FFPCTI01;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;

public class FFPBatchMsg_CTI01 extends FFPMsgBaseParticipantMessage
{
	private FFPJbP110 p110Jb = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public FFPBatchMsg_CTI01(FFPJbP110 p110)
	{
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01;
		this.p110Jb = p110;
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	public HEAD marshalMsgReqHead()
	{
		HEAD head = new HEAD();
		
		head.setRequestID(this.requestID);
		head.setResponseID(this.responseID);
		head.setRequestRefno(FFPStringUtils.isEmptyOrNull(this.reqRefNo) ? FFPIDUtils.getRefno() : reqRefNo);
		head.setTransactionDate(FFPDateUtils.getDateStr(new Date(), FFPDateUtils.INT_DATE_FORMAT));
		head.setTransactionTime(FFPDateUtils.getTimeStr(FFPDateUtils.getTime(new Date()), FFPDateUtils.INT_TIME_FORMAT));
		head.setAccountingDate(FFPDateUtils.getDateStr(new Date(), FFPDateUtils.INT_DATE_FORMAT));
		head.setMessageType(this.msgType);
		return head;
	}

	public BODY marshalMsgReqBody()
	{
		BODY body = null;
		FFPCTI01 cti01 = new FFPCTI01();
		cti01.setSrcRefNm(p110Jb.getTxJnl().getSrcRefNm());
		cti01.setPymtCatPrps(p110Jb.getPymtCatPrps());
		cti01.setSettlDate(p110Jb.getSettlementDate() != null ? sdf.format(p110Jb.getSettlementDate()) : null);
		cti01.setSettlAmt(p110Jb.getSettlementAmount());
		cti01.setSettlCcy(p110Jb.getSettlementCurrency());
		cti01.setCdtrNm(p110Jb.getCreditorName());
		cti01.setCdtrAcTp(p110Jb.getCreditorAccountNumberType());
		cti01.setCdtrAcNo(p110Jb.getCreditorAccountNumber());
		cti01.setCdtrContPhone(p110Jb.getCreditorContPhone());
		cti01.setCdtrContEmailAddr(p110Jb.getCreditorContEmailAddr());
		body = cti01;
		return body;
	}

	public void unmarshalResponseMsg(String ip_responseMsg) throws Exception
	{
		try
		{
			ROOT root = FFPParticipantMessageConverter.parseXml2ReponseObject(ip_responseMsg);
			com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head = root.getHEAD();
			com.forms.ffp.adaptor.jaxb.participant.response.BODY body = root.getBODY();
			com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01 cti01 = (com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01)body;
			
			if(this.reqRefNo.equals(head.getRequestRefno()) && p110Jb.getTxJnl().getSrcRefNm().equals(cti01.getSrcRefNm()))
			{
				//parse response head
				this.requestID = head.getRequestID();
				this.responseID = head.getResponseID();
				this.resRefNo = head.getResponseRefno();
				this.reqRefNo = head.getRequestRefno();
				this.responseSts = head.getResponseStatus();
				this.responseMsgCode = head.getResponseMessageCode();
				this.responseMsg = head.getResponseMessage();
				//parse response body
				//FFPVO_Pacs008_CTI01REPLY reply = new FFPVO_Pacs008_CTI01REPLY();
				//reply.setSrcRefNm(cti01.getSrcRefNm());
				this.rsltCd = cti01.getRsltCd();
				this.rejCd = cti01.getRejCd();
				this.rejMsg = cti01.getRejMsg();
				//reply.setRsltCd(this.rsltCd);
				//if("R".equals(this.rsltCd)){
					//reply.setRejCd(cti01.getRejCd());
					//reply.setRejMsg(cti01.getRejMsg());
				//}
				//msgJb_txInf.setCti01Reply(reply);
			}
			else
			{
				throw new Exception(String.format("Invalid response message from Agent[RequestRefNum = %s] to FFP[OrigRequestRefNum = %s]", head.getRequestRefno(), this.reqRefNo));
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return;
	}
}
