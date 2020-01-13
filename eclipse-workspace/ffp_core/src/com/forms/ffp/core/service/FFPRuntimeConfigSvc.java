package com.forms.ffp.core.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.forms.ffp.core.config.runtime.IniFile;
import com.forms.ffp.core.config.runtime.PropertiesFile;

public class FFPRuntimeConfigSvc
{
	private static Logger _logger = LoggerFactory.getLogger(FFPRuntimeConfigSvc.class);
	
	private static FFPRuntimeConfigSvc instance = null;

	private static String FFP_CONFIG_FLODER_KEY = "init.config.folder";
	
	private PropertiesFile rootPro;
	private String rootConfigFolder;

	private Map<String, IniFile> iniFileStore;
	private Map<String, PropertiesFile> propFileStore;
	private Map<String, Path> configFilePathMap = new HashMap<String, Path>();

	public static FFPRuntimeConfigSvc getInstance() throws Exception
	{
		if(instance == null)
		{
			instance = new FFPRuntimeConfigSvc();
		}
		return instance;
	}
	
	public FFPRuntimeConfigSvc() throws Exception
	{
		try
		{
			ClassPathResource classPathResource = new ClassPathResource("ffpconfig.properties");
			rootPro = new PropertiesFile(classPathResource.getFile());
			
			this.rootConfigFolder = rootPro.get(FFP_CONFIG_FLODER_KEY);
			Path configFolderPath = Paths.get(this.rootConfigFolder, new String[0]);

			if (!Files.exists(configFolderPath, new LinkOption[0]))
			{
				throw new Exception(rootConfigFolder + " not exists!");
			}
		}
		catch(Exception e)
		{
			_logger.error(e.getMessage());
			throw e;
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
			this.configFilePathMap.put(filename, iniFile.getConfigFile().toPath());
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
			this.configFilePathMap.put(filename, propFile.getConfigFile().toPath());
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
			Path path = Paths.get(this.rootConfigFolder, new String[] { filename });
			this.configFilePathMap.put(filename, path);
		}
		return configFilePathMap.get(filename);
	}
	
	public PropertiesFile getRootConfig()
	{
		return rootPro;
	}
}
