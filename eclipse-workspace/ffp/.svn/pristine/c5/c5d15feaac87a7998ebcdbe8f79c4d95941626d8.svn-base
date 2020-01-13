package com.forms.ffp.persistents.service.addressing;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.addressing.FFPJbA100;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_Addressing_Acct;

@Service("FFPDaoService_Addressing_Acct")
public class FFPDaoService_Addressing_Acct implements FFPIDaoService_Addressing_Acct {
	
	@Resource(name="FFPIDao_Addressing_Acct")
	private FFPIDao_Addressing_Acct acctDao;


	@Override
	public int sDeleteAdrs(FFPJbA100 form) {
		int rs[] = acctDao.dDeleteAdrs(form);
		return rs[0];
	}

	@Override
	public int sUpdateAdrs(FFPJbA100 form) {
		int rs[] = acctDao.dUpdateAdrs(form);
		return rs[0];
	}
	
}
