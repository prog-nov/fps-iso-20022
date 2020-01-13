package com.forms.beneform4j.util.param;

import java.util.List;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数服务API接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-10<br>
 */
public interface IParamServiceApi<P extends IParam> {

    /**
     * 获取参数
     * 
     * @param name 参数名称
     * @return 参数
     */
    public P getParam(String name);

    /**
     * 获取一组参数
     * 
     * @param names 参数名称组
     * @return 参数组
     */
    public Map<String, P> getParams(List<String> names);

    /**
     * 从缓存中移除参数
     * 
     * @param names
     */
    public void removeParams(List<String> names);

    /**
     * 是否已经加载
     * 
     * @param name 参数名称
     * @return 是否已经加载
     */
    public boolean isLoaded(String name);

    /**
     * 清空
     */
    public void clear();
}
