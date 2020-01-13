package com.forms.ffp.msg.iclfps.creater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessagePayloads;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.ClearingSystemMemberIdentification21;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.FPSBusinessServiceCode;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.FinancialInstitutionIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.head_001_001.Party9Choice1;
import com.forms.ffp.msg.iclfps.FFPMessageAdaptor;
import com.forms.ffp.msg.iclfps.bussiness.FFPBaseBussinessBean;
import com.forms.ffp.msg.iclfps.bussiness.FFPMsgFormContextBean;

public class FFPMsgGenerator
{
	private static Logger _logger = LoggerFactory.getLogger(FFPMsgGenerator.class);

	public static BusinessApplicationHeaderV01 createAppHdr(String _from, String _to, String _bizMsgIdr, String _msgDefIdr, String _bizSvc, Date _creDt)
	{
		Party9Choice1 fr = createParty(_from);
		Party9Choice1 to = createParty(_to);

		BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
		appHdr.setFr(fr);
		appHdr.setTo(to);
		appHdr.setBizMsgIdr(_bizMsgIdr);
		appHdr.setMsgDefIdr(_msgDefIdr);
		appHdr.setBizSvc(FPSBusinessServiceCode.fromValue(_bizSvc));
		appHdr.setCreDt(toGregorianDt(_creDt));

		return appHdr;
	}

	public static ISO20022BusinessDataV01 createBusinessData(BusinessApplicationHeaderV01 appHdr, Object doc)
	{
		ISO20022BusinessDataV01 bizData = new ISO20022BusinessDataV01();
		bizData.setAppHdr(appHdr);
		bizData.setDocument(doc);

		return bizData;
	}

	public static FpsMessageEnvelope createFpsMessageEnvelope()
	{
		FpsMessageEnvelope envelope = new FpsMessageEnvelope();
		envelope.setNbOfMsgs("0");
		FpsMessagePayloads payloads = new FpsMessagePayloads();
		envelope.setFpsPylds(payloads);
		return envelope;
	}

	public static FpsMessageEnvelope createFpsMessageEnvelope(ISO20022BusinessDataV01 bizData)
	{
		FpsMessageEnvelope envelope = new FpsMessageEnvelope();
		envelope.setNbOfMsgs(bizData == null ? "0" : "1");
		if (bizData != null)
		{
			FpsMessagePayloads payloads = new FpsMessagePayloads();
			payloads.getBizData().add(bizData);
			envelope.setFpsPylds(payloads);
		}
		return envelope;
	}

	public static FpsMessageEnvelope createFpsMessageEnvelope(List<ISO20022BusinessDataV01> listOfBizData)
	{
		FpsMessageEnvelope envelope = new FpsMessageEnvelope();
		envelope.setNbOfMsgs(String.valueOf(listOfBizData == null ? 0 : listOfBizData.size()));
		if ((listOfBizData != null) && (listOfBizData.size() > 0))
		{
			FpsMessagePayloads payloads = new FpsMessagePayloads();
			payloads.getBizData().addAll(listOfBizData);
			envelope.setFpsPylds(payloads);
		}
		return envelope;
	}

	public static Party9Choice1 createParty(String mmbId)
	{
		Party9Choice1 party = new Party9Choice1();
		BranchAndFinancialInstitutionIdentification51 fiId = new BranchAndFinancialInstitutionIdentification51();
		FinancialInstitutionIdentification81 finInstnId = new FinancialInstitutionIdentification81();
		ClearingSystemMemberIdentification21 clrSysMmbId = new ClearingSystemMemberIdentification21();
		clrSysMmbId.setMmbId(mmbId);
		finInstnId.setClrSysMmbId(clrSysMmbId);
		fiId.setFinInstnId(finInstnId);
		party.setFIId(fiId);
		return party;
	}

	public static XMLGregorianCalendar toGregorianDt(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
			gregorianCalendar.setTime(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static XMLGregorianCalendar toGregorianDtNoTs(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			SimpleDateFormat _dtType2 = new SimpleDateFormat("yyyy-MM-dd");
			String fmtDate = _dtType2.format(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(fmtDate);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static XMLGregorianCalendar toGregorianDtType1(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static XMLGregorianCalendar toGregorianDtType2(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			SimpleDateFormat _dtType2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String fmtDate = _dtType2.format(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(fmtDate);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static String getFpsMessage(FFPMsgFormContextBean ctx) throws Exception
	{
		return getFpsMessage(ctx, null);
	}

	public static String getFpsMessage(FFPMsgFormContextBean ctx, ValidationEventHandler handler) throws Exception
	{
		Map<String, FFPBaseBussinessBean> beans = ctx.getFormBeanMap();

		ArrayList<ISO20022BusinessDataV01> bizDataList = new ArrayList<ISO20022BusinessDataV01>();
		for (FFPBaseBussinessBean bean : beans.values())
		{
			if (bean != null)
			{
				ISO20022BusinessDataV01 bizData = FFPMessageAdaptor.getBizData(bean);
				bizDataList.add(bizData);
			}
		}

		String keystore = ctx.getKeystore();
		String keyAlias = ctx.getKeyAlias();

		String message = null;

		if (handler != null)
		{
			message = FFPMessageAdaptor.getMessageWithEnvelope(bizDataList, handler);
		} else
		{
			message = FFPMessageAdaptor.getMessageWithEnvelope(bizDataList);
		}

		if ((message != null) && (message.length() > 0) && (keystore != null) && (keyAlias != null))
		{
			message = FFPMessageAdaptor.signMessage(message, keystore, keyAlias);
		}
		return message;
	}

}
