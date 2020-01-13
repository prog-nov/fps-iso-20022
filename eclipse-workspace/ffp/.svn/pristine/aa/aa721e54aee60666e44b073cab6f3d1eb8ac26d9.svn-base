package com.forms.beneform4j.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Ajax调用控制器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
@Controller
public class AjaxController implements InitializingBean, DisposableBean {

    @ResponseBody
    @RequestMapping("/ajax.AjaxServlet")
    public Object doHandlerAjaxRequest(HttpServletRequest req, HttpServletResponse resp) {
        return AjaxInvoker.invokedAjaxMethod(req, resp);
    }

    @Override
    public void destroy() throws Exception {
        AjaxInvoker.clear();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AjaxInvoker.initAjaxMethod();
    }
}
