package com.forms.beneform4j.webapp.systemmanage.role.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleLimitBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleLimitForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束维护DAO<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
@Repository("roleLimitDao")
public interface IRoleLimitDao {

    /**
     * 查询列表
     * 
     * @param roleLimit
     * @param page
     * @return
     */
    public List<RoleLimitBean> dList(RoleLimitForm roleLimit, IPage page);

    /**
     * 查找
     * 
     * @param limitNo
     * @return
     */
    @SqlRef("dFind")
    public RoleLimitBean dFind(@Param("limitNo") String limitNo);

    /**
     * 查找匹配的约束关系
     * 
     * @param roleIds
     * @param excludeLimitNo
     * @return
     */
    public List<RoleLimitBean> dListMatch(@Param("roleIds") Set<Integer> roleIds, @Param("excludeLimitNo") String excludeLimitNo);

    /**
     * 新增时查询可维护的角色列表
     * 
     * @param userId
     * @return
     */
    public List<RoleBean> dListRoleForAdd(@Param("userId") String userId);

    /**
     * 修改时查询可维护的角色列表
     * 
     * @param userId
     * @param limitNO
     * @return
     */
    public List<RoleBean> dListRoleForEdit(@Param("userId") String userId, @Param("limitNo") String limitNO);

    /**
     * 新增
     * 
     * @param roleLimit
     * @return
     */
    public int[] dInsert(@BatchParam(item = "roleId", property = "roleIds") RoleLimitForm roleLimit);

    /**
     * 更新
     * 
     * @param roleLimit
     * @return
     */
    @Executes({@Execute(sqlRef = @SqlRef("dDelete"), param = @BatchParam(value = false, property = "limitNo")), @Execute(sqlRef = @SqlRef("dInsert"), param = @BatchParam(item = "roleId", property = "roleIds"))})
    public int[] dUpdate(RoleLimitForm roleLimit);

    /**
     * 删除
     * 
     * @param limitNos
     * @return
     */
    public int[] dDelete(@BatchParam(item = "limitNo") String[] limitNos);

}
