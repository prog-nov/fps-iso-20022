package com.forms.ffp.framework.cti01.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.bussiness.participant.cti01.FFPJbCti01;
import com.forms.ffp.bussiness.participant.cti01.FFPJbOSend;
import com.forms.ffp.framework.cti01.dao.FFPICti01Dao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鏈嶅姟灞傦紝鎻愪氦鍚庡彴鏁版嵁搴撶殑鎿嶄綔<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
@Service("cti01Service")
public class FFPCti01Service implements FFPICti01Service {
	@Resource(name = "cti01Dao")
	private FFPICti01Dao cti01Dao;

	@Override
	public int sInsert(FFPJbCti01 form) throws Exception {
		int[] rs = cti01Dao.dInsert(form);
		return rs[0];
	}

	@Override
	public int beSendToDb(FFPJbOSend form) throws Exception {
		// TODO Auto-generated method stub
		int[] rs =cti01Dao.beSendToDb(form);
		return rs[0];
	}

	@Override
	public int confSendToDb(FFPJbOSend form) throws Exception {
		// TODO Auto-generated method stub
		cti01Dao.confSendToDb(form);
		return 0;
	}

}
