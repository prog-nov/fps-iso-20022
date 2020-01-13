package com.forms.beneform4j.webapp.systemmanage.guide.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.systemmanage.guide.form.GuideForm;
import com.forms.beneform4j.webapp.systemmanage.guide.service.IOperGuideService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数管理控制器<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Controller
@Scope("request")
@RequestMapping("/guide")
public class GuideController {

    @Resource(name = "operGuideService")
    private IOperGuideService guideService;

    /**
     * 获取对应的业务操作指引数据
     * 
     * @return
     */
    @RequestMapping("/listView")
    public String listView() {
        return "system/sysmanager/guide/list";
    }

    /**
     * 获取对应的菜单
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/list")
    @ListJsonBody
    public List<Map<String, Object>> listGuide(String id, String menuName) throws UnsupportedEncodingException {
        /**
         * 1、返回类型定义：返回list对象，list中存放map 2、map的定义：map需要符合key-value的规则如下 id - 菜单id text - 菜单名称
         * icon_cls - 菜单样式 pid - 父菜单id state - 菜单在树上显示的状态（closed ,open） seqno - 菜单序号 attributes -
         * 菜单路径
         */
        if (!Tool.CHECK.isEmpty(menuName)) {
            menuName = URLDecoder.decode(menuName, "UTF-8");
        }
        return guideService.sList(id, menuName);
    }

    /**
     * 跳转到修改页
     * 
     * @param menuId
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEditGuide(String menuId) {
        /**
         * 1、返回类型定义：返回map对象 <br>
         * 2、map的定义：<br>
         * map需要符合key-value的规则如下:<br>
         * <li>menu_id - 菜单</li>
         * <li>id menu_name - 菜单名称</li>
         * <li>guide_title - 指引标题</li>
         * <li>guide_content - 指引内容</li>
         * <li>menu_flag - 是否为叶子菜单</li>
         */

        Map<String, Object> map = new HashMap<String, Object>();
        if (!Tool.CHECK.isEmpty(menuId)) {
            map = guideService.sFindForEdit(menuId);
        }
        WebUtils.setRequestAttr("guider", map);
        return "system/sysmanager/guide/add";
    }

    /**
     * 提交修改
     * 
     * @param form
     * @param userId
     * @return
     */
    @RequestMapping("/edit")
    @JsonBody
    public int editGuide(GuideForm form, @User String userId) {
        form.setModiOper(userId);
        form.setInstOper(userId);
        return guideService.sEdit(form);

    }
}
