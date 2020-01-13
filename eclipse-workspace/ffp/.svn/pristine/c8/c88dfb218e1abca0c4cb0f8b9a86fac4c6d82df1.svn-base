package com.forms.ffp.core.connector.listener.msg;

import java.net.Socket;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Node;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.bussiness.helper.FFPHkiclHelper;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.FFPMessageWrapper;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.ffp.core.msg.iclfps.FFPISO20022MessageWrapper;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.dt.FFPDtECert;
import com.forms.ffp.persistents.dao.dt.FFPIDao_ECert;

public class FFPHkicl2FfpAckMsgListener extends FFPBaseMsgListener
{
	private static Logger _logger = LoggerFactory.getLogger(FFPHkicl2FfpAckMsgListener.class);

	@Autowired
	private FFPHkiclHelper helper;
	
	@Resource(name = "FFPIDao_ECert")
	private FFPIDao_ECert eCertDao;

	@Override
	public boolean onValidateSignature(TextMessage message, boolean ignoreException) throws Exception
	{
		try
		{
			org.w3c.dom.Document doc = FFPXMLUtils.string2Documnet(message.getText());
			org.w3c.dom.NodeList signatureNodeList = FFPHkiclMessageConverter.getSignatureNodeList(doc);
			if (_logger.isInfoEnabled())
			{
				_logger.info("validateMessageSignature >> Node List extracted. Start validatie XML.");
			}

			boolean signatureCheck = FFPHkiclMessageConverter.validateDsignXML(signatureNodeList);
			
			boolean ecertValid = true;
			for (int k = 0; k < signatureNodeList.getLength(); k++)
			{
				org.w3c.dom.Node node = signatureNodeList.item(k);
				Node KeyInfo = node.getOwnerDocument().getElementsByTagName("KeyInfo").item(0);
				Node X509Data = KeyInfo.getOwnerDocument().getElementsByTagName("X509Data").item(0);
				Node X509Certificate = X509Data.getOwnerDocument().getElementsByTagName("X509Certificate").item(0);
				String loc_cert = X509Certificate.getTextContent();
				List<FFPDtECert> eCertList = eCertDao.dFindValidECert(FFPConstants.RELATION_SYSTEM_HKICL, loc_cert);
				if(eCertList != null && eCertList.size() > 0)
					ecertValid &= true;
				else
					ecertValid &= false;
			}
			
			return signatureCheck && ecertValid;
		} catch (Exception e)
		{
			if (_logger.isErrorEnabled())
			{
				_logger.warn("Validate Message Signature failed with exception.:", e);
			}
			return false;
		}
	}

	@Override
	public FFPMessageWrapper onMessageParse(TextMessage message, String queueName, String priority) throws Exception
	{
		FFPISO20022MessageWrapper msgWrapper = new FFPISO20022MessageWrapper();
		msgWrapper.setQueueName(queueName);
		msgWrapper.setReceivedTimestamp(Long.valueOf(System.currentTimeMillis()));
		msgWrapper.setMessage((TextMessage) message);
		msgWrapper.setPriority(priority);

		FpsMessageEnvelope fpsMsg = null;
		try
		{
			TextMessage msg = (TextMessage) message;
			String messageContent = msg.getText();
			fpsMsg = FFPHkiclMessageConverter.parseObject(messageContent);
			msgWrapper.setFpsMsg(fpsMsg);
		} catch (Exception e)
		{
			_logger.error(String.format("[%s]>> Failed to parse message", new Object[] { queueName }), e);
			throw e;
		}
		return msgWrapper;
	}

	@Override
	public void handleMessage(FFPMessageWrapper warpper)
	{
		FFPISO20022MessageWrapper msgWrapper = (FFPISO20022MessageWrapper) warpper;
		FpsMessageEnvelope fpsMsg = msgWrapper.getFpsMsg();

		try
		{
			if ((fpsMsg != null) && (fpsMsg.getFpsPylds() != null) && (fpsMsg.getFpsPylds().getBizData() != null))
			{
				for (ISO20022BusinessDataV01 bizData : fpsMsg.getFpsPylds().getBizData())
				{
					JAXBElement<?> headElement = bizData.getContent().get(0);
					BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01) headElement.getValue();
					if ((head == null) && (_logger.isWarnEnabled()))
					{
						_logger.warn("Skip handling message due to application header is null");
					} else
					{
						_logger.debug(String.format("%s>> handling started.", new Object[] { head.getBizMsgIdr() }));
						this.helper.helperHkicl("inward", bizData, msgWrapper);
					}
				}
			} else
			{
				_logger.error(String.format("[%s]>> bizData is null", new Object[] { msgWrapper.getQueueName() }));
				return;
			}
		} catch (Exception e)
		{
			_logger.error(String.format("[%s]>> Failed to handler message.", new Object[] { msgWrapper.getQueueName() }), e);
		}
	}

	@Override
	public FFPMessageWrapper onTcpMessageParse(String message, Socket socket) throws Exception
	{
		return null;
	}
}
