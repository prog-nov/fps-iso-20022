package com.forms.beneform4j.webapp.common.favorites.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.webapp.common.favorites.bean.FavoritesBean;
import com.forms.beneform4j.webapp.common.favorites.form.FavoritesForm;
import com.forms.beneform4j.webapp.common.favorites.service.IFavoritesService;
import com.forms.beneform4j.webapp.common.web.WebTool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 快捷菜单<br>
 * Author : zhangjj <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-22<br>
 */
@Controller
@RequestMapping("common/favorites")
public class FavoritesController {

    @Resource(name = "favoritesService")
    private IFavoritesService service;

    /**
     * 新增快捷菜单
     * 
     * @param fav
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(FavoritesForm fav) {
        fav.setUserId(WebTool.getLoginUser().getUserId());
        if (Tool.CHECK.isEmpty(fav.getDisplayIcon())) {
            fav.setDisplayIcon("bf4j-icon-mid-09");
        }
        return service.sInsert(fav);
    }

    /**
     * 获取快捷菜单列表
     * 
     * @param fav
     * @return
     */
    @RequestMapping("list")
    @ListJsonBody
    public List<FavoritesBean> list(FavoritesForm fav) {
        fav.setUserId(WebTool.getLoginUser().getUserId());
        return service.sList(fav);
    }

    /**
     * 快捷菜单修改
     * 
     * @param fav
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(FavoritesForm fav) {
        return service.sUpdate(fav);
    }

    /**
     * 快捷菜单删除
     * 
     * @param keyIds
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "keyId[]") String[] keyIds) {
        return service.sDelete(keyIds);
    }

    /**
     * 获取快捷菜单树
     * 
     * @param fav
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("listMenuTree")
    @ListJsonBody
    public List<Map<String, String>> listMenuTree(FavoritesForm fav) throws UnsupportedEncodingException {
        fav.setUserId(WebTool.getLoginUser().getUserId());
        if (!Tool.CHECK.isEmpty(fav.getMenuName())) {
            fav.setMenuName(URLDecoder.decode(fav.getMenuName(), "UTF-8"));
        } else if (Tool.CHECK.isEmpty(fav.getMenuId()) && !"1".equals(fav.getDisplayType())) {
            fav.setMenuId("0");
        }
        return service.sListMenuTree(fav);
    }

    /**
     * 保存拖拽排序
     * 
     * @param keyIds
     * @return
     */
    @RequestMapping("saveSort")
    @JsonBody
    public int saveSort(@RequestParam(name = "keyIds[]") String[] keyIds) {
        return service.sSaveSort(keyIds);
    }
}
