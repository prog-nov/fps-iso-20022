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
public class BfGuideForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5429419827655803426L;
  
    //菜单国际化操作指引数据
  	private List<BfMenuGuideForm> guides;
  	
  	//菜单ID
  	private int menuId;

	public List<BfMenuGuideForm> getGuides() {
		return guides;
	}

	public void setGuides(List<BfMenuGuideForm> guides) {
		this.guides = guides;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


}
