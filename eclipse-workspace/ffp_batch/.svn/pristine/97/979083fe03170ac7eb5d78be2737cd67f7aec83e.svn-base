package com.forms.batch.util;

import java.util.Arrays;

import javax.mail.internet.InternetAddress;



/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SMTP配置信息 <br>
 * Author : ChenJiajun <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2018-2-22 <br>
 */

public class SMTPEmailProp {
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
     * 发送邮箱账号
     */
	private InternetAddress from;
	
	/**
     * 接收邮箱账号
     */
	private InternetAddress[] recipients;
	
	/**
	 * 抄送邮箱账号
	 */
	private InternetAddress[] copies;
	
	/**
	 * 密送邮箱账号
	 */
	private InternetAddress[] BlindCarbonCopies;
	
	/**
     * 协议，默认为SMTP，无法修改
     */
	private String protocol = "smtp";
	
	/**
     * SMTP服务器地址
     */
	private String host;
	
	/**
     * SMTP服务器端口，默认25
     */
	private String port = "25";
	
	/**
     * 是否开启身份认证
     */
	private String authEnable;
	
	/**
     * 是否使用ssl
     */
	private boolean sslEnable;
	
	/**
     * 邮件标题
     */
	private String subject;
	
	/**
     * 邮件内容，含附件
     */
	private String content;

	private String socketFactoryPort;
	private String socketFactoryClass;
	
	/**
     * 对非ssl的处理方式：true为处理，false为不处理
     */
	private String socketFactoryFallback;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public InternetAddress getFrom() {
		return from;
	}

	public void setFrom(InternetAddress from) {
		this.from = from;
	}

	public InternetAddress[] getRecipients() {
		return recipients;
	}

	public void setRecipients(InternetAddress[] recipients) {
		this.recipients = Arrays.copyOf(recipients, recipients.length);
	}

	public InternetAddress[] getCopies() {
		return copies;
	}

	public void setCopies(InternetAddress[] copies) {
		this.copies = Arrays.copyOf(copies, copies.length);
	}

	public InternetAddress[] getBlindCarbonCopies() {
		return BlindCarbonCopies;
	}

	public void setBlindCarbonCopies(InternetAddress[] blindCarbonCopies) {
		BlindCarbonCopies = Arrays.copyOf(blindCarbonCopies, blindCarbonCopies.length);
	}

	public String getProtocol() {
		return protocol;
	}

	/*public void setProtocol(String protocol) {
		this.protocol = protocol;
	}*/

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAuthEnable() {
		return authEnable;
	}

	public void setAuthEnable(String authEnable) {
		this.authEnable = authEnable;
	}

	public boolean isSslEnable() {
		return sslEnable;
	}

	public void setSslEnable(boolean sslEnable) {
		this.sslEnable = sslEnable;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public void setSocketFactoryPort(String socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public void setSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
	}

	public String getSocketFactoryFallback() {
		return socketFactoryFallback;
	}

	public void setSocketFactoryFallback(String socketFactoryFallback) {
		this.socketFactoryFallback = socketFactoryFallback;
	}

	@Override
	public String toString() {
		return "SMTPEmailProp [account=" + account + ", password=" + password + ", from=" + from + ", recipients="
				+ Arrays.toString(recipients) + ", copies=" + Arrays.toString(copies) + ", BlindCarbonCopies="
				+ Arrays.toString(BlindCarbonCopies) + ", protocol=" + protocol + ", host=" + host + ", port=" + port
				+ ", authEnable=" + authEnable + ", sslEnable=" + sslEnable + ", subject=" + subject + ", content="
				+ content + ", socketFactoryPort=" + socketFactoryPort + ", socketFactoryClass=" + socketFactoryClass
				+ ", socketFactoryFallback=" + socketFactoryFallback + "]";
	}
	
}
