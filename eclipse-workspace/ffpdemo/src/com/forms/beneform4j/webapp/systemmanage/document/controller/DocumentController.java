package com.forms.beneform4j.webapp.systemmanage.document.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;
import com.forms.beneform4j.webapp.systemmanage.document.service.IDocumentService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文档管理控制类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
@Scope("request")
@Controller
@RequestMapping("system/sysmanager/document")
public class DocumentController {

    @Resource(name = "documentService")
    private IDocumentService service;

    /**
     * 列表
     * 
     * @param docForm
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<DocBean> list(DocForm docForm, IPage page) {
        return service.sList(docForm, page);
    }

    /**
     * 锁定
     * 
     * @param docForm
     * @return
     */
    @RequestMapping("lock")
    @JsonBody
    public int lock(DocForm docForm) {
        return service.sLock(docForm);
    }

    /**
     * 批量删除选择的文件
     * 
     * @param docIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @JsonBody
    public String toDelete(@RequestParam(name = "docIds[]") String[] docIds) throws Exception {
        int i = service.sDelete(docIds);
        return i > 0 ? Tool.LOCALE.getMessage("systemmanager.doc.delete.ok") : Tool.LOCALE.getMessage("systemmanager.doc.delete.error");
    }

    /**
     * 新增
     * 
     * @param docForm
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @JsonBody
    public int insert(DocForm docForm, @User String userId) throws Exception {
        docForm.setInstOper(userId);
        docForm.setInstDate(Tool.DATE.getDate());
        docForm.setInstTime(Tool.DATE.getTime());
        return service.sInsert(docForm);
    }

    /**
     * 更新
     * 
     * @param docForm
     * @param userId
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @JsonBody
    public int update(DocForm docForm, @User String userId) {
        docForm.setModiDate(Tool.DATE.getDate());
        docForm.setModiTime(Tool.DATE.getTime());
        docForm.setModiOper(userId);
        return service.sUpdate(docForm);
    }
}
