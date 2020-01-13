package com.forms.ffp.persistents.service.addressing;

import java.util.List;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;

public interface FFPIDaoService_A120dtl
{
	int sInsert(List<FFPJbA120dtl> form) throws Exception;

	int updateJnlStat(FFPJbBase form) throws Exception;
	
	
	List<FFPJbA120dtl> inqueryAllDtlFrA120Dtl(String jnlNo);
}
