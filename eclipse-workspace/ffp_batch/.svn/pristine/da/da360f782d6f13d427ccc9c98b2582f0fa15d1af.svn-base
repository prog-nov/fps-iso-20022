package com.forms.batch.util;

import java.util.HashMap;

public class MessageValidation {
	private static final String NUMBERIC_ACCOUNT_PATTERN = "\\d{1,35}";
	private static final String EMAIL_PATTERN = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+";
	private static final String MOBIL_PATTERN = "(\\+\\d{3}-\\d{8})|([1-9]{1}\\d{10})";
	private static final String SVID_PATTERN = "\\d{1,35}";
	private static final String CUSTOMER_IDENTIFICATION_PATTERN = "[a-zA-Z0-9]{1,35}";
	
	private static final String REJ_MSG_000001 = "Invalid Account Type!";
	private static final String REJ_MSG_000002 = "Account No Is Not Matched With Account Type!";
	private static final String REJ_MSG_000003 = "Customer ID Required!";
	private static final String REJ_MSG_000004 = "Organization Info And Private Info Could Not Mix!";

	public static HashMap<String, String> validateAccountWithType(String account, String type) {
		String reg = null;
		HashMap<String, String> resMap = new HashMap<>();
		switch (type) {
		case "BBAN":
		case "AIIN":
			reg = NUMBERIC_ACCOUNT_PATTERN;
			break;
		case "EMAL":
			reg = EMAIL_PATTERN;
			break;
		case "MOBN":
			reg = MOBIL_PATTERN;
			break;
		case "SVID":
			reg = SVID_PATTERN;
			break;
		case "CUST":
			reg = CUSTOMER_IDENTIFICATION_PATTERN;
			break;
		default:
			break;
		}

		if (reg == null) {
			resMap.put("result", "0");
			resMap.put("msg", "000001");
		} else if (account.matches(reg)) {
			resMap.put("result", "1");
			resMap.put("msg", "");
		} else {
			resMap.put("result", "0");
			resMap.put("msg", "000002");
		}

		return resMap;
	}

	public static HashMap<String, String> custIdRequired(String type, String orgId, String prvId) {
		HashMap<String, String> resMap = new HashMap<>();
		switch (type) {
		case "BBAN":
		case "AIIN":
			resMap.put("result", "1");
			resMap.put("msg", "");
			return resMap;
		case "EMAL":
		case "MOBN":
		case "SVID":
		case "CUST":
			if (orgId == null && prvId == null) {
				resMap.put("result", "0");
				resMap.put("msg", "000003");
			} else {
				resMap.put("result", "1");
				resMap.put("msg", "");
			}
			return resMap;
		default:
			resMap.put("result", "0");
			resMap.put("msg", "000001");
			return resMap;
		}
	}

	public static HashMap<String, String> doMix(String orgId, String prvtId) {
		HashMap<String, String> resMap = new HashMap<>();
		if (orgId != null && prvtId != null) {
			resMap.put("result", "0");
			resMap.put("msg", "000004");
		} else {
			resMap.put("result", "1");
			resMap.put("msg", "");
		}
		return resMap;
	}

	public static String getRejMsgByCode(String code) {
		String rejMsg = "";

		switch (code) {
		case "000001":
			rejMsg = REJ_MSG_000001;
			break;
		case "000002":
			rejMsg = REJ_MSG_000002;
			break;
		case "000003":
			rejMsg = REJ_MSG_000003;
			break;
		case "000004":
			rejMsg = REJ_MSG_000004;
			break;
		default:
			rejMsg = "Error Rejected Code!";
			break;
		}

		return rejMsg;
	}
}
