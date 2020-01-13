package com.forms.beneform4j.web.ajax;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Ajax调用Servlet<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
public class AjaxServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = -8677953628341904794L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            Object result = AjaxInvoker.invokedAjaxMethod(req, resp);
            Tool.JSON.getSingleonObjectMapper().writeValue(out, result);
        } finally {
            Tool.IO.closeQuietly(out);
        }
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        AjaxInvoker.initAjaxMethod();
    }

    public void destroy() {
        AjaxInvoker.clear();
        super.destroy();
    }
}
