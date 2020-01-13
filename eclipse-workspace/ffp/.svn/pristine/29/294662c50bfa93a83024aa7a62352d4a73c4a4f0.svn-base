package com.forms.beneform4j.webapp.systemmanage.param.single.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.webapp.systemmanage.param.single.bean.SingleParamBean;
import com.forms.beneform4j.webapp.systemmanage.param.single.form.SingleParamForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 单值参数维护数据访问层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Repository("singleParamManagerDao")
public interface ISingleParamManagerDao {

    /**
     * 获取系统参数列表
     * 
     * @param param
     * @return
     */
    public List<SingleParamBean> queryParamList(@Param("codes") Set<String> codes);

    /**
     * 新增系统参数值配置
     * 
     * @param param
     * @return
     */
    public int addParamValue(SingleParamForm param);

    /**
     * 修改系统参数值配置
     * 
     * @param param
     * @return
     */
    public int updateParamValue(SingleParamForm param);

    /**
     * 删除系统参数值配置
     * 
     * @param param
     * @return
     */
    public int deleteParamValue(SingleParamForm param);

    /**
     * 获取下拉数据
     * 
     * @return
     */
    @MapKey("paramCode")
    public List<SingleParamBean> dGetSingleParamComboData(@Param("codes") Set<String> codes, @Param("param") SingleParamForm param);

}
