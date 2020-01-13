package com.forms.beneform4j.webapp.portal.memo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.portal.memo.bean.MemoBean;
import com.forms.beneform4j.webapp.portal.memo.form.MemoForm;
import com.forms.beneform4j.webapp.portal.memo.service.IMemoService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 备忘模块<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-27<br>
 */
@Controller
@Scope("request")
@RequestMapping("portal/memo")
public class MemoController {

    @Resource(name = "memoService")
    private IMemoService service;

    /**
     * 获取备忘列表
     * 
     * @param currentDate
     * @return
     */
    @RequestMapping("getMemoList")
    @JsonBody
    public List<MemoBean> getMemoList(@RequestParam(value = "date", required = false) String currentDate, @User String userId) {
        return service.sGetMemoList(currentDate, userId);
    }

    /**
     * 新增备忘
     * 
     * @param memo
     * @param userId
     * @return
     */
    @RequestMapping("addMemoDef")
    @JsonBody
    public int addMemoDef(MemoForm memo, @User String userId) {
        if (null != memo) {
            memo.setUserId(userId);
        }
        return service.sAddMemoDef(memo);
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping("deleteMemoDef")
    @JsonBody
    public int deleteMemoDef(@RequestParam(value = "id", required = true) String id) {
        return service.sDeleteMemo(id);
    }

    /**
     * 更新备忘状态
     * 
     * @param id
     * @return
     */
    @RequestMapping("updateMemoStatus")
    @JsonBody
    public int updateMemoStatus(@RequestParam(value = "id", required = true) String id) {
        return service.sUpdateMemoStatus(id);
    }

}
