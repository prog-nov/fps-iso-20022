package com.forms.ffp.mq.listener;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forms.ffp.mq.config.FFPMqConfig;
import com.forms.ffp.mq.config.FFPMqConfigSvc;
import com.forms.ffp.mq.listener.mq.FFPApacheMqListenerAgent;
import com.forms.ffp.mq.listener.mq.FFPIbmWebSphereMqListenerAgent;
import com.forms.ffp.mq.listener.mq.FFPMqListenerAgentInterface;

@Component("mqListenerAgentSvc")
public class FFPMqListenerAgentSvc
{
	private static Logger _logger = LoggerFactory.getLogger(FFPMqListenerAgentSvc.class);
//重点看能不能注入进来2
	
	private Map<String, FFPMqListenerAgentInterface> mqListenerAgentMap;

	// @Autowired
	// private FileSystemStorageService sentMessageStorageService;
	//
	// @Autowired
	// private BankSimMessageUtil bankSimMsgUtil;
	//
	// private List<SavedSentMessage> sentMessageList = new ArrayList();

//	@Value("${sent.message.waitForAcknowledge.timeout}")
//	private Long waitForAckTimeout;

	// @Autowired
	// private PerformanceLogUtils performanceLogUtils;

	@Autowired
	private BeanFactory beanFactory;

	@Resource(name = "mqConfigSvc")
	private FFPMqConfigSvc mqConfigSvc;

	@PostConstruct
	public void init() throws Exception
	{
		mqListenerAgentMap = new LinkedHashMap<String, FFPMqListenerAgentInterface>();
		Map<String, FFPMqConfig> qmConfigMap = this.mqConfigSvc.getQmConfigMap();
		if (_logger.isInfoEnabled())
		{
			_logger.info("Start initialize MQ Config for Banks: {}", qmConfigMap);
		}

		Iterator<String> iter = qmConfigMap.keySet().iterator();
		while (iter.hasNext())
		{
			String key = iter.next();
			FFPMqConfig mqConfig = qmConfigMap.get(key);
			FFPMqListenerAgentInterface agent = null;
			if("apachemq".equals(mqConfig.getQmCfg().getQueueType()))
			{
				agent = (FFPMqListenerAgentInterface) this.beanFactory.getBean(FFPApacheMqListenerAgent.class, new Object[] { mqConfig });
			}
			else if("ibmmq".equals(mqConfig.getQmCfg().getQueueType()))
			{
				agent = (FFPMqListenerAgentInterface) this.beanFactory.getBean(FFPIbmWebSphereMqListenerAgent.class, new Object[] { mqConfig });
			}
			else
			{
				throw new Exception(mqConfig.getMqName() + " type not define!");
			}
			this.mqListenerAgentMap.put(key, agent);
		}
	}

	public FFPMqListenerAgentInterface getMqAgent(String mqName)
	{
		if (this.mqListenerAgentMap == null)
		{
			return null;
		}

		return this.mqListenerAgentMap.get(mqName);
	}

	// private String saveSentFile(String jmsMsgId, String bankCode,
	// FpsMessageEnvelope fpsMsg, String message, String filename)
	// {
	// if (StringUtils.isEmptyOrNull(filename)) {
	// String ext = "xml";
	//
	// if (fpsMsg == null) {
	// try {
	// XMLUtil.string2Documnet(message);
	// } catch (Exception e) {
	// ext = "txt";
	// }
	// }
	//
	// filename = String.format("%s_%s_%s.%s", new Object[] {
	// bankCode,
	// DateUtils.getTimeStr(new Timestamp(System.currentTimeMillis()),
	// "yyyyMMddHHmmssSSS"),
	// jmsMsgId.substring(jmsMsgId.length() - 10),
	// ext });
	// }
	//
	// try
	// {
	// this.sentMessageStorageService.store(filename, message);
	//
	// SavedSentMessage sentMessage = new SavedSentMessage(fpsMsg, message,
	// bankCode, filename);
	//
	// addSentMessage(sentMessage);
	//
	//
	// Thread th = new Thread(new FFPMqListenerAgentInterfaceSvc.1(this, sentMessage));
	//
	// sentMessage.setTimeoutThread(th);
	//
	// th.start();
	// return filename;
	// }
	// catch (Exception e) {
	// if (_logger.isErrorEnabled())
	// _logger.error("saveSentFile failed", e);
	// }
	// return null;
	// }

	// private void addSentMessage(SavedSentMessage sentMessage)
	// {
	// synchronized (this.sentMessageList)
	// {
	// if (_logger.isInfoEnabled()) {
	// _logger.info(String.format("Wait for ack response started [%s]", new Object[]
	// { sentMessage.getSentFileName() }));
	// }
	// this.sentMessageList.add(sentMessage);
	// }
	// }

	// public void removeSentMessage(SavedSentMessage sentMessage) {
	// synchronized (this.sentMessageList)
	// {
	// if (this.sentMessageList.contains(sentMessage)) {
	// if (_logger.isInfoEnabled()) {
	// _logger.info(String.format("Wait for ack response end for [%s]", new Object[]
	// { sentMessage.getSentFileName() }));
	// }
	// sentMessage.getTimeoutThread().interrupt();
	// this.sentMessageList.remove(sentMessage);
	// }
	// }
	// }
	//
	// public SendMessageResp sendMessageAndSave(String bankCode, String message,
	// String priority, Integer type)
	// {
	// return sendMessageAndSave(bankCode, message, priority, null, type);
	// }
	//
	// public SendMessageResp sendMessageAndSave(String bankCode, String message,
	// String priority, String saveSentFileName, Integer type)
	// {
	// BankSimMqAgentResp agentResp = invokeMqAgent(bankCode, message, priority,
	// type, true);
	//
	// String savedFileName = saveSentFile(agentResp.getJmsMessageId(), bankCode,
	// agentResp.getFpsMsg(), message, saveSentFileName);
	//
	// SendMessageResp resp = new SendMessageResp();
	// resp.setJmsMessageId(agentResp.getJmsMessageId());
	// resp.setSentSysTime(agentResp.getJmsTimestamp());
	// resp.setSavedFileName(savedFileName);
	//
	// return resp;
	// }
	//
	// public SendMessageResp sendMessage(String bankCode, String message, String
	// priority, Integer type)
	// {
	// BankSimMqAgentResp agentResp = invokeMqAgent(bankCode, message, priority,
	// type, false);
	// SendMessageResp resp = new SendMessageResp();
	// resp.setJmsMessageId(agentResp.getJmsMessageId());
	// resp.setSentSysTime(agentResp.getJmsTimestamp());
	//
	// return resp;
	// }
	//
	// private BankSimMqAgentResp invokeMqAgent(String bankCode, String message,
	// String priority, Integer type, boolean parseFpsMsgToResponse) {
	// if (_logger.isDebugEnabled()) {
	// _logger.debug(String.format("Send Message to [%s]", new Object[] { bankCode
	// }));
	// }
	//
	// BankSimMqAgent mgAgent = getBankMqAgent(bankCode);
	//
	// if (mgAgent == null) {
	// throw new RuntimeException(String.format("MQ Agent not found for bank [%s]",
	// new Object[] { bankCode }));
	// }
	//
	//
	// BankSimMqAgentResp resp = null;
	// if (type.intValue() == 1) {
	// resp = mgAgent.sendRequest(message, null, priority);
	// } else {
	// resp = mgAgent.sendAcknowledge(message, null, priority);
	// }
	//
	// if (parseFpsMsgToResponse)
	// {
	// ISO20022BusinessDataV01 bizData = null;
	// try {
	// FpsMessageEnvelope fpsMsg = this.bankSimMsgUtil.parse(message, true);
	// resp.setFpsMsg(fpsMsg);
	// bizData = (ISO20022BusinessDataV01)fpsMsg.getFpsPylds().getBizData().get(0);
	// }
	// catch (Exception localException) {}
	// this.performanceLogUtils.asyncLogMqPerformance(bankCode,
	// type,
	// Integer.valueOf(1),
	// priority,
	// bizData,
	// resp.getJmsTimestamp());
	// }
	// else
	// {
	// this.performanceLogUtils.asyncLogMqPerformance(bankCode,
	// type,
	// Integer.valueOf(1),
	// priority,
	// message,
	// resp.getJmsTimestamp());
	// }
	//
	//
	//
	// return resp;
	// }
	//
	//
	// public Thread sendAsyncMessageAndSave(String bankCode, String message, String
	// priority, int sleepTime, String saveSentFileName, Integer type)
	// {
	// return sendAsyncMessage(bankCode, message, priority, sleepTime, true,
	// saveSentFileName, type);
	// }
	//
	// public Thread sendAsyncMessage(String bankCode, String message, String
	// priority, int sleepTime, boolean save, String saveSentFileName, Integer type)
	// {
	// if (_logger.isDebugEnabled()) {
	// _logger.debug(String.format("Send Message to [%s]. Sleep Time [%s]", new
	// Object[] { bankCode, Integer.valueOf(sleepTime), message }));
	// }
	//
	// if (getBankMqAgent(bankCode) == null) {
	// throw new RuntimeException(String.format("JMS Template not found for bank
	// [%s]", new Object[] { bankCode }));
	// }
	//
	// Thread th = new Thread(new FFPMqListenerAgentInterfaceSvc.2(this, sleepTime, save,
	// bankCode, message, priority, saveSentFileName, type));
	//
	// th.start();
	//
	// return th;
	// }

	public Set<String> getBankCodes()
	{
		if (this.mqListenerAgentMap == null)
		{
			return new HashSet<String>();
		}
		return this.mqListenerAgentMap.keySet();
	}

	// public SavedSentMessage getSentMessage(ISO20022BusinessDataV01 ackBizData,
	// String ackMessage)
	// {
	// synchronized (this.sentMessageList)
	// {
	// if (this.sentMessageList == null) {
	// return null;
	// }
	//
	// for (SavedSentMessage sentMessage : this.sentMessageList) {
	// if (this.bankSimMsgUtil.isAckMessagePairWithSentMessage(ackBizData,
	// sentMessage)) {
	// if (_logger.isDebugEnabled()) {
	// _logger.debug(String.format("getWaitMessageAckThread found [%s]", new
	// Object[] { sentMessage.getSentFileName() }));
	// }
	// return sentMessage;
	// }
	// }
	//
	//
	// if (_logger.isDebugEnabled()) {
	// _logger.debug("getWaitMessageAckThread not found.");
	// }
	//
	// return null;
	// }
	// }
}
