package com.forms.ffp.framework.camt060.dao;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.ffp.bussiness.iclfps.camt060.FFPJbCamt060;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 鍥涙柟绮惧垱 Java EE 寮�鍙戝钩鍙�? <br>
 * Description : 娑堟伅鐨勬暟鎹簱鎿嶄綔绫�<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-17<br>
 */
@Repository("camt060Dao")
public interface FFPICamt060Dao {
	
    public int dInsert(FFPJbCamt060 form);

}
