package com.forms.beneform4j.webapp.systemmanage.holiday.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.webapp.systemmanage.holiday.bean.HolidayBean;
import com.forms.beneform4j.webapp.systemmanage.holiday.form.HolidayForm;
import com.forms.beneform4j.webapp.systemmanage.holiday.service.IHolidayService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 节假日管理控制层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/holiday")
public class HolidayController {

    @Resource(name = "holidayService")
    private IHolidayService service;

    /**
     * 列表
     * 
     * @param holiday
     * @return
     */
    @RequestMapping("list")
    @ListJsonBody
    public List<HolidayBean> list() {
        return service.sList();
    }

    /**
     * 更新
     * 
     * @param holiday
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int offline(HolidayForm holiday) {
        holiday.setDates(holiday.getDateStr().split(","));
        return service.sUpdate(holiday);
    }
}
