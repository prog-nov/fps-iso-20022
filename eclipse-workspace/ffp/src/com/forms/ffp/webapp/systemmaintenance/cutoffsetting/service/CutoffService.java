package com.forms.ffp.webapp.systemmaintenance.cutoffsetting.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.ss.FFPSsCutoff;
import com.forms.ffp.persistents.dao.ss.FFPIDao_Cutoff;
import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.bean.CutOffBean;
import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.form.CutOffForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理模块服务层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Service("cutoffService")
@Scope("prototype")
public class CutoffService implements ICutoffService {

    @Resource(name = "FFPIDao_Cutoff")
    private FFPIDao_Cutoff dao;
    
    /**
     * @return
     */
    @Override
    public List<CutOffBean> inqueryAllCutoffType() {
    	List<FFPSsCutoff> resultList = dao.inqueryAllCutoffType();
    	List<CutOffBean> cutOffList = new ArrayList<CutOffBean>();
    	if(resultList != null)
    	{
    		for(FFPSsCutoff cutoff : resultList)
    		{
    			CutOffBean bean = new CutOffBean();
    			bean.setCutoffType(cutoff.getCutoffType());
    			SimpleDateFormat loc_sdf = new SimpleDateFormat("HH:mm:ss");
    			bean.setWorkdayStart(loc_sdf.format(cutoff.getWorkdayStart()));
    			bean.setWorkdayEnd(loc_sdf.format(cutoff.getWorkdayEnd()));
    			bean.setSatStart(loc_sdf.format(cutoff.getWorkdayStart()));
    			bean.setSatEnd(loc_sdf.format(cutoff.getSatEnd()));
    			bean.setHolidayStart(loc_sdf.format(cutoff.getHolidayStart()));
    			bean.setHolidayEnd(loc_sdf.format(cutoff.getHolidayEnd()));
    			cutOffList.add(bean);
    		}
    	}
    	return cutOffList;
    }

    @Override
    public int sUpdate(CutOffForm form) {
    	if(form == null)
    	{
    		return 0;
    	}
    	try
    	{
	    	FFPSsCutoff cutoff = new FFPSsCutoff();
	    	cutoff.setCutoffType(form.getCutoffType());
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	cutoff.setWorkdayStart(sdf.parse(form.getWorkdayStart()));
	    	cutoff.setWorkdayEnd((sdf.parse(form.getWorkdayEnd())));
	    	cutoff.setSatStart(sdf.parse(form.getSatStart()));
	    	cutoff.setSatEnd((sdf.parse(form.getSatEnd())));
	    	cutoff.setHolidayStart(sdf.parse(form.getHolidayStart()));
	    	cutoff.setHolidayEnd((sdf.parse(form.getHolidayEnd())));
	        int rs = dao.sUpdate(cutoff);
	        return rs;
    	}
    	catch(Exception ip_e)
    	{
    		return 0;
    	}
    }
}
