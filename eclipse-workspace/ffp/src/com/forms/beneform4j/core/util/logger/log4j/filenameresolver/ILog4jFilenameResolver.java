package com.forms.beneform4j.core.util.logger.log4j.filenameresolver;

import org.apache.log4j.FileAppender;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Log4j日志文件名解析器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface ILog4jFilenameResolver {

    /**
     * 根据文件输出终端和就文件名解析新文件名
     * 
     * @param fileAppender 文件输出终端
     * @param oldFileName 旧文件名
     * @return 新文件名
     */
    public String resolverFileName(FileAppender fileAppender, String oldFileName);
}
