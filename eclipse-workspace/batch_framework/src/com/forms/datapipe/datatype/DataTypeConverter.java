/**
 * updateDate:2012-01-18
 *	ModifiedBy:zt
 *	ModifiedReason:add method printDate4Empty
 *	updateVersion:4 month
 */
package com.forms.datapipe.datatype;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

import com.forms.framework.util.StringUtil;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.datapipe.util.DateUtils;
/**
 * data formatter
 * 
 * @author cxl
 * 
 */
public class DataTypeConverter
{
	
    public static String leftTrim(String value, char trimChar)
    {
        if (value == null) return "";

        char[] chars = value.toCharArray();
        int i = 0;
        for (; i < chars.length; i++)
        {
            if (chars[i] != trimChar) break;
        }
        return value.substring(i);
    }

    public static String rightTrim(String value, char trimChar)
    {
        if (value == null) return "";

        char[] chars = value.toCharArray();
        int i = chars.length - 1;
        for (; i >= 0; i--)
        {
            if (chars[i] != trimChar) break;
        }
        return value.substring(0, i + 1);
    }

    public static String trim(String value, char trimChar)
    {
        return leftTrim(rightTrim(value, trimChar), trimChar);
    }
    
    public static String doNoting(String value, int length)
    {
        return value==null?"":value;
    }

    //---------------StringType-----------------------
    public static boolean isString(String value, int length)
    {
        return true;
    }
    
    public static boolean isNotNull(String value, int length)
    {
    	if(value==null||"".equals(value.trim())){
    		return false;
    	}
        return true;
    }

    /*
     * String.trim()
     */
    public static String trim(String value, int length)
    {
        return (value == null ? null : trim(value, ' '));
    }
    
    public static String rightTrim(String value, int length)
    {
        return value == null ? null : rightTrim(value, ' ');
    }

    public static String printString(String value, int length)
    {
        return (value == null || value.trim().equals("") ? null : value);
    }
    
    public static String printStringEmpty(String value, int length)
    {
    	 return (value == null ? null : value);
    }
    
    public static String printSpaceRight4Spe(String value, int length) throws Exception
    {
        if (value.getBytes().length > length)
        {
            String temp = null;
            String retValue = null;
            for (int i = length / 2; i < value.length(); i++)
            {
                retValue = temp;
                temp = value.substring(0, i);
                if (temp.getBytes().length > length)
                {
                    return retValue;
                }
            }
        }
        return fill("Right", value, " ", length);
    }
 
    //---------------NumberType-----------------------

    public static boolean isNumber(String value, int length)
    {
        if (value == null) return false;
        return value.matches("\\d+");
    }

    public static String parseNumber(String value, int length) throws DataPipeException
    {
        if (value == null)
            throw new DataPipeException("NumberType can not be null!");
        return value.trim();
    }

    public static String printNumber(String value, int length)
    {
        return (value == null ? "0" : value);
    }
    
    public static boolean isNumberWithSpace(String value, int length)
    {
        if (value == null) return false;
        return trim(value, ' ').matches("[0-9]*");
    }
    public static boolean isFloatWithSpace(String value, int length)
    {
        if (value == null) return false;
        return trim(value, ' ').matches("^(\\d+)(\\.\\d+){1}$");
    }
    //----------------------------------------------
    public static boolean isAmt(String value, int length)
    {
        if (value == null) return false;
        return trim(value, ' ').matches("^(\\d+)(\\.\\d{2}){1}$");
    }
    
    public static boolean isUnFormatAmt(String value, int length)
    {
        if (value == null) return false;
        return trim(value, ' ').matches("^(\\d+)(\\.\\d{0,2}){0,1}$");
    }
    
    public static boolean isSignAmt(String value, int length)
    {
        if (value == null) return false;
        return value.matches("^[+-]{0,1}(\\d*)(\\.\\d{2}){1}$");
    }
    
    public static boolean isScSignAmt(String value, int length)
    {
        if (value == null) return false;
        return value.matches("^[+-][1-9]{1}[0-9]{0,2}(,[0-9]{3})*.{0,1}[0-9]*?$")
            || value.matches("^[1-9]{1}[0-9]{0,2}(,[0-9]{3})*.{0,1}[0-9]*?[+-]$");
    }
    
    public static boolean isYddfAmt(String value, int length)
    {
        if (value == null || value.equals("")) return true;
        return trim(trim(value, '"'), ' ').matches("^(\\d*)(\\.\\d{0,2}){0,1}$") ||
            trim(trim(value, '"'), ' ').matches("^[1-9]{1}[0-9]{0,2}(,[0-9]{3})*(\\.\\d{0,2}){0,1}$");
    }
    
    public static boolean isYddfAmtNull(String value, int length)
    {
        if (value == null||value.trim().equals("")) return true;
        return trim(trim(value, '"'), ' ').matches("^[+-]{0,1}(\\d*)(\\.\\d{0,7}){0,1}$") ||
            trim(trim(value, '"'), ' ').matches("^[+-]{0,1}[1-9]{1}[0-9]{0,2}(,[0-9]{3})*(\\.\\d{0,7}){0,1}$");
    }
    
    public static String parse4Yddf(String value, int length) throws Exception
    {
        return trim(trim(value, '"'), ' ').replaceAll(",", "");
    }
    
    public static boolean isChar(String value, int length)
    {
        if (value == null) return false;
        return value.matches("[ a-zA-Z]*");
    }
    
    public static boolean isCharWithNumber(String value, int length)
    {
        if (value == null) return false;
        return value.matches("[ a-zA-Z0-9]*");
    }
    
    public static boolean isBjChar(String value, int length)
    {
        if (value == null) return true;
        for (int i = 0; i < value.length(); i++)
        {
            char temp = value.charAt(i);
            if (temp >= 127)
            {
                return false;
            }
        }
        return true;
    }
    
    public static String printCharLeft(String value, int length) throws Exception
    {
        return DataPipeUtils.fill("Left", value, " ", length);
    }
    
    
    public static String printSpaceLeft(String value, int length) throws Exception
    {
        return DataPipeUtils.fill("Left", value, " ", length);
    }
    
    public static String printSpaceRight(String value, int length) throws Exception
    {
        return DataPipeUtils.fill("Right", value, " ", length);
    }
    
    public static String printZeroLeft(String value, int length) throws Exception
    {
        return DataPipeUtils.fill("Left", value, "0", length);
    }
    
    public static String printZeroRight(String value, int length) throws Exception
    {
        return DataPipeUtils.fill("Right", value, "0", length);
    }
    
    public static String parseSpaceLeft(String value, int length) throws Exception
    {
        return DataPipeUtils.unFill("Left", value, " ");
    }
    
    public static String parseSpaceRight(String value, int length) throws Exception
    {
        return DataPipeUtils.unFill("Right", value, " ");
    }
    
    public static String parseZeroLeft(String value, int length) throws Exception
    {
        return DataPipeUtils.unFill("Left", value, "0");
    }
    
    public static String parseZeroLeft4Amt(String value, int length)
        throws Exception
    {
        String temp = value;
        String sign = "";
        if (value.indexOf('+') > -1 || value.indexOf('-') > -1)
        {
            temp = value.substring(1);
            sign = value.substring(0, 1);
        }
        if (temp.indexOf('.') != -1
            && ("".equals(temp.substring(0, temp.indexOf('.'))) || Integer.parseInt(temp.substring(
                0, temp.indexOf('.'))) == 0))
        {
            return sign + "0" + value.substring(value.indexOf('.'));
        }
        return sign + DataPipeUtils.unFill("Left", temp, "0");
    }
    
    public static String parseZeroRight(String value, int length) throws Exception
    {
        return DataPipeUtils.unFill("Right", value, "0");
    }
    
    /**
     * 
     * @param value
     * @param length
     * @return
     * @throws Exception
     */
    public static boolean isWideString(String value, int length) throws Exception
    {
        value = trim(value, ' ');
        for (int i = 0; i < value.length(); i++)
        {
            char s = value.charAt(i);
            if (s < 127)
            {
                return false;
            }
        }
        return true;
    }
    
    public static String printSpaceLeft4WS(String value, int length) throws Exception
    {
        return fill("Left", value, " ", length);
    }
    
    public static String printSpaceRight4WS(String value, int length) throws Exception
    {
        return fill("Right", value, " ", length);
    }
    
   
    public static String printSpaceRight4WSIBM937(String value, int length) throws Exception
    {
        return fill("Right", value, " ", length,"IBM-937");
    }    
    /**
     * @param value
     * @param length
     * @return
     */
    public static boolean isPkgRecType(String value, int length)
    {
        return "1".equals(value) || "2".equals(value);
    }
    
    public static String fill(String fillType, String src, String fillString,
        int length) throws Exception
    {
        return fill(fillType, src, fillString,
                length,"UTF-8");
    }
    
    public static String fill(String fillType, String src, String fillString,
			int length, String encoding) throws Exception
	{
		if (fillType == null || fillString == null || src == null)
		{
			throw new Exception(" [ At least one of parameters is null! ] ");
		}

		if ("Left".equalsIgnoreCase(fillType)
				&& "Right".equalsIgnoreCase(fillType))
		{
			throw new Exception(" [ The fillType must be Right or Left! ] ");
		}
		Charset c = Charset.forName(encoding);
		Charset ibm937 = Charset.forName("ibm937");
		boolean isibm937 = (ibm937.compareTo(c) == 0);
		int fillLength = 0;
		if (isibm937)
		{
			fillLength = length
					- StringUtil.getBytes4IBM937(src).length;
		} else
		{
			fillLength = length - src.getBytes(encoding).length;
		}
		if (fillLength == 0)
		{
			// not need to fill
			return src;
		} else if (fillLength < 0)
		{
			throw new Exception(
					" [ The src' length is longer than expected ! ] ");
		}

		StringBuffer sb = new StringBuffer();
		while (sb.length() < fillLength)
		{
			sb.append(fillString);
		}
		sb.delete(fillLength, sb.length());

		if ("Left".equalsIgnoreCase(fillType))
		{
			return sb + src;
		} else if ("Right".equalsIgnoreCase(fillType))
		{
			return src + sb;
		}

		return src;
	}
    
    public static String parsePackedDecimal(String value, int length)
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
	
	public static String printV99(String value, int length) throws DataPipeException
    {
        if (value == null || value.trim().equals(""))
		{
			return null;
		}            
        if (value.length() > length)
        	value = value.substring(value.length() - length);
        return printNumberWithPoint(value, length, 2);
    }
	
	public static String printV9999999(String value, int length) throws DataPipeException
    {
        if (value == null)
            throw new DataPipeException("can not be null!");
        if (value.length() > length)
        	value = value.substring(value.length() - length);
        return printNumberWithPoint(value, length, 7);
    }
	
	public static String printV999999(String value, int length) throws DataPipeException
    {
        if (value == null)
            throw new DataPipeException("can not be null!");
        if (value.length() > length)
        	value = value.substring(value.length() - length);
        return printNumberWithPoint(value, length, 6);
    }
	
	/**
	 * number with point
	 * 
	 * @param value
	 * @param length
	 * @param floatCnt
	 * @return
	 * @throws DataPipeException 
	 */
	private static String printNumberWithPoint(String value, int length, int floatCnt) throws DataPipeException
	{
		value = value == null ? "" : value;
		try
		{
			value = DataPipeUtils.fill("Left", value, "0", length);
			if (floatCnt == 0) return value;
			value = value.substring(0, value.length() - floatCnt) + "." + value.substring(value.length() - floatCnt);
			return value;
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	public static String printDate4Null(String value, int length) throws Exception
    {
		if(value==null||value.trim().equals("")||value.trim().startsWith("0000")||value.trim().startsWith("0001")||value.trim().startsWith("9999")){
			return null;
		}
        return DateUtils.format(value, null, "yyyy-MM-dd");
    }
	
	public static String printDate4Empty(String value, int length) throws Exception
    {
		if(value==null||value.trim().equals("")){
			return null;
		}
        return DateUtils.format(value, null, "yyyy-MM-dd");
    }
	
	public static String printV99PointNoSignSpecial(String value, int length)
	throws DataPipeException
	{
		try
		{
			if ("".equals(value.trim()))
				return printSpaceLeft(value, length);
			else
				return printPointNoSign(value, length, 2);
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	public static String printTimeStamp4Null(String value, int length) throws Exception
    {
		if(value==null||value.trim().equals("")){
			return null;
		}
        return DateUtils.format(value, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S");
    }
	
	public static String printTimeStamp(String value, int length) throws Exception
    {
		if (value == null || value.trim().equals("")) {
			value="0001/01/01 00:00:00";
			return fill("Right", value, " ", length);
		} 
		return fill("Right", DateUtils.format(value, "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"), " ", length);
    }
	
	public static String printTime(String value, int length) throws Exception
    {
		if (value == null || value.trim().equals("")) {
			value="00:00:00";
		} 
		return fill("Right", value, " ", length);
    }
	
	public static String printDateNoNull(String value, int length) throws Exception
    {
		if(value==null||value.trim().equals("")){
			return "00010101";
		}
        return DateUtils.format(value, null, "yyyyMMdd");
    }
	
	public static String printDateDefault(String value, int length) throws Exception
    {
		if(value==null||value.trim().equals("")){
			return "0001/01/01";
		}
        return DateUtils.format(value, null, "yyyy/MM/dd");
    }
	
	
	public static String parseNumber4Null(String value, int length) throws DataPipeException
    {
        if (value == null || value.equals(""))
            return "0";
        return value.trim();
    }
	
	public static boolean isUnFormatNumber(String value, int length)
    {
        if (value == null) return true;
        return trim(value, ' ').matches("^(\\d+)(\\.\\d{0,2}){0,1}$");
    }
	
	public static boolean isIntRate(String value, int length)
    {
        if (value == null) return false;
        return trim(value, ' ').matches("^(\\d+)(\\.\\d{5}){1}$");
    }
	
	public static String parseTimestamp(String value, int length) throws Exception
    {		
    	if (value == null || value.trim().equals(""))
    		return null;
        return DateUtils.format(value, "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String parseDt(String value, int length) throws Exception
    {
    	if (value == null || value.trim().equals(""))
    		return null;
        return DateUtils.format(value, null, "yyyy-MM-dd");
    }
    
    public static String parseOutDt(String value, int length) throws Exception
    {
    	if (value == null )
    		return null;
        return DateUtils.format(value, null, "yyyy/MM/dd");
    }
    
    public static String parseDtNoSplit(String value, int length) throws Exception
    {
    	if (value == null)
    		return null;
        return DateUtils.format(value, null, "yyyyMMdd");
    }
    
    /**

	 * @param value
	 * @param length
	 * @return
	 * @throws DataPipeException
	 */
	public static String printV99NotPoint(String value, int length)
			throws DataPipeException
	{
		return printV99NotPoint(value,length,2);
	}
	
	public static String printV9999999NotPoint(String value, int length)
	throws DataPipeException
	{
		return printV99NotPoint(value,length,7);
	}
	
	public static String printV99NotPoint(String value, int length,int floatCnt)
	throws DataPipeException
	{
		try
		{
			if (value == null || "".equals(value.trim()))
			{
				value = "0";
			}
			return DataPipeUtils.fill("Left", printNumberAddPoint(value, floatCnt)
				.replaceAll("\\.", ""), "0", length);
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	public static String printV99PointNoSign(String value, int length)
	throws DataPipeException
	{
		return printPointNoSign(value,length,2);
	}
	
	public static String printV999PointNoSign(String value, int length)
	throws DataPipeException
	{
		return printPointNoSign(value,length,3);
	}
	
	public static String printV99999PointNoSign(String value, int length)
	throws DataPipeException
	{
		return printPointNoSign(value,length,5);
	}
	
	public static String printV9999999PointNoSign(String value, int length)
	throws DataPipeException
	{
		return printPointNoSign(value,length,7);
	}
	
	public static String printV99PointNegSign(String value, int length)
	throws DataPipeException
	{
		return printPointNegSign(value,length,2);
	}
	
	public static String printV999PointNegSign(String value, int length)
	throws DataPipeException
	{
		return printPointNegSign(value,length,3);
	}
	
	public static String printV9999999PointNegSign(String value, int length)
	throws DataPipeException
	{
		return printPointNegSign(value,length,7);
	}
	
	public static String printV99PointSign(String value, int length)
	throws DataPipeException
	{
		return printPointSign(value,length,2);
	}
	
	public static String printV999PointSign(String value, int length)
	throws DataPipeException
	{
		return printPointSign(value,length,3);
	}
	
	public static String printV9999999PointSign(String value, int length)
	throws DataPipeException
	{
		return printPointSign(value,length,7);
	}
	
	public static String printV99999PointSign(String value, int length)
	throws DataPipeException
	{
		return printPointSign(value,length,5);
	}
	
	public static String printPointSign(String value, int length,int floatCnt)
	throws DataPipeException
	{
		try
		{
			String sign="+";
			if (value == null || "".equals(value.trim()))
			{
				value = "0";
			}
			if(value.startsWith("-")){
				sign="-";
				value=value.substring(1);
			}else if(value.startsWith("+")){
				value=value.substring(1);
			}
			
			return sign+DataPipeUtils.fill("Left", printNumberAddPoint(value, floatCnt), "0", length-1);
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	public static String printPointNegSign(String value, int length,int floatCnt)
	throws DataPipeException
	{
		try
		{
			String sign=" ";
			if (value == null || "".equals(value.trim()))
			{
				value = "0";
			}
			if(value.startsWith("-")){
				sign="-";
				value=value.substring(1);
			}else if(value.startsWith(" ")){
				value=value.substring(1);
			}
			
			return sign+DataPipeUtils.fill("Left", printNumberAddPoint(value, floatCnt), "0", length-1);
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	public static String printPointNoSign(String value, int length,int floatCnt)
	throws DataPipeException
	{
		try
		{
			return DataPipeUtils.fill("Left", printNumberAddPoint(value, floatCnt), "0", length);
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	

	/**
	 * @param value
	 * @param length
	 * @return
	 * @throws DataPipeException
	 */
	private static String printNumberAddPoint(String value, int length)
			throws DataPipeException
	{
		try
		{
			StringBuffer pattern = new StringBuffer();
			while (pattern.length() < length)
			{
				pattern.append("0");
			}
			
			DecimalFormat format = new DecimalFormat("0" +(pattern.toString().equals("")?"":"."+pattern.toString()));
			return format.format(Double.parseDouble(value));
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	//
	public static String toPackedDecimal(String value, int length, int floatCnt) throws DataPipeException
	{
		if(null == value||"".equals(value.trim())){
			value="0";
		}
		String str = printNumberAddPoint(value,floatCnt).replaceAll("\\.", "");
		if(str.startsWith("-"))
		{
			str = str.substring(1, str.length()-1) + 'D';
		}
		else if(str.startsWith("+"))
		{
			str = str.substring(1, str.length()-1) + 'C';
		}
		else if(Character.isDigit(str.charAt(0)))
		{
			str = str + 'C';
		}
		else
		{
			throw new DataPipeException("");
		}
		if(str.length() % 2 == 1)
		{
			str = '0' + str;
		}
		return str;
	}

	public static String toPackedDecimal(String value, int length) throws DataPipeException 
	{
		return toPackedDecimal(value,length,0);
	}

	public static String toPDV99(String value, int length) throws DataPipeException 
	{
		return toPackedDecimal(value,length,2);
	}
	public static String toPDV999999(String value, int length) throws DataPipeException 
	{
		return toPackedDecimal(value,length,6);
	}
	public static String toPDV9999999(String value, int length) throws DataPipeException 
	{
		return toPackedDecimal(value,length,7);
	}
	
	public static String toPDV999(String value, int length) throws DataPipeException 
	{
		return toPackedDecimal(value,length,3);
	}
	
	public static String printPackedDecimal(String value,int length) throws DataPipeException
	{
		int len = (2 * length - value.length()) / 2;
		if (value.length() > 2 * length)
			throw new DataPipeException("value length error");
		else
		{
			for (int i = 0; i < len; i++)
				value = "00" + value;
		}
		return value;
	}
	
	public static String printPackedDecimalSub(String value, int length) throws DataPipeException
    {
        if (value == null)
            throw new DataPipeException("PackedDecimalType can not be null!");
        if (value.length() > length)
        	value = value.substring(value.length() - length);
        return value;
    }
	
	public static String printStringDoNothing(String value, int length)
    {
		if(value==null||value.trim().equals("")){
			return null;
		}
        return value;
    }
	
	public static String parseStringDoNothing(String value, int length)
    {
		if(value==null||value.trim().equals("")){
			return null;
		}
        return value;
    }
}
