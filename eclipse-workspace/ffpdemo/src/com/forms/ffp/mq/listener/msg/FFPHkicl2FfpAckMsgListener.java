package com.forms.ffp.mq.listener.msg;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.helper.FFPHkiclHelper;
import com.forms.ffp.msg.iclfps.FFPHkiclMessageAdaptor;
import com.forms.ffp.msg.iclfps.FFPISO20022MessageWrapper;
import com.forms.ffp.msg.iclfps.FFPMessageAdaptor;
import com.forms.ffp.utils.FFPXMLUtils;
@Component("hkicl2FfpAckMsgListener")
public class FFPHkicl2FfpAckMsgListener extends FFPBaseMsgListener {
	private static Logger _logger = LoggerFactory.getLogger(FFPHkicl2FfpAckMsgListener.class);
//觀察一下看能不能注入进来
	@Value("${received.message.folder}")
	private String receivedMessageFolder;

	private FFPISO20022MessageWrapper msgWrapper;

	@Autowired
	private FFPHkiclHelper helper;

	@Override
	public void onMessageParse(TextMessage message, String mqName) throws Exception {
		
		msgWrapper = new FFPISO20022MessageWrapper();
		msgWrapper.setMqName(mqName);
		msgWrapper.setReceivedTimestamp(Long.valueOf(System.currentTimeMillis()));
		msgWrapper.setMessage((TextMessage) message);
		
		FpsMessageEnvelope fpsMsg = null;

		try {
			TextMessage msg = (TextMessage) message;
			String messageContent = msg.getText();
			//add by yrd HKICL ack 消息内容
			System.out.println("--------------receive from: HKICL ack--------------------");
			System.out.println(messageContent);
			System.out.println("-----------------------------------------------------");

			FFPXMLUtils.string2Documnet(messageContent);
			fpsMsg = FFPMessageAdaptor.parse(messageContent);
			msgWrapper.setFpsMsg(fpsMsg);

		} catch (Exception e) {
			_logger.error(String.format("[BANK %s]>> Failed to parse message", new Object[] { mqName }), e);
			// TODO
			// onMessageParseFailed(e);
			return;
		}
	}

	@Override
	public boolean onValidateSignature(TextMessage message, boolean ignoreException) {
		return true;
	}

	public void handleMessage() {

		FpsMessageEnvelope fpsMsg = msgWrapper.getFpsMsg();

		try {
			if ((fpsMsg != null) && (fpsMsg.getFpsPylds() != null) && (fpsMsg.getFpsPylds().getBizData() != null)) {
				for (ISO20022BusinessDataV01 bizData : fpsMsg.getFpsPylds().getBizData()) {
					if ((bizData.getAppHdr() == null) && (_logger.isWarnEnabled())) {
						_logger.warn("Skip handling message due to application header is null");
					} else {
						if (_logger.isDebugEnabled()) {
							_logger.debug(String.format("%s>> handling started.",
									new Object[] { bizData.getAppHdr().getBizMsgIdr() }));
						}
						// write mq log
						// this.performanceLogUtils.asyncLogMqPerformance(msgWrapper.getClearingCode(),
						// Integer.valueOf(1), Integer.valueOf(2),
						// msgWrapper.getPriority(), bizData, sysTime);
						if (!FFPHkiclMessageAdaptor.isRealtimeMessage(bizData)) {
							if (_logger.isDebugEnabled()) {
								_logger.debug(String.format("%s>> No response message due to not real time message.",
										new Object[] { bizData.getAppHdr().getBizMsgIdr() }));
							}
						} else // 实时消息
						{
							this.helper.helperHkicl("inward", bizData);
						}
					}
				}
			} else {
				if (_logger.isErrorEnabled()) {
					_logger.error(String.format("[BANK %s]>> bizData is null",
							new Object[] { msgWrapper.getClearingCode() }));
				}
				return;
			}
		} catch (Exception e) {
			if (_logger.isErrorEnabled()) {
				_logger.error(String.format("[BANK %s]>> Failed to handler message.",
						new Object[] { msgWrapper.getClearingCode() }), e);
			}
		}
	}
}
