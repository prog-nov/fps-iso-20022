package com.forms.batch.job.unit.housekeeping;

import java.util.Map;

import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.job.common.sqlservice.SqlJobService;

public class HouseKeeping extends SqlJobService {

	@Override
	public Map<String, String> dealSqlStr() throws Exception {
		String dayBefore = this.actionElement.elementText("daybefore");
		Map<String, String> retMap = BatchEnvBuilder.getInstance().getParamenterMap();
		retMap.put("dayBefore", dayBefore);
		return retMap;
	}
}
