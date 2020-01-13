package com.forms.beneform4j.core.util.env;

import java.io.File;
import java.net.Inet4Address;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.SystemUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 系统环境相关的常量<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public abstract class EnvConsts {

    /**
     * 是否为Windows操作系统
     */
    public static final boolean IS_WINDOWS = SystemUtils.IS_OS_WINDOWS;

    /**
     * 文件编码
     */
    public static final String FILE_ENCODING = SystemUtils.FILE_ENCODING;

    /**
     * 操作系统编码
     */
    public static final String OS_ENCODING = getSystemProperty("sun.jnu.encoding");

    /**
     * 目录路径分隔符，如windows为“\”，UNIX为“/”
     */
    public static final String PATH_SEPARATOR = File.separator;

    /**
     * 行分隔符
     */
    public static final String LINE_SEPARATOR = SystemUtils.LINE_SEPARATOR;

    /**
     * JDK版本，只读取前面3位，如1.6,1.7
     */
    public static final double JDK_VERSION = Double.parseDouble(SystemUtils.JAVA_VERSION.substring(0, 3));

    /**
     * 临时路径
     */
    public final static String TMP_DIR = SystemUtils.JAVA_IO_TMPDIR;

    /**
     * 用户路径
     */
    public static final String USER_HOME = SystemUtils.USER_HOME;

    /**
     * 用户地区
     */
    public static final String USER_COUNTRY = SystemUtils.USER_COUNTRY;

    /**
     * 用户语言
     */
    public static final String USER_LANGUAGE = SystemUtils.USER_LANGUAGE;

    /**
     * 用户时区
     */
    public static final String USER_TIMEZONE = SystemUtils.USER_TIMEZONE;

    /**
     * 当前LOCALE
     */
    public static final Locale SYSTEM_LOCALE = Locale.getDefault();

    /**
     * 当前时区
     */
    public static final TimeZone SYSTEM_TIMEZONE = TimeZone.getDefault();

    /**
     * 当前主机IP
     */
    public static final String LOCALE_HOST = ToolInnerConsts.LOCALE_HOST;

    // 利用内部类实现需要初始化，但是初始化之后就不能再修改的常量
    private static class ToolInnerConsts {
        private static String LOCALE_HOST;
        static {
            try {
                LOCALE_HOST = Inet4Address.getLocalHost().getHostAddress();
            } catch (Exception e) {
            }
        }
    }

    private static String getSystemProperty(final String property) {
        try {
            return System.getProperty(property);
        } catch (final SecurityException ex) {
            // we are not allowed to look at this property
            System.err.println("Caught a SecurityException reading the system property '" + property + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }
}
