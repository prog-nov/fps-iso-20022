package com.forms.ffp.persistents.dao.addressing;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;

@Repository("FFPIDao_Addressing_Acct")
public interface FFPIDao_Addressing_Acct {
	@Executes({
		@Execute(sqlRef = @SqlRef("DETELE_TB_DT_ADDRESSING_ACCT") , param = @BatchParam(item = "jbAdrs", property = "jbAdrsList")),
		@Execute(sqlRef = @SqlRef("DETELE_TB_DT_ADDRESSING") , param = @BatchParam(false))
	})
	int[] dDeleteAdrs(FFPJbA100 form);
	
	@Executes({
		@Execute(sqlRef = @SqlRef("INSERT_UPDATE_TB_DT_ADDRESSING_ACCT") , param = @BatchParam(item = "jbAdrs", property = "jbAdrsList")),
		@Execute(sqlRef = @SqlRef("INSERT_UPDATE_TB_DT_ADDRESSING") , param = @BatchParam(false))
	})
	int[] dUpdateAdrs(FFPJbA100 form);
	
	
}
