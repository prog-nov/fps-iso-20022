package com.forms.beneform4j.webapp.portal.center.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.core.util.page.PageUtils;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.portal.center.dao.ICenterDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 首页展示服务层<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-21<br>
 */
@Service("centerService")
@Scope("prototype")
public class CenterService implements ICenterService {

    @Resource(name = "centerDao")
    private ICenterDao dao;

    /**
     * 首页展示待办事项任务
     */
    @Override
    public List<Map<String, Object>> sListTaskData(String userId, String orgId) {
        // TODO Auto-generated method stub
        // 取角色
        List<String> rolesList = dao.dFindRoleId(userId);
        // 取任务队列
        List<Map<String, Object>> taskList = dao.dQueryTaskData(userId);
        if (!Tool.CHECK.isEmpty(taskList)) {
            for (int i = 0; i < taskList.size(); i++) {
                Map<String, Object> map = taskList.get(i);
                map.put("idx", (i + 1));
                map.put("idx", (i + 1) < 10 ? "0" + (i + 1) : i + 1);
            }
        }
        // 设置任务数
        this.initTaskNum(taskList, userId, rolesList, orgId);
        return taskList;
    }

    /**
     * 初始化任务数
     * 
     * @param taskList
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void initTaskNum(List<Map<String, Object>> taskList, String userId, List<String> rolesList, String orgId) {
        // TODO Auto-generated method stub
        List<String> taskIdList = null;
        List<HashMap> taskNumList = null;
        HashMap<String, HashMap> tasksMap = null;
        HashMap tmpTaskMap = null;
        HashMap tmpTaskNumMap = null;
        try {
            if (!Tool.CHECK.isEmpty(taskList) && taskList.size() > 0) {
                // 取taskId数组
                taskIdList = new ArrayList<String>();
                for (Map<String, Object> taskMap : taskList) {
                    taskIdList.add((String) taskMap.get("taskId"));
                }
                // 取任务数
                taskNumList = dao.dQueryTaskNum(taskIdList.toArray(), userId, rolesList, orgId);
                tasksMap = new HashMap<String, HashMap>();
                if (!Tool.CHECK.isEmpty(taskNumList) && taskNumList.size() > 0) {
                    for (HashMap taskMap : taskNumList) {
                        tasksMap.put((String) taskMap.get("taskId"), taskMap);
                    }
                    taskNumList.clear();
                }
                // 设置任务数和跳转URL
                for (Map<String, Object> taskMap : taskList) {
                    tmpTaskMap = (HashMap) taskMap;
                    tmpTaskNumMap = (HashMap) tasksMap.get(tmpTaskMap.get("taskId"));
                    if (!Tool.CHECK.isEmpty(tmpTaskNumMap)) {
                        tmpTaskMap.put("taskNum", tmpTaskNumMap.get("taskNum"));
                        tmpTaskMap.put("targetUrl", tmpTaskNumMap.get("targetUrl"));
                    } else {
                        tmpTaskMap.put("taskNum", 0);
                        tmpTaskMap.put("targetUrl", "");
                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 查询待办事项任务数，用户页面更新
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<HashMap> sQueryTaskNum(String[] taskIds, String userId, String orgId) {
        // TODO Auto-generated method stub
        // 取角色
        List<String> rolesList = dao.dFindRoleId(userId);
        return dao.dQueryTaskNum(taskIds, userId, rolesList, orgId);
    }

    /**
     * 查找第1条任务主键和参数
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Map sFindTaskParam(String taskId, String userId, String orgId) {
        // TODO Auto-generated method stub
        List<HashMap> taskNumList = null;
        // 取角色
        List<String> rolesList = dao.dFindRoleId(userId);
        IPage page = PageUtils.createPage();
        List<HashMap> rsMapList = dao.dFindTaskParam(taskId, userId, rolesList, orgId, page);
        HashMap rsMap = null;
        if (Tool.CHECK.isEmpty(rsMapList)) {
            rsMap = new HashMap();
            rsMap.put("taskId", taskId);
            rsMap.put("busiKey", "");
            rsMap.put("params", "");
        } else {
            rsMap = rsMapList.get(0);
            taskNumList = dao.dQueryTaskNum(new Object[] {taskId}, userId, rolesList, orgId);
        }
        if (!Tool.CHECK.isEmpty(taskNumList)) {
            rsMap.put("taskNum", taskNumList.get(0).get("taskNum"));
        } else {
            rsMap.put("taskNum", 0);
        }
        return rsMap;
    }
}
