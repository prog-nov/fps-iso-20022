package com.forms.beneform4j.webapp.systemmanage.org.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.TreeFactory;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgBean;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgNode;
import com.forms.beneform4j.webapp.systemmanage.org.dao.IOrgDao;
import com.forms.beneform4j.webapp.systemmanage.org.form.OrgForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构服务实现类 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9 <br>
 */
@Service("orgService") // 注册为Spring容器中的Bean
@Scope("prototype") // 由于机构服务的功能并非频繁使用，这里生命周期使用prototype，表示每次服务都创建新实例，服务完成后销毁这个实例，如果频繁使用，可使用默认的单列singleon
public class OrgService implements IOrgService {

    // 自动注入IOrgDao的实现类，并指定Bean名称为"orgDao"，一般用于有多个实现类的情况
    @Resource(name = "orgDao")
    private IOrgDao dao;

    @Override
    public ITree<OrgNode> sLoadTree(OrgForm form) {
        List<OrgNode> nodes = dao.dLoadTreeNodes(form);
        ITree<OrgNode> tree = TreeFactory.builder(nodes);
        String orgId = form.getOrgId();
        if (!Tool.CHECK.isBlank(orgId) && !ROOT_ORG_ID.equalsIgnoreCase(orgId)) {
            tree = tree.getSubTree(orgId);
        }
        return tree;
    }

    @Override
    public List<OrgBean> sGetChildren(OrgForm parent) {
        // 如果未传入父机构号，也未传入机构号，就使用默认的根机构号
        if (Tool.CHECK.isBlank(parent.getSupOrgId()) && Tool.CHECK.isBlank(parent.getOrgId())) {
            parent.setOrgId(ROOT_ORG_ID);
        }
        return dao.dGetChildren(parent);
    }

    @Override
    public OrgBean sFind(String orgId) {
        return dao.dFind(orgId);
    }

    /**
     * 这里的删除基于下面的逻辑 1. 首先删除节点本身 2. 由于有节点被删除，如果被删除的节点不是叶子节点的话，就会产生孤立的节点，然后删除这些孤立的节点 3.
     * 循环第2步，直到不存在孤立的节点为止 4. 整个处理处于一个事务中 这里为了兼容性的考虑，没有使用类似于Oracle中的层级查询语句（start with ... connect by
     * ...），提升了兼容性，牺牲了部分性能
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int sDelete(OrgForm org) {
        int rs = dao.dDelete(org);
        int count = 0;
        do {
            count = dao.dDeleteInvalidNodes(ROOT_ORG_ID);// 删除无效的子机构
            rs += count;
        } while (count > 0);
        return rs;
    }

    @Override
    public int sInsert(OrgForm org) {
        // 新增之前，设置新增日志相关的信息，新增用户不能再这里设置，因为获取用户需要从控制层的Session中获取，而服务实现层是不允许访问控制层的API的，因此新增用户需要在控制层中设置
        org.setInstDate(Tool.DATE.getDate());
        org.setInstTime(Tool.DATE.getTime());

        // 一般情况下，不需要捕获和处理异常，平台会自动处理（比如机构号已经存在这类数据重复的异常）
        return dao.dInsert(org);
    }

    @Override
    public int sUpdate(OrgForm org) {
        // 修改之前，设置修改日志相关的信息
        org.setModiDate(Tool.DATE.getDate());
        org.setModiTime(Tool.DATE.getTime());
        return dao.dUpdate(org);
    }

    @Override
    public int sMove(OrgForm org) {
        // 经过控制层的校验，可以假设org != null，并且机构号、父机构号均不为空

        // 拖动机构时，机构号和父机构号不能相同
        if (org.getOrgId().equals(org.getSupOrgId())) {
            throw Throw.createRuntimeException(ExceptionCodes.AP020601);
        }

        OrgBean bean = null;
        try {
            bean = sFind(org.getSupOrgId());
        } catch (Exception e) {
        }
        if (null == bean) {
            // 拖动机构时，父机构不存在
            throw Throw.createRuntimeException(ExceptionCodes.AP020602);
        } else if (org.getOrgId().equals(bean.getSupOrgId())) {
            // 拖动机构时，父机构不能是当前机构的子机构
            throw Throw.createRuntimeException(ExceptionCodes.AP020603);
        }

        // 其它校验，比如父机构不能是当前机构的子孙机构等，这里暂且忽略

        // 执行拖动操作
        return dao.dMove(org);
    }
}
