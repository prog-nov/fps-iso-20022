package com.forms.datapipe.config.valve;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class ValidateValveConfigRuleSet extends RuleSetBase {

	@Override
	public void addRuleInstances(Digester digester) {
		setRoot(digester);
		addValidateRule(digester);
		addJavaValidator(digester);
	}
	
	private void setRoot(Digester digester) {
		String node = "validate-valve-config";
		digester.addObjectCreate(node, ValidateValveConfig.class);
		digester.addBeanPropertySetter("validate-valve-config/approve-forward", "approveForward");
		digester.addBeanPropertySetter("validate-valve-config/unapprove-forward", "unapproveForward");
	}
	
	private void addValidateRule(Digester digester) {
		String node = "validate-valve-config/validate-rules/validate-rule";
		digester.addObjectCreate(node, ValidateRule.class);
		digester.addBeanPropertySetter(node + "/name", "name");
		digester.addBeanPropertySetter(node + "/logic-express", "logicExpress");
		digester.addSetNext(node, "addValidateRule", ValidateRule.class.getName());
	}
	
	private void addJavaValidator(Digester digester) {
		String node = "validate-valve-config/validate-rules/java-validator";
		digester.addObjectCreate(node, JavaValidator.class);
		digester.addBeanPropertySetter(node + "/name", "name");
		digester.addBeanPropertySetter(node + "/class-name", "className");
		digester.addSetNext(node, "addJavaValidator", JavaValidator.class.getName());
	}

}
