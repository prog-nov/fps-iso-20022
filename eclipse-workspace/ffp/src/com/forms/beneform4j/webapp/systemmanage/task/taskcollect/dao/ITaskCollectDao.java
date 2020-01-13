package com.forms.beneform4j.webapp.systemmanage.task.taskcollect.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean.ITaskBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean.TaskBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskRuleBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务采集数据访问层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-20<br>
 */
@Repository("taskCollectDao")
public interface ITaskCollectDao {

    void dTranTaskLog(TaskBean bean);

    void dDeleteTask(TaskBean bean);

    TaskRuleBean dFindRule(TaskBean bean);

    List<String> dSelectLimitUsers(TaskBean bean);

    List<String> dSelectLimitRoles(TaskBean bean);

    List<String> dSelectLimitOrgs(TaskBean bean);

    @Executes({
            // 记录日志
            @Execute(sqlRef = @SqlRef("dTranTaskLog"), param = @BatchParam(false)),
            // 删除旧数据
            @Execute(sqlRef = @SqlRef("dDeleteTask"), param = @BatchParam(false)),
            // 插入新数据
            @Execute(sqlRef = @SqlRef("dInsertTask"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dInsertUsers"), param = @BatchParam(item = "userId", property = "usersList")), @Execute(sqlRef = @SqlRef("dInsertOrgs"), param = @BatchParam(item = "orgId", property = "orgsList")), @Execute(sqlRef = @SqlRef("dInsertRoles"), param = @BatchParam(item = "roleId", property = "rolesList"))})
    int[] dInsertNewTaskTg(TaskBean bean);

    @Executes({
            // 插入新数据
            @Execute(sqlRef = @SqlRef("dInsertTask"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dInsertUsers"), param = @BatchParam(item = "userId", property = "usersList")), @Execute(sqlRef = @SqlRef("dInsertOrgs"), param = @BatchParam(item = "orgId", property = "orgsList")), @Execute(sqlRef = @SqlRef("dInsertRoles"), param = @BatchParam(item = "roleId", property = "rolesList"))})
    int[] dInsertNewTask(TaskBean bean);

    List<String> dFindUserRoleId(@Param("userId") String userId);

    ITaskBean dFindFirstTask(@Param("taskId") String taskId, @Param("userId") String userId, @Param("roleId") String roleId, @Param("orgId") String orgId);

    String dFindTargetUrl(@Param("menuId") String targetUrlId);

}
