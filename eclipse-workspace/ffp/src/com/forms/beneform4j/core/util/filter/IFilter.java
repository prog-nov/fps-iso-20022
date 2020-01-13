package com.forms.beneform4j.core.util.filter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 过滤器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IFilter<E> {

    /**
     * 执行过滤
     * 
     * @param obj 对象
     * @return 满足条件，返回true，不满足返回false
     */
    public boolean accept(E obj);
}
