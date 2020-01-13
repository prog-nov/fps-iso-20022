package com.forms.datapipe.threadpool;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 对应配置文件 threadpool.properties
 * 
 * @author cxl
 *
 */
public class ThreadPoolConfig
{
    /**
     * 配置文件名
     */
    public static final String FILE_NAME = "threadpool";

    /**
     * 属性集合
     */
    private static Properties props;

    /**
     * init resource
     */
    static
    {
        // init default
        ResourceBundle resource = ResourceBundle.getBundle(FILE_NAME,
            Locale.getDefault());
        Enumeration<String> e = resource.getKeys();
        props = new Properties();
        while (e.hasMoreElements())
        {
            String s = e.nextElement().toString();
            props.setProperty(s, resource.getString(s));
        }
    }

    /**
     * 获取配置文件的属性
     * 
     * @param field
     * @return
     */
    public static String get(String field)
    {
        return props.getProperty(field);
    }
}

