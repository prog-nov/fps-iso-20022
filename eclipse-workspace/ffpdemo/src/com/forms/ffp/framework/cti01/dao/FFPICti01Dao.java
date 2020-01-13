package com.forms.ffp.framework.cti01.dao;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.bussiness.participant.cti01.FFPJbCti01;
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
@Repository("cti01Dao")
public interface FFPICti01Dao {
	@Executes({
			// 生产transId并记录每一笔交易
			@Execute(sqlRef = @SqlRef("tbMsgJnl") , param = @BatchParam(false) ),
			// 记录收到Participant发过来的报文
			@Execute(sqlRef = @SqlRef("tbMsgJnlAction") , param = @BatchParam(false) ),
			// 记录报文流水号
			@Execute(sqlRef = @SqlRef("tbMsgData") , param = @BatchParam(false) ) })
	public int[] dInsert(FFPJbCti01 form);

	@Executes({
			// 根据msgId更新上一笔报文
			@Execute(sqlRef = @SqlRef("updateTbMsgJnlAction") , param = @BatchParam(false) ),
			// 记录发往ICl的报文
			@Execute(sqlRef = @SqlRef("insertTbMsgJnlAction") , param = @BatchParam(false) ),
			// 记录报文流水号
			@Execute(sqlRef = @SqlRef("tbMsgDataToICL") , param = @BatchParam(false) ) })
	public int[] beSendToDb(FFPJbOSend form);

	@SqlRef("confSendToDb")
	public int confSendToDb(FFPJbOSend form);

}
