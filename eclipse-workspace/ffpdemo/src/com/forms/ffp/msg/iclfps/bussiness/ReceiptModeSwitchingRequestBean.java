package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;

public class ReceiptModeSwitchingRequestBean extends FFPBaseBussinessDataBean
{
	@FFPValidateMessage(field = "Receipt Mode")
	private String receiptMode;

	public String getReceiptMode()
	{
		return this.receiptMode;
	}

	public void setReceiptMode(String receiptMode)
	{
		this.receiptMode = receiptMode;
	}
}
