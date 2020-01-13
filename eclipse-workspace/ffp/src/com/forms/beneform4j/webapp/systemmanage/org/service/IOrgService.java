package com.forms.beneform4j.webapp.systemmanage.org.service;

import java.util.List;

import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgBean;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgNode;
import com.forms.beneform4j.webapp.systemmanage.org.form.OrgForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构维护服务 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9 <br>
 */
public interface IOrgService {

    /**
     * 最大机构ID
     */
    public String ROOT_ORG_ID = "0000";

    /**
     * 同步加载机构树
     * 
     * @param org
     * @return
     */
    public ITree<OrgNode> sLoadTree(OrgForm org);

    /**
     * 获取机构子节点
     * 
     * @param parent
     * @return
     */
    public List<OrgBean> sGetChildren(OrgForm parent);

    /**
     * 获取机构节点
     * 
     * @param orgId
     * @return
     */
    public OrgBean sFind(String orgId);

    /**
     * 删除节点
     * 
     * @param org
     * @return
     */
    public int sDelete(OrgForm org);

    /**
     * 添加节点
     * 
     * @param org
     * @return
     */
    public int sInsert(OrgForm org);

    /**
     * 修改节点
     * 
     * @param org
     * @return
     */
    public int sUpdate(OrgForm org);

    /**
     * 移动机构节点
     * 
     * @param org
     * @return
     */
    public int sMove(OrgForm org);
}
