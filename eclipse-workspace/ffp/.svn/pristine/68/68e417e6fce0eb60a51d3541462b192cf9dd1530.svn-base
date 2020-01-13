package com.forms.beneform4j.util.toolimpl;

import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 操作系统相关工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class OSUtilsImpl {

    private static final OSUtilsImpl instance = new OSUtilsImpl() {};

    private OSUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static OSUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 获取系统属性
     * 
     * @param property 属性名
     * @return 属性值
     */
    public String getSystemProperty(String property) {
        try {
            return System.getProperty(property);
        } catch (SecurityException ex) {
            System.err.println("Caught a SecurityException reading the system property '" + property + "'; the OSToolImpl property value will default to null.");
            return null;
        }
    }

    /**
     * 运行操作系统命令
     * 
     * @param cmd 命令
     * @return 命令执行结果
     */
    public int runOSCommond(String... cmd) {
        return CoreUtils.runOSCommand(cmd);
    }

    /**
     * 调用操作系统命令打开文件
     * 
     * @param file 文件
     */
    public void openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                Throw.throwRuntimeException(e);
            }
        }
    }

    /**
     * 调用操作系统命令编辑文件
     * 
     * @param file 文件
     */
    public void editFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().edit(file);
            } catch (IOException e) {
                Throw.throwRuntimeException(e);
            }
        }
    }

    /**
     * 调用操作系统命令打开uri浏览地址
     * 
     * @param uri uri地址
     */
    public void browser(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);;
            } catch (IOException e) {
                Throw.throwRuntimeException(e);
            }
        }
    }

    /**
     * 复制内容到键盘
     * 
     * @param str 字符串内容
     */
    public void copyToClipboard(String str) {
        StringSelection copyItem = new StringSelection(str);
        Clipboard clipboard = getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(copyItem, null);
    }

    /**
     * 从键盘获取复制内容
     * 
     * @return 字符串复制内容
     */
    public String getStringFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable paste = clipboard.getContents(null);
        if (paste == null) {
            return null;
        }
        try {
            return (String) paste.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 检查指定类的进程是否在运行中
     * 
     * @param cls 类对象
     * @return 是否在运行
     * @throws Exception
     */
    public boolean checkClassInProcess(Class<?> cls) throws Exception {
        BufferedReader br = null;
        try {
            BufferedReader b = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("jps -l").getInputStream()));
            String line = null;
            while ((line = b.readLine()) != null) {
                if (line.indexOf(cls.getName()) >= 0) {
                    return true;
                }
            }
            return false;
        } finally {
            Tool.IO.closeQuietly(br);
        }
    }

    /**
     * 获取指定类的进程的运行数
     * 
     * @param cls 类对象
     * @return 相关进程运行数量
     * @throws Exception
     */
    public int getClassInProcessNum(Class<?> cls) throws Exception {
        BufferedReader br = null;
        try {
            int i = 0;
            BufferedReader b = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("jps -l").getInputStream()));
            String line = null;
            while ((line = b.readLine()) != null) {
                if (line.indexOf(cls.getName()) >= 0) {
                    i++;
                }
            }
            return i;
        } finally {
            Tool.IO.closeQuietly(br);
        }
    }
}
