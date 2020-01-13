package com.forms.datapipe.config.valve;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class FilterValveConfigRuleSet extends RuleSetBase
{

	@Override
	public void addRuleInstances(Digester digester)
	{
		String pakage = this.getClass().getPackage().getName();
		// root
		String node = "filter-valve-config";
		String clazz = pakage + ".FilterValveConfig";
		digester.addObjectCreate(node, clazz);

		// filter-case
		node = "filter-valve-config/filter-cases/filter-case";
		clazz = pakage + ".FilterCase";
		digester.addObjectCreate(node, clazz);
		digester.addBeanPropertySetter("filter-valve-config/filter-cases/filter-case/name", "name");
		digester.addBeanPropertySetter("filter-valve-config/filter-cases/filter-case/forward", "forward");
		digester.addBeanPropertySetter("filter-valve-config/filter-cases/filter-case/logic-express", "logicExpress");
		digester.addSetNext(node, "addFilterCase", clazz);
	}

}
