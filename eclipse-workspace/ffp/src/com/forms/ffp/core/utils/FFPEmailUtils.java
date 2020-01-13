package com.forms.ffp.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.service.FFPRuntimeConfigSvc;

public class FFPEmailUtils
{
	private static Logger _logger = LoggerFactory.getLogger(FFPEmailUtils.class);
	
	private static Properties emailPro;
	
	static
	{
		FileInputStream fis = null;
		try
		{
			String cofigFile = FFPRuntimeConfigSvc.getInstance().getRootConfig().get("config.Email.filename");
			fis = new FileInputStream(FFPRuntimeConfigSvc.getInstance().getConfigFilePath(cofigFile).toFile());
			emailPro = new Properties();
			emailPro.load(fis);
		} catch (IOException e)
		{
			_logger.error("FFPEmailUtils", e);
		}
		finally
		{
			try
			{
				if(fis != null)
					fis.close();
			} catch (IOException e)
			{
				_logger.error("FFPEmailUtils", e);
			}
		}
	}
	
	public static void sendMaintainEmail(String Subject, String Content)
	{
		try
		{
			Authenticator auth = new Authenticator(){
				 public PasswordAuthentication getPasswordAuthentication(){
				    return new PasswordAuthentication(emailPro.getProperty("mail.server.account"), emailPro.getProperty("mail.server.password"));
				            }
				        };
			
			Session session = Session.getInstance(emailPro, auth);
			
			session.setDebug(Boolean.valueOf(emailPro.getProperty("mail.debug")));
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailPro.getProperty("mail.server.from"), "", FFPConstants.ENCODING_UTF8));
			String[] recipients = emailPro.getProperty("mail.maintain.recipients").split(";");
			InternetAddress[] recipientsArr = new InternetAddress[recipients.length];
			for(int i=0;i<recipients.length;i++){
				recipientsArr[i] = new InternetAddress(recipients[i], "", FFPConstants.ENCODING_UTF8);
			}
			message.addRecipients(MimeMessage.RecipientType.TO, recipientsArr);
			
			if(emailPro.getProperty("mail.maintain.copies") != null && !"".equals(emailPro.getProperty("mail.maintain.copies")))
			{
				String[] copies = emailPro.getProperty("mail.maintain.copies").split(";");
				InternetAddress[] copiesArr = new InternetAddress[copies.length];
				for(int i=0;i<copies.length;i++){
					copiesArr[i] = new InternetAddress(copies[i], "", FFPConstants.ENCODING_UTF8);
				}
				message.addRecipients(MimeMessage.RecipientType.CC, copiesArr);
			}
			
			message.setSubject(Subject, FFPConstants.ENCODING_UTF8);
			message.setContent(Content, "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
			
			Transport transport = session.getTransport();
			transport.connect(emailPro.getProperty("mail.server.account"), emailPro.getProperty("mail.server.password"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e)
		{
			_logger.error("FFPEmailUtils", e);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		FileInputStream fis = new FileInputStream("C:/Users/qingzhizi/working/Project/FFP/Workspaces/FFPWorkspace1/ffp_resources/env/DEVNOMQ/email.properties");
		emailPro = new Properties();
		emailPro.load(fis);
		fis.close();
		sendMaintainEmail("test","test");
	}
}
