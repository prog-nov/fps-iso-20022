package com.forms.beneform4j.webapp.systemmanage.task.taskcollect.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean.ITaskBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean.TaskBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.dao.ITaskCollectDao;
import com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean.TaskRuleBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务采集实现类<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-20<br>
 */
@Service("taskCollectService")
@Scope("prototype")
public class TaskCollectService implements ITaskCollectService {

    @Resource(name = "taskCollectDao")
    private ITaskCollectDao dao;

    /**
     * 任务采集API方法
     * 
     * @param itBean 任务采集bean
     */
    @Override
    public void sCollectTask(ITaskBean itBean) {
        // 1.校验itBean
        this.sCheckParams(itBean);
        // 2.设置日志记录
        this.sSetParam(itBean, "setDealDate", Tool.DATE.getDate());
        this.sSetParam(itBean, "setDealTime", Tool.DATE.getTime());
        this.sSetParam(itBean, "setLogId", Tool.STRING.getRandomKeyId());
        // 有下一个任务节点则 插入新任务处理（已包含记录日志、删除当前任务）
        // 没有写一个任务则 记录日志，删除当前任务
        if (!Tool.CHECK.isEmpty(this.sGetParam(itBean, "getNextTaskId"))) {
            // 3.插入新任务
            this.sInsertNewTaskTg(itBean);
        } else {
            // 4.记录日志，删除当前任务
            this.sTranTaskLog(itBean);
            this.sDeleteTask(itBean);
        }
    }

    /**
     * 校验参数设置是否满足要求
     * 
     * @param bean 任务采集bean
     */
    public void sCheckParams(ITaskBean itBean) {
        // TODO Auto-generated method stub
        // 校验业务主键
        if (Tool.CHECK.isEmpty(itBean)) {
            Throw.throwRuntimeException(ExceptionCodes.AP020502);
        }
        // 校验业务主键
        if (Tool.CHECK.isEmpty(this.sGetParam(itBean, "getBusiKey"))) {
            Throw.throwRuntimeException(ExceptionCodes.AP020503);
        }
    }

    /**
     * 插入历史记录
     * 
     * @param bean 任务采集bean
     */
    public void sTranTaskLog(ITaskBean itBean) {
        // TODO Auto-generated method stub
        this.sSetParam(itBean, "setDealDate", Tool.DATE.getDate());
        this.sSetParam(itBean, "setDealTime", Tool.DATE.getTime());
        this.sSetParam(itBean, "setLogId", Tool.STRING.getRandomKeyId());
        dao.dTranTaskLog((TaskBean) itBean);
    }

    /**
     * 删除当前任务
     * 
     * @param bean 任务采集bean
     */
    public void sDeleteTask(ITaskBean itBean) {
        // TODO Auto-generated method stub
        dao.dDeleteTask((TaskBean) itBean);
    }

    /**
     * 插入新任务 插入前设置参数 1.记录日志 2.删除旧任务 3.插入新任务
     * 
     * @param bean 任务采集bean
     */
    public void sInsertNewTaskTg(ITaskBean itBean) {
        // TODO Auto-generated method stub
        // 插入新任务前赋值限定参数
        this.mInitInstLimits(itBean);
        // 插入新任务
        dao.dInsertNewTaskTg((TaskBean) itBean);
    }

    /**
     * 插入新任务
     * 
     * @param bean 任务采集bean
     */
    public void sInsertNewTask(ITaskBean itBean) {
        // TODO Auto-generated method stub
        // 插入新任务前赋值限定参数
        this.mInitInstLimits((TaskBean) itBean);
        // 插入新任务
        dao.dInsertNewTask((TaskBean) itBean);
    }

    /**
     * 插入新任务前赋值限定参数
     * 
     * @param bean 任务采集bean
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void mInitInstLimits(ITaskBean itBean) {
        TaskRuleBean ruleBean = dao.dFindRule((TaskBean) itBean);
        List limitList = null;
        String targetUrlId = null;
        String targetUrl = null;
        // 进入明细页面，但是传出参数为空，则抛出异常
        // 若非进入明细页面，则跳转队列url
        if ("1".equalsIgnoreCase(ruleBean.getDetailFlag()) && Tool.CHECK.isEmpty(this.sGetParam(itBean, "getParams"))) {
            Throw.throwRuntimeException(ExceptionCodes.AP020505);
        } else if ("1".equalsIgnoreCase(ruleBean.getDetailFlag())) {
            targetUrlId = ruleBean.getDetailMenuId();
        } else {
            targetUrlId = ruleBean.getMenuId();
        }
        targetUrl = dao.dFindTargetUrl(targetUrlId);
        if (Tool.CHECK.isBlank(targetUrl)) {
            Throw.throwRuntimeException(ExceptionCodes.AP020506);
        }
        this.sSetParam(itBean, "setTargetUrl", targetUrl);
        // 限定用户，且用户为空则使用已维护规则的用户列表
        // 若维护的用户列表为空则默认为当前用户
        if ("1".equalsIgnoreCase(ruleBean.getOperFlag()) && Tool.CHECK.isEmpty(this.sGetParam(itBean, "getUsersList"))) {
            limitList = dao.dSelectLimitUsers((TaskBean) itBean);
            if (Tool.CHECK.isEmpty(limitList)) {
                limitList = new ArrayList<String>();
                limitList.add(this.sGetParam(itBean, "getDealOper"));
            }
            this.sSetParam(itBean, "setUsersList", limitList);
        }
        // 限定角色，且角色为空则使用已维护规则的角色列表
        // 若维护的角色为空则默认为当前用户角色ID
        if ("1".equalsIgnoreCase(ruleBean.getRoleFlag()) && Tool.CHECK.isEmpty(this.sGetParam(itBean, "getRolesList"))) {
            limitList = dao.dSelectLimitRoles((TaskBean) itBean);
            if (Tool.CHECK.isEmpty(limitList)) {
                limitList = new ArrayList<String>();
                limitList.addAll(this.getUserRoleId((String) this.sGetParam(itBean, "getDealOper")));
            }
            this.sSetParam(itBean, "setRolesList", limitList);
        }
        // 限定机构，且机构为空则使用已维护规则的机构列表
        // 若维护的机构为空则默认为当前用户机构ID
        if ("1".equalsIgnoreCase(ruleBean.getOrgFlag()) && Tool.CHECK.isEmpty(this.sGetParam(itBean, "getOrgsList"))) {
            limitList = dao.dSelectLimitOrgs((TaskBean) itBean);
            if (Tool.CHECK.isEmpty(limitList)) {
                limitList = new ArrayList<String>();
                limitList.add(this.sGetParam(itBean, "getDealOrgId"));
            }
            this.sSetParam(itBean, "setOrgsList", limitList);
        }
        // 如果keyId为空则系统自动生成
        if (Tool.CHECK.isEmpty(this.sGetParam(itBean, "getKeyId"))) {
            this.sSetParam(itBean, "setKeyId", Tool.STRING.getRandomKeyId());
        }
    }

    /**
     * 获取用户对应roleId
     * 
     * @param userId
     * @return
     */
    public List<String> getUserRoleId(String userId) {
        return dao.dFindUserRoleId(userId);
    }

    /**
     * 设置ITaskBean的值
     * 
     * @param itBean
     * @param method
     * @param value
     */
    @Override
    public void sSetParam(ITaskBean itBean, String method, Object value) {
        // TODO Auto-generated method stub
        Tool.REFLECT.invokeMethod(itBean, method, value);
    }

    /**
     * 获取ITaskBean的值
     * 
     * @param itBean
     * @param method
     * @param value
     */
    @Override
    public Object sGetParam(ITaskBean itBean, String method) {
        // TODO Auto-generated method stub
        return Tool.REFLECT.invokeMethod(itBean, method);
    }

    /**
     * 初始化ItaskBean objs[0] dealOper objs[1] dealOrgId objs[2] currentTaskId objs[3] nextTaskId
     * objs[4] busiKey objs[5] params
     */
    @Override
    public void sInitTaskBean(ITaskBean itBean, Object... objs) {
        // TODO Auto-generated method stub
        int len = 0;
        if (!Tool.CHECK.isEmpty(objs)) {
            len = objs.length;
            if (len >= 1 && !Tool.CHECK.isEmpty(objs[0])) {
                this.sSetParam(itBean, "setDealOper", objs[0].toString());
            }
            if (len >= 2 && !Tool.CHECK.isEmpty(objs[1])) {
                this.sSetParam(itBean, "setDealOrgId", objs[1].toString());
            }
            if (len >= 3 && !Tool.CHECK.isEmpty(objs[2])) {
                this.sSetParam(itBean, "setCurrentTaskId", objs[2].toString());
            }
            if (len >= 4 && !Tool.CHECK.isEmpty(objs[3])) {
                this.sSetParam(itBean, "setNextTaskId", objs[3].toString());
            }
            if (len >= 5 && !Tool.CHECK.isEmpty(objs[4])) {
                this.sSetParam(itBean, "setBusiKey", objs[4].toString());
            }
            if (len >= 6 && !Tool.CHECK.isEmpty(objs[5])) {
                this.sSetParam(itBean, "setParams", objs[5].toString());
            }
        }
    }

}
