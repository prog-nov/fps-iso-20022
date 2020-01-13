package com.forms.ffp.persistents.service.tx.m100;

import com.forms.ffp.persistents.bean.tx.m100.FFPJbM100;

public interface FFPIDaoService_M100
{
	int sInsert(FFPJbM100 form);

	public FFPJbM100 inquiryM100ByJnlNoOrMsgId(String jnlNo, String msgId);

	int update(FFPJbM100 form);
}
