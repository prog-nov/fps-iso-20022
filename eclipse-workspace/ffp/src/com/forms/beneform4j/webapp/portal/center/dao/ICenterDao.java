package com.forms.beneform4j.webapp.portal.center.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.util.page.IPage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 首页展示数据访问层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-21<br>
 */
@Repository("centerDao")
public interface ICenterDao {

    /**
     * 获取首页待办事项队列
     * 
     * @param orgId
     * @param roleId
     * @param userId
     * @return
     */
    public List<Map<String, Object>> dQueryTaskData(@Param("userId") String userId);

    /**
     * 获取用户对应角色ID
     * 
     * @param userId
     * @return
     */
    public List<String> dFindRoleId(@Param("userId") String userId);

    /**
     * 获取任务数
     */
    @SuppressWarnings("rawtypes")
    public List<HashMap> dQueryTaskNum(@Param("taskIds") Object[] taskIds, @Param("userId") String userId, @Param("rolesId") List<String> rolesId, @Param("orgId") String orgId);

    @SuppressWarnings("rawtypes")
    public List<HashMap> dFindTaskParam(@Param("taskId") String taskId, @Param("userId") String userId, @Param("rolesId") List<String> rolesId, @Param("orgId") String orgId, IPage page);
}
