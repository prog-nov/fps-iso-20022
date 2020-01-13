package com.forms.datapipe.input.header;

import java.util.HashMap;
import java.util.Map;

import com.forms.datapipe.Input;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.framework.env.BatchEnvBuilder;

public class CsvFileInputFooterHandler implements FileInputFooterHandler {

	public Map<String, String> handle(Input input, PipeContext pipeContext,
			String string) throws DataPipeException {
		if (!string.startsWith("|||||Begin")) {
			throw new DataPipeException(
					"Error Footer, Not Begin with '|||||Begin'");
		}
		String loc_footInfoArray[] = string.split("\\\\r\\\\n");
		Map<String, String> loc_footInfoMap = new HashMap<String, String>();
		for (int loc_i = 0; loc_i < loc_footInfoArray.length; loc_i++) {
			String[] loc_fields = loc_footInfoArray[loc_i].substring(
					loc_footInfoArray[loc_i].indexOf("|||||") + 5).split("=");
			if (!loc_fields[0].startsWith("Begin")
					&& !loc_fields[0].startsWith("End"))
				loc_footInfoMap.put(loc_fields[0], loc_fields[1] == null ? ""
						: loc_fields[1].trim());
		}
		try {
			if (!loc_footInfoMap.get("DataEndDate").equals(
					BatchEnvBuilder.getInstance().getParamenter("acDate"))) {
				throw new DataPipeException("Error Input File DataEndDate : "
						+ loc_footInfoMap.get("DataEndDate")
						+ ", System AcDate : "
						+ BatchEnvBuilder.getInstance().getParamenter("acDate"));
			}
		} catch (Exception ip_e) {
			throw new DataPipeException(ip_e);
		}
		return loc_footInfoMap;
	}
}
