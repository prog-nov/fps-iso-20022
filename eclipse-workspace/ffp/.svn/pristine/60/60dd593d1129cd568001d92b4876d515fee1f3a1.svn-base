package com.forms.ffp.bussiness.utils;

import java.util.Date;

import javax.annotation.Resource;

import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;

public class FFPJnlUtils
{
	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction txJnlActionService;
	
	private static FFPJnlUtils instance = null;
	
	public static FFPJnlUtils getInstance()
	{
		if(instance == null)
			instance = new FFPJnlUtils();
		return instance;
	}
	
	public FFPTxJnlAction newJnlAction(String jnlNo, String msgId,String msgDirection, String systemId, String msgType, String msgStatus, 
			Date msgCreatDate, Date msgProceDate, Date msgComplDate, String refMsgId)
	{
		return newJnlAction(jnlNo, msgId, msgDirection, systemId, msgType, msgStatus, null, null, msgCreatDate, msgProceDate, msgComplDate, refMsgId, null);
	}
	
	public FFPTxJnlAction newJnlAction(String jnlNo, String msgId,String msgDirection, String systemId, String msgType, String msgStatus, String msgCode,String msgResult,
			Date msgCreatTs, Date msgProceTs, Date msgComplTs, String refMsgId, String isAutoCheck)
	{
		FFPTxJnlAction jnlAction = new FFPTxJnlAction();
		jnlAction.setJnlNo(jnlNo);
		jnlAction.setMsgId(msgId);
		jnlAction.setMsgDirection(msgDirection);
		jnlAction.setMsgSystemId(systemId);
		jnlAction.setMsgType(msgType);
		jnlAction.setMsgStatus(msgStatus);
		
		jnlAction.setMsgCode(msgCode);
		jnlAction.setMsgResult(msgResult);
		
		jnlAction.setMsgCreatTs(msgCreatTs);
		jnlAction.setMsgProceTs(msgProceTs);
		jnlAction.setMsgComplTs(msgComplTs);
		jnlAction.setRefMsgId(refMsgId);
		
		jnlAction.setIsAutoCheck(isAutoCheck);
		
		return jnlAction;
	}
}
