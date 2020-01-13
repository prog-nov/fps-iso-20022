package com.forms.ffp.persistents.service.tx.m100;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.tx.m100.FFPJbM100;
import com.forms.ffp.persistents.dao.tx.m100.FFPIDao_M100;

@Service("FFPDaoService_M100")
public class FFPDaoService_M100 implements FFPIDaoService_M100
{
	@Resource(name = "FFPIDao_M100")
	private FFPIDao_M100 dao;

	@Override
	public int sInsert(FFPJbM100 form)
	{
		int[] rs = dao.dInsert(form);
		return rs[0];
	}

	@Override
	public FFPJbM100 inquiryM100ByJnlNoOrMsgId(String jnlNo, String msgId)
	{
		return dao.inquiryM100ByJnlNoOrMsgId(jnlNo, msgId);
	}

	@Override
	public int update(FFPJbM100 form)
	{
		int[] update = dao.update(form);
		return update[0];
	}		

}
