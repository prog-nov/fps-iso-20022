package com.forms.beneform4j.webapp.portal.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.webapp.portal.memo.bean.MemoBean;
import com.forms.beneform4j.webapp.portal.memo.form.MemoForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 备忘DAO<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-27<br>
 */
@Repository("memoDao")
public interface IMemoDao {

    /**
     * 获取备忘列表
     * 
     * @param date
     * @param userId
     * @return
     */
    public List<MemoBean> dGetMemoList(@Param("date") String date, @Param("userId") String userId);

    /**
     * 根据月份获取备忘列表
     * 
     * @param month
     * @param userId
     * @return
     */
    public List<MemoBean> dGetMemoListByMonth(@Param("month") int month, @Param("userId") String userId);

    /**
     * 根据Id获取备忘
     * 
     * @param id
     * @return
     */
    public MemoBean dGetMemoById(@Param("id") String id);

    /**
     * 新增备忘
     * 
     * @param memo
     * @return
     */
    public int dAddMemoDef(MemoForm memo);

    /**
     * 更新备忘
     * 
     * @param memo
     * @return
     */
    public int dUpdateMemoDef(MemoForm memo);

    /**
     * 删除备忘
     * 
     * @param id
     * @return
     */
    public int dDeleteMemoDef(@Param("id") String id);

    /**
     * 新增备忘
     * 
     * @param memo
     * @return
     */
    public int dAddMemoLog(MemoForm memo);

    /**
     * 删除备忘日志
     * 
     * @param logId
     * @return
     */
    public int dDeleteMemoLog(@Param("logId") String logId);

}
