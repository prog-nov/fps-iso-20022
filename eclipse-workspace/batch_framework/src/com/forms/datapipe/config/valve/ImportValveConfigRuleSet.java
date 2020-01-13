package com.forms.datapipe.config.valve;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class ImportValveConfigRuleSet extends RuleSetBase
{

	@Override
	public void addRuleInstances(Digester digester)
	{
		String pakage = this.getClass().getPackage().getName();
		// root
		String node = "import-valve-config";
		String clazz = pakage + ".ImportValveConfig";
		digester.addObjectCreate(node, clazz);

		node = "import-valve-config/field-mappings/mapping";
		clazz = pakage + ".ImportFieldMapping";
		digester.addObjectCreate(node, clazz);
		digester.addSetProperties(node, "input-field", "inputField");
		digester.addSetProperties(node, "pipe-field", "pipeField");
		digester.addSetNext(node, "addFieldMapping", clazz);
	}

}
