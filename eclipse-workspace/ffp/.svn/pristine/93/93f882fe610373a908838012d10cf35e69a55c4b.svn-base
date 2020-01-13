package com.forms.beneform4j.webapp.common.docmanager.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.forms.beneform4j.webapp.common.docmanager.bean.AttachBean;
import com.forms.beneform4j.webapp.common.docmanager.bean.DataParamBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 附件管理服务接口<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-25<br>
 */
public interface IDocManagerService {

    /**
     * 用于富文本用的附件上传处理 将数据存放到FTP 说明：开发人员无须使用此方法
     * 
     * @param uploadedFile
     * @param relativeDir
     */
    void sStoreFileRemoteForKindEditor(File uploadedFile, String relativeDir);

    /**
     * 用户富文件的文件获取 获取远程FTP上的文件 说明：开发人员无须使用此方法
     * 
     * @param relativeDirPath
     * @param fileName
     * @return
     */
    File sGetFileRemoteForKindEditor(String relativeDirPath, String fileName);

    /**
     * 文件下载公用方法 下载一个附件编号下面的指定的文档 例如：一个附件编号对应了三个文档，可以选择下载里面的两个文档 说明：提供开发人员使用
     * 
     * @param attachId 附件编号
     * @param docIds 文档编号
     * @return
     * @throws Exception
     */
    DataParamBean sDownLoadFile(String attachId, String[] docIds) throws Exception;

    /**
     * 上传附件的公共方法 上传单个文件 说明：提供开发人员使用
     * 
     * @param docForm
     * @return 返回一个附件对象，包含附件编号、文件编号等信息
     * @throws Exception
     */
    List<AttachBean> sUploadFile(DocForm docForm) throws Exception;

    /**
     * 上传文件
     * 
     * @param bussinessKey 业务主键
     * @param fileKeys 页面上传控制返回回来的文件编号
     * @return
     * @throws Exception
     */
    List<AttachBean> sUploadFile(String bussinessKey, String[] fileKeys) throws Exception;

    /**
     * 流试上传一个文件的处理
     * 
     * @param fileName 上传的原文件名称
     * @param input 输入流对象
     * @return AttachBean：一个附件list，对于多文件，bean对象当中的附件编号一样 文档编号唯一
     * @throws Exception
     */
    AttachBean sUploadFile(String fileName, InputStream input) throws Exception;

    /**
     * 多个文件上传处理
     * 
     * @param filePath 上传的文件，带文件路径的文件，例如 root/a/b/1.txt
     * @return AttachBean：一个附件list，对于多文件，bean对象当中的附件编号一样 文档编号唯一
     * @throws Exception
     */
    List<AttachBean> sUploadFiles(String[] filePath) throws Exception;

    /**
     * 文件上传时，将文件临时存放到数据库临时表
     * 
     * @param docForm 数据对象 说明：无须开发人员使用
     * @return
     */
    int sPlugUpload(DocForm docForm);

    /**
     * 删除文件(非临时文件，主要是删除本地正式文件，或远程服务器上文件)
     * 
     * @param docId
     * @return
     * @throws Exception
     */
    public int sDeleteFile(String docId) throws Exception;

}
