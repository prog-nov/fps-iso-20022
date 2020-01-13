package com.forms.beneform4j.webapp.common.favorites.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
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
@Repository("favoritesDao")
public interface IFavoritesDao {

    /**
     * 快捷菜单新增
     * 
     * @param fav
     * @return
     */
    int dInsert(@Param("fav") FavoritesForm fav);

    /**
     * 获取用户快捷菜单最大序号
     * 
     * @param userId
     * @return
     */
    int dGetMaxSeqno(String userId);

    /**
     * 获取快捷菜单列表
     * 
     * @param fav
     * @return
     */
    List<FavoritesBean> dList(FavoritesForm fav);

    /**
     * 更新快捷菜单明细
     * 
     * @param fav
     * @return
     */
    int dUpdate(FavoritesForm fav);

    /**
     * 删除快捷菜单
     * 
     * @param keyIds
     * @return
     */
    @Executes({@Execute(sqlRef = @SqlRef("dDelete"), param = @BatchParam(item = "keyId"))})
    int[] dDelete(String[] keyIds);

    /**
     * 获取菜单树
     * 
     * @param fav
     * @return
     */
    List<Map<String, String>> dListMenuTree(FavoritesForm fav);

    /**
     * 保存拖拽排序
     * 
     * @param fav
     */
    int dUpdateSeqno(FavoritesForm fav);

}
