package com.forms.beneform4j.webapp.systemmanage.document.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文档管理服务类接口<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016年5月18日<br>
 */
public interface IDocumentService {

    /**
     * 列表
     * 
     * @param docForm
     * @param page
     * @return
     */
    List<DocBean> sList(DocForm docForm, IPage page);

    /**
     * 查找单个文档
     * 
     * @param docId
     * @return
     */
    DocBean sFind(String docId);

    /**
     * 锁定文档
     * 
     * @param docForm
     * @return
     */
    int sLock(DocForm docForm);

    /**
     * 删除
     * 
     * @param docIds
     * @return
     * @throws Exception
     */
    int sDelete(String[] docIds) throws Exception;

    /**
     * 新增
     * 
     * @param docForm
     * @return
     * @throws Exception
     */
    int sInsert(DocForm docForm) throws Exception;

    /**
     * 更新
     * 
     * @param docForm
     * @return
     */
    int sUpdate(DocForm docForm);

}
