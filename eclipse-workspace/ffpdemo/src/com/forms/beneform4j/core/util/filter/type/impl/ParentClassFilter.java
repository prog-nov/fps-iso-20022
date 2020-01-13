package com.forms.beneform4j.core.util.filter.type.impl;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 实现了指定接口或继承了指定父类的可实例化类过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class ParentClassFilter extends InstanceableClassFilter {

    /**
     * 接口或父类类型
     */
    private Class<?> parent;

    /**
     * 默认构造函数
     */
    public ParentClassFilter() {}

    /**
     * 使用接口或父类类型的构造函数
     * 
     * @param parent
     */
    public ParentClassFilter(Class<?> parent) {
        this.parent = parent;
    }

    /**
     * 执行过滤
     */
    public boolean accept(Class<?> cls) {
        if (null != parent && parent.isAssignableFrom(cls) && super.accept(cls)) {
            return true;
        }
        return false;
    }

    /**
     * 获取父类类型
     * 
     * @return
     */
    public Class<?> getParent() {
        return parent;
    }

    /**
     * 设置父类类型
     * 
     * @param parent
     */
    public void setParent(Class<?> parent) {
        this.parent = parent;
    }
}
