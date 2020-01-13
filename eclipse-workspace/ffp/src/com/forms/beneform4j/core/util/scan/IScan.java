package com.forms.beneform4j.core.util.scan;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import com.forms.beneform4j.core.util.filter.field.IFieldFilter;
import com.forms.beneform4j.core.util.filter.method.IMethodFilter;
import com.forms.beneform4j.core.util.filter.type.ITypeFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 扫描接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IScan {

    /**
     * 扫描满足条件的类
     * 
     * @param basePackage 扫描包
     * @param filter 类过滤器
     * @return 满足条件的类集合
     */
    public Set<Class<?>> scanClasses(String basePackage, ITypeFilter filter);

    /**
     * 扫描满足条件的方法
     * 
     * @param basePackage 扫描包
     * @param filter 方法过滤器
     * @return 满足条件的方法集合
     */
    public Set<Method> scanMethods(String basePackage, IMethodFilter filter);

    /**
     * 扫描满足条件的属性
     * 
     * @param basePackage 扫描包
     * @param filter 属性过滤器
     * @return 满足条件的属性集合
     */
    public Set<Field> scanFields(String basePackage, IFieldFilter filter);

    /**
     * 扫描满足条件的文件
     * 
     * @param basePackage 扫描包
     * @param filter 文件过滤器
     * @return 满足条件的文件集合
     */
    public Set<File> scanFiles(String basePackage, FileFilter filter);
}
