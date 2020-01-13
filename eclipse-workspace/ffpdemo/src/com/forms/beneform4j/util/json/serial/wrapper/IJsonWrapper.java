package com.forms.beneform4j.util.json.serial.wrapper;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : JSON序列化前的包装器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
public interface IJsonWrapper {

    /**
     * 包装对象
     * 
     * @param original 原始对象
     * @return 包装后的对象
     */
    public Object wrap(Object original);
}
