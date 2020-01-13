package com.forms.ffp.bussiness.helper;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.FFPMessageWrapper;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;
import com.forms.ffp.persistents.bean.FFPTxBase;

public class FFPParticipantHelper extends FFPHelper
{
	@Autowired
	private BeanFactory beanFactory;

	public boolean helperParticipant(String inwardorOutward, FFPMessageWrapper ip_wrapper) throws Exception
	{
		FFPParticipantMessageWrapper wrapper = (FFPParticipantMessageWrapper) ip_wrapper;
		
		String msgType = null;
		
		if(wrapper.getRequestRoot() != null)
		{
			HEAD head = wrapper.getRequestRoot().getHEAD();
			msgType = head.getMessageType();
		}else if(wrapper.getResponseRoot() != null)
		{
			com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head = wrapper.getResponseRoot().getHEAD();
			msgType = head.getMessageType();
		}
		String serviceName = FFPConstants.TX_SOURCE_FFPAGENT + "." + msgType;
		FFPTxBase txBase = (FFPTxBase) this.beanFactory.getBean(serviceName);
		if (txBase != null)
		{
			txBase.setServiceName(serviceName);
			txBase.parseParticipantData(wrapper);
			boolean valid = txBase.validate();
			if(valid)
			{
				txBase.perform();
			}
		}
		return true;
	}
}
