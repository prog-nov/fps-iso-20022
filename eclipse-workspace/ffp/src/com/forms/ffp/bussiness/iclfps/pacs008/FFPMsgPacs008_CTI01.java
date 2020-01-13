package com.forms.ffp.bussiness.iclfps.pacs008;

import java.math.BigDecimal;
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

public class FFPMsgPacs008_CTI01 extends FFPMsgBaseParticipantMessage
{
	private FFPVO_Pacs008_CdtTrfTxInf msgJb_txInf = null;

	public FFPMsgPacs008_CTI01(FFPVO_Pacs008 pacs008, int ip_i)
	{
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01;
//		this.pacs008 = pacs008;
		this.msgJb_txInf = pacs008.getCdtTrfTxInfList().get(ip_i);
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	public HEAD marshalMsgReqHead()
	{
		HEAD head = new HEAD();
		// RequestID
		// TransactionDate
		// TransactionTime
		// RequestRefno
		// ResponseID
		// MessageType

		// optional
		// SystemRefno
		// AccountingDate
		head.setRequestID(this.requestID);
		head.setResponseID(this.responseID);
		head.setRequestRefno(FFPStringUtils.isEmptyOrNull(this.reqRefNo) ? FFPIDUtils.getRefno() : reqRefNo);
		head.setTransactionDate(FFPDateUtils.getDateStr(new Date(), FFPDateUtils.INT_DATE_FORMAT));
		head.setTransactionTime(FFPDateUtils.getTimeStr(FFPDateUtils.getTime(new Date()), FFPDateUtils.INT_TIME_FORMAT));
		head.setMessageType(this.msgType);
		return head;
	}

	public BODY marshalMsgReqBody()
	{
		BODY body = null;
		FFPCTI01 cti01 = new FFPCTI01();
		cti01.setSrcRefNm(msgJb_txInf.getP110Jb().getTxJnl().getSrcRefNm());
		cti01.setPymtCatPrps(msgJb_txInf.getCtgyPurp());
		cti01.setSettlDate(FFPDateUtils.convertDateToString(msgJb_txInf.getIntrBkSttlmDt(), FFPDateUtils.INT_DATE_FORMAT));
		cti01.setSettlAmt(new BigDecimal(msgJb_txInf.getIntrBkSttlmAmt()));
		cti01.setSettlCcy(msgJb_txInf.getIntrBkSttlmAmtCcy());
		cti01.setCdtrNm(msgJb_txInf.getCdtrNm());
		cti01.setCdtrAcTp(msgJb_txInf.getCdtrAcctSchmeNm());
		cti01.setCdtrAcNo(msgJb_txInf.getCdtrAcctId());
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
			
			if(msgJb_txInf.getP110Jb().getTxJnl().getSrcRefNm().equals(cti01.getSrcRefNm()))
			{
				//parse response head
				this.requestID = head.getRequestID();
				this.responseID = head.getResponseID();
				super.responseMsg = head.getResponseMessage();
				super.responseMsgCode = head.getResponseMessageCode();
				
				this.respMsgCreateTs = new SimpleDateFormat("yyyyMMddHHmmss").parse(head.getTransactionDate()+head.getTransactionTime());
				
				//parse response body
				FFPVO_Pacs008_CTI01REPLY reply = new FFPVO_Pacs008_CTI01REPLY();
				reply.setSrcRefNm(cti01.getSrcRefNm());
				this.rsltCd = cti01.getRsltCd();
				reply.setRsltCd(this.rsltCd);
				if("R".equals(this.rsltCd)){
					this.rejCd = cti01.getRejCd();
					this.rejMsg = cti01.getRejMsg();
					reply.setRejCd(cti01.getRejCd());
					reply.setRejMsg(cti01.getRejMsg());
				}
				msgJb_txInf.setCti01Reply(reply);
			}
			else
			{
				//TODO
			}
		} catch (Exception e)
		{
			throw new Exception();
		}
		return;
	}
}
