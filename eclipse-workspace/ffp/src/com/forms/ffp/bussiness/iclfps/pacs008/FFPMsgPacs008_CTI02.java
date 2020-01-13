package com.forms.ffp.bussiness.iclfps.pacs008;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcti02.FFPCTI02;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.core.utils.FFPStringUtils;

public class FFPMsgPacs008_CTI02 extends FFPMsgBaseParticipantMessage
{
	private FFPVO_Pacs008_CdtTrfTxInf msgJb_txInf = null;

	public FFPMsgPacs008_CTI02(FFPVO_Pacs008 pacs008, int ip_i)
	{
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI02;
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
		head.setRequestRefno(createRefNo(this.reqRefNo));
		head.setTransactionDate(FFPDateUtils.getDateStr(new Date(), FFPDateUtils.INT_DATE_FORMAT));
		head.setTransactionTime(FFPDateUtils.getTimeStr(FFPDateUtils.getTime(new Date()), FFPDateUtils.INT_TIME_FORMAT));
		head.setMessageType(this.msgType);
		return head;
	}

	private String createRefNo(String reqRefNo)
	{
		if (FFPStringUtils.isEmptyOrNull(reqRefNo))
		{
			this.reqRefNo = FFPIDUtils.getRefno();
		}
		return this.reqRefNo;
	}

	public BODY marshalMsgReqBody()
	{
		BODY body = null;
		FFPCTI02 cti02 = new FFPCTI02();
		cti02.setSrcRefNm(msgJb_txInf.getP110Jb().getTxJnl().getSrcRefNm());
		cti02.setCdtrNm(msgJb_txInf.getCdtrNm());
		cti02.setCdtrAcTp(msgJb_txInf.getCdtrAcctSchmeNm());
		cti02.setCdtrAcNo(msgJb_txInf.getCdtrAcctId());
		body = cti02;
		return body;
	}

	public void unmarshalResponseMsg(String ip_responseMsg)
	{
		try
		{
			ROOT root = FFPParticipantMessageConverter.parseXml2ReponseObject(ip_responseMsg);
			com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head = root.getHEAD();
			com.forms.ffp.adaptor.jaxb.participant.response.BODY body = root.getBODY();
			com.forms.ffp.adaptor.jaxb.participant.response.ffpcti02.FFPCTI02 cti02 = (com.forms.ffp.adaptor.jaxb.participant.response.ffpcti02.FFPCTI02)body;
			
			if(msgJb_txInf.getP110Jb().getTxJnl().getSrcRefNm().equals(cti02.getSrcRefNm()))
			{
				//parse response head
				this.requestID = head.getRequestID();
				this.responseID = head.getResponseID();
				super.responseMsg = head.getResponseMessage();
				super.responseMsgCode = head.getResponseMessageCode();
				
				this.respMsgCreateTs = new SimpleDateFormat("yyyyMMddHHmmss").parse(head.getTransactionDate()+head.getTransactionTime());
				
				//parse response body
				FFPVO_Pacs008_CTI02REPLY reply = new FFPVO_Pacs008_CTI02REPLY();
				reply.setSrcRefNm(cti02.getSrcRefNm());
				this.rsltCd = cti02.getRsltCd();
				reply.setRsltCd(this.rsltCd);
				if("R".equals(this.rsltCd)){
					this.rejCd = cti02.getRejCd();
					this.rejMsg = cti02.getRejMsg();
					reply.setRejCd(cti02.getRejCd());
					reply.setRejMsg(cti02.getRejMsg());
				}
				msgJb_txInf.setCti02Reply(reply);
			}
			else
			{
				//TODO
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
}
