package com.forms.beneform4j.webapp.common.favorites.service;

import java.util.List;
import java.util.Map;

import com.forms.beneform4j.webapp.common.favorites.bean.FavoritesBean;
import com.forms.beneform4j.webapp.common.favorites.form.FavoritesForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 快捷菜单<br>
 * Author : zhangjj <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-22<br>
 */
public interface IFavoritesService {

    /**
     * 菜单收藏
     * 
     * @param fav
     * @return
     */
    int sInsert(FavoritesForm fav);

    /**
     * 获取收藏列表
     * 
     * @param fav
     * @return
     */
    List<FavoritesBean> sList(FavoritesForm fav);

    /**
     * 修改快捷菜单明细
     * 
     * @param fav
     * @return
     */
    int sUpdate(FavoritesForm fav);

    /**
     * 批量删除快捷菜单
     * 
     * @param keyIds
     * @return
     */
    int sDelete(String[] keyIds);

    /**
     * 获取菜单树
     * 
     * @param fav
     * @return
     */
    List<Map<String, String>> sListMenuTree(FavoritesForm fav);

    /**
     * 拖拽排序保存
     * 
     * @param menuIds
     * @return
     */
    int sSaveSort(String[] keyIds);
}
