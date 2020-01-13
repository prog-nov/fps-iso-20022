package com.forms.ffp.core.connector.listener.mq.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.bussiness.common.FFPTxSwitchModeSevice;
import com.forms.ffp.core.config.FFPConnectorConfig;
import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.config.tcp.FFPTcpConfig;
import com.forms.ffp.core.connector.listener.FFPListenerAgentInterface;
import com.forms.ffp.core.connector.listener.FFPMqListenerAgentInterface;
import com.forms.ffp.core.connector.listener.FFPTcpListenerAgentInterface;
import com.forms.ffp.core.connector.listener.mq.FFPApacheMqListenerAgent;
import com.forms.ffp.core.connector.listener.mq.FFPIbmWebSphereMqListenerAgent;
import com.forms.ffp.core.connector.listener.tcp.FFPTcpListenerAgent;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.service.FFPConnectorConfigSvc;
import com.forms.ffp.core.service.FFPRuntimeConfigSvc;
import com.forms.ffp.core.utils.FFPCutoffUtils;
import com.forms.ffp.persistents.bean.ss.FFPSsSystem;
import com.forms.ffp.persistents.dao.ss.FFPIDao_System;

@Component("FFPRealTimeListenerControllerThread")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FFPRealTimeListenerControllerThread implements Runnable
{
	private Logger _logger = LoggerFactory.getLogger(FFPRealTimeListenerControllerThread.class);
	
	private boolean controllerStop = false;
	
	private int RETRY_COUNT = 3;
	
	private long RETRY_CHECK_MODE = 60000;

	private Map<String, FFPListenerAgentInterface> listenerAgentMap = new HashMap<String, FFPListenerAgentInterface>();

	@Autowired
	private BeanFactory beanFactory;

	@Resource(name = "FFPIDao_System")
	private FFPIDao_System daoSystem;

	@Autowired
	private FFPCutoffUtils cutoffUtils;

	private int period;

	public void run()
	{
		try
		{
			_logger.info("[FFPRealTimeListenerControllerThread]:START");
			FFPSsSystem system = daoSystem.inquiry();
			if(!FFPConstants.LISTENER_STATUS_NOFUNCTION.equals(system.getRealtimeControlStat()))
			{
				daoSystem.updateRealtimeControlStat(FFPConstants.LISTENER_STATUS_RUNNING);
				while (!controllerStop)
				{
					boolean cutoff = this.cutoffUtils.isCutoff(FFPConstants.CUTOFF_TYPE_FFP);
					if (cutoff)
					{
						int retry = 0;
						boolean receiveModeIsBatch = false;
						while(retry < RETRY_COUNT)
						{
							receiveModeIsBatch = switchFpsReceiveMode(FFPConstants.FPS_RECEIVE_MODE_BATCH);
							if(receiveModeIsBatch)
								break;
						}
						
						if(receiveModeIsBatch)
						{
							stopListener();
						}
						else
						{
							_logger.error("CHANGE RECEIVE MODE TO BATCH FAIL, PLEASE CHECK!!!");
						}
					}
					else
					{
						boolean listenerStart = startListener();
						if(listenerStart)
						{
							int retry = 0;
							boolean receiveModeIsRltm = false;
							while(retry < RETRY_COUNT)
							{
								receiveModeIsRltm = switchFpsReceiveMode(FFPConstants.FPS_RECEIVE_MODE_REALTIME);
								if(receiveModeIsRltm)
									break;
							}
						}
					}
					Thread.sleep(period);
				}
				daoSystem.updateRealtimeControlStat(FFPConstants.LISTENER_STATUS_CLOSE);
			}
		} catch (Exception ip_e)
		{
			_logger.error("[FFPRealTimeListenerControllerThread]:EXIT WITH EXCEPTION", ip_e);
		}
	}

	public void init() throws Exception
	{
		period = FFPRuntimeConfigSvc.getInstance().getRootConfig().getInteger("CUTOFF_CHECK_PERIOD");

		Map<String, FFPConnectorConfig> connectorConfigMap = FFPConnectorConfigSvc.getInstance().getConnectorConfigMap();

		Iterator<String> iter = connectorConfigMap.keySet().iterator();
		while (iter.hasNext())
		{
			String key = iter.next();
			FFPConnectorConfig connectorConfig = connectorConfigMap.get(key);

			if (_logger.isInfoEnabled())
			{
				_logger.info("Start initialize Connector Config for: {}", connectorConfig.getConnectorName());
			}

			if (connectorConfig instanceof FFPMqConfig)
			{
				FFPMqConfig mqConfig = (FFPMqConfig) connectorConfig;
				FFPMqListenerAgentInterface agent = null;
				if (FFPConstants.CONNECTTOR_TYPE_MQ_APACHEMQ.equals(mqConfig.getConnectorType()))
				{
					agent = (FFPMqListenerAgentInterface) this.beanFactory.getBean(FFPApacheMqListenerAgent.class, new Object[] { mqConfig });
				} else if (FFPConstants.CONNECTTOR_TYPE_MQ_WEBPHEREMQ.equals(mqConfig.getConnectorType()))
				{
					agent = (FFPMqListenerAgentInterface) this.beanFactory.getBean(FFPIbmWebSphereMqListenerAgent.class, new Object[] { mqConfig });
				} else
				{
					throw new Exception(mqConfig.getConnectorName() + " type not define!");
				}
				agent.init();
				this.listenerAgentMap.put(key, agent);
			} else if (connectorConfig instanceof FFPTcpConfig)
			{
				FFPTcpConfig tcpConfig = (FFPTcpConfig) connectorConfig;
				FFPTcpListenerAgentInterface agent = (FFPTcpListenerAgentInterface) this.beanFactory.getBean(FFPTcpListenerAgent.class, new Object[] { tcpConfig });
				agent.init();
				this.listenerAgentMap.put(key, agent);
			} else
			{
				throw new Exception(connectorConfig.getConnectorName() + " type not define!");
			}
		}
	}

	public FFPListenerAgentInterface getMqAgent(String mqName)
	{
		if (this.listenerAgentMap == null)
		{
			return null;
		}

		return this.listenerAgentMap.get(mqName);
	}
	
	public void setControllerStop(boolean ip_controllerStop)
	{
		this.controllerStop = ip_controllerStop;
	}
	
	public boolean startListener()
	{
		_logger.info("FFP Listener will be auto start");
		FFPSsSystem system = daoSystem.inquiry();
		_logger.info("FFP Realtime Listener stat " + system.getRealtimeListenerStat());
		if(FFPConstants.LISTENER_STATUS_CLOSE.equals(system.getRealtimeListenerStat()))
		{
			if (listenerAgentMap != null)
			{
				daoSystem.updateRealtimeListenerStat(FFPConstants.LISTENER_STATUS_START_PROCESSING);
				_logger.info("FFP Realtime Listener stat " + FFPConstants.LISTENER_STATUS_START_PROCESSING);
				boolean hasException = false;
				Iterator<String> iter = listenerAgentMap.keySet().iterator();
				while (iter.hasNext())
				{
					String key = iter.next();
					FFPListenerAgentInterface agent = listenerAgentMap.get(key);
					_logger.info("FFP Listener will be start(key=" + key + ")");
					try
					{
						agent.startListeners();
					} catch (Exception e)
					{
						hasException = true;
						_logger.error("Start Listener failure " + agent.getClass().getName(), e);
					}
					if (agent.getStartWithException())
					{
						_logger.error("Start Listener failure " + agent.getClass().getName());
						hasException = true;
						break;
					}
					_logger.info("FFP Listener started(key=" + key + ")");
				}
				
				if(hasException)
				{
					system.setRealtimeListenerStat(FFPConstants.LISTENER_STATUS_CLOSE);
					daoSystem.update(system);
				}
				else
				{
					system.setRealtimeListenerStat(FFPConstants.LISTENER_STATUS_RUNNING);
					system.setRunningMode(FFPConstants.RUNNING_MODE_REALTIME);
					daoSystem.update(system);
				}
				_logger.info("FFP Realtime Listener stat " + FFPConstants.LISTENER_STATUS_RUNNING);
				_logger.info("FFP RealTime Listener started");
			}
			return true;
		}
		else if(FFPConstants.LISTENER_STATUS_RUNNING.equals(system.getRealtimeListenerStat()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean stopListener() throws Exception
	{
		_logger.info("FFP Listener will be stop");
		FFPSsSystem system = daoSystem.inquiry();
		_logger.info("FFP Realtime Listener stat " + system.getRealtimeListenerStat());
		if(FFPConstants.LISTENER_STATUS_RUNNING.equals(system.getRealtimeListenerStat()))
		{
			if (listenerAgentMap != null)
			{
				daoSystem.updateRealtimeListenerStat(FFPConstants.LISTENER_STATUS_STOP_PROCESSING);
				_logger.info("FFP Realtime Listener stat " + FFPConstants.LISTENER_STATUS_STOP_PROCESSING);
				Iterator<String> iter = listenerAgentMap.keySet().iterator();
				boolean hasException = false;
				while (iter.hasNext())
				{
					String key = iter.next();
					FFPListenerAgentInterface agent = listenerAgentMap.get(key);
					_logger.info("FFP Listener will be stop(key=" + key + ")");
					try
					{
						agent.onDestory();
					} catch (Exception e)
					{
						hasException = true;
						_logger.error("Stop Listener failure" + agent.getClass().getName(), e);
					}
					_logger.info("FFP Listener stopped(key=" + key + ")");
				}
				
				if(hasException)
				{
					system.setRealtimeListenerStat(FFPConstants.LISTENER_STATUS_RUNNING);
					daoSystem.update(system);
				}
				else
				{
					system.setRealtimeListenerStat(FFPConstants.LISTENER_STATUS_CLOSE);
					system.setRunningMode(FFPConstants.RUNNING_MODE_BATCH);
					daoSystem.update(system);
				}
				
				_logger.info("FFP Realtime Listener stop " + FFPConstants.LISTENER_STATUS_CLOSE);
				_logger.info("FFP RealTime Listener stopped:");
			}
			return true;
		}
		else if(FFPConstants.LISTENER_STATUS_CLOSE.equals(system.getRealtimeListenerStat()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean switchFpsReceiveMode(String changeMode)
	{
		FFPSsSystem system = daoSystem.inquiry();
		if(changeMode.equals(system.getFpsReceiveMode()))
		{
			return true;
		}
		else
		{
			FFPTxSwitchModeSevice mode = beanFactory.getBean(FFPTxSwitchModeSevice.class);
			mode.init(changeMode, null);
			mode.perform();
			long curtime = System.currentTimeMillis();
			while((System.currentTimeMillis() - curtime) / RETRY_CHECK_MODE <= 3)
			{
				system = daoSystem.inquiry();
				if(changeMode.equals(system.getFpsReceiveMode()))
				{
					return true;
				}
				try
				{
					Thread.sleep(RETRY_CHECK_MODE);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
