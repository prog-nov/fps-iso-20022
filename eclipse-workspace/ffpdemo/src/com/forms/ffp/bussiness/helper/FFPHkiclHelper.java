package com.forms.ffp.bussiness.helper;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.config.FFPRuntimeConfigSvc;
import com.forms.ffp.config.PropertiesFile;
import com.forms.ffp.framework.define.FFPConstants;
@Component("hkiclHelper")
public class FFPHkiclHelper extends FFPHelper
{
	@Resource(name = "runtimeConfigSvc")
	private FFPRuntimeConfigSvc runtimeConfigSvc;
	@Autowired
	private BeanFactory beanFactory;
	
	public boolean helperHkicl(String inwardorOutward, ISO20022BusinessDataV01 bizData) throws Exception
	{
		PropertiesFile proFile = this.runtimeConfigSvc.getPropertiesFile(FFPConstants.TX_MAPPING_PROPERTIES_FILE);
		
		String msgtype = bizData.getAppHdr().getMsgDefIdr();
		String txClass = proFile.get(FFPConstants.TX_SOURCE_HKICL + "." + msgtype + ".txclass");
		FFPTxBase txBase = (FFPTxBase) this.beanFactory.getBean(txClass);

		if (txBase != null) {
			txBase.setServiceName("rrs." + msgtype);
			txBase.parseISO20022BizData(bizData);
			txBase.validate();
			txBase.perform();
		}
		return true;
	}
}
