package com.forms.beneform4j.core.dao.sql.interceptor.impl;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.interceptor.ISqlInterceptor;
import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用前后缀识别的SQL语句拦截器接口抽象实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public abstract class AbstractSqlInterceptor implements ISqlInterceptor {

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 执行SQL拦截
     */
    @Override
    public String intercept(IJndi jndi, String src, Object root) {
        String prefix = getPrefix(), suffix = getSuffix();
        if (null == src || CoreUtils.isBlank(prefix) || CoreUtils.isBlank(suffix)) {
            return src;
        }
        if (prefix.equals(suffix)) {
            return resolverSome(jndi, src, root);
        }
        int prefixLength = prefix.length();
        int lastPrefixIndex = src.lastIndexOf(prefix);// 前缀出现的位置
        String tail = "";
        while (lastPrefixIndex != -1) {
            int suffixIndex = src.indexOf(suffix, lastPrefixIndex + prefixLength);// 与前缀对应的后缀出现的位置
            if (-1 != suffixIndex) {// 当前的前缀和后缀没有成对出现
                try {
                    String expression = src.substring(lastPrefixIndex + prefixLength, suffixIndex);
                    String replace = doIntercept(jndi, expression, root);
                    src = src.replaceAll("\\Q" + prefix + expression + suffix + "\\E", replace);
                } catch (Exception e) {// 为了防止出错导致死循环，这里重置src，并将已经处理的部分保存至tail中
                    tail = src.substring(lastPrefixIndex) + tail;
                    src = src.substring(0, lastPrefixIndex);
                }
            } else {
                tail = src.substring(lastPrefixIndex) + tail;
                src = src.substring(0, lastPrefixIndex);
            }
            if (lastPrefixIndex == 0) {
                break;
            }
            lastPrefixIndex = src.lastIndexOf(prefix);
        }
        return src + tail;
    }

    /**
     * 处理前后缀相同情况下的替换
     * 
     * @param jndi 数据源
     * @param src 原SQL
     * @param context 上下文环境
     * @param root 根对象
     * @return 处理后的SQL语句
     */
    private String resolverSome(IJndi jndi, String src, Object root) {
        int length = prefix.length();
        int index = src.indexOf(prefix);// 第一次出现的位置
        String tail = "";
        while (index != -1) {
            int suffixIndex = src.indexOf(prefix, index + length);// 与前缀对应的后缀出现的位置
            if (-1 != suffixIndex) {// 当前的前缀和后缀没有成对出现
                try {
                    String expression = src.substring(index + length, suffixIndex);
                    String replace = doIntercept(jndi, expression, root);
                    src = src.replaceAll("\\Q" + prefix + expression + prefix + "\\E", replace);
                } catch (Exception e) {// 为了防止出错导致死循环，这里重置src，并将已经处理的部分保存至tail中
                    tail = src.substring(index) + tail;
                    src = src.substring(0, index);
                }
            } else {
                tail = src.substring(index) + tail;
                src = src.substring(0, index);
            }
            if (index == 0) {
                break;
            }
            index = src.indexOf(prefix);
        }
        return src + tail;
    }

    /**
     * 执行拦截部分的表达式解析
     * 
     * @param jndi 数据源
     * @param expression 被拦截设别的表达式
     * @param root 根对象
     * @return 解析后的字符串
     */
    protected abstract String doIntercept(IJndi jndi, String expression, Object root);

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
