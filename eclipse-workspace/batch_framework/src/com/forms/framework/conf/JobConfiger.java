package com.forms.framework.conf;

import static com.forms.framework.util.CommonAPI.SIGNAL_POINT;

import java.util.List;

import org.dom4j.Element;

import com.forms.framework.exception.BatchFrameworkException;

/*
 * The JobConfiger object includes job config items:
 *   defined in %JobName%.xml,one job one config file.
 *   include fixed properties and private properties,
 *     for fixed properties,BatchConfiger provides getJobXxx() function for accessing,
 *     for private properties,BatchConfiger provides DOM object directly
 * For all properties above,JobConfiger provides another omnipotence access 
 * function:getElementsByIndex(String p_index).
 * e.g.
 * <job-config>
 *   <private-settings>
 *     <item1>...</item1>
 *     <item2>...</item2>
 *   </private-settings>
 * </job-config>
 * use getElementsByIndex("job-config.private-settings"),
 * we can get a hashmap includes all the "itemx" and it's sub items if exists.
 */
public class JobConfiger extends BaseConfiger
{

	public static final String ELE_JOB_CONFIG = "job-config";

	public static final String ELE_FIXED_SETTINGS = "fixed-settings";

	public static final String ELE_ACTION_FLOW = "action-flow";

	public static final String ELE_ACTION = "action";

	public static final String ELE_ACTION_NAME = "action-name";

	protected String jobId = null;

	protected String runFrequency = null;

	protected String rerunType = null;

	public JobConfiger(String ip_fileName) throws BatchFrameworkException
	{
		super(ip_fileName);
		init();
	}

	public String getJobId()
	{
		return jobId;
	}

	public String getRunFrequency()
	{
		return runFrequency;
	}

	public String getRerunType()
	{
		return rerunType;
	}

	public Element getActionElementsByName(String ip_actName)
			throws BatchFrameworkException
	{
		Element loc_element = null;
		StringBuffer loc_stb = null;
		List<Element> loc_liEles = null;
		String loc_strName = null;

		if (ip_actName == null)
		{
			return null;
		}

		loc_stb = new StringBuffer();
		loc_stb.append(ELE_JOB_CONFIG).append(SIGNAL_POINT).append(
				ELE_ACTION_FLOW).append(SIGNAL_POINT).append(ELE_ACTION);
		loc_liEles = getElementsByPath(loc_stb.toString());
		for (Element loc_ele : loc_liEles)
		{
			loc_strName = loc_ele.elementText(ELE_ACTION_NAME);
			if (ip_actName.equals(loc_strName))
			{
				loc_element = loc_ele;
				break;
			}
		}

		return loc_element;
	}

	private void init() throws BatchFrameworkException
	{
		StringBuffer loc_stb = null;
		List<Element> loc_liEles = null;

		loc_stb = new StringBuffer();
		loc_stb.append(ELE_JOB_CONFIG).append(SIGNAL_POINT).append(
				ELE_FIXED_SETTINGS);
		loc_liEles = getSubElementsByPath(loc_stb.toString());
		jobId = loc_liEles.get(0).getTextTrim();
		if (jobId == null || "".equals(jobId))
		{
			throw new BatchFrameworkException("jobId is null");
		}
		runFrequency = loc_liEles.get(1).getTextTrim();
		rerunType = loc_liEles.get(2).getTextTrim();
	}

}
