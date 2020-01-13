package com.forms.ffp.persistents.service.payment.directdebit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP200;
import com.forms.ffp.persistents.dao.payment.directdebit.FFPIDao_P200;

@Service("FFPDaoService_P200")
public class FFPDaoService_P200 implements FFPIDaoService_P200 {
	@Resource(name = "FFPIDao_P200")
	private FFPIDao_P200 dao;

	@Override
	public int sInsert(FFPJbP200 form) throws Exception {
		int[] rs = dao.dInsert(form);
		return rs[0];
	}

	@Override
	public int updateJnlStat(FFPJbP200 form) throws Exception {
		int[] rs = dao.updateJnlStat(form);
		return rs[0];
	}

	@Override
	public String inquiryJnlNoBySrcRefNm(String srcRefNm) throws Exception {
		return dao.inquiryJnlNoBySrcRefNm(srcRefNm);
	}
}
