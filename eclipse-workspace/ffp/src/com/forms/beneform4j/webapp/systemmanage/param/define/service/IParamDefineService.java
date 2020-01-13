package com.forms.beneform4j.webapp.systemmanage.param.define.service;

import java.util.List;

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
public interface IParamDefineService {
	
    /**
     * 列举单值参数列表
     * 
     * @param param
     * @return
     */
    public List<ParamDefineBean> sList(ParamDefineForm form, IPage page);

    /**
     * 查找单值参数
     * 
     * @param param
     * @return
     */
    public ParamDefineBean sFind(String paramCode);

    /**
     * 插入单值参数
     * 
     * @param param
     * @return
     */
    public int sInsert(ParamDefineForm form);
    
    /**
     * 修改单值参数
     * 
     * @param param
     * @return
     */
    public int sUpdate(ParamDefineForm form);

    /**
     * 删除单值参数
     * 
     * @param param
     * @return
     */
    public int sDelete(String[] paramCodes);

	
}
