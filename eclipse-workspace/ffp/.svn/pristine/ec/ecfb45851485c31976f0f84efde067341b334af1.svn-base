package com.forms.beneform4j.web.springmvc.argumentresolver;

import org.apache.ibatis.session.RowBounds;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.forms.beneform4j.core.dao.mybatis.page.PageAdapter;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.core.util.page.PageUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 分页参数转换器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断是否为分页参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        return IPage.class.isAssignableFrom(paramType) || RowBounds.class.isAssignableFrom(paramType);
    }

    /**
     * 解析分页参数
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> paramType = parameter.getParameterType();
        IPage page = PageUtils.createPage();
        if (IPage.class.isAssignableFrom(paramType)) {
            return page;
        } else {
            PageAdapter pa = new PageAdapter(page);
            return pa;
        }
    }
}
