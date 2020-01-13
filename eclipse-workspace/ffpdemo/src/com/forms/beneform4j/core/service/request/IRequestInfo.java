package com.forms.beneform4j.core.service.request;

import java.util.Date;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IRequestInfo {

    /**
     * 返回唯一请求号
     * 
     * @return 唯一请求号，一般为当前环境中跟踪号
     */
    public String getRequestId();

    /**
     * 获取请求Url
     * 
     * @return 请求URL
     */
    public String getRequestUrl();

    /**
     * 获取协议
     * 
     * @return 请求协议
     */
    public String getProtocol();

    /**
     * 获取远程IP
     * 
     * @return 远程IP
     */
    public String getRemoteIp();

    /**
     * 获取远程操作系统
     * 
     * @return 操作系统
     */
    public String getRemoteOperateSystem();

    /**
     * 获取远程浏览器
     * 
     * @return 浏览器
     */
    public String getRemoteBrowser();

    /**
     * 获取请求参数
     * 
     * @return 请求参数
     */
    public Map<String, Object> getParameters();

    /**
     * 返回请求日期
     * 
     * @return 请求日期
     */
    public Date getDate();

    /**
     * 返回请求时间
     * 
     * @return 请求时间
     */
    public long getTime();
}
