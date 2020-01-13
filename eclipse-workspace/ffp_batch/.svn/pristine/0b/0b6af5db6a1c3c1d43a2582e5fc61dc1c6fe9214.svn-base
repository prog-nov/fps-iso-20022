package com.forms.batch.util;

import com.forms.batch.util.SMTPEmailProp;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 邮件工具，目前只支持smtp协议<br>
 * Author : ChenJiajun <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2018-2-22<br>
 */
public class Email {
	public final static String DEFAULT_CHARACTER_ENCODING = "UTF-8";
	public final static String DEFAULT_PAGE_FORMAT = "text/html;charset=UTF-8";
	public final static String DEFAULT_NICK_NAME = "";

	public static void send(SMTPEmailProp emailProps) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", emailProps.getProtocol());
		props.setProperty("mail.smtp.host", emailProps.getHost());
		props.setProperty("mail.smtp.auth", emailProps.getAuthEnable());
		props.setProperty("mail.smtp.port", emailProps.getPort());

		// 启用ssl
		if (emailProps.isSslEnable()) {
			props.put("mail.smtp.ssl.enable", emailProps.isSslEnable());
			props.setProperty("mail.smtp.socketFactory.class", emailProps.getSocketFactoryClass());
			props.setProperty("mail.smtp.socketFactory.fallback", emailProps.getSocketFactoryFallback());
			props.setProperty("mail.smtp.socketFactory.port", emailProps.getSocketFactoryPort());
		}

		Session session = Session.getInstance(props);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);

		// 发送人
		message.setFrom(emailProps.getFrom());

		// 收件人
		message.addRecipients(MimeMessage.RecipientType.TO, emailProps.getRecipients());

		// 抄送人
		message.addRecipients(MimeMessage.RecipientType.CC, emailProps.getCopies());

		// 密送人
		message.addRecipients(MimeMessage.RecipientType.BCC, emailProps.getBlindCarbonCopies());

		message.setSubject(emailProps.getSubject(), DEFAULT_CHARACTER_ENCODING);

		message.setContent(emailProps.getContent(), DEFAULT_PAGE_FORMAT);

		message.setSentDate(new Date());

		message.saveChanges();

		Transport transport = session.getTransport();

		transport.connect(emailProps.getAccount(), emailProps.getPassword());

		transport.sendMessage(message, message.getAllRecipients());

		transport.close();
	}

}