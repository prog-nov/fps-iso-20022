package com.forms.beneform4j.webapp.common.favorites.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.favorites.bean.FavoritesBean;
import com.forms.beneform4j.webapp.common.favorites.dao.IFavoritesDao;
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
@Service("favoritesService")
public class FavoritesService implements IFavoritesService {

    @Resource(name = "favoritesDao")
    IFavoritesDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public int sInsert(FavoritesForm fav) {
        // 检查菜单是否已存在
        if (checkMenuId(fav)) {
            return -9;
        }
        // 设置keyId
        fav.setKeyId(Tool.STRING.getRandomNumericIncludeTime(6));
        // 序号为空则赋默认值为最大序号
        if (Tool.CHECK.isEmpty(fav.getSeqno())) {
            fav.setSeqno(selectMaxSeqno(fav.getUserId()));
        }
        // 新增收藏操作
        return dao.dInsert(fav);
    }

    @Override
    public List<FavoritesBean> sList(FavoritesForm fav) {

        return dao.dList(fav);
    }

    @Override
    public int sUpdate(FavoritesForm fav) {

        return dao.dUpdate(fav);
    }

    @Override
    public int sDelete(String[] keyIds) {

        return dao.dDelete(keyIds)[0];
    }

    @Override
    public List<Map<String, String>> sListMenuTree(FavoritesForm fav) {
        return dao.dListMenuTree(fav);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int sSaveSort(String[] keyIds) {
        int i = 1;
        FavoritesForm fav = new FavoritesForm();
        for (String keyId : keyIds) {
            fav.setKeyId(keyId);
            fav.setSeqno(i + "");
            dao.dUpdateSeqno(fav);
            i++;
        }
        return keyIds.length;
    }

    /**
     * 校验快捷菜单是否已存在
     * 
     * @param fav
     * @return true：存在，false：不存在
     */
    public boolean checkMenuId(FavoritesForm fav) {
        if (Tool.CHECK.isEmpty(fav.getMenuId())) {
            Throw.throwRuntimeException("menuId is null");
        } else {
            List<FavoritesBean> list = dao.dList(fav);
            if (list != null && list.size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取序号
     * 
     * @param userId
     * @return
     */
    public String selectMaxSeqno(String userId) {
        int seqno = dao.dGetMaxSeqno(userId);
        return Tool.CHECK.isEmpty(seqno) ? "1" : (seqno + 1) + "";
    }

}
