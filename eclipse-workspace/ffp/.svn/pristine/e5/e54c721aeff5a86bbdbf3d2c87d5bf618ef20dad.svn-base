package com.forms.beneform4j.util.toolimpl;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 输入输出工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class IoUtilsImpl {

    private static final IoUtilsImpl instance = new IoUtilsImpl() {};

    private IoUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static IoUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 关闭对象，忽略关闭中的异常
     * 
     * @param closeable 可变参数，可以传入关闭对象数组，或不定参数关闭对象
     */
    public void closeQuietly(Closeable... closeable) {
        CoreUtils.closeQuietly(closeable);
    }

    /**
     * 获取资源，将字符串描述转换为Spring中的资源对象
     * 
     * @param location 资源位置，支持classpath:格式
     * @return Spring中的资源接口
     */
    public Resource getResource(String location) {
        return getResourcePatternResolver().getResource(location);
    }

    /**
     * 获取符合模式的一组资源
     * 
     * @param locationPattern 资源模式，支持classpath*:格式
     * @return Spring中的资源接口数组
     */
    public Resource[] getResources(String locationPattern) {
        try {
            return getResourcePatternResolver().getResources(locationPattern);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 加载属性文件
     * 
     * @param location 属性文件路径
     * @return 属性对象
     */
    public Properties loadProperties(String location) {
        Properties properties = new Properties();
        fillProperties(properties, location);
        return properties;
    }

    /**
     * 填充属性对象
     * 
     * @param props 属性对象
     * @param location 属性文件路径
     */
    public void fillProperties(Properties props, String location) {
        InputStream is = null;
        try {
            Resource resource = getResource(location);
            is = resource.getInputStream();
            String filename = resource.getFilename();
            if (filename != null && filename.toLowerCase().endsWith(".xml")) {
                props.loadFromXML(is);
            } else {
                props.load(is);
            }
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            this.closeQuietly(is);;
        }
    }

    /**
     * 加载属性文件中前缀为prefix的属性
     * 
     * @param location 属性文件路径
     * @param prefix 属性前缀
     * @return 属性对象
     */
    public Properties loadProperties(String location, String prefix) {
        Properties properties = loadProperties(location);
        if (null == properties || properties.isEmpty()) {
            return properties;
        }
        Properties rs = new Properties();
        int index = prefix.length();
        for (Iterator<Object> i = properties.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            if (key.startsWith(prefix) && key.length() > index) {
                rs.put(key.substring(index + 1), properties.get(key));
            }
        }
        return rs;
    }

    /**
     * 跟据资源路径获取输入流
     * 
     * @param location 资源路径
     * @return 输入流对象
     */
    public InputStream getInputStream(String location) {
        try {
            return getResource(location).getInputStream();
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 根据类相对路径获取输入流
     * 
     * @param path 路径
     * @param cls 类
     * @return 输入流对象
     */
    public InputStream getInputStream(String path, Class<?> cls) {
        try {
            return new ClassPathResource(path, cls).getInputStream();
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 根据资源路径获取文件
     * 
     * @param resourceLocation 资源路径
     * @return 文件对象
     */
    public File getFile(String resourceLocation) {
        try {
            return ResourceUtils.getFile(resourceLocation);
        } catch (FileNotFoundException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 根据资源路径获取URL
     * 
     * @param resourceLocation 资源路径
     * @return URL对象
     */
    public URL getURL(String resourceLocation) {
        try {
            return ResourceUtils.getURL(resourceLocation);
        } catch (FileNotFoundException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 将输入流转换为按行的迭代器
     * 
     * @param input 输入流，按BaseConfig.getEncoding()编码，默认值为UTF-8
     * @return 行迭代器
     */
    public LineIterator lineIterator(InputStream input) {
        return lineIterator(input, BaseConfig.getEncoding());
    }

    /**
     * 将输入流转换为按行的迭代器
     * 
     * @param input 输入流
     * @param encoding 字符集
     * @return 行迭代器
     */
    public LineIterator lineIterator(InputStream input, String encoding) {
        try {
            return IOUtils.lineIterator(input, encoding);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 将输入流按行读至字符串列表中
     * 
     * @param input 输入流，按BaseConfig.getEncoding()编码，默认值为UTF-8
     * @return 字符串列表
     */
    public List<String> readLines(InputStream input) {
        return readLines(input, BaseConfig.getEncoding());
    }

    /**
     * 将输入流按行读至字符串列表中
     * 
     * @param input 输入流
     * @param encoding 字符集
     * @return 字符串列表
     */
    public List<String> readLines(InputStream input, String encoding) {
        try {
            return IOUtils.readLines(input, encoding);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 对象序列化至输出流中
     * 
     * @param obj 目标对象
     * @param outputStream 输出流
     */
    public void serialize(Serializable obj, OutputStream outputStream) {
        SerializationUtils.serialize(obj, outputStream);
    }

    /**
     * 对象序列化为字节数组
     * 
     * @param obj 目标对象
     * @return 字节数组
     */
    public byte[] serialize(Serializable obj) {
        return SerializationUtils.serialize(obj);
    }

    /**
     * 从输入流中反序列化为对象
     * 
     * @param inputStream 输入流
     * @return 反序列化后的对象
     */
    public Object deserialize(InputStream inputStream) {
        return SerializationUtils.deserialize(inputStream);
    }

    /**
     * 从字节数组中反序列化为对象
     * 
     * @param objectData 字节数组
     * @return 反序列化后的对象
     */
    public Object deserialize(byte[] objectData) {
        return SerializationUtils.deserialize(objectData);
    }

    /**
     * 流复制
     * 
     * @param input 输入
     * @param output 输出
     */
    public void copy(InputStream input, OutputStream output) {
        try {
            IOUtils.copy(input, output);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 流复制
     * 
     * @param input 输入
     * @param output 输出
     */
    public void copy(Reader input, Writer output) {
        try {
            IOUtils.copy(input, output);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    private ResourcePatternResolver getResourcePatternResolver() {
        return BaseConfig.getResourcePatternResolver();
    }
}
