package com.forms.ffp.core.config.runtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniFile extends ConfigFile<Map<String, String>>
{
	private Pattern sectionPattern = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
	private Pattern keyValuePattern = Pattern.compile("\\s*([^=]*)=(.*)");

	public IniFile(String filePath) throws IOException
	{
		super(filePath);
		reload();
	}

	public IniFile(File iniFile) throws IOException
	{
		super(iniFile);
		reload();
	}

	public void reload() throws IOException
	{
		clearCache();
		BufferedReader br = null;

		try
		{
			br = new BufferedReader(new FileReader(this.configFile));
			String line = null;
			String currSection = null;
			while ((line = br.readLine()) != null)
			{
				Matcher m = this.sectionPattern.matcher(line);
				if (m.matches())
				{
	
					currSection = m.group(1).trim();
					if (!hasKey(currSection))
					{
						put(currSection, new HashMap<String, String>());
	
					}
	
				} else if (currSection != null)
				{
					m = this.keyValuePattern.matcher(line);
					if (m.matches())
					{
						String k = m.group(1).trim();
						String v = m.group(2).trim();
	
						((Map<String, String>) get(currSection)).put(k, v);
					}
				}
			}
		}
		catch(IOException e)
		{
			throw e;
		}
		finally
		{
			if(br != null) br.close();
		}
	}

	public String get(String section, String key)
	{
		Map<String, String> map = (Map<String, String>) get(section);
		if (map != null)
		{
			return (String) map.get(key);
		}
		return null;
	}

	public Boolean getBoolean(String section, String key)
	{
		return Boolean.valueOf(get(section, key));
	}

	public Integer getInteger(String section, String key)
	{
		return Integer.valueOf(Integer.parseInt(get(section, key)));
	}

	public File getIniFile()
	{
		return this.configFile;
	}
}
