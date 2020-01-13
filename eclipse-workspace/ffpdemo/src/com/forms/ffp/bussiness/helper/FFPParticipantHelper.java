package com.forms.ffp.bussiness.helper;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.config.FFPRuntimeConfigSvc;
import com.forms.ffp.config.PropertiesFile;
import com.forms.ffp.framework.define.FFPConstants;
@Component("participantHelper")
public class FFPParticipantHelper extends FFPHelper {
	@Resource(name = "runtimeConfigSvc")
	private FFPRuntimeConfigSvc runtimeConfigSvc;
	@Autowired
	private BeanFactory beanFactory;

	public boolean helperParticipant(String inwardorOutward, ROOT root) throws Exception {
		PropertiesFile proFile = this.runtimeConfigSvc.getPropertiesFile(FFPConstants.TX_MAPPING_PROPERTIES_FILE);

		String txClass = proFile.get(FFPConstants.TX_SOURCE_PARTICIPANT + ".cti01.txclass");
		FFPTxBase txBase = (FFPTxBase) this.beanFactory.getBean(txClass);
		if (txBase != null) {
			txBase.setServiceName(FFPConstants.TX_SOURCE_PARTICIPANT + ".cti01");
			txBase.parseParticipantFrmData(root);
			txBase.validate();
			txBase.perform();
		}
		return true;
	}
}
