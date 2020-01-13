package com.forms.framework.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.XmlUtil;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.ByteUtils;

public class GenSplitFile
{
	private static FileInputStream fis;

	private static FileOutputStream fos;

	private static String controlCode;

	private static String fileEncoding;

	private static String limitCode;

	private static int readStartLine;

	private static int readEndLine;

	private static int iffLineNum;

	private static String UTF8Encoding = "UTF-8";

	private static String lineFeed = "\r\n";

	private static String characterFeed = "\t";

	private static String flagY = "Y";

	private static String flagN = "N";

	// query all with no condition
	private static String queryCode1 = "00";

	// query all with condition
	private static String queryCode2 = "01";

	// query column with no condition
	private static String queryCode3 = "10";

	// query column with condition
	private static String queryCode4 = "11";

	private static String split1 = ":";

	private static String split2 = ",";

	private static String split3 = "=";

	private static String userInfo = "";

	public GenSplitFile(String ip_type, String ip_xmlPath, String ip_fields,
			String ip_lineLimit, String ip_fromFile, String ip_toFile)
			throws BatchJobException
	{
		try
		{
			fis = new FileInputStream(ip_fromFile);
			fos = new FileOutputStream(ip_toFile);
			userInfo += "Input Iff Path:" + ip_fromFile + lineFeed;
			userInfo += "Output Iff Path:" + ip_toFile + lineFeed;
			limitCode = ip_lineLimit;
			this.init(ip_type, ip_xmlPath, ip_fields);
		} catch (Exception ip_e)
		{
			try
			{
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException e)
			{
				throw new BatchJobException(e);
			}
			throw new BatchJobException(ip_e);
		}
	}

	@SuppressWarnings( { "static-access", "unchecked" })
	public void init(String ip_type, String ip_xmlPath, String ip_fields)
			throws BatchJobException
	{
		XmlUtil loc_XmlUtil = new XmlUtil();
		try
		{
			this.userInfo += "Config Xml Path:" + ip_xmlPath + lineFeed;
			Element loc_rootElement = loc_XmlUtil.loadRootElement(
					ip_xmlPath, CommonAPI.ENCODING_UTF_8);
			this.fileEncoding = loc_rootElement.elementText("encoding");
			this.userInfo += "File Encoding: " + this.fileEncoding + lineFeed;
			Element loc_fieldElement = loc_rootElement
					.element("field-definition");
			List<Element> loc_fileElements = loc_fieldElement.elements();
			String[] loc_fields = ip_fields.split(split1);
			int loc_filedsSizes = loc_fileElements.size();
			String loc_controlCode = "";
			int loc_iffLength = 0;
			String loc_visiableFiledInfo = "Visiable Fields:";
			String loc_conditionFiledInfo = "Condition Fields:";
			for (int loc_i = 0; loc_i < loc_filedsSizes; loc_i++)
			{
				String loc_visiableFlag = flagN;
				String loc_conditionFlag = flagN;
				String loc_recordLength = loc_fileElements.get(loc_i)
						.attribute("bytes").getText();
				String loc_recordName = loc_fileElements.get(loc_i).attribute(
						"name").getText();
				String loc_fieldType = loc_fileElements.get(loc_i).attribute(
						"data-type").getText();
				String loc_fieldData = "";
				loc_iffLength += Integer.parseInt(loc_recordLength);
				for (int loc_j = 0; loc_j < loc_fields.length; loc_j++)
				{
					String[] loc_fieldsInfo = loc_fields[loc_j].split(split3);
					if (loc_visiableFlag.equals(flagN))
					{
						if (loc_recordName.equals(loc_fieldsInfo[0]))
						{
							loc_visiableFlag = flagY;
							loc_visiableFiledInfo += loc_recordName + split2;
						} else
						{
							loc_visiableFlag = flagN;
						}
					}
					if (loc_conditionFlag.equals(flagN))
					{
						if (loc_fieldsInfo.length > 1)
						{
							if (loc_recordName.equals(loc_fieldsInfo[0]))
							{
								loc_fieldData = loc_fieldsInfo[1];
								loc_conditionFlag = flagY;
								loc_conditionFiledInfo += loc_recordName
										+ split3 + loc_fieldData + split2;
							}
						}
					}
				}
				if (loc_i == loc_filedsSizes - 1)
					loc_controlCode += loc_visiableFlag + split1
							+ loc_recordLength + split1 + loc_recordName
							+ split1 + loc_conditionFlag + split1
							+ loc_fieldData + split1 + loc_fieldType;
				else
					loc_controlCode += loc_visiableFlag + split1
							+ loc_recordLength + split1 + loc_recordName
							+ split1 + loc_conditionFlag + split1
							+ loc_fieldData + split1 + loc_fieldType + split2;
			}
			if (ip_type.equals(queryCode1))
			{
				this.userInfo += "Query Type: query all with no condition"
						+ lineFeed;
				this.userInfo += "Visiable Fields: all" + lineFeed;
				this.userInfo += "Condition Fields: none" + lineFeed;
			} else if (ip_type.equals(queryCode2))
			{
				this.userInfo += "Query Type: query all with condition"
						+ lineFeed;
				this.userInfo += "Visiable Fields: all" + lineFeed;
				this.userInfo += loc_conditionFiledInfo + lineFeed;
			} else if (ip_type.equals(queryCode3))
			{
				this.userInfo += "Query Type: query column with no condition"
						+ lineFeed;
				this.userInfo += loc_visiableFiledInfo + lineFeed;
				this.userInfo += "Condition Fields: none" + lineFeed;
			} else if (ip_type.equals(queryCode4))
			{
				this.userInfo += "Query Type: query column with condition"
						+ lineFeed;
				this.userInfo += loc_visiableFiledInfo + lineFeed;
				this.userInfo += loc_conditionFiledInfo + lineFeed;
			} else
			{
				this.userInfo += "Query Type: unknow type" + lineFeed;
			}

			this.controlCode = ip_type + split2 + loc_iffLength + split2
					+ loc_controlCode;
			iffLineNum = fis.available() / loc_iffLength;
			if ("0".equals(limitCode))
			{
				readStartLine = 1;
				readEndLine = iffLineNum;
				this.userInfo += "Read Line:" + "all" + lineFeed;
			} else
			{
				String[] loc_lineLimit = limitCode.split("-");
				readStartLine = Integer.parseInt(loc_lineLimit[0]);
				readEndLine = Integer.parseInt(loc_lineLimit[1]);
				this.userInfo += "Read Line:" + readStartLine + "-->"
						+ readEndLine + lineFeed;
			}
			this.userInfo += "Record Length:" + loc_iffLength + lineFeed
					+ lineFeed;

		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}

	public void normalProcess() throws BatchJobException
	{
		String[] loc_firstArray = controlCode.split(",");
		String loc_flag = loc_firstArray[0];
		int loc_recordLength = Integer.parseInt(loc_firstArray[1]);
		try
		{
			byte[] loc_byte = new byte[loc_recordLength];
			int loc_num = 0;
			int loc_count = 1;
			int loc_size = loc_firstArray.length - 2;
			fos.write(userInfo.getBytes(UTF8Encoding));
			while ((loc_num = fis.read(loc_byte)) > -1)
			{

				int loc_readLength = 0;
				int loc_columnLength = 0;
				Map<String, String> loc_pipeData = new HashMap<String, String>();
				String loc_data;
				int loc_conditionCount = 0;
				int loc_matchCount = 0;
				String loc_str = new String(loc_byte, 0, loc_num, fileEncoding);
				if (loc_str.startsWith("1"))
				{
					if (loc_count >= readStartLine && loc_count <= readEndLine)
					{
						loc_count++;
						for (int loc_i = 0; loc_i < loc_size; loc_i++)
						{
							String[] loc_secondArray = loc_firstArray[loc_i + 2]
									.split(split1);
							loc_columnLength = Integer
									.parseInt(loc_secondArray[1]);
							if (loc_secondArray[5].toUpperCase().matches(
									"PACKEDDECIMAL.*"))
							{
								byte[] b = new byte[loc_columnLength];
								System.arraycopy(loc_byte, loc_readLength, b,
										0, loc_columnLength);
								loc_data = this.parsePackedDecimal(ByteUtils
										.toHexString(b), Integer
										.parseInt(loc_secondArray[1]));
							} else
								loc_data = new String(loc_byte, loc_readLength,
										loc_columnLength, fileEncoding);
							loc_pipeData.put(loc_secondArray[2], loc_data);
							loc_readLength += loc_columnLength;
							if (flagY.equals(loc_secondArray[3]))
							{
								loc_conditionCount++;
								if (loc_data.trim().equals(loc_secondArray[4]))
								{
									loc_matchCount++;
								} else if (loc_secondArray[5].toUpperCase()
										.matches("PACKEDDECIMAL.*")
										&& loc_data.trim().substring(1).equals(
												loc_secondArray[4]))
								{
									loc_matchCount++;
								}
							}
						}
						if (queryCode1.equals(loc_flag))
						{
							fos.write(loc_str.getBytes(UTF8Encoding));
							fos.write(lineFeed.getBytes(UTF8Encoding));
						} else if (queryCode2.equals(loc_flag)
								&& (loc_conditionCount == loc_matchCount))
						{
							fos.write(loc_str.getBytes(UTF8Encoding));
							fos.write(lineFeed.getBytes(UTF8Encoding));
						} else if (queryCode3.equals(loc_flag))
						{
							for (int loc_i = 0; loc_i < loc_size; loc_i++)
							{
								String[] loc_secondArray = loc_firstArray[loc_i + 2]
										.split(split1);
								if (flagY.equals(loc_secondArray[0]))
								{
									fos.write(loc_pipeData.get(
											loc_secondArray[2]).getBytes(
											UTF8Encoding));
									fos.write(characterFeed
											.getBytes(UTF8Encoding));
								}
							}
							fos.write(lineFeed.getBytes(UTF8Encoding));
						} else if (queryCode4.equals(loc_flag)
								&& (loc_conditionCount == loc_matchCount))
						{
							for (int loc_i = 0; loc_i < loc_size; loc_i++)
							{
								String[] loc_secondArray = loc_firstArray[loc_i + 2]
										.split(split1);
								if (flagY.equals(loc_secondArray[0]))
								{
									fos.write(loc_pipeData.get(
											loc_secondArray[2]).getBytes(
											UTF8Encoding));
									fos.write(characterFeed
											.getBytes(UTF8Encoding));
								}
							}
							fos.write(lineFeed.getBytes(UTF8Encoding));
						}
					} else
					{
						loc_count++;
					}
				}
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
			try
			{
				fis.close();
				fos.close();
			} catch (Exception ip_e)
			{
				throw new BatchJobException(ip_e);
			}
		}
	}

	public String parsePackedDecimal(String value, int length)
			throws DataPipeException
	{
		if (value == null)
			throw new DataPipeException("PackedDecimalType can not be null!");

		if ("".equals(value.replaceAll("40", "")))
			return "";

		String sign = value.substring(value.length() - 1);
		value = value.substring(0, value.length() - 1);
		if ("C".equalsIgnoreCase(sign) || "F".equalsIgnoreCase(sign)
				|| "0".equals(sign))
			return value;
		else if ("D".equalsIgnoreCase(sign))
			sign = "-";
		else
			throw new DataPipeException("Invalid Packed Decimal value!");
		if (Long.parseLong(value) == 0)
			sign = "";
		return sign + value;
	}

	public static void main(String[] args) throws BatchJobException
	{

		long startTime = System.currentTimeMillis();
		GenSplitFile csf = new GenSplitFile(args[0], args[1], args[2],
				args[3], args[4], args[5]);
		csf.normalProcess();
		System.out.println("Spend time :"
				+ (System.currentTimeMillis() - startTime));
	}
}
