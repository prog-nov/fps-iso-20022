package com.forms.beneform4j.web.ajax;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.Cache;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.cache.Caches;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.reflect.method.impl.MethodWrapInvoker;
import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Ajax调用者<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public class AjaxInvoker {

    private static final String AJAX_HANDLER_KEY = "ajaxHandlerKey";
    private static final String AJAX_ID = "ajaxStatusId";
    private static final int STATUS_RUNNING = 1;
    private static final int STATUS_SUCCESS = 0;
    private static final int STATUS_FAILURE = 2;

    private static Cache ajaxStatusCache;
    private static Cache ajaxMethodCache;

    /**
     * 设置Ajax状态
     * 
     * @param id
     */
    @Ajax(id = "setAjaxStatus", args = {AJAX_ID})
    public void setAjaxStatus(String id) {
        ajaxStatusCache.put(id, STATUS_RUNNING);
    }

    /**
     * 查询Ajax状态
     * 
     * @param id
     * @return
     */
    @Ajax(id = "selectAjaxStatus", args = {AJAX_ID})
    public int selectAjaxStatus(String id) {
        int status = ajaxStatusCache.get(id, Integer.class);
        return status;
    }

    /**
     * 删除状态
     * 
     * @param id
     */
    @Ajax(id = "deleteAjaxStatus", args = {AJAX_ID})
    public void delete(String id) {
        ajaxStatusCache.evict(id);
    }

    /**
     * 更新为成功
     * 
     * @param id
     */
    public static void updateSuccess(String id) {
        update(id, STATUS_SUCCESS);
    }

    /**
     * 更新为失败
     * 
     * @param id
     */
    public static void updateFailure(String id) {
        update(id, STATUS_FAILURE);
    }

    /**
     * 清除缓存
     * 
     * @param ajaxMethodCache
     */
    /* package */ static void clear() {
        ajaxStatusCache.clear();
        if (null != ajaxMethodCache) {
            ajaxMethodCache.clear();
        }
    }

    /**
     * 调用Ajax方法
     * 
     * @param req
     * @param resp
     * @return
     */
    /* package */ static Object invokedAjaxMethod(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String key = req.getParameter(AJAX_HANDLER_KEY);
            if (CoreUtils.isBlank(key)) {
                Throw.throwRuntimeException("没有传入Ajax处理方法的ID");
            }
            MethodWrapInvoker ajaxMethod = ajaxMethodCache.get(key, MethodWrapInvoker.class);
            if (null == ajaxMethod) {
                Throw.throwRuntimeException("没有找到ID为" + key + "对应的Ajax处理方法");
            }
            Object rs = ajaxMethod.invoke(new Object[] {}, new RequestParamConvert(req, resp));
            result.put("data", rs);
            result.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("data", Throw.getMessage(e));
        }
        return result;
    }

    /**
     * 初始化Ajax方法
     */
    /* package */ static void initAjaxMethod() {
        if (null == ajaxMethodCache) {
            synchronized (AjaxInvoker.class) {
                if (null == ajaxMethodCache) {
                    Cache cache = Caches.getCache(AjaxInvoker.class, "method");
                    Set<Method> handler = CoreUtils.scanMethods("com.forms", Ajax.class);
                    for (Method h : handler) {
                        try {
                            Ajax ajax = h.getAnnotation(Ajax.class);
                            String key = ajax.id();
                            MethodWrapInvoker old = cache.get(key, MethodWrapInvoker.class);
                            if (null != old) {
                                if (old.getOrder() == ajax.order()) {// 优先级相同
                                    Throw.throwRuntimeException("发现多个@Ajax注解的ID为" + key + "，并且优先级相同，请检查注解，删除重复注解，或者添加优先级以决定注册使用哪个@Ajax");
                                } else if (old.getOrder() > ajax.order()) {// 已解析注解优先级数值大，即优先级别低，覆盖
                                    CommonLogger.info("发现多个@Ajax注解的ID为" + key + "，根据优先级，忽略优先级为" + old.getOrder() + "的注解");
                                    cache.put(key, new MethodWrapInvoker(h, ajax.order(), ajax.args()));
                                } else {
                                    CommonLogger.info("发现多个@Ajax注解的ID为" + key + "，根据优先级，忽略注解" + ajax);
                                }
                            } else {
                                cache.put(key, new MethodWrapInvoker(h, ajax.order(), ajax.args()));
                            }
                        } catch (Exception ignore) {
                            CommonLogger.error("注册Ajax处理方法" + h + "失败", ignore);
                        }
                    }
                    ajaxMethodCache = cache;
                }
            }
        }

        if (null == ajaxStatusCache) {
            synchronized (AjaxInvoker.class) {
                if (null == ajaxStatusCache) {
                    ajaxStatusCache = Caches.getCache(AjaxInvoker.class, "status");
                }
            }
        }
    }

    /**
     * 更新状态
     * 
     * @param id
     * @param status
     */
    private static void update(String id, int status) {
        if (!Tool.CHECK.isBlank(id)) {
            ajaxStatusCache.put(id, status);
        }
    }
}
