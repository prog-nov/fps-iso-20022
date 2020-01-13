package com.forms.ffp.core.service;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.forms.ffp.core.config.keystore.FFPKeystoreConfig;
import com.forms.ffp.core.config.runtime.PropertiesFile;

@Service
public class FFPKeyStoreSvc
{
	private static FFPKeyStoreSvc instance = null;
	
	public static String PARTICIPANT_KEYSTORE_NAME = "participant.keystore";

	private Map<String, FFPKeystoreConfig> keystoreConfigMap = new HashMap<String, FFPKeystoreConfig>();

	public static FFPKeyStoreSvc getInstance()
	{
		if (instance == null)
		{
			instance = new FFPKeyStoreSvc();
		}
		return instance;
	}

	public FFPKeystoreConfig getKeysotreConfig(String key) throws Exception
	{
		if (keystoreConfigMap.get(key) == null)
		{
			String messageKeystoreFolder = FFPRuntimeConfigSvc.getInstance().getRootConfig().get("config.keystore.message.folder");
			String keystoreCofigFile = FFPRuntimeConfigSvc.getInstance().getRootConfig().get("config.keystore.filename");
			PropertiesFile proFile = FFPRuntimeConfigSvc.getInstance().getPropertiesFile(keystoreCofigFile);
			
			FFPKeystoreConfig config = new FFPKeystoreConfig();

			String filename = proFile.get(key + ".file");
			config.setFilename(proFile.get(key + ".file"));

			Path filepath = FFPRuntimeConfigSvc.getInstance().getConfigFilePath(messageKeystoreFolder + filename);
			config.setFilepath(filepath);
			config.setAlias(proFile.get(key + ".alias"));
			config.setPassword(proFile.get(key + ".password"));
			config.setType(proFile.get(key + ".type"));

			keystoreConfigMap.put(key, config);
		}
		return keystoreConfigMap.get(key);
	}

}