package com.forms.datapipe.config.valve;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class ExportValveConfigRuleSet extends RuleSetBase
{

	@Override
	public void addRuleInstances(Digester digester)
	{
		String pakage = this.getClass().getPackage().getName();
		// root
		String node = "export-valve-config";
		String clazz = pakage + ".ExportValveConfig";
		digester.addObjectCreate(node, clazz);

		// field-mappings
		node = "export-valve-config/field-mappings/mapping";
		clazz = pakage + ".ExportFieldMapping";
		digester.addObjectCreate(node, clazz);
		digester.addSetProperties(node, "pipe-field", "pipeField");
		digester.addSetProperties(node, "output-field", "outputField");
		digester.addSetNext(node, "addFieldMapping", clazz);
	}
}
