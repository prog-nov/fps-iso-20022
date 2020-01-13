package com.forms.beneform4j.webapp.systemmanage.menulocale.form;

import java.io.Serializable;
import java.util.ArrayList;
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
public class BfMenuAllFrom implements Serializable {

    private static final long serialVersionUID = 5429419827655803426L;

    /**
     * 菜单列表
     */
	private List<BfMenuForm> menus;

    public List<BfMenuForm> getMenus() {
        return menus;
    }

    public void setMenus(List<BfMenuForm> menus) {
        this.menus = menus;
    }

    public List<BfMenuLocaleForm> getLocaleMenus() {

        List<BfMenuForm> menus = getMenus();
        if (null != menus && !menus.isEmpty()) {
            List<BfMenuLocaleForm> localeMenus = new ArrayList<BfMenuLocaleForm>();
            for (BfMenuForm permType : menus) {
                if (null != permType.getLocaleMenus()) {
                    localeMenus.addAll(permType.getLocaleMenus());
                }
            }
            return localeMenus;
        }
        return null;

    }

}
