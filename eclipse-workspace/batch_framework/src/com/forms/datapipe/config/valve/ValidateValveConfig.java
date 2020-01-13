package com.forms.datapipe.config.valve;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author cxl
 * 
 */
public class ValidateValveConfig
{
    private String approveForward;
    private String unapproveForward;
    
    private Map<String, ValidateRule> validateRules = new HashMap<String, ValidateRule>();
    private Map<String, JavaValidator> javaValidators = new HashMap<String, JavaValidator>();

    public void addValidateRule(ValidateRule validateRule) {
        validateRules.put(validateRule.getName(), validateRule);
    }

    public Map<String, ValidateRule> getValidateRules() {
        return validateRules;
    }

    public void setValidateRules(Map<String, ValidateRule> validateRules) {
        this.validateRules = validateRules;
    }
    
    public void addJavaValidator(JavaValidator validator) {
    	javaValidators.put(validator.getName(), validator);
    }
    
    public Map<String, JavaValidator> getJavaValidators() {
		return javaValidators;
	}

	public void setJavaValidators(Map<String, JavaValidator> javaValidators) {
		this.javaValidators = javaValidators;
	}

	public String getApproveForward() {
        return approveForward;
    }

    public void setApproveForward(String approveForward) {
        this.approveForward = approveForward;
    }

    public String getUnapproveForward() {
        return unapproveForward;
    }

    public void setUnapproveForward(String unapproveForward) {
        this.unapproveForward = unapproveForward;
    }
}
