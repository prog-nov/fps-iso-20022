package com.forms.beneform4j.webapp.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.webapp.common.guider.service.IGuiderService;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsForm;
import com.forms.beneform4j.webapp.systemmanage.news.service.INewsService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 公共处理操作，为公共模块提供后台操作1、业务操作指引； 2、快捷菜单操作处理 3、消息公告处理 4、其它还没有想到的<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    /**
     * 业务操作指引服务类
     */
    @Resource(name = "guiderService")
    private IGuiderService guiderService;

    /**
     * 消息公告服务类
     */
    @Resource(name = "newsService")
    private INewsService newsService;

    /**
     * 界面业务操作指纹处理
     * 
     * @param menuId 传入参数说明 菜单id
     * @return
     * @throws Exception
     */
    @RequestMapping("/leaderViewData")
    public String leaderViewData(String menuId) throws Exception {

        // 获取操作指引内容
        Map<String, String> guiderMap = guiderService.getGuider(menuId);
        WebUtils.setRequestAttr("guider", guiderMap);

        return "common/leader/leaderViewData";
    }

    /**
     * 查看通知详细信息
     * 
     * @param msgId
     * @return
     * @throws Exception
     */
    @RequestMapping("/viewNoteice")
    public ModelAndView viewNoteice(String msgId) throws Exception {

        // 获取通知内容
        NewsForm noteice = newsService.sFind(msgId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/mainframe/portal/noteice");
        mv.addObject("noteice", noteice);

        return mv;
    }

    /**
     * 获取消息数据信息
     * 
     * @param form
     * @param page
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    @RequestMapping("/listNoteiceData")
    @JsonBody
    public List<NewsForm> listDatas(NewsForm form, IPage page) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // 对接收的数据进行转换成对应的接口MAP
        return newsService.sList(form, page);
    }

}
