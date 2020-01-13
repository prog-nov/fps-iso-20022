package com.forms.beneform4j.webapp.common.param.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.param.IParamService;
import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.enums.IEnumParamItem;
import com.forms.beneform4j.util.param.single.ISingleParam;
import com.forms.beneform4j.util.param.tree.ITreeParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Component
@DependsOn("beneform4jConfig")
public class ParamHolder implements InitializingBean {

    /**
     * 参数服务实现类
     */
    private static IParamService service;

    /**
     * 注入参数服务实现类
     * 
     * @param service
     */
    @Autowired
    @Qualifier("paramService")
    public void setService(IParamService service) {
        ParamHolder.service = service;
    }

    /**
     * 初始化加载系统参数
     */
    public void afterPropertiesSet() {
        service.refreshParameters();
    }

    /**
     * 获取参数值
     * 
     * @param name 参数名称
     * @return 参数值
     */
    public static String getParameter(String name) {
        return service.getParameter(name);
    }

    /**
     * 根据目标类型获取参数值
     * 
     * @param name 参数名称
     * @param cls 目标类型
     * @return 转换为目标类型后的参数值
     */
    public static <E> E getParameter(String name, Class<E> cls) {
        return service.getParameter(name, cls);
    }

    /**
     * 根据目标类型和默认值获取参数值
     * 
     * @param name 参数名称
     * @param defaultValue 默认值
     * @param cls 目标类型
     * @return 转换为目标类型后的参数值
     */
    public static <E> E getParameter(String name, E defaultValue, Class<E> cls) {
        return service.getParameter(name, defaultValue, cls);
    }

    /**
     * 刷新缓存中参数
     */
    public static void refreshParameters() {
        service.refreshParameters();
    }

    /**
     * 获取单值参数
     * 
     * @param name 参数名称
     * @return 单值参数
     */
    public static ISingleParam getSingleParameter(String name) {
        return service.getSingleParameter(name);
    }

    /**
     * 获取一组单值参数
     * 
     * @param names 参数名称组
     * @return 单值参数组
     */
    public static Map<String, ISingleParam> getSingleParameters(List<String> names) {
        return service.getSingleParameters(names);
    }

    /**
     * 获取枚举参数
     * 
     * @param name 参数名称
     * @return 枚举参数
     */
    public static IEnumParam getEnumParameter(String name) {
        return service.getEnumParameter(name);
    }

    /**
     * 获取一组枚举参数
     * 
     * @param names 参数名称组
     * @return 枚举参数组
     */
    public static Map<String, IEnumParam> getEnumParameters(List<String> names) {
        return service.getEnumParameters(names);
    }

    /**
     * 获取枚举参数数据项列表
     * 
     * @param name 参数名称
     * @return 枚举参数数据项列表
     */
    public static List<IEnumParamItem> getEnumParameterItems(String name) {
        return service.getEnumParameterItems(name);
    }

    /**
     * 获取树型枚举参数
     * 
     * @param name 参数名称
     * @return 树型枚举参数
     */
    public static ITreeParam getTreeParameter(String name) {
        return service.getTreeParameter(name);
    }

    /**
     * 获取一组树型枚举参数
     * 
     * @param names 参数名称组
     * @return 树型枚举参数组
     */
    public static Map<String, ITreeParam> getTreeParameters(List<String> names) {
        return service.getTreeParameters(names);
    }

    /**
     * 移除缓存中枚举参数
     * 
     * @param names 参数名称组
     */
    public static void removeEnumParameter(List<String> names) {
        service.removeEnumParameter(names);
    }

    /**
     * 移除缓存中树型枚举参数
     * 
     * @param names 参数名称组
     */
    public static void removeTreeParameter(List<String> names) {
        service.removeTreeParameter(names);
    }

    /**
     * 获取参数对象
     * 
     * @param names 参数名称组
     * @return 参数组
     */
    public static List<IParam> getParameters(List<String> names) {
        return service.getParameters(names);
    }

    /**
     * 获取树形参数的代码集合
     * 
     * @param name 参数名称
     * @param code 参数代码
     * @return
     */
    public static Set<String> getTreeParamCodes(String name, String code) {
        return service.getTreeParamCodes(name, code);
    }

    /**
     * 获取树形参数的条件
     * 
     * @param name 参数名称
     * @param code 参数代码
     * @return
     */
    public static String getTreeParamCondition(String name, String code) {
        return service.getTreeParamCondition(name, code);
    }

    /**
     * 获取树形参数的条件
     * 
     * @param name 参数名称
     * @param code 参数代码
     * @param isNumeric 是否数字类型
     * @return
     */
    public static String getTreeParamCondition(String name, String code, boolean isNumeric) {
        return service.getTreeParamCondition(name, code, isNumeric);
    }
}
