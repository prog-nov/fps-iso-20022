package com.forms.beneform4j.webapp.common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.JsonField;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;
import com.forms.beneform4j.webapp.common.utils.UIHolder;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgBean;
import com.forms.beneform4j.webapp.systemmanage.org.bean.OrgNode;
import com.forms.beneform4j.webapp.systemmanage.org.dao.IOrgDao;
import com.forms.beneform4j.webapp.systemmanage.org.form.OrgForm;
import com.forms.beneform4j.webapp.systemmanage.org.service.IOrgService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : UI帮助控制层<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-11<br>
 */
@Controller
@Scope("singleton")
@RequestMapping("uiholder")
public class UIHolderController {

    @Resource(name = "orgService")
    private IOrgService service;
    
    @Resource(name = "orgDao")
    private IOrgDao dao;

    /**
     * 获取下拉
     * 
     * @param paramCodes
     * @return
     */
    @RequestMapping("getComboDatas")
    @ListJsonBody
    public List<Object> getComboDatas(@RequestParam("paramCodes") String paramCodes) {
    	List<Object> datas = new ArrayList<Object>();
    	if("ORGANIZATION_ID".equals(paramCodes))
    	{
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("type", "LIST");
            map.put("paramCode", paramCodes);
            
    		List<OrgBean> orgList = dao.dList();
    		if(orgList != null)
    		{
    			List<Map<String, Object>> showdatas = new ArrayList<Map<String, Object>>();
    			for(OrgBean orgBean : orgList)
    			{
    				Map<String, Object> data = new HashMap<String, Object>();
	    			data.put("id", orgBean.getOrgId());
	                data.put("text", orgBean.getOrgName());
	                showdatas.add(data);
    			}
    			map.put("data", showdatas);
    		}
    		datas.add(map);
    	}
    	else
    	{
    		List<String> names = Arrays.asList(paramCodes.split("\\s*,\\s*"));
            List<IParam> params = ParamHolder.getParameters(names);
            if (null != params) {
                for (IParam param : params) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("type", param.getParamAttr());
                    map.put("paramCode", param.getParamCode());
                    map.put("data", UIHolder.convertParam2EasyUIView(param));
                    datas.add(map);
                }
            }
    	}
        return datas;
    }

    /**
     * 获取子机构
     * 
     * @param root
     * @param supOrgId
     * @param minOrgLevel
     * @return
     */
    @RequestMapping("/getOrganChildren")
    @ListJsonBody
    public List<OrgBean> getOrganChildren(@RequestParam(value = "root", required = false) String root, @RequestParam(value = "id", required = false) String supOrgId, @RequestParam(value = "minOrgLevel", required = false, defaultValue = "-1") int minOrgLevel) {
        OrgForm parent = new OrgForm();
        if (Tool.CHECK.isBlank(supOrgId)) {
            if (!Tool.CHECK.isBlank(root)) {
                parent.setOrgId(root);
            } else {
                // 当前用户最大权限机构，暂以根机构代替
                parent.setOrgId(IOrgService.ROOT_ORG_ID);
            }
        } else {
            parent.setSupOrgId(supOrgId);
        }
        parent.setMinOrgLevel(minOrgLevel);
        return service.sGetChildren(parent);
    }

    /**
     * 获取机构树
     * 
     * @param root
     * @param minOrgLevel
     * @return
     */
    @RequestMapping("/getOrganTree")
    @JsonBody(jsonFields = {@JsonField(value = "id", ignore = true), @JsonField(value = "code", alias = "id")})
    public List<ITreeNode> getOrganTree(@RequestParam(value = "root", required = false) String root, @RequestParam(value = "minOrgLevel", required = false, defaultValue = "-1") int minOrgLevel) {
        OrgForm form = new OrgForm();
        if (!Tool.CHECK.isBlank(root)) {
            form.setOrgId(root);
        } else {
            // 当前用户最大权限机构，暂以根机构代替
            form.setOrgId(IOrgService.ROOT_ORG_ID);
        }
        form.setMinOrgLevel(minOrgLevel);
        ITree<OrgNode> tree = service.sLoadTree(form);
        return tree.getRoot().getChildren();
    }
}
