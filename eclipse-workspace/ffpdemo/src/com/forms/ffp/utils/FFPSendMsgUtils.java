package com.forms.ffp.utils;

import java.util.Iterator;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.forms.ffp.mq.config.FFPMqConfig;
import com.forms.ffp.mq.config.FFPMqConfigSvc;
import com.forms.ffp.mq.config.FFPQueueConfig;

public class FFPSendMsgUtils {

	public static void sendMsg(String message, String destination) throws Exception {

		// 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		// brokerURL服务器的ip及端口号
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		// 第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
		// 第三步：开启连接，调用Connection对象的start方法。
		connection.start();
		// 第四步：使用Connection对象创建一个Session对象。
		// 第一个参数：是否开启事务。true：开启事务，第二个参数忽略。
		// 第二个参数：当第一个参数为false时，才有意义。消息的应答模式。1、自动应答2、手动应答。一般是自动应答。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
		// 参数：队列的名称。
		Queue queue = session.createQueue(destination);
		// 第六步：使用Session对象创建一个Producer对象。
		MessageProducer producer = session.createProducer(queue);
		// 第七步：创建一个Message对象，创建一个TextMessage对象。
		TextMessage textMessage = session.createTextMessage(message);
		// 第八步：使用Producer对象发送消息。
		producer.send(textMessage);

		// 第九步：关闭资源。
		producer.close();
		session.close();
		connection.close();
	}
	

	public static String selectQueue(String msg, String groupName, String type, String level) {
		// TODO 设置默认值，后期有需要再改
		if (FFPStringUtils.isEmptyOrNull(groupName)) {
			groupName = "ffp.to.hkicl.sim";
		}

		// 根据类型拼接ack
		String queueNameStr = type + "." + level;
		FFPMqConfigSvc mqConfigSvc = new FFPMqConfigSvc();
		Map<String, FFPMqConfig> qmConfigMap = mqConfigSvc.getQmConfigMap();
		Iterator<String> it = qmConfigMap.keySet().iterator();
		while (it.hasNext()) {
			String mqConfigName = it.next();
			if (groupName.equals(mqConfigName)) {
				// 取到名为groupName的mqConfig
				FFPMqConfig mqConfig = qmConfigMap.get(mqConfigName);
				Map<String, FFPQueueConfig> sendQueueNameMap = mqConfig.getSendQueueNameMap();
				Iterator<String> iterator = sendQueueNameMap.keySet().iterator();

				while (iterator.hasNext()) {
					String sendQueueName = iterator.next();
					if (sendQueueName.contains(queueNameStr.toLowerCase())) {
						FFPQueueConfig queueConfig = sendQueueNameMap.get(sendQueueName);
						String destination = queueConfig.getQueueName();
						return destination;
					}
				}
			}
		}
		return null;
	}
}
