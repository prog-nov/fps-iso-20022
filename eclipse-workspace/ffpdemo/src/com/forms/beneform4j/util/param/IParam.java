package com.forms.beneform4j.util.param;

import java.io.Serializable;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9<br>
 */
public interface IParam extends Serializable {

    /**
     * 获取参数代码
     * 
     * @return 参数代码
     */
    public String getParamCode();

    /**
     * 获取参数名称
     * 
     * @return 参数名称
     */
    public String getParamName();

    /**
     * 获取参数属性 SINGLE 单值参数 LIST 列表型枚举参数 TREE 树型枚举参数
     * 
     * @return 参数属性
     */
    public String getParamAttr();

    /**
     * 是否可编辑
     * 
     * @return
     */
    public boolean isEditable();

    /**
     * 获取参数组别
     * 
     * @return 参数组别
     */
    public String getParamGroup();

    /**
     * 获取序号
     * 
     * @return 序号
     */
    public int getSeqno();

    /**
     * 获取描述
     * 
     * @return 描述
     */
    public String getDes();
}
