package com.forms.ffp.keystore;

import java.security.Key;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.X509Data;

public class FFPX509KeySelector extends KeySelector
{
	public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException
	{
		Iterator ki = keyInfo.getContent().iterator();
		while (ki.hasNext())
		{
			XMLStructure info = (XMLStructure) ki.next();
			if ((info instanceof X509Data))
			{
				X509Data x509Data = (X509Data) info;
				Iterator xi = x509Data.getContent().iterator();
				while (xi.hasNext())
				{
					Object o = xi.next();
					if ((o instanceof X509Certificate))
					{
						final PublicKey key = ((X509Certificate) o).getPublicKey();
						if (algEquals(method.getAlgorithm(), key.getAlgorithm()))
						{
							return new FFPKeySelectorResult(key);
						}
					}
				}
			}
		}
		throw new KeySelectorException("No key found!");
	}

	public static boolean algEquals(String algURI, String algName)
	{
		if (((algName.equalsIgnoreCase("DSA")) && (algURI.equalsIgnoreCase("http://www.w3.org/2000/09/xmldsig#dsa-sha1"))) || ((algName.equalsIgnoreCase("RSA"))
				&& ((algURI.equalsIgnoreCase("http://www.w3.org/2000/09/xmldsig#rsa-sha1")) || (algURI.equalsIgnoreCase("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256")))))
		{
			return true;
		}
		return false;
	}
	
	class FFPKeySelectorResult implements KeySelectorResult
	{
		private Key key;
		
		FFPKeySelectorResult(Key inkey)
		{
			key = inkey;
		}
		
		@Override
		public Key getKey()
		{
			return key;
		}
		
	}
}
