package com.forms.beneform4j.webapp.systemmanage.holiday.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.webapp.systemmanage.holiday.bean.HolidayBean;
import com.forms.beneform4j.webapp.systemmanage.holiday.dao.IHolidayDao;
import com.forms.beneform4j.webapp.systemmanage.holiday.form.HolidayForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 节假日管理服务层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20<br>
 */
@Service("holidayService")
@Scope("prototype")
public class HolidayService implements IHolidayService {

    @Resource(name = "holidayDao")
    private IHolidayDao dao;

    /**
     * 查询节假日列表
     * 
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<HolidayBean> sList(HolidayForm holiday) {
        return dao.dList(holiday);
    }

    /**
     * 更新节假日
     * 
     * @param userId
     * @return
     */
    @Override
    public int sUpdate(HolidayForm holiday) {
        int[] rs = dao.dUpdate(holiday);
        return rs[rs.length - 1];
    }

}
