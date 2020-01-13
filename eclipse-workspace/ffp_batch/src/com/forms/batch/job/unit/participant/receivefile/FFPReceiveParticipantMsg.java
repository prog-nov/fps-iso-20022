package com.forms.batch.job.unit.participant.receivefile;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import com.forms.ffp.core.define.FFPConstants;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.CommonAPI;

public class FFPReceiveParticipantMsg extends BatchBaseJob
{
	public static final String TOKEN_FILE_NAME = "/TOKEN/BATCH_RECEIVEMSG_RUNNING.TOKEN";
	
	private File tokenFile = null;
	
	private int port;
	
	private ThreadGroup threadGroup;
	
	public void init() throws BatchJobException
	{
		try
		{
			port = Integer.valueOf(BatchEnvBuilder.getInstance().getEnv("RECEIVE_FILE_SERVER_PORT"));
			threadGroup = new ThreadGroup("FFP AGENT LISTENER");
			tokenFile = new File(this.batchData + TOKEN_FILE_NAME);
			
		} catch (Exception e)
		{
			throw new BatchJobException();
		}
	}
	
	//  @SuppressWarnings("unchecked")
	//  @Override
	public boolean execute() throws BatchJobException
	{
		if(!tokenFile.exists())
		{
			try
			{
				this.batchLogger.info(tokenFile.getPath() + " will be create!");
				if(!tokenFile.getParentFile().exists())
					tokenFile.getParentFile().mkdirs();
				tokenFile.createNewFile();
				this.batchLogger.info(tokenFile.getPath() + " created!");
			}
			catch(Exception ip_e)
			{
				this.batchLogger.info(tokenFile.getPath() + " delete!");
				tokenFile.delete();
				throw new BatchJobException(ip_e);
			}
			
			ServerSocket serverSocket = null;
			try
			{
				serverSocket = new ServerSocket(port);
				Socket socket = null;
				this.batchLogger.info("***服务器即将启动，等待客户端的连接***");
				updateListenerStat(FFPConstants.LISTENER_STATUS_RUNNING);
				while(true)
				{
					if(!tokenFile.exists())
						break;
					socket = serverSocket.accept();
					Thread thread = new Thread(threadGroup, new FFPReceiveFileServerThread(socket));
					thread.start();
				}
				
				// 进入中止程序
				batchLogger.info("***BATCH Listener Processor: Stop listener message");
				updateListenerStat(FFPConstants.LISTENER_STATUS_STOP_PROCESSING);
				if(serverSocket != null)
				{
					serverSocket.close();
				}
				batchLogger.info("***BATCH Listener Processor: check running message");
				while(threadGroup.activeGroupCount() > 0)
				{
					batchLogger.info("***BATCH Listener Processor: " + threadGroup.activeGroupCount() + " message processing");
					Thread.sleep(3 * 1000);
				}
				updateListenerStat(FFPConstants.LISTENER_STATUS_CLOSE);
				batchLogger.info("***BATCH Listener Processor Normal Closed***");
				return true;
			}
			catch(Exception ip_e)
			{
				this.batchLogger.info("BATCH Listener Processor Exit");
				try
				{
					serverSocket.close();
					updateListenerStat(FFPConstants.LISTENER_STATUS_CLOSE);
				}
				catch(Exception ip_ee)
				{
					this.batchLogger.error(ip_ee);
				}
				this.batchLogger.info(tokenFile.getPath() + " delete!");
				tokenFile.delete();
				throw new BatchJobException(ip_e);
			}
		}
		else
		{
			System.out.println("BATCH Receive Message Processor is running!!!!!!");
			this.batchLogger.warn("BATCH Receive Message Processor is running!!!!!!");
			return true;
		}
	}
	
	public boolean close() throws BatchJobException
	{
		if(threadGroup != null)
			threadGroup.destroy();
		return true;
	}
	
	private void updateListenerStat(String stat) throws Exception
	{
		String sql = "UPDATE " + CommonAPI.schema + ".TB_SS_SYSTEM SET BATCH_LISTENER_STAT=? WHERE DATA_KEY='0000'";
		EntityManager.update(sql, stat);
	}
}
