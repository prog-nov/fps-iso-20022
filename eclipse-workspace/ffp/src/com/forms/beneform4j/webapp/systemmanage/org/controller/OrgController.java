package com.forms.beneform4j.webapp.systemmanage.org.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.NoWrapJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgBean;
import com.forms.beneform4j.webapp.systemmanage.org.form.OrgForm;
import com.forms.beneform4j.webapp.systemmanage.org.service.IOrgService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构服务控制层 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9 <br>
 */
@Controller // 注册为Spring容器的Bean
@Scope("request") // 由于机构维护的功能并非频繁使用，这里生命周期使用request，表示每次请求都创建新实例，请求完成后销毁这个实例，如果频繁使用，可使用默认的单列singleon
@RequestMapping("system/sysmanager/org") // 定义机构维护URL的公共部分
public class OrgController {

    // 自动注入service，这里只需要使用服务接口，Spring容器会自动注入实现类
    @Resource(name = "orgService")
    private IOrgService service;

    @RequestMapping("/getChildren")
    @ListJsonBody // 类似于@ResponseBody，返回JSON格式数据
    public List<OrgBean> getChildren(OrgForm parent) {// 使用表单对象接受请求参数
        return service.sGetChildren(parent);
    }

    @RequestMapping("/find")
    @NoWrapJsonBody
    public OrgBean find(@RequestParam("orgId") String orgId) {// 使用@RequestParam注解接受请求参数
        return service.sFind(orgId);
    }

    /**
     * 新增子机构，启用分组校验OrgForm.Insert
     * 
     * @param org 使用表单对象接受请求参数
     * @param userId 使用自定义参数解析器 UserPropertyArgumentResolver 设置参数
     * @return
     */
    @RequestMapping("/insert")
    @JsonBody // 使用MapJsonWrapper包装后，再输出JSON格式
    public int insert(@Validated(OrgForm.Insert.class) OrgForm org, @User String userId) {
        org.setInstOper(userId);
        return service.sInsert(org);
    }

    /**
     * 更新
     * 
     * @param org
     * @param userId
     * @return
     */
    @RequestMapping("/update")
    @JsonBody
    public int update(@Validated(OrgForm.Update.class) OrgForm org, @User String userId) {
        org.setModiOper(userId);
        return service.sUpdate(org);
    }

    /**
     * 移动节点
     * 
     * @param org
     * @return
     */
    @RequestMapping("/move")
    @JsonBody
    public int move(@Validated(OrgForm.Move.class) OrgForm org) {
        return service.sMove(org);
    }

    /**
     * 删除节点
     * 
     * @param org
     * @return
     */
    @RequestMapping("/delete")
    @JsonBody
    public int delete(OrgForm org) {
        return service.sDelete(org);
    }
}
