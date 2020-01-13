package com.forms.ffp.persistents.service.addressing;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A120;

@Service("FFPDaoService_A120")
public class FFPDaoService_A120 implements FFPIDaoService_A120
{
	
	@Resource(name="FFPIDao_A120")
	private FFPIDao_A120 dao;
	
	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl txjnlDao;

	@Override
	public int updateA120Status(String status, String jnlNo) {
		return dao.updateA120Status(status, jnlNo);
	}

	@Override
	public int insertA120Msg(List<FFPJbA120> list) {
		return dao.insertA120Msg(list);
	}

	@Override
	public int[] updateJnlStat(FFPJbBase form) {
		// TODO Auto-generated method stub
		return dao.updateJnlStat(form);
	}

	
	@Override
	public FFPTxJnl iqueryTXJNL(String jnlNo) {
		// TODO Auto-generated method stub
		return txjnlDao.inquiryByJnlNo(jnlNo);
	}
	
	
}
