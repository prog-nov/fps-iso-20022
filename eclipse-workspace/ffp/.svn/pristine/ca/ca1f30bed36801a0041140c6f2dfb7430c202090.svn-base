package com.forms.ffp.persistents.service.payment.returnrefund;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.ffp.persistents.dao.payment.returnrefund.FFPIDao_P300;

@Service("FFPDaoService_P300")
public class FFPDaoService_P300 implements FFPIDaoService_P300 {
	@Resource(name = "FFPIDao_P300")
	private FFPIDao_P300 dao;


	@Override
	public int sInsert(FFPJbP300 form) throws Exception {
		// TODO Auto-generated method stub
		int[] rs = dao.dInsert(form);
		return rs[0];
	}


	@Override
	public FFPJbP300 inquiryByJnlNo(String jnlNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int  insertTxjnl(FFPTxJnl txjnl) throws Exception {
		int[] rs = dao.insertTXJNL(txjnl);
		return rs[0];
	}


	@Override
	public int updateTxjnl(FFPTxJnl txjnl) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTxjnl(txjnl);
	}

}
