package com.forms.datapipe.headfoot;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.framework.util.DateUtil;
import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.output.FixedFileOutput4DetailConfig;
import com.forms.datapipe.config.output.FixedFileOutput4MultiConfig;
import com.forms.datapipe.config.output.FixedFileOutputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.valve.FieldConverter;

/**
 * headfoot util
 * 
 * @author ahnan createDate:2011-04-10 updateDate:2011-04-28
 */
public class HeadFootUtil
{
	private static final String headfootStr="HEADFOOT";
	
	/**
	 * replace parameter
	 * 
	 * @param ip_para
	 * @param ip_pipeContext
	 * @return
	 * @throws ParseException
	 */
	public static String parameterReplace(String ip_para, PipeContext ip_pipeContext)
			throws ParseException
	{
		String loc_format = DateUtil.BATCH_DATE_FORMAT;

		String loc_ret = "";
		int loc_index = 0;
		if (ip_para.startsWith("#{sysDate"))
		{
			loc_index = ip_para.indexOf("[");
			if (loc_index != -1)
				loc_format = ip_para.substring(loc_index + 1, ip_para.indexOf("]"));

			loc_ret = DateUtil.dateToString(new Date(), loc_format);
		} else if (ip_para.startsWith("#{acDate"))
		{
			loc_index = ip_para.indexOf("[");
			if (loc_index != -1)
				loc_format = ip_para.substring(loc_index + 1, ip_para.indexOf("]"));

			loc_ret = DateUtil.dateToString(DateUtil.stringToDate(ip_pipeContext
					.getParameter("acDate"), DateUtil.BATCH_DATE_FORMAT),
					loc_format);
		} else if (ip_para.equals("#{docCnt}"))
		{
			Object loc_temp = ip_pipeContext.getAttribute("docCnt");
			if (null == loc_temp)
				loc_temp = "0";
			loc_ret = String.valueOf(loc_temp);
		} else
		{
			loc_ret = ip_para;
		}

		return loc_ret;
	}

	/**
	 * set value
	 * 
	 * @param ip_value
	 * @param ip_pipeContext
	 * @return
	 * @throws ParseException
	 */
	public static String setValue(String ip_value, PipeContext ip_pipeContext)
			throws ParseException
	{
		return parameterReplace(ip_value, ip_pipeContext);
	}

	/**
	 * print headfoot
	 * 
	 * @param ip_list
	 * @param ip_pipeContext
	 * @param ip_dataArray
	 * @return
	 * @throws Exception
	 */
	public static String dealHeadFoot(List<HeadFootBean> ip_list,
			PipeContext ip_pipeContext, String[][] ip_dataArray) throws Exception
	{
		String loc_ret = "";
		int loc_index = 0;
		int loc_bytes = 0;
		for (String[] loc_str : ip_dataArray)
		{
			boolean loc_found = false;
			Field loc_field = new Field();
			String loc_value = "";
			loc_index++;
			loc_field.setName(headfootStr+" "+loc_str[0]);
			for (HeadFootBean loc_bean : ip_list)
			{
				if (loc_str[0].equals(loc_bean.getName()))
				{
					loc_field.setBytes(Integer.parseInt(loc_bean.getBytes()));
					loc_field.setDataType(loc_bean.getDataType());
					loc_value = loc_bean.getValue();
					loc_found = true;
					break;
				}
			}

			if (!loc_found)
			{
				loc_field.setBytes(Integer.parseInt(loc_str[1]));
				loc_field.setDataType(loc_str[2]);
				loc_value = loc_str[3];
			}
			if (loc_index == ip_dataArray.length)
			{
				loc_field.setBytes(Integer.parseInt(loc_str[1]) - loc_bytes);
			}
			loc_bytes += loc_field.getBytes();
			loc_ret += FieldConverter.print(loc_field, setValue(loc_value, ip_pipeContext));
		}

		return loc_ret;
	}

	/**
	 * print headfoot
	 * 
	 * @param ip_list
	 * @param lineFileInputConfig
	 * @param ip_dataArray
	 * @param ip_rowData
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> dealInputHeadFoot(
			List<HeadFootBean> ip_list,
			String ip_encoding, String[][] ip_dataArray,
			byte[] ip_rowData) throws Exception
	{
		Map<String, String> loc_returnValue = new HashMap<String, String>();
		int loc_index = 0;
		int loc_currIndex = 0;
		int loc_bytes = 0;
		for (String[] loc_str : ip_dataArray)
		{
			boolean loc_found = false;
			Field loc_field = new Field();
			String loc_value = null;
			loc_currIndex++;
			for (HeadFootBean loc_bean : ip_list)
			{
				if (loc_str[0].equals(loc_bean.getName()))
				{
					loc_field.setBytes(Integer.parseInt(loc_bean.getBytes()));
					loc_found = true;
					break;
				}
			}

			if (!loc_found)
			{
				loc_field.setBytes(Integer.parseInt(loc_str[1]));
			}
			if (loc_currIndex == ip_dataArray.length)
			{
				loc_field.setBytes(Integer.parseInt(loc_str[1]) - loc_field.getBytes());
			}
			loc_value = new String(ip_rowData, loc_index, loc_field.getBytes(),
					ip_encoding);
			loc_returnValue.put(loc_str[0], loc_value);
			loc_index += loc_field.getBytes();
			loc_bytes += loc_field.getBytes();
		}
		return loc_returnValue;
	}
	/**
	 * getfileName4MultRow
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	public static String getFileName4Mult(Output ip_output) throws DataPipeException
	{
		FixedFileOutput4MultiConfig loc_config = DataPipeConfigFactory
				.getFixedFileOutput4MultiConfig(ip_output.getConfig()
						.getConfigFile());
		return parseFileName(loc_config.getFileName());
	}
	/**
	 * getfileName4DetailRow
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	public static String getFileName4Detail(Output ip_output) throws DataPipeException
	{
		FixedFileOutput4DetailConfig loc_config = DataPipeConfigFactory
				.getFixedFileOutput4DetailConfig(ip_output.getConfig()
						.getConfigFile());
		return parseFileName(loc_config.getFileName());
	}
	/**
	 * getfileName
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	public static String getFileName(Output ip_output) throws DataPipeException
	{
		FixedFileOutputConfig loc_config = DataPipeConfigFactory
				.getFixedFileOutputConfig(ip_output.getConfig().getConfigFile());		
		return parseFileName(loc_config.getFileName());
	}
	/**
	 * parse file name
	 * @param ip_name
	 * @return
	 */
	public static String parseFileName(String ip_name){
		int loc_sIndex = (ip_name.lastIndexOf("/") == -1 ? 0 : ip_name.lastIndexOf("/") + 1);       // if mark / do not exists then 0	
//		int eIndex = (name.lastIndexOf(".") == -1 ? name.length() : name.lastIndexOf(".")); // if mark . do not exists then sIndex
//		String fileName = name.substring(sIndex, eIndex);
		String loc_fileName = ip_name.substring(loc_sIndex);
		return loc_fileName;
	}
	/**
	 * get record length
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	public static int getRecLen(Output ip_output) throws DataPipeException
	{
		int loc_recLen = 0;
		try
		{
			FixedFileOutputConfig loc_config = DataPipeConfigFactory
					.getFixedFileOutputConfig(ip_output.getConfig()
							.getConfigFile());
			for (Field loc_f : loc_config.getFieldDefinition().getFields())
			{
				loc_recLen += loc_f.getBytes();
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
		return loc_recLen;
	}
	/**
	 * get record length 4 multiRows
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	protected static int getRecLen4Multi(Output ip_output) throws DataPipeException
	{
		int loc_recLen = 0;
		try
		{
			FixedFileOutput4MultiConfig loc_config = DataPipeConfigFactory
					.getFixedFileOutput4MultiConfig(ip_output.getConfig()
							.getConfigFile());
			String loc_controlFlag = null;
			for (Field f : loc_config.getFieldDefinition().getFields())
			{
				if (loc_controlFlag != null
						&& !loc_controlFlag.equals(f.getControlFlag()))
				{
					break;
				}
				loc_recLen += f.getBytes();
				loc_controlFlag=f.getControlFlag();
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
		return loc_recLen;
	}
	/**
	 * get record length 4 detailRows
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	protected static int getRecLen4Detail(Output ip_output) throws DataPipeException
	{
		int loc_recLen = 0;
		try
		{
			FixedFileOutput4DetailConfig loc_config = DataPipeConfigFactory
					.getFixedFileOutput4DetailConfig(ip_output.getConfig()
							.getConfigFile());
			String loc_controlFlag = null;
			for (Field f : loc_config.getFieldDefinition().getFields())
			{
				if (loc_controlFlag != null
						&& !loc_controlFlag.equals(f.getControlFlag()))
				{
					break;
				}
				loc_recLen += f.getBytes();
				loc_controlFlag=f.getControlFlag();
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
		return loc_recLen;
	}
}
