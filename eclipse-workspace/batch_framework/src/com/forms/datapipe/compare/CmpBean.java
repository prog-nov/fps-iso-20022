package com.forms.datapipe.compare;

import java.util.List;
import java.util.Map;

/**
 * bean for compare
 * @author ahnan
 *
 */
public class CmpBean 
{
	private String useMapping;                        // mapping
	
	private String configName;                        // mappingname
	
	public List<Map<String, String>> mappings = null; // field

	public List<Map<String, String>> getMappings() {
		return mappings;
	}

	public void setMappings(List<Map<String, String>> mappings) {
		this.mappings = mappings;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getUseMapping() {
		return useMapping;
	}

	public void setUseMapping(String useMapping) {
		this.useMapping = useMapping;
	}
}
