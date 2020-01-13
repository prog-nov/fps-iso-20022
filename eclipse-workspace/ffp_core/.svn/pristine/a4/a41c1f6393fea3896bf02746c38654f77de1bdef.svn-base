package com.forms.ffp.core.connector.sender.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.core.config.tcp.FFPTcpConfig;
import com.forms.ffp.core.connector.sender.FFPSenderAgentInterface;
import com.forms.ffp.core.exception.FFPErrorLevel;
import com.forms.ffp.core.exception.FFPSystemException;
import com.forms.ffp.core.exception.FFPTeErrorMsg;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;

public class FFPTcpSenderAgent implements FFPSenderAgentInterface
{
	public static final Class<FFPTcpSenderAgent> CLASS_NAME = FFPTcpSenderAgent.class;
	public static final String ERROR_CODE = FFPTeErrorMsg.getErrorCode(CLASS_NAME, 0);
	private static Logger logger = LoggerFactory.getLogger(CLASS_NAME);

	protected FFPTcpConfig config;

	public FFPTcpSenderAgent(FFPTcpConfig config){
		this.config = config;
		init();
	}

	public void init()
	{
		
	}

	//send to FFPAGENT
	public FFPSendTcpMessageResp send(String message) throws FFPSystemException
	{
		final String METHOD_NAME = "send()";
		String messageReturn = null;
		Socket socket = null;
		StringBuffer sb = new StringBuffer();
		FFPSendTcpMessageResp messageResp = new FFPSendTcpMessageResp();
		
		try {
			String sendHost = config.getSendhost();
			int sendPort = config.getSendport();
			socket = new Socket(sendHost, sendPort);
			BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufOut.write(message);
			bufOut.flush();
			socket.shutdownOutput();
			
			//return 
			BufferedReader bufIn = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			long startTimeMillis = System.currentTimeMillis();
			int timeOut = config.getReceiveTimeOut();
			
			long curSec = -1;
			String line = null;
			messageResp.setTimeOut(true);
			
			while(curSec < (startTimeMillis+timeOut*1000)){
				curSec = System.currentTimeMillis();
				while((line = bufIn.readLine())!=null){
					messageResp.setTimeOut(false);
					sb.append(line);
					messageReturn = sb.toString();
					messageResp.setRespMessage(messageReturn);
				}
			}
			
		} catch (Exception ex) {
			
			logger.error("Send Message To FFP Agent had error", ex);
			throw new FFPSystemException(
					ERROR_CODE,
					CLASS_NAME.getName() + "->" + METHOD_NAME, 
					ex.getMessage(), 
					FFPErrorLevel.ERR_LEVEL_ERRO, 
					ex);
		}finally {
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return messageResp;
	}
	
	

	public FFPTcpConfig getConfig() {
		return config;
	}

	public void setConfig(FFPTcpConfig config) {
		this.config = config;
	}

	
//	public static void main(String[] args) {
//		long startSec = System.currentTimeMillis();
//		System.out.println(startSec);
//	}
}
