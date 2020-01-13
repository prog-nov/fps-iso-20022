package com.forms.ffp.core.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class FFPSecurityUtils
{
	private static Logger _logger = LoggerFactory.getLogger(FFPSecurityUtils.class);

	public static KeyStore loadKeyStore(Resource keystore, String password, String keyStoreType) throws Exception
	{
		KeyStore ks = null;
		InputStream fileInputStream = null;
		try
		{
			if (!keystore.exists())
			{
				throw new FileNotFoundException("Keystore found cannot be found at " + keystore);
			}
			fileInputStream = keystore.getInputStream();
			ks = KeyStore.getInstance(keyStoreType);
			ks.load(fileInputStream, password.toCharArray());
		} catch (Exception e)
		{
			if (_logger.isErrorEnabled())
			{
				_logger.error("Failed to load keystore [{}]", keystore, e);
			}
		} finally
		{
			if (fileInputStream == null)
				return ks;
		}
		IOUtils.closeQuietly(fileInputStream);
		return ks;
	}

	public static KeyStore loadKeyStore(String keystore, String password, String keyStoreType) throws Exception
	{
		Resource r = new DefaultResourceLoader().getResource(keystore);
		return loadKeyStore(r, password, keyStoreType);
	}

	public static SSLContext createSSLContext(Path truststore, String trustStorePwd, String trustStoreType, Path keystore, String keyStorePwd, String keystoreType) throws Exception
	{
		Resource tsRes = new FileSystemResource(truststore.toString());

		if (!tsRes.exists())
		{
			throw new FileNotFoundException(truststore.toString());
		}

		Resource ksRes = new FileSystemResource(keystore.toString());

		if (!ksRes.exists())
		{
			throw new FileNotFoundException(keystore.toString());
		}

		KeyStore ts = loadKeyStore(tsRes, trustStorePwd, trustStoreType);
		KeyStore ks = loadKeyStore(ksRes, keyStorePwd, keystoreType);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, keyStorePwd.toCharArray());
		tmf.init(ts);

		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		return sslContext;
	}
}
