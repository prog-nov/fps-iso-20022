package com.forms.beneform4j.util.param.enums;

import java.util.List;

import com.forms.beneform4j.util.param.IParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public interface IEnumParam extends IParam {

    /**
     * 获取枚举参数数据项
     * 
     * @return 数据项集合
     */
    public List<IEnumParamItem> getItems();

    /**
     * 获取枚举项
     * 
     * @param code
     * @return
     */
    public IEnumParamItem getItem(String code);
}
