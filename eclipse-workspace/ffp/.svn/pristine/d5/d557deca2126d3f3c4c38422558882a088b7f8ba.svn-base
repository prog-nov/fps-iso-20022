package com.forms.ffp.persistents.service.payment.credittransfer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;
import com.forms.ffp.persistents.dao.payment.credittransfer.FFPIDao_P100;

@Service("FFPDaoService_P100")
public class FFPDaoService_P100 implements FFPIDaoService_P100
{
	@Resource(name = "FFPIDao_P100")
	private FFPIDao_P100 dao;

	@Override
	public int sInsert(FFPJbP100 form) throws Exception
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
//	public int[] updateJbP100(FFPJbP100 form) throws Exception {
//		return dao.updateJbP100(form);
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
