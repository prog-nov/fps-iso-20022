package com.forms.beneform4j.webapp.systemmanage.menulocale.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfLocaleBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfMenuBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.dao.IMenuLocaleDao;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfMenuAllFrom;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单国际化<br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
@Service("menuLocaleService")
public class MenuLocaleService implements IMenuLocaleService {

    @Resource(name = "menuLocaleDao")
    private IMenuLocaleDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BfMenuBean> sLoadChildren(int menuId) {
        return dao.dLoadChildren(menuId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BfLocaleBean> sListLocaleBean() {
        return dao.dListLocaleBean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sSaveLocaleMenu(BfMenuAllFrom form) {
        int[] rs = dao.dSaveLocaleMenu(form);
        return rs[0];
    }
}
