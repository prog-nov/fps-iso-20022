package com.forms.ffp.persistents.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forms.ffp.persistents.bean.FFPTxJnlAction;

public interface FFPIDaoService_TxjnlAction {

	List<FFPTxJnlAction> inquiryByJnlNo(@Param("jnlNo") String jnlNo);
	
	int insertUpdateTxJnlAction(FFPTxJnlAction jnlAction) throws Exception;
	
	FFPTxJnlAction inquiryJnlActionByMsgId(@Param("msgId") String msgId);
}
