package com.forms.beneform4j.core.service.spring.schema.common.definition.impl;

import org.springframework.context.ApplicationContext;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.service.spring.schema.common.definition.IMergeDefinition;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用ref实现的用来存储可合并配置的元素定义<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public abstract class ReferenceMergeDefinition implements IMergeDefinition {

    private String ref;

    @Override
    public Object getMergeDefinitionOrBean() {
        ApplicationContext applicationContext = SpringHelp.getApplicationContext();
        if (null != applicationContext && null != ref && !"".equals(ref.trim())) {
            Object bean = applicationContext.getBean(ref.trim());
            return doMerge(bean);
        } else {
            return this;
        }
    }

    protected Object doMerge(Object refBean) {
        return refBean;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
