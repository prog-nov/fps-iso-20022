package com.forms.ffp.persistents.dao.addressing;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;

@Repository("FFPIDao_A120")
public interface FFPIDao_A120
{
	

	
	@Executes({ 
			@Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList"))
	})
	public int[] updateJnlStat(FFPJbBase form);
	
	int updateA120Status(@Param("status")String status,@Param("jnlNo")String jnlNo);
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	int insertA120Msg(List<FFPJbA120> list);
	
	
	String iqueryA120MsgStat(@Param("jnlNo")String jnlNo);
	
	
	
}
