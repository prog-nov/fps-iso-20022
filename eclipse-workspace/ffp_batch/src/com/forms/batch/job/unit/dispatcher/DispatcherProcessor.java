package com.forms.batch.job.unit.dispatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.dom4j.Element;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;

public class DispatcherProcessor extends BatchBaseJob
{
	public static final String TOKEN_FILE_NAME = "/TOKEN/DISPATCHERPROCESSOR.TOKEN";
	
	private File tokenFile = null;
	
	private int THREAD_POOL = 10;
	
	private int CHECK_TOKEN_PERIOD = 10000;
	
	private List<FFPTimerTask> timerGroupList = new ArrayList<FFPTimerTask>();
	
	public void init() throws BatchJobException
	{
		try
		{
			tokenFile = new File(this.batchData + TOKEN_FILE_NAME);
			
			THREAD_POOL = Integer.valueOf(this.actionElement.element("private-settings").elementText("thread-pool"));
			CHECK_TOKEN_PERIOD = Integer.valueOf(this.actionElement.element("private-settings").elementText("check-token-period"));
			
			@SuppressWarnings("unchecked")
			List<Element> timerGroupEleList = this.actionElement.elements("timer-group");
			if(timerGroupEleList != null)
			{
				for(Element loc_timerGroup : timerGroupEleList)
				{
					@SuppressWarnings("unchecked")
					List<Element> timerEleList = loc_timerGroup.elements("timer");
					if(timerEleList != null)
					{
						for (Element loc_e : timerEleList)
						{
							FFPTimerTask task = new FFPTimerTask();
							task.setName(loc_timerGroup.attributeValue("GROUP_NAME") + "-" + loc_e.elementText("name"));
							task.setCommand(this.batchBin + File.separator + loc_e.elementText("command"));
							if("Y".equals(loc_e.element("delay").attributeValue("IS_TIME")))
							{
								String loc_time = loc_e.elementText("delay");
								Calendar loc_curCal = Calendar.getInstance();
								loc_curCal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(loc_time.substring(0, 2)));
								loc_curCal.set(Calendar.MINUTE, Integer.valueOf(loc_time.substring(3, 5)));
								loc_curCal.set(Calendar.SECOND, Integer.valueOf(loc_time.substring(6, 8)));
								long initDelay  = loc_curCal.getTime().getTime() - System.currentTimeMillis();
								long oneDay = 24 * 60 * 60 * 1000;
								long delay = 0;
								if(initDelay > 0)
								{
									delay = Double.valueOf(Math.ceil(initDelay / 1000.0)).longValue();
								}
								else if(initDelay < 0)
								{
									delay = Double.valueOf(Math.ceil((oneDay + initDelay) / 1000.0)).longValue();
								}
								task.setDelay(delay);
							}
							else
							{
								task.setDelay(Integer.valueOf(loc_e.elementText("delay")));
							}
							task.setNextMethod(loc_e.elementText("next-method"));
							task.setPeriod(Integer.valueOf(loc_e.elementText("period")));
							task.setTimeutil(loc_e.elementText("timeunit"));
							timerGroupList.add(task);
						}
					}
				}
			}
		}
		catch(Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}
	
	@Override
	public boolean execute() throws BatchJobException
	{
		// CHECK EXCUTED
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
			
			ScheduledExecutorService pool = Executors.newScheduledThreadPool(THREAD_POOL);
			this.batchLogger.info("Dispathcher Processor starting...");
			try
			{
				for(FFPTimerTask task : timerGroupList)
				{
					this.batchLogger.info("Loading " + task.getName() + ", run period " + task.getPeriod() + " " + task.getTimeutil());
					if(task.getPeriod() == 0)
					{
						pool.schedule(task, task.getDelay(), TimeUnit.SECONDS);
					}
					else
					{
						if("START".equals(task.getNextMethod()))
						{
							pool.scheduleAtFixedRate(task, task.getDelay(), task.getPeriod(), TimeUnit.SECONDS);
						}
						else
						{
							pool.scheduleWithFixedDelay(task, task.getDelay(), task.getPeriod(), TimeUnit.SECONDS);
						}
					}
				}
			
				while(true)
				{
					Thread.sleep(CHECK_TOKEN_PERIOD);
					if(!tokenFile.exists())
						break;
				}
				
				pool.shutdown();
				while (!pool.awaitTermination(10, TimeUnit.SECONDS))
				{
		            System.out.println("Dispathcer Processor: Job is running! Please wait");
		            this.batchLogger.info("Dispathcer Processor: Job is running! Please wait");
		            //TODO query TB_BH_JOB_JNL
		        }
				System.out.println("Dispathcer Processor normally closed");
				this.batchLogger.info("Dispathcer Processor normally closed");
			}
			catch(Exception ip_e)
			{
				pool.shutdownNow();
				System.out.println("Dispathcer Processor: positive closed");
				this.batchLogger.info("Dispathcer Processor: positive closed");
				this.batchLogger.info(tokenFile.getPath() + " delete!");
				tokenFile.delete();
				throw new BatchJobException(ip_e);
			}
			return true;
		}
		else
		{
			System.out.println("BATCH Dispatcher Processor is running!!!!!!");
			this.batchLogger.warn("BATCH Dispatcher Processor is running!!!!!!");
			return true;
		}
	}
}
