package com.forms.beneform4j.util.toolimpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateModelException;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 模板工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class TemplateUtilsImpl {

    private static final TemplateUtilsImpl instance = new TemplateUtilsImpl() {};

    private TemplateUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static TemplateUtilsImpl getInstance() {
        return instance;
    }

    /**
     * FreeMarker模板配置，模板配置单一实例
     */
    private static final Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    static {
        config.setLocalizedLookup(false);// 关闭国际化模板查找
    }

    /**
     * 获取模板配置
     * 
     * @return Freemarker模板配置
     */
    public Configuration getTemplateConfiguration() {
        return config;
    }

    /**
     * 设置模板共享变量
     * 
     * @param sharedVariables 变量
     */
    public void setSharedVariable(Map<String, Object> sharedVariables) {
        if (null != sharedVariables && !sharedVariables.isEmpty()) {
            for (Map.Entry<String, Object> entry : sharedVariables.entrySet()) {
                try {
                    config.setSharedVariable(entry.getKey(), entry.getValue());
                } catch (TemplateModelException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 填充字符串模板，返回字符串
     * 
     * @param ftl 字符串模板
     * @param data 模型数据
     * @return 填充后的字符串
     */
    public String fillStringFtl2String(String ftl, Object data) {
        try {
            String ftlName = "tmp";
            StringTemplateLoader tl = new StringTemplateLoader();
            tl.putTemplate(ftlName, ftl);
            config.setTemplateLoader(tl);
            Template template = config.getTemplate(ftlName);
            StringWriter result = new StringWriter();
            template.process(data, result);
            return result.toString();
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 填充文件模板，返回字符串
     * 
     * @param ftlPath 模板文件路径
     * @param data 填充数据
     * @return 填充后的字符串
     */
    public String fillFileFtl2String(String ftlPath, Object data) {
        return fillFileFtl2String(ftlPath, data, getTemplateEncoding());
    }

    /**
     * 填充文件模板
     * 
     * @param ftlPath 模板文件路径
     * @param data 填充数据
     * @param encoding 编码
     * @return 填充后的字符串
     */
    public String fillFileFtl2String(String ftlPath, Object data, String encoding) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            fillFileFtl2OutputStream(ftlPath, data, out, encoding, encoding);
            return out.toString(encoding);
        } catch (UnsupportedEncodingException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(out);
        }
    }

    /**
     * 填充文件模板，生成新文件
     * 
     * @param ftlPath 模板文件路径
     * @param data 填充数据
     * @param targetPath 目标文件
     */
    public void fillFileFtl2File(String ftlPath, Object data, String targetPath) {
        fillFileFtl2File(ftlPath, data, targetPath, getTemplateEncoding());
    }

    /**
     * 填充文件模板，生成新文件
     * 
     * @param ftlPath 模板文件路径
     * @param data 填充数据
     * @param targetPath 目标文件
     * @param encoding 编码
     */
    public void fillFileFtl2File(String ftlPath, Object data, String targetPath, String encoding) {
        OutputStream out = null;
        try {
            File file = new File(targetPath);
            Tool.FILE.forceMkdir(file.getParentFile());
            out = new FileOutputStream(file);
            fillFileFtl2OutputStream(ftlPath, data, out, encoding, encoding);
        } catch (FileNotFoundException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(out);
        }
    }

    /**
     * 填充文件模板，并将结果写入输出流
     * 
     * @param ftlPath 模板文件路径
     * @param data 填充数据
     * @param out 输出流
     * @param ftlEncoding 模板文件编码
     * @param outputEncoding 模板输出编码
     */
    public void fillFileFtl2OutputStream(String ftlPath, Object data, OutputStream out, String ftlEncoding, String outputEncoding) {
        Writer writer = null;
        try {
            File ftl = new File(ftlPath);
            config.setDirectoryForTemplateLoading(ftl.getParentFile());
            Template template = config.getTemplate(ftl.getName(), ftlEncoding);
            writer = new OutputStreamWriter(out, outputEncoding);
            template.process(data, writer);
            out.flush();
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(writer);
        }
    }

    /**
     * 填充类路径下的模板文件，返回字符串
     * 
     * @param cls 模板文件路径
     * @param name 模板名称
     * @param data 填充数据
     * @param encoding 编码
     * @return 填充后的字符串
     */
    public String fillClasspathFtl2String(Class<?> cls, String name, Object data) {
        return fillClasspathFtl2String(cls, name, data, getTemplateEncoding());
    }

    /**
     * 填充类路径下的模板文件，返回字符串
     * 
     * @param cls 模板文件路径
     * @param name 模板名称
     * @param data 填充数据
     * @param encoding 编码
     * @return 填充后的字符串
     */
    public String fillClasspathFtl2String(Class<?> cls, String name, Object data, String encoding) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            fillClasspathFtl2OutputStream(cls, name, data, out, encoding, encoding);
            return out.toString(encoding);
        } catch (UnsupportedEncodingException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(out);
        }
    }

    /**
     * 填充类路径下的模板文件，并将结果写入输出流
     * 
     * @param cls 类
     * @param name 模板名称
     * @param data 模型数据
     * @param os 输出流
     * @param ftlEncoding 模板文件编码
     * @param outputEncoding 输出编码
     */
    public void fillClasspathFtl2OutputStream(Class<?> cls, String name, Object data, OutputStream os, String ftlEncoding, String outputEncoding) {
        Writer writer = null;
        try {
            config.setClassForTemplateLoading(cls, "");
            Template template = config.getTemplate(name, ftlEncoding);
            writer = new OutputStreamWriter(os, outputEncoding);
            template.process(data, writer);
            os.flush();
        } catch (Exception e) {
            Throw.throwRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(writer);
        }
    }

    /**
     * 填充模板对象，返回字符串
     * 
     * @param template 模板对象
     * @param model 模板数据
     * @return 填充后的字符串
     */
    public String processTemplateIntoString(Template template, Object model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        }
    }

    private String getTemplateEncoding() {
        String encoding = config.getDefaultEncoding();
        if (Tool.CHECK.isBlank(encoding)) {
            encoding = BaseConfig.getEncoding();
        }
        return encoding;
    }
}
