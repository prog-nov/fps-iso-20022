package com.forms.beneform4j.security.core.common.pathmatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 路径匹配支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-10<br>
 */
public class PathMatcherSupport {

    private PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 匹配的URL模式
     */
    private List<String> urlPatterns;

    /**
     * URL模式配置为空时是否匹配
     */
    private boolean matcherWhenUrlPatternsIsEmpty;

    /**
     * 获取匹配的模式字符串
     * 
     * @param url 目标URL
     * @return 匹配的模式字符串
     */
    protected String getMatcherPattern(String url) {
        List<String> urlPatterns = getUrlPatterns();
        if (null != urlPatterns && !urlPatterns.isEmpty()) {
            List<String> matchingPatterns = new ArrayList<String>();
            for (String pattern : urlPatterns) {
                if (pathMatcher.match(pattern, url)) {
                    matchingPatterns.add(pattern);
                }
            }
            if (!matchingPatterns.isEmpty()) {
                Comparator<String> patternComparator = pathMatcher.getPatternComparator(url);
                Collections.sort(matchingPatterns, patternComparator);
                String pattern = matchingPatterns.get(0);
                return pattern;
            }
        }
        return null;
    }

    /**
     * 是否匹配
     * 
     * @param url 目标URL
     * @return 是否匹配
     */
    protected boolean isMatcher(String url) {
        List<String> urlPatterns = getUrlPatterns();
        if (null != urlPatterns && !urlPatterns.isEmpty()) {
            for (String pattern : urlPatterns) {
                if (pathMatcher.match(pattern, url)) {
                    return true;
                }
            }
            return false;
        } else {
            return this.isMatcherWhenUrlPatternsIsEmpty();
        }
    }

    /**
     * 提取路径变量
     * 
     * @param pattern 路径模式
     * @param url 目标URL
     * @return 路径变量Map
     */
    protected Map<String, String> extractUriTemplateVariables(String pattern, String url) {
        return pathMatcher.extractUriTemplateVariables(pattern, url);
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(List<String> urlPatterns) {
        if (null != urlPatterns && !urlPatterns.isEmpty()) {
            for (String urlPattern : urlPatterns) {
                setUrlPattern(urlPattern);
            }
        }
    }

    public void setUrlPattern(String urlPattern) {
        if (null != urlPattern) {
            if (null == this.urlPatterns) {
                this.urlPatterns = new ArrayList<String>();
            }
            this.urlPatterns.addAll(Arrays.asList(urlPattern.split("\\s*,\\s*")));
        }
    }

    public boolean isMatcherWhenUrlPatternsIsEmpty() {
        return matcherWhenUrlPatternsIsEmpty;
    }

    public void setMatcherWhenUrlPatternsIsEmpty(boolean matcherWhenUrlPatternsIsEmpty) {
        this.matcherWhenUrlPatternsIsEmpty = matcherWhenUrlPatternsIsEmpty;
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
}
