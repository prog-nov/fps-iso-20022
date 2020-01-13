package com.forms.beneform4j.webapp.systemmanage.param.enums.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamDataBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamDefBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamTreeBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamDataForm;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamDefForm;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamTreeForm;
import com.forms.beneform4j.webapp.systemmanage.param.enums.dao.IEnumParamManagerDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数维护服务类<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-7<br>
 */
@Service("enumManagerService")
@Scope("prototype")
public class EnumManagerService implements IEnumManagerService {

    @Resource(name = "enumParamManagerDao")
    private IEnumParamManagerDao paramDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EnumParamDefBean> sListParamDef(EnumParamDefForm form, IPage page) {
        return paramDao.dListParamDef(form, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EnumParamDataBean> sListParamData(EnumParamDataForm form) {
        return paramDao.dListParamData(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EnumParamTreeBean> sListParamTree(EnumParamTreeForm form) {
        return paramDao.dListParamTree(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sInsertParamDef(EnumParamDefForm form) {
        return paramDao.dInsertParamDef(form);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public int sUpdateListData(List<EnumParamDataForm> list) {
        int[] rs = paramDao.dUpdateListData(list);
        return rs[rs.length - 1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sUpdateTreeNode(EnumParamTreeForm form) {
        return paramDao.dUpdateTreeNode(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sUpdateParamDef(EnumParamDefForm form) {
        return paramDao.dUpdateParamDef(form);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public int sDeleteParamDef(String[] paramCodes) {
        int[] rs = paramDao.dDeleteParamDef(paramCodes);
        return rs[rs.length - 1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumParamTreeBean sFindTreeNode(EnumParamTreeForm form) {
        return paramDao.dFindTreeNode(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sInsertTreeNode(EnumParamTreeForm form) {
        return paramDao.dInsertTreeNode(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sMoveTreeNode(EnumParamTreeForm form) {
        return paramDao.dMoveTreeNode(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sDeleteTreeNode(EnumParamTreeForm form) {
        return paramDao.dDeleteTreeNode(form);
    }

}
