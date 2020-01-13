package com.forms.beneform4j.util.param.enums.impl;

import java.util.Map;

import com.forms.beneform4j.util.param.AbstractParamServiceApi;
import com.forms.beneform4j.util.param.common.IParamStore;
import com.forms.beneform4j.util.param.enums.IEnumParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数服务实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public class EnumParamService extends AbstractParamServiceApi<IEnumParam> {

    @Override
    protected String getStoreElementName() {
        return IEnumParam.class.getName();
    }

    @Override
    protected void afterLoad(Map<String, IEnumParam> params) {
        IParamStore<IEnumParam> store = super.getStore();
        if (null != store) {
            for (String name : params.keySet()) {
                store.save(name, params.get(name));
            }
        }
    }
}
