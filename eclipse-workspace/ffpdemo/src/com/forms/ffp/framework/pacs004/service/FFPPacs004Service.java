package com.forms.ffp.framework.pacs004.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.bussiness.iclfps.pacs004.FFPJbPacs004;
import com.forms.ffp.framework.pacs004.dao.FFPIPacs004Dao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鏈嶅姟灞傦紝鎻愪氦鍚庡彴鏁版嵁搴撶殑鎿嶄綔<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
@Service("pacs004Service")
public class FFPPacs004Service implements FFPIPacs004Service {
	@Resource(name = "pacs004Dao")
	private FFPIPacs004Dao pacs004Dao;

	@Override
	public int sInsert(FFPJbPacs004 form) throws Exception {
		int[] rs = pacs004Dao.dInsert(form);
		return rs[0];
	}
	@Override
	public int beSendToDb(FFPJbPacs004 form) throws Exception {
		// TODO Auto-generated method stub
		int[] rs =pacs004Dao.beSendToDb(form);
		return rs[0];
	}

	@Override
	public int confSendToDb(FFPJbPacs004 form) throws Exception {
		// TODO Auto-generated method stub
		int[] rs = pacs004Dao.confSendToDb(form);
		return rs[0];
	}


}
