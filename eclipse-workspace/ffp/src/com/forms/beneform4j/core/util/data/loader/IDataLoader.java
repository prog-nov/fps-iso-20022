package com.forms.beneform4j.core.util.data.loader;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据加载器<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public interface IDataLoader {

    /**
     * 根据参数加载为数据
     * 
     * @param param 参数
     * @return 数据
     */
    public Object load(Object param);
}
