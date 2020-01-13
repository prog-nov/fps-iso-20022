package com.forms.beneform4j.webapp.systemmanage.task.taskrule.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskOrgBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskRuleBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.dao.ITaskRuleDao;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.form.TaskRuleForm;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务规则管理模块服务层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-20<br>
 */
@Service("taskRuleService")
@Scope("prototype")
public class TaskRuleService implements ITaskRuleService {

    @Resource(name = "taskRuleDao")
    private ITaskRuleDao dao;

    /**
     * 查询规则列表
     * 
     * @param taskrule
     * @param page
     * @return
     */
    @Override
    public List<TaskRuleBean> sList(TaskRuleForm taskrule, IPage page) {
        return dao.dList(taskrule, page);
    }

    /**
     * 查找单个规则
     * 
     * @param keyId
     * @return
     */
    @Override
    public TaskRuleBean sFind(String keyId) {
        return dao.dFind(keyId);
    }

    /**
     * 新增单个用户
     * 
     * @param taskrule
     * @return
     */
    @Override
    public int sInsert(TaskRuleForm taskrule) {

        TaskRuleBean checkBean = dao.dCheckExistParam(taskrule);
        if (null != checkBean && !Tool.CHECK.isEmpty(checkBean.getRuleId())) {
            List<TaskRuleBean> nodeList = dao.dListTaskNode(taskrule);
            Throw.throwRuntimeException(ExceptionCodes.AP020501, nodeList.get(0).getText());
        }
        taskrule.setInstDate(Tool.DATE.getDate());
        taskrule.setInstTime(Tool.DATE.getTime());
        taskrule.setRuleId(Tool.STRING.getRandomKeyId());
        if (!"1".equalsIgnoreCase(taskrule.getOperFlag())) {
            taskrule.setUsersList(new ArrayList<UserBean>());
        }
        if (!"1".equalsIgnoreCase(taskrule.getOrgFlag())) {
            taskrule.setOrgsList(new ArrayList<TaskOrgBean>());
        }
        if (!"1".equalsIgnoreCase(taskrule.getRoleFlag())) {
            taskrule.setRolesList(new ArrayList<RoleBean>());
        }
        int[] rs = dao.dInsert(taskrule);
        return rs[0];
    }

    /**
     * 更新单个用户、用户角色关系
     * 
     * @param taskrule
     * @return
     */
    @Override
    public int sUpdate(TaskRuleForm taskrule) {
        taskrule.setModiDate(Tool.DATE.getDate());
        taskrule.setModiTime(Tool.DATE.getTime());
        if (!"1".equalsIgnoreCase(taskrule.getOperFlag())) {
            taskrule.setUsersList(new ArrayList<UserBean>());
        }
        if (!"1".equalsIgnoreCase(taskrule.getOrgFlag())) {
            taskrule.setOrgsList(new ArrayList<TaskOrgBean>());
        }
        if (!"1".equalsIgnoreCase(taskrule.getRoleFlag())) {
            taskrule.setRolesList(new ArrayList<RoleBean>());
        }
        int[] rs = dao.dUpdate(taskrule);
        return rs[0];
    }

    /**
     * 删除一组任务规则
     * 
     * @param keyIds
     * @return
     */
    @Override
    public int sDelete(String[] keyIds) {
        int[] rs = dao.dDelete(keyIds);
        return rs[0];
    }

    /**
     * 展示机构数
     */
    @Override
    public List<TaskOrgBean> sListOrgTree(String ruleId) {
        return dao.dListOrgTree(ruleId);
    }

    /**
     * 查询任务节点
     */
    @Override
    public List<TaskRuleBean> sListTaskNode(TaskRuleForm taskrule) {
        return dao.dListTaskNode(taskrule);
    }

    /**
     * 展示角色列表
     */
    @Override
    public List<RoleBean> sListRole(String dealType, String ruleId) {
        List<RoleBean> list = null;
        if ("add".equalsIgnoreCase(dealType)) {
            list = dao.dListRoleForAdd();
        } else if ("edit".equalsIgnoreCase(dealType)) {
            list = dao.dListRoleForEdit(ruleId);
        }
        return list;
    }

    @Override
    public List<UserBean> sListTaskUser(String ruleId, IPage page) {
        return dao.dListTaskUser(ruleId, page);
    }

    @Override
    public List<UserBean> sSearchTaskUser(UserForm user, IPage page) {
        return dao.dSearchTaskUser(user, page);
    }

}
