package com.forms.beneform4j.webapp.systemmanage.task.taskrule.service;

import java.util.List;

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
 * Description : 任务规则管理服务层接口<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-20<br>
 */
public interface ITaskRuleService {

    /**
     * 查询任务节点列表
     * 
     * @param bean
     * @param taskInit
     * @param page
     * @return
     */
    List<TaskRuleBean> sList(TaskRuleForm taskInit, IPage page);

    /**
     * 查找单个任务节点
     * 
     * @param nodeId
     * @return
     */
    TaskRuleBean sFind(String nodeId);

    /**
     * 新增单个任务节点
     * 
     * @param taskInit
     * @return
     */
    int sInsert(TaskRuleForm taskInit);

    /**
     * 更新单个任务节点
     * 
     * @param taskInit
     * @return
     */
    int sUpdate(TaskRuleForm taskInit);

    /**
     * 删除一组任务节点
     * 
     * @param nodeIds
     * @return
     */
    int sDelete(String[] ruleIds);

    /**
     * 查询机构树
     * 
     * @param dealType
     * @param ruleId
     * @return
     */
    List<TaskOrgBean> sListOrgTree(String ruleId);

    /**
     * 查询任务节点列表
     * 
     * @param taskrule
     * @return
     */
    List<TaskRuleBean> sListTaskNode(TaskRuleForm taskrule);

    /**
     * 查询勾连角色信息
     * 
     * @param currUserId
     * @param object
     * @return
     */
    List<RoleBean> sListRole(String dealType, String ruleId);

    /**
     * 查询任务限定人
     * 
     * @param ruleId
     * @param user
     * @param page
     * @return
     */
    List<UserBean> sListTaskUser(String ruleId, IPage page);

    /**
     * 查询任务限定人
     * 
     * @param user
     * @param page
     * @return
     */
    List<UserBean> sSearchTaskUser(UserForm user, IPage page);

}
