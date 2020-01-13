package com.forms.ffp.persistents.dao.tx.inquiry.I100;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.tx.inquiry.I100.FFPJbI100;

@Repository("FFPIDao_I100")
public interface FFPIDao_I100
{
	@Executes({
			// 插入交易表
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_I100DAT") , param = @BatchParam(false) ) })
	public int[] dInsert(FFPJbI100 ip_jb);

	@Executes({ @Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList")), })
	public int[] updateJnlStat(FFPJbBase ip_jb);
	
	/**
     * 根据交易流水号查找对应的交易
     * 
     * @param docId
     * @return
     */
    FFPJbI100 inquiryByJnlNo(@Param("jnlNo") String jnlNo);
    
	@Executes({ 
		@Execute(sqlRef = @SqlRef("updateI100"), param = @BatchParam(value = false)),
		@Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT") , param = @BatchParam(value = false, property = "txJnl") ),
		@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList")), })
    int[] updateJbI100(FFPJbI100 ip_jb);
    
    String inquiryJnlNoByMsgId(@Param("msgId") String msgId);
    
    FFPJbI100 inquiryLastI100DATByCcy(@Param("ccy") String ccy);
}
