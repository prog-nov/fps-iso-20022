package com.forms.ffp.webapp.cashmanagement.accountinquery.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.util.Tool;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.tx.inquiry.I100.FFPJbI100;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.tx.inquiry.I100.FFPIDao_I100;
import com.forms.ffp.webapp.cashmanagement.accountinquery.form.AccountBalanceInquiryForm;

@Service("AccountInquiryService")
@Scope("prototype")
public class AccountBalanceInquiryService implements IAccountBalanceInquiryService
{
	@Resource(name = "FFPIDao_I100")
	private FFPIDao_I100 dao;
	
	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl jnlDao;

	@Override
	public AccountBalanceInquiryForm queryCcy(AccountBalanceInquiryForm form)
	{
		AccountBalanceInquiryService_Camt060 camt060 = new AccountBalanceInquiryService_Camt060(form);
		
		FFPJbI100 loc_jb = new FFPJbI100();
		loc_jb.setMsgId(camt060.getMsgID());
		loc_jb.setCcy(form.getCurrency());
		loc_jb.setJnlNo(FFPIDUtils.getJnlNo());
		FFPTxJnl txJnl = new FFPTxJnl();
		txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_I100.getCode());
		txJnl.setJnlNo(loc_jb.getJnlNo());
		txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
		txJnl.setTxSrc(FFPConstants.TX_SOURCE_HKICL);
		txJnl.setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
		txJnl.setTransactionId(null);
		txJnl.setEndToEndId(null);
		Date loc_date = new Date();
		txJnl.setCreateTs(loc_date);
		txJnl.setLastUpdateTs(loc_date);
		loc_jb.setTxJnl(txJnl);
		
		List<FFPTxJnlAction> actionList = new ArrayList<FFPTxJnlAction>();
		FFPTxJnlAction action = FFPJnlUtils.getInstance().newJnlAction(txJnl.getJnlNo(), camt060.getMsgID(),
				FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.RELATION_SYSTEM_HKICL, camt060.getMsgTypeName(),
				FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), loc_date, loc_date, loc_date, null);
		actionList.add(action);
		loc_jb.setJnlActionList(actionList);
		
		FFPSendMessageResp resp = FFPAdaptorMgr.getInstance().execute(camt060);
		action.setMsgStatus(resp.getMessageStatus());
		if(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus().equals(resp.getMessageStatus()))
		{
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus());
		}
		else
		{
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus());
		}
		dao.dInsert(loc_jb);
		
		boolean hasReturn = false;
		long loc_cur = System.currentTimeMillis();
		while((System.currentTimeMillis() - loc_cur) / 1000 <= 6)
		{
			FFPTxJnl jnl = jnlDao.inquiryByJnlNo(txJnl.getJnlNo());
			if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus().equals(jnl.getTxStat()))
			{
				hasReturn = true;
				break;
			}
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
			}
		}
		
		if(hasReturn)
		{
			FFPJbI100 loc_i100 = dao.inquiryByJnlNo(txJnl.getJnlNo());
			DecimalFormat format = new DecimalFormat("0,000.00");
			String loc_amt = format.format(loc_i100.getBalance());
			if("DBIT".equals(loc_i100.getCreditDebitInd()))
			{
				loc_amt = "-" + loc_amt;
			}
			form.setBalance(loc_amt);
		}
		else
		{
			form.setBalance(Tool.LOCALE.getMessage("ERRMSG.I1000001"));
		}
		return form;
	}
}
