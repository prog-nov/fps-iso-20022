package com.forms.ffp.webapp.cashmanagement.transactionstatus.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPConstantsTxJnl.MSG_STATUS;
import com.forms.ffp.core.define.FFPConstantsTxJnl.TX_STATUS;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.FFPBaseResp;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.tx.inquiry.I110.FFPJbI110;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.tx.inquiry.I110.FFPIDao_I110;

@Service("TransactionStatusService")
@Scope("prototype")
public class TransactionStatusService implements ITransactionStatusService
{

	@Resource(name = "FFPIDao_I110")
	private FFPIDao_I110 dao;
	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl txJnlDao;

	@Override
	public List<FFPJbI110> searchByJnlNo(FFPJbI110 form)
	{
		// TODO Auto-generated method stub
		List<FFPJbI110> dList = dao.searchByJnlNo(form);
		return dList;
	}

	@Override
	public int insert(FFPJbI110 form)
	{

		int[] insert = dao.insert(form);
		return insert[0];
	}

	@Override
	public List<FFPJbI110> insertAndSend(FFPJbI110 form) throws Exception
	{
		boolean flag = false;
		form.setJnlNo(FFPIDUtils.getJnlNo());
		form.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_I110.getCode());
		form.setMsgId(FFPIDUtils.getRefno());
		form.getTxJnl().setJnlNo(form.getJnlNo());
		form.getTxJnl().setTxCode(form.getTxCode());
		form.getTxJnl().setTxSrc(FFPConstants.MSG_TYPE_AGENT);
		form.getTxJnl().setTxMode(FFPConstants.RUNNING_MODE_REALTIME);
		form.getTxJnl().setTxStat(TX_STATUS.TX_STAT_CREAT.getStatus());
		form.getJnlActionList().add(FFPJnlUtils.getInstance().newJnlAction(form.getJnlNo(), form.getMsgId(), FFPConstants.MSG_DIRECTION_OUTWARD, FFPConstants.TX_SOURCE_HKICL,
				FFPJaxbConstants.JAXB_MSG_TYPE_PACS_028, MSG_STATUS.MSG_STAT_MSYNC.getStatus(), new Date(), new Date(), null, null));
		insert(form);
		FFPTxJnl UpdateTxJnl = form.getTxJnl();
		UpdateTxJnl.setTxStat(TX_STATUS.TX_STAT_APPST.getStatus());
		boolean sendResult = perform(form);
		txJnlDao.updateJnlStat(UpdateTxJnl);
		// if send out sucessfull
		if (sendResult)
		{
			// start to search txStat for X seconds
			// 15 second
			// Date date = new Date();
			Date loc_startTime = Calendar.getInstance().getTime();
			while (!flag)
			{
				if ((Calendar.getInstance().getTime().getTime() - loc_startTime.getTime()) / 1000 <= 120)
				{
					FFPTxJnl txJnl = txJnlDao.inquiryByJnlNo(form.getJnlNo());
					if (txJnl != null)
					{
						if (TX_STATUS.TX_STAT_COMPL.getStatus().equals(txJnl.getTxStat()))
						{
							List<FFPJbI110> searchResult = searchByJnlNo(form);
							return searchResult;
						}
					}
					Thread.sleep(2000);
				} else
				{
					flag = true;
				}
			}
		}
		return null;
	}

	public boolean perform(FFPJbI110 form) throws Exception
	{
		// is need insert into DB
		// send to ICl
		boolean loc_PacsMsgStat = false;
		FFPVO_Pacs028 pacs028 = new FFPVO_Pacs028(form);
		TransactionStatusService_Pacs028 locMsg = new TransactionStatusService_Pacs028(pacs028);
		FFPBaseResp response = FFPAdaptorMgr.getInstance().execute(locMsg);
		if (response != null)
		{
			FFPSendMessageResp msgRespFromICL = (FFPSendMessageResp) response;
			if (msgRespFromICL.getJmsMessageId() != null)
			{
				loc_PacsMsgStat = true;
			}
		}
		return loc_PacsMsgStat;
	}

}
