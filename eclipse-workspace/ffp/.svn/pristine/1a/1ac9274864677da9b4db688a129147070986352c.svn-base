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
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.dao.IRoleDao;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleForm;
import com.forms.ffp.core.define.FFPConstants;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色维护服务<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
@Service("roleService")
@Scope("prototype")
public class RoleService implements IRoleService
{

	@Resource(name = "roleDao")
	private IRoleDao dao;

	/**
	 * 查询角色列表
	 * 
	 * @param role
	 * @param page
	 * @return
	 */
	@Override
	public List<RoleBean> sList(RoleForm role, IPage page)
	{
		return dao.dList(role, page);
	}

	/**
	 * 查询权限类型列表
	 * 
	 * @param permType
	 * @return
	 */
	@Override
	public List<PermTypeBean> sListPermType(String permType)
	{
		return dao.dListPermType(permType);
	}

	/**
	 * 查找单个角色
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public RoleBean sFind(int roleId)
	{
		return dao.dFind(roleId);
	}

	/**
	 * 新增角色时查询可维护的菜单列表
	 * 
	 * @param userId
	 * @param permType
	 * @return
	 */
	@Override
	public List<RolePermissionBean> sListRolePermissionForAdd(String userId, String permType)
	{
		List<PermTypeBean> ptbs = dao.dListPermType(permType);
		if (null == ptbs || ptbs.isEmpty())
		{
			return Collections.emptyList();
		} else
		{
			return dao.dListRolePermissionForAdd(userId, ptbs.get(0));
		}
	}

	@Override
	public List<RoleAllotBean> sListRoleAllotForAdd(String userId)
	{
		return dao.dListRoleAllotForAdd(userId);
	}

	/**
	 * 新增单个角色
	 * 
	 * @param role
	 * @return
	 */
	@Override
	public synchronized int sInsert(RoleForm role)
	{
		List<RoleBean> list = dao.dListByRoleName(role.getRoleName(), 0);
		if (null != list && !list.isEmpty())
		{
			throw Throw.createRuntimeException(ExceptionCodes.DATA_HAS_EXIST, role.getRoleName());
		}
		role.setInstDate(Tool.DATE.getDate());
		role.setInstTime(Tool.DATE.getTime());
		int rs[] = dao.dInsert(role);
		return rs[0];
	}

	/**
	 * 修改角色时查询可维护的菜单列表
	 * 
	 * @param userId
	 * @param permType
	 * @param roleId
	 * @return
	 */
	@Override
	public List<RolePermissionBean> sListRolePermissionForEdit(String userId, String permType, int roleId)
	{
		List<PermTypeBean> ptbs = dao.dListPermType(permType);
		if (null == ptbs || ptbs.isEmpty())
		{
			return Collections.emptyList();
		} else
		{
			return dao.dListRolePermissionForEdit(userId, ptbs.get(0), roleId);
		}
	}

	@Override
	public List<RoleAllotBean> sListRoleAllotForEdit(String userId, int roleId)
	{
		return dao.dListRoleAllotForEdit(userId, roleId);
	}

	/**
	 * 更新单个角色、角色权限(菜单)关系
	 * 
	 * @param role
	 * @return
	 */
	@Override
	public int sUpdate(RoleForm role)
	{
		checkDeleteAdministratorRoleIds(new int[]{role.getRoleId()});
		List<RoleBean> list = dao.dListByRoleName(role.getRoleName(), role.getRoleId());
		if (null != list && !list.isEmpty())
		{
			throw Throw.createRuntimeException(ExceptionCodes.DATA_HAS_EXIST, role.getRoleName());
		}
		role.setModiDate(Tool.DATE.getDate());
		role.setModiTime(Tool.DATE.getTime());
		int[] rs = dao.dUpdate(role);
		return rs[0];
	}

	/**
	 * 删除一组角色
	 * 
	 * @param roleIds
	 * @return
	 */
	@Override
	public int sDelete(int[] roleIds)
	{
		checkDeleteAdministratorRoleIds(roleIds);
		int[] rs = dao.dDelete(roleIds);
		return rs[rs.length - 1];// 最后一个为删除角色信息
	}

	private void checkDeleteAdministratorRoleIds(int[] roleIds)
	{
		for (int roleId : roleIds)
		{
			if (FFPConstants.ROLE_ID == roleId)
			{
				Throw.throwRuntimeException(ExceptionCodes.AP020302);
			}

		}
	}
}
