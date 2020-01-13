package com.forms.ffp.msg.iclfps.bussiness;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;

public class MandateBaseBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Mandate ID")
	private String mandateId;
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Mandate Request ID")
	private String mandateRequestId;
	@FFPValidateMessage(field = "Mandate Type Code")
	private String mandateTypeCode;
	@FFPValidateMessage(field = "Transaction Sequence")
	private String transactionSequenceType;
	@FFPValidateMessage(field = "Period Type")
	private String periodType;
	@FFPValidateMessage(field = "Count Per Period")
	private String countPerPeriod;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FFPValidateMessage(field = "Duration From Date")
	private Date durationFromDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FFPValidateMessage(field = "Duration To Date")
	private Date durationToDate;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Tracking Indicator")
	private String trackingIndicator;
	@FFPValidateMessage(field = "Collection Amount")
	private BigDecimal collectionAmount;
	@FFPValidateMessage(field = "Collection Currency")
	private String collectionCurrency;
	@FFPValidateMessage(field = "Maximum Amount")
	private BigDecimal maximumAmount;
	@FFPValidateMessage(field = "Maximum Currency")
	private String maximumCurrency;
	@FFPValidateMessage(field = "Creditor Name")
	private String creditorName;
	@FFPValidateMessage(field = "Debtor Name")
	private String debtorName;
	@FFPValidateMessage(field = "Ultimate Debtor Name")
	private String ultimateDebtorName;
	@FFPValidateMessage(field = "Creditor Reference")
	private String creditorReference;

	public String getMandateId()
	{
		return this.mandateId;
	}

	public void setMandateId(String mandateId)
	{
		this.mandateId = mandateId;
	}

	public String getMandateRequestId()
	{
		return this.mandateRequestId;
	}

	public void setMandateRequestId(String mandateRequestId)
	{
		this.mandateRequestId = mandateRequestId;
	}

	public String getMandateTypeCode()
	{
		return this.mandateTypeCode;
	}

	public void setMandateTypeCode(String mandateTypeCode)
	{
		this.mandateTypeCode = mandateTypeCode;
	}

	public String getTransactionSequenceType()
	{
		return this.transactionSequenceType;
	}

	public void setTransactionSequenceType(String transactionSequenceType)
	{
		this.transactionSequenceType = transactionSequenceType;
	}

	public String getPeriodType()
	{
		return this.periodType;
	}

	public void setPeriodType(String periodType)
	{
		this.periodType = periodType;
	}

	public String getCountPerPeriod()
	{
		return this.countPerPeriod;
	}

	public void setCountPerPeriod(String countPerPeriod)
	{
		this.countPerPeriod = countPerPeriod;
	}

	public Date getDurationFromDate()
	{
		return this.durationFromDate;
	}

	public void setDurationFromDate(Date durationFromDate)
	{
		this.durationFromDate = durationFromDate;
	}

	public Date getDurationToDate()
	{
		return this.durationToDate;
	}

	public void setDurationToDate(Date durationToDate)
	{
		this.durationToDate = durationToDate;
	}

	public String getTrackingIndicator()
	{
		return this.trackingIndicator;
	}

	public void setTrackingIndicator(String trackingIndicator)
	{
		this.trackingIndicator = trackingIndicator;
	}

	public BigDecimal getCollectionAmount()
	{
		return this.collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount)
	{
		this.collectionAmount = collectionAmount;
	}

	public String getCollectionCurrency()
	{
		return this.collectionCurrency;
	}

	public void setCollectionCurrency(String collectionCurrency)
	{
		this.collectionCurrency = collectionCurrency;
	}

	public BigDecimal getMaximumAmount()
	{
		return this.maximumAmount;
	}

	public void setMaximumAmount(BigDecimal maximumAmount)
	{
		this.maximumAmount = maximumAmount;
	}

	public String getMaximumCurrency()
	{
		return this.maximumCurrency;
	}

	public void setMaximumCurrency(String maximumCurrency)
	{
		this.maximumCurrency = maximumCurrency;
	}

	public String getCreditorName()
	{
		return this.creditorName;
	}

	public void setCreditorName(String creditorName)
	{
		this.creditorName = creditorName;
	}

	public String getDebtorName()
	{
		return this.debtorName;
	}

	public void setDebtorName(String debtorName)
	{
		this.debtorName = debtorName;
	}

	public String getUltimateDebtorName()
	{
		return this.ultimateDebtorName;
	}

	public void setUltimateDebtorName(String ultimateDebtorName)
	{
		this.ultimateDebtorName = ultimateDebtorName;
	}

	public String getCreditorReference()
	{
		return this.creditorReference;
	}

	public void setCreditorReference(String creditorReference)
	{
		this.creditorReference = creditorReference;
	}
}

/*
 * Location: C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\
 * ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\
 * MandateBaseBean.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */