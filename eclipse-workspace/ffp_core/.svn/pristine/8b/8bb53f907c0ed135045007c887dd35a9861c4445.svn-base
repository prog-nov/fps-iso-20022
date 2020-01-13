package com.forms.ffp.core.config.runtime;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesFile extends ConfigFile<String>
{
	public static final String PLACEHOLDER_PREFIX = "${";
	public static final String PLACEHOLDER_SUFFIX = "}";
	public static final String VALUE_SEPARATOR = ":";
	private PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", true);
	private Properties props;

	public PropertiesFile(String filePath) throws IOException
	{
		super(filePath);
		reload();
	}

	public PropertiesFile(File propertiesFile) throws IOException
	{
		super(propertiesFile);
		reload();
	}

	public void reload() throws IOException
	{
		clearCache();

		Resource resource = new FileSystemResource(this.configFile);
		this.props = PropertiesLoaderUtils.loadProperties(resource);

		for (Object key : this.props.keySet())
		{
			String keyStr = key.toString();
			put(keyStr, this.helper.replacePlaceholders(this.props.getProperty(keyStr), props));
		}
	}

	public String get(String key)
	{
		return (String) super.get(key);
	}

	public Set<String> keySet()
	{
		return super.keySet();
	}

	public Boolean getBoolean(String key)
	{
		return get(key) != null ? Boolean.valueOf(get(key)) : false;
	}

	public Integer getInteger(String key)
	{
		return get(key) != null ? Integer.valueOf(get(key)) : 0;
	}
}
