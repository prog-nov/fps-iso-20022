package com.forms.beneform4j.core.util.logger.log4j;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.FileAppender;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.logger.log4j.filenameresolver.ILog4jFilenameResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 自定义文件名的Log4j终端帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class CustomLoggerAppenderHolder {

    private static final Map<String, ILog4jFilenameResolver> map = new HashMap<String, ILog4jFilenameResolver>();

    /**
     * 解析自定义的文件名称
     * 
     * @param fileAppender 文件输出终端
     * @param filenameResolver 文件名称解析类名
     * @param oldFileName 旧文件名
     * @return 解析后的新文件名
     */
    public static String resolverCustomFileName(FileAppender fileAppender, String filenameResolver, String oldFileName) {
        if (null == filenameResolver) {
            return oldFileName;
        } else {
            ILog4jFilenameResolver resolver = getResolver(filenameResolver);
            if (null == resolver) {
                return oldFileName;
            } else {
                String newFileName = resolver.resolverFileName(fileAppender, oldFileName);
                if (CoreUtils.isBlank(newFileName)) {
                    return oldFileName;
                } else {
                    return newFileName;
                }
            }
        }
    }

    /**
     * 获取文件名称解析类实例
     * 
     * @param filenameResolver
     * @return
     */
    private static ILog4jFilenameResolver getResolver(String filenameResolver) {
        if (map.containsKey(filenameResolver)) {
            return map.get(filenameResolver);
        } else {
            ILog4jFilenameResolver rs = null;
            try {
                Object o = CoreUtils.newInstance(filenameResolver);
                if (o instanceof ILog4jFilenameResolver) {
                    rs = (ILog4jFilenameResolver) o;
                }
            } catch (Exception e) {
            }
            map.put(filenameResolver, rs);
            return rs;
        }

    }
}
