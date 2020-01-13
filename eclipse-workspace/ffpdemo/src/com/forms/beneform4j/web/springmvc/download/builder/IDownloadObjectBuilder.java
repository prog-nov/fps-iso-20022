package com.forms.beneform4j.web.springmvc.download.builder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 下载对象生成器<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
public interface IDownloadObjectBuilder {

    /**
     * 是否单例
     * 
     * @return
     */
    public boolean isSingleon();

    /**
     * 优先级
     * 
     * @return
     */
    public int getOrder();

    /**
     * 构建类型，不区分大小写
     * 
     * @return
     */
    public String getBuildType();

    /**
     * 创建下载对象
     * 
     * @param model 控制层返回的数据模型
     * @param request 请求对象
     * @param response 响应对象
     * @return
     */
    public Object build(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response);
}
