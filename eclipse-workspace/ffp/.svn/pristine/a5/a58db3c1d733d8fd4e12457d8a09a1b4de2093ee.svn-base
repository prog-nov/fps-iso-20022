package com.forms.beneform4j.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.path.IPathResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 路径解析标签<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
public class PathTag extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 8150237386260219530L;

    private String url; // 支持{root}、{locale}、{theme}、{debug}、{min}五个变量

    public int doStartTag() throws JspException {
        JspWriter out = null;
        try {
            out = pageContext.getOut();
            IPathResolver pathResolver = WebBeneform4jConfig.getPathResolver();
            if (null != pathResolver && null != url) {
                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                out.print(pathResolver.resolver(request, url));
            } else {
                out.print(url);
            }
        } catch (Exception e) {
            throw new JspException(e);
        }
        return super.doStartTag();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
