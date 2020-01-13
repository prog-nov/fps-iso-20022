package com.forms.beneform4j.webapp.systemmanage.document.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.docmanager.bean.AttachBean;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;
import com.forms.beneform4j.webapp.common.docmanager.service.IDocManagerService;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.document.dao.IDocDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文件附件管理服务类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
@Service("documentService")
@Scope("prototype")
public class DocumentService implements IDocumentService {

    @Resource(name = "docDao")
    private IDocDao docDao;

    @Resource(name = "docManagerService")
    IDocManagerService docManagerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocBean> sList(DocForm docForm, IPage page) {
        return docDao.dList(docForm, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocBean sFind(String docId) {
        return docDao.dFind(docId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sLock(DocForm docForm) {
        return docDao.dLock(docForm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sDelete(String[] docIds) throws Exception {
        int sum = 0;
        for (String doc : docIds) {
            int i = docManagerService.sDeleteFile(doc);
            sum = sum + i;
        }
        return sum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int sInsert(DocForm docForm) throws Exception {
        List<AttachBean> attachBeans = docManagerService.sUploadFile(docForm);
        return attachBeans.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int sUpdate(DocForm docForm) {
        DocBean bean = null;
        int rtn = 0;
        try {
            bean = docDao.dFind(docForm.getDocId());
            if (Tool.CHECK.isEmpty(bean)) {
                Throw.throwRuntimeException(ExceptionCodes.DATA_NOT_FOUND, docForm.getDocName());
            }
            // 证明有换新的文件，需要对文件更新文件，并删除旧的文件
            if (!Tool.CHECK.isEmpty(docForm.getFileKeys()) && docForm.getFileKeys().length > 0) {

                // 删除原有文件
                docManagerService.sDeleteFile(docForm.getDocId());

                // 新增加新的数据文件
                docManagerService.sUploadFile(docForm);
            }
            bean.setDocType(docForm.getDocType());
            bean.setDocState(docForm.getDocState());
            rtn = docDao.dUpdate(bean);
            return rtn;
        } catch (Exception e) {
            Throw.throwRuntimeException(e);
        } finally {
        }
        return rtn;
    }
}
