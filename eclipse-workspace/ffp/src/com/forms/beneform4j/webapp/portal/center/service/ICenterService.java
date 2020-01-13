package com.forms.beneform4j.webapp.portal.center.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 首页展示接口<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-21<br>
 */
public interface ICenterService {

    /**
     * 获取首页代办任务数据
     * 
     * @return
     */
    public List<Map<String, Object>> sListTaskData(String userId, String orgId);

    @SuppressWarnings("rawtypes")
    public List<HashMap> sQueryTaskNum(String[] taskIds, String userId, String orgId);

    @SuppressWarnings("rawtypes")
    public Map sFindTaskParam(String taskId, String userId, String orgId);

}
