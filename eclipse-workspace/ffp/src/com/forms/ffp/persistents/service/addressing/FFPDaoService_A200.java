package com.forms.ffp.persistents.service.addressing;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.addressing.FFPJbA200;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A200;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_Addressing_Acct;

@Service("FFPDaoService_A200")
public class FFPDaoService_A200 implements FFPIDaoService_A200
{
	@Resource(name = "FFPIDao_A200")
	private FFPIDao_A200 a200Dao;
	
	@Resource(name = "FFPIDao_Addressing_Acct")
	private FFPIDao_Addressing_Acct adrsDao;
	

	@Override
	public int sInsert(FFPJbA200 form) throws Exception{
		int[] rs = a200Dao.dInsert(form);
		return rs[0];
	}

	@Override
	public int sUpdateAddressing(FFPJbA200 form, String sts) {
		int[] result = {0};
		if("CNCL".equals(sts)){
			result = a200Dao.dDelete(form);
		}else if("AMND".equals(sts)){
			result = a200Dao.dUpdateAdrsOnly(form);
		}else if("NWRG".equals(sts)){
			//TODO 
//			result = adrsDao.dInsert(form);
		}
		return result[0];
	}
}
