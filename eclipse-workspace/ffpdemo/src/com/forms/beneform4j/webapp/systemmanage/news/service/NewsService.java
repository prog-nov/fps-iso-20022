package com.forms.beneform4j.webapp.systemmanage.news.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.systemmanage.news.dao.INewsDao;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsForm;
import com.forms.beneform4j.webapp.systemmanage.news.form.NewsLogForm;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 消息服务层，提交后台数据库的操作<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
@Service("newsService")
@Scope("prototype")
public class NewsService implements INewsService {

    @Resource(name = "newsDao")
    private INewsDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NewsForm> sList(NewsForm form, IPage page) {
        return dao.dList(form, page);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sInsert(NewsForm form) throws ParseException {
        iniEffectiveDateAndTime(form);
        return dao.dInsert(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NewsForm sFind(String msgId) throws Exception {
        return dao.dFind(msgId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sUpdate(NewsForm form) throws ParseException {
        iniEffectiveDateAndTime(form);
        return dao.dUpdate(form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sDelete(String[] msgIds) {
        int[] rtn = dao.dDelete(msgIds);
        return rtn.length;
    }

    /**
     * @param form
     * @throws ParseException
     */
    private void iniEffectiveDateAndTime(NewsForm form) throws ParseException {
        if ("3".equals(form.getEffectiveFlag())) {
            SimpleDateFormat dateDF1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            form.setEffectiveStartTime(Tool.DATE.getFormatDate(dateDF1.parse(form.getEffectiveStartDate()), "HHmmss"));
            form.setEffectiveStartDate(Tool.DATE.getFormatDate(dateDF1.parse(form.getEffectiveStartDate()), "yyyyMMdd"));
            form.setEffectiveEndTime(Tool.DATE.getFormatDate(dateDF1.parse(form.getEffectiveEndDate()), "HHmmss"));
            form.setEffectiveEndDate(Tool.DATE.getFormatDate(dateDF1.parse(form.getEffectiveEndDate()), "yyyyMMdd"));
        } else if ("1".equals(form.getEffectiveFlag()) || "2".equals(form.getEffectiveFlag())) {
            calculateDate(form);
        } else {

        }
    }

    /**
     * 计算失效日期
     * 
     * @param form
     * @throws ParseException
     */
    private void calculateDate(NewsForm form) throws ParseException {
        String date = Tool.DATE.getDate();
        String dateNew = "2".equals(form.getEffectiveFlag()) ? Tool.DATE.dateCalculate(date, 0, 0, Integer.valueOf(form.getEffectiveDayCnt())) : Tool.DATE.dateCalculate(date, 0, 0, Integer.valueOf(100 * 365));
        // 设置起始时间
        form.setEffectiveStartDate(date);
        form.setEffectiveStartTime(Tool.DATE.getTime());
        // 设置结束时间
        form.setEffectiveEndDate(dateNew);
        form.setEffectiveEndTime(form.getEffectiveStartTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sGetMsgNum(UserInfo userInfo) {
        return dao.dGetMsgNum(userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NewsForm sGetNewMsg(UserInfo userInfo) {
        NewsForm news = new NewsForm();
        NewsLogForm logForm = new NewsLogForm();
        List<NewsForm> list = dao.dListNewMsg(userInfo);
        if (list != null && list.size() > 0) {
            news = list.get(0);
            logForm.setMsgId(news.getMsgId());
            logForm.setUserId(userInfo.getUserId());
            logForm.setOperDate(Tool.DATE.getDate());
            logForm.setOperTime(Tool.DATE.getTime());
            dao.dInsertNewsLog(logForm);
        }
        return news;
    }
}
