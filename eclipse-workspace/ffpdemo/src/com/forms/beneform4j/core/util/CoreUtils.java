package com.forms.beneform4j.core.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import com.forms.beneform4j.core.util.bean.IContextBeanOperateWrapper;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.env.EnvConsts;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;
import com.forms.beneform4j.core.util.filter.method.IMethodFilter;
import com.forms.beneform4j.core.util.filter.method.impl.AnnoMethodFilter;
import com.forms.beneform4j.core.util.filter.type.ITypeFilter;
import com.forms.beneform4j.core.util.filter.type.impl.AnnoClassFilter;
import com.forms.beneform4j.core.util.filter.type.impl.ParentClassFilter;
import com.forms.beneform4j.core.util.locale.ILocaleMessageResolver;
import com.forms.beneform4j.core.util.locale.ILocaleResolver;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台核心使用的工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
final public class CoreUtils {

    /**
     * Spring的类创建对象
     */
    private static final SpringObjenesis objenesis = new SpringObjenesis();
    /**
     * 国际化消息的替换正则表达式
     */
    private static final Pattern localeReplacePattern = Pattern.compile("\\s*\\{\\s*\\d+\\s*}\\s*");

    /**
     * 每个月的最大日期
     */
    private static final int[] MAX_DAY_OF_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 线程本地缓存
     */
    private static final ThreadLocal<Map<String, Object>> cache = new InheritableThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Map<String, Object> childValue(Map<String, Object> parentValue) {
            if (parentValue != null) {
                return (Map<String, Object>) ((HashMap<String, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    };

    /**
     * 字符串是否为空
     * 
     * @param cs 字符串
     * @return 是否为空白字符串
     */
    public static boolean isBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    /**
     * 对象是否为空
     * 
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    /**
     * 格式化字符串，去掉首尾空白字符，压缩中间连续的空白字符为一个
     * 
     * @param src 原始字符串
     * @return 压缩后的字符串
     */
    public static String formatWhitespace(String src) {
        if (null == src) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(src);
        StringBuilder builder = new StringBuilder();
        while (st.hasMoreTokens()) {
            builder.append(st.nextToken()).append(" ");
        }
        return builder.toString();
    }

    /**
     * 将下划线格式的字段转为驼峰式，多个连续下划线作为一个处理
     * 
     * @param str 原始字符串
     * @return 驼峰式字符串
     */
    public static String convertToCamel(String str) {
        if (null != str) {
            StringBuilder rs = new StringBuilder();
            boolean upper = false, first = true;
            for (char ch : str.trim().toCharArray()) {
                if (ch == '-' || ch == '_') {
                    upper = !first;
                } else {
                    rs.append(upper ? Character.toUpperCase(ch) : Character.toLowerCase(ch));
                    upper = false;
                    first = false;
                }
            }
            return rs.toString();
        }
        return null;
    }

    /**
     * 字符串分割
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @return 分割后的字符串列表
     */
    public static List<String> splitToList(String src, String separator) {
        List<String> l = new ArrayList<String>();
        if (null == src) {
            return null;
        } else if (null == separator) {
            l.add(src);
        } else {
            for (int i = src.indexOf(separator), o = separator.length(); i != -1; i = src.indexOf(separator)) {
                l.add(src.substring(0, i));
                src = src.substring(i + o);
            }
            if (!isBlank(src)) {
                l.add(src);
            }
        }
        return l;
    }

    /**
     * 字符串分割成固定长度的List，如果不够长度，使用默认字符串填充，如果长度超过指定长度，保持不变
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @param minSize 返回集合的最小长度
     * @param defaultString 填充的默认字符串
     * @return 分割后的字符串列表
     */
    public static List<String> splitToList(String src, String separator, int minSize, String defaultString) {
        List<String> l = splitToList(src, separator);
        if (null == l) {
            l = new ArrayList<String>();
        }
        for (int i = minSize - l.size() - 1; i >= 0; i--) {
            l.add(defaultString);
        }
        return l;
    }

    /**
     * 字符串分割
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @return 分割后的字符串数组
     */
    public static String[] split(String src, String separator) {
        List<String> l = splitToList(src, separator);
        return null == l ? null : l.toArray(new String[l.size()]);
    }

    /**
     * 字符串分割分割成固定数目的数组，如果不够，使用默认字符串填充，如果长度超过指定长度，保持不变
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @param minLength 分割后数组的最小长度
     * @param defaultString 默认字符串
     * @return 分割后的字符串数组
     */
    public static String[] split(String src, String separator, int minLength, String defaultString) {
        List<String> l = splitToList(src, separator, minLength, defaultString);
        return null == l ? null : l.toArray(new String[l.size()]);
    }

    /**
     * 用连接符连接列表中的项
     * 
     * @param list 对象列表
     * @param separator 连接符
     * @return 连接后的字符串
     */
    public static String join(List<?> list, String separator) {
        if (null == list || list.isEmpty()) {
            return null;
        }
        if (null == separator) {
            separator = ",";
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : list) {
            if (null != obj) {
                sb.append(separator).append(obj.toString());
            }
        }
        return sb.substring(separator.length()).toString();
    }

    /**
     * 用连接符连接数组中的项
     * 
     * @param arr 对象数组
     * @param separator 连接符
     * @return 连接后的字符串
     */
    public static String join(Object[] arr, String separator) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : arr) {
            if (null != obj) {
                sb.append(separator).append(obj.toString());
            }
        }
        return sb.substring(separator.length()).toString();
    }

    /**
     * 右填充
     * 
     * @param str 原始字符串
     * @param size 填充后长度
     * @param padStr 填充字符串
     * 
     *        <pre>
     * rightPad(null, *, *)      = null
     * rightPad("", 3, "z")      = "zzz"
     * rightPad("bat", 3, "yz")  = "bat"
     * rightPad("bat", 5, "yz")  = "batyz"
     * rightPad("bat", 8, "yz")  = "batyzyzy"
     * rightPad("bat", 1, "yz")  = "bat"
     * rightPad("bat", -1, "yz") = "bat"
     * rightPad("bat", 5, null)  = "bat  "
     * rightPad("bat", 5, "")    = "bat  "
     *        </pre>
     * 
     * @return 填充后字符串
     */
    public static String rightPad(final String str, final int size, final String padStr) {
        return StringUtils.rightPad(str, size, padStr);
    }

    /**
     * 安全比较两个字符串是否相等
     * 
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相等
     */
    public static boolean safeEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 安全比较两个字符串忽略大小写是否相等
     * 
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 忽略大小写是否相等
     */
    public static boolean safeEqualsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    /**
     * 字符串转换为布尔类型
     * <p>
     * 转换为true的字符串有（不区分大小写）
     * <ul>
     * <li>"1"
     * <li>"Y"
     * <li>"TRUE"
     * <li>"ON"
     * <ul>
     * <p>
     * 
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean string2Boolean(String str) {
        if (null == str) {
            return false;
        }
        str = str.trim();
        return "1".equals(str) || "Y".equalsIgnoreCase(str) || "TRUE".equalsIgnoreCase(str) || "ON".equalsIgnoreCase(str);
    }

    /**
     * 强制类型转换
     * 
     * @param obj 原始对象
     * @return 转换后的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        if (obj != null) {
            try {
                return (T) obj;
            } catch (Exception e) {
                throw new ClassCastException("Cannot cast " + obj.getClass().getName() + " to <T>...");
            }
        }
        return null;
    }

    /**
     * 转换为List类型，如果不为空，只校验第一个元素的类型是否匹配
     * 
     * @param source 原始对象
     * @param elementType 元素类型
     * @return 转换后的集合
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> convertToList(Object source, Class<E> elementType) {
        if (null == source) {
            return null;
        } else if (source.getClass().isArray()) {
            List<E> list = new ArrayList<E>();
            Class<?> c = source.getClass();
            if (c.equals(int[].class)) {
                int[] arr = (int[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Integer.class)) {
                    for (Integer a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is int, but the target type is " + elementType);
                }
            } else if (c.equals(short[].class)) {
                short[] arr = (short[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Short.class)) {
                    for (Short a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is short, but the target type is " + elementType);
                }
            } else if (c.equals(long[].class)) {
                long[] arr = (long[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Long.class)) {
                    for (Long a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is long, but the target type is " + elementType);
                }
            } else if (c.equals(byte[].class)) {
                byte[] arr = (byte[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Byte.class)) {
                    for (Byte a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is byte, but the target type is " + elementType);
                }
            } else if (c.equals(boolean[].class)) {
                boolean[] arr = (boolean[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Boolean.class)) {
                    for (Boolean a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is boolean, but the target type is " + elementType);
                }
            } else if (c.equals(char[].class)) {
                char[] arr = (char[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Character.class)) {
                    for (Character a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is char, but the target type is " + elementType);
                }
            } else if (c.equals(float[].class)) {
                float[] arr = (float[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Float.class)) {
                    for (Float a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is float, but the target type is " + elementType);
                }
            } else if (c.equals(double[].class)) {
                double[] arr = (double[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else if (elementType.isAssignableFrom(Double.class)) {
                    for (Double a : arr) {
                        list.add((E) a);
                    }
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is double, but the target type is " + elementType);
                }
            } else {
                Object[] arr = (Object[]) source;
                if (arr.length == 0) {
                    return Collections.<E>emptyList();
                } else {
                    Object first = arr[0];
                    if (elementType.isAssignableFrom(first.getClass())) {
                        for (Object a : arr) {
                            list.add((E) a);
                        }
                        return list;
                    } else {
                        throw new ClassCastException("can not convert the list, the element type is " + first.getClass() + ", but the target type is " + elementType);
                    }
                }
            }
        } else if (source instanceof List) {
            List<E> list = (List<E>) source;
            if (list.isEmpty()) {
                return Collections.<E>emptyList();
            } else {
                Object first = list.get(0);
                if (elementType.isAssignableFrom(first.getClass())) {
                    return list;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is " + first.getClass() + ", but the target type is " + elementType);
                }
            }
        } else if (source instanceof Enumeration) {
            Enumeration<E> i = (Enumeration<E>) source;
            if (!i.hasMoreElements()) {
                return Collections.<E>emptyList();
            } else {
                Object first = i.nextElement();
                if (elementType.isAssignableFrom(first.getClass())) {
                    List<E> rs = new ArrayList<E>();
                    rs.add((E) first);
                    while (i.hasMoreElements()) {
                        rs.add(i.nextElement());
                    }
                    return rs;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is " + first.getClass() + ", but the target type is " + elementType);
                }
            }
        } else if (source instanceof Iterator) {
            Iterator<E> i = (Iterator<E>) source;
            if (!i.hasNext()) {
                return Collections.<E>emptyList();
            } else {
                Object first = i.next();
                if (elementType.isAssignableFrom(first.getClass())) {
                    List<E> rs = new ArrayList<E>();
                    rs.add((E) first);
                    while (i.hasNext()) {
                        rs.add(i.next());
                    }
                    return rs;
                } else {
                    throw new ClassCastException("can not convert the list, the element type is " + first.getClass() + ", but the target type is " + elementType);
                }
            }
        } else if (source instanceof Iterable) {
            return convertToList(((Iterable<?>) source).iterator(), elementType);
        } else if (source instanceof Map) {
            return convertToList(((Map<?, ?>) source).values(), elementType);
        } else {
            return convertToList(new Object[] {source}, elementType);
        }
    }

    /**
     * Number类型转换为子类型
     * 
     * @param number number值
     * @param targetClass 目标类型
     * @return 转换后的值
     */
    public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass) {
        return NumberUtils.convertNumberToTargetClass(number, targetClass);
    }

    /**
     * 字符串解析为Number类型
     * 
     * @param text
     * @param targetClass
     * @return
     */
    public static <T extends Number> T convertStringToTargetClass(String text, Class<T> targetClass) {
        return NumberUtils.parseNumber(text, targetClass);
    }

    /**
     * 类型转换
     * 
     * @param source 源对象
     * @param targetType 目标类型
     */
    public static <T> T convert(Object source, Class<T> targetType) {
        return BaseConfig.getConversionService().convert(source, targetType);
    }

    /**
     * 转换IP地址
     * 
     * @param ip
     * @return
     */
    public static String convertIp(String ip) {
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "0.0.0.0".equals(ip)) {
            return EnvConsts.LOCALE_HOST;
        } else {
            return ip;
        }
    }

    /**
     * 格式化字符串
     * 
     * <pre>
     * 	<ul>
     * 	<li>format("中华{0}共和国{1}央{0}政府","人民", "中")        ==>中华人民共和国中央人民政府
     *  <li>format("中华{0}共和国{1}央{0}政府","人民")              ==>中华人民共和国央人民政府
     *  <li>format("中华{0}共和国{1}央{0}政府","人民", "中", "多余") ==>中华人民共和国中央人民政府
     *  <li>format("中华人民共和国中央人民政府","无占位符")           ==>中华人民共和国中央人民政府
     *  </ul>
     * </pre>
     * 
     * @param pattern 模式字符串
     * @param arguments 占位符参数
     * @return 格式化后的字符串
     */
    public static String format(String pattern, Object... arguments) {
        String msg = null;
        if (null == arguments || 0 == arguments.length) {
            msg = pattern;
        } else {
            msg = MessageFormat.format(pattern, arguments);
        }
        if (null != msg) {
            msg = localeReplacePattern.matcher(msg).replaceAll("");
        }
        return msg;
    }

    /**
     * 获取国际化信息
     * 
     * @param code
     * @param args
     * @return
     */
    public static String getMessage(String code, Object... args) {
        return getMessage(code, null, null, args);
    }

    /**
     * 获取国际化信息
     * 
     * @param code
     * @param locale
     * @param args
     * @return
     */
    public static String getMessage(String code, Locale locale, Object... args) {
        return getMessage(code, null, locale, args);
    }

    /**
     * 获取国际化信息
     * 
     * @param code 国际化文件中的key值
     * @param defaultMessage 默认信息
     * @param locale 国际化对象
     * @param args 国际化配置中的占位符参数
     * @return 国际化后的信息，如果有未替换的占位符，也会一起去掉
     */
    public static String getMessage(String code, String defaultMessage, Locale locale, Object... args) {
        ILocaleMessageResolver localeResolver = BaseConfig.getLocaleMessageResolver();
        String msg = localeResolver.getMessage(code, defaultMessage, locale, args);
        if (null != msg) {
            msg = localeReplacePattern.matcher(msg).replaceAll("");
        }
        return msg;
    }

    /**
     * 获取当前国际化对象
     * 
     * @return
     */
    public static Locale getCurrentLocale() {
        Locale locale = null;
        ILocaleResolver lr = BaseConfig.getLocaleResolver();
        if (null != lr) {
            locale = lr.getLocale();
        }
        return null == locale ? EnvConsts.SYSTEM_LOCALE : locale;
    }

    /**
     * 设置当前国际化对象
     * 
     * @param locale 国际化对象
     */
    public static void setCurrentLocale(Locale locale) {
        ILocaleResolver lr = BaseConfig.getLocaleResolver();
        if (null != lr) {
            lr.setLocale(locale);
        } else {
            Locale.setDefault(locale);
        }
    }

    /**
     * 将字符串解析为Locale对象
     * 
     * @param str locale字符串
     * @return Locale对象
     */
    public static Locale toLocale(String str) {
        return LocaleUtils.toLocale(str);
    }

    /**
     * 创建类实例
     * 
     * @param cls
     * @return
     */
    public static <T> T newInstance(Class<T> cls) {
        try {
            if (null == cls) {
                return null;
            }
            return BaseConfig.getObjectFactory().create(cls);
        } catch (Exception e) {
            CommonLogger.debug("can not create instance by default constructor, try use the objenesis method, the class : " + cls);
            return objenesis.newInstance(cls);
        }
    }

    /**
     * 创建类实例
     * 
     * @param className
     * @return
     */
    public static Object newInstance(String className) {
        if (null == className) {
            return null;
        }
        try {
            return BaseConfig.getObjectFactory().create(className);
        } catch (Exception e) {
            CommonLogger.debug("can not create instance by default constructor, try use the objenesis method, the class : " + className);
            Class<?> cls = forName(className);
            return objenesis.newInstance(cls);
        }
    }

    /**
     * 加载类
     * 
     * @param name
     * @return
     */
    public static Class<?> forName(String name) {
        return forName(name, null);
    }

    /**
     * 使用指定类加载器加载类
     * 
     * @param name
     * @param classLoader
     * @return
     */
    public static Class<?> forName(String name, ClassLoader classLoader) {
        try {
            if (null == classLoader) {
                classLoader = getDefaultClassLoader();
            }
            return org.springframework.util.ClassUtils.forName(name, classLoader);
        } catch (Exception ex) {
            throw Throw.createRuntimeException(ExceptionCodes.BF010005, ex, name);
        }
    }

    /**
     * 将类名称数组转换为类对象数组
     * 
     * @param classNames
     * @return
     */
    public static List<Class<?>> convertClassNamesToClasses(List<String> classNames) {
        if (classNames == null) {
            return null;
        }
        List<Class<?>> classes = new ArrayList<Class<?>>(classNames.size());
        ClassLoader classLoader = getDefaultClassLoader();
        for (String className : classNames) {
            try {
                classes.add(forName(className, classLoader));
            } catch (Exception ex) {
                classes.add(null);
            }
        }
        return classes;
    }

    /**
     * 将类对象数组转换为类名称数组
     * 
     * @param classes
     * @return
     */
    public static List<String> convertClassesToClassNames(List<Class<?>> classes) {
        if (classes == null) {
            return null;
        }
        List<String> classNames = new ArrayList<String>(classes.size());
        for (Class<?> cls : classes) {
            if (cls == null) {
                classNames.add(null);
            } else {
                classNames.add(cls.getName());
            }
        }
        return classNames;
    }

    /**
     * 获取默认类加载器
     * 
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
        }
        if (cl == null) {
            cl = CoreUtils.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                }
            }
        }
        return cl;
    }

    /**
     * 从类型中查找属性
     * 
     * @param clazz
     * @param name
     * @return
     */
    public static Field findField(Class<?> clazz, String name) {
        return findField(clazz, name, null);
    }

    /**
     * 从类型中查找属性
     * 
     * @param clazz
     * @param name
     * @param type
     * @return
     */
    public static Field findField(Class<?> clazz, String name, Class<?> type) {
        Class<?> searchType = clazz;
        while (Object.class != searchType && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if ((name == null || name.equals(field.getName())) && (type == null || type.equals(field.getType()))) {
                    return field;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * 加载资源对象
     * 
     * @param location 资源路径
     * @return 资源对象
     */
    public static Resource getResource(String location) {
        return getResource(null, location);
    }

    /**
     * 加载资源对象
     * 
     * @param environment 环境
     * @param location 资源路径
     * @return 资源对象
     */
    public static Resource getResource(Environment environment, String location) {
        if (null != environment) {
            location = environment.resolveRequiredPlaceholders(location);
        }
        File file = new File(location);
        if (file.exists()) {
            return new FileSystemResource(file);
        } else {
            ResourcePatternResolver loader = BaseConfig.getResourcePatternResolver();
            return loader.getResource(location);
        }
    }

    /**
     * 将模式字符串加载为资源对象集合
     * 
     * @param locationPattern 模式字符串
     * @return 资源对象集合
     */
    public static Set<Resource> getResources(String locationPattern) {
        return getResources(null, new String[] {locationPattern});
    }

    /**
     * 将一组模式字符串加载为资源对象集合
     * 
     * @param locationPatterns 一组模式字符串
     * @return 资源对象集合
     */
    public static Set<Resource> getResources(String[] locationPatterns) {
        return getResources(null, locationPatterns);
    }

    /**
     * 将指定环境下的一组模式字符串加载为资源对象集合
     * 
     * @param environment 指定环境对象
     * @param locationPatterns 一组模式字符串，可以使用环境中的变量
     * @return 资源对象集合
     */
    public static Set<Resource> getResources(Environment environment, String[] locationPatterns) {
        try {
            Set<Resource> resources = new LinkedHashSet<Resource>();
            ResourcePatternResolver loader = BaseConfig.getResourcePatternResolver();
            for (String location : locationPatterns) {
                if (null != environment) {
                    location = environment.resolveRequiredPlaceholders(location);
                }
                for (Resource resource : loader.getResources(location)) {
                    resources.add(resource);
                }
            }
            return resources;
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 根据父类/接口扫描类
     * 
     * @param basePackage
     * @param parent
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E> Set<Class<? extends E>> scanClassesByParentCls(String basePackage, Class<E> parent) {
        Set<Class<?>> set = scanClasses(basePackage, new ParentClassFilter(parent));
        Set<Class<? extends E>> rs = new LinkedHashSet<Class<? extends E>>();
        if (null != set && !set.isEmpty()) {
            for (Class<?> s : set) {
                rs.add((Class<? extends E>) s);
            }
        }
        return rs;
    }

    /**
     * 根据注解扫描类
     * 
     * @param basePackage
     * @param annoCls
     * @return
     */
    public static <E extends Annotation> Set<Class<?>> scanClasses(String basePackage, Class<E> annoCls) {
        return scanClasses(basePackage, new AnnoClassFilter(annoCls));
    }

    /**
     * 扫描类
     * 
     * @param basePackage
     * @param filter
     * @return
     */
    public static Set<Class<?>> scanClasses(String basePackage, ITypeFilter filter) {
        return BaseConfig.getScan().scanClasses(basePackage, filter);
    }

    /**
     * 根据注解扫描方法
     * 
     * @param basePackage
     * @param annoCls
     * @return
     */
    public static <E extends Annotation> Set<Method> scanMethods(String basePackage, Class<E> annoCls) {
        return scanMethods(basePackage, new AnnoMethodFilter(annoCls));
    }

    /**
     * 扫描方法
     * 
     * @param basePackage
     * @param filter
     * @return
     */
    public static Set<Method> scanMethods(String basePackage, IMethodFilter filter) {
        return BaseConfig.getScan().scanMethods(basePackage, filter);
    }

    /**
     * 获取属性
     * 
     * @param bean
     * @param property
     * @return
     */
    public static Object getProperty(Object bean, String property) {
        return getBeanOperateWrapper().getProperty(bean, property);
    }

    /**
     * 获取属性
     * 
     * @param bean
     * @param property
     * @param resultType
     * @return
     */
    public static <E> E getProperty(Object bean, String property, Class<E> resultType) {
        return getBeanOperateWrapper().getProperty(bean, property, resultType);
    }

    /**
     * 设置属性
     * 
     * @param bean
     * @param property
     * @param value
     */
    public static void setProperty(Object bean, String property, Object value) {
        getBeanOperateWrapper().setProperty(bean, property, value);
    }

    /**
     * 移除属性
     * 
     * @param bean
     * @param property
     */
    public static void removeProperty(Object bean, String property) {
        getBeanOperateWrapper().removeProperty(bean, property);
    }

    /**
     * 获取上下文环境中的表达式值
     * 
     * @param bean
     * @param expression
     * @param context
     * @return
     */
    public static Object getProperty(Object bean, String expression, Map<String, Object> context) {
        return getBeanOperateWrapper().getProperty(bean, expression, context);
    }

    /**
     * 获取上下文环境中的表达式值
     * 
     * @param bean
     * @param expression
     * @param context
     * @param resultType
     * @return
     */
    public static <E> E getProperty(Object bean, String expression, Map<String, Object> context, Class<E> resultType) {
        return getBeanOperateWrapper().getProperty(bean, expression, context, resultType);
    }

    /**
     * 设置上下文环境中的表达式值
     * 
     * @param bean
     * @param expression
     * @param value
     * @param context
     */
    public static void setProperty(Object bean, String expression, Object value, Map<String, Object> context) {
        getBeanOperateWrapper().setProperty(bean, expression, value, context);
    }

    /**
     * 获取一组表达式的值
     * 
     * @param bean
     * @param expressions
     * @return
     */
    public static Object[] getProperties(Object bean, String[] expressions) {
        return getProperties(bean, expressions, (Map<String, Object>) null);
    }

    /**
     * 获取一组表达式的值
     * 
     * @param bean
     * @param expressions
     * @param context
     * @return
     */
    public static Object[] getProperties(Object bean, String[] expressions, Map<String, Object> context) {
        if (null == expressions || null == bean || 0 == expressions.length) {
            return new Object[0];
        }
        int length = expressions.length;
        Object[] result = new Object[length];
        for (int i = 0; i < length; i++) {
            result[i] = getProperty(bean, expressions[i], context);
        }
        return result;
    }

    private static IContextBeanOperateWrapper getBeanOperateWrapper() {
        return BaseConfig.getBeanOperateWrapper();
    }

    /**
     * 放入当前线程缓存
     * 
     * @param key
     * @param value
     */
    public static void putThreadCache(String key, Object value) {
        cache.get().put(key, value);
    }

    /**
     * 获取当前线程缓存
     * 
     * @param key
     * @param cls
     * @return
     */
    public static <E> E getThreadCache(String key, Class<E> cls) {
        return cls.cast(cache.get().get(key));
    }

    /**
     * 移除当前线程缓存
     * 
     * @param key
     */
    public static void removeThreadCache(String key) {
        cache.get().remove(key);
    }

    /**
     * 清空当前线程缓存
     */
    public static void clearThreadCache() {
        cache.get().clear();
        cache.remove();
    }

    /**
     * 关闭对象
     * 
     * @param closeable
     */
    public static void closeQuietly(Closeable... closeable) {
        if (null != closeable && closeable.length >= 1) {
            for (Closeable c : closeable) {
                if (null != c) {
                    try {
                        c.close();
                    } catch (IOException i) {
                    }
                }
            }
        }
    }

    /**
     * 获取当前日期，格式为平台配置中的BaseConfig.getDateFormat()，默认格式yyyyMMdd
     * 
     * @return
     */
    public static String getDate() {
        return getFormatDate(new Date(), BaseConfig.getDateFormat());
    }

    /**
     * 获取当前时间，格式为平台配置中的BaseConfig.getTimeFormat()，默认格式HH:mm:ss
     * 
     * @return
     */
    public static String getTime() {
        return getFormatDate(new Date(), BaseConfig.getTimeFormat());
    }

    /**
     * 获取当前日期时间，格式为平台配置中的BaseConfig.getDatetimeFormat()，默认格式yyyyMMdd-HH:mm:ss
     * 
     * @return
     */
    public static String getDateAndTime() {
        return getDateAndTime(new Date());
    }

    /**
     * 日期时间格式化
     * 
     * @param date 日期对象，格式为平台配置中的BaseConfig.getDatetimeFormat()，默认格式yyyyMMdd-HH:mm:ss
     * @return 格式化后的日期字符串
     */
    public static String getDateAndTime(Date date) {
        return getFormatDate(date, BaseConfig.getDatetimeFormat());
    }

    /**
     * 日期时间格式化
     * 
     * @param date 日期对象
     * @param format 日期格式
     * @return 格式化后的日期字符串
     */
    public static String getFormatDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String rs = df.format(date);
        return rs;
    }

    /**
     * 日期计算
     * 
     * @param date 日期字符串，格式为平台配置中的BaseConfig.getDateFormat()，默认格式yyyyMMdd
     * @param mYear 需要增加的年数，如果需要减少，传入负数
     * @param mMonth 需要增加的月数，如果需要减少，传入负数
     * @param mDay 需要增加的日数，如果需要减少，传入负数
     * @return 计算后并且格式化的日期字符串
     */
    public static String dateCalculate(String date, int mYear, int mMonth, int mDay) {
        return dateCalculate(date, BaseConfig.getDateFormat(), mYear, mMonth, mDay);
    }

    /**
     * 日期计算
     * 
     * @param date 日期字符串
     * @param format 日期格式
     * @param mYear 需要增加的年数，如果需要减少，传入负数
     * @param mMonth 需要增加的月数，如果需要减少，传入负数
     * @param mDay 需要增加的日数，如果需要减少，传入负数
     * @return 计算后并且格式化的日期字符串
     */
    public static String dateCalculate(String date, String format, int mYear, int mMonth, int mDay) {
        try {
            DateFormat fo = new SimpleDateFormat(format);
            Date sDate = fo.parse(date);
            Date rDate = dateCalculate(sDate, mYear, mMonth, mDay);
            return fo.format(rDate);
        } catch (ParseException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 日期计算
     * 
     * @param date 日期对象
     * @param mYear 需要增加的年数，如果需要减少，传入负数
     * @param mMonth 需要增加的月数，如果需要减少，传入负数
     * @param mDay 需要增加的日数，如果需要减少，传入负数
     * @return 计算后的日期对象
     */
    public static Date dateCalculate(Date date, int mYear, int mMonth, int mDay) {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(date);
        tempCal.add(Calendar.YEAR, mYear);
        tempCal.add(Calendar.MONTH, mMonth);
        tempCal.add(Calendar.DATE, mDay);
        return tempCal.getTime();
    }

    /**
     * 计算是当年的第几个日期
     * 
     * @param year 年份
     * @param month 月份
     * @param day 日数
     * @return 当年的第几个日期
     */
    public static int dayOfYear(int year, int month, int day) {
        int day_of_year;
        if (isLeapYear(year)) {
            day_of_year = ((275 * month) / 9) - ((month + 9) / 12) + day - 30;
        } else {
            day_of_year = ((275 * month) / 9) - (((month + 9) / 12) << 1) + day - 30;
        }
        return day_of_year;
    }

    /**
     * 判断是否闰年
     * 
     * @param year 年份
     * @return 是否闰年
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * 获取月份的最大日期
     * 
     * @param year 年份
     * @param month 月份
     * @return 月份的最大日期
     */
    public static int getMaxDayOfMonth(int year, int month) {
        if ((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        if ((year == 1582) && (month == 10)) {
            return 21;
        }
        return MAX_DAY_OF_MONTH[month];
    }

    /**
     * 根据日期格式获取指定时间的毫秒数
     * 
     * @param datetime 日期时间
     * @param datetimeFormat 日期时间格式
     * @return 毫秒数
     */
    public static long getTime(String datetime, String datetimeFormat) {
        try {
            DateFormat fo = new SimpleDateFormat(datetimeFormat);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fo.parse(datetime));
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 运行操作系统命令
     * 
     * @param cmd
     * @return
     * @since 1.1.0
     */
    public synchronized static int runOSCommand(String... cmd) {
        int result = -1;
        BufferedReader bufferReader = null;
        BufferedReader bufferErrorReader = null;
        Process pid = null;
        try {
            if (0 == cmd.length) {
                pid = Runtime.getRuntime().exec(cmd[0]);
            } else {
                pid = Runtime.getRuntime().exec(cmd);
            }

            bufferReader = new BufferedReader(new InputStreamReader(pid.getInputStream()));
            while (bufferReader.readLine() != null) {
                // 清除process的流，以免造成process阻塞
            }

            bufferErrorReader = new BufferedReader(new InputStreamReader(pid.getErrorStream(), EnvConsts.OS_ENCODING));
            StringBuffer error = new StringBuffer();
            String line = null;
            while ((line = bufferErrorReader.readLine()) != null) {
                // 清除process的流，以免造成process阻塞
                error.append(EnvConsts.LINE_SEPARATOR).append(line);
            }
            if (!CoreUtils.isBlank(error)) {
                CommonLogger.error(error.toString());
            }
            pid.waitFor();
            result = pid.exitValue();
            if (0 != result && 2 != result) {
                Throw.throwRuntimeException("执行" + StringUtils.join(cmd, " ") + "命令异常");
            }
        } catch (Exception ioe) {
            Throw.throwRuntimeException("执行" + StringUtils.join(cmd, " ") + "命令异常", ioe);
        } finally {
            CoreUtils.closeQuietly(bufferReader, bufferErrorReader);
            if (pid != null) {
                pid.destroy();
            }
        }
        return result;
    }
}
