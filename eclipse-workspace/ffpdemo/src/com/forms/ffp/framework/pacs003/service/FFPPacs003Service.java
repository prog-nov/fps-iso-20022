package com.forms.ffp.framework.pacs003.service;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.bussiness.iclfps.pacs003.FFPJbPacs003;
import com.forms.ffp.framework.pacs003.dao.FFPIPacs003Dao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鏈嶅姟灞傦紝鎻愪氦鍚庡彴鏁版嵁搴撶殑鎿嶄綔<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
@Service("pacs003Service")
public class FFPPacs003Service implements FFPIPacs003Service {
	@Resource(name = "pacs003Dao")
	private FFPIPacs003Dao pacs003Dao;

	@Override
	public int sInsert(FFPJbPacs003 form) throws Exception {
		int[] rs = pacs003Dao.insertHead(form);
		return rs[0];
	}

}
