package com.forms.beneform4j.webapp.common.docmanager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.docmanager.bean.AttachBean;
import com.forms.beneform4j.webapp.common.docmanager.bean.DataParamBean;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.docmanager.dao.IDocManagerDao;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;
import com.forms.beneform4j.webapp.common.docmanager.util.FileService;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文件处理的公共服务类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016年5月24日<br>
 */
@Service("docManagerService")
@Scope("prototype")
public class DocManagerService implements IDocManagerService {

    @Resource(name = "docManagerDao")
    private IDocManagerDao docManagerDao;

    @Resource(name = "fileService")
    private FileService fileService;

    /**
     * 富文件的后台远程文件存放处理
     */
    @Override
    public void sStoreFileRemoteForKindEditor(File uploadedFile, String relativeDir) {
        // 代码是要存放到远程目录
        if (fileService.isFtpStore()) {
            // FTP上的目录
            String ftpFileDir = ParamHolder.getParameter("DOC_FILE_FTP_DIR") + relativeDir;
            fileService.sPutFileToRemote(uploadedFile.getAbsolutePath(), ftpFileDir);
        }
    }

    /**
     * 富文件远程文件获取处理
     */
    @Override
    public File sGetFileRemoteForKindEditor(String relativeDirPath, String fileName) {
        if (fileService.isFtpStore()) {
            fileName = fileService.getRealPath(ParamHolder.getParameter("DOC_FILE_FTP_DIR") + relativeDirPath) + fileName;
        } else {
            fileName = fileService.getRealPath(ParamHolder.getParameter("KINDEDIT_FILE_PATH") + relativeDirPath) + fileName;
        }
        return fileService.sGetServerFile(fileName);
    }

    /**
     * 文件下载公共服务类 1、下载附件编号下的所有文件； 2、下载附件编号下指定的文档编号的的文档 3、下载指定文档编号的一个或多个文档
     * 说明：系统自动根据文档的个数据，进行压缩判断，如果是多于一个文件的，则进行压缩处理。返回压缩后的文件；
     */
    @Override
    public DataParamBean sDownLoadFile(String attachId, String[] docIds) throws Exception {

        DataParamBean rtnBean = new DataParamBean();

        // 获取需要进行下载的数据文件
        List<DocBean> listBeans = getDocList(attachId, docIds);

        // 检查文件状态
        checkDoc(listBeans);

        // 如果附件编号为空，则需要虚拟一下载的压缩编号
        attachId = Tool.CHECK.isEmpty(attachId) ? Tool.STRING.getRandomKeyId() : attachId;

        rtnBean.setFile(getDocFile(attachId, listBeans));
        if (listBeans.size() == 1) {
            rtnBean.setFileName(listBeans.get(0).getDocName());
        } else {
            rtnBean.setFileName(attachId);
        }
        return rtnBean;
    }

    /**
     * 文件上传处理服务方法，实现文件的上传处理 docForm当中的fileKeys字段必填；此字段为一个数据组； 如果fileKeys中是一个文件key，则是单文件上传；
     * 如果fileKeys中是一组文件key，则是多文件上传 说明：文件上传后，会归档到相关的附件号与文档表当中，临时目录数据、临时表数据会清掉
     * 在后台数据参数维护为文档远程存放时，支持集群环境；
     */
    @Override
    public List<AttachBean> sUploadFile(DocForm docForm) throws Exception {
        List<AttachBean> rtnAttachBeans = null;
        if (!Tool.CHECK.isEmpty(docForm.getFileKeys()) && docForm.getFileKeys().length > 0) {
            rtnAttachBeans = new ArrayList<AttachBean>();
            String attachId = Tool.STRING.getRandomKeyId();
            for (String fileKey : docForm.getFileKeys()) {
                rtnAttachBeans.add(storeOneFile(docForm, fileKey, attachId));
            }
        } else {
            Throw.throwRuntimeException(ExceptionCodes.AP020406);
        }
        // 返回附件编号
        return rtnAttachBeans;
    }

    /**
     * 前台web上传，需要插件返回的filekeys
     */
    @Override
    public List<AttachBean> sUploadFile(String bussinessKey, String[] fileKeys) throws Exception {
        DocForm docForm = new DocForm();
        docForm.setBussinessKey(bussinessKey);
        docForm.setFileKeys(fileKeys);
        return sUploadFile(docForm);
    }

    /**
     * 后台以流的形式，上传文件
     */
    @Override
    public AttachBean sUploadFile(String inputFileName, InputStream input) throws Exception {
        AttachBean attachBean = null;
        // 将数据存放到临时目录
        DocForm docForm = storeInputStream(inputFileName, input);
        // 将附件的信息进行入库处理
        sPlugUpload(docForm);

        String attachId = Tool.STRING.getRandomKeyId();
        attachBean = storeOneFile(docForm, docForm.getDocId(), attachId);
        // 返回附件编号
        return attachBean;
    }

    /**
     * 后台多文件上传处理
     */
    @Override
    public List<AttachBean> sUploadFiles(String[] filePath) throws Exception {
        List<AttachBean> rtnAttachBeans = new ArrayList<AttachBean>();
        if (Tool.CHECK.isEmpty(filePath) || filePath.length < 1) {
            Throw.throwRuntimeException(ExceptionCodes.AP020408);
        }
        String attachId = Tool.STRING.getRandomKeyId();
        for (String fileName : filePath) {
            DocForm docForm = iniDocForm(FilenameUtils.getName(fileName));
            // 将用户的文件上传到临时目录
            Tool.IO.copy(new FileInputStream(fileName), new FileOutputStream(docForm.getStorePath()));
            // 将附件的信息进行入库处理
            sPlugUpload(docForm);
            rtnAttachBeans.add(storeOneFile(docForm, docForm.getDocId(), attachId));
        }
        // 返回附件编号
        return rtnAttachBeans;
    }

    /**
     * @Title: sPlugUpload @Description: 插件上传文件 在点击文件时，就会将文件上传到临时目录，并写入后台数据库临时表 @param
     *         docForm：入库的数据对象 @return @throws
     */
    public int sPlugUpload(DocForm docForm) {
        File f = new File(docForm.getStorePath());
        docForm.setDocSize(f.length() + "");
        // 计算MD5
        String md5 = Tool.STRING.getMd5(f);
        docForm.setCheckSum(md5);
        docForm.setCheckSumType("MD5");
        docForm.setDocState("1");
        docForm.setInstDate(Tool.DATE.getDate());
        docForm.setInstTime(Tool.DATE.getTime());

        return docManagerDao.dPlugInsert(docForm);
    }

    /**
     * 删除文件 oldFile ：为数据库表当中存放的全文件路径 例如：d:/a/b/c/d/1.txt 或者 root/a/b/c/d/1.txt
     */
    @Override
    public int sDeleteFile(String docId) throws Exception {
        // 根据文件编号查找对应的文件
        DocBean docBean = docManagerDao.dFind(docId);
        DocBean docMidBean = docManagerDao.dFindMid(docId);

        deleteServerFile(docBean);
        deleteTempFile(docMidBean);
        // 删除后台文档表与附件表的数据
        int[] i = docManagerDao.dDeleteDocAndAttach(docId);
        return i.length;
    }

    /**
     * @Title: storeOneFile @Description: 存储文件，将临时目录中的文件转存对正式的目录结构下，并将数据持久化处理 @param 传入参数说明 @return
     *         返回结果说明 @throws
     */
    @Transactional(rollbackFor = Throwable.class)
    private DocBean storeOneFile(DocForm docForm, String docId, String attachId) throws Exception {
        // 根据fileKey先查找出相关的文件
        DocBean docBean = iniDocBean(docForm, docId, attachId);
        // 将文档数据入正式库
        docManagerDao.dCopyDocRecode(docBean);
        // 删除临时表数据
        docManagerDao.dDeleteMidData(docBean.getDocId());
        // 写附件表
        docManagerDao.dInsertAttach(docBean);
        // 将文件从临时目录当中转存到相关的正式目录
        fileService.sCopyFile(docBean.getStorePathTemp(), docBean.getStorePath());
        return docBean;
    }

    /**
     * @Title: iniDocBean @Description: 初始化数据bean @param docForm:前端传过来的数据 docId：文档ID @return
     *         DocBean：数据模型对象 @throws
     */
    private DocBean iniDocBean(DocForm docForm, String docId, String attachId) {
        DocBean docBean = docManagerDao.dFindMid(docId);
        docBean.setAttachId(attachId);
        // 设置文件类型
        if (!Tool.CHECK.isEmpty(docForm.getDocType())) {
            docBean.setDocType(docForm.getDocType());
        }
        // 设置文件状态
        if (!Tool.CHECK.isEmpty(docForm.getDocState())) {
            docBean.setDocState(docForm.getDocState());
        }
        // 设置临时目录
        docBean.setStorePathTemp(docBean.getStorePath());
        String storeFile = "";
        if (fileService.isFtpStore()) {
            storeFile = docBean.getStorePath().replaceFirst(ParamHolder.getParameter("DOC_FILE_PATH_TEMP"), fileService.getRealPath(ParamHolder.getParameter("DOC_FILE_FTP_DIR")));
        } else {
            storeFile = docBean.getStorePath().replaceFirst(ParamHolder.getParameter("DOC_FILE_PATH_TEMP"), fileService.getRealPath(ParamHolder.getParameter("DOC_FILE_PATH")));
        }
        docBean.setStorePath(storeFile);
        return docBean;
    }

    /**
     * @Description: 检查文档 @param 传入参数说明 @return 返回结果说明 @throws
     */
    private void checkDoc(List<DocBean> listBeans) {
        for (DocBean db : listBeans) {
            if ("0".equals(db.getDocState())) {
                Throw.throwRuntimeException(ExceptionCodes.AP020401);
            }
        }
    }

    /**
     * @Title: getDocFile @Description: 对文件进行加工处理 @param 传入参数说明 @return 返回结果说明 @throws
     */
    private File getDocFile(String attachId, List<DocBean> listBeans) throws Exception {
        File file = null;
        if (!Tool.CHECK.isEmpty(listBeans)) {
            if (listBeans.size() > 1) {
                List<DocBean> list = new ArrayList<DocBean>();
                for (int i = 0; i < listBeans.size(); i++) {
                    // 将文件从FTP上放到本地临时目录
                    File tempFile = fileService.sGetServerFile(listBeans.get(i).getStorePath());
                    DocBean docBean = new DocBean();
                    BeanUtils.copyProperties(docBean, listBeans.get(i));
                    docBean.setStorePath(tempFile.getAbsolutePath());
                    list.add(docBean);
                }
                // 需求进行压缩处理
                file = fileService.sZipFileList(list, attachId);
            } else if (listBeans.size() == 1) {
                // 只有一个文件，无需压缩处理
                file = fileService.sGetServerFile(listBeans.get(0).getStorePath());
            } else {
                Throw.throwRuntimeException(ExceptionCodes.AP020407);
            }
        } else {
            Throw.throwRuntimeException(ExceptionCodes.AP020407);
        }
        return file;
    }

    /**
     * @Title: getDocList @Description: 获取文件数据 @param 传入参数说明 @return 返回结果说明 @throws
     */
    private List<DocBean> getDocList(String attachId, String[] docIds) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("attachId", attachId);
        params.put("docIds", docIds);
        List<DocBean> listBeans = docManagerDao.dList(params);
        if (Tool.CHECK.isEmpty(listBeans) || listBeans.size() < 1) {
            Throw.throwRuntimeException(ExceptionCodes.AP020402);
        }
        return listBeans;
    }

    /**
     * @Title: storeInputStream @Description: 将一个流中的数据存放到本地服务器临时目录当中 @param inputFileName：原文件名称
     *         input：数据流 @return DocForm ：文件数据对象，有路径信息，文件名信息，后台服务器文件名信息 @throws
     */
    private DocForm storeInputStream(String inputFileName, InputStream input) {
        DocForm docForm = iniDocForm(inputFileName);
        // 将流当中的数据放到临时目录
        File fileDir = new File(FilenameUtils.getFullPath(docForm.getStorePath()));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(docForm.getStorePath());
            IOUtils.copy(input, fw, Beneform4jConfig.getEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Tool.IO.closeQuietly(fw);
        }
        return docForm;
    }

    /**
     * @Title: iniDocForm @Description: 初始化一个参数数据bean @param 传入参数说明 @return 返回结果说明 @throws
     */
    private DocForm iniDocForm(String fileName) {
        DocForm docForm = new DocForm();
        docForm.setDocId(Tool.STRING.getRandomKeyId());
        // 获取临时目录
        String tmepFileName = FilenameUtils.getFullPathNoEndSeparator(ParamHolder.getParameter("DOC_FILE_PATH_TEMP")) + File.separator + Tool.DATE.getDate() + File.separator + docForm.getDocId();
        if (!Tool.CHECK.isEmpty(fileName)) {
            tmepFileName = tmepFileName + "." + FilenameUtils.getExtension(fileName);
            docForm.setSuffix(FilenameUtils.getExtension(fileName));
            docForm.setDocName(fileName);// 如果调用的函数传过来的文件名不为空，则原文件名为传过来的文件名
        } else {
            docForm.setDocName(docForm.getDocId());// 如果调用的函数传过来的文件名为空，则原文件名为系统生成的文件名
        }
        docForm.setStorePath(tmepFileName);
        docForm.setDocType("2");// 设置文件类型，通过其它方式进系统的都是
        return docForm;
    }

    /**
     * 删除临时目录中的文档
     * 
     * @param docMidBean
     */
    private void deleteTempFile(DocBean docMidBean) {
        // 删除临时目录中的数据文档
        if (!Tool.CHECK.isEmpty(docMidBean) && !Tool.CHECK.isEmpty(docMidBean.getStorePath())) {
            File f = new File(docMidBean.getStorePath());
            if (f.exists() && f.isFile()) {
                // 一断出现了异常，则需要对已经上传的数据文件进行删除操作处理
                Tool.FILE.forceDelete(f);
            }
        }
    }

    /**
     * 删除服务器上的文档
     * 
     * @param docBean
     */
    private void deleteServerFile(DocBean docBean) {
        if (!Tool.CHECK.isEmpty(docBean) && !Tool.CHECK.isEmpty(docBean.getStorePath())) {
            if (fileService.isFtpStore()) {
                fileService.sRemoveFileInFtp(docBean.getStorePath());
            } else {
                File f = new File(docBean.getStorePath());
                if (f.exists() && f.isFile()) {
                    // 一断出现了异常，则需要对已经上传的数据文件进行删除操作处理
                    Tool.FILE.forceDelete(f);
                }
            }
        }
    }
}
