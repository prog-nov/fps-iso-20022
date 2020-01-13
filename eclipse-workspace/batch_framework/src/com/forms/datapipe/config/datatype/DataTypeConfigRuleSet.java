package com.forms.datapipe.config.datatype;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class DataTypeConfigRuleSet
    extends RuleSetBase
{

    @Override
    public void addRuleInstances(Digester digester)
    {
        String pakage = this.getClass().getPackage().getName();
        //root
        String node = "datatype-config";
        String clazz = pakage + ".DataTypeConfig";
        digester.addObjectCreate(node, clazz);
        
        //DataType
        node = "datatype-config/datatypes/datatype";
        clazz = pakage + ".DataType";
        digester.addObjectCreate(node, clazz);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "validate-method", "validateMethod");
        digester.addSetProperties(node, "parse-method", "parseMethod");
        digester.addSetProperties(node, "print-method", "printMethod");
        digester.addSetNext(node, "addDataType", clazz);
    }

}
