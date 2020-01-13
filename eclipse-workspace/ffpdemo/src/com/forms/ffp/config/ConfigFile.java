package com.forms.ffp.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class ConfigFile<T>
{
	private Map<String, T> cache = new LinkedHashMap<String, T>();

	public abstract void reload() throws IOException;

	public ConfigFile(String filePath) throws IOException
	{
		this.configFile = new File(filePath);

		if (!this.configFile.exists())
			throw new FileNotFoundException(this.configFile.getAbsolutePath());
	}

	protected File configFile;

	public ConfigFile(File configFile) throws IOException
	{
		this.configFile = configFile;

		if (!this.configFile.exists())
		{
			throw new FileNotFoundException(this.configFile.getAbsolutePath());
		}
	}

	private Map<String, T> getCacheMap()
	{
		if (this.cache == null)
		{
			this.cache = new HashMap<String, T>();
		}

		return this.cache;
	}

	protected void clearCache()
	{
		getCacheMap().clear();
	}

	protected T get(String key)
	{
		return (T) getCacheMap().get(key);
	}

	protected void put(String key, T value)
	{
		getCacheMap().put(key, value);
	}

	protected void putAll(Map<String, T> map)
	{
		getCacheMap().putAll(map);
	}

	protected boolean hasKey(String key)
	{
		return getCacheMap().containsKey(key);
	}

	protected Set<String> keySet()
	{
		return getCacheMap().keySet();
	}
}
