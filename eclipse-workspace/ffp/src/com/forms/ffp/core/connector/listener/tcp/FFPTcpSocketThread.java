package com.forms.ffp.core.connector.listener.tcp;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.core.connector.listener.handler.FFPTCPMessageHanlder;
import com.forms.ffp.core.define.FFPConstants;

/**
 * socket 线程类
 * 
 * @author yrd
 */
public class FFPTcpSocketThread implements Runnable
{

	private static Logger logger = LoggerFactory.getLogger(FFPTcpSocketThread.class);

	private Socket socket = null;

	public FFPTcpSocketThread(Socket socket)
	{
		this.socket = socket;
	}

	public void run()
	{
		// 处理消息
		Thread thread = Thread.currentThread();
		thread.setPriority(Thread.NORM_PRIORITY);
		String threadLog = "Listener Thread Processing:" + thread.getId();
		logger.info(threadLog);

		String message = null;
		ByteArrayOutputStream result = null;
		try
		{
			DataInputStream is = new DataInputStream(socket.getInputStream());
			
			socket.setSoTimeout(60 * 1000);
			result = new ByteArrayOutputStream();
			byte[] lengthByte = new byte[FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN];
			is.readFully(lengthByte);
			result.write(lengthByte, 0, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN);
			int length = Integer.valueOf(new String(lengthByte, FFPConstants.ENCODING_UTF8));
			
			byte[] dataByte = new byte[length];
			is.readFully(dataByte);
			result.write(dataByte, 0, length);
			
			message = new String(result.toByteArray(), FFPConstants.ENCODING_UTF8);
			logger.info(threadLog + " receive agent request messge=" + message);
			FFPTCPMessageHanlder.handleAsynchronousMsg(message, socket, thread.getId());
		}
		catch(SocketTimeoutException ip_timeout)
		{
			try
			{
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), FFPConstants.ENCODING_UTF8));
				pw.write("SOCKET TIME UP");
				pw.flush();
				socket.shutdownOutput();
			}
			catch(Exception ip_e)
			{
				logger.error(threadLog + " FFPTcpSocketThread.run.SocketTimeoutException" + ip_e.getMessage());
			}
		}
		catch (Exception e)
		{
			logger.error(threadLog, e);
		} finally
		{
			try
			{
				if (result != null)
					result.close();
			} catch (IOException e)
			{
				logger.error(threadLog, e);
			}
			try
			{
				if (socket != null)
					socket.close();
			} catch (IOException e)
			{
				logger.error(threadLog, e);
			}
		}
		logger.info("Listener Thread Completed:" + thread.getId());
	}

}