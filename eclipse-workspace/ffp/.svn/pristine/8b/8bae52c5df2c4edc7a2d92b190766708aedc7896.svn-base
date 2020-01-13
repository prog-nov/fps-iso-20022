package com.forms.beneform4j.webapp.systemmanage.param.enumparam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamDataBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamDefBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamTreeBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamDataForm;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamDefForm;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamTreeForm;
import com.forms.beneform4j.webapp.systemmanage.param.enums.service.IEnumManagerService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数维护控制器<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-8<br>
 */
@Controller
@RequestMapping("system/sysmanager/param/enum")
public class EnumParamController {

    @Resource(name = "enumManagerService")
    private IEnumManagerService service;

    /**
     * 枚举参数列表
     * 
     * @param form
     * @param page
     * @return
     */
    @RequestMapping("listEnumDef")
    @PageJsonBody
    public List<EnumParamDefBean> listEnumDef(EnumParamDefForm form, IPage page) {
        return service.sListParamDef(form, page);
    }

    /**
     * 枚举参数数据列表
     * 
     * @param form
     * @return
     */
    @RequestMapping("listEnumData")
    @ListJsonBody
    public List<EnumParamDataBean> listEnumData(EnumParamDataForm form) {
        return service.sListParamData(form);
    }

    /**
     * 枚举参数树形列表
     * 
     * @param form
     * @return
     */
    @RequestMapping("listEnumTree")
    @ListJsonBody
    public List<EnumParamTreeBean> listEnumData(EnumParamTreeForm form) {
        return service.sListParamTree(form);
    }

    /**
     * 新增
     * 
     * @param form
     * @return
     */
    @RequestMapping("addEnumDef")
    @JsonBody
    public int insertEnumDef(EnumParamDefForm form) {
        return service.sInsertParamDef(form);
    }

    /**
     * 更新定义
     * 
     * @param form
     * @return
     */
    @RequestMapping("editEnumDef")
    @JsonBody
    public int updateEnumDef(EnumParamDefForm form) {
        return service.sUpdateParamDef(form);
    }

    /**
     * 更新枚举数据
     * 
     * @param paramCode
     * @param dataCode
     * @param dataText
     * @param dataParam
     * @param seqno
     * @param des
     * @return
     */
    @RequestMapping("editListData")
    @JsonBody
    public int updateListData(@RequestParam(name = "paramCode[]") String[] paramCode, @RequestParam(name = "dataCode[]") String[] dataCode, @RequestParam(name = "dataText[]") String[] dataText, @RequestParam(name = "dataParam[]") String[] dataParam, @RequestParam(name = "seqno[]") String[] seqno, @RequestParam(name = "des[]") String[] des) {
        List<EnumParamDataForm> list = new ArrayList<EnumParamDataForm>();
        for (int i = 0; i < paramCode.length; i++) {
            EnumParamDataForm form = new EnumParamDataForm();
            form.setParamCode(paramCode[i]);
            form.setDataCode(dataCode[i]);
            form.setDataText(dataText[i]);
            form.setDataParam(dataParam[i]);
            form.setSeqno(Integer.parseInt(seqno[i]));
            form.setDes(des[i]);
            list.add(form);
        }
        return service.sUpdateListData(list);
    }

    /**
     * 新增树形枚举数据
     * 
     * @param form
     * @return
     */
    @RequestMapping("addTreeNode")
    @JsonBody
    public int insertTreeNode(EnumParamTreeForm form) {
        return service.sInsertTreeNode(form);
    }

    /**
     * 查找节点
     * 
     * @param form
     * @return
     */
    @RequestMapping("findTreeNode")
    @JsonBody
    public EnumParamTreeBean findTreeNode(EnumParamTreeForm form) {
        return service.sFindTreeNode(form);
    }

    /**
     * 移动树形节点
     * 
     * @param form
     * @return
     */
    @RequestMapping("moveTreeNode")
    @JsonBody
    public int moveTreeNode(EnumParamTreeForm form) {
        return service.sMoveTreeNode(form);
    }

    /**
     * 更新树形节点
     * 
     * @param form
     * @return
     */
    @RequestMapping("editTreeNode")
    @JsonBody
    public int updateTreeNode(EnumParamTreeForm form) {
        return service.sUpdateTreeNode(form);
    }

    /**
     * 删除树形节点
     * 
     * @param form
     * @return
     */
    @RequestMapping("deleteTreeNode")
    @JsonBody
    public int deleteTreeNode(EnumParamTreeForm form) {
        return service.sDeleteTreeNode(form);
    }

    /**
     * 删除定义
     * 
     * @param paramCodes
     * @return
     */
    @RequestMapping("deleteEnumDef")
    @JsonBody
    public int deleteEnumDef(@RequestParam(name = "paramCode[]") String[] paramCodes) {
        return service.sDeleteParamDef(paramCodes);
    }

}
