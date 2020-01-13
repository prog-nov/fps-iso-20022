package com.forms.ffp.persistents.dao.addressing;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;

@Repository("FFPIDao_A100")
public interface FFPIDao_A100{
	
	@Executes({
			// 插入交易表
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_TB_TX_A100DAT") , param = @BatchParam(false)),
	})
	public int[] dInsert(FFPJbA100 form);
	
	@Executes({ 
			@Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList")) 
	})
	public int[] updateJnlStat(FFPJbBase form);

	public String inqueryJnlNoByMsgId(String msgId);
	
	public FFPJbA100 inqueryJbA100ByJnlNo(String jnlNo);
	
	
	@Execute(sqlRef = @SqlRef("INSERT_UPDATE_TB_TX_A100DAT") , param = @BatchParam(false))
	int[] dUpdateA100(FFPJbA100 form);
	
	FFPJbA100 inqueryAdrs(String CusId,String ProxyId,String ProxyIdTp);
}
