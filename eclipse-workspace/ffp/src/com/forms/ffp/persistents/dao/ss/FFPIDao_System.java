package com.forms.ffp.persistents.dao.ss;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.forms.ffp.persistents.bean.ss.FFPSsSystem;

@Repository("FFPIDao_System")
@Scope("prototype")
public interface FFPIDao_System
{
	public int update(FFPSsSystem form);
	
	public FFPSsSystem inquiry();
	
	public int updateFpsReceiveMode(@Param("fpsReceiveMode") String fpsReceiveMode);
	
	public int updateRealtimeListenerStat(@Param("realtimeListenerStat") String realtimeListenerStat);
	
	public int updateRealtimeControlStat(@Param("realtimeControlStat") String realtimeControlStat);
	
	public int updateBatchListenerStat(@Param("batchListenerStat") String batchListenerStat);
}
