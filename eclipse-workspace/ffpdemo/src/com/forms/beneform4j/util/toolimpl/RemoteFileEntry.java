package com.forms.beneform4j.util.toolimpl;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 远程FTP文件实体BEAN<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-14<br>
 */
public class RemoteFileEntry {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件大小（字节）
     */
    private long size;

    /**
     * 是否文件 true/false
     */
    private boolean isFile;

    /**
     * 是否文件夹true/false
     */
    private boolean isDir;

    /**
     * 文件最后修改日期(时间戳)
     */
    private String lastModifyTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean isDir) {
        this.isDir = isDir;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}
