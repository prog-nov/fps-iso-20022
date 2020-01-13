package com.forms.beneform4j.web.servlet.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.web.WebUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的跳过路径匹配的过滤器，匹配的路径不做相关的过滤逻辑<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class AbstractSkipPathMatcherFilter extends OncePerRequestFilter {

    private static final AntPathMatcher antMatcher = new AntPathMatcher();

    /**
     * 不需要执行该过滤器的请求URL
     */
    private List<String> excludePatterns;

    /**
     * 执行过滤
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        List<String> excludePatterns = getExcludePatterns();
        if (null != excludePatterns && !excludePatterns.isEmpty()) {
            String url = WebUtils.getUriWithoutRoot(request.getRequestURI());
            if (CoreUtils.isBlank(url)) {
                filterChain.doFilter(request, response);
                return;
            }
            for (String excludePattern : excludePatterns) {
                if (antMatcher.match(excludePattern, url)) {// 如果url匹配，跳过本过滤器
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        this.executeFilter(request, response, filterChain);
    }

    protected abstract void executeFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    /**
     * 初始化
     */
    @Override
    protected void initFilterBean() throws ServletException {
        FilterConfig config = super.getFilterConfig();
        if (null != config) {
            String excludePatterns = config.getInitParameter("excludePatterns");
            if (null != excludePatterns) {
                String[] eps = excludePatterns.split("\\s*,\\s*");
                if (null != eps) {
                    if (null == this.excludePatterns) {
                        this.excludePatterns = new ArrayList<String>();
                    }
                    for (String ep : eps) {
                        this.excludePatterns.add(ep);
                    }
                }
            }
        }
    }

    public List<String> getExcludePatterns() {
        return excludePatterns;
    }

    public void setExcludePatterns(List<String> excludePatterns) {
        if (null != excludePatterns && !excludePatterns.isEmpty()) {
            if (null == this.excludePatterns) {
                this.excludePatterns = new ArrayList<String>();
            }
            this.excludePatterns.addAll(excludePatterns);
        }
    }

    public void setExcludePattern(String excludePattern) {
        if (null != excludePattern) {
            if (null == this.excludePatterns) {
                this.excludePatterns = new ArrayList<String>();
            }
            this.excludePatterns.add(excludePattern);
        }
    }
}
