package com.forms.beneform4j.core.util.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 根据Ini配置读取XSD的实体解析器，也可手工添加命名空间和XSD文件对应的Resource的对应关系<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class IniConfigEntityResolver implements EntityResolver {

    private final Map<String, Resource> resources = new HashMap<String, Resource>();

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (CoreUtils.isBlank(systemId)) {
            return null;
        }

        Resource resource = resources.get(systemId);
        if (null != resource) {
            return resolveInputSource(publicId, systemId, resource);
        } else {
            String resourceLocation = XmlHelper.getNamespaceSchema(systemId);
            if (resourceLocation != null) {
                return resolveInputSource(publicId, systemId, new ClassPathResource(resourceLocation));
            }
        }
        return null;
    }

    private InputSource resolveInputSource(String publicId, String systemId, Resource resource) throws IOException {
        InputSource source = new InputSource(resource.getInputStream());
        source.setPublicId(publicId);
        source.setSystemId(systemId);
        return source;
    }

    /**
     * 添加解析器路径
     * 
     * @param systemId
     * @param location
     */
    public void addResource(String systemId, String location) {
        if (null != location && null != systemId) {
            try {
                Resource resource = CoreUtils.getResource(location);
                if (null != resource && resource.exists()) {
                    this.resources.put(systemId, resource);
                }
            } catch (Exception e) {
                Throw.throwRuntimeException(e);
            }
        }
    }

    public void setResources(Map<String, Resource> resources) {
        if (null != resources) {
            this.resources.putAll(resources);
        }
    }
}
