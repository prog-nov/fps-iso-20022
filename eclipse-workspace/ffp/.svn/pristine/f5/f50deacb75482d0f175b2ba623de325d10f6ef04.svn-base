package com.forms.beneform4j.core.service.request.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.forms.beneform4j.core.service.request.IRequestInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求接口实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class BaseRequestInfo implements IRequestInfo {

    /**
     * 唯一请求号
     */
    private String requestId;

    /**
     * 请求Url
     */
    private String requestUrl;

    /**
     * 请求协议
     */
    private String protocol;

    /**
     * 远程IP
     */
    private String remoteIp;

    /**
     * 远程操作系统
     */
    private String remoteOperateSystem;

    /**
     * 远程浏览器
     */
    private String remoteBrowser;

    /**
     * 请求参数
     */
    private Map<String, Object> parameters;

    /**
     * 请求日期
     */
    private Date date;

    /**
     * 请求时间
     */
    private long time;

    /**
     * 返回唯一请求号
     * 
     * @return 唯一请求号，一般为当前环境中跟踪号
     */
    @Override
    public String getRequestId() {
        return this.requestId;
    }

    /**
     * 获取请求Url
     * 
     * @return 请求URL
     */
    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    /**
     * 获取协议
     * 
     * @return 请求协议
     */
    @Override
    public String getProtocol() {
        return this.protocol;
    }

    /**
     * 获取远程IP
     * 
     * @return 远程IP
     */
    @Override
    public String getRemoteIp() {
        return this.remoteIp;
    }

    /**
     * 获取远程操作系统
     * 
     * @return 操作系统
     */
    @Override
    public String getRemoteOperateSystem() {
        return this.remoteOperateSystem;
    }

    /**
     * 获取远程浏览器
     * 
     * @return 浏览器
     */
    @Override
    public String getRemoteBrowser() {
        return this.remoteBrowser;
    }

    /**
     * 获取请求参数
     * 
     * @return 请求参数
     */
    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    /**
     * 返回请求日期
     * 
     * @return 请求日期
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * 返回请求时间
     * 
     * @return 请求时间
     */
    @Override
    public long getTime() {
        return this.time;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public void setRemoteOperateSystem(String remoteOperateSystem) {
        this.remoteOperateSystem = remoteOperateSystem;
    }

    public void setRemoteBrowser(String remoteBrowser) {
        this.remoteBrowser = remoteBrowser;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void addParameters(String key, Object parameter) {
        Map<String, Object> parameters = getParameters();
        if (null == parameters) {
            parameters = new LinkedHashMap<String, Object>();
        }
        parameters.put(key, parameter);
    }

    public void addAllParameters(Map<String, Object> parameters) {
        Map<String, Object> params = this.getParameters();
        if (null == parameters) {
            parameters = new LinkedHashMap<String, Object>();
        }
        params.putAll(parameters);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
