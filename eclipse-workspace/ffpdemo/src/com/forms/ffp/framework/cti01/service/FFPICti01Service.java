package com.forms.ffp.framework.cti01.service;

import com.forms.ffp.bussiness.participant.cti01.FFPJbCti01;
import com.forms.ffp.bussiness.participant.cti01.FFPJbOSend;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅缁存姢鎿嶄綔鏈嶅姟鎺ュ�?<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
public interface FFPICti01Service {

	int sInsert(FFPJbCti01 form) throws Exception;

	int beSendToDb(FFPJbOSend form) throws Exception;
	int confSendToDb(FFPJbOSend form) throws Exception;
}
