package com.forms.ffp.persistents.dao.payment.returnrefund;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;


@Repository("FFPIDao_P300")
public interface FFPIDao_P300 {
	
	@Executes({
		// 插入交易表
		@Execute(sqlRef = @SqlRef("INSERT_P300_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
		@Execute(sqlRef = @SqlRef("P300insertTXJNL") ,param = @BatchParam(value = false, property = "txJnl") ),
		@Execute(sqlRef = @SqlRef("INSERTLIST_TB_TX_P300DAT") , param = @BatchParam(false) )
		})
	public int[] dInsert(FFPJbP300 form);
	
	
	FFPJbP300 inquiryP300ByJnlNo(@Param("jnlNo") String jnlNo);
	
	@SqlRef("P300insertTXJNL")
	int[] insertTXJNL(FFPTxJnl txJnl);
	
	
	/**
	 * update 
	 * @param txJnl
	 * @return
	 */
	int updateTxjnl(FFPTxJnl txJnl);

}
