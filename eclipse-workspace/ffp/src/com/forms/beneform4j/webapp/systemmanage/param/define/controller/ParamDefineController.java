package com.forms.beneform4j.webapp.systemmanage.param.define.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.systemmanage.param.define.bean.ParamDefineBean;
import com.forms.beneform4j.webapp.systemmanage.param.define.form.ParamDefineForm;
import com.forms.beneform4j.webapp.systemmanage.param.define.service.IParamDefineService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数定义<br>
 * Author : LiYun <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-23<br>
 */

@Controller
@RequestMapping("system/sysmanager/param/define")
public class ParamDefineController {

    @Autowired
    private IParamDefineService service;

    //    获取单值参数列表
    @RequestMapping("list")
    @PageJsonBody
    public List<ParamDefineBean> list(ParamDefineForm form, IPage page) {
        return service.sList(form, page);
    }

    @RequestMapping("find")
    @JsonBody
    public ParamDefineBean find(@RequestParam("paramCode") String paramCode) {
        return service.sFind(paramCode);
    }

    //  添加单值参数
    @RequestMapping("add")
    @JsonBody
    public int insert(ParamDefineForm form) {
        return service.sInsert(form);
    }
    
    //  修改单值参数
    @RequestMapping("edit")
    @JsonBody
    public int update(ParamDefineForm form) {
        return service.sUpdate(form);
    }
    
    
    //  删除单值参数
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam("paramCode[]") String[] paramCodes) {
        return service.sDelete(paramCodes);
    }
}
