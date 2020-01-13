package com.forms.beneform4j.webapp.portal.memo.service;

import java.util.List;

import com.forms.beneform4j.webapp.portal.memo.bean.MemoBean;
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
public interface IMemoService {

    /**
     * 获取备忘列表
     * 
     * @param currentDate
     * @param userId
     * @return
     */
    public List<MemoBean> sGetMemoList(String date, String userId);

    /**
     * 新增备忘
     * 
     * @param memo
     * @return
     */
    public int sAddMemoDef(MemoForm memo);

    /**
     * 修改备忘
     * 
     * @param id
     * @return
     */
    public int sUpdateMemo(MemoForm memo);

    /**
     * 删除备忘
     * 
     * @param memo
     * @return
     */
    public int sDeleteMemo(String id);

    /**
     * 更新备忘状态
     * 
     * @param id
     * @return
     */
    public int sUpdateMemoStatus(String id);

}
