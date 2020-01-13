package com.forms.ffp.msg.iclfps;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.BusinessApplicationHeaderV01;
import com.forms.ffp.msg.iclfps.bussiness.FFPBaseBussinessBean;
import com.forms.ffp.msg.iclfps.creater.FFPMsgGenerator;
import com.forms.ffp.msg.iclfps.creater.FFPXmlDocCreater;
import com.forms.ffp.utils.FFPXMLUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.w3c.dom.NodeList;

public class FFPMessageAdaptor
{
	private static Logger _logger = LoggerFactory.getLogger(FFPMessageAdaptor.class);

	@Autowired
	@Qualifier("util.hkiclMsgAdaptor")
	private FFPHkiclMessageAdaptor fpsMsgAdaptor;

	public static ISO20022BusinessDataV01 getBizData(FFPBaseBussinessBean bean) throws RuntimeException
	{
		FFPXmlDocCreater util = (FFPXmlDocCreater) FFPMessageDefinitionIdentifier.xmlDocCreater.get(bean.getMessageDefinitionIdentifier());
		if (util == null)
		{
			throw new RuntimeException(String.format("No such message definition util [%s]", new Object[] { bean.getMessageDefinitionIdentifier() }));
		}

		if (_logger.isInfoEnabled())
		{
			_logger.info(String.format("Creating docuemnts... [%s] - count: %s", new Object[] { bean.getMessageDefinitionIdentifier(), Integer.valueOf(bean.getCount()) }));
		}

		Object document = util.createDocument(bean);
		return createBizData(bean, document);
	}

	private static ISO20022BusinessDataV01 createBizData(FFPBaseBussinessBean bean, Object document)
	{
		BusinessApplicationHeaderV01 appHdr = FFPMsgGenerator.createAppHdr(bean.getFrMmbId(), bean.getToMmbId(), bean.getMessageId(), bean.getMessageDefinitionIdentifier(), bean.getBusinessService(),
				new Date());
		ISO20022BusinessDataV01 bizData = FFPMsgGenerator.createBusinessData(appHdr, document);
		return bizData;
	}

	public String getMessageWithEnvelope(FFPBaseBussinessBean bean) throws Exception
	{
		ISO20022BusinessDataV01 bizData = getBizData(bean);
		return getMessageWithEnvelope(bizData);
	}

	public static String getMessageWithEnvelope(List<ISO20022BusinessDataV01> bizDataList) throws Exception
	{
		FpsMessageEnvelope fpsMsgEnvelope = FFPMsgGenerator.createFpsMessageEnvelope(bizDataList);
		String message = null;
		if (bizDataList.size() > 1)
		{
			message = FFPHkiclMessageAdaptor.makeupMultiBizDataXml(FFPHkiclMessageAdaptor.packageXml(fpsMsgEnvelope));
		} else
		{
			message = FFPHkiclMessageAdaptor.makeupRealTimeXml(FFPHkiclMessageAdaptor.packageXml(fpsMsgEnvelope));
		}
		return message;
	}

	public static String getMessageWithEnvelope(List<ISO20022BusinessDataV01> bizDataList, ValidationEventHandler handler) throws Exception
	{
		FpsMessageEnvelope fpsMsgEnvelope = FFPMsgGenerator.createFpsMessageEnvelope(bizDataList);
		String message = null;
		if (bizDataList.size() > 1)
		{
			message = FFPHkiclMessageAdaptor.makeupMultiBizDataXml(FFPHkiclMessageAdaptor.packageXml(fpsMsgEnvelope, handler));
		} else
		{
			message = FFPHkiclMessageAdaptor.makeupRealTimeXml(FFPHkiclMessageAdaptor.packageXml(fpsMsgEnvelope, handler));
		}
		return message;
	}

	public static String getMessageWithEnvelope(ISO20022BusinessDataV01 bizData) throws Exception
	{
		FpsMessageEnvelope fpsMsgEnvelope = FFPMsgGenerator.createFpsMessageEnvelope(bizData);
		String message = FFPHkiclMessageAdaptor.makeupRealTimeXml(FFPHkiclMessageAdaptor.packageXml(fpsMsgEnvelope));
		return message;
	}

	public String getMessageWithEnvelope(ISO20022BusinessDataV01 bizData, ValidationEventHandler handler) throws Exception
	{
		FpsMessageEnvelope fpsMsgEnvelope = FFPMsgGenerator.createFpsMessageEnvelope(bizData);
		String message = FFPHkiclMessageAdaptor.makeupRealTimeXml(FFPHkiclMessageAdaptor.packageXml(fpsMsgEnvelope, handler));
		return message;
	}

	public String signMessageByBank(String message, String clearingCode)
	{
		// TODO
		return "";
	}

	/* Error */
	// public String signMessage(String message, KeystoreConfig keystoreConfig,
	// String alias)
	// {
	//
	// }

	public static String signMessage(String message, String keystore, String keyAlias)
	{
		// TODO
		return "";
	}

	public static FpsMessageEnvelope parse(String message) throws Exception
	{
		return parse(message, false);
	}

	public static FpsMessageEnvelope parse(String message, boolean skipValidation) throws Exception
	{
		if (skipValidation)
		{
			return FFPHkiclMessageAdaptor.parseObject(message, new ValidationEventHandler()
			{
				public boolean handleEvent(ValidationEvent event)
				{
					if (FFPMessageAdaptor._logger.isInfoEnabled())
					{
						FFPMessageAdaptor._logger.info(String.format("Skip validate event %s. ", new Object[] { event.getMessage() }));
					}
					if ((FFPMessageAdaptor._logger.isErrorEnabled()) && (event.getLinkedException() != null))
					{
						FFPMessageAdaptor._logger.error("Validate Event Exception", event.getLinkedException());
					}
					return true;
				}
			});
		}
		return FFPHkiclMessageAdaptor.parseObject(message);
	}
}
