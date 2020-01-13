package com.forms.ffp.core.msg;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.core.config.runtime.FFPRuntimeConstants;
import com.forms.ffp.core.connector.sender.FFPSenderAgentSvc;
import com.forms.ffp.core.connector.sender.mq.FFPMqSenderAgent;
import com.forms.ffp.core.connector.sender.tcp.FFPTcpSenderAgent;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.exception.FFPTeErrorMsg;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPDateUtils;

public class FFPAdaptorMgr
{
	public static final Class<FFPAdaptorMgr> CLASS_NAME = FFPAdaptorMgr.class;
	public static final String ERROR_CODE = FFPTeErrorMsg.getErrorCode(CLASS_NAME, 0);
	private static Logger _logger = LoggerFactory.getLogger(FFPAdaptorMgr.class);
	private static FFPAdaptorMgr instance = null;

	private FFPAdaptorMgr()
	{
	}

	public static FFPAdaptorMgr getInstance()
	{
		if (instance == null)
			instance = new FFPAdaptorMgr();
		return instance;
	}

	public FFPSendMessageResp execute(FFPMsgBaseHkiclMessage ip_object)
	{
		FFPSendMessageResp resp = null;
		FFPMsgBaseHkiclMessage messageObj = ip_object;
		String message = "";
		try
		{
			message = messageObj.parseHkiclMessage();
			FFPMqSenderAgent sender = (FFPMqSenderAgent) FFPSenderAgentSvc.getSenderAgent(FFPRuntimeConstants.ICL_MQ_CONFIG_SELECT);
			if (FFPConstants.SEND_TYPE_REQ.equals(messageObj.getSendType()))
			{
				resp = sender.sendRequest(message, null, messageObj.getPriority());
			} else if (FFPConstants.SEND_TYPE_ACK.equals(messageObj.getSendType()))
			{
				resp = sender.sendAcknowledge(message, null, messageObj.getPriority());
			}
			resp.setMessageStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
			resp.setMessage(String.format("The message is sent to target MQ. (%s)", new Object[] { FFPDateUtils.getDateStr(new Date(resp.getSentSysTime().longValue()), "yyyy-MM-dd HH:mm:ss") }));
			
		} catch (Exception e)
		{
			_logger.error("FFPBaseResp.execute(FFPMsgBaseHkiclMessage)Error Msg:" + e.getMessage());
			if(resp == null)
			{
				resp = new FFPSendMessageResp();
			}
			resp.setMessageStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
			resp.setMessage(String.format("The message isn't sent to target MQ. (%s)", new Object[] { FFPDateUtils.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss") }));
			resp.setSentSysTime(System.currentTimeMillis());
		}
		return resp;
	}

	public FFPSendTcpMessageResp execute(FFPMsgBaseParticipantMessage messageObj) throws Exception
	{
		FFPSendTcpMessageResp resp = null;
		String message = messageObj.parseParticipantMessage();

		_logger.info("SEND request message to agent. message=" + message);
		long loc_time1 = 0;
		long loc_time2 = 0;

		try
		{
			try
			{
				loc_time1 = System.currentTimeMillis();
			} catch (Exception ip_e)
			{
			}

			FFPTcpSenderAgent sender = (FFPTcpSenderAgent) FFPSenderAgentSvc.getSenderAgent(FFPRuntimeConstants.PARTICIPANT_TCP_CONFIG_SELECT);
			resp = sender.send(message);
			_logger.info("Receive response message from agent. message=" + resp.getRespMessage());
			try
			{
				loc_time2 = System.currentTimeMillis();
			} catch (Exception ip_e)
			{
			}
		} catch (Exception ip_e)
		{
			throw ip_e;
		} finally
		{
			if (loc_time1 != 0 && loc_time2 != 0)
			{
				SimpleDateFormat loc_sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
				String loc_logInfo = "[S=" + loc_sdf.format(new Date(loc_time1)) + ";R=" + loc_sdf.format(new Date(loc_time2));
				_logger.info(loc_logInfo);
			}
		}

		return resp;
	}
	
	public FFPSendTcpMessageResp execute(FFPMsgBaseResponseParticipantMessage messageObj) throws Exception
	{
		FFPSendTcpMessageResp resp = null;
		String message = messageObj.parseParticipantMessage();

		_logger.info("SEND response message to agent. message=" + message);
		long loc_time1 = 0;
		long loc_time2 = 0;

		try
		{
			try
			{
				loc_time1 = System.currentTimeMillis();
			} catch (Exception ip_e)
			{
			}

			FFPTcpSenderAgent sender = (FFPTcpSenderAgent) FFPSenderAgentSvc.getSenderAgent(FFPRuntimeConstants.PARTICIPANT_TCP_CONFIG_SELECT);
			resp = sender.send(message);
			_logger.info("Receive response message from agent. message=" + resp.getRespMessage());
			try
			{
				loc_time2 = System.currentTimeMillis();
			} catch (Exception ip_e)
			{
			}
		} catch (Exception ip_e)
		{
			throw ip_e;
		} finally
		{
			if (loc_time1 != 0 && loc_time2 != 0)
			{
				SimpleDateFormat loc_sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
				String loc_logInfo = "[S=" + loc_sdf.format(new Date(loc_time1)) + ";R=" + loc_sdf.format(new Date(loc_time2));
				_logger.info(loc_logInfo);
			}
		}

		return resp;
	}
	
	public FFPSendTcpMessageResp execute(FFPMsgBaseResponseParticipantMessage messageObj, Socket socket) throws Exception
	{
		FFPSendTcpMessageResp resp = new FFPSendTcpMessageResp();
		String message = messageObj.parseParticipantMessage();

		_logger.info("SEND response message to agent. message=" + message);
		long loc_time1 = 0;
		long loc_time2 = 0;

		try
		{
			try
			{
				loc_time1 = System.currentTimeMillis();
			} catch (Exception ip_e)
			{
			}

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), FFPConstants.ENCODING_UTF8));
			pw.write(message);
			pw.flush();
			socket.shutdownOutput();
			resp.setTimeOut(false);
			try
			{
				loc_time2 = System.currentTimeMillis();
			} catch (Exception ip_e)
			{
			}
		} catch (Exception ip_e)
		{
			resp.setTimeOut(false);
			_logger.warn("", ip_e);
			throw ip_e;
		} finally
		{
			if (loc_time1 != 0 && loc_time2 != 0)
			{
				SimpleDateFormat loc_sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
				String loc_logInfo = "[S=" + loc_sdf.format(new Date(loc_time1)) + ";R=" + loc_sdf.format(new Date(loc_time2));
				_logger.info(loc_logInfo);
			}
		}

		return resp;
	}
}
