package com.forms.beneform4j.webapp.systemmanage.holiday.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.webapp.systemmanage.holiday.bean.HolidayBean;
import com.forms.beneform4j.webapp.systemmanage.holiday.form.HolidayForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台<br>
 * Description : 节假日DAO <br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20 <br>
 */
@Repository("holidayDao")
public interface IHolidayDao {

    /**
     * 查询节假日列表
     * 
     * @param sessionId
     * @return
     */
    public List<HolidayBean> dList(HolidayForm holiday);

    /**
     * 更新节假日信息
     * 
     * @param userId
     * @return
     */
    public int[] dUpdate(HolidayForm holiday);
}
