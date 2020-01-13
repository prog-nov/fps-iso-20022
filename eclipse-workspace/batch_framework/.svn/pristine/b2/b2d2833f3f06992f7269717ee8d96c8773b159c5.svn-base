package com.forms.datapipe.config.valve;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class CaseValveConfigRuleSet
    extends RuleSetBase
{

    @Override
    public void addRuleInstances(Digester digester)
    {
        String pakage = this.getClass().getPackage().getName();
        // root
        String node = "case-valve-config";
        String clazz = pakage + ".CaseValveConfig";
        digester.addObjectCreate(node, clazz);

        // filter-case
        node = "case-valve-config/filter-cases/filter-case";
        clazz = pakage + ".FilterCase";
        digester.addObjectCreate(node, clazz);
        digester.addBeanPropertySetter("case-valve-config/filter-cases/filter-case/name", "name");
        digester.addBeanPropertySetter("case-valve-config/filter-cases/filter-case/forward", "forward");
        digester.addBeanPropertySetter("case-valve-config/filter-cases/filter-case/logic-express", "logicExpress");
        digester.addSetNext(node, "addFilterCase", clazz);

    }

}
