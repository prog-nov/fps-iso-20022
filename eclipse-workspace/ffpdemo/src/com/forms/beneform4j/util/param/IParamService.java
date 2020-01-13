package com.forms.beneform4j.util.param;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.enums.IEnumParamItem;
import com.forms.beneform4j.util.param.single.ISingleParam;
import com.forms.beneform4j.util.param.tree.ITreeParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数服务接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-6<br>
 */
public interface IParamService {

    /**
     * 获取参数值
     * 
     * @param name 参数名称
     * @return 参数值
     */
    public String getParameter(String name);

    /**
     * 根据目标类型获取参数值
     * 
     * @param name 参数名称
     * @param cls 目标类型
     * @return 转换为目标类型后的参数值
     */
    public <E> E getParameter(String name, Class<E> cls);

    /**
     * 根据目标类型和默认值获取参数值
     * 
     * @param name 参数名称
     * @param defaultValue 默认值
     * @param cls 目标类型
     * @return 转换为目标类型后的参数值
     */
    public <E> E getParameter(String name, E defaultValue, Class<E> cls);

    /**
     * 刷新缓存中参数
     */
    public void refreshParameters();

    /**
     * 获取单值参数
     * 
     * @param name 参数名称
     * @return 单值参数
     */
    public ISingleParam getSingleParameter(String name);

    /**
     * 获取一组单值参数
     * 
     * @param names 参数名称组
     * @return 单值参数组
     */
    public Map<String, ISingleParam> getSingleParameters(List<String> names);

    /**
     * 清空单值参数
     */
    public void clearSingleParameters();

    /**
     * 获取枚举参数
     * 
     * @param name 参数名称
     * @return 枚举参数
     */
    public IEnumParam getEnumParameter(String name);

    /**
     * 获取一组枚举参数
     * 
     * @param names 参数名称组
     * @return 枚举参数组
     */
    public Map<String, IEnumParam> getEnumParameters(List<String> names);

    /**
     * 获取枚举参数数据项列表
     * 
     * @param name 参数名称
     * @return 枚举参数数据项列表
     */
    public List<IEnumParamItem> getEnumParameterItems(String name);

    /**
     * 从缓存中移除枚举参数
     * 
     * @param names 参数名称组
     */
    public void removeEnumParameter(List<String> names);

    /**
     * 清空列表枚举参数
     */
    public void clearEnumParameters();

    /**
     * 获取树型枚举参数
     * 
     * @param name 参数名称
     * @return 树型枚举参数
     */
    public ITreeParam getTreeParameter(String name);

    /**
     * 获取一组树型枚举参数
     * 
     * @param names 参数名称组
     * @return 树型枚举参数组
     */
    public Map<String, ITreeParam> getTreeParameters(List<String> names);

    /**
     * 从缓存中移除树型枚举参数
     * 
     * @param names 参数名称组
     */
    public void removeTreeParameter(List<String> names);

    /**
     * 清空树型参数
     */
    public void clearTreeParameters();

    /**
     * 获取参数对象
     * 
     * @param names
     * @return
     */
    public List<IParam> getParameters(List<String> names);

    /**
     * 获取树形参数的代码集合
     * 
     * @param name 参数名称
     * @param code 参数代码
     * @return
     * @since 1.1.0
     */
    public Set<String> getTreeParamCodes(String name, String code);

    /**
     * 获取树形参数的条件
     * 
     * @param name 参数名称
     * @param code 参数代码
     * @return
     * @since 1.1.0
     */
    public String getTreeParamCondition(String name, String code);

    /**
     * 获取树形参数的条件
     * 
     * @param name 参数名称
     * @param code 参数代码
     * @param isNumeric 是否数字类型
     * @return
     * @since 1.1.0
     */
    public String getTreeParamCondition(String name, String code, boolean isNumeric);
}
