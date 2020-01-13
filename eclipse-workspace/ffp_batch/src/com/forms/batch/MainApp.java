package com.forms.batch;

import java.math.BigDecimal;

import com.forms.framework.RunBatchJob;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.util.EncryptUtil;

public class MainApp
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			//resend : /job/iclfps/payment/credittransfer/inward_fpspyci_reader.xml
			//credit outward response: /job/participant/outwardresponse/response_FPSPzCRR_reader.xml
			// /job/participant/outwardresponse/response_FPSPCRR_reader.xml
			// /job/iclfps/payment/returnorrefund/inward_fpspyci_returnrefund_processor.xml
			// /job/participant/receivemsg/receivemsg.xml
			// /job/iclfps/payment/certificate/fpsd2006_reader.xml
			//RunBatchJob test = RunBatchJob.getInstance();
			//test.execute(args);
			
			/*String str = "[123]|[333]|[4444]|[66666]";
			
			Pattern r = Pattern.compile(str);
			Matcher m = r.matcher("66666");

			System.out.println(m.find());*/
			
			//System.out.println(Math.round(Math.abs(Math.random())));
			
			new BigDecimal((String)null);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static String decrypt(String ip_input) throws BatchFrameworkException
	{
		String loc_strPassword = null;
		EncryptUtil loc_encUtil = null;
		byte[] loc_arrKey = null;
		byte[] loc_arrEnc = null;
		byte[] loc_arrDec = null;

		if (ip_input == null || ip_input.length() < 1)
		{
			throw new BatchFrameworkException("Required input string.");
		}
		loc_encUtil = new EncryptUtil();
		loc_arrKey = loc_encUtil.generateKey();
		loc_arrEnc = loc_encUtil.hex2byte(ip_input);
		loc_arrDec = loc_encUtil.decode(loc_arrEnc, loc_arrKey);
		loc_strPassword = new String(loc_arrDec);

		return loc_strPassword;
	}
}

