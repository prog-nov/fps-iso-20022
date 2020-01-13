package com.forms.beneform4j.util.param.tree.impl;

import java.util.Map;

import com.forms.beneform4j.util.param.AbstractParamServiceApi;
import com.forms.beneform4j.util.param.common.IParamStore;
import com.forms.beneform4j.util.param.tree.ITreeParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树型枚举参数服务实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public class TreeParamService extends AbstractParamServiceApi<ITreeParam> {

    @Override
    protected void afterLoad(Map<String, ITreeParam> params) {
        IParamStore<ITreeParam> store = super.getStore();
        for (String name : params.keySet()) {
            ITreeParam tree = params.get(name);
            tree.build();
            if (null != store) {
                store.save(name, tree);
            }
        }
    }

    @Override
    protected String getStoreElementName() {
        return ITreeParam.class.getName();
    }
}
