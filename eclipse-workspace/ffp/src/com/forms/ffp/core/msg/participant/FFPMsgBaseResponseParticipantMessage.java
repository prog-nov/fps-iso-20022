package com.forms.ffp.core.msg.participant;

import java.util.Date;

import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;

public class FFPMsgBaseResponseParticipantMessage
{
	private Date createDate;

	// for sub class
	protected String direction;
	
	
	
	protected String requestID;
	protected String TransactionDate;
	protected String TransactionTime;
	protected String reqRefNo;
	protected String responseID;
	protected String msgType;
	protected String resRefNo;
	protected String ResponseBeginTime;
	protected String ResponseEndTime;
	protected String responseSts = "N";
	protected String FinalNode = "2";
	protected String ResponseMessageCode;
	protected String ResponseMessage;

	public FFPMsgBaseResponseParticipantMessage()
	{
		
	}

	public FFPMsgBaseResponseParticipantMessage(FFPTxJnlAction orgAgentMsg)
	{
		this.createDate = new Date();
		
		this.requestID = orgAgentMsg.getMsgSystemId();
		this.TransactionDate = FFPDateUtils.convertDateToString(orgAgentMsg.getMsgCreatTs(), FFPDateUtils.INT_DATE_FORMAT);
		this.TransactionDate = FFPDateUtils.convertDateToString(orgAgentMsg.getMsgCreatTs(), FFPDateUtils.INT_TIME_FORMAT);
		this.reqRefNo = orgAgentMsg.getMsgId();
		this.responseID = FFPConstants.MSG_CODE_FFP;
		this.msgType = orgAgentMsg.getMsgType();
		
		this.ResponseBeginTime = FFPDateUtils.convertDateToString(orgAgentMsg.getMsgProceTs(), FFPDateUtils.INT_TIME_FORMAT);
		this.ResponseEndTime = FFPDateUtils.convertDateToString(createDate, FFPDateUtils.INT_TIME_FORMAT);
	}

	public HEAD marshalMsgResHead()
	{
		HEAD head = new HEAD();
		// RequestID
		// TransactionDate
		// TransactionTime
		// RequestRefno
		// ResponseID
		// MessageType
		// SystemRefno2
		// ResponseRefno
		// ResponseStatus
		// FinalNode

		// optional
		// AccountingDate
		// SystemBeginTIme
		// SystemEndTIme
		// ResponseBeginTime
		// ResponseEndTime
		// SystemMessageCode
		// ResponseMessageCode
		// ResponseMessage

		head.setRequestID(this.requestID);
		head.setTransactionDate(this.TransactionDate);
		head.setTransactionTime(TransactionTime);
		head.setRequestRefno(reqRefNo);
		head.setResponseID(this.responseID);
		head.setMessageType(this.msgType);
		head.setResponseRefno(this.resRefNo);
		head.setResponseBeginTime(ResponseBeginTime);
		head.setResponseEndTime(ResponseEndTime);
		head.setResponseStatus(responseSts);
		head.setFinalNode(FinalNode);
		head.setResponseMessageCode(ResponseMessageCode);
		head.setResponseMessage(ResponseMessage);
		return head;
	}

	public BODY marshalMsgResBody()
	{
		return null;
	}

	public String parseParticipantMessage() throws Exception
	{
		String message = null;
		Object obj = this.marshalMsgRoot();
		ROOT root = (ROOT) obj;
		message = FFPParticipantMessageConverter.packageReponseObject2Xml(root);

		return message;
	}

	public ROOT marshalMsgRoot()
	{

		ROOT root = new ROOT();
		HEAD reqHead = this.marshalMsgResHead();
		BODY reqBody = this.marshalMsgResBody();
		root.setHEAD(reqHead);
		root.setBODY(reqBody);
		return root;
	}

	public void unmarshalResponseMsg(String ip_responseMsg)
	{
		return;
	}

	public String getReqRefNo()
	{
		return this.reqRefNo == null ? FFPIDUtils.getRefno() : this.reqRefNo;
	}

	public void setReqRefNo(String reqRefNo)
	{
		this.reqRefNo = reqRefNo;
	}

	public String getResRefNo()
	{
		return this.resRefNo == null ? FFPIDUtils.getRefno() : this.resRefNo;
	}

	public void setResRefNo(String resRefNo)
	{
		this.resRefNo = resRefNo;
	}

	public String getMsgType()
	{
		return msgType;
	}

	public void setMsgType(String msgType)
	{
		this.msgType = msgType;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public String getRequestID()
	{
		return requestID;
	}

	public void setRequestID(String requestID)
	{
		this.requestID = requestID;
	}

	public String getResponseID()
	{
		return responseID;
	}

	public void setResponseID(String responseID)
	{
		this.responseID = responseID;
	}

	public String getResponseSts() {
		return responseSts;
	}

	public void setResponseSts(String responseSts) {
		this.responseSts = responseSts;
	}

	
}
