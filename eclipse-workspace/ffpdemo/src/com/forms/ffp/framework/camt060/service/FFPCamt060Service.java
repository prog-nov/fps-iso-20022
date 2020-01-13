package com.forms.ffp.framework.camt060.service;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.bussiness.iclfps.camt060.FFPJbCamt060;
import com.forms.ffp.framework.camt060.dao.FFPICamt060Dao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鏈嶅姟灞傦紝鎻愪氦鍚庡彴鏁版嵁搴撶殑鎿嶄綔<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
@Service("camt060Service")
public class FFPCamt060Service implements FFPICamt060Service {
	@Resource(name = "camt060Dao")
	private FFPICamt060Dao camt060Dao;
	@Override
	public int sInsert(FFPJbCamt060 form) throws ParseException {
		// TODO Auto-generated method stub
		return camt060Dao.dInsert(form);
	}
}
