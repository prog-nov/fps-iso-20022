package com.forms.ffp.framework.pacs004.service;

import com.forms.ffp.bussiness.iclfps.pacs004.FFPJbPacs004;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅缁存姢鎿嶄綔鏈嶅姟鎺ュ�?<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
public interface FFPIPacs004Service {

	int sInsert(FFPJbPacs004 form) throws Exception;
	int beSendToDb(FFPJbPacs004 form) throws Exception;
	int confSendToDb(FFPJbPacs004 form) throws Exception;
}
