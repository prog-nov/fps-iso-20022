package com.forms.framework.util;

import java.util.List;


/**
 * Handle basic and common validations (non-business related) 
 */
public class ValidateUtil 
{
	/** Char Set*/
    private static final String CHARSET_NUMBER 			= "0123456789"; // ASCII 48-57
    private static final String CHARSET_LETTER_UPCASE 	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // ASCII 65-90
    private static final String CHARSET_LETTER_LOWCASE	= "abcdefghijklmnopqrstuvwxyz"; // ASCII 97-122
	private static final String CHARSET_LETTER 			= CHARSET_LETTER_UPCASE + CHARSET_LETTER_LOWCASE;
	private static final String CHARSET_ALPHANUMERIC 	= CHARSET_NUMBER + CHARSET_LETTER;

	/** Pattern */
	private static final char PATTERN_NUMERIC	= '~';
	private static final char PATTERN_ALPHA		= '^';
	private static final char PATTERN_ANY		= '|';
	
	/**
	 * Check if input string is null or empty
	 * @param ip_string		Input string
	 * @return				true if input is null or empty; false otherwise.
	 */
	public static boolean isEmpty(String ip_string)
	{
		return (ip_string == null || ip_string.length() == 0);
	}
	
	/**
	 * Check if input array is null or empty
	 * @param ip_stringArray	Input string array
	 * @return					true if input is null or empty; false otherwise.
	 */
	public static boolean isEmpty(String[] ip_stringArray)
	{
		return (ip_stringArray == null || ip_stringArray.length == 0);
	}
	
	/**
	 * Check if input list is null or empty
	 * @param ip_list		Input list
	 * @return				true if input is null or empty; false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmpty(List ip_list)
	{ 
		return (ip_list == null || ip_list.size() == 0);
	}
	
	/**
	 * Check if input is in given char set or not
	 * @param ip_str			Input string
	 * @param ip_char_set		Character set
	 * @return					true if each character of input string is in character set; false otherwise.
	 * @throws					Exception
	 */
	public static boolean isInCharSet(String ip_str, String ip_char_set)
		throws Exception
	{
		try
		{
			String loc_char_set = "";
			
			if (ip_str == null)
				return false;
	
			if (ip_char_set != null)
				loc_char_set += ip_char_set;
		
			// Check the input is in the given charaset or not
			for (int j = 0; j < ip_str.length(); j++)
			{
				char loc_char = ip_str.charAt(j);
				if (loc_char_set.indexOf(loc_char) == -1)
					return false;
			}
			
			return true;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}

	/**
	 * Check if input is in given char set or not
	 * @param ip_str				Input string
	 * @param ip_char_set			Character set
	 * @param ip_allow_tc			Allow traditional chinese or not
	 * @param ip_allow_sc			Allow simplified chinese or not
	 * @return						true if each character of input string is in character set; false otherwise.
	 * @throws						Exception
	 */
	public static boolean isInCharSet(String ip_str, String ip_char_set, boolean ip_allow_tc, boolean ip_allow_sc)
		throws Exception
	{
		try
		{
			return isInCharSet(ip_str, ip_char_set, ip_allow_tc, ip_allow_sc, false);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if input is in given char set or not
	 * @param ip_str				Input string
	 * @param ip_char_set			Character set
	 * @param ip_allow_tc			Allow traditional chinese or not
	 * @param ip_allow_sc			Allow simplified chinese or not
	 * @return						true if each character of input string is in character set; false otherwise.
	 * @throws						Exception
	 */
	public static boolean isInCharSet(String ip_str, String ip_char_set, boolean ip_allow_tc, boolean ip_allow_sc, boolean isTxSrcWTS)
		throws Exception
	{
		try
		{
			String loc_char_set = "";
			
			if (ip_str == null)
				return false;
	
			if (ip_char_set != null)
				loc_char_set += ip_char_set;
			
			// Check the input is in the given charaset or not
			for (int j = 0; j < ip_str.length(); j++)
			{
				if (isChineseCharcter(ip_str.substring(j, j+1), isTxSrcWTS))
				{
					if (ip_allow_tc && ip_allow_sc)
					{
						if (!CharsetConversionUtils.isTraditionalChinese(ip_str.substring(j, j+1)) &&
							!CharsetConversionUtils.isGB2312Chinese(ip_str.substring(j, j+1)))
							return false;
					}
					else if (ip_allow_tc)
					{
						if (!CharsetConversionUtils.isTraditionalChinese(ip_str.substring(j, j+1)))
							return false;
					}
					else if (ip_allow_sc)
					{
						if (!CharsetConversionUtils.isGB2312Chinese(ip_str.substring(j, j+1)))
							return false;
					}
					else
						return false;
				}
				else
				{
					char loc_char = ip_str.charAt(j);
					if (loc_char_set.indexOf(loc_char) == -1)
						return false;
				}
			}
			
			return true;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check the input string is in the ascii range of not 
	 * @param ip_str			Input string
	 * @param ip_start_ascii	Start index of ASCII
	 * @param ip_end_ascii		End index of ASCII
	 * @return					true if the input string is in the ascii range; false otherwise.
	 * @throws					Exception
	 */
	public static boolean isInAsciiRange(String ip_str, int ip_start_ascii, int ip_end_ascii)
		throws Exception
	{
		try
		{
			for (int i = 0; i < ip_str.length(); i++)
			{
				char loc_char = ip_str.charAt(i);
				if ((loc_char < ip_start_ascii) || (loc_char > ip_end_ascii))
					return false;
			}
			
			return true;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}

	/**
	 * Check if input is letter (A-Z a-z) or not
	 * @param ip_str		Input string
	 * @return				true if the input string is consist of letter (A-Z a-z); false otherwise.
	 * @throws				Exception 
	 */
	public static boolean isLetter(String ip_str)
		throws Exception
	{
		try
		{
			return isInCharSet(ip_str, CHARSET_LETTER);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if input is upcase letter(A-Z) or not
	 * @param ip_str		Input string
	 * @return				true if the input string is consist of upper letter (A-Z); false otherwise.
	 * @throws				Exception
	 */
	public static boolean isLetterUp(String ip_str)
		throws Exception
	{
		try
		{
			return isInCharSet(ip_str, CHARSET_LETTER_UPCASE);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if input is number (0-9) or not
	 * @param ip_str		Input string
	 * @return				true if the input string is consist of number (0-9); false otherwise.
	 * @throws				Exception  
	 */
	public static boolean isNumber(String ip_str)
		throws Exception
	{
		try
		{
			return isInCharSet(ip_str, CHARSET_NUMBER);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if input is Alphanumeric (A-Z a-z 0-9) or not
	 * @param ip_str	Input string
	 * @return			true if the input string is consist of alphanumeric (A-Z a-z 0-9); false otherwise.
	 * @throws			Exception
	 */
	public static boolean isAlphanumeric(String ip_str)
		throws Exception
	{
		try
		{
			return isInCharSet(ip_str, CHARSET_ALPHANUMERIC);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}

	/**
	 * Check if the length of input string is equal to input length or not
	 * @param ip_str	Input string
	 * @param ip_len	Input length
	 * @return			true if the length of input string is equal to input length; false otherwise.
	 * @throws			Exception
	 */
	public static boolean isLen(String ip_str, int ip_len)
		throws Exception
	{
		try
		{
			return isLen(ip_str, ip_len, ip_len, false);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if the length of input string is in valid length or not
	 * @param ip_str		Input string
	 * @param ip_max_len	Max length
	 * @param ip_min_len	Min length
	 * @return				true if the length of input string is in valid length; false otherwise.
	 * @throws				Exception
	 */
	public static boolean isLen(String ip_str, int ip_max_len, int ip_min_len)
		throws Exception
	{
		try
		{
			return isLen(ip_str, ip_max_len, ip_min_len, false);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if the length of input string is in valid length or not
	 * @param ip_str		Input string
	 * @param ip_max_len	Max length
	 * @param ip_min_len	Min length
	 * @return				true if the length of input string is in valid length; false otherwise.
	 * @throws				Exception
	 */
	public static boolean isLen(String ip_str, int ip_max_len, int ip_min_len, boolean ip_txSrcWTS)
		throws Exception
	{
		try
		{
			if (ip_str == null)
				return false;
			
			int loc_length = 0;
			for(int loc_i = 0; loc_i < ip_str.length(); loc_i++)
				
			{
				if (isChineseCharcter(ip_str.substring(loc_i, loc_i+1), ip_txSrcWTS))
				{
					loc_length += 5;
				}
				else
				{
					loc_length++;
				}
			}
			
			if (loc_length > ip_max_len ||
				loc_length < ip_min_len)
				return false;
			
			return true;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	
	/**
	 * Check if the length of input string is in valid length or not
	 * @param ip_str		Input string
	 * @param ip_max_len	Max length
	 * @param ip_min_len	Min length
	 * @param ip_txSrcWTS   WTS Tx. Flag
	 * @param ip_chineseCharBytes	Chinese Character Bytes
	 * @return				true if the length of input string is in valid length; false otherwise.
	 * @throws				Exception
	 */
	public static boolean isLen(String ip_str, int ip_max_len, int ip_min_len, boolean ip_txSrcWTS, int ip_chineseCharBytes)
		throws Exception
	{
		try
		{
			if (ip_str == null)
				return false;
			
			int loc_length = 0;
			for (int loc_i = 0; loc_i < ip_str.length(); loc_i++)
			{
				if (isChineseCharcter(ip_str.substring(loc_i, loc_i+1), ip_txSrcWTS))
				{
					loc_length += ip_chineseCharBytes;
				}
				else
				{
					loc_length++;
				}
			}
			
			if (loc_length > ip_max_len || loc_length < ip_min_len)
				return false;
			
			return true;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check the input string contain any chinese character or not
	 * @param ip_str		Input string
	 * @return 				true if the input string contain any chinese character
	 * 						(e.g 3400-4DBF, 4E00-9FFF, 20000-2A6DF, 2F800-2FA1F); false otherwise. 
	 * @throws 				Exception
	 */
	public static boolean isConsistChineseChar(String ip_str)
		throws Exception
	{
		try
		{
			if (isEmpty(ip_str))
				return false;
			
			for (int i = 0; i < ip_str.length(); i++)
			{
				char loc_ch = ip_str.charAt(i);
				
				Character.UnicodeBlock loc_block = Character.UnicodeBlock.of(loc_ch);
				if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(loc_block) //4E00-9FFF
					|| Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(loc_block) //2F800-2FA1F
					|| Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(loc_block) //3400-4DBF
					|| Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B.equals(loc_block) //20000-2A6DF
					)
				{
					return true;
				}
			}
			
			return false;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}

	/**
	 * Check the input string is match the input pattern or not (i.e. ip_str = 8ABC, ip_pattern = ~^B|, the result will return true)
	 * @param ip_str			Input string
	 * @param ip_pattern 		Input pattern (i.e '~' for numeric char, '^' for alphabet char, '|' for any char)
	 * @return					true if the input match the pattern; false otherwise.
	 * @throws 					Exception
	 */
	public static boolean isPattern(String ip_str, String ip_pattern)
		throws Exception
	{
		try
		{
			if ((ip_str == null) || (ip_pattern == null))
				return false;

			String loc_trim_pattern = ip_pattern.trim();
			String loc_trim_str = ip_str.trim();
			
			if (loc_trim_pattern.length() != loc_trim_str.length())
				return false;

			char loc_char ;
			for (int i= 0 ; i < loc_trim_pattern.length(); i++)
			{
				loc_char = loc_trim_pattern.charAt(i);
				if (loc_char == PATTERN_ALPHA)
				{
					if (!isLetter(loc_trim_str.substring(i,i+1)))
						return false;
				}
				else if(loc_char == PATTERN_NUMERIC)
				{
					if (!isNumber(loc_trim_str.substring(i,i+1)))
						return false;
				}
				else if(loc_char == PATTERN_ANY)
				{
					// allow any char
				}
				else
				{
					if(loc_trim_str.charAt(i) != loc_char)
						return false;
				}
			}

			return true;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check the input string is in the input string array or not
	 * @param ip_str			Input string
	 * @param ip_str_array		Input string array
	 * @return					true if input string exists in input string array
	 * @throws 					Exception
	 */
	public static boolean isInStringArray(String ip_str, String[] ip_str_array)
		throws Exception
	{
		try
		{
			if (ip_str == null)
				return false;
			
			for (int i = 0; i < ip_str_array.length; i++)
				if (ip_str.equals(ip_str_array[i]))
					return true;
			
			return false;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if the input text contains Html Character (e.g '<', '>', '"', '|', '&', ' ')
	 * @param ip_text			Input string
	 * @return					true if input contains character (e.g '<', '>', '"', '|', '&', ' '); false otherwise.
	 * @throws					Exception
	 */
	public static boolean containsHtmlChar(String ip_text)
		throws Exception 
	{
		try 
		{
			if (ip_text == null || ip_text.length() ==0)
				return false;
			else 
			{	
				if (ip_text.indexOf('<') >= 0 ||
					ip_text.indexOf('>') >= 0 ||
					ip_text.indexOf('"') >= 0 ||
					ip_text.indexOf('|') >= 0 ||
					ip_text.indexOf('&') >= 0 ||
					ip_text.indexOf(' ') >= 0)
					return true;
				else
					return false;
			}
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * Check if the character passed in is chinese character or not
	 * @param ip_char
	 * @param ip_txSrcWTS
	 * @return
	 * @throws Exception
	 */
	public static boolean isChineseCharcter(String ip_char, boolean ip_txSrcWTS) throws Exception
	{
		try
		{
			return (ip_txSrcWTS && ip_char.getBytes(CommonAPI.ENCODING_UTF_8).length > 1) ||
					(!ip_txSrcWTS && ip_char.getBytes().length > 1);
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * get the length of input string
	 * @param ip_str		Input string
	 * @return				et the length of input string
	 * @throws				Exception
	 */
	public static int getLen(String ip_str, boolean ip_txSrcWTS)
		throws Exception
	{
		try
		{
			if (isEmpty(ip_str))
				return 0;
			
			int loc_length = 0;
			for(int loc_i = 0; loc_i < ip_str.length(); loc_i++)
				
			{
				if (isChineseCharcter(ip_str.substring(loc_i, loc_i+1), ip_txSrcWTS))
				{
					loc_length += 5;
				}
				else
				{
					loc_length++;
				}
			}
			
			return loc_length;
		}
		catch (Exception ip_exception)
		{
			throw ip_exception;
		}
	}
	
	/**
	 * if input string has special char of html return true, else return false
	 * 
	 * @param ip_str
	 *            source String
	 * @return
	 */
	public static boolean hasHTMLSpecialChar(String ip_str)
	{
		if (null == ip_str || "".equals(ip_str.trim()))
			return false;
		else
		{
			if ((ip_str.indexOf('<') >= 0) || (ip_str.indexOf('>') >= 0)
					|| (ip_str.indexOf('"') >= 0) || (ip_str.indexOf('|') >= 0)
					|| (ip_str.indexOf('&') >= 0) || (ip_str.indexOf(' ') >= 0))

				return true;
			else
				return false;
		}
	}
}
