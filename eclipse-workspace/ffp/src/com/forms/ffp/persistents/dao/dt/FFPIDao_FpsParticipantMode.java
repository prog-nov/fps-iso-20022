package com.forms.ffp.persistents.dao.dt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.ffp.persistents.bean.dt.FFPDtFpsParticipantMode;

@Repository("FFPIDao_FpsParticipantMode")
public interface FFPIDao_FpsParticipantMode
{
	public List<FFPDtFpsParticipantMode> dList();
	
	public int insertUpdate(FFPDtFpsParticipantMode mode);
}
