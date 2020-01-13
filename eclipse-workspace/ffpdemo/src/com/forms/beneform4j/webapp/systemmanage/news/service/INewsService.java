package com.forms.beneform4j.webapp.systemmanage.news.service;

import java.text.ParseException;
import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsForm;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 消息维护操作服务接口<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
public interface INewsService {

    /**
     * 取消息列表
     * 
     * @param form
     * @param page
     * @return
     */
    List<NewsForm> sList(NewsForm form, IPage page);

    /**
     * 新增
     * 
     * @param form
     * @return
     * @throws ParseException
     */
    int sInsert(NewsForm form) throws ParseException;

    /**
     * 查找单个
     * 
     * @param msgId
     * @return
     * @throws Exception
     */
    NewsForm sFind(String msgId) throws Exception;

    /**
     * 更新
     * 
     * @param form
     * @return
     * @throws ParseException
     */
    int sUpdate(NewsForm form) throws ParseException;

    /**
     * 删除
     * 
     * @param msgIds
     * @return
     */
    int sDelete(String[] msgIds);

    /**
     * 获取个人未读消息总数
     * 
     * @param userInfo
     * @return
     */
    String sGetMsgNum(UserInfo userInfo);

    /**
     * 获取未读消息
     * 
     * @param userId
     * @return
     */
    NewsForm sGetNewMsg(UserInfo userInfo);

}
