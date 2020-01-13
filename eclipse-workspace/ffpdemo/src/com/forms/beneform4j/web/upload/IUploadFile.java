package com.forms.beneform4j.web.upload;

import java.io.File;
import java.io.InputStream;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 上传的文件接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public interface IUploadFile {

    /**
     * 上传文件的表单域
     * 
     * @return 表单域的name属性
     */
    public String getName();

    /**
     * 原始文件名
     * 
     * @return 文件名称
     */
    public String getOriginalFilename();

    /**
     * 类型
     * 
     * @return 内容类型
     */
    public String getContentType();

    /**
     * 是否为空
     * 
     * @return 文件是否为空
     */
    public boolean isEmpty();

    /**
     * 大小
     * 
     * @return 文件大小
     */
    public long getSize();

    /**
     * 字节
     * 
     * @return 文件字节
     */
    public byte[] getBytes();

    /**
     * 获取输入流
     * 
     * @return 文件输入流
     */
    public InputStream getInputStream();

    /**
     * 存储至本地文件
     * 
     * @param file 本地文件
     */
    public void transferTo(File file);

    /**
     * 删除临时文件
     */
    public void deleteTmpFile();
}
