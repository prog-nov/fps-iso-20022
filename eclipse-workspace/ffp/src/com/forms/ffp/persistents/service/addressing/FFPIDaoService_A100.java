package com.forms.ffp.persistents.service.addressing;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;

public interface FFPIDaoService_A100
{
	int sInsert(FFPJbA100 form) throws Exception;
	
	int updateJnlStat(FFPJbBase form) throws Exception;

	int sUpdateA100(FFPJbA100 form) throws Exception;
	
	String inqueryJnlNoByMsgId(String msgId);
	
	FFPJbA100 inqueryJbA100ByJnlNo(String jnlNo);
	
	FFPJbA100 inqueryAdrs(String CusId,String ProxyId,String ProxyIdTp);
	
}
