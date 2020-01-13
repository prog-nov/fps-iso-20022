package com.forms.ffp.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

import com.forms.ffp.utils.FFPStringUtils;

public class FFPJnlUtils
{
	private static int seq = 0;
	
	public static String getUploadedFileName(String originalFilename)
	{
		String fileName = FilenameUtils.getName(originalFilename);
		String fileExtension = FilenameUtils.getExtension(originalFilename);
		String date = getDateStr();

		return fileName.substring(0, fileName.length() - fileExtension.length() - 1) + "_" + date + "." + fileExtension;
	}

	public static String getOtherOutwardFileName(String mmbId)
	{
		String date = getDateStr();
		return "B" + mmbId + date.substring(0, date.length() - 1) + ".xml";
	}

	public static String getDirectDebitOutwardFileName(String mmbId)
	{
		String date = getDateStr();
		return "FPSPDDO" + mmbId + date.substring(0, date.length() - 1) + ".xml";
	}

	public static String getCreditTransferOutwardFileName(String mmbId)
	{
		String date = getDateStr();
		return "FPSPCRO" + mmbId + date.substring(0, date.length() - 1) + ".xml";
	}

	public static String getReturnRefundOutwardFileName(String mmbId)
	{
		String date = getDateStr();
		return "FPSPCRO" + mmbId + date.substring(0, date.length() - 1) + ".xml";
	}

	public static String getAddressingOutwardFileName(String mmbId)
	{
		String date = getDateStr();
		return "FPSADRO" + mmbId + date.substring(0, date.length() - 1) + ".xml";
	}

	public static String getEDDAOutwardFileName(String mmbId)
	{
		String date = getDateStr();
		return "FPSEDAO" + mmbId + date.substring(0, date.length() - 1) + ".xml";
	}

	public static String getTemplateFileName(String fileId)
	{
		String date = getDateStr();
		return "template_" + fileId + ".xml";
	}

	public static String getMandateRequestId(String mmbId, String type)
	{
		return "M" + type + mmbId + getDateStr();
	}

	public static String getAddressingRequestId(String mmbId, String type)
	{
		return "A" + type + mmbId + getDateStr();
	}

	public static String getMessageId(String mmbId)
	{
		return "M" + mmbId + getDateStr() + getSeq();
	}

	private static String getDateStr()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
	public synchronized static String getSeq()
	{
		seq ++;
		String str = FFPStringUtils.padZero(seq, 6);
		if(seq >= 100000)
		{
			seq = 0;
		}
		return str;
	}
}
