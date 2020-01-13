package com.forms.beneform4j.util.param.common;

import com.forms.beneform4j.util.param.IParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数存储器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public interface IParamStore<P extends IParam> {

    /**
     * 获取参数
     * 
     * @param name 参数名称
     * @return 参数
     */
    public P get(String name);

    /**
     * 移除参数
     * 
     * @param name 参数名称
     */
    public void remove(String name);

    /**
     * 保存参数
     * 
     * @param name 参数名称
     * @param value 参数值
     */
    public void save(String name, P value);

    /**
     * 是否包含名称为name的参数
     * 
     * @param name 参数名称
     * @return 是否包含
     */
    public boolean contains(String name);

    /**
     * 清空
     */
    public void clear();
}
