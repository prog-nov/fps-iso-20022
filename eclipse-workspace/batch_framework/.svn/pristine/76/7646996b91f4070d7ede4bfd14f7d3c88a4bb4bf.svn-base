package com.forms.framework.conf;

import static com.forms.framework.util.CommonAPI.ENCODING_UTF_8;
import static com.forms.framework.util.CommonAPI.SIGNAL_POINT;
import static com.forms.framework.util.CommonAPI.SPLIT_IND_POINT;
import static com.forms.framework.util.CommonAPI.STRING_FALSE;
import static com.forms.framework.util.CommonAPI.STRING_TRUE;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.util.EncryptUtil;
import com.forms.framework.util.ResourceUtil;

/*
 * The SysConfiger object includes system config items:
 *   defined in batch-config.xml,
 *   all are fixed properties,
 *   SysConfiger provides getSysXxx() function for accessing.
 * For all properties,BatchConfiger provides another omnipotence access 
 * function:getElementsByIndex(String p_index).
 * e.g.
 * <sys-config>
 *   <transfers>
 *     <ftp>...</ftp>
 *     <ftp>...</ftp>
 *   </transfers>
 * </sys-config>
 * use getElementsByIndex("sys-config.transfers"),
 * we can get a hashmap includes all the "ftpx",include it's sub items if exists.
 */

public class SysConfiger extends BaseConfiger
{

	// root
	public static final String ELE_SYSTEM_CONFIG = "system-config";

	// globle
	public static final String ELE_GLOBLE = "globle";

	public static final String ELE_DEFAULT_DATABASE = "default-database";

	// database-list
	public static final String ELE_DATABASE_LIST = "database-list";

	public static final String ELE_DATABASE = "database";

	public static final String ELE_DATABASE_NAME = "database-name";

	public static final String ELE_URL = "url";

	public static final String ELE_DRIVER = "driver";

	public static final String ELE_USERNAME = "username";

	public static final String ELE_PASSWORD = "password";

	public static final String ELE_DEFAULT_SCHEMA = "default-schema";

	// transfer-list
	public static final String ELE_TRANSFER_LIST = "transfer-list";

	public static final String ELE_TRANSFER = "transfer";

	public static final String ELE_TRANSFER_NAME = "transfer-name";

	public static final String ELE_TRANSFER_PROTOCOL = "transfer-protocol";

	public static final String ELE_HOST_IP = "host-ip";

	public static final String ELE_HOST_PORT = "host-port";

	public static final String ELE_USER = "user";

	public static final String ELE_DEFAULT_LOCAL_PATH = "default-local-path";

	public static final String ELE_DEFAULT_REMOTE_PATH = "default-remote-path";

	// attribute
	public static final String ATTR_COMMENT = "comment";

	public static final String ATTR_ENCRYPTED = "encrypted";

	public static final String[] ENCRYPT_FILEDS = new String[] {
			"system-config.database-list.database.password",
			"system-config.transfer-list.transfer.password" };

	public SysConfiger(String ip_fileName) throws BatchFrameworkException
	{
		super(ip_fileName);
	}

	/**
	 * get<globle>element.
	 * 
	 * @return <globle>element.
	 * @throws BatchFrameworkException
	 */
	public Element getGlobleElement() throws BatchFrameworkException
	{
		Element loc_element = null;
		StringBuffer loc_stb = null;
		List<Element> loc_liEles = null;
		int loc_iSize = 0;

		loc_stb = new StringBuffer();
		loc_stb.append(ELE_SYSTEM_CONFIG).append(SIGNAL_POINT).append(ELE_GLOBLE);
		loc_liEles = getElementsByPath(loc_stb.toString());
		loc_iSize = loc_liEles.size();
		if (loc_iSize == 1)
		{
			loc_element = loc_liEles.get(0);
		}
		if (loc_iSize > 1)
		{
			throw new BatchFrameworkException("Duplicate GLOBLE elements.");
		}

		return loc_element;
	}

	/**
	 * get<database>element by name.
	 * 
	 * @param ip_dbName
	 *            <database>element name.
	 * @return <database>element.
	 * @throws BatchFrameworkException
	 */
	public Element getDatabaseElementByName(String ip_dbName)
			throws BatchFrameworkException
	{
		Element loc_element = null;
		StringBuffer loc_stb = null;
		List<Element> loc_liEles = null;
		Element loc_elePassword = null;
		String loc_strName = null;

		if (ip_dbName == null)
		{
			return null;
		}

		loc_stb = new StringBuffer();
		loc_stb.append(ELE_SYSTEM_CONFIG).append(SIGNAL_POINT).append(
				ELE_DATABASE_LIST).append(SIGNAL_POINT).append(ELE_DATABASE);
		loc_liEles = getElementsByPath(loc_stb.toString());
		for (Element loc_ele : loc_liEles)
		{
			loc_strName = loc_ele.elementText(ELE_DATABASE_NAME);
			if (ip_dbName.equals(loc_strName))
			{
				loc_element = loc_ele;
				break;
			}
		}

		loc_elePassword = loc_element.element(ELE_PASSWORD);
		if (loc_elePassword != null)
		{
			loc_elePassword.setText(decrypt(loc_elePassword.getTextTrim()));
		}
		return loc_element;
	}

	/**
	 * get<database> properties by name
	 * 
	 * @param ip_dbName
	 * 
	 * @return database properties
	 * @throws BatchFrameworkException
	 */
	public Properties getDatabasePropertiesByName(String ip_dbName)
			throws BatchFrameworkException
	{
		Element loc_dbEle = getDatabaseElementByName(ip_dbName);
		Properties loc_prop = null;
		if (loc_dbEle.elementText("property-resource") == null)
		{
			loc_prop = new Properties();
			loc_prop.put("databaseName", loc_dbEle.elementText("database-name"));
			loc_prop.put("url", loc_dbEle.elementText("url"));
			loc_prop.put("driverClassName", loc_dbEle.elementText("driver"));
			loc_prop.put("username", loc_dbEle.elementText("username"));
			loc_prop.put("password", loc_dbEle.elementText("password"));
			loc_prop.put("defaultSchema", loc_dbEle.elementText("default-schema"));
		} else
		{
			try
			{
				ResourceUtil loc_resourceManager = ResourceUtil.getInstance();
				loc_prop = (Properties) loc_resourceManager.getResource(loc_dbEle
						.elementText("property-resource"), ResourceUtil.RESOURCE_PROPERTIES_TYPE);
				loc_prop.put("databaseName", loc_dbEle.elementText("database-name"));
				loc_prop.put("password", decrypt(loc_prop.getProperty("password")));
			} catch (Exception ex)
			{
				throw new BatchFrameworkException(ex);
			}
		}
		return loc_prop;
	}

	/**
	 * get<transfer>element by name.
	 * 
	 * @param ip_tranName
	 *            <transfer>element name.
	 * @return <transfer>element.
	 * @throws BatchFrameworkException
	 */
	public Element getTransferElementByName(String ip_tranName)
			throws BatchFrameworkException
	{
		Element loc_element = null;
		StringBuffer loc_stb = null;
		List<Element> loc_liEles = null;
		Element loc_elePassword = null;
		String loc_strName = null;

		if (ip_tranName == null)
		{
			return null;
		}

		loc_stb = new StringBuffer();
		loc_stb.append(ELE_SYSTEM_CONFIG).append(SIGNAL_POINT).append(
				ELE_TRANSFER_LIST).append(SIGNAL_POINT).append(ELE_TRANSFER);
		loc_liEles = getElementsByPath(loc_stb.toString());
		for (Element loc_ele : loc_liEles)
		{
			loc_strName = loc_ele.elementText(ELE_TRANSFER_NAME);
			if (ip_tranName.equals(loc_strName))
			{
				loc_element = loc_ele;
				break;
			}
		}

		loc_elePassword = loc_element.element(ELE_PASSWORD);
		if (loc_elePassword != null)
		{
			loc_elePassword.setText(decrypt(loc_elePassword.getTextTrim()));
		}

		return loc_element;
	}

	/**
	 * get<transfer> properties by name
	 * 
	 * @param ip_tranName
	 * 
	 * @return <transfer> properties
	 * @throws BatchFrameworkException
	 */
	public Properties getTransferPropertiesByName(String ip_tranName)
			throws BatchFrameworkException
	{
		Element loc_tranEle = getTransferElementByName(ip_tranName);
		Properties loc_prop = null;
		if (loc_tranEle.elementText("property-resource") == null)
		{
			loc_prop = new Properties();
			loc_prop.put("transferName", loc_tranEle.elementText("transfer-name"));
			loc_prop.put("transferProtocol", loc_tranEle
					.elementText("transfer-protocol"));
			loc_prop.put("hostIp", loc_tranEle.elementText("host-ip"));
			loc_prop.put("hostPort", loc_tranEle.elementText("host-port"));
			loc_prop.put("user", loc_tranEle.elementText("user"));
			loc_prop.put("password", loc_tranEle.elementText("password"));
			loc_prop.put("defaultLocalInputPath", loc_tranEle
					.elementText("default-local-input-path"));
			loc_prop.put("defaultLocalOutputPath", loc_tranEle
					.elementText("default-local-output-path"));
			loc_prop.put("defaultRemoteInputPath", loc_tranEle
					.elementText("default-remote-input-path"));
			loc_prop.put("defaultRemoteOutputPath", loc_tranEle
					.elementText("default-remote-output-path"));
		} else
		{
			try
			{
				ResourceUtil loc_resourceManager = ResourceUtil.getInstance();
				loc_prop = (Properties) loc_resourceManager.getResource(loc_tranEle
						.elementText("property-resource"), ResourceUtil.RESOURCE_PROPERTIES_TYPE);
				loc_prop.put("transferName", loc_tranEle.elementText("transfer-name"));
				if (loc_prop.getProperty("password") != null)
				{
					loc_prop.put("password", decrypt(loc_prop.getProperty("password")));
				}
			} catch (Exception ex)
			{
				throw new BatchFrameworkException(ex);
			}
		}
		return loc_prop;
	}

	public boolean encrypt() throws BatchFrameworkException
	{
		boolean loc_bResult = false;
		Document loc_doc = null;
		Element loc_eleRoot = null;
		List<Element> loc_liPa = null;
		List<Element> loc_liCh = null;
		String[] loc_arrEleNames = null;
		String loc_strRootName = null;
		String loc_strEncrypted = null;
		EncryptUtil loc_encUtil = null;
		byte[] loc_arrKey = null;
		byte[] loc_arrSrc = null;
		byte[] loc_arrEnc = null;
		String loc_strSrc = null;
		String loc_strEnc = null;
		StringBuffer loc_stbDest = null;

		if (ENCRYPT_FILEDS == null || ENCRYPT_FILEDS.length < 1)
		{
			throw new BatchFrameworkException("Required element path.");
		}

		loc_eleRoot = loadRootElement();
		loc_doc = loc_eleRoot.getDocument();

		for (String loc_strElePath : ENCRYPT_FILEDS)
		{
			if (loc_strElePath == null)
			{
				continue;
			}

			// splite element path.
			if (loc_strElePath.indexOf(SIGNAL_POINT) < 0)
			{
				loc_arrEleNames = new String[] { loc_strElePath };
			} else
			{
				loc_arrEleNames = loc_strElePath.split(SPLIT_IND_POINT);
			}

			loc_liPa = new ArrayList<Element>();
			loc_strRootName = loc_eleRoot.getName();
			if (loc_strRootName.equals(loc_arrEleNames[0]))
			{
				loc_liPa.add(loc_eleRoot);
				for (int i = 1, n = loc_arrEleNames.length; i < n; i++)
				{
					for (Element ele : loc_liPa)
					{
						loc_liCh = new ArrayList<Element>();
						for (Object obj : ele.elements(loc_arrEleNames[i]))
						{
							loc_liCh.add((Element) obj);
						}
						if (loc_liPa.isEmpty())
						{
							break;
						}
						if (i == n - 1)
						{
							for (Element loc_eleCh : loc_liCh)
							{
								loc_strEncrypted = loc_eleCh
										.attributeValue(ATTR_ENCRYPTED);
								if (STRING_FALSE.equalsIgnoreCase(loc_strEncrypted))
								{
									// source.
									loc_strSrc = loc_eleCh.getTextTrim();
									try
									{
										loc_arrSrc = loc_strSrc
												.getBytes(ENCODING_UTF_8);
									} catch (UnsupportedEncodingException e)
									{
										e.printStackTrace();
										throw new BatchFrameworkException(e);
									}
									// encrypt.
									loc_encUtil = new EncryptUtil();
									// get key.
									loc_arrKey = loc_encUtil.generateKey();
									// scr.
									loc_arrEnc = loc_encUtil.encode(loc_arrSrc, loc_arrKey);
									loc_strEnc = loc_encUtil.byte2hex(loc_arrEnc);
									// record key .
									loc_stbDest = new StringBuffer();
									loc_stbDest.append(loc_strEnc);
									loc_eleCh.addAttribute(ATTR_ENCRYPTED,
											STRING_TRUE);
									loc_eleCh.setText(loc_stbDest.toString());
									loc_bResult = true;
								}
							}
						}
						loc_liPa = loc_liCh;
					}
				}
			}
		}

		if (loc_bResult)
		{
			writeDocument(fileName, loc_doc);
		}

		return loc_bResult;
	}

	public String decrypt(String ip_input) throws BatchFrameworkException
	{
		String loc_strPassword = null;
		EncryptUtil loc_encUtil = null;
		byte[] loc_arrKey = null;
		byte[] loc_arrEnc = null;
		byte[] loc_arrDec = null;

		if (ip_input == null || ip_input.length() < 1)
		{
			throw new BatchFrameworkException("Required input string.");
		}
		loc_encUtil = new EncryptUtil();
		loc_arrKey = loc_encUtil.generateKey();
		loc_arrEnc = loc_encUtil.hex2byte(ip_input);
		loc_arrDec = loc_encUtil.decode(loc_arrEnc, loc_arrKey);
		loc_strPassword = new String(loc_arrDec);

		return loc_strPassword;
	}

	public static void main(String[] args) throws Exception
	{
		SysConfiger loc_conf = null;
		Element loc_eleGloble = null;
		Element loc_eleDb = null;
		Element loc_eleTran = null;
		boolean loc_bool = false;

		if (args.length < 1)
		{
			throw new Exception("Required config file name.");
		}
		loc_conf = new SysConfiger(args[0]);
		loc_eleGloble = loc_conf.getGlobleElement();
		System.out.println(loc_eleGloble.asXML());
		loc_eleDb = loc_conf.getDatabaseElementByName("dbtest");
		System.out.println(loc_eleDb.asXML());
		loc_eleTran = loc_conf.getTransferElementByName("cm2Sftp");
		System.out.println(loc_eleTran.asXML());
		loc_bool = loc_conf.encrypt();
		System.out.println("Has been encrypted : " + loc_bool);
	}

}
