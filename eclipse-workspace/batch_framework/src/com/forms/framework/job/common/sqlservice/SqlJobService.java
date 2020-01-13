package com.forms.framework.job.common.sqlservice;

import java.util.Map;

import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.job.SqlJob;

/**
 * sqlJob base
 * @author ahnan
 * createDate:2011-04-28
 * updateDate:2011-04-28
 */
public class SqlJobService extends SqlJob
{

	@Override
	public Map<String, String> dealSqlStr() throws Exception
	{
		return BatchEnvBuilder.getInstance().getParamenterMap();
	}

}
