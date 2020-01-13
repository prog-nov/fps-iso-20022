package com.forms.beneform4j.webapp.systemmanage.task.taskcollect.aop;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean.ITaskBean;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.service.TaskCollectService;
import com.forms.beneform4j.webapp.systemmanage.task.taskcollect.sterotype.Task;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户数据访问层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-19<br>
 */
public class TaskAspect {

    @Resource(name = "taskCollectService")
    private TaskCollectService service;

    /**
     * 切面处理
     * 
     * @param pjd
     * @return
     * @throws Throwable
     */
    @Transactional(rollbackFor = Throwable.class)
    public Object doAspect(ProceedingJoinPoint pjd, Task task) throws Throwable {
        Object result = null;
        boolean isSameTransactional = false;
        boolean doCollectTaskFlag = false;
        Throwable busiExce = null;
        try {
            // 获取是否同意事务
            isSameTransactional = task.isSameTransactional();
            // 1.业务处理
            try {
                result = pjd.proceed();
                doCollectTaskFlag = true;
            } catch (Throwable e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // 若业务处理抛出异常且属于同一事务中则不执行任务收集
                if (isSameTransactional) {
                    doCollectTaskFlag = false;
                }
                busiExce = e;
            }

            // 2.任务收集
            try {
                if (doCollectTaskFlag) {
                    ITaskBean itBean = this.getITaskBean(pjd);
                    service.sCollectTask(itBean);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                throw e;
            }
            // 若业务处理异常则抛出异常
            if (!Tool.CHECK.isEmpty(busiExce)) {
                throw busiExce;
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * 获取任务采集ITaskBean
     * 
     * @param pjd
     * @return
     */
    public ITaskBean getITaskBean(ProceedingJoinPoint pjd) {
        Object[] args = null;
        ITaskBean itBean = null;
        try {
            args = pjd.getArgs();
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof ITaskBean) {
                    itBean = (ITaskBean) args[i];
                    break;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return itBean;
    }
}
