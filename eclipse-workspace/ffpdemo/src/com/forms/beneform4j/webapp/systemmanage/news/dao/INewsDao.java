package com.forms.beneform4j.webapp.systemmanage.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsForm;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsLogForm;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 消息的数据库操作类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-17<br>
 */
@Repository("newsDao")
@Scope("prototype")
public interface INewsDao {

    /**
     * 列表
     * 
     * @param form
     * @param page
     * @return
     */
    public List<NewsForm> dList(@Param("news") NewsForm form, IPage page);

    /**
     * 新增
     * 
     * @param form
     * @return
     */
    public int dInsert(@Param("news") NewsForm form);

    /**
     * 查找
     * 
     * @param msgId
     * @return
     */
    public NewsForm dFind(@Param("msgId") String msgId);

    /**
     * 更新
     * 
     * @param form
     * @return
     */
    public int dUpdate(@Param("news") NewsForm form);

    /**
     * 删除
     * 
     * @param msgIds
     * @return
     */
    public int[] dDelete(@BatchParam(item = "msgId") String[] msgIds);

    /**
     * 获取消息数量
     * 
     * @param userInfo
     * @return
     */
    public String dGetMsgNum(@Param("user") UserInfo userInfo);

    /**
     * 消息列表
     * 
     * @param userInfo
     * @return
     */
    public List<NewsForm> dListNewMsg(@Param("user") UserInfo userInfo);

    /**
     * 新增消息日志
     * 
     * @param logForm
     */
    public void dInsertNewsLog(NewsLogForm logForm);

}
