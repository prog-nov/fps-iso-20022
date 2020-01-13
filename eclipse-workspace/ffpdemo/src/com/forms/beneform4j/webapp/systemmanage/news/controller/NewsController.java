package com.forms.beneform4j.webapp.systemmanage.news.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.common.web.WebTool;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsForm;
import com.forms.beneform4j.webapp.systemmanage.news.service.INewsService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 消息控制跳转处理类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
@Controller
@Scope("request")
@RequestMapping("/news")
public class NewsController {

    @Resource(name = "newsService")
    private INewsService newsService;

    /**
     * 转向到显示界面
     * 
     * @return
     */
    @RequestMapping("/listView")
    public String listView() {
        return "system/sysmanager/news/list";
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
    @RequestMapping("/listDatas")
    @PageJsonBody
    public List<NewsForm> listDatas(NewsForm form, IPage page) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return newsService.sList(form, page);
    }

    /**
     * 跳转到新增页面
     * 
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAddView() {
        return "system/sysmanager/news/add";
    }

    /**
     * 新增加消息
     * 
     * @param form
     * @param userId
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws ParseException
     */
    @RequestMapping("/add")
    @JsonBody
    public int toAdd(NewsForm form, @User String userId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParseException {
        form.setMsgId(Tool.STRING.getRandomNumeric(16));
        form.setSendOper(userId);
        form.setSendDate(Tool.DATE.getDate());
        form.setSendTime(Tool.DATE.getTime());
        form.setStatus("0");// 未阅读

        return newsService.sInsert(form);
    }

    /**
     * 打开修改页面
     * 
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping("/toEdit")
    public String toEditView(NewsForm form) throws Exception {
        NewsForm bean = newsService.sFind(form.getMsgId());
        WebUtils.setRequestAttr("news", bean);
        return "system/sysmanager/news/edit";
    }

    /**
     * 进行数据修改
     * 
     * @param form
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws ParseException
     */
    @RequestMapping("/edit")
    @JsonBody
    public int toEdit(NewsForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParseException {
        return newsService.sUpdate(form);
    }

    /**
     * 数据删除
     * 
     * @param msgIds
     * @return
     */
    @RequestMapping("/delete")
    @JsonBody
    public int toDelete(@RequestParam(name = "msgIds[]") String[] msgIds) {
        return newsService.sDelete(msgIds);
    }

    /**
     * 打开修改页面
     * 
     * @param msgId
     * @return
     * @throws Exception
     */
    @RequestMapping("/toDetail")
    public String toDetailView(String msgId) throws Exception {
        NewsForm form = newsService.sFind(msgId);
        WebUtils.setRequestAttr("news", form);
        return "system/sysmanager/news/detail";
    }

    /**
     * 获取个人未读消息数
     * 
     * @return
     */
    @RequestMapping("/checkMsgNum")
    @JsonBody
    public String checkMsgNum() {
        return newsService.sGetMsgNum(WebTool.getLoginUser());
    }

    /**
     * 获取未读消息明细（单条）
     * 
     * @return
     */
    @RequestMapping("/checkMsgDetail")
    public String checkMsgDetail() {
        NewsForm form = newsService.sGetNewMsg(WebTool.getLoginUser());
        WebUtils.setRequestAttr("news", form);
        return "system/sysmanager/news/personalNews";
    }
}
