package com.forms.ffp.webapp.testing.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcti01.FFPCTI01;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcti02.FFPCTI02;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcto01.FFPCTO01;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpddi02.FFPDDI02;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPStringUtils;

public class TcpSimulatorThread extends Thread {
	public void run() {
		System.out.println(
				"===================================TCP SIMULATOR START============================================");
		ServerSocket server = null;
		try {
			server = new ServerSocket(19999);
			while (!server.isClosed()) {
				Socket socket = server.accept();
				socket.setSoTimeout(60 * 1000);
				ByteArrayOutputStream result = new ByteArrayOutputStream();
				byte[] lengthByte = new byte[FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN];
				DataInputStream is = new DataInputStream(socket.getInputStream());
				is.readFully(lengthByte);
				result.write(lengthByte, 0, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN);
				int length = Integer.valueOf(new String(lengthByte, FFPConstants.ENCODING_UTF8));

				byte[] dataByte = new byte[length];
				is.readFully(dataByte);
				result.write(dataByte, 0, length);

				String message = new String(result.toByteArray(), FFPConstants.ENCODING_UTF8);
				ROOT root = FFPParticipantMessageConverter.parseXml2RequestObject(message);
				if (root.getBODY() instanceof FFPCTO01) {
					System.out.println("==========RECEIVE FROM FFP=============start============");
					System.out.println(message);
					System.out.println("==========RECEIVE FROM FFP==============end=============");
				} else {
					String str = returnMessage(root);
					OutputStream os = socket.getOutputStream();
					// //set time out
					// Thread.sleep(10000);
					os.write(str.getBytes("utf-8"));
					os.close();
				}
			}
		} catch (Exception ip_e) {
			ip_e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String returnMessage(ROOT root) {
		FileInputStream fis = null;
		String returnMessage = null;
		try {
			HEAD head = root.getHEAD();
			BODY body = root.getBODY();

			String configFile = "C:/ffp/testing/Config/" + head.getMessageType() + ".xml";
			fis = new FileInputStream(configFile);
			SAXReader loc_saxReader = new SAXReader();
			InputStreamReader loc_isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader loc_br = new BufferedReader(loc_isr);
			Document loc_doc = loc_saxReader.read(loc_br);
			Element loc_eleRoot = loc_doc.getRootElement();
			fis.close();
			
			//GET KEY
			String returnFileKey = head.getMessageType();
			List<Element> list = loc_eleRoot.element("KEYS").elements("KEY");
			for(Element ele : list)
			{
				System.out.println(ele.getTextTrim());
				returnFileKey += "." + invokeKeyValue(ele.getTextTrim(), root);
			}
			System.out.println(returnFileKey);
			
			//GET REPLACE MAP
			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("##RequestID##", head.getRequestID());
			replaceMap.put("##TransactionDate##", head.getTransactionDate());
			replaceMap.put("##TransactionTime##", head.getTransactionTime());
			replaceMap.put("##RequestRefno##", head.getRequestRefno());
			replaceMap.put("##AccountingDate##", head.getTransactionDate());
			replaceMap.put("##ResponseID##", head.getResponseID());
			replaceMap.put("##MessageType##", head.getMessageType());
			replaceMap.put("##SystemRefno##", head.getAccountingDate());
			replaceMap.put("##hhmmss##", new SimpleDateFormat("hhmmss").format(Calendar.getInstance().getTime()));
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
					replaceMap.put(ele.elementText("NAME"), invokeKeyValue(ele.elementText("VALUE"), root));
				}
			}
			
			Properties pro = new Properties();
			fis = new FileInputStream("C:/ffp/testing/Config/RETURN_MAPPING.properties");
			pro.load(fis);
			
			String returnFileName=pro.getProperty(returnFileKey);
			returnMessage = readFile(returnFileName);
			Iterator<String> iter = replaceMap.keySet().iterator();
			while(iter.hasNext())
			{
				String key = iter.next();
				String value = replaceMap.get(key);
				returnMessage = returnMessage.replaceAll(key, value);
			}
			returnMessage = FFPStringUtils.padZero(returnMessage.getBytes().length, 8) + returnMessage;
			
			return returnMessage;
			
//			if (FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01.equals(head.getMessageType())) {
//				FFPCTI01 cti01 = (FFPCTI01) body;
//				srcRefNm = cti01.getSrcRefNm();
//				fis = new FileInputStream("C:/ffp/testing/FFPCTI01.xml");
//			} else if (FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI02.equals(head.getMessageType())) {
//				FFPCTI02 cti02 = (FFPCTI02) body;
//				srcRefNm = cti02.getSrcRefNm();
//				fis = new FileInputStream("C:/ffp/testing/FFPCTI02.xml");
//			}else if(FFPJaxbConstants.JAXB_MSG_TYPE_FFPDDI02.equals(head.getMessageType())) {
//				FFPDDI02 ddi02 = (FFPDDI02)body;
//				srcRefNm = ddi02.getSrcRefNm();
//				fis = new FileInputStream("C:/ffp/testing/FFPDDI02.xml");
//			}
//			// common data
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] b = new byte[1024];
//			int len = 0;
//			while (-1 != (len = fis.read(b))) {
//				baos.write(b, 0, len);
//			}
//			returnMessage = new String(baos.toByteArray(), "utf-8");
//			returnMessage = returnMessage.replace("##RequestID##", head.getRequestID());
//			returnMessage = returnMessage.replace("#{SrcRefNm}", srcRefNm);
//
//			return returnMessage;
		} catch (Exception ip_e) {
			ip_e.printStackTrace();
			;
			return "";
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String invokeKeyValue(String key, ROOT root) throws Exception
	{
		HEAD head = root.getHEAD();
		BODY body = root.getBODY();
		
		String[] keyArray = key.split("\\.");
		Object obj = "head".equals(keyArray[0]) ? head : body;
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
}
