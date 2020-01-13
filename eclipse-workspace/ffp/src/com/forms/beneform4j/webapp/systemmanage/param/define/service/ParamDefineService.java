package com.forms.beneform4j.webapp.systemmanage.param.define.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.param.define.bean.ParamDefineBean;
import com.forms.beneform4j.webapp.systemmanage.param.define.dao.IParamDefineDao;
import com.forms.beneform4j.webapp.systemmanage.param.define.form.ParamDefineForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数定义<br>
 * Author : LiYun <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-23<br>
 */
@Service
public class ParamDefineService implements IParamDefineService {

    @Autowired
    private IParamDefineDao dao;

    @Override
    public List<ParamDefineBean> sList(ParamDefineForm form, IPage page) {
        return dao.dList(form, page);
    }

    @Override
    public ParamDefineBean sFind(String paramCode) {
        return dao.dFind(paramCode);
    }

    @Override
    public int sInsert(ParamDefineForm form) {
        return dao.dInsert(form);
    }
    

    @Override
    public int sUpdate(ParamDefineForm form) {
        return dao.dUpdate(form);
    }

    @Override
    public int sDelete(String[] paramCodes) {
        int[] rs = dao.dDelete(paramCodes);
        return rs[0];
    }

}
