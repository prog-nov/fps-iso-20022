package com.forms.beneform4j.webapp.systemmanage.holiday.service;

import java.util.List;

import com.forms.beneform4j.webapp.systemmanage.holiday.bean.HolidayBean;
import com.forms.beneform4j.webapp.systemmanage.holiday.form.HolidayForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 节假日管理服务层接口<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20<br>
 */
public interface IHolidayService {

    /**
     * 查询节假日信息
     * 
     * @param holiday
     * @return
     */
    List<HolidayBean> sList(HolidayForm holiday);

    /**
     * 更新节假日信息
     * 
     * @param date
     * @param holidayName
     * @param isHoliday
     * @return
     */
    int sUpdate(HolidayForm holiday);
}
