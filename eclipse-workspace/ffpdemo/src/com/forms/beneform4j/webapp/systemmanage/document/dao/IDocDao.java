package com.forms.beneform4j.webapp.systemmanage.document.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 文档处理数据库操作类 <br>
 * Description : BF_DOC <br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18 <br>
 */
@Repository("docDao")
@Scope("prototype")
public interface IDocDao {

    /**
     * 删除
     * 
     * @param docIds
     * @return
     */
    int[] dDelete(@BatchParam(item = "docId") String[] docIds);

    /**
     * 插入
     * 
     * @param docForm
     * @return
     */
    int dInsert(@Param("doc") DocForm docForm);

    /**
     * 列表
     * 
     * @param docForm
     * @param page
     * @return
     */
    List<DocBean> dList(@Param("doc") DocForm docForm, IPage page);

    /**
     * 查找
     * 
     * @param docId
     * @return
     */
    DocBean dFind(@Param("docId") String docId);

    /**
     * 查找Md5
     * 
     * @param md5
     * @return
     */
    DocBean dFindMd5(@Param("md5") String md5);

    /**
     * 更新
     * 
     * @param bean
     * @return
     */
    int dUpdate(@Param("doc") DocBean bean);

    /**
     * 锁定
     * 
     * @param docForm
     * @return
     */
    int dLock(@Param("doc") DocForm docForm);
}
