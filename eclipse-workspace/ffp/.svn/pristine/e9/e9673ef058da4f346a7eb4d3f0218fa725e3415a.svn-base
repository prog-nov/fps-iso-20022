package com.forms.beneform4j.webapp.common.kindeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.toolimpl.RemoteFileEntry;
import com.forms.beneform4j.webapp.common.docmanager.service.IDocManagerService;
import com.forms.beneform4j.webapp.common.docmanager.util.FileService;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.common.kindeditor.form.FileForm;
import com.forms.beneform4j.webapp.common.kindeditor.service.IKindeditorService;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 富文件数据下载服务类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-28<br>
 */
@Service("kindeditorService")
public class KindeditorService implements IKindeditorService {

    @Resource(name = "docManagerService")
    private IDocManagerService docManagerService;

    @Resource(name = "fileService")
    private FileService fileService;

    /**
     * 附件回显处理
     */
    @Override
    public void sOutputAttachedFile(File file, OutputStream write) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, write);
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != fileInputStream) {
                IOUtils.closeQuietly(fileInputStream);
            }
        }

    }

    /**
     * 附件上传处理
     */
    @Override
    public Map<String, Object> sFileUpload(String dir, String contextPath, List<?> items) {
        Map<String, Object> rtn = null;
        FileForm FileForm = iniFileUploadBean(dir, contextPath);
        if (!FileForm.getExtMap().containsKey(FileForm.getDirName())) {
            return sGetError(Tool.LOCALE.getMessage("systemmanager.doc.file.dir.error"));
        }
        Iterator<?> itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            String fileName = item.getName();
            if (!item.isFormField()) {
                // 检查文件大小
                if (item.getSize() > FileForm.getMaxSize()) {
                    rtn = sGetError(Tool.LOCALE.getMessage("systemmanager.doc.file.size.limit"));
                    break;
                }
                // 检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (!Arrays.<String>asList(FileForm.getExtMap().get(FileForm.getDirName()).split(",")).contains(fileExt)) {
                    rtn = sGetError(Tool.LOCALE.getMessage("systemmanager.doc.file.dir.error", FileForm.getExtMap().get(FileForm.getDirName())));
                    break;
                }
                String newFileName = Tool.STRING.getRandomNumeric(16) + "." + fileExt;
                try {
                    File uploadedFile = new File(fileService.getRealPath(FileForm.getSavePath()), newFileName);
                    item.write(uploadedFile);
                    // 存放远程的目录（目前是FTP）
                    docManagerService.sStoreFileRemoteForKindEditor(uploadedFile, FileForm.getRelativeDirPath());
                } catch (Exception e) {
                    rtn = sGetError(Tool.LOCALE.getMessage("systemmanager.doc.file.upload.error"));
                    break;
                }
                rtn = getSuccess(FileForm.getSaveUrl() + newFileName);
            }
        }
        return rtn;
    }

    /**
     * 附件管理处理
     */
    @Override
    public Object sFileManager(String dirName, String contextPath, String path, String order) {
        if (!Tool.CHECK.isEmpty(dirName)) {
            if (!Arrays.<String>asList(new String[] {"image", "flash", "media", "file"}).contains(dirName)) {
                return "Invalid Directory name.";
            }
        }
        // 不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            return "Access is not allowed.";
        }
        // 最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            return "Parameter is not valid.";
        }
        // 图片扩展名
        String[] fileTypes = new String[] {"gif", "jpg", "jpeg", "png", "bmp"};
        FileForm fileForm = iniFileManagerBean(dirName, contextPath, path);
        // 目录不存在或不是目录
        File currentPathFile = new File(fileForm.getCurrentPath());
        if (!currentPathFile.isDirectory()) {
            return "Directory does not exist.";
        }
        // 遍历目录取的文件信息
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        if (fileService.isFtpStore()) {
            List<RemoteFileEntry> listFiles = listFtpFiles(fileForm.getCurrentPath());
            for (RemoteFileEntry file : listFiles) {
                Hashtable<String, Object> hash = iniMap(fileTypes, file, fileForm);
                fileList.add(hash);
            }
        } else {
            File[] listFiles = null;
            listFiles = currentPathFile.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    Hashtable<String, Object> hash = iniMap(fileTypes, file);
                    fileList.add(hash);
                }
            }
        }
        sortList(order, fileList);
        return iniResult(fileForm, fileList);
    }

    private List<RemoteFileEntry> listFtpFiles(String currentPath) {
        List<RemoteFileEntry> listFtpFiles = null;
        if ("1".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            listFtpFiles = Tool.FTP.listFtpFiles(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), currentPath);
        } else if ("2".equals(ParamHolder.getParameter("DOC_FILE_FTP_TYPE"))) {
            listFtpFiles = Tool.FTP.listSftpFiles(ParamHolder.getParameter("DOC_FILE_FTP_IP"), ParamHolder.getParameter("DOC_FILE_FTP_PORT", Integer.class), ParamHolder.getParameter("DOC_FILE_FTP_USER"), ParamHolder.getParameter("DOC_FILE_FTP_PASSWORD"), currentPath);
        } else {
            Throw.throwRuntimeException(ExceptionCodes.AP020409);
        }
        return listFtpFiles;
    }

    /**
     * 初始化返回对象
     * 
     * @param FileForm
     * @param fileList
     * @return
     */
    private Object iniResult(FileForm FileForm, List<Map<String, Object>> fileList) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("moveup_dir_path", FileForm.getMoveupDirPath());
        result.put("current_dir_path", FileForm.getCurrentDirPath());
        result.put("current_url", FileForm.getCurrentUrl());
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        return result;
    }

    /**
     * 对list进行排序
     * 
     * @param order
     * @param fileList
     */
    private void sortList(String order, List<Map<String, Object>> fileList) {
        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
    }

    /**
     * 初始化一个map
     * 
     * @param fileTypes
     * @param file
     * @return
     */
    private Hashtable<String, Object> iniMap(String[] fileTypes, RemoteFileEntry file, FileForm fileForm) {
        Hashtable<String, Object> hash = new Hashtable<String, Object>();
        String fileName = file.getName();
        if (file.isDir()) {
            hash.put("is_dir", true);
            List<RemoteFileEntry> listFiles = listFtpFiles(fileForm.getCurrentPath() + file.getName());
            hash.put("has_file", (listFiles != null));
            hash.put("filesize", 0L);
            hash.put("is_photo", false);
            hash.put("filetype", "");
        } else if (file.isFile()) {
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            hash.put("is_dir", false);
            hash.put("has_file", false);
            hash.put("filesize", file.getSize());
            hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
            hash.put("filetype", fileExt);
            if (fileService.isFtpStore()) {
                File f = fileService.sGetServerFile(fileForm.getCurrentPath() + fileName);
                String currentDirPath = f.getAbsolutePath();
                currentDirPath = currentDirPath.substring(currentDirPath.indexOf("image") + 6, currentDirPath.length());
                // 20160704/
                fileForm.setCurrentDirPath(fileService.getRealPath(FilenameUtils.getFullPath(currentDirPath)));
                // current_url=/beneform4j-webapp4easyui/attached/image/20160704/
                fileForm.setCurrentUrl(fileForm.getCurrentUrl().replaceFirst(ParamHolder.getParameter("DOC_FILE_FTP_DIR"), "/attached/"));
            }
        }
        hash.put("filename", fileName);
        hash.put("datetime", file.getLastModifyTime());
        return hash;
    }

    /**
     * 初始化一个map
     * 
     * @param fileTypes
     * @param file
     * @return
     */
    private Hashtable<String, Object> iniMap(String[] fileTypes, File file) {
        Hashtable<String, Object> hash = new Hashtable<String, Object>();
        String fileName = file.getName();
        if (file.isDirectory()) {
            hash.put("is_dir", true);
            hash.put("has_file", (file.listFiles() != null));
            hash.put("filesize", 0L);
            hash.put("is_photo", false);
            hash.put("filetype", "");
        } else if (file.isFile()) {
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            hash.put("is_dir", false);
            hash.put("has_file", false);
            hash.put("filesize", file.length());
            hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
            hash.put("filetype", fileExt);
        }
        hash.put("filename", fileName);
        hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
        return hash;
    }

    /**
     * 初始化文件上传bean
     * 
     * @param dir
     * @param contextPath
     * @return
     */
    private FileForm iniFileUploadBean(String dir, String contextPath) {
        FileForm bean = new FileForm();
        bean.setDirName(Tool.CHECK.isEmpty(dir) ? "image" : dir);
        bean.setRelativeDirPath(fileService.getRealPath(bean.getDirName() + File.separator + Tool.DATE.getDate() + File.separator));
        bean.setSavePath(fileService.getRealPath(ParamHolder.getParameter("KINDEDIT_FILE_PATH") + bean.getRelativeDirPath()));
        bean.setMaxSize(ParamHolder.getParameter("KINDEDIT_FILE_LIMIT_SIZE", Integer.class) * 1024);
        bean.setSaveUrl(fileService.getRealPath(contextPath + ParamHolder.getParameter("KINDEDIT_FILE_PATH").substring(2) + bean.getRelativeDirPath()));
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        bean.setExtMap(extMap);
        File file = new File(bean.getSavePath());
        // 如果目录不存在，则需要建立目录
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        return bean;
    }

    /**
     * 初始化文件管理bean
     * 
     * @param dirName
     * @param contextPath
     * @param path
     * @return
     */
    private FileForm iniFileManagerBean(String dirName, String contextPath, String path) {
        FileForm bean = new FileForm();

        if (fileService.isFtpStore()) {
            bean.setRootPath(fileService.getRealPath(ParamHolder.getParameter("DOC_FILE_FTP_DIR") + dirName + File.separator));
            bean.setRootUrl(fileService.getRealPath(contextPath + File.separator + ParamHolder.getParameter("DOC_FILE_FTP_DIR") + File.separator + dirName + File.separator));
        } else {
            bean.setRootPath(fileService.getRealPath(ParamHolder.getParameter("KINDEDIT_FILE_PATH") + dirName + File.separator));
            bean.setRootUrl(fileService.getRealPath(contextPath + ParamHolder.getParameter("KINDEDIT_FILE_PATH").substring(ParamHolder.getParameter("KINDEDIT_FILE_PATH").indexOf("/")) + dirName + File.separator));
        }
        bean.setCurrentDirPath(fileService.getRealPath(path));
        bean.setCurrentPath(fileService.getRealPath(bean.getRootPath() + path));
        bean.setCurrentUrl(fileService.getRealPath(bean.getRootUrl() + path));
        if (!Tool.CHECK.isEmpty(path)) {
            String str = bean.getCurrentDirPath().substring(0, bean.getCurrentDirPath().length() - 1);
            if (str.endsWith("/")) {
                str = str.substring(0, str.length() - 1);
            }
            bean.setMoveupDirPath(str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "");
        }
        File file = new File(bean.getRootPath());
        // 如果目录不存在，则需要建立目录
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        return bean;
    }

    public Map<String, Object> sGetError(String errorMsg) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("error", 1);
        errorMap.put("message", errorMsg);
        return errorMap;
    }

    private Map<String, Object> getSuccess(String url) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("error", 0);
        errorMap.put("url", url);
        return errorMap;
    }

    private class NameComparator implements Comparator<Map<String, Object>> {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
            }
        }
    }
    private class SizeComparator implements Comparator<Map<String, Object>> {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                    return 1;
                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    private class TypeComparator implements Comparator<Map<String, Object>> {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
            }
        }
    }

    @Override
    public File sGetServerFile(String relativeDirPath, String fileName) {
        // return fileService.sGetServerFile(relativeDirPath + fileName);
        return docManagerService.sGetFileRemoteForKindEditor(relativeDirPath, fileName);
    }
}
