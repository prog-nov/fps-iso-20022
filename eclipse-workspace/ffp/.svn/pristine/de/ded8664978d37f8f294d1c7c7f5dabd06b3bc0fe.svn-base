package com.forms.beneform4j.webapp.systemmanage.param.singleparam.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.webapp.systemmanage.param.single.bean.SingleParamBean;
import com.forms.beneform4j.webapp.systemmanage.param.single.form.SingleParamForm;
import com.forms.beneform4j.webapp.systemmanage.param.single.service.IParamManagerService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数管理控制器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
@Controller
@Scope("request")
@RequestMapping("systemmanage/param/single")
public class SingleParamController {

    @Resource(name = "paramManagerService")
    private IParamManagerService service;

    /**
     * 进入平台参数值维护界面
     */
    @RequestMapping("gotoList")
    public ModelAndView gotoListParam() {
        ModelAndView mv = new ModelAndView("system/sysmanager/param/single/list");
        Map<String, List<SingleParamBean>> queryParamList = service.queryParamList();
        mv.addObject("data", queryParamList);
        return mv;
    }

    /**
     * 更新参数值
     * 
     * @param param
     * @return
     */
    @RequestMapping("updateParamValue")
    @JsonBody
    public int updateParamValue(@RequestParam(name = "paramCode[]") String[] paramCodes, @RequestParam(name = "paramValue[]") String[] paramValues) {
        return service.updateParamValue(paramCodes, paramValues);
    }

    /**
     * 下拉
     * 
     * @param form
     * @return
     */
    @RequestMapping("getSingleParamComboData")
    @JsonBody
    public List<SingleParamBean> getComboData(SingleParamForm form) {
        return service.dGetSingleParamComboData(form);
    }

}
