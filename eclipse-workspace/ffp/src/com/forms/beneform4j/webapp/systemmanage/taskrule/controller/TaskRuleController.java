package com.forms.beneform4j.webapp.systemmanage.taskrule.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.web.annotation.TreeJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.service.IRoleService;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskOrgBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskRuleBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.form.TaskRuleForm;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.service.ITaskRuleService;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;
import com.forms.beneform4j.webapp.systemmanage.user.service.IUserService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务规则管理控制层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-20<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/taskrule")
public class TaskRuleController {

    @Resource(name = "taskRuleService")
    private ITaskRuleService service;

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Resource(name = "userService")
    private IUserService userService;

    /**
     * 列表
     * 
     * @param taskrule
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<TaskRuleBean> list(TaskRuleForm taskrule, IPage page) {
        return service.sList(taskrule, page);
    }

    /**
     * 新增页面
     * 
     * @param userId
     * @return
     */
    @RequestMapping("gotoAdd")
    public ModelAndView gotoAdd(@User String userId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/taskrule/add");
        return mv;
    }

    /**
     * 新增页面
     * 
     * @param userId
     * @return
     */
    @RequestMapping("gotoUserAdd")
    public ModelAndView gotoUserAdd(@User String userId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/taskrule/userlist");
        return mv;
    }

    /**
     * 编辑页面
     * 
     * @param userId
     * @return
     */
    @RequestMapping("gotoEdit")
    public ModelAndView gotoEdit(@User String userId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/taskrule/edit");
        return mv;
    }

    /**
     * 角色列表
     * 
     * @param dealType
     * @param ruleId
     * @return
     */
    @RequestMapping("listRole")
    @ListJsonBody
    public List<RoleBean> listRole(@RequestParam("dealType") String dealType, @RequestParam("ruleId") String ruleId) {
        return service.sListRole(dealType, ruleId);
    }

    /**
     * 列出任务节点
     * 
     * @param taskrule
     * @return
     */
    @RequestMapping("listTaskNode")
    @ListJsonBody
    public List<TaskRuleBean> listTaskNode(TaskRuleForm taskrule) {
        List<TaskRuleBean> list = service.sListTaskNode(taskrule);
        return list;
    }

    /**
     * 机构树
     * 
     * @param ruleId
     * @return
     */
    @RequestMapping("listOrgTree")
    @TreeJsonBody
    public List<TaskOrgBean> listOrgTree(@RequestParam("ruleId") String ruleId) {
        List<TaskOrgBean> list = service.sListOrgTree(ruleId);
        return list;
    }

    /**
     * 新增
     * 
     * @param taskrule
     * @param userId
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(TaskRuleForm taskrule, @User String userId) {
        taskrule.setInstOper(userId);
        return service.sInsert(taskrule);
    }

    /**
     * 更新
     * 
     * @param taskrule
     * @param userId
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(TaskRuleForm taskrule, @User String userId) {
        taskrule.setModiOper(userId);
        return service.sUpdate(taskrule);
    }

    /**
     * 删除
     * 
     * @param ruleIds
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "ruleIds[]") String[] ruleIds) {
        return service.sDelete(ruleIds);
    }

    /**
     * 根据Id列出任务
     * 
     * @param ruleId
     * @param page
     * @return
     */
    @RequestMapping("listTaskUser")
    @PageJsonBody
    public List<UserBean> listTaskUser(@RequestParam("ruleId") String ruleId, IPage page) {
        return service.sListTaskUser(ruleId, page);
    }

    /**
     * 查询待办任务
     * 
     * @param user
     * @param page
     * @return
     */
    @RequestMapping("searchTaskUser")
    @PageJsonBody
    public List<UserBean> searchTaskUser(UserForm user, IPage page) {
        return service.sSearchTaskUser(user, page);
    }

}
