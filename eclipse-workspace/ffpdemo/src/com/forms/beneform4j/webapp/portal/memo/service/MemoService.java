package com.forms.beneform4j.webapp.portal.memo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.portal.memo.bean.MemoBean;
import com.forms.beneform4j.webapp.portal.memo.dao.IMemoDao;
import com.forms.beneform4j.webapp.portal.memo.form.MemoForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 备忘提醒服务类<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-27<br>
 */
@Service("memoService")
@Scope("prototype")
public class MemoService implements IMemoService {

    @Resource(name = "memoDao")
    private IMemoDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MemoBean> sGetMemoList(String date, String userId) {
        return dao.dGetMemoList(date, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sAddMemoDef(MemoForm memo) {
        return dao.dAddMemoDef(memo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sUpdateMemo(MemoForm memo) {
        return dao.dUpdateMemoDef(memo);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public int sDeleteMemo(String id) {
        dao.dDeleteMemoDef(id);
        dao.dDeleteMemoLog(id);
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sUpdateMemoStatus(String id) {
        MemoBean m = dao.dGetMemoById(id);
        MemoForm f = new MemoForm();
        if (m != null) {
            try {
                BeanUtils.copyProperties(f, m);
            } catch (Exception e) {
                Throw.throwRuntimeException(e);
            }
            if (null != m.getLogId()) {
                // 更新为未完成,删除日志表
                dao.dDeleteMemoLog(m.getLogId());
            } else {
                // 更新为已完成,写入日志表
                m.setLogId(Tool.STRING.getRandomNumeric(32));
                m.setOpDate(Tool.DATE.getDate());
                m.setOpTime(Tool.DATE.getTime());
                dao.dAddMemoLog(f);
            }
            return 1;
        }
        return 0;

    }

}
