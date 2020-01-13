package com.forms.ffp.persistents.dao.addressing;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;

@Repository("FFPIDao_A120dtl")
public interface FFPIDao_A120dtl
{
	
	public int INSERTLIST_TB_TX_A120DAT_DTL(List<FFPJbA120dtl> list);

	
	
	@Executes({ 
			@Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList")) 
	})
	public int[] updateJnlStat(FFPJbBase form);
	
	
	 
	 List<FFPJbA120dtl> inqueryAddress_A120Dtl(@Param("jnlNo")String jnlNo);
	
	
	
}
