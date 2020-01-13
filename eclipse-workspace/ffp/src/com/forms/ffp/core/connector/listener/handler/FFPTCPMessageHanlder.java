package com.forms.ffp.core.connector.listener.handler;

import java.net.Socket;

import com.forms.ffp.bussiness.utils.SpringApplicationContextHolder;
import com.forms.ffp.core.connector.listener.msg.FFPBaseMsgListener;
import com.forms.ffp.core.connector.listener.msg.FFPParticipant2FfpAckMsgListener;
import com.forms.ffp.core.connector.listener.msg.FFPParticipant2FfpReqMsgListener;

public class FFPTCPMessageHanlder
{

	public static final String FFPAGENT_MESSAGE_TYPE_FFPCTO01 = "FFPCTO01";
	public static final String FFPAGENT_MESSAGE_TYPE_FFPDBI01 = "FFPDBI01";

	public static void handleSynchronousResponseMessage(String respMessage, Socket socket, long threadId) throws Exception
	{
		Object object = SpringApplicationContextHolder.getSpringBean("com.forms.ffp.core.connector.listener.msg.FFPParticipant2FfpAckMsgListener");
		FFPParticipant2FfpAckMsgListener listener = (FFPParticipant2FfpAckMsgListener) object;
		listener.onTcpMessage(respMessage, socket, threadId);
	}

	public static void handleAsynchronousMsg(String msg, Socket socket, long threadId) throws Exception
	{
		Object object = SpringApplicationContextHolder.getSpringBean("com.forms.ffp.core.connector.listener.msg.FFPParticipant2FfpReqMsgListener");
		FFPBaseMsgListener listener = (FFPParticipant2FfpReqMsgListener) object;
		listener.onTcpMessage(msg, socket, threadId);
	}
}
