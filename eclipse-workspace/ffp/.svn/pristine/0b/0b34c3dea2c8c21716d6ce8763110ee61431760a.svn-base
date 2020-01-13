package com.forms.beneform4j.core.util.clean;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.env.Beneform4jVersion;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 清理任务加载器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-19<br>
 */
public class CleanManager {

    /**
     * 是否已经初始化的标志
     */
    private static final AtomicBoolean initMonitor = new AtomicBoolean(false);

    /**
     * 平台需要扫描的执行清理类基包
     */
    private static final String beneform4jScanPackage = "com.forms.beneform4j";

    private static final SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

    private static final String JOB_GROUP_NAME = "BENEFORM4J_CLEAN_JOB_GROUP";

    private static final String TRIGGER_GROUP_NAME = "BENEFORM4J_CLEAN_TRIGGER_GROUP";

    /**
     * 系统启动初始化
     */
    public static void initialize() {
        if (!initMonitor.get()) {
            synchronized (initMonitor) {
                if (!initMonitor.get()) {
                    CommonLogger.info(Beneform4jVersion.Version + " celean job start...");
                    try {
                        init();
                    } catch (Exception e) {
                    }
                    initMonitor.set(true);
                }
            }
        } else {
        }
    }

    /**
     * 初始化
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private static void init() throws Exception {
        String scanPackage = BaseConfig.getInitScanPackage();
        if (CoreUtils.isBlank(scanPackage) || scanPackage.equals(beneform4jScanPackage)) {
            scanPackage = BaseConfig.getScanPackage();
        }
        if (CoreUtils.isBlank(scanPackage)) {
            scanPackage = beneform4jScanPackage;
        } else if (!scanPackage.equals(beneform4jScanPackage)) {
            scanPackage += "," + beneform4jScanPackage;
        }
        CommonLogger.debug("Beneform4j @clean scan package is " + scanPackage);
        // 扫描含初始化注解的类
        Set<Class<?>> init = CoreUtils.scanClasses(scanPackage, Clean.class);
        if (null != init) {
            for (Class<?> r : init) {
                if (checkCls(r)) {
                    Class<? extends IJob> j = (Class<? extends IJob>) r;
                    addJob(j);
                }
            }

        }
    }

    private static void addJob(Class<? extends IJob> cls) throws Exception {
        Clean clean = cls.getAnnotation(Clean.class);
        if (null != clean) {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(clean.description(), JOB_GROUP_NAME).build();
            CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(clean.cron());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(clean.description(), TRIGGER_GROUP_NAME).withSchedule(builder).build();
            sched.scheduleJob(jobDetail, trigger);
            sched.start();
        }
    }

    private static boolean checkCls(Class<?> r) {
        Class<?>[] interfaces = r.getInterfaces();
        if (null != interfaces && interfaces.length > 0) {
            for (Class<?> cls : interfaces) {
                if (cls.isAssignableFrom(IJob.class) || cls.isAssignableFrom(Job.class)) {
                    return true;
                }
            }
        }
        return false;
    }
}
