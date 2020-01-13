package com.forms.beneform4j.webapp.systemmanage.param.enums.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
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
 * Description : 枚举参数维护数据访问层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-7<br>
 */
@Repository("enumParamManagerDao")
public interface IEnumParamManagerDao {

    /**
     * 获取枚举参数定义列表
     * 
     * @param param
     * @return
     */
    public List<EnumParamDefBean> dListParamDef(EnumParamDefForm form, IPage page);

    /**
     * 获取列表型参数值列表
     * 
     * @param param
     * @return
     */
    public List<EnumParamDataBean> dListParamData(EnumParamDataForm form);

    /**
     * 获取树型参数值列表
     * 
     * @param param
     * @return
     */
    public List<EnumParamTreeBean> dListParamTree(EnumParamTreeForm form);

    /**
     * 新增枚举参数定义
     * 
     * @param param
     * @return
     */
    public int dInsertParamDef(EnumParamDefForm form);

    /**
     * 修改枚举参数定义
     * 
     * @param param
     * @return
     */
    public int dUpdateParamDef(EnumParamDefForm form);

    /**
     * 修改列表型参数值
     * 
     * @param param
     * @return
     */
    @Executes({@Execute(sqlRef = @SqlRef("dDeleteParamListForUpdate"), param = @BatchParam(item = "data")), @Execute(sqlRef = @SqlRef("dInsertParamList"), param = @BatchParam(item = "data"))})
    public int[] dUpdateListData(List<EnumParamDataForm> list);

    /**
     * 查找树型节点
     * 
     * @param form
     * @return
     */
    public EnumParamTreeBean dFindTreeNode(EnumParamTreeForm form);

    /**
     * 新增树型节点
     * 
     * @param form
     * @return
     */
    public int dInsertTreeNode(EnumParamTreeForm form);

    /**
     * 修改树型节点
     * 
     * @param form
     * @return
     */
    public int dUpdateTreeNode(EnumParamTreeForm form);

    /**
     * 移动树型节点
     * 
     * @param form
     * @return
     */
    public int dMoveTreeNode(EnumParamTreeForm form);

    /**
     * 删除树型节点
     * 
     * @param form
     * @return
     */
    public int dDeleteTreeNode(EnumParamTreeForm form);

    /**
     * 删除一组枚举参数
     * 
     * @param param
     * @return
     */
    @Executes({@Execute(sqlRef = @SqlRef("dDeleteParamList"), param = @BatchParam(item = "paramCode")), @Execute(sqlRef = @SqlRef("dDeleteParamTree"), param = @BatchParam(item = "paramCode")), @Execute(sqlRef = @SqlRef("dDeleteParamDef"), param = @BatchParam(item = "paramCode"))})
    public int[] dDeleteParamDef(String[] paramCodes);

}
