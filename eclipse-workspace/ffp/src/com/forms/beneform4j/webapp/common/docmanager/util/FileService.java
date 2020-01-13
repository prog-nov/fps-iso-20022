package com.forms.beneform4j.webapp.common.docmanager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 附件管理辅助处理类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-6-1<br>
 */
@Component("fileService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FileService {

    /**
     * 压缩需要进行压缩的文件 将多个文件（非目录）压缩到一个文件当中
     * 
     * @param fileList 传入的文件列表，每一个都是一个全路径（包含文件名）的文件
     * @param attachId 压缩到的文件
     * @return
     * @throws Exception
     */
    public File sZipFileList(List<DocBean> fileList, String attachId) throws Exception {
        ZipOutputStream zos = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String destZipFile = null;
        try {
            boolean fileStoreFlag = isFtpStore();
            if (fileList != null && fileList.size() != 0) {
                destZipFile = getRealPath(ParamHolder.getParameter("DOC_FILE_PATH_TEMP") + File.separator + Tool.DATE.getDate() + File.separator + attachId + ".zip");
                fos = new FileOutputStream(destZipFile);
                zos = new ZipOutputStream(fos);
                for (DocBean docbean : fileList) {
                    File f = null;
                    // 如果文件存放在远程，则先取下文件到本地临时目录
                    if (fileStoreFlag) {
                        f = sGetServerFile(docbean.getStorePath());
                    } else {
                        f = new File(docbean.getStorePath());
                    }
                    if (f.exists() && f.isFile()) {
                        try {
                            fis = new FileInputStream(docbean.getStorePath());
                            zos.putNextEntry(new ZipEntry(new File(docbean.getStorePath()).getName()));
                            int b;
                            byte[] by = new byte[2048];
                            while ((b = fis.read(by)) != -1) {
                                zos.write(by, 0, b);
                            }
                        } catch (Exception e) {
                            throw e;
                        } finally {
                            f = null;
                            Tool.IO.closeQuietly(fis);
                        }
                    } else {
                        Throw.throwRuntimeException(ExceptionCodes.AP020408, docbean.getStorePath());
                    }
                }
                return new File(destZipFile);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Tool.IO.closeQuietly(zos, fos);
        }
        return null;
    }

    /**
     * 远程FTP拿取数据文件
     * 
     * @param fullFileName
     * @return
     */
    public File sGetServerFile(String fullFileName) {
        String tempDir = fullFileName;
        if (isFtpStore()) {
            // 本地临时目录
            tempDir = FilenameUtils.getFullPath(ParamHolder.getParameter("DOC_FILE_PATH_TEMP")) + fullFileName.replaceFirst(ParamHolder.getParameter("DOC_FILE_FTP_DIR"), "");
            File f = new File(FilenameUtils.getFullPathNoEndSeparator(tempDir));
            if (!f.exists()) {
                f.mkdirs();
            }
            sGetFileFromFtp(fullFileName, tempDir);
        }
        return new File(tempDir);
    }

    /**
     * @param fullFileName
     * @param tempDir
     */
    public void sGetFileFromFtp(String fullFileName, String tempDir) {
        if ("1".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            Tool.FTP.getFile(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), getRealPath(FilenameUtils.getFullPath(fullFileName)), // FTP服务器文件目录
                    FilenameUtils.getName(fullFileName), // FTP服务器文件名称
                    FilenameUtils.getFullPath(tempDir), // 本地目录
                    FilenameUtils.getName(fullFileName));// 本地文件名称
        } else if ("2".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            Tool.FTP.getSftpFile(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), getRealPath(FilenameUtils.getFullPath(fullFileName)), // FTP服务器文件目录
                    FilenameUtils.getName(fullFileName), // FTP服务器文件名称
                    FilenameUtils.getFullPath(tempDir), // 本地目录
                    FilenameUtils.getName(fullFileName));// 本地文件名称
        } else {
            Throw.throwRuntimeException(ExceptionCodes.AP020409);
        }
    }

    /**
     * 删除FTP上的文档
     * 
     * @param fullFileName
     */
    public void sRemoveFileInFtp(String fullFileName) {
        if ("1".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            Tool.FTP.removeFtpFile(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), fullFileName);
        } else if ("2".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            Tool.FTP.removeSftpFile(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), fullFileName);
        } else {
            Throw.throwRuntimeException(ExceptionCodes.AP020409);
        }
    }

    /**
     * 将文件上传到远程FTP
     * 
     * @param srcFile
     * @param destFile
     */
    public void sPutFileToRemote(String srcFile, String destFile) {
        sSendFile2Ftp(srcFile, destFile);
        Tool.FILE.forceDelete(new File(srcFile));
    }

    /**
     * 文件发送到FTP
     * 
     * @param srcFile
     * @param destFile
     */
    public void sSendFile2Ftp(String srcFile, String destFile) {
        if ("1".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            Tool.FTP.sendFile(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), destFile, srcFile, // 本地文件全路径
                    FilenameUtils.getName(srcFile));
        } else if ("2".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            Tool.FTP.sendSftpFile(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), destFile, srcFile, // 本地文件全路径
                    FilenameUtils.getName(srcFile));
        } else {
            Throw.throwRuntimeException(ExceptionCodes.AP020409);
        }
    }

    /**
     * @param fileDir
     * @return
     */
    public String getRealPath(String fileDir) {
        if (Tool.CHECK.isEmpty(fileDir)) {
            return "";
        }
        char endChar = fileDir.charAt(fileDir.length() - 1);
        if (endChar != '/' && endChar != '\\') {
            fileDir = fileDir + File.separator;
        } else if (endChar == '\\') {
            fileDir = fileDir.replace("\\", File.separator);
        }
        fileDir = fileDir.replace("/", File.separator);
        fileDir = fileDir.replace("\\", "/");
        // 增加生成目录的功能
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        return fileDir;
    }

    /**
     * 判断是否是FTP上存放文件
     * 
     * @return
     */
    public boolean isFtpStore() {
        String storeFileFlag = ParamHolder.getParameter("DOC_STORE_FLAG");
        return "2".equals(storeFileFlag) ? true : false;
    }

    /**
     * 文件复制处理
     * 
     * @param srcFile
     * @param destFile
     * @throws Exception
     */
    public void sCopyFile(String srcFile, String destFile) throws Exception {
        if (isFtpStore()) {
            // 如果是远程，则送文件到远程FTP
            String relative = destFile.replaceFirst(getRealPath(ParamHolder.getParameter("DOC_FILE_PATH")), "");
            sPutFileToRemote(srcFile, FilenameUtils.getFullPath(relative));
        } else {
            // 如果是本地文件存储，则放入对应的数据目录
            localCope(srcFile, destFile);
        }
        Tool.FILE.forceDelete(new File(srcFile));
    }

    /**
     * 将文件从临时目录，移至正式目录
     * 
     * @param srcFile 临时目录
     * @param destFile 文件存储目录
     * @throws Exception
     */
    private void localCope(String srcFile, String destFile) throws Exception {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(srcFile);
            // 如果目录不存在，则建立目录
            File f = new File(getRealPath(FilenameUtils.getFullPath(destFile)), FilenameUtils.getName(destFile));
            if (!f.exists()) {
                f.createNewFile();
            }
            fout = new FileOutputStream(f);
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = fin.read(buffer, 0, buffer.length)) != -1) {
                fout.write(buffer, 0, len);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Tool.IO.closeQuietly(fin, fout);
        }
    }
}
