package com.forms.beneform4j.webapp.common.kindeditor.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 富文本输入的后台服务接口<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
public interface IKindeditorService {

    /**
     * 回显附件处理
     * 
     * @param file
     * @param write
     * @throws IOException
     */
    public void sOutputAttachedFile(File file, OutputStream write) throws IOException;

    /**
     * 附件上传处理
     * 
     * @param dir
     * @param contextPath
     * @param items
     * @return
     */
    public Map<String, Object> sFileUpload(String dir, String contextPath, List<?> items);

    /**
     * 出错信息封装
     * 
     * @param errorMsg
     * @return
     */
    public Map<String, Object> sGetError(String errorMsg);

    /**
     * 后台附件管理
     * 
     * @param dirName
     * @param contextPath
     * @param path
     * @param order
     * @return
     */
    public Object sFileManager(String dirName, String contextPath, String path, String order);

    /**
     * 获取服务器文件
     * 
     * @param relativeDirPath
     * @param fileName
     * @return
     */
    public File sGetServerFile(String relativeDirPath, String fileName);
}
