package com.forms.ffp.msg.iclfps.bussiness;

import java.util.LinkedHashMap;
import java.util.Map;

public class FFPMsgFormContextBean
{
	private String keystore;
	private String keyAlias;
	private Map<String, FFPBaseBussinessBean> formBeanMap;
	private String fileName;
	private String fileFullName;
	private String hyperlink;
	private String createDate;
	private String lastModifiedDate;
	private String stateKey;

	public String getKeystore()
	{
		return this.keystore;
	}

	public void setKeystore(String keystore)
	{
		this.keystore = keystore;
	}

	public String getKeyAlias()
	{
		return this.keyAlias;
	}

	public void setKeyAlias(String keyAlias)
	{
		this.keyAlias = keyAlias;
	}

	public Map<String, FFPBaseBussinessBean> getFormBeanMap()
	{
		return this.formBeanMap;
	}

	public void setFormBeanMap(Map<String, FFPBaseBussinessBean> formBeanMap)
	{
		this.formBeanMap = formBeanMap;
	}

	public String getStateKey()
	{
		return this.stateKey;
	}

	public void setStateKey(String stateKey)
	{
		this.stateKey = stateKey;
	}

	public void putFormBean(String key, FFPBaseBussinessBean bean)
	{
		if (this.formBeanMap == null)
		{
			this.formBeanMap = new LinkedHashMap();
		}

		this.formBeanMap.put(key, bean);
	}

	public FFPBaseBussinessBean getFormBean(String key)
	{
		if (this.formBeanMap == null)
		{
			return null;
		}
		return (FFPBaseBussinessBean) this.formBeanMap.get(key);
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileFullName()
	{
		return fileFullName;
	}

	public void setFileFullName(String fileFullName)
	{
		this.fileFullName = fileFullName;
	}

	public String getHyperlink()
	{
		return hyperlink;
	}

	public void setHyperlink(String hyperlink)
	{
		this.hyperlink = hyperlink;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getLastModifiedDate()
	{
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}
	
	
}
