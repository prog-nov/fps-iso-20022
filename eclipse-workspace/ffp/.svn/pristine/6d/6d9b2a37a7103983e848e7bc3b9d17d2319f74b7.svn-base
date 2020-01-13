package com.forms.ffp.persistents.dao.tx.m100;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.tx.m100.FFPJbM100;

@Repository("FFPIDao_M100")
public interface FFPIDao_M100
{

	@Executes({
		@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
		@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
		@Execute(sqlRef = @SqlRef("INSERTLIST_TB_TX_M100") , param = @BatchParam(false) ) })
	public int[] dInsert(FFPJbM100 m100);

	public FFPJbM100 inquiryM100ByJnlNoOrMsgId(@Param("jnlNo")String jnlNo, @Param("msgId")String msgId);
	
	@Executes({
		@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
		@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
		@Execute(sqlRef = @SqlRef("UPDATE_TB_TX_M100") , param = @BatchParam(false) ) })
	public int[] update(FFPJbM100 m100);


}
