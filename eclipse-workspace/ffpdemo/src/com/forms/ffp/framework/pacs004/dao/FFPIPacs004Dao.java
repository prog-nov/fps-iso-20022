package com.forms.ffp.framework.pacs004.dao;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.bussiness.iclfps.pacs004.FFPJbPacs004;
import com.forms.ffp.bussiness.participant.cti01.FFPJbOSend;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鐨勬暟鎹簱鎿嶄綔绫�<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-17<br>
 */
@Repository("pacs004Dao")
public interface FFPIPacs004Dao {
	@Executes({
			// 记录每一笔报文
			@Execute(sqlRef = @SqlRef("tbMsgJnlActionFromICLACK") , param = @BatchParam(item = "msgJnlAction",property = "tbMsgJnlAction") ),
			// 插入报文流水号
			@Execute(sqlRef = @SqlRef("tbMsgDataFromICLACK") , param = @BatchParam(value = false,property = "tbMsgJnl") ) })
	public int[] dInsert(FFPJbPacs004 form);

	@Executes({
			// 根据msgId更新上一笔报文
			@Execute(sqlRef = @SqlRef("UTbMsgJnlActionFromICLACK") , param = @BatchParam(false) ),
			// 记录发往Participant的报文
			@Execute(sqlRef = @SqlRef("TbMsgJnlActionToParACK") , param = @BatchParam(false) ),
			// 记录报文流水号
			@Execute(sqlRef = @SqlRef("tbMsgDataToParACK") , param = @BatchParam(false) ) })
	public int[] beSendToDb(FFPJbPacs004 form);

	@Executes({
			// 更改报文状态
			@Execute(sqlRef = @SqlRef("confToParACK") , param = @BatchParam(false) ),
			// 更新交易状态
			@Execute(sqlRef = @SqlRef("tbMsgJnlFromICLACK") , param = @BatchParam(false) ) })
	public int[] confSendToDb(FFPJbPacs004 form);

}
