package com.forms.ffp.bussiness.helper;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.iclfps.FFPISO20022MessageWrapper;
import com.forms.ffp.persistents.bean.FFPTxBase;

public class FFPHkiclHelper extends FFPHelper
{
	@Autowired
	private BeanFactory beanFactory;
	
	public boolean helperHkicl(String inwardorOutward, ISO20022BusinessDataV01 bizData, FFPISO20022MessageWrapper wrapper) throws Exception
	{
		BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01)bizData.getContent().get(0).getValue();

		String msgtype = head.getMsgDefIdr();
		String serviceName = FFPConstants.TX_SOURCE_HKICL + "." + msgtype;
		FFPTxBase txBase = (FFPTxBase) this.beanFactory.getBean(serviceName);
		if (txBase != null)
		{
			txBase.setServiceName(serviceName);
			txBase.parseISO20022BizData(bizData);
			txBase.setFFPISO20022MessageWrapper(wrapper);
			boolean valid = txBase.validate();
			if(valid)
			{
				txBase.perform();
			}
		}
		return true;
	}
}
