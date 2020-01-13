package com.forms.beneform4j.util.param.single;

import com.forms.beneform4j.util.param.IParamServiceApi;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 简单参数服务接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-6<br>
 */
public interface ISingleParamService extends IParamServiceApi<ISingleParam> {

    /**
     * 获取简单参数值
     * 
     * @param name 简单参数名称
     * @return 简单参数值
     */
    public String get(String name);

    /**
     * 根据目标类型获取简单参数值
     * 
     * @param name 简单参数名称
     * @param cls 目标类型
     * @return 转换为目标类型后的简单参数值
     */
    public <E> E get(String name, Class<E> cls);

    /**
     * 根据目标类型和默认值获取简单参数值
     * 
     * @param name 简单参数名称
     * @param defaultValue 默认值
     * @param cls 目标类型
     * @return 转换为目标类型后的简单参数值
     */
    public <E> E get(String name, E defaultValue, Class<E> cls);

    /**
     * 刷新缓存中参数
     */
    public void refresh();
}
