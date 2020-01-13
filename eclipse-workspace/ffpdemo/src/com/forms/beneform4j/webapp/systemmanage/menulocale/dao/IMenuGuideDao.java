package com.forms.beneform4j.webapp.systemmanage.menulocale.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfGuideBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfGuideForm;

/**
 * Copy Right Information : Forms Syntron <br> 
 * Project : 四方精创 Java EE 开发平台<br> 
 * Description : 菜单国际化操作指引DAO  <br> 
 * Author : XGP <br> 
 * Version : 1.0.0 <br> 
 * Since : 1.0.0 <br> 
 * Date : 2016-12-29 <br> 
 */

@Repository
public interface IMenuGuideDao {
	
	/**
	 * 
	 * 菜单国际化操作指引接口
	 */
	public List<BfGuideBean> dLoadGuideChildren(int menuId);
	/**
	 * 
	 * 提交保存数据
	 * 菜单国际化操作指引接口
	 */
	@Executes({
		@Execute(sqlRef=@SqlRef("dDeleteMenuGuide"), param=@BatchParam(false)),
		@Execute(sqlRef=@SqlRef("dInsertMenuGuide"), param=@BatchParam(item="guide", property="guides"))
	})
	public int[] dSaveGuideMenu(BfGuideForm form);
	
	
}