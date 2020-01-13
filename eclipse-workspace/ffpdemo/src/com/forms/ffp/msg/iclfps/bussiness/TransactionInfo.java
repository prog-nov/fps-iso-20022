package com.forms.ffp.msg.iclfps.bussiness;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;

public class TransactionInfo implements Serializable
{
	@FFPValidateMessage(field = "Acceptance Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date acceptanceDate;
	@FFPValidateMessage(field = "Clearing System Reference")
	private String clearingSystemReference;
	@FFPValidateMessage(field = "Original End to End ID")
	private String oEndToEndId;
	@FFPValidateMessage(field = "Original Transaction")
	private String oTransactionId;
	@FFPValidateMessage(field = "Reason")
	private String reason;
	@FFPValidateMessage(field = "Additional Information")
	private String additionalInformation;
	@FFPValidateMessage(field = "Transaction Status")
	private String transactionStatus;

	public Date getAcceptanceDate()
	{
		return this.acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate)
	{
		this.acceptanceDate = acceptanceDate;
	}

	public String getClearingSystemReference()
	{
		return this.clearingSystemReference;
	}

	public void setClearingSystemReference(String clearingSystemReference)
	{
		this.clearingSystemReference = clearingSystemReference;
	}

	public String getoEndToEndId()
	{
		return this.oEndToEndId;
	}

	public void setoEndToEndId(String oEndToEndId)
	{
		this.oEndToEndId = oEndToEndId;
	}

	public String getoTransactionId()
	{
		return this.oTransactionId;
	}

	public void setoTransactionId(String oTransactionId)
	{
		this.oTransactionId = oTransactionId;
	}

	public String getReason()
	{
		return this.reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getAdditionalInformation()
	{
		return this.additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

	public String getTransactionStatus()
	{
		return this.transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus)
	{
		this.transactionStatus = transactionStatus;
	}
}

/*
 * Location: C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\
 * ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\
 * TransactionInfo.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */