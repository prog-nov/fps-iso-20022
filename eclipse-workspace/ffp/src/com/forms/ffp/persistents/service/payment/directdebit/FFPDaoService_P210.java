package com.forms.ffp.persistents.service.payment.directdebit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;
import com.forms.ffp.persistents.dao.payment.directdebit.FFPIDao_P210;

@Service("FFPDaoService_P210")
public class FFPDaoService_P210 implements FFPIDaoService_P210 {
	@Resource(name = "FFPIDao_P210")
	private FFPIDao_P210 dao;

	@Override
	public int sInsert(FFPJbP210 form) throws Exception {
		int[] rs = dao.dInsert(form);
		return rs[0];
	}

	@Override
	public int updateJnlStat(FFPJbP210 form) throws Exception {
		int[] rs = dao.updateJnlStat(form);
		return rs[0];
	}

	@Override
	public String inquiryJnlNoBySrcRefNm(String srcRefNm) throws Exception {
		return dao.inquiryJnlNoBySrcRefNm(srcRefNm);
	}
}
