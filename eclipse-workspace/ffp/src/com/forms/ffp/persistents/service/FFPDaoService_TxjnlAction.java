package com.forms.ffp.persistents.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnlAction;

@Service("FFPDaoService_TxjnlAction")
public class FFPDaoService_TxjnlAction implements FFPIDaoService_TxjnlAction {
	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl dao;

	@Resource(name = "FFPIDao_TxJnlAction")
	private FFPIDao_TxJnlAction actionDao;

	@Override
	public int insertUpdateTxJnlAction(FFPTxJnlAction jnlAction) throws Exception {
		int[] rs = actionDao.insertUpdateTxJnlAction(jnlAction);
		return rs[0];
	}

	public List<FFPTxJnlAction> inquiryByJnlNo(String jnlNo){
		return actionDao.inquiryByJnlNo(jnlNo);
	}
	
	public FFPTxJnlAction inquiryJnlActionByMsgId(String msgId)
	{
		return actionDao.inquiryJnlActionByMsgId(msgId);
	}
}
