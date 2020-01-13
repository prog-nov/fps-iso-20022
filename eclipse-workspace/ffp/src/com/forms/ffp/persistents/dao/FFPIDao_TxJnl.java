package com.forms.ffp.persistents.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;

@Repository("FFPIDao_TxJnl")
public interface FFPIDao_TxJnl {
	@Executes({ @Execute(sqlRef = @SqlRef("INSERT_TXJNL"), param = @BatchParam(false)) })
	public int[] dInsert(FFPTxJnl jnl);

	@Executes({
			// 更改交易状态
			@Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT"),param = @BatchParam(false)) })
	public int[] updateJnlStat(FFPTxJnl txJnl);

	@Executes({ @Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT"), param = @BatchParam(value = false, property = "txJnl")),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION"), param = @BatchParam(item = "jnlAction", property = "jnlActionList")), })
	public int[] updateJnlStatAndAction(FFPJbBase form);

	/**
	 * 根据交易流水号查找对应的交易
	 * 
	 * @param docId
	 * @return
	 */
	FFPTxJnl inquiryByJnlNo(@Param("jnlNo") String jnlNo);

	/**
	 * 根据交易流水号查找对应的交易
	 * 
	 * @param docId
	 * @return
	 */
	FFPTxJnl inquiryById(@Param("transactionId") String transactionId, @Param("endToEndId") String endToEndId);
	
	List<FFPTxJnl> inquiryBySrcRefNm(@Param("srcRefNm") String srcRefNm);
}
