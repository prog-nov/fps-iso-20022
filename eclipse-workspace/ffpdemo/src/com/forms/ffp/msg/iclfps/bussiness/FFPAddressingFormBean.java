package com.forms.ffp.msg.iclfps.bussiness;

import java.util.List;

public class FFPAddressingFormBean extends FFPBaseBussinessBean
{
	private List<FFPAddressingRegistrationBean> addressingRegistrationList;
	private List<FFPAddressingAmendmentBean> addressingAmendmentList;
	private List<FFPAddressingCancellationBean> addressingCancellationList;
	private FFPAddressingRegistrationSummaryBean addressingRegistrationSummaryBean;
	private List<FFPAddressingEnquiryBean> addressingEnquiryList;

	public List<FFPAddressingRegistrationBean> getAddressingRegistrationList()
	{
		return this.addressingRegistrationList;
	}

	public void setAddressingRegistrationList(List<FFPAddressingRegistrationBean> addressingRegistrationList)
	{
		this.addressingRegistrationList = addressingRegistrationList;
	}

	public List<FFPAddressingAmendmentBean> getAddressingAmendmentList()
	{
		return this.addressingAmendmentList;
	}

	public void setAddressingAmendmentList(List<FFPAddressingAmendmentBean> addressingAmendmentList)
	{
		this.addressingAmendmentList = addressingAmendmentList;
	}

	public List<FFPAddressingCancellationBean> getAddressingCancellationList()
	{
		return this.addressingCancellationList;
	}

	public void setAddressingCancellationList(List<FFPAddressingCancellationBean> addressingCancellationList)
	{
		this.addressingCancellationList = addressingCancellationList;
	}

	public FFPAddressingRegistrationSummaryBean getAddressingRegistrationSummaryBean()
	{
		return this.addressingRegistrationSummaryBean;
	}

	public void setAddressingRegistrationSummaryBean(FFPAddressingRegistrationSummaryBean addressingRegistrationSummaryBean)
	{
		this.addressingRegistrationSummaryBean = addressingRegistrationSummaryBean;
	}

	public List<FFPAddressingEnquiryBean> getAddressingEnquiryList()
	{
		return this.addressingEnquiryList;
	}

	public void setAddressingEnquiryList(List<FFPAddressingEnquiryBean> addressingEnquiryList)
	{
		this.addressingEnquiryList = addressingEnquiryList;
	}

	public int getCount()
	{
		int count = 0;
		count += countBean(this.addressingRegistrationList);
		count += countBean(this.addressingAmendmentList);
		count += countBean(this.addressingCancellationList);
		count += countBean(this.addressingRegistrationSummaryBean);
		count += countBean(this.addressingEnquiryList);
		return count;
	}
}
