package com.forms.ffp.core.connecor.sender;

import java.util.HashMap;
import java.util.Map;

import com.forms.ffp.core.config.FFPConnectorConfig;
import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.config.tcp.FFPTcpConfig;
import com.forms.ffp.core.connecor.sender.mq.FFPApacheMqSenderAgent;
import com.forms.ffp.core.connecor.sender.mq.FFPIbmWebSphereMqSenderAgent;
import com.forms.ffp.core.connecor.sender.tcp.FFPTcpSenderAgent;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.service.FFPConnectorConfigSvc;

public class FFPSenderAgentSvc
{
	private static Map<String, FFPSenderAgentInterface> senderAgentMap;

	public static FFPSenderAgentInterface getSenderAgent(String senderName) throws Exception
	{
		if(senderAgentMap == null)
		{
			senderAgentMap = new HashMap<String, FFPSenderAgentInterface>();
		}
		if(senderAgentMap.get(senderName) == null)
		{
			FFPConnectorConfig config = FFPConnectorConfigSvc.getInstance().getConnectorConfigMap().get(senderName);
			if(FFPConstants.CONNECTTOR_TYPE_MQ_APACHEMQ.equals(config.getConnectorType())){
				FFPApacheMqSenderAgent agent = new FFPApacheMqSenderAgent((FFPMqConfig)config);
				senderAgentMap.put(senderName, agent);
			}else if(FFPConstants.CONNECTTOR_TYPE_MQ_WEBPHEREMQ.equals(config.getConnectorType())){
				FFPIbmWebSphereMqSenderAgent agent = new FFPIbmWebSphereMqSenderAgent((FFPMqConfig)config);
				senderAgentMap.put(senderName, agent);
			}else if(FFPConstants.CONNECTTOR_TYPE_TCP.equals(config.getConnectorType())){
				FFPTcpSenderAgent agent = new FFPTcpSenderAgent((FFPTcpConfig)config);
				senderAgentMap.put(senderName, agent);
			}else{
				return null;
			}
		}
		return senderAgentMap.get(senderName);
	}
}
