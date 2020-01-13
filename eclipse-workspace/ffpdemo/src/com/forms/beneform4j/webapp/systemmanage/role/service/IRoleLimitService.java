package com.forms.beneform4j.webapp.systemmanage.role.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleLimitBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleLimitForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束维护服务接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
public interface IRoleLimitService {

    /**
     * 查询角色约束列表
     * 
     * @param roleLimit
     * @param page
     * @return
     */
    List<RoleLimitBean> sList(RoleLimitForm roleLimit, IPage page);

    /**
     * 查找单个约束关系
     * 
     * @param limitNo
     * @return
     */
    RoleLimitBean sFind(String limitNo);

    /**
     * 根据角色ID获取第一个匹配的约束关系
     * 
     * @param roleIds
     * @param excludeLimitNo
     * @return
     */
    RoleLimitBean sFindFirstMatch(List<Integer> roleIds, String excludeLimitNo);

    /**
     * 新增时查询可维护的角色列表
     * 
     * @param userId
     * @return
     */
    List<RoleBean> sListRoleForAdd(String userId);

    /**
     * 修改时查询可维护的角色列表
     * 
     * @param userId
     * @param limitNo
     * @return
     */
    List<RoleBean> sListRoleForEdit(String userId, String limitNo);

    /**
     * 新增约束关系
     * 
     * @param roleLimit
     * @return
     */
    int sInsert(RoleLimitForm roleLimit);

    /**
     * 更新约束关系
     * 
     * @param roleLimit
     * @return
     */
    int sUpdate(RoleLimitForm roleLimit);

    /**
     * 删除一组约束关系
     * 
     * @param limitNos
     * @return
     */
    int sDelete(String[] limitNos);
}
