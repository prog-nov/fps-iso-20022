package com.forms.ffp.webapp.testing.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.ffp.core.msg.iclfps.creator.FFPMsgCreator;
import com.forms.ffp.core.msg.iclfps.creator.FFPMsgProperty;

public class MqSimulatorThread extends Thread
{
	private DefaultMessageListenerContainer container1;
	private DefaultMessageListenerContainer container2;
	
	public void run()
	{
		System.out.println("===================================MQ SIMULATOR START============================================");
		Connection conn = null;
//		final Session session = null;
//		final MessageProducer producerH = null;
//		final MessageProducer producerM = null;
        try
        {
			ConnectionFactory connFactory = new ActiveMQConnectionFactory("admin","admin", "tcp://172.21.21.253:61616");
			conn = connFactory.createConnection();   
			conn.start();
//			final Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
//			consumerH = session.createConsumer(session.createQueue("FFP.HKICL.REQ.H01.LOC"));
//			consumerM = session.createConsumer(session.createQueue("FFP.HKICL.REQ.M01.LOC"));
			
			container1 = new DefaultMessageListenerContainer();
			container1.setConnectionFactory(connFactory);
			container1.setRecoveryInterval(6000L);
			container1.setDestinationName("FFP.HKICL.REQ.H01.LOC");
			container1.setConcurrentConsumers(1);
			container1.setMaxConcurrentConsumers(1);
			container1.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
			MessageListener msgListener = new MessageListener()
			{
				@Override
				public void onMessage(Message msg)
				{
					try
					{
						String text = ((TextMessage)msg).getText();
						FpsMessageEnvelope fps = FFPHkiclMessageConverter.parseObject(text);
						String message = returnMessage(fps);
//						if(message!=null)producerH.send(session.createTextMessage(message));
						
						JmsTemplate jmsTemplate = new JmsTemplate();
						ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin", "tcp://172.21.21.253:61616");
						jmsTemplate.setConnectionFactory(factory);
						jmsTemplate.setSessionTransacted(true);
						jmsTemplate.setPubSubDomain(false);
						FFPMsgCreator msgCreator = new FFPMsgCreator();
						msgCreator.setMsgObj(message);
						FFPMsgProperty msgProperty = new FFPMsgProperty();
						msgProperty.setPropertyMap(null);
						msgCreator.setMsgProperty(msgProperty);
						msgCreator.setMsgConverter(jmsTemplate.getMessageConverter());
						jmsTemplate.send("HKICL.FFP.ACK.H.REM", msgCreator);
						System.out.println("================"+msgCreator.getMsg().getJMSMessageID()+"================================"+msgCreator.getCorrelationId());
					} catch (Exception e)
					{
						System.out.println(e + e.getMessage());
					}

				}
			};
			container1.setMessageListener(msgListener);
			container1.initialize();
			container1.start();
			
			container2 = new DefaultMessageListenerContainer();
			container2.setConnectionFactory(connFactory);
			container2.setRecoveryInterval(6000L);
			container2.setDestinationName("FFP.HKICL.REQ.M01.LOC");
			container2.setConcurrentConsumers(1);
			container2.setMaxConcurrentConsumers(1);
			container2.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
			MessageListener msgListener2 = new MessageListener()
			{
				@Override
				public void onMessage(Message msg)
				{
					try
					{
						String text = ((TextMessage)msg).getText();
						FpsMessageEnvelope fps = FFPHkiclMessageConverter.parseObject(text);
						String message = returnMessage(fps);
//						if(message!=null)producerM.send(session.createTextMessage(message));
						
						JmsTemplate jmsTemplate = new JmsTemplate();
						ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin", "tcp://172.21.21.253:61616");
						jmsTemplate.setConnectionFactory(factory);
						jmsTemplate.setSessionTransacted(true);
						jmsTemplate.setPubSubDomain(false);
						FFPMsgCreator msgCreator = new FFPMsgCreator();
						msgCreator.setMsgObj(message);
						FFPMsgProperty msgProperty = new FFPMsgProperty();
						msgProperty.setPropertyMap(null);
						msgCreator.setMsgProperty(msgProperty);
						msgCreator.setMsgConverter(jmsTemplate.getMessageConverter());
						jmsTemplate.send("HKICL.FFP.ACK.M.REM", msgCreator);
						System.out.println("================"+msgCreator.getMsg().getJMSMessageID()+"================================"+msgCreator.getCorrelationId());
					} catch (Exception e)
					{
						System.out.println(e + e.getMessage());
					}

				}
			};
			container2.setMessageListener(msgListener2);
			container2.initialize();
			container2.start();
        }
        catch(Exception ip_e)
        {
        	ip_e.printStackTrace();
        }
    }

	@SuppressWarnings({"unchecked" })
	private String returnMessage(FpsMessageEnvelope fps)
	{
		FileInputStream fis = null;
		try
		{
			ISO20022BusinessDataV01 bizData = (ISO20022BusinessDataV01)fps.getFpsPylds().getBizData().get(0);
			BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01)bizData.getContent().get(0).getValue();
			
			String configFile = "C:/ffp/testing/Config/" + head.getMsgDefIdr() + ".xml";
			fis = new FileInputStream(configFile);
			SAXReader loc_saxReader = new SAXReader();
			InputStreamReader loc_isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader loc_br = new BufferedReader(loc_isr);
			Document loc_doc = loc_saxReader.read(loc_br);
			Element loc_eleRoot = loc_doc.getRootElement();
			fis.close();
			
			//GET KEY
			String returnFileKey = head.getMsgDefIdr();
			List<Element> list = loc_eleRoot.element("KEYS").elements("KEY");
			for(Element ele : list)
			{
				System.out.println(ele.getTextTrim());
				returnFileKey += "." + invokeKeyValue(ele.getTextTrim(), fps.getFpsPylds().getBizData().get(0));
			}
			System.out.println(returnFileKey);
			
			//GET REPLACE MAP
			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("##FrMmbId##", head.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
			replaceMap.put("##ToMmbId##", head.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
			replaceMap.put("##BizSvc##", head.getBizSvc().value());
			replaceMap.put("##OrgnlMsgId##", head.getBizMsgIdr());
			replaceMap.put("##OrgnlMsgNmId##", head.getMsgDefIdr());
			replaceMap.put("##yyyy-MM-dd'T'hh:mm:ss.sss'Z'##", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.sss'Z'").format(Calendar.getInstance().getTime()));
			replaceMap.put("##yyyyMMddhhmmss##", new SimpleDateFormat("yyyyMMddhhmmss").format(Calendar.getInstance().getTime()));
			replaceMap.put("##yyyy-MM-dd'T'hh:mm:ss.sss##", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.sss").format(Calendar.getInstance().getTime()));
			replaceMap.put("##yyyy-MM-dd'T'hh:mm:ss##", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(Calendar.getInstance().getTime()));
			replaceMap.put("##random##", String.valueOf(Double.valueOf(Math.random() * 10000).intValue()));
			List<Element> maplist = loc_eleRoot.element("REPLACE_MAP").elements("Object");
			if(maplist != null)
			{
				for(Element ele : maplist)
				{
					replaceMap.put(ele.elementText("NAME"), invokeKeyValue(ele.elementText("VALUE"), fps.getFpsPylds().getBizData().get(0)));
				}
			}
			
			Properties pro = new Properties();
			fis = new FileInputStream("C:/ffp/testing/Config/RETURN_MAPPING.properties");
			pro.load(fis);
			
			String returnFileName=pro.getProperty(returnFileKey);
			String returnMessage = readFile(returnFileName);
			Iterator<String> iter = replaceMap.keySet().iterator();
			while(iter.hasNext())
			{
				String key = iter.next();
				String value = replaceMap.get(key);
				returnMessage = returnMessage.replaceAll(key, value);
			}
			
			return returnMessage;
			
			
			
//			String returnFileKey = head.getMsgDefIdr();
//			List<Element> list = loc_eleRoot.element("KEYS").elements("KEY");
//			
//			String returnFileName = "C:/ffp/testing/Return/";
//			
//			System.out.println(loc_eleRoot.element("KEYS").elements("KEY").get(0));
//			
//			
//			if(FFPJaxbConstants.JAXB_MSG_TYPE_PACS_008.equals(head.getMsgDefIdr()))
//			{
//				returnMessage = readFile("D:/ffp/testing/pacs002_AccountVerification.xml");
//				
//				com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document doc = (com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document)bizData.getContent().get(1).getValue();
//				returnMessage = returnMessage.replace("##OrgnlEndToEndId##", doc.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getEndToEndId());
//				returnMessage = returnMessage.replace("##OrgnlTxId##", doc.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getTxId());
//			}
//			else if(FFPJaxbConstants.JAXB_MSG_TYPE_CAMT_060.equals(head.getMsgDefIdr()))
//			{
//				returnMessage = readFile("C:/Users/qingzhizi/working/Project/FFP/Workspaces/FFPWorkspace1/ffp_resources/testing/camt.052.001.06_TEMPLATE.xml");
//				com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.Document doc = (com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.Document)bizData.getContent().get(1).getValue();
//				returnMessage = returnMessage.replace("##Currency##", doc.getAcctRptgReq().getRptgReq().getAcct().getCcy().value());
//			}else if(FFPJaxbConstants.JAXB_MSG_TYPE_PACS_003.equals(head.getMsgDefIdr())){
//				returnMessage = readFile("D:/ffp/testing/pacs002_AccountVerification.xml");
//				com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Document doc = (com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Document)bizData.getContent().get(1).getValue();
//				returnMessage = returnMessage.replace("##OrgnlEndToEndId##", doc.getFIToFICstmrDrctDbt().getDrctDbtTxInf().getPmtId().getEndToEndId());
//				returnMessage = returnMessage.replace("##OrgnlTxId##",  doc.getFIToFICstmrDrctDbt().getDrctDbtTxInf().getPmtId().getTxId());
//			}
//			else if(FFPJaxbConstants.JAXB_MSG_TYPE_FPS_ADMI_001.equals(head.getMsgDefIdr()))
//			{
//				returnMessage = readFile("C:/Users/qingzhizi/working/Project/FFP/Workspaces/FFPWorkspace1/ffp_resources/testing/fps.admi.002.001.01.TEMPLATE.xml");
//				com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.Document doc = (com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.Document)bizData.getContent().get(1).getValue();
//				returnMessage = returnMessage.replace("##RCPTMD##", doc.getRcptMdSwtchgReq().getRcptMd().value());
//				returnMessage = returnMessage.replace("##SwtchgTm##", doc.getRcptMdSwtchgReq().getGrpHdr().getCreDtTm().toXMLFormat());
//			}
//			
//			returnMessage = returnMessage.replace("##FrMmbId##", head.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
//			returnMessage = returnMessage.replace("##ToMmbId##", head.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
//			returnMessage = returnMessage.replace("##BizSvc##", head.getBizSvc().value());
//			returnMessage = returnMessage.replace("##OrgnlMsgId##", head.getBizMsgIdr());
//			returnMessage = returnMessage.replace("##OrgnlMsgNmId##", head.getMsgDefIdr());
//			
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.sss'Z'");
//			returnMessage = returnMessage.replace("##yyyy-MM-dd'T'hh:mm:ss.sss'Z'##", sdf1.format(Calendar.getInstance().getTime()));
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
//			returnMessage = returnMessage.replace("##yyyyMMddhhmmss##", sdf2.format(Calendar.getInstance().getTime()));
//			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.sss");
//			returnMessage = returnMessage.replace("##yyyy-MM-dd'T'hh:mm:ss.sss##", sdf3.format(Calendar.getInstance().getTime()));
//			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
//			returnMessage = returnMessage.replace("##yyyy-MM-dd'T'hh:mm:ss##", sdf4.format(Calendar.getInstance().getTime()));
//			
//			int random = Double.valueOf(Math.random()).intValue();
//			returnMessage = returnMessage.replace("##random##", String.valueOf(random));
//			return returnMessage;
		}
		catch(Exception ip_e)
		{
			ip_e.printStackTrace();
			return null;
		}
		finally
		{
				try
				{
					if(fis != null)
					fis.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}
	}
	
	private String readFile(String filename)
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(filename);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while(-1 != (len = fis.read(b)))
			{
				baos.write(b, 0, len);
			}
			return new String(baos.toByteArray(),"utf-8");
		}
		catch(Exception ip_e)
		{
			ip_e.printStackTrace();
			return "";
		}
		finally
		{
			try
			{
				if(fis != null)
					fis.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String invokeKeyValue(String key, ISO20022BusinessDataV01 bizData) throws Exception
	{
		BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01) bizData.getContent().get(0).getValue();
		Object doc = bizData.getContent().get(1).getValue();
		
		String[] keyArray = key.split("\\.");
		Object obj = "head".equals(keyArray[0]) ? head : doc;
		Class clazz = obj.getClass();
		for(int i = 1; i < keyArray.length; i ++)
		{
			Method method = clazz.getMethod(keyArray[i]);
			obj = method.invoke(obj);
			if(obj instanceof List)
				obj = ((List) obj).get(0);
			clazz = obj.getClass();
		}
		
		if(obj instanceof String)
			return (String) obj;
		
		return "";
	}
	
	@PreDestroy
	public void destroy()
	{
		container1.destroy();
		container2.destroy();
		container1.stop();
		container2.stop();
		this.interrupt();
	}
}
