package com.forms.beneform4j.webapp.systemmanage.menulocale.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfGuideBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfGuideForm;
import com.forms.beneform4j.webapp.systemmanage.menulocale.service.IMenuGuideService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单操作国际化指引控制层<br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */


@Controller
@Scope("request")
@RequestMapping("system/sysmanager/menulocale")
public class MenuGuideController {

	@Resource
	private IMenuGuideService service;
	
	/**
	 * 
	 * 转向到菜单国际化指引维护页面
	 * 
	 */
	@RequestMapping("view")
	public String listView(){
		return "system/sysmanager/menulocale/view";
	}
	/**
	 * 加载国际化操作指引数据
	 */
	@RequestMapping("guide")
	@ListJsonBody
	public List<BfGuideBean> guide(@RequestParam(name="id", defaultValue="0") int menuId){
		return service.sLoadGuideChildren(menuId);
	}
	
	/**
	 * 
	 * 提交保存菜单国际化维护数据
	 * @return
	 */
	@RequestMapping("saveGuide")
	@JsonBody
	public int saveGuide(BfGuideForm form){
		return service.sSaveGuideMenu(form);
	}
}