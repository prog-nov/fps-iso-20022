package com.forms.ffp.persistents.service;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;

public interface FFPIDaoService_Txjnl
{
	int sInsert(FFPTxJnl jnl) throws Exception;

	int updateJnl(FFPTxJnl jnl) throws Exception;
	
	public int updateJnlStat(FFPJbBase form) throws Exception;
	
	public Object inquiryByJnlNo(String jnlNo) throws Exception;
	
	public Object inquiryById(String transactionId, String endToEndId) throws Exception;
}
