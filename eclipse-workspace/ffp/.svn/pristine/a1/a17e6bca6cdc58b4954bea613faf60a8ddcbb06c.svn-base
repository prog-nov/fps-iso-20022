package com.forms.ffp.persistents.dao.payment.credittransfer;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;

@Repository("FFPIDao_P100")
public interface FFPIDao_P100
{
	@Executes({
			// 插入交易表
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_JNL") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList") ),
			@Execute(sqlRef = @SqlRef("INSERT_TB_TX_P100DAT") , param = @BatchParam(false) ) })
	public int[] dInsert(FFPJbP100 form);

	@Executes({ @Execute(sqlRef = @SqlRef("UPDATE_TXJNL_STAT") , param = @BatchParam(value = false, property = "txJnl") ),
			@Execute(sqlRef = @SqlRef("INSERT_UPDATE_LIST_TXJNLACTION") , param = @BatchParam(item = "jnlAction", property = "jnlActionList")), })
	public int[] updateJnlStat(FFPJbBase form);
	
	/**
     * 根据交易流水号查找对应的交易
     * 
     * @param docId
     * @return
     */
    FFPJbP100 inquiryByJnlNo(FFPTxJnl txJnl);
    
    
    /**
     * 更新 p100 
     */
//    int[] updateJbP100(FFPJbP100 form);
    
    /**
     * inquiry jnl_no by SrcRefNm
     * credit transfer in only
     */
    String inquiryJnlNoBySrcRefNm(@Param("SrcRefNm") String srcRefNm);
    
    String inquiryJnlNoByMsgId(@Param("MsgId") String msgId);
}
