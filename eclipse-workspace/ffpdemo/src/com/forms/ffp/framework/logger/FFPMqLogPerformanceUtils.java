package com.forms.ffp.framework.logger;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.utils.FFPDateUtils;
import com.forms.ffp.utils.FFPStringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FFPMqLogPerformanceUtils{}
//{
//	private static Logger _logger = LoggerFactory.getLogger(FFPMqLogPerformanceUtils.class);
//	@Autowired
//	private BankSimMessageUtil bankSimMsgUtil;
//	private ExecutorService executorSvc = Executors.newFixedThreadPool(10);
//
//	public void logInfo(String format, Object... args)
//	{
//		if (_logger.isInfoEnabled())
//		{
//			_logger.info(String.format(format, args));
//		}
//	}
//
//	public void logDebug(String format, Object... args)
//	{
//		if (_logger.isDebugEnabled())
//		{
//			_logger.debug(String.format(format, args));
//		}
//	}
//
//	public void logMqPerformance(Long threadId, String processId, String bankCode, Integer msgType, Integer msgDirection, String priority, ISO20022BusinessDataV01 bizData, Long sysTime)
//	{
//		ThreadContext.put("msgType", getMsgTypeStr(msgType));
//		logInfo("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
//				new Object[] { FFPStringUtils.rpad(String.valueOf(threadId), ' ', 8), FFPStringUtils.rpad(processId, ' ', 20),
//						FFPStringUtils
//								.rpad(getString(bankCode), ' ',
//										4),
//						FFPStringUtils.rpad(getMsgDirectionStr(msgDirection), ' ', 8), FFPStringUtils.rpad(getMsgTypeStr(msgType), ' ', 3), FFPStringUtils.rpad(priority, ' ', 1),
//						FFPStringUtils.rpad(getMsgDef(bizData), ' ', 20), FFPStringUtils.rpad(getKey1(msgType, msgDirection, bizData), ' ', 35),
//						FFPStringUtils.rpad(getKey2(msgType, msgDirection, bizData), ' ', 35), FFPStringUtils.rpad(getKey3(msgType, msgDirection, bizData), ' ', 35),
//						FFPStringUtils.rpad(String.valueOf(sysTime), ' ', 15),
//						FFPStringUtils.rpad(sysTime != null ? FFPDateUtils.getDateStr(new Date(sysTime.longValue()), "yyyy-MM-dd-HH:mm:ss.SSS") : "", ' ', 23) });
//	}
//
//	public void logMqPerformance(String bankCode, Integer msgType, Integer msgDirection, String priority, ISO20022BusinessDataV01 bizData, Long sysTime)
//	{
//		Long threadId = Long.valueOf(Thread.currentThread().getId());
//		String processId = getProcessId();
//
//		logMqPerformance(threadId, processId, bankCode, msgType, msgDirection, priority, bizData, sysTime);
//	}
//
//	public void asyncLogMqPerformance(final String bankCode, final Integer msgType, final Integer msgDirection, final String priority, final ISO20022BusinessDataV01 bizData, final Long sysTime)
//	{
//		final Long threadId = Long.valueOf(Thread.currentThread().getId());
//		final String processId = getProcessId();
//
//		this.executorSvc.execute(new Runnable()
//		{
//			public void run()
//			{
//				FFPMqLogPerformanceUtils.this.logMqPerformance(threadId, processId, bankCode, msgType, msgDirection, priority, bizData, sysTime);
//			}
//		});
//	}
//
//	public void asyncLogMqPerformance(final String bankCode, final Integer msgType, final Integer msgDirection, final String priority, final String message, final Long sysTime)
//	{
//		final Long threadId = Long.valueOf(Thread.currentThread().getId());
//		final String processId = getProcessId();
//
//		this.executorSvc.execute(new Runnable()
//		{
//			public void run()
//			{
//				ISO20022BusinessDataV01 bizData = null;
//				try
//				{
//					FpsMessageEnvelope fpsMsg = FFPMqLogPerformanceUtils.this.bankSimMsgUtil.parse(message);
//					bizData = (ISO20022BusinessDataV01) fpsMsg.getFpsPylds().getBizData().get(0);
//				} catch (Exception localException)
//				{
//				}
//				FFPMqLogPerformanceUtils.this.logMqPerformance(threadId, processId, bankCode, msgType, msgDirection, priority, bizData, sysTime);
//			}
//		});
//	}
//
////	private String getProcessId()
////	{
////		try
////		{
////			Thread t = Thread.currentThread();
////			if ((t instanceof BulkProcessBaseThread))
////			{
////				return getString(((BulkProcessBaseThread) t).getContext().getId());
////			}
////			return "";
////		} catch (Exception e)
////		{
////		}
////		return "ERROR";
////	}
//
//	private String getMsgTypeStr(Integer msgType)
//	{
//		switch (msgType.intValue())
//		{
//		case 1:
//			return "REQ";
//		case 2:
//			return "ACK";
//		}
//		return "";
//	}
//
//	private String getMsgDirectionStr(Integer msgDirection)
//	{
//		switch (msgDirection.intValue())
//		{
//		case 1:
//			return "SENT";
//		case 2:
//			return "RECEIVED";
//		}
//		return "";
//	}
//
////	private String getKey1(Integer msgType, Integer msgDirection, ISO20022BusinessDataV01 bizData)
////	{
////		try
////		{
////			return getString(bizData.getAppHdr().getBizMsgIdr());
////		} catch (Exception localException)
////		{
////		}
////		return "";
////	}
//
////	private String getKey2(Integer msgType, Integer msgDirection, ISO20022BusinessDataV01 bizData)
////	{
////		try
////		{
////			if (msgType.intValue() == 2)
////			{
////				String msgDef = getMsgDef(bizData);
////				AckMessageHelper ackMessageHelper = (AckMessageHelper) this.bankSimMsgUtil.getAckMessageHelpers().get(msgDef);
////				if (ackMessageHelper != null)
////				{
////					return getString(ackMessageHelper.getOriginalBusinessMessageId(bizData));
////				}
////			}
////		} catch (Exception localException)
////		{
////		}
////		return "";
////	}
//
////	private String getKey3(Integer msgType, Integer msgDirection, ISO20022BusinessDataV01 bizData)
////  {
////    String msgDef = getMsgDef(bizData);
////    try
////    {
////      String str1;
////      switch ((str1 = msgDef).hashCode())
////      {
////      case -2122888890: 
////        if (str1.equals("pacs.008.001.06")) {
////          break;
////        }
////        break;
////      case -2041207699: 
////        if (str1.equals("pain.018.001.01")) {}
////        break;
////      case -1879817749: 
////        if (str1.equals("camt.054.001.06")) {
////          break;
////        }
////        break;
////      case -1355978152: 
////        if (str1.equals("pain.011.001.05")) {}
////        break;
////      case -504195637: 
////        if (str1.equals("pacs.004.001.07")) {}
////        break;
////      case 305150990: 
////        if (str1.equals("pacs.002.001.08")) {}
////        break;
////      case 386832183: 
////        if (str1.equals("pain.012.001.05")) {}
////        break;
////      case 1196178809: 
////        if (str1.equals("pain.010.001.05")) {}
////        break;
////      case 1509057103: 
////        if (str1.equals("pain.009.001.05")) {}
////        break;
////      case 1605802921: 
////        if (str1.equals("camt.056.001.06")) {}
////      case 2047961324: 
////        if ((goto 304) && (str1.equals("pacs.003.001.07")))
////        {
////          return getString(searchFieldValue("endToEndId", bizData));
////          
////          return getString(searchFieldValue("orgnlEndToEndId", bizData));
////          
////          return getString(searchFieldValue("mndtId", bizData));
////        }
////        break;
////      }
////      return "";
////    }
////    catch (Exception localException1) {}
////    return "";
////  }
//
//	private String getMsgDef(ISO20022BusinessDataV01 bizData)
//	{
//		try
//		{
//			return getString(bizData.getAppHdr().getMsgDefIdr());
//		} catch (Exception localException)
//		{
//		}
//		return "";
//	}
//
//	private String getString(Object obj)
//	{
//		if (obj == null)
//		{
//			return "";
//		}
//		return obj.toString().trim();
//	}
//
//	private Object searchFieldValue(String fieldName, Object obj)
//	{
//		Object result = null;
//		try
//		{
//			Class<? extends Object> clazz = obj.getClass();
//			Field field = getFieldFromClass(clazz, fieldName);
//			if (field != null)
//			{
//				field.setAccessible(true);
//				result = field.get(obj);
//				field.setAccessible(false);
//			} else
//			{
//				Field[] fields = clazz.getDeclaredFields();
//				for (int i = 0; i < fields.length; i++)
//				{
//					Field f = fields[i];
//					f.setAccessible(true);
//					Object v = f.get(obj);
//					f.setAccessible(false);
//					if (v != null)
//					{
//						if ((v instanceof Iterable))
//						{
//							Iterator<?> itr = ((Iterable) v).iterator();
//							ParameterizedType type = (ParameterizedType) f.getGenericType();
//							while (itr.hasNext())
//							{
//								Object sv = itr.next();
//								result = searchFieldValue(fieldName, sv);
//								if (result != null)
//								{
//									return result;
//								}
//							}
//						} else if (!v.getClass().getPackage().getName().startsWith("java.lang"))
//						{
//							result = searchFieldValue(fieldName, v);
//							if (result != null)
//							{
//								return result;
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception localException)
//		{
//		}
//		return result;
//	}
//
//	private Field getFieldFromClass(Class<?> clazz, String fieldName)
//	{
//		Field field = null;
//		try
//		{
//			field = clazz.getDeclaredField(fieldName);
//			if ((field == null) && (clazz.getSuperclass() != null))
//			{
//				field = getFieldFromClass(clazz.getSuperclass(), fieldName);
//			}
//		} catch (Exception localException)
//		{
//		}
//		return field;
//	}
//}
