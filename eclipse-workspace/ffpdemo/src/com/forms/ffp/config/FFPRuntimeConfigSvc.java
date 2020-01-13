package com.forms.ffp.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("runtimeConfigSvc")
public class FFPRuntimeConfigSvc
{
	private static Logger _logger = LoggerFactory.getLogger(FFPRuntimeConfigSvc.class);

	@Value("${init.config.folder}")
	private String configFolder;

	private Map<String, IniFile> iniFileStore;
	private Map<String, PropertiesFile> propFileStore;
	private Map<String, Path> configFilePathMap = new HashMap<String, Path>();

	@PostConstruct
	public void checkConfigFolderIfNotExist()
	{
		try
		{
			Path configFolderPath = Paths.get(this.configFolder, new String[0]);

			if (!Files.exists(configFolderPath, new LinkOption[0]))
			{
				throw new Exception(configFolder + " not exists!");
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public IniFile getIniConfigFile(String filename) throws IOException
	{
		IniFile iniFile = null;

		if (this.iniFileStore == null)
		{
			this.iniFileStore = new HashMap<String, IniFile>();
		}

		iniFile = (IniFile) this.iniFileStore.get(filename);

		if (iniFile == null)
		{
			iniFile = new IniFile(getConfigFile(filename));
			this.iniFileStore.put(filename, iniFile);
			this.configFilePathMap.put(filename, iniFile.configFile.toPath());
		}

		return iniFile;
	}

	public PropertiesFile getPropertiesFile(String filename) throws IOException
	{
		PropertiesFile propFile = null;

		if (this.propFileStore == null)
		{
			this.propFileStore = new HashMap<String, PropertiesFile>();
		}

		propFile = (PropertiesFile) this.propFileStore.get(filename);

		if (propFile == null)
		{
			propFile = new PropertiesFile(getConfigFile(filename));
			this.propFileStore.put(filename, propFile);
			this.configFilePathMap.put(filename, propFile.configFile.toPath());
		}

		return propFile;
	}

	public void reloadAllIniFile()
	{
		for (IniFile iniFile : this.iniFileStore.values())
		{
			try
			{
				iniFile.reload();
			} catch (Exception e)
			{
				_logger.error(String.format("Failed to reload ini file [%s]", new Object[] { iniFile.getIniFile().getAbsolutePath() }), e);
			}
		}
	}

	private File getConfigFile(String filename)
	{
		return getConfigFilePath(filename).toFile();
	}

	public Path getConfigFilePath(String filename)
	{
		if (configFilePathMap == null)
			configFilePathMap = new HashMap<String, Path>();

		if (configFilePathMap.get(filename) == null)
		{
			Path path = Paths.get(this.configFolder, new String[] { filename });
			this.configFilePathMap.put(filename, path);
		}
		return configFilePathMap.get(filename);
	}
}
