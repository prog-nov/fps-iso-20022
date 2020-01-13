package com.forms.ffp.webapp.cashmanagement.transactionstatus.service;

import java.util.List;

import com.forms.ffp.persistents.bean.tx.inquiry.I110.FFPJbI110;

public interface ITransactionStatusService
{
	List<FFPJbI110> searchByJnlNo(FFPJbI110 form);

	int insert(FFPJbI110 form);

	List<FFPJbI110> insertAndSend(FFPJbI110 form) throws Exception;
}
