package com.forms.beneform4j.webapp.systemmanage.menulocale.form;

import java.io.Serializable;
import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : <br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
public class BfMenuForm implements Serializable {

    private static final long serialVersionUID = 3991904061280309117L;

    /**
     * 菜单ID
     */
    private int menuId;

    private List<BfMenuLocaleForm> localeMenus;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public List<BfMenuLocaleForm> getLocaleMenus() {
        return localeMenus;
    }

    public void setLocaleMenus(List<BfMenuLocaleForm> localeMenus) {
        this.localeMenus = localeMenus;
    }

}
