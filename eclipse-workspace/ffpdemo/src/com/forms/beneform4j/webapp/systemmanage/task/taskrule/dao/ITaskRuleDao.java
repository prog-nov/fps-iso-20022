package com.forms.beneform4j.webapp.systemmanage.task.taskrule.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskOrgBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskRuleBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.form.TaskRuleForm;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务规则管理数据访问层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-20<br>
 */
@Repository("taskRuleDao")
public interface ITaskRuleDao {

    List<TaskRuleBean> dList(TaskRuleForm taskrule, IPage page);

    TaskRuleBean dFind(String ruleId);

    @Executes({@Execute(sqlRef = @SqlRef("dUpdateTaskParam"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dInsertRegex"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dInsertUsers"), param = @BatchParam(item = "user", property = "usersList")), @Execute(sqlRef = @SqlRef("dInsertOrgs"), param = @BatchParam(item = "org", property = "orgsList")), @Execute(sqlRef = @SqlRef("dInsertRoles"), param = @BatchParam(item = "role", property = "rolesList"))})
    int[] dInsert(TaskRuleForm taskrule);

    @Executes({@Execute(sqlRef = @SqlRef("dUpdate"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dDeleteUsers"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dDeleteOrgs"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dDeleteRoles"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dInsertUsers"), param = @BatchParam(item = "user", property = "usersList")), @Execute(sqlRef = @SqlRef("dInsertOrgs"), param = @BatchParam(item = "org", property = "orgsList")), @Execute(sqlRef = @SqlRef("dInsertRoles"), param = @BatchParam(item = "role", property = "rolesList"))})
    int[] dUpdate(TaskRuleForm taskrule);

    @Executes({@Execute(sqlRef = @SqlRef("dUpdateTaskParamDel"), param = @BatchParam(item = "ruleId")), @Execute(sqlRef = @SqlRef("dDeleteRegex"), param = @BatchParam(item = "ruleId")), @Execute(sqlRef = @SqlRef("dDeleteUsers"), param = @BatchParam(item = "ruleId")), @Execute(sqlRef = @SqlRef("dDeleteOrgs"), param = @BatchParam(item = "ruleId")), @Execute(sqlRef = @SqlRef("dDeleteRoles"), param = @BatchParam(item = "ruleId"))})
    int[] dDelete(String[] ruleIds);

    List<TaskRuleBean> dListTaskNode(TaskRuleForm taskrule);

    int dDeleteLink(TaskRuleForm taskrule);

    List<TaskOrgBean> dListOrgTree(String ruleId);

    List<RoleBean> dListRoleForEdit(String ruleId);

    List<RoleBean> dListRoleForAdd();

    List<UserBean> dListTaskUser(String ruleId, IPage page);

    List<UserBean> dSearchTaskUser(UserForm user, IPage page);

    TaskRuleBean dCheckExistParam(TaskRuleForm taskrule);

}
