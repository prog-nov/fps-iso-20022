package com.forms.beneform4j.core.util.logger.log4j.filenameresolver.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.FileAppender;

import com.forms.beneform4j.core.util.env.EnvConsts;
import com.forms.beneform4j.core.util.logger.log4j.filenameresolver.ILog4jFilenameResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 添加主机IP的Log4j日志文件名解析器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class AddHostFilenameResolver implements ILog4jFilenameResolver {

    /**
     * 解析新文件名，在日志文件名前添加主机IP文件夹
     */
    @Override
    public String resolverFileName(FileAppender fileAppender, String oldFileName) {
        String path = FilenameUtils.getFullPathNoEndSeparator(oldFileName);
        if (!path.endsWith("/" + EnvConsts.LOCALE_HOST)) {
            path += "/" + EnvConsts.LOCALE_HOST;
        }
        return path + "/" + FilenameUtils.getBaseName(oldFileName) + "." + FilenameUtils.getExtension(oldFileName);
    }
}
