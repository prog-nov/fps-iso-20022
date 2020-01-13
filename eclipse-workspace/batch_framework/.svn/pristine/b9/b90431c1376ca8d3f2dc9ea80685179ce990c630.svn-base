package com.forms.datapipe.compare;

import java.util.List;
import java.util.Map;

import com.forms.framework.util.StringUtil;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.output.FixedFileOutput;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.Output;

/**
 * compareutil
 * @author ahnan
 * createDate:2011-04-16
 * updateDate:2011-04-18
 */
public class CmpXMLUtil 
{
	public static boolean dataCompare(List<CmpBean> ip_cmpList, OutputContext ip_out, PipeData ip_pipeData) throws DataPipeException
	{
		CmpBean loc_bean = ip_cmpList.get(0);
		Output loc_o = null;
		for (Output loc_output : ip_out.getOutputs().values())
		{
			if (loc_output instanceof FixedFileOutput)              
			{
				loc_o = loc_output;
				break;
			}
		}
		
		if(loc_o == null)
			throw new DataPipeException("we can not found the output config file");
		
		FieldDefinition loc_fd = loc_o.getFieldDefinition();           
		
		if (loc_bean == null)
			throw new DataPipeException("we can not found the config file");
        List<Field> loc_list = loc_fd.getFields();
        String loc_data1 = "";
        String loc_data2 = "";
        
        if (loc_bean.getUseMapping() != null && !loc_bean.getUseMapping().equals(""))
        {
        	for (Field loc_f : loc_list)
            {
            	loc_data1 = (String) ip_pipeData.getPipeFieldValue(loc_f.getName());
            	loc_data2 = (String) ip_pipeData.getPipeFieldValue(loc_f.getName() + loc_bean.getUseMapping().toUpperCase());
            	if (loc_data1 != null)
            		loc_data1 = loc_data1.trim();
            	if (loc_data2 != null)
            		loc_data2 = loc_data2.trim();
            	
            	if (!StringUtil.compareString(loc_data1, loc_data2))
            		return true;
            }
        } else
        {
        	for (Map<String, String> loc_map : loc_bean.getMappings())
        	{
        		loc_data1 = (String) ip_pipeData.getPipeFieldValue(loc_map.get("inputName"));
        		loc_data2 = (String) ip_pipeData.getPipeFieldValue(loc_map.get("outputName"));
        		if (loc_data1 != null)
            		loc_data1 = loc_data1.trim();
            	if (loc_data2 != null)
            		loc_data2 = loc_data2.trim();
            	
            	if (!StringUtil.compareString(loc_data1, loc_data2))
            		return true;
        	}
        }
        
		return false;
	}
}
