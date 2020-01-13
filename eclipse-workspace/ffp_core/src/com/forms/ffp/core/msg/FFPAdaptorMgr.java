package com.forms.ffp.core.msg;

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
import com.forms.ffp.core.define.FFPConstantsMsg;
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

	// public FFP_CTI01_OVO sendTcpMsg(String msg) throws FFPSystemException
	// {
	// final String METHOD_NAME = "sendTcpMsg()";
	// try
	// {
	// FFPTcpSenderAgent sender = (FFPTcpSenderAgent)
	// FFPSenderAgentSvc.getSenderAgent(FFPRuntimeConstants.PARTICIPANT_TCP_CONFIG_SELECT);
	//
	// String str = sender.sendSynchronous(msg, null, null);
	// _logger.info(String.format("Agent to FFP response message:%s", str));
	// //System.out.println(str);
	// String type =
	// parseMessageType(str);//str.substring(str.indexOf("<MessageType>"),
	// str.lastIndexOf("</MessageType>"));
	// //type = type.substring(type.indexOf(">") + 1);
	// FFP_CTI01_OVO reply = null;
	//
	// if("FFPCTI01".equals(type))
	// {
	// com.forms.ffp.adaptor.jaxb.participant.response.ROOT root =
	// FFPParticipantMessageConverter.parseXml2ReponseObject(str);
	// if(root != null)
	// {
	// com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01
	// ffpCti01 =
	// (com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01)root.getBODY();
	// com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head =
	// (com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD)root.getHEAD();
	//
	// if(ffpCti01 != null && head != null)
	// {
	// reply = new FFP_CTI01_OVO();
	// //ffpCti01.get
	// reply.setSrcRefNm(ffpCti01.getSrcRefNm());
	// reply.setTransactionId(ffpCti01.getTransactionId());
	// reply.setRsltCd(ffpCti01.getRsltCd());
	// reply.setRejCd(ffpCti01.getRejCd());
	// reply.setRejMsg(ffpCti01.getRejMsg());
	// reply.setResposneRefNo(head.getResponseRefno());
	// reply.setResponseStatus(head.getResponseStatus());
	// }
	// }
	// }
	// else
	// {
	// _logger.info(String.format("Error message type[%s] form FFP Agent",
	// type));
	// throw new RuntimeException(String.format("Error message type[%s] form FFP
	// Agent", type));
	// }
	// return reply;
	// }
	// catch(Exception ex)
	// {
	// ex.printStackTrace();
	// throw new FFPSystemException(
	// ERROR_CODE,
	// CLASS_NAME.getName() + "->" + METHOD_NAME,
	// ex.getMessage(),
	// FFPErrorLevel.ERR_LEVEL_ERRO,
	// ex);
	// }
	//
	// }

	// public FFPSendTcpMessageResp executeTcpMsg(String msg) throws Exception{
	// FFPSendTcpMessageResp resp = new FFPSendTcpMessageResp();
	// FFPTcpSenderAgent sender = (FFPTcpSenderAgent)
	// FFPSenderAgentSvc.getSenderAgent(FFPRuntimeConstants.PARTICIPANT_TCP_CONFIG_SELECT);
	// resp = sender.send(msg, null, null);
	// return resp;
	// }

	public FFPBaseResp execute(Object object) throws Exception
	{
		if (object instanceof FFPMsgBaseHkiclMessage)
		{
			FFPMsgBaseHkiclMessage messageObj = (FFPMsgBaseHkiclMessage) object;

			String message = "";
			try
			{
				message = messageObj.parseHkiclMessage();
			} catch (Exception e)
			{
				throw e;
			}

			FFPSendMessageResp resp = new FFPSendMessageResp();
			try
			{
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
				resp = new FFPSendMessageResp();
				resp.setMessageStatus(FFPConstantsMsg.RESP_MSG_STS_TMOUT);
				resp.setMessage(String.format("Error (%s) : Fail to send message to MQ due to %s - %s",
						new Object[] { FFPDateUtils.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"), e.getClass().getSimpleName(), e.getMessage() }));
				_logger.error(resp.getMessage(), e);
			}

			return resp;

		} else if (object instanceof FFPMsgBaseParticipantMessage || object instanceof FFPMsgBaseResponseParticipantMessage)
		{
			FFPSendTcpMessageResp resp = null;
			String message = null;

			if (object instanceof FFPMsgBaseParticipantMessage)
			{
				FFPMsgBaseParticipantMessage messageObj = (FFPMsgBaseParticipantMessage) object;
				message = messageObj.parseParticipantMessage();
			} else if (object instanceof FFPMsgBaseResponseParticipantMessage)
			{
				FFPMsgBaseResponseParticipantMessage messageObj = (FFPMsgBaseResponseParticipantMessage) object;
				message = messageObj.parseParticipantMessage();
			}

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
		} else
		{
			// TODO
			throw new Exception("TODO");
		}
	}
}
