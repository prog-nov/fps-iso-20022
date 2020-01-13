package com.forms.beneform4j.webapp.systemmanage.param.single.service;

import java.util.List;
import java.util.Map;

import com.forms.beneform4j.webapp.systemmanage.param.single.bean.SingleParamBean;
import com.forms.beneform4j.webapp.systemmanage.param.single.form.SingleParamForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : <br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public interface IParamManagerService {
    /**
     * 查询系统参数列表
     * 
     * @param param
     * @return
     */
    public Map<String, List<SingleParamBean>> queryParamList();

    /**
     * 更新系统参数值配置
     * 
     * @param param
     * @return
     */
    public int updateParamValue(String[] paramCodes, String[] paramValues);

    /**
     * 删除系统参数值配置
     * 
     * @param param
     * @return
     */
    public int deleteParamValue(SingleParamForm param);

    /**
     * 获取下拉数据
     * 
     * @return
     */
    public List<SingleParamBean> dGetSingleParamComboData(SingleParamForm form);

}
