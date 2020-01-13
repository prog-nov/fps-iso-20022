package com.forms.beneform4j.webapp.systemmanage.menulocale.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfGuideBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.dao.IMenuGuideDao;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfGuideForm;
/**
 * Copy Right Information : Forms Syntron <br> 
 * Project : 四方精创 Java EE 开发平台<br> 
 * Description : 菜单国际化操作指引  <br> 
 * Author : XGP <br> 
 * Version : 1.0.0 <br> 
 * Since : 1.0.0 <br> 
 * Date : 2016-12-28 <br> 
 */

@Service("menuGuideService")
public class MenuGuideService implements IMenuGuideService{

	@Resource
	private IMenuGuideDao dao;
	
	/**
	 * 菜单国际化操作
	 * 查询数据
	 */
	
	@Override
	public List<BfGuideBean> sLoadGuideChildren(int menuId) {
		return dao.dLoadGuideChildren(menuId);
	}
	
	/**
	 * 菜单国际化操作指引接口 
	 * 提交数据
	 */
	@Override
	public int sSaveGuideMenu(BfGuideForm form) {
		int[] rs = dao.dSaveGuideMenu(form);
		return rs[0];
	}

}
