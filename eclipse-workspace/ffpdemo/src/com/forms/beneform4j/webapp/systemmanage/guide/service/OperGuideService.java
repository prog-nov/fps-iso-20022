package com.forms.beneform4j.webapp.systemmanage.guide.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.systemmanage.guide.dao.IOperGuideDao;
import com.forms.beneform4j.webapp.systemmanage.guide.form.GuideForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户登录服务层<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
@Service("operGuideService")
@Scope("prototype")
public class OperGuideService implements IOperGuideService {

    @Resource(name = "operGuideDao")
    private IOperGuideDao operGuideDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> sList(String menuId, String menuName) {
        if (Tool.CHECK.isEmpty(menuId) && Tool.CHECK.isEmpty(menuName)) {
            menuId = "0";
        }
        return operGuideDao.dList(menuId, menuName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> sFindForEdit(String menuId) {

        return operGuideDao.dFindForEdit(menuId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sEdit(GuideForm form) {
        int updateCnt = 0;
        form.setInstDate(Tool.DATE.getDate());
        form.setInstTime(Tool.DATE.getTime());
        form.setModiDate(Tool.DATE.getDate());
        form.setModiTime(Tool.DATE.getTime());
        // 查找有没有记录
        Map<String, String> m = sFind(form.getMenuId());
        if (Tool.CHECK.isEmpty(m)) {
            updateCnt = operGuideDao.dInsert(form);
        } else {
            updateCnt = operGuideDao.dEdit(form);
        }
        return updateCnt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> sFind(String menuId) {
        return operGuideDao.dFind(menuId);
    }
}
