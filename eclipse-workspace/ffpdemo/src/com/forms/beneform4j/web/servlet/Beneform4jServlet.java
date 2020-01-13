package com.forms.beneform4j.web.servlet;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.init.InitManage;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.view.ViewMappingHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Beneform4j核心控制器，继承Spring MVC的Servlet<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class Beneform4jServlet extends DispatcherServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -1598541136448241235L;

    /**
     * 覆盖父类初始化方法
     */
    // @Override
    // protected WebApplicationContext initWebApplicationContext() {
    // WebApplicationContext context = super.initWebApplicationContext();
    // ServletHelp.setApplication(context.getServletContext());
    // InitManage.initialize();
    // init(context.getServletContext(), context);
    // return context;
    // }

    @Override
    protected void onRefresh(ApplicationContext context) {
        super.onRefresh(context);
        ServletHelp.setApplication(super.getServletContext());
        initRequestMappingHandlerAdapter(context);
        InitManage.initialize();
        init(super.getServletContext(), context);
    }

    private void initRequestMappingHandlerAdapter(ApplicationContext context) {
        try {
            List<HandlerMethodReturnValueHandler> returnValueHandlers = WebBeneform4jConfig.getPriorReturnValueHandlers();
            if (null != returnValueHandlers && !returnValueHandlers.isEmpty()) {
                RequestMappingHandlerAdapter adapter = context.getBean(RequestMappingHandlerAdapter.class.getName(), RequestMappingHandlerAdapter.class);
                Field returnValueHandlersField = RequestMappingHandlerAdapter.class.getDeclaredField("returnValueHandlers");
                returnValueHandlersField.setAccessible(true);
                HandlerMethodReturnValueHandlerComposite composite = (HandlerMethodReturnValueHandlerComposite) returnValueHandlersField.get(adapter);
                returnValueHandlersField = HandlerMethodReturnValueHandlerComposite.class.getDeclaredField("returnValueHandlers");
                returnValueHandlersField.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<HandlerMethodReturnValueHandler> origin = (List<HandlerMethodReturnValueHandler>) returnValueHandlersField.get(composite);
                origin.addAll(0, returnValueHandlers);
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 执行初始化，给子类覆盖
     * 
     * @param servletContext Servlet上下文
     * @param springApplicationContext Spring容器上下文
     */
    protected void init(ServletContext servletContext, ApplicationContext context) {

    }

    /**
     * 获取默认视图，覆盖父类方法
     */
    @Override
    protected String getDefaultViewName(HttpServletRequest request) throws Exception {
        IRequestInfo info = ServletHelp.getRequestInfo();
        String defaultView = ViewMappingHolder.getDefaultView(info);
        if (!CoreUtils.isBlank(defaultView)) {
            return defaultView;
        } else {
            return super.getDefaultViewName(request);
        }
    }

    /**
     * 销毁方法
     */
    @Override
    public void destroy() {
        try {
            InitManage.destory();
        } finally {
            super.destroy();
        }
    }
}
