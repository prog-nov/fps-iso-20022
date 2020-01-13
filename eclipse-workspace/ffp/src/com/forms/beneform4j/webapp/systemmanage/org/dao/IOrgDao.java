package com.forms.beneform4j.webapp.systemmanage.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgBean;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgNode;
import com.forms.beneform4j.webapp.systemmanage.org.form.OrgForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构服务DAO <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9 <br>
 */
@Repository("orgDao")
public interface IOrgDao {

	public List<OrgBean> dList();
	
    /**
     * 加载所有机构节点，用于同步加载机构树
     * 
     * @param form
     * @return
     */
    public List<OrgNode> dLoadTreeNodes(OrgForm form);

    /**
     * 获取子机构列表，用于异步加载加载机构树
     * 
     * @param parent
     * @return
     */
    public List<OrgBean> dGetChildren(OrgForm parent);

    /**
     * 查找机构
     * 
     * @param orgId
     * @return
     */
    public OrgBean dFind(@Param("orgId") String orgId);

    /**
     * 插入机构
     * 
     * @param org
     * @return
     */
    public int dInsert(OrgForm org);

    /**
     * 更新机构
     * 
     * @param org
     * @return
     */
    public int dUpdate(OrgForm org);

    /**
     * 删除机构
     * 
     * @param org
     * @return
     */
    public int dDelete(OrgForm org);

    /**
     * 删除孤立的无效的节点
     * 
     * @param rootId
     * @return
     */
    public int dDeleteInvalidNodes(@Param("rootId") String rootId);

    /**
     * 移动机构
     * 
     * @param org
     * @return
     */
    public int dMove(OrgForm org);
}
