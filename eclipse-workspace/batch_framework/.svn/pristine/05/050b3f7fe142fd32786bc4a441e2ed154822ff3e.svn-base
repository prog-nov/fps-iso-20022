package com.forms.framework.job.common.backupservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.StringUtil;
import com.forms.framework.BatchBaseJob;

/**
 * backup JOBBASE class
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public abstract class BackupBaseJob extends BatchBaseJob
{
	protected List<Map<String, String>> para = new ArrayList<Map<String, String>>();

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws BatchJobException
	{
		super.init();
		Element loc_paras = this.actionElement.element("backup-list");
		if (loc_paras == null)
		{
			throw new BatchJobException(
					"backupJob Null configer for backup action parameters in job configer");
		}
		List<Element> loc_backupList = loc_paras.elements("backup");
		List<Element> loc_fileParaList = null;
		for (Element loc_e : loc_backupList)
		{
			Map<String, String> loc_backupParameter = new HashMap<String, String>();
			loc_backupParameter.put("acDate", this.batchAcDate);			
			loc_fileParaList = loc_e.elements();
			for (Element loc_ee : loc_fileParaList)
			{
				loc_backupParameter.put(StringUtil.xmlToJavaLow(loc_ee.getName()), loc_ee.getText());
			}
			para.add(loc_backupParameter);
		}

	}

}
