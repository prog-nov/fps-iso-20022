package com.forms.framework.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.forms.framework.exception.BatchFrameworkException;

//import com.sun.crypto.provider.SunJCE;

public class EncryptUtil
{

	// [ DES | DESede | Blowfish ]
	public static final String ALGORITHM_DES = "DES";

	public static final String ALGORITHM_DESEDE = "DESede";

	public static final String ALGORITHM_BLOWFISH = "Blowfish";

	// [ MD5 | SHA-1 ]
	public static final String ALGORITHM_MD5 = "MD5";

	public static final String ALGORITHM_SHA_1 = "SHA-1";

	private static String specified_algorithm = null;

	/**
	 * default construct
	 */
	public EncryptUtil()
	{
		this(ALGORITHM_DES);
	}

	/**
	 * 
	 * 
	 * <pre>
	 *   1. EncryptUtil.ALGORITHM_DES
	 *   2. EncryptUtil.ALGORITHM_DESEDE
	 *   3. EncryptUtil.ALGORITHM_BLOWFISH
	 * </pre>
	 * 
	 * @param ip_algorithm
	 *            
	 */
	public EncryptUtil(String ip_algorithm)
	{
		specified_algorithm = ALGORITHM_DES;
	}

	/**
	 * 
	 * @return key.
	 * @throws BatchFrameworkException
	 */
	public byte[] generateKey() throws BatchFrameworkException
	{
		return hex2byte("CE9EBF5810B049F4");
	}

	/**
	 * 
	 * @param ip_input
	 *            
	 * @param ip_key
	 * @return 
	 * @throws BatchFrameworkException
	 */
	public byte[] encode(byte[] ip_input, byte[] ip_key)
			throws BatchFrameworkException
	{
		byte[] loc_arrByte = null;
		SecretKey loc_secKey = null;
		Cipher loc_ci = null;
		try
		{
			loc_secKey = new SecretKeySpec(ip_key, specified_algorithm);
			loc_ci = Cipher.getInstance(specified_algorithm);
			loc_ci.init(Cipher.ENCRYPT_MODE, loc_secKey);
			loc_arrByte = loc_ci.doFinal(ip_input);
			return loc_arrByte;
		} catch (GeneralSecurityException ip_e)
		{
			throw new BatchFrameworkException(ip_e);
		}
	}

	/**
	 * 
	 * @param ip_input
	 *           value.
	 * @param ip_key
	 *            key.
	 * @return bytes.
	 * @throws BatchFrameworkException
	 */
	public byte[] decode(byte[] ip_input, byte[] ip_key)
			throws BatchFrameworkException
	{
		byte[] loc_arrByte = null;
		SecretKey loc_secKey = null;
		Cipher loc_ci = null;
		try
		{
			loc_secKey = new SecretKeySpec(ip_key, specified_algorithm);
			loc_ci = Cipher.getInstance(specified_algorithm);
			loc_ci.init(Cipher.DECRYPT_MODE, loc_secKey);
			loc_arrByte = loc_ci.doFinal(ip_input);
			return loc_arrByte;
		} catch (GeneralSecurityException ip_e)
		{
			throw new BatchFrameworkException(ip_e);
		}
	}

	/**
	 * 
	 * <pre>
	 *   1. EncryptUtil.ALGORITHM_MD5
	 *   2. EncryptUtil.ALGORITHM_SHA_1
	 * </pre>
	 * 
	 * @param ip_algorithm
	 *            MD5.
	 * @param ip_input
	 *            value.
	 * @return byte array.
	 * @throws BatchFrameworkException
	 */
	public byte[] md5(String ip_algorithm, byte[] ip_input)
			throws BatchFrameworkException
	{
		byte[] loc_arrByte = null;
		MessageDigest loc_md = null;
		try
		{
			loc_md = MessageDigest.getInstance(ip_algorithm);
			loc_md.update(ip_input);
			loc_arrByte = loc_md.digest();
			return loc_arrByte;
		} catch (NoSuchAlgorithmException ip_e)
		{
			throw new BatchFrameworkException(ip_e);
		}
	}

	/**
	 * 
	 * @param ip_input
	 *            input value.
	 * @return bytes.
	 */
	public String byte2hex(byte[] ip_input)
	{
		StringBuffer loc_stb = null;
		String loc_str = null;

		loc_stb = new StringBuffer();
		for (int i = 0, n = ip_input.length; i < n; i++)
		{
			loc_str = Integer.toHexString(ip_input[i] & 0XFF);
			if (loc_str.length() == 1)
			{
				loc_stb.append("0");
			}
			loc_stb.append(loc_str);
		}
		loc_str = loc_stb.toString();

		return loc_str.toUpperCase();
	}

	/**
	 * 
	 * @param ip_input
	 *            string.
	 * @return byte array.
	 */
	public byte[] hex2byte(String ip_input)
	{
		final int loc_FACTOR = 2;
		byte[] loc_arrByte = null;
		int loc_iLength = 0;
		int loc_iSize = 0;
		int loc_iBegin = 0;
		int loc_iEnd = 0;
		String loc_strSub = null;

		loc_iLength = ip_input.length();
		loc_iSize = (int) Math.ceil(loc_iLength / (double) loc_FACTOR);
		loc_arrByte = new byte[loc_iSize];
		for (int loc_i = 0; loc_i < loc_iSize; loc_i++)
		{
			loc_iBegin = loc_i * loc_FACTOR;
			loc_iEnd = loc_iBegin + loc_FACTOR;
			if (loc_iEnd >= loc_iLength)
			{
				loc_iEnd = loc_iLength;
			}
			loc_strSub = ip_input.substring(loc_iBegin, loc_iEnd);
			loc_arrByte[loc_i] = (byte) Integer.parseInt(loc_strSub, 16);
		}

		return loc_arrByte;
	}

	public static void main(String[] args) throws Exception
	{
		EncryptUtil loc_util = null;
		byte[] loc_arrKey = null;
		byte[] loc_arrSrc = null;
		byte[] loc_arrEnc = null;
		String loc_strAlg = null;
		String loc_strSrc = null;
		String loc_strEnc = null;

		loc_strSrc = "Mysql123!";
		loc_arrSrc = loc_strSrc.getBytes(CommonAPI.ENCODING_UTF_8);
		System.out.println("Source str: " + loc_strSrc);

		loc_strAlg = ALGORITHM_DES;
		loc_util = new EncryptUtil(loc_strAlg);
		loc_arrKey = loc_util.generateKey();
		System.out.println("Generate key: " + loc_util.byte2hex(loc_arrKey));

		loc_arrEnc = loc_util.encode(loc_arrSrc, loc_arrKey);
		loc_strEnc = loc_util.byte2hex(loc_arrEnc);
		System.out.println("Encode str: " + loc_strEnc);
	}

}
