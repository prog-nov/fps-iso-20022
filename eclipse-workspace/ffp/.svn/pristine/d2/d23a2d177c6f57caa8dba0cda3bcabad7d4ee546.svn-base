package com.forms.beneform4j.webapp.portal.center.centroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.portal.center.service.ICenterService;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 首页中间展示<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-19<br>
 */
@Controller
@RequestMapping("portal/center")
public class CenterController {

    @Resource(name = "centerService")
    private ICenterService service;

    @RequestMapping("queryCenterShowkData")
    public String queryCenterShowkData() {
        UserInfo user = (UserInfo) WebUtils.getSessionAttr(LoginConst.LOGIN_USER);
        // 待办事项任务展示
        List<Map<String, Object>> taskDataList = service.sListTaskData(user.getUserId(), user.getOrgId());
        WebUtils.setRequestAttr("taskDataList", taskDataList);
        return "system/mainframe/layout/center";
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("queryTaskNum")
    @ListJsonBody
    public List<HashMap> listTaskNode(@RequestParam("taskIds[]") String[] taskIds) {
        UserInfo user = (UserInfo) WebUtils.getSessionAttr(LoginConst.LOGIN_USER);
        return service.sQueryTaskNum(taskIds, user.getUserId(), user.getOrgId());
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("findTaskParam")
    @JsonBody
    public Map findTaskParam(@RequestParam("taskId") String taskId) {
        UserInfo user = (UserInfo) WebUtils.getSessionAttr(LoginConst.LOGIN_USER);
        return service.sFindTaskParam(taskId, user.getUserId(), user.getOrgId());
    }
}
