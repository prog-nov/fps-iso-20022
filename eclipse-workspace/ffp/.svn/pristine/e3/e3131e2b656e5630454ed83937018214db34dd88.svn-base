package com.forms.beneform4j.webapp.systemmanage.menulocale.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfLocaleBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfMenuBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfMenuAllFrom;
import com.forms.beneform4j.webapp.systemmanage.menulocale.service.IMenuLocaleService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 国际化<br>
 * Author : xgp <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/menulocale")
public class MenuLocaleController {

    @Resource(name = "menuLocaleService")
    private IMenuLocaleService service;

    /**
     * 列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("search")
    public String list(HttpServletRequest request) {
        List<BfLocaleBean> list = service.sListLocaleBean();
        request.setAttribute("locales", list);
        return "system/sysmanager/menulocale/list";
    }

    /**
     * 查询菜单列表
     * 
     * @param menuId
     * @return
     */
    @RequestMapping("list")
    @ListJsonBody
    public List<BfMenuBean> list(@RequestParam(name = "menuId", defaultValue = "0") int menuId) {
        return service.sLoadChildren(menuId);
    }

    /**
     * 保存
     * 
     * @param form
     * @return
     */
    @RequestMapping("save")
    @JsonBody
    public int save(BfMenuAllFrom form) {
        return service.sSaveLocaleMenu(form);
    }

   
}
