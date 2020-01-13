package com.forms.ffp.framework.pacs003.dao;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.bussiness.iclfps.pacs003.FFPJbPacs003;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鐨勬暟鎹簱鎿嶄綔绫�<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-17<br>
 */
@Repository("pacs003Dao")
public interface FFPIPacs003Dao {
	 @Executes({
         //记录每一笔交易
         @Execute(sqlRef = @SqlRef("tbMsgJnl"), param = @BatchParam(false)),
         //记录每一笔报文
         @Execute(sqlRef = @SqlRef("tbMsgJnlAction"), param = @BatchParam(false)),
         //插入报文流水号
         @Execute(sqlRef = @SqlRef("tbMsgData"), param = @BatchParam(false))})
    public int[] insertHead(FFPJbPacs003 form);

}
