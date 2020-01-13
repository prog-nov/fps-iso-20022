package com.forms.ffp.persistents.service.addressing;

import com.forms.ffp.persistents.bean.addressing.FFPJbA200;

public interface FFPIDaoService_A200{
	
	int sUpdateAddressing(FFPJbA200 form, String sts)throws Exception;

	int sInsert(FFPJbA200 form) throws Exception;
}
