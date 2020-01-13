package com.forms.beneform4j.webapp.systemmanage.holiday.service;

import java.util.ArrayList;
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
    public List<HolidayBean> sList() {
    	List<HolidayBean> holidayList = dao.dList();
    	if(holidayList != null)
    	{
    		for(HolidayBean bean : holidayList)
    		{
    			bean.setYear(Integer.valueOf(bean.getSolarDate().substring(0, 4)));
    			bean.setMonth(Integer.valueOf(bean.getSolarDate().substring(4, 6)));
    			bean.setDay(Integer.valueOf(bean.getSolarDate().substring(6, 8)));
    		}
    	}
    	else
    	{
    		holidayList = new ArrayList<HolidayBean>();
    	}
    	return holidayList;
    }

    @Override
    public boolean isHolidayDay(String ip_date) {
    	HolidayBean bean = dao.inquiry(ip_date);
    	if(bean != null)
    	{
    		return true;
    	}
    	return false;
    }
    
    /**
     * 更新节假日
     * 
     * @param userId
     * @return
     */
    @Override
    public int sUpdate(HolidayForm holiday) {
    	if(holiday != null)
    	{
    		List<HolidayBean> holidayList = new ArrayList<HolidayBean>();
    		if(holiday.getDates() != null)
    		{
    			for(String date : holiday.getDates())
    			{
    				HolidayBean bean = new HolidayBean();
    				bean.setSolarDate(date);
    				bean.setIsHolidayDay(holiday.getIsHoliday());
    				bean.setHolidayDesc(holiday.getHolidayName());
    				holidayList.add(bean);
    			}
    		}
    		int[] rs = dao.dUpdate(holidayList);
            return rs[rs.length - 1];
    	}
    	return 0;
    }
}
