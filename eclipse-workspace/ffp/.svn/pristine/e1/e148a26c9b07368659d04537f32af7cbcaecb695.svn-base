package com.forms.ffp.persistents.service.payment.credittransfer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;
import com.forms.ffp.persistents.dao.payment.credittransfer.FFPIDao_P110;

@Service("FFPDaoService_P110")
public class FFPDaoService_P110 implements FFPIDaoService_P110
{
	@Resource(name = "FFPIDao_P110")
	private FFPIDao_P110 dao;

	@Override
	public int sInsert(FFPJbP110 form) throws Exception
	{
		int[] rs = dao.dInsert(form);
		return rs[0];
	}

	@Override
	public int updateJnlStat(FFPJbBase form) throws Exception
	{
		int[] rs = dao.updateJnlStat(form);
		return rs[0];
	}

//	@Override
//	public int[] updateJbP110(FFPJbP110 form) throws Exception {
//		return dao.updateJbP110(form);
//	}

	@Override
	public String inquiryJnlNoBySrcRefNm(String srcRefNm) throws Exception {
		return dao.inquiryJnlNoBySrcRefNm(srcRefNm);
	}

	@Override
	public String inquiryJnlNoByMsgId(String msgId) throws Exception {
		return dao.inquiryJnlNoByMsgId(msgId) ;
	}
}
