package com.forms.ffp.persistents.dao.tx.inquiry.I110;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.tx.inquiry.I110.FFPJbI110;

@Repository("FFPIDao_I110")
public interface FFPIDao_I110
{
	public List<FFPJbI110> searchByJnlNo(FFPJbI110 form);

	@Executes({ @Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_e100dat") , param = @BatchParam(false) ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ) })
	public int[] insert(FFPJbI110 form);

}
