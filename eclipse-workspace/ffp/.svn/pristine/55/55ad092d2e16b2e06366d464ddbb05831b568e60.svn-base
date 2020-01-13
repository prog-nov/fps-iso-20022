package com.forms.ffp.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;

import com.forms.ffp.core.config.runtime.FFPRuntimeConstants;

public class FFPIDUtils
{
	private static int seq = 0;
	
	private static long seqLong = 0;
	
	public synchronized static String getUploadedFileName(String originalFilename)
	{
		String fileName = FilenameUtils.getName(originalFilename);
		String fileExtension = FilenameUtils.getExtension(originalFilename);
		String date = getDateStr();
		return fileName.substring(0, fileName.length() - fileExtension.length() - 1) + "_" + date + "." + fileExtension;
	}

	public synchronized static String getOtherOutwardFileName()
	{
		String date = getDateStr();
		return "B" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + date.substring(0, date.length() - 1) + ".xml";
	}

	public synchronized static String getDirectDebitOutwardFileName()
	{
		String date = getDateStr();
		return "FPSPDDO" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + date.substring(0, date.length() - 1) + ".xml";
	}

	public synchronized static String getCreditTransferOutwardFileName()
	{
		String date = getDateStr();
		return "FPSPCRO" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + date.substring(0, date.length() - 1) + ".xml";
	}

	public synchronized static String getReturnRefundOutwardFileName()
	{
		String date = getDateStr();
		return "FPSPCRO" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + date.substring(0, date.length() - 1) + ".xml";
	}

	public synchronized static String getAddressingOutwardFileName()
	{
		String date = getDateStr();
		return "FPSADRO" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + date.substring(0, date.length() - 1) + ".xml";
	}

	public synchronized static String getEDDAOutwardFileName()
	{
		String date = getDateStr();
		return "FPSEDAO" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + date.substring(0, date.length() - 1) + ".xml";
	}

	public synchronized static String getTemplateFileName(String fileId)
	{
		return "template_" + fileId + ".xml";
	}

	public synchronized static String getMandateRequestId(String type)
	{
		return "M" + type + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + getDateStr();
	}

	public synchronized static String getAddressingRequestId(String type)
	{
		return "A" + type + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + getDateStr();
	}

	public synchronized static String getMessageId()
	{
		return "M" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + getDateStr() + getSeq();
	}
	
	public synchronized static String getTransactionId()
	{
		return "T" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + getDateStr() + getSeq();
	}
	
	public synchronized static String getEndToEndId()
	{
		return "E" + FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID + getDateStr() + getSeq();
	}
	
	//17
	private synchronized static String getDateStr()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
	//14
	private synchronized static String getSimpleDateStr()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
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
	
	public synchronized static String getBiggerSeq(int count)
	{
		seqLong ++;
		String str = FFPStringUtils.padZeroLong(seqLong, count);
		if(seq >= 1*Math.pow(10,count+1)){
			seq = 0;
		}
		
		return str;
	}
	
	/** X30
	 * generate JNL_NO
	 * @return
	 */
	public synchronized static String getJnlNo(){
		
		return "JNL"+getDateStr()+getBiggerSeq(10);
	}
	
	/**
	 * generate SRC_REF_NM
	 * @return
	 */
	public synchronized static String getSrcRefNm(){
		
		return "REFNM"+getDateStr()+getBiggerSeq(8);
	}
	
	
	/**
	 * generate all refno X(30) , pure number
	 * @return
	 */
	public synchronized static String getRefno(){
		return getSimpleDateStr()+RandomStringUtils.randomNumeric(10)+getBiggerSeq(6);
	}
	
	/**
	 * generate 32 bit UUID
	 * @return
	 */
	public synchronized static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * X35
	 * @return
	 */
	public synchronized static String getAdrReqId(){
		return "A" + getDateStr() + RandomStringUtils.randomNumeric(12)+getBiggerSeq(5);
	}
	
//	public static void main(String[] args) {
//		for(int i=0; i<20; i++){
//			System.out.println(getUUID());
//		}
//	}
	
}
