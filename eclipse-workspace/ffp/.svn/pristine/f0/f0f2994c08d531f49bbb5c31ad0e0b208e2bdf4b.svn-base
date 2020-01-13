package com.forms.beneform4j.webapp.systemmanage.param.enums.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamDataBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamDefBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean.EnumParamTreeBean;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamDataForm;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamDefForm;
import com.forms.beneform4j.webapp.systemmanage.param.enumparam.form.EnumParamTreeForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数维护<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-7<br>
 */
public interface IEnumManagerService {
    /**
     * 查询枚举参数定义列表
     * 
     * @param param
     * @return
     */
    public List<EnumParamDefBean> sListParamDef(EnumParamDefForm form, IPage page);

    /**
     * 查询列表型参数明细
     * 
     * @param param
     * @return
     */
    public List<EnumParamDataBean> sListParamData(EnumParamDataForm form);

    /**
     * 查询树型参数明细
     * 
     * @param param
     * @return
     */
    public List<EnumParamTreeBean> sListParamTree(EnumParamTreeForm form);

    /**
     * 查找树型节点
     * 
     * @param param
     * @return
     */
    public EnumParamTreeBean sFindTreeNode(EnumParamTreeForm form);

    /**
     * 新增树型节点
     * 
     * @param param
     * @return
     */
    public int sInsertTreeNode(EnumParamTreeForm form);

    /**
     * 移动树型节点
     * 
     * @param param
     * @return
     */
    public int sMoveTreeNode(EnumParamTreeForm form);

    /**
     * 更新树型参数值
     * 
     * @param param
     * @return
     */
    public int sUpdateTreeNode(EnumParamTreeForm form);

    /**
     * 删除树型节点
     * 
     * @param param
     * @return
     */
    public int sDeleteTreeNode(EnumParamTreeForm form);

    /**
     * 新增枚举参数定义
     * 
     * @param param
     * @return
     */
    public int sInsertParamDef(EnumParamDefForm form);

    /**
     * 更新枚举参数定义
     * 
     * @param param
     * @return
     */
    public int sUpdateParamDef(EnumParamDefForm form);

    /**
     * 更新列表型参数值
     * 
     * @param param
     * @return
     */
    public int sUpdateListData(List<EnumParamDataForm> list);

    /**
     * 删除枚举参数定义
     * 
     * @param param
     * @return
     */
    public int sDeleteParamDef(String[] paramCodes);

}
