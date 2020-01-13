package com.forms.ffp.persistents.dao.payment.directdebit;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;

@Repository("FFPIDao_P210")
public interface FFPIDao_P210 {
	@Executes({
			// 插入交易表
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_P210DAT") , param = @BatchParam(false) ) })
	public int[] dInsert(FFPJbP210 form);
	@Executes({
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ), })
	public int[] updateJnlStat(FFPJbP210 form);
	
	 /**
     * inquiry jnl_no by SrcRefNm
     */
    String inquiryJnlNoBySrcRefNm(@Param("SrcRefNm") String srcRefNm);
    /**
     * 根据交易流水号查找对应的交易
     * 
     * @param docId
     * @return
     */
    FFPJbP210 inquiryByJnlNo(@Param("jnlNo") String jnlNo);
}
