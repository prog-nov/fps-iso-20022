package com.forms.ffp.persistents.dao.addressing;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.addressing.FFPJbA200;

@Repository("FFPIDao_A200")
public interface FFPIDao_A200{
	
	@Executes({
			// 插入交易表
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_A200DAT") , param = @BatchParam(false)),
	})
	public int[] dInsert(FFPJbA200 form);
	
	@Executes({
		@Execute(sqlRef = @SqlRef("INSERT_UPDATE_TB_DT_ADDRESSING") , param = @BatchParam(false))
	})
	int[] dUpdateAdrsOnly(FFPJbA200 form);
	
	@Executes({
		@Execute(sqlRef = @SqlRef("DELETE_TB_DT_ADDRESSING") , param = @BatchParam(false))
	})
	int[] dDelete(FFPJbA200 form);
	
}
