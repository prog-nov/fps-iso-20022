package com.forms.beneform4j.webapp.systemmanage.role.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.role.bean.PermTypeBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.dao.IRoleAllotDao;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleAllotForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色(分配)维护服务<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
@Service("roleAllotService")
@Scope("prototype")
public class RoleAllotService implements IRoleAllotService {

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Resource(name = "roleAllotDao")
    private IRoleAllotDao dao;

    /**
     * 查询角色(分配)列表
     * 
     * @param roleAllot
     * @param page
     * @return
     */
    @Override
    public List<RoleAllotBean> sList(RoleAllotForm roleAllot, IPage page) {
        return dao.dList(roleAllot, page);
    }

    /**
     * 查找单个角色(分配)
     * 
     * @param roleAllotId
     * @return
     */
    @Override
    public RoleAllotBean sFind(int roleAllotId) {
        return dao.dFind(roleAllotId);
    }

    /**
     * 新增角色(分配)时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @return
     */
    @Override
    public List<RolePermissionBean> sListRolePermissionForAdd(String userId, String permType) {
        List<PermTypeBean> ptbs = roleService.sListPermType(permType);
        if (null == ptbs || ptbs.isEmpty()) {
            return Collections.emptyList();
        } else {
            return dao.dListRolePermissionForAdd(userId, ptbs.get(0));
        }
    }

    /**
     * 新增单个角色(分配)
     * 
     * @param roleAllot
     * @return
     */
    @Override
    public synchronized int sInsert(RoleAllotForm roleAllot) {
        List<RoleAllotBean> list = dao.dListByRoleAllotName(roleAllot.getRoleAllotName(), 0);
        if (null != list && !list.isEmpty()) {
            Throw.throwRuntimeException(ExceptionCodes.DATA_HAS_EXIST, roleAllot.getRoleAllotName());
        }
        roleAllot.setInstDate(Tool.DATE.getDate());
        roleAllot.setInstTime(Tool.DATE.getTime());
        int rs[] = dao.dInsert(roleAllot);
        return rs[0];
    }

    /**
     * 修改角色(分配)时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @param roleAllotId
     * @return
     */
    @Override
    public List<RolePermissionBean> sListRolePermissionForEdit(String userId, String permType, int roleAllotId) {
        List<PermTypeBean> ptbs = roleService.sListPermType(permType);
        if (null == ptbs || ptbs.isEmpty()) {
            return Collections.emptyList();
        } else {
            return dao.dListRolePermissionForEdit(userId, ptbs.get(0), roleAllotId);
        }
    }

    /**
     * 更新单个角色(分配)、角色(分配)权限(菜单)关系
     * 
     * @param roleAllot
     * @return
     */
    @Override
    public int sUpdate(RoleAllotForm roleAllot) {
        List<RoleAllotBean> list = dao.dListByRoleAllotName(roleAllot.getRoleAllotName(), roleAllot.getRoleAllotId());
        if (null != list && !list.isEmpty()) {
            Throw.throwRuntimeException(ExceptionCodes.DATA_HAS_EXIST, roleAllot.getRoleAllotName());
        }
        roleAllot.setModiDate(Tool.DATE.getDate());
        roleAllot.setModiTime(Tool.DATE.getTime());
        int[] rs = dao.dUpdate(roleAllot);
        return rs[0];
    }

    /**
     * 删除一组角色(分配)
     * 
     * @param roleAllotIds
     * @return
     */
    @Override
    public int sDelete(int[] roleAllotIds) {
        int[] rs = dao.dDelete(roleAllotIds);
        return rs[rs.length - 1];// 最后一个为删除角色(分配)信息
    }
}
