package com.forms.beneform4j.web.springmvc.resourceresolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.path.IPathResolver;
import com.forms.beneform4j.web.servlet.ServletHelp;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 静态资源解析<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-3<br>
 */
public class StaticResourceResolver extends PathResourceResolver {

    final protected Resource getResource(String resourcePath, Resource location) throws IOException {
        return super.getResource(resolverResourcePath(resourcePath), location);
    }

    protected String resolverResourcePath(String resourcePath) {
        HttpServletRequest request = ServletHelp.getRequest();
        IPathResolver pathResolver = WebBeneform4jConfig.getPathResolver();
        if (null != request && null != pathResolver) {
            return pathResolver.resolver(request, resourcePath);
        }
        return resourcePath;
    }
}
