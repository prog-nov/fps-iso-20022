package com.forms.beneform4j.webapp.systemmanage.param.define.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.param.define.bean.ParamDefineBean;
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
@Repository
public interface IParamDefineDao {

    /**
     * 获取单值参数定义列表
     * 
     * @param param
     * @return
     */
    public List<ParamDefineBean> dList(ParamDefineForm form, IPage page);

    @SqlRef("dList")
    
    /**
     * 查找单值参数
     * 
     * @param param
     * @return
     */
    public ParamDefineBean dFind(String paramCode);

    /**
     * 添加单值参数
     * 
     * @param param
     * @return
     */
    public int dInsert(ParamDefineForm form);
    
    /**
     * 修改单值参数
     * 
     * @param param
     * @return
     */
    public int dUpdate(ParamDefineForm form);
    
    /**
     * 删除单值参数
     * 
     * @param param
     * @return
     */
    public int[] dDelete(@BatchParam(item = "paramCode") String[] paramCodes);
}
