package com.forms.ffp.persistents.service.addressing;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A100;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_Addressing_Acct;

@Service("FFPDaoService_A100")
public class FFPDaoService_A100 implements FFPIDaoService_A100
{
	@Resource(name = "FFPIDao_A100")
	private FFPIDao_A100 a100Dao;
	
	@Resource(name = "FFPIDao_Addressing_Acct")
	private FFPIDao_Addressing_Acct adrsDao;
	

	@Override
	public int sInsert(FFPJbA100 form) throws Exception
	{
		int[] rs = a100Dao.dInsert(form);
		return rs[0];
	}

	@Override
	public int updateJnlStat(FFPJbBase form) throws Exception
	{
		int[] rs = a100Dao.updateJnlStat(form);
		return rs[0];
	}

	@Override
	public int sUpdateA100(FFPJbA100 form) throws Exception {
		int[] rs = a100Dao.dUpdateA100(form);
		return rs[0];
	}

	@Override
	public String inqueryJnlNoByMsgId(String msgId) {
		return a100Dao.inqueryJnlNoByMsgId(msgId);
	}



	@Override
	public FFPJbA100 inqueryJbA100ByJnlNo(String jnlNo) {
		return a100Dao.inqueryJbA100ByJnlNo(jnlNo);
	}

	@Override
	public FFPJbA100 inqueryAdrs(String CusId, String ProxyId, String ProxyIdTp) {
		
		return a100Dao.inqueryAdrs(CusId, ProxyId, ProxyIdTp);
	}

	

//	@Override
//	public int sInsertTxJnlAndJnlAction(FFPJbA100 form) throws Exception {
//		int[] rs = a100Dao.dInsertTxJnlAndJnlAction(form);
//		return rs[0];
//	}

//	@Override
//	public int sUpdateA100ProxyId(String jnlNo) throws Exception {
//		int rs[] = a100Dao.dUpdateA100ProxyId(jnlNo);
//		return rs[0];
//	}

	

	
	
}
