package com.forms.beneform4j.util.toolimpl;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文件工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class FileUtilsImpl {

    private static final FileUtilsImpl instance = new FileUtilsImpl() {};

    private FileUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static FileUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 强制删除文件
     * 
     * @param file 不定参数，可以传入文件数组，或不定参数文件
     */
    public void forceDelete(File... file) {
        if (null != file) {
            for (File f : file) {
                try {
                    FileUtils.forceDelete(f);
                } catch (Exception i) {
                }
            }
        }
    }

    /**
     * 循环删除文件/文件夹
     * 
     * @param root 根目录
     * @return 删除是否成功
     */
    public boolean deleteRecursively(File root) {
        if (root != null && root.exists()) {
            if (root.isDirectory()) {
                File[] children = root.listFiles();
                if (children != null) {
                    for (File child : children) {
                        deleteRecursively(child);
                    }
                }
            }
            return root.delete();
        }
        return false;
    }

    /**
     * 强制创建目录
     * 
     * @param directory 目录
     */
    public void forceMkdir(File directory) {
        if (null != directory) {
            try {
                FileUtils.forceMkdir(directory);
            } catch (Exception i) {
            }
        }
    }

    /**
     * 在文件名上添加字符串，如：
     * <ul>
     * <li>"example.txt","add" => example-add.txt
     * <li>"example","add" => example-add
     * </ul>
     * 
     * @param name 文件名
     * @param append 需要附加的字符串
     * @return 修改后的文件名
     */
    public String getAppendFilename(String name, String append) {
        String extension = FilenameUtils.getExtension(name);
        if (Tool.CHECK.isBlank(extension)) {
            return name + "-" + append;
        } else {
            return FilenameUtils.removeExtension(name) + "-" + append + "." + extension;
        }
    }

    /**
     * 替换文件后缀，如：
     * <ul>
     * <li>"example.txt","add" => example.add
     * <li>"example","add" => example.add
     * </ul>
     * 
     * @param name 文件名
     * @param extension 新后缀
     * @return 修改后的文件名
     */
    public String replaceExtension(String name, String extension) {
        return FilenameUtils.removeExtension(name) + "." + extension;
    }

    /**
     * 重命名文件的回调接口
     */
    public static interface IRenameFileCallback {
        /**
         * 执行回调
         * 
         * @param newFile 重命名后的文件
         */
        public void callback(File newFile);
    }

    /**
     * 重命名文件，在回调结束后又恢复
     * 
     * @param file 文件
     * @param callback 重命名文件的回调
     */
    public void renameFileThenRecover(File file, IRenameFileCallback callback) {
        renameFileThenRecover(file, getAppendFilename(file.getPath(), "" + System.nanoTime()), callback);
    }

    /**
     * 重命名文件，在回调结束后又恢复
     * 
     * @param file 文件
     * @param newFilename 重命名的新文件名
     * @param callback 重命名文件的回调
     */
    public void renameFileThenRecover(File file, String newFilename, IRenameFileCallback callback) {
        if (null != file) {
            File tmp = new File(newFilename);
            try {
                file.renameTo(tmp);
                callback.callback(tmp);
            } finally {
                tmp.renameTo(file);
            }
        }
    }

    /**
     * 扫描包路径下的文件
     * 
     * @param basePackage 扫描基本包
     * @param filter 文件过滤器
     * @return 文件集合
     */
    public Set<File> scanFiles(String basePackage, FileFilter filter) {
        return BaseConfig.getScan().scanFiles(basePackage, filter);
    }
}
