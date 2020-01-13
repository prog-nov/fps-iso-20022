package com.forms.beneform4j.webapp.systemmanage.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.cache.stat.ICacheInfo;
import com.forms.beneform4j.core.util.cache.stat.ICacheStatService;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存监控管理控制层<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/cache")
public class CacheStatController {

    @Autowired
    private ICacheStatService service;

    /**
     * 查询所有缓存容器列表
     * 
     * @return
     */
    @RequestMapping("list")
    @ListJsonBody
    public List<ICacheInfo> list() {
        return service.sListCacheInfo();
    }

    /**
     * 查找单个缓存及其缓存条目信息
     * 
     * @param cacheName
     * @return
     */
    @RequestMapping("dtl")
    @JsonBody
    public ICacheInfo find(@RequestParam(name = "cacheName") String cacheName) {
        return service.sFindCacheInfo(cacheName);
    }

    /**
     * 清除非平台缓存，如cacheName为平台缓存，抛出异常
     * 
     * @param cacheName
     */
    @RequestMapping("clear")
    @JsonBody
    public int clear(@RequestParam(name = "cacheName") String cacheName) {
        service.clear(cacheName);
        return 0;
    }

    /**
     * 清除所有非平台缓存
     */
    @RequestMapping("clearAll")
    @JsonBody
    public int clearAll() {
        service.clearAll();
        return 0;
    }

    /**
     * 移除缓存条目，如cacheName为平台缓存，抛出异常
     * 
     * @param cacheName
     * @param keys
     */
    @RequestMapping("remove")
    @JsonBody
    public int remove(@RequestParam(name = "cacheName") String cacheName, @RequestParam(name = "keys[]") String[] keys) {
        service.remove(cacheName, keys);
        return 0;
    }
}
