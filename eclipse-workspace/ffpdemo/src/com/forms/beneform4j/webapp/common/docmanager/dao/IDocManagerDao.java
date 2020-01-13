package com.forms.beneform4j.webapp.common.docmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.webapp.common.docmanager.bean.DocBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文档管理<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
@Repository("docManagerDao")
@Scope("prototype")
public interface IDocManagerDao {

    /**
     * 列表
     * 
     * @param params
     * @return
     */
    List<DocBean> dList(Map<String, Object> params);

    /**
     * 写入
     * 
     * @param docForm
     * @return
     */
    int dPlugInsert(@Param("doc") DocForm docForm);

    /**
     * 根据文档编号查找对应的数据bean
     * 
     * @param docId
     * @return
     */
    DocBean dFind(@Param("docId") String docId);

    /**
     * 根据MD5查找出对应的数据bean
     * 
     * @param docId
     * @return
     */
    DocBean dFindMid(@Param("docId") String docId);

    /**
     * 复制
     * 
     * @param docBean
     * @return
     */
    int dCopyDocRecode(@Param("doc") DocBean docBean);

    /**
     * 插入
     * 
     * @param docBean
     * @return
     */
    int dInsertAttach(@Param("doc") DocBean docBean);

    // @SqlRef("dListByDocid")
    // List<DocBean> dListByDocid(String[] docIds);

    /**
     * 删除中间表
     * 
     * @param docId
     * @return
     */
    int dDeleteMidData(@Param("docId") String docId);

    /**
     * 删除附件相关的数据
     * 
     * @param roleAllot
     * @return
     */
    @Executes({
            // 删除文档数据
            @Execute(sqlRef = @SqlRef("dDeleteDoc"), param = @BatchParam(false)),
            // 删除附件数据
            @Execute(sqlRef = @SqlRef("dDeleteAttachByDocid"), param = @BatchParam(false)),
            // 删除中间表数据
            @Execute(sqlRef = @SqlRef("dDeleteMidData"), param = @BatchParam(false))})
    int[] dDeleteDocAndAttach(@Param("docId") String docId);

}
