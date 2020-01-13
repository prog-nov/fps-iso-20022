package com.forms.ffp.persistents.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;

@Repository("FFPIDao_TxJnlAction")
public interface FFPIDao_TxJnlAction {

	public List<FFPTxJnlAction> inquiryByJnlNo(@Param("jnlNo") String jnlNo);

	@SqlRef("SingleInsertUpdateTxJnlAction")
	public int[] insertUpdateTxJnlAction(FFPTxJnlAction jnlAction);

	public FFPTxJnlAction inquiryJnlActionByMsgId(@Param("msgId") String msgId);
}
