package com.forms.framework.job.common.cmdservice;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.CommonAPI;
import com.forms.framework.util.ExecCmdUtil;
import com.forms.framework.util.PatternUtil;

public class ExecuteCMDService extends BatchBaseJob {

	protected List<String> argsList = new ArrayList<String>();;

	@Override
	public void init() throws BatchJobException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Element loc_parameters = this.actionElement.element("parameters");
			String loc_cmdName = loc_parameters.elementText("cmd-name");
			if (loc_cmdName == null || "".equals(loc_cmdName)) {
				throw new BatchJobException("cmd-name not found in config");
			}
			argsList.add(BatchEnvBuilder.getInstance().getEnv(
					CommonAPI.ENV_BATCH_BIN_PATH)
					+ File.separator + loc_cmdName);
			Element loc_values = loc_parameters.element("values");
			Map<String, String> loc_valuesMap = getValues(loc_values);
			PatternUtil loc_sqlJob = new PatternUtil(this.batchAcDate);
			for (int loc_i = 1; loc_i <= loc_valuesMap.size(); loc_i++) {
				if (loc_valuesMap.containsKey(String.valueOf(loc_i))) {
					String loc_para = loc_valuesMap.get(String.valueOf(loc_i));
					loc_para = loc_sqlJob.patternReplace(BatchEnvBuilder.getInstance().getParamenterMap(), loc_para);
					argsList.add(loc_para);
				} else {
					throw new BatchJobException("value's sequence should be in order from 1 to N");
				}
			}

		} catch (Exception e) {
			throw new BatchJobException(e);
		}
	}

	@Override
	public boolean execute() throws BatchJobException {
		try {
			ExecCmdUtil.execCmd(getParameter());
		} catch (Exception e) {
			throw new BatchJobException(e);
		}
		return false;
	}

	protected String[] getParameter() throws ParseException {
		String[] loc_parameters = new String[argsList.size()];
		argsList.toArray(loc_parameters);
		return loc_parameters;
	}

	@SuppressWarnings("unchecked")
	protected Map<String, String> getValues(Element ip_element) {
		Map<String, String> loc_map = new HashMap<String, String>();
		Iterator<Element> loc_it = ip_element.elementIterator();
		while (loc_it.hasNext()) {
			Element loc_element = loc_it.next();
			loc_map.put(loc_element.attributeValue("index"), loc_element
					.getText());
		}
		return loc_map;
	}

}
