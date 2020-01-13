package com.forms.ffp.webapp.systemmaintenance.cutoffsetting.service;

import java.util.List;

import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.bean.CutOffBean;
import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.form.CutOffForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理服务层接口<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public interface ICutoffService {

    /**
     * 查询用户列表
     * 
     * @param user
     * @param page
     * @return
     */
    List<CutOffBean> inqueryAllCutoffType();

    /**
     * 更新单个用户、用户角色关系
     * 
     * @param user
     * @return
     */
    int sUpdate(CutOffForm form);
}
