package com.forms.ffp.persistents.dao.ss;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.ffp.persistents.bean.ss.FFPSsCutoff;

@Repository("FFPIDao_Cutoff")
public interface FFPIDao_Cutoff
{
	public FFPSsCutoff inquiry(@Param("cutoffType") String cutoffType);
	
	public List<FFPSsCutoff> inqueryAllCutoffType();
	
	public int sUpdate(FFPSsCutoff cutoff);
}
