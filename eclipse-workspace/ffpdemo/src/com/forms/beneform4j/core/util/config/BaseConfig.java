package com.forms.beneform4j.core.util.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.aopalliance.aop.Advice;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.config.CacheManagementConfigUtils;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.annotation.Configable;
import com.forms.beneform4j.core.util.annotation.Warning;
import com.forms.beneform4j.core.util.bean.IContextBeanOperateWrapper;
import com.forms.beneform4j.core.util.data.accessor.IDataAccessorFactory;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.exception.level.ExceptionLevel;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMetaLoader;
import com.forms.beneform4j.core.util.locale.ILocaleMessageResolver;
import com.forms.beneform4j.core.util.locale.ILocaleResolver;
import com.forms.beneform4j.core.util.logger.termination.ILogTermination;
import com.forms.beneform4j.core.util.monitor.IMonitor;
import com.forms.beneform4j.core.util.page.IPageFactory;
import com.forms.beneform4j.core.util.reflect.object.IObjectFactory;
import com.forms.beneform4j.core.util.reflect.object.impl.SpringObjectFactory;
import com.forms.beneform4j.core.util.scan.IScan;
import com.forms.beneform4j.core.util.stack.IStackFactory;
import com.forms.beneform4j.core.util.track.ITracker;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 基本配置<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-22<br>
 */
public class BaseConfig extends ConfigHelper implements InitializingBean, ApplicationContextAware {

    /**
     * Spring容器
     */
    private static ApplicationContext applicationContext;

    /**
     * 扫描包
     */
    @Configable
    private static String scanPackage;

    /**
     * 初始化注解@Init的扫描包
     */
    @Configable
    private static String initScanPackage;

    /**
     * 字符集
     */
    @Configable
    private static String encoding;

    /**
     * 默认本地化对象
     */
    @Configable
    private static String defaultLocale;

    /**
     * 日期格式
     */
    @Configable
    private static String dateFormat;

    /**
     * 时间格式
     */
    @Configable
    private static String timeFormat;

    /**
     * 日期时间格式
     */
    @Configable
    private static String datetimeFormat;

    /**
     * 应用名称
     */
    @Configable
    private static String appName;

    /**
     * 国际化解析器
     */
    @Configable
    private static ILocaleResolver localeResolver;

    /**
     * 国际化消息解析器
     */
    @Configable
    @Warning
    private static ILocaleMessageResolver localeMessageResolver;

    /**
     * Bean操作封装类
     */
    private static IContextBeanOperateWrapper beanOperateWrapper;

    /**
     * 分页工厂
     */
    @Configable
    @Warning
    private static IPageFactory pageFactory;

    /**
     * 堆栈工厂
     */
    @Configable
    private static IStackFactory stackFactory;

    /**
     * 日志终端列表
     */
    @Configable
    private static List<ILogTermination> logTerminations;

    /**
     * 日志监控
     */
    @Configable
    private static IMonitor logMonitor;

    /**
     * 异常监控
     */
    @Configable
    private static IMonitor exceptionMonitor;

    /**
     * 异常元数据加载器
     */
    @Configable
    @Warning
    private static IExceptionMetaLoader exceptionMetaLoader;

    /**
     * 异常处理器列表
     */
    @Configable
    private static List<IExceptionHandler> exceptionHandlers;

    /**
     * 异常代码
     */
    @Configable
    private static String exceptionCode;

    /**
     * 异常级别
     */
    @Configable
    private static ExceptionLevel exceptionLevel;

    /**
     * 异常逻辑视图
     */
    @Configable
    private static String exceptionView;

    /**
     * 资源扫描器
     */
    private static IScan scan;

    /**
     * 资源加载器
     */
    private static ResourcePatternResolver resourcePatternResolver;

    /**
     * 类型转换服务
     */
    private static ConversionService conversionService;

    /**
     * 跟踪器
     */
    @Configable
    private static ITracker tracker;

    /**
     * 打印日志时不需要打印的堆栈
     */
    @Configable
    private static List<String> ignoreStacks;

    /**
     * 加密密钥
     */
    @Configable
    private static String encryptKey;

    /**
     * 平台国际化配置的名称
     */
    private static String[] beneform4jLocaleBasenames;

    /**
     * 默认缓存名称
     */
    @Configable
    private static String defaultCacheName;

    /**
     * 缓存管理器
     */
    @Configable
    private static CacheManager cacheManager;

    /**
     * 对象工厂
     */
    @Configable
    private static IObjectFactory objectFactory;

    /**
     * 数据访问者工厂
     */
    @Configable
    private static IDataAccessorFactory dataAccessorFactory;

    /**
     * 实现InitializingBean接口
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 配置值有效性校验
        this.validate();
        // Spring相关的组件初始化
        if (null != applicationContext) {
            try {
                initSpringComponents();
            } catch (Exception ignore) {
            }

            if (null == objectFactory) {
                SpringObjectFactory sof = new SpringObjectFactory();
                sof.setApplicationContext(applicationContext);
                objectFactory = sof;
            }
        }

        // 默认国际化对象，一般用于操作系统的默认语言和期望的语言不一致的情况
        String defaultLocale = getDefaultLocale();
        if (!CoreUtils.isBlank(defaultLocale)) {
            try {
                Locale.setDefault(LocaleUtils.toLocale(defaultLocale));
            } catch (Exception e) {
                Throw.throwRuntimeException(ExceptionCodes.BF010010, e, defaultLocale);
            }
        }
    }

    /**
     * Spring相关的组件初始化
     */
    protected void initSpringComponents() {
        // 资源加载器
        if (null == resourcePatternResolver) {
            resourcePatternResolver = applicationContext;
        }
        // 类型转换服务
        if (null == conversionService) {
            try {
                AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
                if (beanFactory instanceof ConfigurableBeanFactory) {
                    conversionService = ((ConfigurableBeanFactory) beanFactory).getConversionService();
                }
                if (null == conversionService) {
                    conversionService = applicationContext.getBean(ConversionService.class);
                }
            } catch (Exception e) {
            }
        }
        // 缓存管理器
        if (null == cacheManager) {
            // 缓存切面
            BeanFactoryCacheOperationSourceAdvisor bean = applicationContext.getBean(CacheManagementConfigUtils.CACHE_ADVISOR_BEAN_NAME, BeanFactoryCacheOperationSourceAdvisor.class);
            // 缓存通知
            Advice advice = bean.getAdvice();
            if (advice instanceof CacheInterceptor) {
                CacheResolver cacheResolver = ((CacheInterceptor) advice).getCacheResolver();
                if (cacheResolver instanceof SimpleCacheResolver) {
                    cacheManager = ((SimpleCacheResolver) cacheResolver).getCacheManager();
                }
            }
        }
    }

    /**
     * 实现ApplicationAware接口
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseConfig.applicationContext = applicationContext;
    }

    /**
     * 校验
     */
    public void validate() {
        // 校验字符集是否支持
        boolean isSupported = false;
        try {
            isSupported = Charset.isSupported(encoding);
        } catch (Exception e) {
        }
        if (!isSupported) {
            Throw.createRuntimeException(ExceptionCodes.BF010002, encoding);
        }

        // 校验日期、时间、日期时间格式
    }

    /**
     * 获取扫描包
     * 
     * @return 扫描包
     */
    public static String getScanPackage() {
        return getValue(scanPackage, "scan_package");
    }

    /**
     * 注入扫描包
     * 
     * @param scanPackage 扫描包，默认值com.forms.beneform4j
     */
    public void setScanPackage(String scanPackage) {
        BaseConfig.scanPackage = scanPackage;
    }

    /**
     * 获取初始化扫描包
     * 
     * @return 初始化扫描包，用于扫描@Init注解的基础包
     */
    public static String getInitScanPackage() {
        return getValue(initScanPackage, "scan_package");
    }

    /**
     * 设置初始化扫描包
     * 
     * @param initScanPackage 初始化扫描包，默认和scanPackage相同
     */
    public void setInitScanPackage(String initScanPackage) {
        BaseConfig.initScanPackage = initScanPackage;
    }

    /**
     * 获取平台默认字符集
     * 
     * @return 字符集
     */
    public static String getEncoding() {
        return getValue(encoding, "encoding");
    }

    /**
     * 设置平台默认字符集
     * 
     * @param encoding 字符集，默认值UTF-8
     */
    public void setEncoding(String encoding) {
        BaseConfig.encoding = encoding;
    }

    /**
     * 获取默认国际化对象
     * 
     * @return 默认国际化对象
     */
    public static String getDefaultLocale() {
        return getValue(defaultLocale, "defaultLocale");
    }

    /**
     * 设置默认国际化对象
     * 
     * @param defaultLocale 默认国际化对象，默认值zh_CN
     */
    public void setDefaultLocale(String defaultLocale) {
        BaseConfig.defaultLocale = defaultLocale;
    }

    /**
     * 获取日期格式
     * 
     * @return 日期格式
     */
    public static String getDateFormat() {
        return getValue(dateFormat, "date_format");
    }

    /**
     * 注入日期格式
     * 
     * @param dateFormat 日期格式，默认值yyyyMMdd
     */
    public void setDateFormat(String dateFormat) {
        BaseConfig.dateFormat = dateFormat;
    }

    /**
     * 获取时间格式
     * 
     * @return 时间格式
     */
    public static String getTimeFormat() {
        return getValue(timeFormat, "time_format");
    }

    /**
     * 注入时间格式
     * 
     * @param timeFormat 时间格式，默认值HH:mm:ss
     */
    public void setTimeFormat(String timeFormat) {
        BaseConfig.timeFormat = timeFormat;
    }

    /**
     * 获取日期时间格式
     * 
     * @return 日期时间格式
     */
    public static String getDatetimeFormat() {
        return getValue(datetimeFormat, "datetime_format");
    }

    /**
     * 注入日期时间格式
     * 
     * @param datetimeFormat 日期时间格式，默认值yyyyMMdd-HH:mm:ss
     */
    public void setDatetimeFormat(String datetimeFormat) {
        BaseConfig.datetimeFormat = datetimeFormat;
    }

    /**
     * 获取应用名称
     * 
     * @return 应用名称
     */
    public static String getAppName() {
        return getValue(appName, "app_name");
    }

    /**
     * 注入应用名称
     * 
     * @param appName 应用名称
     */
    public void setAppName(String appName) {
        BaseConfig.appName = appName;
    }

    /**
     * 获取国际化对象解析器
     * 
     * @return 国际化对象解析器
     */
    public static ILocaleResolver getLocaleResolver() {
        return getComponent(localeResolver, ILocaleResolver.class);
    }

    /**
     * 注入国际化对象解析器
     * 
     * @param localeResolver 国际化对象解析器
     */
    public void setLocaleResolver(ILocaleResolver localeResolver) {
        BaseConfig.localeResolver = localeResolver;
    }

    /**
     * 获取国际化消息解析器
     * 
     * @return 国际化消息解析器
     */
    public static ILocaleMessageResolver getLocaleMessageResolver() {
        return getComponent(localeMessageResolver, ILocaleMessageResolver.class);
    }

    /**
     * 注入国际化消息解析器
     * 
     * @param localeMessageResolver 国际化消息解析器
     */
    public void setLocaleMessageResolver(ILocaleMessageResolver localeMessageResolver) {
        BaseConfig.localeMessageResolver = localeMessageResolver;
    }

    /**
     * 获取扫描接口实现类
     * 
     * @return 扫描器实现类
     */
    public static IScan getScan() {
        return getComponent(scan, IScan.class);
    }

    // public void setScan(IScan scan) {
    // BaseConfig.scan = scan;
    // }

    /**
     * 获取资源解析器
     * 
     * @return 资源解析器
     */
    public static ResourcePatternResolver getResourcePatternResolver() {
        return getComponent(resourcePatternResolver, ResourcePatternResolver.class);
    }

    // public void setResourcePatternResolver(ResourcePatternResolver resourcePatternResolver) {
    // BaseConfig.resourcePatternResolver = resourcePatternResolver;
    // }

    /**
     * 获取Bean访问的包装器
     * 
     * @return Bean访问的包装器
     */
    public static IContextBeanOperateWrapper getBeanOperateWrapper() {
        return getComponent(beanOperateWrapper, IContextBeanOperateWrapper.class);
    }

    // public void setBeanOperateWrapper(IContextBeanOperateWrapper beanOperateWrapper) {
    // BaseConfig.beanOperateWrapper = beanOperateWrapper;
    // }

    /**
     * 获取分页工厂
     * 
     * @return 分页工厂
     */
    public static IPageFactory getPageFactory() {
        return getComponent(pageFactory, IPageFactory.class);
    }

    /**
     * 注入分页工厂
     * 
     * @param pageFactory 分页工厂
     */
    public void setPageFactory(IPageFactory pageFactory) {
        BaseConfig.pageFactory = pageFactory;
    }

    /**
     * 获取堆栈工厂
     * 
     * @return 堆栈工厂
     */
    public static IStackFactory getStackFactory() {
        return getComponent(stackFactory, IStackFactory.class);
    }

    /**
     * 注入堆栈工厂
     * 
     * @param stackFactory 堆栈工厂
     */
    public void setStackFactory(IStackFactory stackFactory) {
        BaseConfig.stackFactory = stackFactory;
    }

    /**
     * 获取日志打印终端
     * 
     * @return 日志打印终端
     */
    public static List<ILogTermination> getLogTerminations() {
        return getComponents(logTerminations, ILogTermination.class);
    }

    /**
     * 注入日志打印终端
     * 
     * @param logTerminations 日志打印终端
     */
    public void setLogTerminations(List<ILogTermination> logTerminations) {
        BaseConfig.logTerminations = logTerminations;
    }

    /**
     * 获取日志DEBUG模式开启的监控器
     * 
     * @return 日志DEBUG模式开启的监控器
     */
    public static IMonitor getLogMonitor() {
        return getComponent(logMonitor, IMonitor.class, "log");
    }

    /**
     * 注入日志DEBUG模式开启的监控器
     * 
     * @param logMonitor 日志DEBUG模式开启的监控器
     */
    public void setLogMonitor(IMonitor logMonitor) {
        BaseConfig.logMonitor = logMonitor;
    }

    /**
     * 获取异常元信息加载器
     * 
     * @return 异常元信息加载器
     */
    public static IExceptionMetaLoader getExceptionMetaLoader() {
        return getComponent(exceptionMetaLoader, IExceptionMetaLoader.class);
    }

    /**
     * 设置异常元信息加载器
     * 
     * @param exceptionMetaLoader 异常元信息加载器
     */
    public void setExceptionMetaLoader(IExceptionMetaLoader exceptionMetaLoader) {
        BaseConfig.exceptionMetaLoader = exceptionMetaLoader;
    }

    /**
     * 获取异常详细堆栈监控器
     * 
     * @return 异常详细堆栈监控器
     */
    public static IMonitor getExceptionMonitor() {
        return getComponent(exceptionMonitor, IMonitor.class, "exception");
    }

    /**
     * 注入异常详细堆栈监控器
     * 
     * @param exceptionMonitor 异常详细堆栈监控器
     */
    public void setExceptionMonitor(IMonitor exceptionMonitor) {
        BaseConfig.exceptionMonitor = exceptionMonitor;
    }

    /**
     * 获取异常处理器列表
     * 
     * @return 异常处理器列表
     */
    public static List<IExceptionHandler> getExceptionHandlers() {
        return getComponents(exceptionHandlers, IExceptionHandler.class);
    }

    /**
     * 注入异常处理器列表
     * 
     * @param exceptionHandlers 异常处理器列表
     */
    public void setExceptionHandlers(List<IExceptionHandler> exceptionHandlers) {
        BaseConfig.exceptionHandlers = exceptionHandlers;
    }

    /**
     * 获取默认异常代码
     * 
     * @return 默认异常代码
     */
    public static String getExceptionCode() {
        return getValue(exceptionCode, "exception_code");
    }

    /**
     * 注入默认异常代码
     * 
     * @param exceptionCode 默认异常代码
     */
    public void setExceptionCode(String exceptionCode) {
        BaseConfig.exceptionCode = exceptionCode;
    }

    /**
     * 获取默认异常级别
     * 
     * @return 默认异常级别
     */
    public static ExceptionLevel getExceptionLevel() {
        return getComponent(exceptionLevel, ExceptionLevel.class);
    }

    /**
     * 注入默认异常级别
     * 
     * @param exceptionLevel 默认异常级别，默认值FATAL
     */
    public void setExceptionLevel(ExceptionLevel exceptionLevel) {
        BaseConfig.exceptionLevel = exceptionLevel;
    }

    /**
     * 获取默认异常视图
     * 
     * @return 默认异常视图
     */
    public static String getExceptionView() {
        return getValue(exceptionView, "exception_view");
    }

    /**
     * 注入默认异常视图
     * 
     * @param exceptionView 默认异常视图，默认值exception
     */
    public void setExceptionView(String exceptionView) {
        BaseConfig.exceptionView = exceptionView;
    }

    /**
     * 获取类型转换服务
     * 
     * @return 类型转换服务
     */
    public static ConversionService getConversionService() {
        return getComponent(conversionService, ConversionService.class);
    }

    // public void setConversionService(ConversionService conversionService) {
    // BaseConfig.conversionService = conversionService;
    // }

    /**
     * 获取上下文环境跟踪器
     * 
     * @return 上下文环境跟踪器
     */
    public static ITracker getTracker() {
        return getComponent(tracker, ITracker.class);
    }

    /**
     * 注入上下文环境跟踪器
     * 
     * @param tracker 上下文环境跟踪器
     */
    public void setTracker(ITracker tracker) {
        BaseConfig.tracker = tracker;
    }

    /**
     * 获取打印日志时不需要打印堆栈的类列表
     * 
     * @return 忽略堆栈
     */
    public static List<String> getIgnoreStacks() {
        return combineValues(ignoreStacks, "ignore_stacks");
    }

    /**
     * 注入忽略堆栈
     * 
     * @param ignoreStacks 忽略堆栈
     */
    public void setIgnoreStacks(List<String> ignoreStacks) {
        if (null != ignoreStacks && !ignoreStacks.isEmpty()) {
            if (null == BaseConfig.ignoreStacks) {
                BaseConfig.ignoreStacks = ignoreStacks;
            } else {
                BaseConfig.ignoreStacks.addAll(ignoreStacks);
            }
        }
    }

    /**
     * 获取加密密钥
     * 
     * @return 加密密钥
     */
    public static String getEncryptKey() {
        return getValue(encryptKey, "encryptKey");
    }

    /**
     * 注入加密密钥
     * 
     * @param encryptKey 加密密钥
     */
    public static void setEncryptKey(String encryptKey) {
        BaseConfig.encryptKey = encryptKey;
    }

    /**
     * 获取平台国际化配置的基本名
     * 
     * @return 平台国际化配置的基本名
     */
    public static String[] getBeneform4jLocaleBasenames() {
        List<String> basenames = null;
        if (null != beneform4jLocaleBasenames && beneform4jLocaleBasenames.length >= 1) {
            basenames = Arrays.asList(beneform4jLocaleBasenames);
        }
        List<String> ss = getValues(basenames, "beneform4j_locale_basenames");
        if (null != ss && !ss.isEmpty()) {
            String[] bs = new String[ss.size()];
            ss.toArray(bs);
            return bs;
        }
        return null;
    }

    // public void setBeneform4jLocaleBasenames(String[] beneform4jLocaleBasenames) {
    // BaseConfig.beneform4jLocaleBasenames = beneform4jLocaleBasenames;
    // }

    /**
     * 获取默认缓存名称
     * 
     * @return 默认缓存名称
     */
    public static String getDefaultCacheName() {
        return defaultCacheName;
    }

    /**
     * 设置默认缓存名称
     * 
     * @param defaultCacheName 默认缓存名称
     */
    public void setDefaultCacheName(String defaultCacheName) {
        BaseConfig.defaultCacheName = defaultCacheName;
    }

    /**
     * 获取缓存管理器
     * 
     * @return 缓存管理器
     */
    public static CacheManager getCacheManager() {
        return getComponent(cacheManager, CacheManager.class);
    }

    /**
     * 设置缓存管理器
     * 
     * @param cacheManager 缓存管理器
     */
    public void setCacheManager(CacheManager cacheManager) {
        BaseConfig.cacheManager = cacheManager;
    }

    /**
     * 获取对象工厂
     * 
     * @return 对象工厂
     */
    public static IObjectFactory getObjectFactory() {
        return getComponent(objectFactory, IObjectFactory.class);
    }

    /**
     * 设置对象工厂
     * 
     * @param objectFactory 对象工厂
     */
    public void setObjectFactory(IObjectFactory objectFactory) {
        BaseConfig.objectFactory = objectFactory;
    }

    /**
     * 获取数据访问器工厂
     * 
     * @return
     */
    public static IDataAccessorFactory getDataAccessorFactory() {
        return ConfigHelper.getComponent(dataAccessorFactory, IDataAccessorFactory.class);
    }

    /**
     * 注入数据访问器工厂
     * 
     * @param dataAccessorFactory
     */
    public void setDataAccessorFactory(IDataAccessorFactory dataAccessorFactory) {
        BaseConfig.dataAccessorFactory = dataAccessorFactory;
    }
}
