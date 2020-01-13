package com.forms.framework.job.common.validate;


public class DataFormat
{
	public static final String NUMBER_REGEX = "\\d+";

	public static final String EMPTY_OR_NUMBER_REGEX = "\\d*";
	
	public static final String Y_OR_N_REGEX ="[Y|N]"; 
	
	public static final String CLASS_REGEX ="[ABCDEFGHIJKL]"; 
	
	public static final String SWIFT_CODE_REGEX = "[ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-]*";
	
	public static final String OVS_ACC_REGEX = "[ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ]+";

	public static final String CRN_SVN_AUTHTYPE_REGEX ="TR|PR";
	
	public static final String BANK_CODE_REGEX ="012|039|043";
	
	public static final String CHK_LEVEL_REGEX ="0|1|2";
	
	public static final String MER_CODE_REGEX ="\\d{4}";
	
	public static final String DR_LMT_REGEX ="\\d{15}";
	
	public static final String TX_LST_REGEX ="[YN ]*";
	
	public static final String CHANNEL_REGEX = "[E|S]";

	public static final String LANG_REGEX = "[E|C|S]";
	
	public static final int MAX_USER_LENGTH = 6;

	public static final int LEAST_USER_LENGTH = 1;

	public static final int MAX_EMAIL_LENGTH = 30;

	public static final int MIN_EMAIL_LENGTH = 1;

	public static final int MOBILENO_MAX_LENGTH = 12;

	public static final String EMPTY_FIELD = "";

	public static final char SPACE_FIELD = ' ';

	/** account type 'CSS' */
	public final static String ACCOUNT_TYPE_CSS = "CSS";

	/** account type 'CRN' */
	public final static String ACCOUNT_TYPE_CRN = "CRN";

	/** account type 'SVN' */
	public final static String ACCOUNT_TYPE_SVN = "SVN";

	/** account type 'TFS' */
	public final static String ACCOUNT_TYPE_TFS = "TFS";

	/** account type 'FXS' */
	public final static String ACCOUNT_TYPE_FXS = "FXS";

	/** account type 'MTC' */
	public final static String ACCOUNT_TYPE_MTC = "MTCS";

	/** account type 'LNS' */
	public final static String ACCOUNT_TYPE_LNS = "LNS";

	/** account type 'CIF' */
	public final static String ACCOUNT_TYPE_CIF = "CIF";

	/** account type 'ITB' */
	public final static String ACCOUNT_TYPE_ITB = "ITB";

	/**
	 * At least one number[0-9]
	 * 
	 * @param ip_parameter
	 */
	public static boolean isNumberValidate(String ip_parameter)
	{
		return ip_parameter.matches(NUMBER_REGEX);
	}

	/**
	 * Space or number
	 * 
	 * @param ip_parameter
	 */
	public static boolean isEmptyOrNumberValidate(String ip_parameter)
	{
		return ip_parameter.matches(EMPTY_OR_NUMBER_REGEX);
	}

	/**
	 * Validate field is Y/N
	 * 
	 * @param ip_parameter
	 */
	public static boolean isYNValidate(String ip_parameter)
	{
		return ip_parameter.matches(Y_OR_N_REGEX);
	}
	
	/**
	 * Validate field is Y/N/SPACE
	 * 
	 * @param ip_parameter
	 */
	public static boolean isYNSpaceValidate(String ip_parameter)
	{
		if (ip_parameter.trim().equals(""))
			return true;
		return ip_parameter.matches(Y_OR_N_REGEX);
	}
	
	
	/**
	 * Validate Class is A, B, C, D, E, F, G, H, I, J, K, L, space
	 * 
	 * @param ip_parameter
	 */
	public static boolean isClassValidate(String ip_parameter)
	{
		if (ip_parameter.trim().equals(""))
			return true;
		return ip_parameter.matches(CLASS_REGEX);
	}
	
	
	/**
	 * Validate SwiftCode is A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, 
	 * 						 T, U, V, W, X, Y, Z, 1, 2, 3, 4, 5, 6 , 7, 8, 9, -, space
	 * 
	 * @param ip_parameter
	 */
	public static boolean swiftCodeValidate(String ip_parameter)
	{
		return ip_parameter.matches(SWIFT_CODE_REGEX);
	}
	
	/**
	 * Validate SwiftCode is A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, 
	 * 						 T, U, V, W, X, Y, Z, 1, 2, 3, 4, 5, 6 , 7, 8, 9, space
	 * 
	 * @param ip_parameter
	 */
	public static boolean ovsAccNameValidate(String ip_parameter)
	{
		return ip_parameter.matches(OVS_ACC_REGEX);
	}
	
	/**
	 * Validate CRN/SVN should be TR/PR
	 * 
	 * @param ip_parameter
	 */
	public static boolean svnOrCrnTypeValidate(String ip_parameter)
	{
		return ip_parameter.matches(CRN_SVN_AUTHTYPE_REGEX);
	}
	
	/**
	 * Validate CHECKER LEVEL 0/1/2
	 * 
	 * @param ip_parameter
	 */
	
	public static boolean chkLevelValidate(String ip_parameter)
	{
		return ip_parameter.matches(CHK_LEVEL_REGEX);
	}
	
	/**
	 * Validate mer code
	 * 
	 * @param ip_parameter
	 */
	
	/**
	 * Validate bank code 012/039/043
	 * 
	 * @param ip_parameter
	 */
	public static boolean bankCodeValidate(String ip_parameter)
	{
		return ip_parameter.matches(BANK_CODE_REGEX);
	}
	
	/**
	 * Validate mer code
	 * 
	 * @param ip_parameter
	 */
	public static boolean merCodeValidate(String ip_parameter)
	{
		return ip_parameter.matches(MER_CODE_REGEX);
	}
	
	/**
	 * Validate DR LMT
	 * 
	 * @param ip_parameter
	 */
	public static boolean isDrLmtValidate(String ip_parameter)
	{
		return ip_parameter.matches(DR_LMT_REGEX);
	}
	
	/**
	 * Validate tx stat list
	 * 
	 * @param ip_parameter
	 */
	public static boolean txListValidate(String ip_parameter)
	{
		return ip_parameter.matches(TX_LST_REGEX);
	}
	
	/**
	 * Validate Channel E|S
	 * 
	 * @param ip_parameter
	 */
	public static boolean channelValidate(String ip_parameter)
	{
		return ip_parameter.matches(CHANNEL_REGEX);
	}
	
	/**
	 * Validate Lang E|S|C
	 * 
	 * @param ip_parameter
	 */
	public static boolean langValidate(String ip_parameter)
	{
		return ip_parameter.matches(LANG_REGEX);
	}
	
}

