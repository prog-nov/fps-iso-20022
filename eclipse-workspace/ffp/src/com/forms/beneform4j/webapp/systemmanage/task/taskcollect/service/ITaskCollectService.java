package com.forms.beneform4j.webapp.systemmanage.task.taskcollect.service;

import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean.ITaskBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务采集接口<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-20<br>
 */
public interface ITaskCollectService {

    /**
     * 任务采集总入口
     * 
     * @param itBean
     */
    public void sCollectTask(ITaskBean itBean);

    /**
     * 任务采集前校验
     * 
     * @param bean
     */
    public void sCheckParams(ITaskBean bean);

    /**
     * 记录任务日志
     * 
     * @param itBean
     */
    public void sTranTaskLog(ITaskBean itBean);

    /**
     * 删除当前任务 删除 busiKey 和 taskIs
     * 
     * @param itBean
     */
    public void sDeleteTask(ITaskBean itBean);

    /**
     * 插入下一个节点任务
     * 
     * @param itBean
     */
    public void sInsertNewTask(ITaskBean itBean);

    /**
     * 设置ITaskBean的值
     * 
     * @param itBean
     * @param method
     * @param value
     */
    public void sSetParam(ITaskBean itBean, String method, Object value);

    /**
     * 获取ITaskBean的值
     * 
     * @param itBean
     * @param method
     * @param value
     */
    public Object sGetParam(ITaskBean itBean, String method);

    /**
     * 初始化ITaskBean
     * 
     * @param objs
     */
    public void sInitTaskBean(ITaskBean itBean, Object... objs);
}
