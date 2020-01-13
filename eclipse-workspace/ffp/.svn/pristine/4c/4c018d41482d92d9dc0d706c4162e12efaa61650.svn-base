package com.forms.ffp.core.config.tcp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.forms.ffp.core.config.runtime.PropertiesFile;
import com.forms.ffp.core.service.FFPRuntimeConfigSvc;

public class FFPTcpConfigSvc {
	
	@Autowired
	private FFPRuntimeConfigSvc runtimeConfigSvc;
	private String propertiesFile;
	private PropertiesFile props;
	
	@PostConstruct
	public void init() throws Exception
	{
		try{
			this.props = this.runtimeConfigSvc.getPropertiesFile(this.propertiesFile);
			initTcpManagerConfig();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void initTcpManagerConfig() {
		//TODO 多组tcp数据，进行遍历分配
		
	}

	public String getPropertiesFile()
	{
		return this.propertiesFile;
	}

	public void setPropertiesFile(String propertiesFile)
	{
		this.propertiesFile = propertiesFile;
	}

	public PropertiesFile getProps() {
		return props;
	}

	public void setProps(PropertiesFile props) {
		this.props = props;
	}
	
	
	
	
}
