package com.forms.beneform4j.webapp.systemmanage.role.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleLimitBean;
import com.forms.beneform4j.webapp.systemmanage.role.dao.IRoleLimitDao;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleLimitForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束维护服务<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
@Service("roleLimitService")
@Scope("prototype")
public class RoleLimitService implements IRoleLimitService {

    @Resource(name = "roleLimitDao")
    private IRoleLimitDao dao;

    /**
     * 查询列表
     * 
     * @param roleLimit
     * @param page
     * @return
     */
    @Override
    public List<RoleLimitBean> sList(RoleLimitForm roleLimit, IPage page) {
        return dao.dList(roleLimit, page);
    }

    /**
     * 查找
     * 
     * @param limitNo
     * @return
     */
    @Override
    public RoleLimitBean sFind(String limitNo) {
        return dao.dFind(limitNo);
    }

    /**
     * 查找第一个匹配的约束关系
     * 
     * @param roleIds
     * @param excludeLimitNo
     * @return
     */
    @Override
    public RoleLimitBean sFindFirstMatch(List<Integer> roleIds, String excludeLimitNo) {
        Set<Integer> roles = new HashSet<Integer>(roleIds);// 去掉重复的角色ID
        List<RoleLimitBean> matches = dao.dListMatch(roles, excludeLimitNo);
        if (null != matches && matches.size() >= 1) {
            return matches.get(0);
        }
        return null;
    }

    /**
     * 新增时查询可维护的角色列表
     * 
     * @param userId
     * @return
     */
    @Override
    public List<RoleBean> sListRoleForAdd(String userId) {
        return dao.dListRoleForAdd(userId);
    }

    /**
     * 修改时查询可维护的角色列表
     * 
     * @param userId
     * @param limitNo
     * @return
     */
    @Override
    public List<RoleBean> sListRoleForEdit(String userId, String limitNo) {
        return dao.dListRoleForEdit(userId, limitNo);
    }

    /**
     * 新增
     * 
     * @param roleLimit
     * @return
     */
    @Override
    public int sInsert(RoleLimitForm roleLimit) {
        RoleLimitBean bean = sFindFirstMatch(roleLimit.getRoleIds(), null);
        if (!Tool.CHECK.isEmpty(bean)) {
            throw Throw.createRuntimeException(ExceptionCodes.AP020301, bean.toString());
        } else {
            roleLimit.setLimitNo(Tool.STRING.getRandomKeyId());
            roleLimit.setInstDate(Tool.DATE.getDate());
            roleLimit.setInstTime(Tool.DATE.getTime());
            int rs[] = dao.dInsert(roleLimit);
            return rs[0];
        }
    }

    /**
     * 更新单个角色、角色权限(菜单)关系
     * 
     * @param roleLimit
     * @return
     */
    @Override
    public int sUpdate(RoleLimitForm roleLimit) {
        RoleLimitBean bean = sFindFirstMatch(roleLimit.getRoleIds(), roleLimit.getLimitNo());
        if (!Tool.CHECK.isEmpty(bean)) {
            throw Throw.createRuntimeException(ExceptionCodes.AP020301, bean.toString());
        } else {
            roleLimit.setInstDate(Tool.DATE.getDate());
            roleLimit.setInstTime(Tool.DATE.getTime());
            int rs[] = dao.dUpdate(roleLimit);
            return rs[0];
        }
    }

    /**
     * 删除一组角色
     * 
     * @param limitNos
     * @return
     */
    @Override
    public int sDelete(String[] limitNos) {
        int[] rs = dao.dDelete(limitNos);
        return rs[rs.length - 1];
    }
}
