package com.forms.batch.util;

import java.io.File;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;

import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class FFPCheckSignDigitalCertsUtils 
{
	
	public static boolean checkFPSSignDigitalCertificate(File file) throws Exception
	{
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = reader.read(file);
		
		return checkFPSSignDigitalCertificate(document.asXML());
	}
	
	
	public static boolean checkFPSSignDigitalCertificate(String message) throws Exception
	{
		Connection conn = null;
		String cert_sql = "SELECT EFFECTIVE_DATE, EXPIRY_DATE FROM TB_DT_ECERT WHERE SYSTEM_ID = ? AND ECERT_KEY = ? AND REVOKED_TS IS NULL";
		
		try
		{
			conn = ConnectionManager.getInstance().getConnection();
			
			org.w3c.dom.Document doc = FFPXMLUtils.string2Documnet(message);
			org.w3c.dom.NodeList signatureNodeList = FFPHkiclMessageConverter.getSignatureNodeList(doc);
			
			boolean signatureCheck = FFPHkiclMessageConverter.validateDsignXML(signatureNodeList);
			
			if(!signatureCheck)//invalid digital sign
				return signatureCheck;
				
			boolean ecertValid = true;
			for (int k = 0; k < signatureNodeList.getLength(); k++)
			{
				org.w3c.dom.Node node = signatureNodeList.item(k);
				Node KeyInfo = node.getOwnerDocument().getElementsByTagName("KeyInfo").item(0);
				Node X509Data = KeyInfo.getOwnerDocument().getElementsByTagName("X509Data").item(0);
				Node X509Certificate = X509Data.getOwnerDocument().getElementsByTagName("X509Certificate").item(0);
				String loc_cert = X509Certificate.getTextContent();
				//Query tb_dt_ecert
				
				Object[] obj = EntityManager.queryArray(conn, cert_sql, FFPConstants.RELATION_SYSTEM_HKICL, loc_cert.replaceAll("\r", "").replaceAll("\n", ""));
				
				if(obj == null)
				{
					ecertValid &= false;
				}
				else
				{
					Date effectDate = (Date)obj[0];
					Date expiryDate = (Date)obj[1];
					
					Calendar cl_now = Calendar.getInstance();
					Calendar cl_eff = Calendar.getInstance();
					Calendar cl_exp = Calendar.getInstance();
					
					cl_now.setTime(new Date());
					cl_eff.setTime(effectDate);
					cl_exp.setTime(expiryDate);
					
					//1. expiry date >= effective date, valid
					if(cl_exp.before(cl_eff)) 
					{
						ecertValid &= false;
					}
					//now >= effective date && now < expiry date, valid
					if(cl_eff.after(cl_now) || cl_exp.before(cl_now))
					{
						ecertValid &= false;
					}
				}
			}
			
			return signatureCheck && ecertValid;
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			if(conn != null)
				conn.close();
		}
	}
	
	/*public boolean checkDate(Date now, Date certEffDate, Date certExDate)
	{
		Calendar cl_now = Calendar.getInstance();
		Calendar cl_eff = Calendar.getInstance();
		Calendar cl_exp = Calendar.getInstance();
		
		cl_now.setTime(now);
		cl_eff.setTime(certExDate);
		cl_exp.setTime(certExDate);
		
		//1. expiry date >= effective date, valid
		if(cl_exp.before(cl_eff)) 
		{
			return false;
		}
		//now >= effective date && now < expiry date, valid
		if(cl_eff.after(cl_now) || cl_exp.before(cl_now))
		{
			return false;
		}
		
		return true;
	}*/
}
