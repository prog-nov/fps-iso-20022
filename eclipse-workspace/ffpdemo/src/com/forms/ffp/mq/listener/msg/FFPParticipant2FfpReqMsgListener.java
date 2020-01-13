package com.forms.ffp.mq.listener.msg;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.bussiness.helper.FFPParticipantHelper;
import com.forms.ffp.msg.participant.FFPParticipantMessageAdaptor;
import com.forms.ffp.msg.participant.FFPParticipantMessageWrapper;
@Component("participant2FfpReqMsgListener")
public class FFPParticipant2FfpReqMsgListener extends FFPBaseMsgListener
{
	private static Logger _logger = LoggerFactory.getLogger(FFPParticipant2FfpReqMsgListener.class);

	@Resource(name = "participantHelper")
	private FFPParticipantHelper helper;
	
	private FFPParticipantMessageWrapper msgWrapper;

	@Override
	public boolean onValidateSignature(TextMessage message, boolean ignoreException) throws Exception
	{
		try
		{
			//TODO
//			Document doc = FFPXMLUtils.string2Documnet(message.getText());
//			NodeList signatureNodeList = FFPMessageAdaptor.getSignatureNodeList(doc);
//			if (_logger.isInfoEnabled())
//			{
//				_logger.info("validateMessageSignature >> Node List extracted. Start validatie XML.");
//			}
//
//			return FFPMessageAdaptor.validateDsignXML(signatureNodeList);
			return true;
		} catch (Exception e)
		{
			if (_logger.isErrorEnabled())
			{
				_logger.error("Validate Message Signature failed with exception. {} : {}", new Object[] { e.getClass().getSimpleName(), e.getMessage(), e });
			}

			if (ignoreException)
			{
				return false;
			}
			throw e;
		}
	}

	@Override
	public void onMessageParse(TextMessage message, String mqName) throws Exception
	{
		msgWrapper = new FFPParticipantMessageWrapper();
		msgWrapper.setMqName(mqName);
		msgWrapper.setReceivedTimestamp(Long.valueOf(System.currentTimeMillis()));
		msgWrapper.setMessage((TextMessage) message);

		ROOT root = null;
		
		try
		{
			TextMessage msg = (TextMessage) message;
			String messageContent = msg.getText();
			System.out.println("----------------receive from participant：------------------");
			System.out.println(messageContent);
			System.out.println("------------------------------------------------------------");
			
//			//取业务编号
//			String codeStr = messageContent.substring(0, messageContent.indexOf('\t'));
//			//取xml字符串
//			String messageContentXmlStr = messageContent.substring(messageContent.indexOf("<?"));
			//将字符串解析成对象
			root = FFPParticipantMessageAdaptor.parseObject(messageContent);
			msgWrapper.setRoot(root);
//			FFPXMLUtils.string2Documnet(messageContent);
//			fpsMsg = FFPMessageAdaptor.parse(messageContent);
//			msgWrapper.setFpsMsg(fpsMsg);
			
		} catch (Exception e)
		{
			_logger.error(String.format("[BANK %s]>> Failed to parse message", new Object[] { mqName }), e);
			// TODO
			// onMessageParseFailed(e);
			return;
		}
	}

	@Override
	public void handleMessage() throws Exception
	{
		this.helper.helperParticipant("inward", msgWrapper.getRoot());
	}
}
