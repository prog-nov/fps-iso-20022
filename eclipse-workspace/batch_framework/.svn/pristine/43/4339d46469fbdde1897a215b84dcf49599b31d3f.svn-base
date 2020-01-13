package com.forms.framework.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.forms.framework.exception.BatchFrameworkException;
import com.ibm.icu.charset.CharsetEncoderICU;
import com.ibm.icu.charset.CharsetICU;

enum Sign {
	Positive, Negative, None;
}

public class CharsetConversionUtils {
	private static Logger logger = Logger.getRootLogger();

	public static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static final Map MAPPING_TRA_SIM = new HashMap();

	private static Map<String, String> configCache = null;
	private static Charset IBM937_charset = CharsetICU
			.forNameICU("ibm-937_P110-1999-BOC-20110812");
	private static final int[] SIGN_NIBBLE_NEGATIVES = { 10, 13 };
	private static Charset IBM935_charset = CharsetICU.forNameICU("ibm-935");

	private static Charset getCharset(String codePage) {
		Charset charset = null;
		if ("ibm-937_P110-1999-BOC-20110812".equals(codePage))
			charset = IBM937_charset;
		else if ("ibm-935".equals(codePage))
			charset = IBM935_charset;
		else {
			charset = CharsetICU.forNameICU(codePage);
		}
		return charset;
	}

	private static boolean isCanEncode(String codePage, String source) {
		if (logger.isDebugEnabled()) {
			logger.debug("isCanEncode is invoked,codepage=" + codePage
					+ ",source=" + source);
		}
		Charset charset = getCharset(codePage);
		Object set = charset.newEncoder();
		if (set instanceof CharsetEncoderICU) {
			CharsetEncoderICU icu = (CharsetEncoderICU) set;
			icu.setFallbackUsed(true);
			boolean b = icu.canEncode(source);
			if (logger.isDebugEnabled()) {
				logger
						.debug("set instanceof CharsetEncoderICU= true,result is="
								+ b);
			}
			return b;
		}
		if (logger.isDebugEnabled()) {
			logger
					.debug("set instanceof CharsetEncoderICU= false,so return false for method invoked");
		}
		return false;
	}

	private static int getInt(char c) throws UnsupportedEncodingException {
		switch (c) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		case ':':
		case ';':
		case '<':
		case '=':
		case '>':
		case '?':
		case '@':
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case '[':
		case '\\':
		case ']':
		case '^':
		case '_':
		case '`':
		}
		throw new UnsupportedEncodingException(c + " is not a valid hex char");
	}

	private static String getConfig(String key) {
		synchronized (CharsetConversionUtils.class) {
			if ((configCache == null) || (configCache.size() == 0)) {
				loadConfig();
			}
		}
		String s = (String) configCache.get(key);
		return s;
	}

	@SuppressWarnings("unchecked")
	private static void loadConfig() {
		configCache = new ConcurrentHashMap();

		String resource = "com.boc.mcf.conv.chineseRange";
		ResourceBundle rb = ResourceBundle.getBundle(resource, Locale
				.getDefault());

		configCache.put("SIMPLIFIED_RANGE", rb.getString("SIMPLIFIED_RANGE"));
		configCache.put("TRADITIONAL_RANGE", rb.getString("TRADITIONAL_RANGE"));
		configCache.put("GB2312_RANGE", rb.getString("GB2312_RANGE"));
		configCache.put("IBM937_DB_NONE_CHAR", rb
				.getString("IBM937_DB_NONE_CHAR"));
		configCache.put("IBM935_DB_NONE_CHAR", rb
				.getString("IBM935_DB_NONE_CHAR"));
		configCache.put("IBM937_SG_NONE_CHAR", rb
				.getString("IBM937_SG_NONE_CHAR"));
		configCache.put("IBM935_SG_NONE_CHAR", rb
				.getString("IBM935_SG_NONE_CHAR"));
		configCache.put("FORCE_CONVERT_TRA2SIM", rb
				.getString("FORCE_CONVERT_TRA2SIM"));
	}

	private static void loadCharsetMapping() throws Exception {
		loadCharsetMappingInJavaFile();
	}

	@SuppressWarnings("unchecked")
	private static void loadCharsetMappingInJavaFile() throws Exception {
		MAPPING_TRA_SIM.clear();
		try {
			String tra_sim = getTraSimFondEntry();
			if (logger.isDebugEnabled()) {
				logger.debug("loadCharsetMappingInJavaFile being called");
			}
			String[] t = tra_sim.split(",");
			if ((tra_sim == null) || (t.length % 2 != 0)) {
				throw new Exception(
						"count of traditional not equal count of simplified chinese");
			}

			int c = t.length / 2;
			if (logger.isDebugEnabled()) {
				logger.debug("tra.size=" + c);
			}
			for (int row = 0; row < t.length; row += 2) {
				String tra = getCodePointInteger(t[row]);

				String sim = getCodePointString(t[(row + 1)]);

				if (MAPPING_TRA_SIM.containsKey(tra)) {
					if (!(logger.isDebugEnabled()))
						continue;
					logger.debug("in java file MAPPING_TRA_SIM have two ="
							+ tra + " key,index=" + (row / 2) + ",sim(haved)="
							+ MAPPING_TRA_SIM.get(tra) + ",new sim=" + sim);
				} else {
					MAPPING_TRA_SIM.put(tra, sim);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static String bytesToHex(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		char[] chars = new char[bytes.length * 2];
		int i = 0;
		for (int j = 0; i < bytes.length; ++i) {
			byte b1 = bytes[i];
			chars[(j++)] = DIGITS[((b1 & 0xF0) >>> 4)];
			chars[(j++)] = DIGITS[(b1 & 0xF)];
		}
		return new String(chars);
	}

	public static String byteToHex(byte b) {
		char[] chars = new char[2];
		chars[0] = DIGITS[((b & 0xF0) >>> 4)];
		chars[1] = DIGITS[(b & 0xF)];
		return new String(chars);
	}

	public static byte[] zonedDecToPackDec(byte[] zonedDec)
			throws BatchFrameworkException {
		if (zonedDec == null) {
			throw new NullPointerException("input data must not be null");
		}
		if (zonedDec.length == 0) {
			return new byte[0];
		}
		return stringToPackDecimal(zonedDecimalToString(zonedDec));
	}

	public static byte[] packDecToZonedDec(byte[] packDec)
			throws BatchFrameworkException {
		if (packDec == null) {
			throw new NullPointerException("input data must not be null");
		}
		if (packDec.length == 0) {
			return new byte[0];
		}
		return stringToZonedDecimal(packDecimalToString(packDec));
	}

	public static String convertIBM937ToUnicode(byte[] sources)
			throws UnsupportedEncodingException {
		return convert(sources, "ibm-937_P110-1999-BOC-20110812");
	}

	public static byte[] convertUnicodeToIBM937(String sources)
			throws UnsupportedEncodingException {
		return convert(sources, "ibm-937_P110-1999-BOC-20110812");
	}

	public static String convertIBM935ToUnicode(byte[] sources)
			throws UnsupportedEncodingException {
		return convert(sources, "ibm-935");
	}

	public static byte[] convertUnicodeToIBM935(String sources)
			throws UnsupportedEncodingException {
		return convert(sources, "ibm-935");
	}

	public static String convertUniTraCnToUniSimCn(String source)
			throws UnsupportedEncodingException {
		String config = getConfig("FORCE_CONVERT_TRA2SIM");
		boolean isForce = "Y".equals(config);
		return convertUniTraCnToUniSimCn(source, isForce);
	}

	public static String convertUniTraCnToUniSimCn(String source,
			boolean forceConvert) throws UnsupportedEncodingException {
		if (source == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();

		int c = source.length();
		for (int i = 0; i < c; ++i) {
			int cp = Character.codePointAt(source, i);
			char[] cs = Character.toChars(cp);
			if (cs.length > 1) {
				++i;
			}

			String s = String.valueOf(cp);
			if (MAPPING_TRA_SIM.containsKey(s)) {
				sb.append(MAPPING_TRA_SIM.get(s));
			} else {
				if (forceConvert) {
					throw new UnsupportedEncodingException("char " + s
							+ " can not be converted");
				}

				s = new String(cs);
				sb.append(s);
			}
		}

		return sb.toString();
	}

	public static int getInt(String s) throws UnsupportedEncodingException {
		int b = 0;
		int l = 1;
		s = s.toLowerCase();
		for (int i = s.length() - 1; i >= 0; --i) {
			char c = s.charAt(i);
			int k = getInt(c);
			b += k * l;
			l *= 16;
		}
		return b;
	}

	public static String getCodePointString(String s) throws Exception {
		String back = null;
		try {
			String t;
			if (s.length() > 4) {
				char[] c = Character.toChars(getInt(s));
				t = new String(c);
				back = t;
			} else {
				byte[] bb = getBytes(s);
				t = new String(bb, "utf16");
				back = t;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return back;
	}

	private static String getCodePointInteger(String s) throws Exception {
		String back = getCodePointString(s);
		int c = back.codePointCount(0, back.length());
		if (c > 1) {
			throw new Exception(
					"mapping create error:hex code error in FontCode.java ,error code is "
							+ s);
		}

		int cp = back.codePointAt(0);
		back = String.valueOf(cp);
		return back;
	}

	public static boolean isContainsTraditionalChinese(String source)
			throws Exception {
		return isTraditional(source);
	}

	public static boolean isContainsSimplifiedChinese(String source)
			throws Exception {
		return isSimplified(source);
	}

	public static boolean isGB2312Chinese(String source) throws Exception {
		if (source == null) {
			return false;
		}
		byte[] b = convertUnicodeToIBM935(source);
		boolean back = isGB2312Chinese(b);
		return back;
	}

	public static boolean isTraditionalChinese(String source) throws Exception {
		if (logger.isDebugEnabled()) {
			try {
				if (source == null) {
					logger.debug("isTraditionalChinese input=null");
				} else {
					logger.debug("isTraditionalChinese input=" + source);
					logger.debug("isTraditionalChinese input(hex,utf-8)="
							+ toHexString(source.getBytes("utf-8")));
				}
			} catch (Exception ex) {
			}
		}
		boolean b = isCanEncode("ibm-937_P110-1999-BOC-20110812", source);
		if (logger.isDebugEnabled()) {
			logger.debug("isTraditionalChinese back=" + b);
		}
		return b;
	}

	public static boolean isSimplifiedChinese(String source) throws Exception {
		if (logger.isDebugEnabled()) {
			try {
				if (source == null) {
					logger.debug("isSimplifiedChinese input=null");
				} else {
					logger.debug("isSimplifiedChinese input=" + source);
					logger.debug("isSimplifiedChinese input(hex,utf-8)="
							+ toHexString(source.getBytes("utf-8")));
				}
			} catch (Exception ex) {
			}
		}
		boolean b = isCanEncode("ibm-935", source);
		if (logger.isDebugEnabled()) {
			logger.debug("isSimplifiedChinese back=" + b);
		}
		return b;
	}

	private static boolean isTraditional(String source)
			throws UnsupportedEncodingException {
		if (source == null) {
			return false;
		}
		byte[] b = convertUnicodeToIBM937(source);
		boolean back = isTraChinese(b);
		return back;
	}

	private static boolean isSimplified(String source)
			throws UnsupportedEncodingException {
		if (source == null) {
			return false;
		}
		byte[] b = convertUnicodeToIBM935(source);
		boolean back = isSimChinese(b);
		return back;
	}

	private static boolean isSimChinese(byte[] b)
			throws UnsupportedEncodingException {
		String s = getConfig("SIMPLIFIED_RANGE");
		int[][] range = getRange(s);
		return isChinese(b, 0, range);
	}

	private static boolean isGB2312Chinese(byte[] b)
			throws UnsupportedEncodingException {
		String s = getConfig("GB2312_RANGE");
		int[][] range = getRange(s);
		return isChinese(b, 0, range, true);
	}

	private static boolean isTraChinese(byte[] b)
			throws UnsupportedEncodingException {
		String s = getConfig("TRADITIONAL_RANGE");
		int[][] range = getRange(s);
		return isChinese(b, 0, range);
	}

	private static int[][] getRange(String s)
			throws UnsupportedEncodingException {
		String[] t = s.split(",");
		int[][] range = new int[t.length][2];
		for (int i = 0; i < t.length; ++i) {
			String[] f = t[i].split("-");
			if (f.length == 2) {
				byte[] b0 = getBytes(f[0]);
				byte[] b1 = getBytes(f[1]);
				if ((b0.length == 2) && (b1.length == 2)) {
					int x = getInt(b0[0], b0[1]);
					int y = getInt(b1[0], b1[1]);
					range[i][0] = x;
					range[i][1] = y;
				} else {
					throw new UnsupportedEncodingException(
							"chineseRange.properties config error,config=" + s
									+ "," + f[0] + " and " + f[1]
									+ " .bytes length should be 2");
				}
			} else {
				throw new UnsupportedEncodingException(
						"chineseRange.properties config error,config=" + s
								+ "," + t[i]
								+ " should be range1-range2 format");
			}
		}
		return range;
	}

	private static boolean isChinese(byte[] b, int start, int[][] range) {
		boolean back = isChinese(b, start, range, false);
		return back;
	}

	private static boolean isChinese(byte[] b, int start, int[][] range,
			boolean needAll) {
		if (start >= b.length) {
			return false;
		}
		if ((b == null) || (b.length < 2)) {
			return false;
		}
		byte oe = 14;
		byte of = 15;
		int e = -1;
		int f = -1;
		int end = -1;
		for (int i = start; i < b.length; ++i) {
			if (b[i] == oe) {
				e = i;
			} else if (b[i] == of) {
				f = i;
				end = i + 1;
				break;
			}
		}
		if ((needAll) && (((e != start) || (f != b.length - 1)))) {
			return false;
		}

		if ((e != -1) && (f != -1)) {
			for (int i = e + 1; i < f; i += 2) {
				int c = getInt(b[i], b[(i + 1)]);
				boolean back = isAtrange(range, c);
				if (!(needAll)) {
					if (!(back))
						continue;
					return true;
				}

				if (!(back)) {
					return false;
				}
			}

			if ((needAll) && (end >= b.length)) {
				return true;
			}

			return isChinese(b, end, range, needAll);
		}
		return false;
	}

	private static boolean isAtrange(int[][] range, int v) {
		for (int i = 0; i < range.length; ++i) {
			if ((v >= range[i][0]) && (v <= range[i][1])) {
				return true;
			}
		}
		return false;
	}

	public static int getInt(byte b1, byte b2) {
		int c1 = b1;
		int c2 = b2;

		c1 = (c1 >= 0) ? c1 : 256 + c1;
		c2 = (c2 >= 0) ? c2 : 256 + c2;
		c1 *= 256;
		int c = c1 + c2;
		return c;
	}

	private static String convert(byte[] sources, String codePage)
			throws UnsupportedEncodingException {
		Charset charset = getCharset(codePage);
		CharBuffer charBuffer = charset.decode(ByteBuffer.wrap(sources));
		return charBuffer.toString();
	}

	private static byte[] convert(String sources, String codePage)
			throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("convert invoked,sources=" + sources + ",codePage="
					+ codePage);
		}
		Charset charset = getCharset(codePage);
		ByteBuffer byteBuffer = charset.encode(sources);
		if (!(byteBuffer.hasArray())) {
			return new byte[0];
		}
		int i = byteBuffer.limit();
		byte[] b = new byte[i];
		byte[] c = byteBuffer.array();
		System.arraycopy(c, 0, b, 0, i);
		return b;
	}

	public static int toUnsignedByte(byte b) {
		if (b < 0) {
			return (b + 256);
		}
		return b;
	}

	public static String toHexString(byte b) {
		String s = Integer.toHexString(toUnsignedByte(b));
		if (s.length() == 1)
			return "0" + s;
		return s;
	}

	public static String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			sb.append(toHexString(bytes[i]));
		}
		return sb.toString();
	}

	static {
		try {
			loadCharsetMapping();
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled())
				logger.debug("error when loadCharsetMapping,message="
						+ e.getMessage());
		}
	}

	public static byte[] getBytes(String s) throws UnsupportedEncodingException {
		s = s.toLowerCase();
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		int len = (s.length() - (s.length() % 2)) / 2;
		byte[] b = new byte[len];
		int count = 0;

		for (int i = 0; i < s.length(); i += 2) {
			char c = s.charAt(i);
			int h = getInt(c);
			c = s.charAt(i + 1);
			int l = getInt(c);
			int by = h * 16 + l;

			b[count] = new Integer(by).byteValue();
			++count;
			if (count >= len) {
				break;
			}
		}
		return b;
	}

	public static byte[] stringToZonedDecimal(String decimal)
			throws BatchFrameworkException {
		if (decimal == null) {
			throw new NullPointerException("input data must not be null.");
		}
		if ("".equals(decimal)) {
			return new byte[0];
		}
		Sign sign = Sign.None;
		String value = decimal;
		String signNibble = value.substring(0, 1);
		if (signNibble.equals("+")) {
			value = value.substring(1);
			sign = Sign.Positive;
		} else if (signNibble.equals("-")) {
			value = value.substring(1);
			sign = Sign.Negative;
		}
		byte[] zonedBytes = new byte[value.length()];
		for (int i = 0; i < value.length(); ++i) {
			if (!(Character.isDigit(value.charAt(i)))) {
				throw new BatchFrameworkException("Invalid Digit: "
						+ value.charAt(i) + " in " + decimal);
			}

			int digit = Integer.valueOf(value.charAt(i)).intValue();
			zonedBytes[i] = (byte) (digit & 0xF | 0xF0);
		}

		int lastIndex = zonedBytes.length - 1;
		if (sign == Sign.Positive)
			zonedBytes[lastIndex] = (byte) (zonedBytes[lastIndex] & 0xF | 0xC0);
		else if (sign == Sign.Negative) {
			zonedBytes[lastIndex] = (byte) (zonedBytes[lastIndex] & 0xF | 0xD0);
		}
		return zonedBytes;
	}

	public static String packDecimalToString(byte[] packedDecimal)
			throws BatchFrameworkException {
		if (packedDecimal == null) {
			throw new NullPointerException("Input data must not be null.");
		}
		if (packedDecimal.length == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer(32);

		for (int i = 0; i < packedDecimal.length - 1; ++i) {
			int highNibble = (packedDecimal[i] & 0xF0) >>> 4;
			if (highNibble > 9) {
				throw new BatchFrameworkException("Invalid decimal digit: "
						+ highNibble + " in " + bytesToHex(packedDecimal));
			}
			result.append(DIGITS[highNibble]);
			int lowNibble = packedDecimal[i] & 0xF;
			if (lowNibble > 9) {
				throw new BatchFrameworkException("Invalid decimal digit: "
						+ lowNibble + " in " + bytesToHex(packedDecimal));
			}
			result.append(DIGITS[lowNibble]);
		}

		int lastNibble = (packedDecimal[(packedDecimal.length - 1)] & 0xF0) >>> 4;
		if (lastNibble > 9) {
			throw new BatchFrameworkException("Invalid decimal digit: "
					+ lastNibble + " in " + bytesToHex(packedDecimal));
		}
		result.append(DIGITS[lastNibble]);
		int signNibble = packedDecimal[(packedDecimal.length - 1)] & 0xF;
		if (signNibble < 10) {
			throw new BatchFrameworkException("Invalid deciaml sign: "
					+ bytesToHex(packedDecimal));
		}

		if (isSignNegative(signNibble)) {
			result.insert(0, '-');
		} else if (12 == signNibble) {
			result.insert(0, '+');
		}
		return result.toString();
	}

	private static final boolean isSignNegative(int signNibble) {
		for (int i = 0; i < SIGN_NIBBLE_NEGATIVES.length; ++i) {
			if (signNibble == SIGN_NIBBLE_NEGATIVES[i]) {
				return true;
			}
		}
		return false;
	}

	private static final boolean isSignNone(int signNibble) {
		return (15 == signNibble);
	}

	public static String zonedDecimalToString(byte[] zonedBytes)
			throws BatchFrameworkException {
		if (zonedBytes == null) {
			throw new NullPointerException("input data must not be null");
		}
		if (zonedBytes.length == 0) {
			return "";
		}
		int lastIndex = zonedBytes.length - 1;
		StringBuffer decimal = new StringBuffer();
		for (int i = 0; i < lastIndex; ++i) {
			byte nibble = zonedBytes[i];

			getHighNibbleIntValue(nibble, zonedBytes);
			decimal.append(getLowNibbleIntValue(nibble, zonedBytes));
		}
		decimal.append(getLowNibbleIntValue(zonedBytes[lastIndex], zonedBytes));
		String value = decimal.toString();
		return getSign(zonedBytes[lastIndex], zonedBytes) + value;
	}

	private static String getSign(byte nibble, byte[] zonedBytes)
			throws BatchFrameworkException {
		int signNibble = (nibble & 0xF0) >>> 4;
		if (isSignPositive(signNibble)) {
			return "+";
		}

		if (isSignNegative(signNibble)) {
			return "-";
		}
		if (isSignNone(signNibble)) {
			return "";
		}
		throw new BatchFrameworkException("Invalid sign nibble: "
				+ byteToHex(nibble) + " in " + bytesToHex(zonedBytes));
	}

	private static final boolean isSignPositive(int signNibble) {
		return (12 == signNibble);
	}

	private static int getLowNibbleIntValue(byte nibble, byte[] zonedBytes)
			throws BatchFrameworkException {
		int digit = Integer.valueOf((byte) (nibble & 0xF | 0x0)).intValue();
		if ((digit < 0) || (digit > 9)) {
			throw new BatchFrameworkException("Invalid low nibble: "
					+ byteToHex(nibble) + " in " + bytesToHex(zonedBytes));
		}

		return digit;
	}

	private static int getHighNibbleIntValue(byte nibble, byte[] zonedBytes)
			throws BatchFrameworkException {
		int digit = Integer.valueOf((byte) ((nibble & 0xF0) >>> 4)).intValue();
		if (digit != 15) {
			throw new BatchFrameworkException("Invalid high nibble: "
					+ byteToHex(nibble) + " in " + bytesToHex(zonedBytes));
		}

		return digit;
	}

	public static byte[] stringToPackDecimal(String decimal)
			throws BatchFrameworkException {
		if (decimal == null) {
			throw new NullPointerException("input data must not be null.");
		}
		if ("".equals(decimal)) {
			return new byte[0];
		}
		int digitCount = getDigitCount(decimal);
		if (digitCount == 0) {
			throw new BatchFrameworkException("Invalid number[" + decimal);
		}
		byte[] packedDecimal = new byte[getTargetPackedDecimalSize(digitCount)];

		packedDecimal[(packedDecimal.length - 1)] = 15;

		boolean arrayIndexToNext = true;
		int charIndex = decimal.length() - 1;
		for (int arrayIndex = packedDecimal.length - 1; charIndex >= 0;) {
			char nibble = decimal.charAt(charIndex);

			if (('0' <= nibble) && ('9' >= nibble)) {
				byte digit = (byte) (nibble - '0');
				if (arrayIndexToNext) {
					packedDecimal[arrayIndex] = (byte) (packedDecimal[arrayIndex] | digit << 4);
					arrayIndexToNext = false;
					--arrayIndex;
				} else {
					packedDecimal[arrayIndex] = (byte) (packedDecimal[arrayIndex] | digit);
					arrayIndexToNext = true;
				}
				--charIndex;
			} else {
				switch (nibble) {
				case ' ':
				case '$':
				case ',':
				case '.':
					--charIndex;
					break;
				case '+':
					packedDecimal[(packedDecimal.length - 1)] = (byte) (packedDecimal[(packedDecimal.length - 1)] & 0xF0 | 0xC);

					--charIndex;
					break;
				case '-':
					packedDecimal[(packedDecimal.length - 1)] = (byte) (packedDecimal[(packedDecimal.length - 1)] & 0xF0 | 0xD);

					--charIndex;
					break;
				case '!':
				case '"':
				case '#':
				case '%':
				case '&':
				case '\'':
				case '(':
				case ')':
				case '*':
				default:
					throw new BatchFrameworkException(
							"Invalid decimal digit: " + nibble
									+ " from input: " + decimal);
				}
			}
		}

		int noneZeroIndex = getNoneZeroIndex(packedDecimal);
		int realLength = packedDecimal.length - noneZeroIndex;
		byte[] realPackedDecimal = new byte[realLength];
		System.arraycopy(packedDecimal, noneZeroIndex, realPackedDecimal, 0,
				realLength);
		return packedDecimal;
	}

	private static int getDigitCount(String decimal) {
		if (decimal == null) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < decimal.length(); ++i) {
			char digit = decimal.charAt(i);
			if (Character.isDigit(digit)) {
				++count;
			}
		}
		return count;
	}

	private static int getTargetPackedDecimalSize(int digitCount) {
		if ((digitCount - 1) % 2 == 0) {
			return ((digitCount - 1) / 2 + 1);
		}
		return ((digitCount - 1) / 2 + 2);
	}

	private static int getNoneZeroIndex(byte[] packedDecimal) {
		int i = 0;
		for (int n = packedDecimal.length; i < n; ++i) {
			int digit = Integer.valueOf(packedDecimal[i]).intValue();
			if (digit > 0) {
				return i;
			}
		}
		return 0;
	}

	public static String getTraSimFondEntry() {
		StringBuffer sb = new StringBuffer();
		sb
				.append(
						"00AF,2013,02CD,FF3F,2027,FF0E,20302,4F1E,20308,547D,20542,518D,20545,518D,207B0,527F,20AB1,5386,20D30,54B1,20EF3,55FD,21681,5F0A,21A18,51A4,21B36,5BFB,22B38,62EC,22BA5,64CD,22DEC,6363,2365C,69B7,23E8C,6E3A,242EE,7199,24A0F,7410,24C1C,4EA9,24C48,4EA9,24C4A,7559,24EA5,7629,25128,7785,2574,2581,2591A,79CB,25997,7A97,260B3,7D27,260C2,7D27,26246,78B4,26351,7F8C,26548,7707,26703,51A5,26D4F,846C,28F7B,96B6,294D0,8116,295D7,98D3,347A,4FCA,34BA,7F54,3541,5374,3551,53A8,3565,4EE5,362D,5773,375B,5BBF,3760,51A5,3761,6700,37C1,5CB8,3800,5C9B,382F,4EE5,3836,5E06,384C,5E3D,3898,5EC9,38A0,56DE,3919,6069,3966,60EC,396B,60C7,39F1,62FF,3A17,6377,3A2A,6643,3A3F,636E,3A57,643A,3A66,643A,3A9A,6563,3A9F,6566,3B09,6696,3BED,6A79,3C43,996E,3CC4,6D8E,3CD2,6CD5,3D31,6DF1,3F1D,7897,3F5E,7559,3FDC,762A,4230,7B72,4280,7CCA,45EC,8702,460F,6064,4611,8109,461A,5352,46D0,8BCD,4754,737E,47D6,8D9F,4800,8D9F,4836,5C04,4965,9570,4B03,98D2,4B7E,9A6E,4C1F,9B42,4C3E,9C83,4C81,9CDA,4C98,9CE4,4CD8,9E45,4D09,9E6E,4D38,9EB8,4D8A,8844,4E1F,4E22,4E57,4E58,4E79,4E7E,4E81,4E7E,4E82,4E71,4E99,4E98,4E9D,658B,4E9E,4E9A,4EAF,4EAB,4EB1,591C,4EB7,5EC9,4EBE,4EA1,4F40,4F3C,4F47,4F2B,4F48,5E03,4F54,5360,4F75,5E76,4F86,6765,4F96,4ED1,4FB6,4FA3,4FB7,5C40,4FC1,4FE3,4FC2,7CFB,4FD4,4F23,4FDB,4FEF,4FE0,4FA0,4FFB,5907,5000,4F25,5003,54B1,5006,4FE9,5008,4FEB,5009,4ED3,500B,4E2A,5010,500F,5011,4EEC,5016,5E78,5023,4EFF,502B,4F26,5038,776C,5049,4F1F,5058,4F83,506A,903C,5074,4FA7,5075,4FA6,507A,54B1,507D,4F2A,508C,9A82,5091,6770,5096,4F27,5098,4F1E,5099,5907,509A,6548,50A2,5BB6,50AD,4F63,50AF,506C,50B3,4F20,50B4,4F1B,50B5,503A,50B7,4F24,50BE,503E,50C2,507B,50C5,4EC5,50C9,4F65,50CA,4ED9,50CD,52A8,50D1,4FA8,50D5,4EC6,50DE,4F2A,50E5,4FA5,50E8,507E,50F1,96C7,50F9,4EF7,5100,4EEA,5101,4FCA,5102,4FAC,5104,4EBF,5108,4FA9,5109,4FED,510C,4FA5,5110,50A7,5114,4FE6,5115,4FAA,5117,62DF,5118,5C3D,511F,507F,512A,4F18,5132,50A8,5135,500F,5137,4FEA,513A,50A9,513B,50A5,513C,4FE8,5147,51F6,514C,5151,514E,5154,5152,513F,5157,5156,5160,515C,5167,5185,5169,4E24,5184,5189,518A,518C,5190,5192,51A3,6700,51AA,5E42,51BA,6CEF,51CD,51BB,51E2,51E1,51F1,51EF,51F4,51ED,5225,522B,5226,52AB,5227,52AB,522A,5220,523C,52AB,5244,522D,5247,5219,5249,9509,524F,521B,5259,521B,525B,521A,525D,5265,526E,5250,5273,672D,5274,5240,5275,521B,5277,94F2,5279,622E,5283,5212,5284,672D,5287,5267,5289,5218,528A,523D,528C,523F,528D,5251,5292,5251,52B5,5238,52B9,6548,52C1,52B2,52C5,6555,52CC,5026,52D5,52A8,52D7,52D6,52D9,52A1,52DB,52CB,52DD,80DC,52DE,52B3,52E2,52BF,52E6,527F,52E9,52DA,52F1,52A2,52F3,52CB,52F5,52B1,52F8,529D,52FB,5300,5303,4E10,5304,4E10,531F,7095,532D,5326,532F,6C47,5331,532E,5332,5941,5333,5941,5340,533A,5354,534F,5379,6064,537B,5374,5380,819D,5399,538D,53A0,5395,53A4,5386,53AD,538C,53B2,5389,53B4,53A3,53C3,53C2,53C5,53C2,53E1,777F,53E2,4E1B,541A,54BF,5433,5434,5442,5415,544C,53EB,546A,5492,548A,548C,54B2,7B11,54BC,5459,54E1,5458,54F6,54A9,5504,5457,5515,5523,5518,542F,552B,541F,5538,5FF5,554E,5FE4,554F,95EE,5551,558B,5553,542F,5557,5556,555E,54D1,555F,542F,5563,8854,5592,54B1,559A,5524,55AA,4E27,55AB,5403,55AC,4E54,55AE,5355,55B2,54DF,55C1,557C,55C6,545B,55C7,556C,55CA,551D,55CE,5417,55DA,545C,55E9,5522,55F6,54D4,5605,6168,5606,53F9,560D,55BD,5611,547C,5614,5455,5616,5567,5617,5C1D,561C,551B,5620,560E,5629,54D7,562E,5520,562F,5578,5630,53FD,5635,54D3,5637,55E5,5638,5452,563D,5574,5649,5556,5653,5618,565D,549D,5660,54D2,5665,54DD,5666,54D5,566F,55F3,5672,54D9,5674,55B7,5678,5428,5679,5F53,5680,549B,5687,5413,5690,5C1D,5695,565C,5699,556E,56A5,54BD,56A6,5456,56A8,5499,56AE,5411,56B2,4EB8,56B3,55BE,56B4,4E25,56B6,5624,56C0,556D,56C1,55EB,56C2,56A3,56C5,5181,56C8,5453,56C9,5570,56CC,82CF,56D1,5631,56D3,556E,56D9,56E0,5705,51FD,5707,56F5,570B,56FD,570D,56F4,5712,56ED,5713,5706,5716,56FE,5718,56E2,5775,4E18,577F,9644,579C,579B,57BB,575D,57DC,91CE,57E1,57AD,57F3,574E,57F7,6267,5805,575A,580A,57A9,5816,57B4,5818,584D,581D,57DA,5826,9636,582F,5C27,5831,62A5,5834,573A,583F,78B1,584A,5757,584B,8314,584F,57B2,5852,57D8,5857,6D82,585A,51A2,585F,846C,5862,575E,5864,57D9,5872,573A,5875,5C18,5879,5811,587C,7816,588A,57AB,5896,5854,589C,5760,58AA,58A9,58AE,5815,58B3,575F,58B6,57AF,58BB,5899,58BE,57A6,58C4,91CE,58C7,575B,58CB,57B1,58CE,57D9,58D3,538B,58D8,5792,58D9,5739,58DA,5786,58DC,575B,58DE,574F,58DF,5784,58E2,575C,58E9,575D,58EA,5846,58EF,58EE,58FA,58F6,58FB,5A7F,58FC,58F8,58FD,5BFF,5918,536F,5920,591F,5922,68A6,593E,5939,5950,5942,5967,5965,5969,5941,596A,593A,596C,5956,596E,594B,599D,5986,59AC,5992,59B3,4F60,59B7,4F84,59C9,59CA,59CD,59D7,59D9,598A,59E6,5978,59EA,4F84,5A1B,5A31,5A3F,5A40,5A41,5A04,5A50,5A40,5A63,59FB,5A66,5987,5A6C,6DEB,5A6D,5A05,5A8D,5987,5AA7,5A32,5AAE,5077,5AAF,59AB,5ABC,5AAA,5ABD,5988,5ABF,6127,5ACB,8885,5AD7,59AA,5AF0,5AE9,5AF5,59A9,5AFA,5A34,5AFB,5A34,5AFF,5A73,5B00,59AB,5B03,5AAD,5B08,5A06,5B0B,5A75,5B0C,5A07,5B19,5AF1,5B1D,8885,5B21,5AD2,5B24,5B37,5B2A,5AD4,5B2D,5976,5B30,5A74,5B38,5A76,5B3E,61D2,5B43,5A18,5B4C,5A08,5B6B,5B59,5B78,5B66,5B7C,5B7D,5B7F,5B6A,5B82,5197,5BAE,5BAB,5BBC,5BC7,5BC0,91C7,5BC3,51A4,5BD1,5BDD,5BD4,5B9E,5BD5,5B81,5BD8,7F6E,5BE2,5BDD,5BE6,5B9E,5BE7,5B81,5BE9,5BA1,5BEB,5199,5BEC,5BBD,5BF3,5B9D,5BF5,5BA0,5BF6,5B9D,5C05,514B,5C07,5C06,5C08,4E13,5C0B,5BFB,5C0D,5BF9,5C0E,5BFC,5C12,5C14,5C1F,9C9C,5C20,9C9C,5C37,5C34,5C46,5C4A,5C4D,5C38,5C53,5C43,5C62,5C61,5C64,5C42,5C68,5C66,5C6C,5C5E,5C85,5742,5CA1,5188,5CDD,5CD2,5CE9,5CE8,5CEF,5CF0,5CF4,5C98,5CF6,5C9B,5CFD,5CE1,5D0D,5D03,5D10,6606,5D11,6606,5D17,5C97,5D18,4ED1,5D19,4ED1,5D22,5CE5,5D2C,5CBD,5D50,5C9A,5D52,5CA9,5D57,5C81,5D81,5D5D,5D83,5D2D,5D84,5D2D,5D87,5C96,5D94,5D5A,5D97,5D02,5DA0,5CE4,5DA2,5CE3,5DA7,5CC4,5DA8,5CC3,5DAE,5D04,5DB8,5D58,5DBA,5CAD,5DBC,5C7F,5DBD,5CB3,5DCB,5CBF,5DD2,5CE6,5DD4,5DC5,5DD6,5CA9,5DD7,5CA9,5DF0,5DEF,5DF5,536E,5E00,531D,5E0B,7EB8,5E25,5E05,5E2B,5E08,5E2C,88D9,5E33,5E10,5E36,5E26,5E40,5E27,5E43,5E0F,5E47,5E2E,5E51,5FBD,5E57,5E3C,5E58,5E3B,5E59,5E55,5E5A,5E2E,5E5F,5E1C,5E63,5E01,5E6B,5E2E,5E6C,5E31,5E79,5E72,5E7E,51E0,5EAB,5E93,5EBB,5EB6,5EBD,5BD3,5EC1,5395,5EC2,53A2,5EC4,53A9,5EC8,53A6,5ECE,5EBC,5ED0,53A9,5ED5,836B,5EDA,53A8,5EDD,53AE,5EDF,5E99,5EE0,5382,5EE1,5E91,5EE2,5E9F,5EE3,5E7F,5EEC,5E90,5EF3,5385,5EF5,5DE1,5EF9,8FEB,5EFC,4E43,5F14,540A,5F35,5F20,5F37,5F3A,5F46,522B,5F48,5F39,5F4A,5F3A,5F4C,5F25,5F4E,5F2F,5F59,6C47,5F60,5F5F,5F65,5F66,5F6B,96D5,5F7F,4F5B,5F83,5F80,5F8C,540E,5F91,5F84,5F9E,4ECE,5FA0,5F95,5FA7,904D,5FA9,590D,5FB9,5F7B,6031,5306,6033,604D,6046,6052,6060,602A,6061,541D,6065,803B,6085,60A6,60A4,5306,60B5,6005,60B6,95F7,60BD,51C4,60CF,5A6A,60E1,6076,60E5,607F,60EA,5FB7,60F1,607C,60F2,607D,60F7,8822,60FB,607B,611B,7231,611C,60EC,612C,8BC9,6134,6006,6137,607A,613D,535A,613E,5FFE,6142,607F,6147,6BB7,614B,6001,614D,6120,6158,60E8,6159,60ED,615A,60ED,615F,6078,6163,60EF,6164,60AB,616A,6004,616B,6002,616E,8651,6173,60AD,6174,6151,6176,5E86,617C,621A,617D,621A,617E,6B32,6182,5FE7,6187,61A9,618A,60EB,6190,601C,6191,51ED,6192,6126,6196,616D")
				.append(
						",619A,60EE,61A4,6124,61AB,60AF,61AE,6003,61B2,5BAA,61B6,5FC6,61C3,52E4,61C7,6073,61C9,5E94,61CC,603F,61DE,8499,61DF,603C,61E3,61D1,61E8,6079,61F2,60E9,61F6,61D2,61F7,6000,61F8,60AC,61FA,5FCF,61FC,60E7,61FD,6B22,61FE,6151,6200,604B,6207,6206,6214,620B,621E,621B,6227,6217,622F,620F,6230,6218,6232,620F,6236,6237,6239,5384,623C,536F,629D,62D7,62CB,629B,62CF,62FF,62D5,62D6,6310,62FF,6331,6332,6335,5F04,633C,632A,633E,631F,6344,6551,6368,820D,636B,626A,6372,5377,637C,632A,6383,626B,6384,62A1,6399,6323,639B,6302,63A1,91C7,63BD,78B0,63C0,62E3,63D1,634F,63DA,626C,63DB,6362,63EB,63EA,63EE,6325,63F7,63D2,63F9,80CC,6406,6784,6407,63FF,6409,69B7,640D,635F,6416,6447,6417,6363,6424,627C,6425,6376,6428,62D3,642F,638F,6436,62A2,643E,69A8,6443,625B,6451,63B4,645C,63BC,645F,6402,646F,631A,6473,62A0,6476,629F,647B,63BA,6488,635E,648F,6326,6490,6491,6493,6320,649A,62C8,649D,39D1,649F,6322,64A3,63B8,64A5,62E8,64A6,626F,64AB,629A,64B2,6251,64B3,63FF,64BB,631E,64BE,631D,64BF,6361,64C1,62E5,64C4,63B3,64C7,62E9,64CA,51FB,64CB,6321,64D4,62C5,64D5,643A,64DA,636E,64E0,6324,64E3,6363,64E7,4E3E,64EC,62DF,64EF,6448,64F0,62E7,64F1,6401,64F2,63B7,64F4,6269,64F7,64B7,64FA,6446,64FB,64DE,64FC,64B8,64FE,6270,6504,6445,6506,64B5,650F,62E2,6514,62E6,6516,6484,6519,6400,651B,64BA,651C,643A,651D,6444,6522,6512,6523,631B,6524,644A,6529,6321,652A,6405,652C,63FD,6537,8003,6542,53E9,654D,53D9,6557,8D25,6558,53D9,656D,626C,6575,654C,6578,6570,657A,9A71,6582,655B,6583,6BD9,6586,6569,6595,6593,65AC,65A9,65AE,65AB,65B2,65AB,65B5,65AB,65B7,65AD,65C2,65D7,65E4,7978,65F9,65F6,65FE,6625,661A,614E,662C,660F,6630,662F,6642,65F6,6649,664B,665D,663C,6673,6670,667B,6697,6688,6655,6689,6656,668E,6620,6698,65F8,66A0,7693,66A2,7545,66AB,6682,66B1,6635,66C4,6654,66C6,5386,66C7,6619,66C9,6653,66D6,66A7,66E0,65F7,66E1,53E0,66E8,663D,66EC,6652,66F8,4E66,6703,4F1A,671E,671F,6722,671B,6727,80E7,6736,6735,6771,4E1C,678F,6960,6792,4E2B,67DF,6960,67F5,6805,67F9,67FF,67FA,62D0,67FB,67E5,6801,67F3,681E,520A,6822,67CF,6830,7B4F,6852,6851,686E,676F,687A,67F3,687F,6746,6894,6800,6898,67A7,689D,6761,689F,67AD,68C3,68A8,68C4,5F03,68CA,68CB,68D6,67A8,68D7,67A3,68DF,680B,68E7,6808,68F2,6816,68F6,68BE,6909,4E58,690F,6860,6917,7887,6936,68D5,6937,7F04,693E,7B3A,694A,6768,6953,67AB,6965,6966,6968,6862,696D,4E1A,6973,6885,6975,6781,6998,77E9,69A6,5E72,69AA,6769,69AE,8363,69BF,6864,69C0,69C1,69CB,6784,69CD,67AA,69D1,6885,69D3,6760,69D5,684C,69E4,68BF,69E7,6920,69E8,6901,69E9,6982,69F3,6868,69F6,6922,69FC,89C4,6A01,6869,6A02,4E50,6A05,679E,6A10,6A79,6A11,6881,6A13,697C,6A19,6807,6A1E,67A2,6A23,6837,6A38,6734,6A39,6811,6A3A,6866,6A48,6861,6A4B,6865,6A5C,6A5B,6A5F,673A,6A62,692D,6A64,854A,6A6B,6A2A,6A89,67FD,6A94,6863,6A9C,6867,6A9D,696B,6A9F,69DA,6AA2,68C0,6AA3,6A2F,6AAE,68BC,6AAF,53F0,6AB3,69DF,6AB8,67E0,6ABB,69DB,6AC2,68F9,6AC3,67DC,6AC8,51F3,6AD3,6A79,6ADA,6988,6ADB,6809,6ADD,691F,6ADE,6A7C,6ADF,680E,6AE5,6A71,6AE7,69E0,6AE8,680C,6AEA,67A5,6AEC,6987,6AF3,680A,6AF8,6989,6AFB,6A31,6B04,680F,6B0A,6743,6B0F,6924,6B12,683E,6B16,6984,6B1D,90C1,6B1E,68C2,6B2C,54B3,6B35,6B3E,6B3D,94A6,6B4E,53F9,6B50,6B27,6B5B,655B,6B5F,6B24,6B61,6B22,6B72,5C81,6B74,5386,6B77,5386,6B78,5F52,6B7F,6B81,6B80,592D,6B98,6B8B,6B9E,6B92,6BA4,6B87,6BAB,6B9A,6BAD,50F5,6BAE,6B93,6BAF,6BA1,6BB2,6B7C,6BBA,6740,6BBB,58F3,6BBC,58F3,6BC0,6BC1,6BC6,6BB4,6BD8,6BD7,6BE7,7ED2,6BEC,7403,6BFF,6BF5,6C02,7266,6C08,6BE1,6C0A,6BE1,6C0C,6C07,6C23,6C14,6C2B,6C22,6C2C,6C29,6C33,6C32,6C37,51B0,6C4E,6CDB,6C59,6C61,6C5A,6C61,6C7A,51B3,6C92,6CA1,6C96,51B2,6CC1,51B5,6CDD,6EAF,6D29,6CC4,6D36,6C79,6D44,51C0,6D79,6D43,6D87,6CFE,6D96,8385,6DBC,51C9,6DD2,51C4,6DDA,6CEA,6DDB,6D59,6DE5,6E0C,6DE8,51C0,6DEA,6CA6,6DF5,6E0A,6DF6,6D9E,6DFA,6D45,6E19,6DA3,6E1B,51CF,6E22,6CA8,6E26,6DA1,6E2C,6D4B,6E3E,6D51,6E4A,51D1,6E5E,6D48,6E67,6D8C,6E6F,6C64,6E7B,6DF3,6E7C,6D85,6E88,6CA9,6E96,51C6,6E9D,6C9F,6EAB,6E29,6EAE,6D49,6EB3,6DA2,6EBC,6E7F,6EC4,6CA7,6EC5,706D,6ECC,6DA4,6ECE,8365,6ED9,6C47,6EDB,6DEB,6EEC,6CAA,6EEF,6EDE,6EF2,6E17,6EF7,5364,6EF8,6D52,6EFB,6D50,6EFE,6EDA,6EFF,6EE1,6F01,6E14,6F0A,6E87,6F1A,6CA4,6F22,6C49,6F23,6D9F,6F2C,6E0D,6F32,6DA8,6F38,6E10,6F3F,6D46,6F41,988D,6F44,6F31,6F51,6CFC,6F54,6D01,6F59,6CA9,6F5B,6F5C,6F64,6DA6,6F6F,6D54,6F70,6E83,6F77,6ED7,6F7F,6DA0,6F80,6DA9,6F81,6DA9,6F82,6F84,6F86,6D47,6F87,6D9D,6F90,6C84,6F97,6DA7,6FA0,6E11,6FA3,6D63,6FA4,6CFD,6FA6,6EEA,6FAE,6D4D,6FB1,6DC0,6FC1,6D4A,6FC3,6D53,6FC7,6DA9,6FD5,6E7F,6FD8,6CDE,6FDA,6E81,6FDC,6D55,6FDF,6D4E,6FE4,6D9B,6FEB,6EE5,6FEC,6D5A,6FF0,6F4D,6FF1,6EE8,6FF6,9614,6FFA,6E85,6FFC,6CFA,6FFE,6EE4,7002,6F9B,7005,6EE2,7006,6E0E,7009,6CFB,700B,6C88,700F,6D4F,7015,6FD2,7018,6CF8,701D,6CA5,701F,6F47,7020,6F46,7027,6CF7,7028,6FD1,7030,5F25,7032,6F4B,703E,6F9C,7043,6CA3,7044,6EE0,704B,6CD5,7051,6D12,7055,6F13,7058,6EE9,705D,704F,7063,6E7E,7064,6EE6,7067,6EDF,7068,8D63,707D,707E,70A4,7167,70BA,4E3A,70CF,4E4C,70D6,707E,70F1,70AF,70F4,70C3,7121,65E0,7147,8F89,7149,70BC,7151,716E,7152,709C,7156,6696,7157,6696,7159,70DF,7162,8315,7165,7115,7169,70E6,716C,7080,7188,7199,7192,8367,7197,709D,71B1,70ED,71B2,988E,71BE,70BD,71C1,70E8,71C4,7130,71C8,706F,71D0,78F7,71D2,70E7,71D9,70EB,71DC,7116,71DF,8425,71E6,707F,71EC,6BC1,71ED,70DB,71F4,70E9,71FB,718F,71FC,70EC,71FE,7118,71FF,8000,720D,70C1,7210,7089,7215,71EE,7217,70E8,721B,70C2,722D,4E89,7232,4E3A,723A,7237,723E,5C14,7240,5E8A,7246,5899,724B,7B3A,724E,7A97,7250,95F8,7253,699C,7255,7A97,7258,724D,7260,5B83,7274,62B5,727D,7275,7282,7281,7296,8366,729B,7266,72A2,728A,72A7,727A,72C0,72B6,72E5,5F87,72F9,72ED,72FD,72C8,7302,608D,7319,72F0,7328,733F,7336,72B9,733B,72F2,7341,72B8,7343,5446,7344,72F1,7345,72EE,734B,55E5,734E,5956,7358,6BD9,7367,72F7,7368,72EC,736A,72EF,736B,7303,736E,72DD,7370,72DE,7372,83B7,7375,730E,7377,72B7,7378,517D,737A,736D,737B,732E,737C,7315,7380,7321,7385,5999,73CE,73CD,73FE,73B0,740D,7483,7416,76CF,7431,96D5,7439,7434,743A,73D0,743F,73F2,7447,73B3,744B,73AE,7452,739A,7460,7409,7463,7410,7464,7476,7469,83B9,746A,739B,746F,7405,7472,73B1,7489,740F,74A1,740E,74A2,7409,74A3,7391,74A6,7477,74AB,73F0,74B0,73AF,74B5,7399,74BD,73BA,74BF,7487,74C8,7483,74CA,743C,74CF,73D1,74D4,748E,74DA,74D2,750C,74EF,750E,7816,7515,74EE,7516,7F42,751E,5C1D,7522,4EA7,7523,4EA7,7542,4EA9,7546,4EA9,754A,8015,755D,4EA9,7562,6BD5,7567,7565,756B,753B,756E,4EA9,7570,5F02,7571,7559,7576,5F53,7587,7574,7589,53E0,758A,53E0,758B,5339,758E,758F,7598,809B,75BF,75F1,75D0,86D4,75D9,75C9,75F3,6DCB,75FA,75F9,75FE,75B4,7609,6108,760B,75AF,760D,75A1,7613,75EA,7616,5591,761E,7617,7621,75AE,7627,759F,762E,7606,7632,75AD,763B,7618,7642,7597,7644,6194,7645,7624,7646,75E8,7647,75EB,7648,5E9F,7649,7605,7652,6108,7658,75A0,765F,762A,7661,75F4,7662,75D2,7664,7596,7665,75C7,7667,75AC,7669,765E,766C,7663,766D,763F,766E,763E,7670,75C8,7671,762B,7672,766B,767C,53D1,7681,7682,7690,768B,769A,7691,769C,7693,76B0,75B1,76B7,9F13,76B8,76B2,76BA,76B1,76C3,676F,76C7,76CD,76CB,94B5,76CC,7897,76DE,76CF,76E1,5C3D,76E3,76D1,76E4,76D8,76E7,5362,76EA")
				.append(
						",8361,770E,89C6,7721,89C6,7725,7726,773E,4F17,774F,56F0,775C,7741,775E,7750,7760,7737,7787,772F,7796,7FF3,7798,770D,779E,7792,77BC,7751,77C1,7785,77C7,8499,77D3,772C,77D9,77B0,77DA,77A9,77EF,77EB,77F4,7887,7832,70AE,785C,7841,7864,7856,7868,7817,786F,781A,7881,68CB,78A9,7855,78AA,7827,78AD,7800,78B8,781C,78BA,786E,78BC,7801,78D1,7859,78DA,7816,78DF,788C,78E3,789C,78E7,789B,78EF,77F6,78FD,7857,78FE,40C5,7904,785A,790E,7840,7919,788D,7926,77FF,792A,783A,792B,783E,792C,77FE,792E,70AE,7931,783B,7951,5E19,7957,53EA,797F,7984,798D,7978,798E,796F,7995,794E,79A1,7943,79A6,5FA1,79A9,7940,79AA,7985,79AE,793C,79B0,7962,79B1,7977,79BF,79C3,79C8,7C7C,79CA,5E74,79CC,79CB,79D4,7CB3,79D6,53EA,7A05,7A0E,7A08,79C6,7A09,7CB3,7A1C,68F1,7A1F,7980,7A2C,7CEF,7A2D,79F8,7A2E,79CD,7A31,79F0,7A3A,7A1A,7A3E,7A3F,7A40,8C37,7A45,7CE0,7A49,7A1A,7A4C,7A23,7A4D,79EF,7A4E,9896,7A60,79FE,7A61,7A51,7A62,79FD,7A64,7CEF,7A68,9893,7A69,7A33,7A6B,83B7,7A7D,9631,7A93,7A97,7AA9,7A9D,7AAA,6D3C,7AAE,7A77,7AAF,7A91,7AB0,7A91,7AB5,7A8E,7AB6,7AAD,7ABA,7AA5,7ABB,7A97,7AC4,7A9C,7AC5,7A8D,7AC7,7AA6,7AC8,7076,7ACA,7A83,7ADA,4F2B,7ADD,5E76,7AE2,4FDF,7AEA,7AD6,7AF6,7ADE,7B46,7B14,7B4D,7B0B,7B5E,7B56,7B66,7BA1,7B67,7B15,7B69,7B52,7B6F,7BB8,7B74,7B56,7B87,4E2A,7B8B,7B3A,7B8F,7B5D,7B92,5E1A,7BA0,68F0,7BC0,8282,7BC4,8303,7BC9,7B51,7BCB,7BA7,7BD4,7B7C,7BDB,7BAC,7BE4,7B03,7BE9,7B5B,7BF3,7B5A,7BF9,7E82,7C00,7BA6,7C0D,7BD3,7C11,84D1,7C12,7BE1,7C1E,7BAA,7C21,7B80,7C23,7BD1,7C2B,7BAB,7C2E,7C2A,7C37,6A90,7C39,7B5C,7C3D,7B7E,7C3E,5E18,7C43,7BEE,7C4C,7B79,7C50,85E4,7C51,9994,7C59,7B93,7C5B,7BEF,7C5C,7BA8,7C5F,7C41,7C60,7B3C,7C62,5941,7C64,7B7E,7C69,7B3E,7C6A,7C16,7C6C,7BF1,7C6E,7BA9,7C72,5401,7C83,79D5,7C87,7CE0,7CA6,78F7,7CA7,5986,7CB0,9EB8,7CB5,7CA4,7CBA,7A17,7CC9,7CBD,7CDD,7CC1,7CDE,7CAA,7CE7,7CAE,7CF0,56E2,7CF2,7C9D,7CF4,7C74,7CF6,7C9C,7CFA,7EA0,7CFE,7EA0,7D00,7EAA,7D02,7EA3,7D04,7EA6,7D05,7EA2,7D06,7EA1,7D07,7EA5,7D08,7EA8,7D09,7EAB,7D0B,7EB9,7D0D,7EB3,7D10,7EBD,7D13,7EBE,7D14,7EAF,7D15,7EB0,7D16,7EBC,7D17,7EB1,7D18,7EAE,7D19,7EB8,7D1A,7EA7,7D1B,7EB7,7D1C,7EAD,7D1D,7EB4,7D21,7EBA,7D25,624E,7D2E,624E,7D30,7EC6,7D31,7EC2,7D32,7EC1,7D33,7EC5,7D35,7EBB,7D39,7ECD,7D3A,7EC0,7D3C,7ECB,7D3F,7ED0,7D40,7ECC,7D42,7EC8,7D43,5F26,7D44,7EC4,7D45,4339,7D46,7ECA,7D4E,7ED7,7D4F,7EC1,7D50,7ED3,7D55,7EDD,7D5B,7EE6,7D5D,7ED4,7D5E,7EDE,7D61,7EDC,7D62,7EDA,7D66,7ED9,7D68,7ED2,7D70,7ED6,7D71,7EDF,7D72,4E1D,7D73,7EDB,7D76,7EDD,7D79,7EE2,7D81,7ED1,7D83,7EE1,7D86,7EE0,7D88,7EE8,7D89,7EE3,7D8C,7EE4,7D8F,7EE5,7D91,6346,7D93,7ECF,7D9C,7EFC,7DA0,7EFF,7DA2,7EF8,7DA3,7EFB,7DAB,7EBF,7DAC,7EF6,7DAD,7EF4,7DAF,7EF9,7DB0,7EFE,7DB1,7EB2,7DB2,7F51,7DB3,7EF7,7DB4,7F00,7DB5,5F69,7DB8,7EB6,7DB9,7EFA,7DBA,7EEE,7DBB,7EFD,7DBD,7EF0,7DBE,7EEB,7DBF,7EF5,7DC4,7EF2,7DC7,7F01,7DCA,7D27,7DCB,7EEF,7DD0,7E41,7DD1,7EFF,7DD2,7EEA,7DD4,7EF1,7DD7,7F03,7DD8,7F04,7DD9,7F02,7DDA,7F10,7DDC,7EF5,7DDD,7F09,7DDE,7F0E,7DE0,7F14,7DE1,7F17,7DE3,7F18,7DE5,8913,7DE6,7F0C,7DE8,7F16,7DE9,7F13,7DEC,7F05,7DEF,7EAC,7DF1,7F11,7DF2,7F08,7DF4,7EC3,7DF9,7F07,7DFB,81F4,7DFC,7F0A,7E08,8426,7E09,7F19,7E0A,7F22,7E0B,7F12,7E10,7EC9,7E11,7F23,7E15,7F0A,7E1A,7EE6,7E1B,7F1A,7E1D,7F1C,7E1E,7F1F,7E1F,7F1B,7E23,53BF,7E27,7EE6,7E2B,7F1D,7E2D,7F21,7E2E,7F29,7E31,7EB5,7E32,7F27,7E34,7EA4,7E35,7F26,7E36,7D77,7E37,7F15,7E39,7F25,7E3D,603B,7E3E,7EE9,7E43,7EF7,7E45,7F2B,7E46,7F2A,7E52,7F2F,7E54,7EC7,7E55,7F2E,7E56,4F1E,7E59,7FFB,7E5A,7F2D,7E5E,7ED5,7E61,7EE3,7E66,8941,7E69,7EF3,7E6A,7ED8,7E6B,7CFB,7E6D,8327,7E6E,7F30,7E6F,7F33,7E70,7F32,7E73,7F34,7E79,7ECE,7E7C,7EE7,7E7D,7F24,7E7E,7F31,7E88,7F2C,7E8A,7EA9,7E8C,7EED,7E8D,7D2F,7E8F,7F20,7E93,7F28,7E94,624D,7E96,7EA4,7E98,7F35,7E9C,7F06,7F3D,94B5,7F3E,74F6,7F43,44E8,7F47,6A3D,7F48,575B,7F4B,74EE,7F4C,7F42,7F4E,575B,7F63,6302,7F70,7F5A,7F75,9A82,7F77,7F62,7F78,7F5A,7F85,7F57,7F86,7F74,7F88,7F81,7F97,7F8C,7FA2,7ED2,7FA3,7FA4,7FA5,7F9F,7FA9,4E49,7FB4,81BB,7FB6,81BB,7FC4,7FC5,7FD2,4E60,7FEB,73A9,7FEC,7FDA,7FF9,7FD8,7FFA,7FF1,7FFD,7FD9,8011,4E13,8021,9504,802C,8027,802E,8022,8056,5723,805E,95FB,806F,8054,8070,806A,8072,58F0,8073,8038,8075,8069,8076,8042,8077,804C,8079,804D,807D,542C,807E,804B,8085,8083,808E,80AF,8090,80F3,80A7,80DA,80F7,80F8,8103,8106,8105,80C1,8107,80C1,8108,8109,8117,543B,811B,80EB,8123,5507,812B,8131,8139,80C0,814E,80BE,8156,80E8,8161,8136,8166,8111,816B,80BF,8173,811A,8178,80A0,8183,817D,8193,80A0,8195,8158,819A,80A4,81A0,80F6,81A9,817B,81BD,80C6,81BE,810D,81BF,8113,81C8,814A,81C9,8138,81CB,81C0,81CD,8110,81CF,8191,81D5,8198,81D8,814A,81D9,80ED,81DA,80EA,81DD,88F8,81DF,810F,81E0,8114,81E2,81DC,81E5,5367,81E8,4E34,81EF,768B,81FA,53F0,8207,4E0E,8208,5174,8209,4E3E,820A,65E7,8216,94FA,8218,9986,8229,8239,8259,8231,8262,6A2F,8263,6A79,8264,8223,8266,8230,826A,6A79,826B,823B,8271,8270,8277,8273,8278,8349,82B2,82B1,82BB,520D,82E7,82CE,8318,8354,834D,835E,8373,8C46,838A,5E84,8396,830E,83A2,835A,83A7,82CB,83D1,707E,83D3,679C,83EF,534E,83F4,5EB5,83F8,70DF,8407,82CC,840A,83B1,842C,4E07,8432,8431,8435,83B4,8449,53F6,8452,836D,8460,53C2,8466,82C7,8477,8364,8493,83BC,8494,83B3,849E,8385,84BC,82CD,84C0,836A,84C6,5E2D,84CB,76D6,84E1,53C2,84EE,83B2,84EF,82C1,84F4,83BC,84FD,835C,8506,83F1,8514,535C,8515,8482,851E,848C,8523,848B,8525,8471,8526,8311,852D,836B,8534,9EBB,853E,85DC,8541,8368,8546,8487,854B,854A,854E,835E,8552,836C,8553,82B8,8555,83B8,8558,835B,855A,843C,8562,8489,8569,8361,856A,829C,856D,8427,8577,84E3,857F,8431,8588,835F,858A,84DF,858C,8297,8591,59DC,8594,8537,8598,8359,8599,5243,859F,83B6,85A6,8350,85A9,8428,85B4,82E7,85BA,8360,85CD,84DD,85CE,8369,85DD,827A,85E5,836F,85EA,85AE,85F4,8574,85F6,82C8,85F7,85AF,85F9,853C,85FA,853A,85FC,8431,8600,841A,8602,854A,8604,8572,8606,82A6,8607,82CF,860A,8574,8610,8431,8613,82CF,861A,85D3,861E,8539,8622,830F,8624,82B1,862D,5170,863A,84E0,863F,841D,8655,5904,8656,547C,865B,865A,865C,864F,865F,53F7,8667,4E8F,866F,866C,8675,86C7,8698,86D4,86D5,86D4,86FA,86F1,86FB,8715,8706,86AC,870B,8782,8716,86D4,8728,8776,873A,9713,8755,8680,875F,732C,8761,8815,8766,867E,8768,8671,876F,733F,8771,867B,8778,8717,8784,86F3,878E,878D,879E,8682,87A1,868A,87A2,8424,87AE,45D6,87BB,877C,87C1,868A,87C4,86F0,87C7,87C6,87C8,8748,87CE,87A8,87E3,866E,87EC,8749,87EF,86F2,87F2,866B,87F6,86CF,87FB,8681,8805,8747,8806,867F,880D,874E,880F,87F9,8810,86F4,8811,877E,8812,8327,8814,869D,881F,8721,8823,86CE,8828,87CF,882D,8702,8831,86CA,8836,8695,883B,86EE,8842,8844,8846,4F17,8847,8109,884A,8511,8853,672F,8855,540C,8856,5F04,8858,8854,885A,80E1,885B,536B,885D,51B2,885E,536B,8879,53EA,887A,90AA,889E,886E,88A0,5E19,88B4,88E4,88B5,887D,88CA,8885,88CC,5939,88CF,91CC,88DC,8865,88DD,88C5,88E0,88D9,88E1,91CC,88FD,5236,8907,590D,890C,88C8,8918,8886,892D,8885,8932,88E4,8933,88E2,8938,891B,893B,4EB5,8943,8912,8947,88E5,894D,6742,894F,88AF,8956,8884,895D,88E3,8960,88C6,8962,8892,8964,8934,896A,889C,896C,6446,896F,886C,8972,88AD,8987,9738,8988,6838,898A,7F81,898B,89C1,898E,89C3,898F,89C4,8993,89C5,8994,89C5,8996,89C6,8998,89C7,899C,773A,89A1,89CB,89A6,89CE")
				.append(
						",89A9,7779,89AA,4EB2,89AC,89CA,89AF,89CF,89B2,89D0,89B7,89D1,89BA,89C9,89BD,89C8,89BF,89CC,89C0,89C2,89D4,65A4,89D5,7C97,89DD,62B5,89F4,89DE,89F6,89EF,89F8,89E6,8A02,8BA2,8A03,8BA3,8A08,8BA1,8A0A,8BAF,8A0C,8BA7,8A0E,8BA8,8A10,8BA6,8A12,8BB1,8A13,8BAD,8A15,8BAA,8A16,8BAB,8A17,6258,8A18,8BB0,8A1B,8BB9,8A1D,8BB6,8A1F,8BBC,8A23,8BC0,8A25,8BB7,8A29,8BBB,8A2A,8BBF,8A2D,8BBE,8A31,8BB8,8A34,8BC9,8A36,8BC3,8A3A,8BCA,8A3B,6CE8,8A41,8BC2,8A46,8BCB,8A4E,8BB5,8A50,8BC8,8A52,8BD2,8A54,8BCF,8A55,8BC4,8A56,8BD0,8A57,8BC7,8A58,8BCE,8A5B,8BC5,8A5E,8BCD,8A60,548F,8A61,8BE9,8A62,8BE2,8A63,8BE3,8A66,8BD5,8A67,5BDF,8A69,8BD7,8A6B,8BE7,8A6C,8BDF,8A6D,8BE1,8A6E,8BE0,8A70,8BD8,8A71,8BDD,8A72,8BE5,8A73,8BE6,8A75,8BDC,8A76,916C,8A7C,8BD9,8A7F,8BD6,8A84,8BD4,8A85,8BDB,8A86,8BD3,8A87,5938,8A8C,5FD7,8A8D,8BA4,8A91,8BF3,8A95,8BDE,8A96,6096,8A98,8BF1,8A9A,8BEE,8A9E,8BED,8AA0,8BDA,8AA1,8BEB,8AA3,8BEC,8AA4,8BEF,8AA5,8BF0,8AA6,8BF5,8AA8,8BF2,8AAA,8BF4,8AAC,8BF4,8AB0,8C01,8AB2,8BFE,8AB6,8C07,8AB9,8BFD,8ABC,8C0A,8ABE,8A1A,8ABF,8C03,8AC2,8C04,8AC4,8C06,8AC7,8C08,8AC9,8BFF,8ACB,8BF7,8ACD,8BE4,8ACF,8BF9,8AD0,6106,8AD1,8BFC,8AD2,8C05,8AD6,8BBA,8AD7,8C02,8AD9,8BDD,8ADB,8C00,8ADC,8C0D,8ADD,8C1E,8ADE,8C1D,8AE0,55A7,8AE1,8C25,8AE2,8BE8,8AE4,8C14,8AE6,8C1B,8AE7,8C10,8AEB,8C0F,8AED,8C15,8AF1,8BB3,8AF3,8C19,8AF6,8C0C,8AF7,8BBD,8AF8,8BF8,8AFA,8C1A,8AFC,8C16,8AFE,8BFA,8B00,8C0B,8B01,8C12,8B02,8C13,8B04,8A8A,8B05,8BCC,8B0A,8C0E,8B0C,6B4C,8B0E,8C1C,8B10,8C27,8B14,8C11,8B16,8C21,8B17,8C24,8B19,8C26,8B1A,8C25,8B1B,8BB2,8B1D,8C22,8B20,8C23,8B28,8C1F,8B29,8C1F,8B2B,8C2A,8B2C,8C2C,8B2D,8C2B,8B33,8BB4,8B39,8C28,8B3C,547C,8B3E,8C29,8B41,54D7,8B46,563B,8B49,8BC1,8B4C,8BB9,8B4E,8C32,8B4F,8BA5,8B54,64B0,8B56,8C2E,8B58,8BC6,8B59,8C2F,8B5A,8C2D,8B5C,8C31,8B5F,566A,8B6B,8C35,8B6D,6BC1,8B6F,8BD1,8B70,8BAE,8B74,8C34,8B77,62A4,8B7D,8A89,8B7E,8C2B,8B80,8BFB,8B81,8C2A,8B8A,53D8,8B8B,8A5F,8B8E,96E0,8B90,4EC7,8B92,8C17,8B93,8BA9,8B95,8C30,8B96,8C36,8B99,6B22,8B9A,8D5E,8B9C,8C20,8B9E,8C33,8C48,5C82,8C4E,7AD6,8C50,4E30,8C53,8273,8C54,8273,8C6C,732A,8C76,8C6E,8C8D,72F8,8C93,732B,8C99,4759,8C9B,737E,8C9D,8D1D,8C9E,8D1E,8CA0,8D1F,8CA1,8D22,8CA2,8D21,8CA7,8D2B,8CA8,8D27,8CA9,8D29,8CAA,8D2A,8CAB,8D2F,8CAC,8D23,8CAF,8D2E,8CB0,8D33,8CB2,8D40,8CB3,8D30,8CB4,8D35,8CB6,8D2C,8CB7,4E70,8CB8,8D37,8CBA,8D36,8CBB,8D39,8CBC,8D34,8CBD,8D3B,8CBF,8D38,8CC0,8D3A,8CC1,8D32,8CC2,8D42,8CC3,8D41,8CC4,8D3F,8CC5,8D45,8CC7,8D44,8CC8,8D3E,8CC9,6064,8CCA,8D3C,8CD1,8D48,8CD2,8D4A,8CD3,5BBE,8CD5,8D47,8CD9,8D52,8CDA,8D49,8CDB,8D5E,8CDC,8D50,8CDE,8D4F,8CE0,8D54,8CE1,8D53,8CE2,8D24,8CE3,5356,8CE4,8D31,8CE6,8D4B,8CEA,8D28,8CEB,8D4D,8CEC,8D26,8CED,8D4C,8CF4,8D56,8CF5,8D57,8CF7,8D4D,8CFA,8D5A,8CFB,8D59,8CFC,8D2D,8CFD,8D5B,8CFE,8D5C,8D04,8D3D,8D05,8D58,8D07,8D5F,8D08,8D60,8D0A,8D5E,8D0B,8D5D,8D0D,8D61,8D0F,8D62,8D10,8D46,8D11,8D63,8D13,8D43,8D14,8D51,8D16,8D4E,8D17,8D5D,8D1B,8D63,8D6C,8D6A,8D82,8D81,8D95,8D76,8D99,8D75,8DA8,8D8B,8DB2,8DB1,8DE1,8FF9,8DE5,8DFA,8DF4,8E29,8DFC,5C40,8E01,80EB,8E10,8DF5,8E2B,78B0,8E30,903E,8E34,8E0A,8E4C,8DC4,8E4F,8E44,8E54,6682,8E55,8DF8,8E5F,8FF9,8E60,8DD6,8E63,8E52,8E64,8E2A,8E67,7CDF,8E75,8E74,8E7A,8DF7,8E7B,8DF7,8E82,8DF6,8E89,8DB8,8E8A,8E0C,8E8B,8DFB,8E8D,8DC3,8E91,8E2F,8E92,8DDE,8E93,8E2C,8E95,8E70,8E9A,8DF9,8EA1,8E51,8EA5,8E7F,8EA6,8E9C,8EAA,8E8F,8EAD,803D,8EB3,8EAC,8EB6,88F8,8EC0,8EAF,8ECA,8F66,8ECB,8F67,8ECC,8F68,8ECD,519B,8ED1,8F6A,8ED2,8F69,8ED4,8F6B,8EDB,8F6D,8EDF,8F6F,8EE4,8F77,8EEB,8F78,8EF2,8F71,8EF8,8F74,8EF9,8F75,8EFA,8F7A,8EFB,8F72,8EFC,8F76,8EFE,8F7C,8F03,8F83,8F05,8F82,8F07,8F81,8F08,8F80,8F09,8F7D,8F0A,8F7E,8F12,8F84,8F13,633D,8F14,8F85,8F15,8F7B,8F19,8F84,8F1B,8F86,8F1C,8F8E,8F1D,8F89,8F1E,8F8B,8F1F,8F8D,8F25,8F8A,8F26,8F87,8F29,8F88,8F2A,8F6E,8F2C,8F8C,8F2D,8F6F,8F2F,8F91,8F33,8F8F,8F38,8F93,8F3B,8F90,8F3C,8F92,8F3E,8F97,8F3F,8206,8F40,8F92,8F42,6BC2,8F44,8F96,8F45,8F95,8F46,8F98,8F49,8F6C,8F4D,8F99,8F4E,8F7F,8F54,8F9A,8F5F,8F70,8F61,8F94,8F62,8F79,8F64,8F73,8FA0,7F6A,8FA2,8FA3,8FA4,8F9E,8FA6,529E,8FAD,8F9E,8FAE,8FAB,8FAF,8FA9,8FB2,519C,8FB3,519C,8FF4,56DE,8FFB,79FB,9008,8FE5,9015,8FF3,9019,8FD9,9023,8FDE,9025,56DE,9029,5954,9031,5468,9032,8FDB,9049,4FA6,904A,6E38,904B,8FD0,904E,8FC7,9054,8FBE,9055,8FDD,9059,9065,905C,900A,905E,9012,9060,8FDC,9061,6EAF,9069,9002,906F,9041,9072,8FDF,9076,7ED5,9077,8FC1,9078,9009,907A,9057,907C,8FBD,9081,8FC8,9084,8FD8,9087,8FE9,908A,8FB9,908F,903B,9090,9026,90DF,90CF,90F5,90AE,9106,90D3,9109,4E61,9112,90B9,9114,90AC,9116,90E7,9127,9093,912D,90D1,9130,90BB,9132,90F8,9134,90BA,9136,90D0,913A,909D,9147,9142,9148,90E6,9156,9E29,9167,916C,9183,814C,9186,76CF,9195,9187,9196,915D,919C,4E11,919E,915D,91AB,533B,91AC,9171,91B1,9166,91BB,916C,91BC,5BB4,91C0,917F,91C1,8845,91C3,917E,91C5,917D,91CB,91CA,91D3,9486,91D4,9487,91D5,948C,91D7,948A,91D8,9489,91D9,948B,91DD,9488,91E3,9493,91E4,9490,91E6,6263,91E7,948F,91E9,9492,91EC,710A,91F5,9497,91F7,948D,91F9,9495,91FA,948E,9200,94AF,9201,94AB,9203,9498,9204,94AD,9206,94C5,9208,949A,9209,94A0,920D,949D,920E,94A9,9210,94A4,9211,94A3,9214,949E,9215,94AE,921E,94A7,9223,9499,9225,94AC,9226,949B,9227,94AA,922E,94CC,9230,94C8,9234,94C3,9237,94B4,9238,94B9,9239,94CD,923A,94B0,923E,94C0,923F,94BF,9240,94BE,9245,949C,9248,94CA,9249,94C9,924B,5228,924D,94CB,924F,9504,9251,94C2,9255,94B7,9257,94B3,925A,94C6,925B,94C5,925E,94BA,9262,94B5,9264,94A9,9266,94B2,926C,94BC,926D,94BD,9276,94CF,9278,94F0,927A,94D2,927B,94EC,927F,94EA,9280,94F6,9283,94F3,9285,94DC,928D,94DA,9291,94E3,9293,94E8,9296,94E2,9298,94ED,929A,94EB,929B,94E6,929C,8854,92A0,94D1,92A3,94F7,92A5,94F1,92A6,94DF,92A8,94F5,92A9,94E5,92AA,94D5,92AB,94EF,92AC,94D0,92B1,94DE,92B2,710A,92B3,9510,92B7,9500,92B9,9508,92BB,9511,92BC,9509,92C1,94DD,92C3,9512,92C5,950C,92C7,94A1,92CC,94E4,92CF,94D7,92D2,950B,92D9,94FB,92DD,950A,92DF,9513,92E3,94D8,92E4,9504,92E5,9503,92E6,9514,92E8,9507,92EA,94FA,92ED,9510,92EE,94D6,92EF,9506,92F0,9502,92F1,94FD,92F6,950D,92F8,952F,92FC,94A2,9301,951E,9304,5F55,9306,9516,9307,952B,9308,9529,9310,9525,9312,9515,9315,951F,9318,9524,9319,9531,931A,94EE,931B,951B,931F,952C,9320,952D,9321,951C,9322,94B1,9326,9526,9328,951A,932B,9521,932E,9522,932F,9519,9332,5F55,9333,9530,9336,8868,9338,94FC,9340,951D,9341,9528,9343,952A,9346,9494,9347,9534,9348,9533,934A,70BC,934B,9505,934D,9540,9354,9537,9358,94E1,935A,9496,935B,953B,9360,953D,9364,9538,9365,9532,9369,9518,936B,9539,936C,9539,9370,953E,9373,9274,9375,952E,9376,9536,937A,9517,937C,9488,937E,953A,9382,9541,9384,953F,9387,9545,938A,9551,938C,9570,9394,9555,9396,9501,9397,67AA,9398,9549,939A,9524,939B,9548,93A1,9543,93A2,94A8,93A3,84E5,93A6,954F,93A7,94E0,93A9,94E9,93AA,953C,93AC,9550,93AE,9547,93B0,9552,93B3,954D,93B5,9553,93B8,954C,93BB,9501,93BF,954E,93C3,955E,93C7,65CB,93C8,94FE,93CC,9546,93D0,9560,93D1,955D,93D7,94FF,93D8,9535,93DC,9557,93DD,9558,93DE,955B,93DF,94F2,93E1,955C,93E2,9556,93E4,9542,93E8,933E,93F0,955A,93F5,94E7,93F7,9564,93F9,956A,93FD,9508,9403,94D9,940B,94F4,9410,9563,9412,94F9,9413,9566,9414,9561,9418")
				.append(
						",949F,9419,956B,941D,9562,9420,9568,9426,950E,9427,950F,9428,9544,942E,9570,9432,956F,9433,956D,9435,94C1,9436,956E,9438,94CE,943A,94DB,943F,9571,9444,94F8,944A,956C,944C,9554,9451,9274,9452,9274,9454,9572,9455,9527,945A,94BB,945B,77FF,945E,9574,9460,94C4,9463,9573,9464,5228,9465,9565,946D,9567,9470,94A5,9471,9575,9472,9576,9475,7F50,9477,954A,9479,9569,947C,9523,947D,94BB,947E,92AE,947F,51FF,9482,954B,9577,957F,9580,95E8,9582,95E9,9583,95EA,9586,95EB,9588,95EC,9589,95ED,958B,5F00,958C,95F6,958E,95F3,958F,95F0,9591,95F2,9592,95F2,9593,95F4,9594,95F5,9598,95F8,9599,95F9,95A1,9602,95A3,9601,95A4,5408,95A5,9600,95A7,54C4,95A8,95FA,95A9,95FD,95AB,9603,95AC,9606,95AD,95FE,95B1,9605,95B2,9605,95B6,960A,95B9,9609,95BB,960E,95BC,960F,95BD,960D,95BE,9608,95C3,9612,95C6,677F,95C7,6697,95C8,95F1,95CA,9614,95CB,9615,95CC,9611,95CD,9607,95D0,9617,95D2,9618,95D3,95FF,95D4,9616,95D5,9619,95D6,95EF,95DA,7AA5,95DC,5173,95DE,961A,95E1,9610,95E2,8F9F,95E5,95FC,9628,5384,962C,5751,962F,5740,9657,5CED,9658,9649,965D,9655,9663,9635,9670,9634,9673,9648,9678,9646,967B,5819,967D,9633,967F,72ED,9682,9634,9684,5824,968A,961F,968E,9636,9695,9668,9696,575E,969B,9645,96A3,90BB,96A8,968F,96AA,9669,96B1,9690,96B4,9647,96B7,96B6,96B8,96B6,96BB,53EA,96CB,96BD,96D6,867D,96D9,53CC,96DB,96CF,96DC,6742,96DD,96CD,96DE,9E21,96E2,79BB,96E3,96BE,96F0,6C1B,96F2,4E91,96FB,7535,9711,6CBE,9727,96FE,973D,9701,9742,96F3,9744,972D,9746,53C7,9748,7075,9749,53C6,975A,9753,975C,9759,9768,9765,976D,97E7,9771,97E7,978C,978D,978F,5DE9,97A6,79CB,97B5,978B,97BD,9792,97BE,9774,97C1,7F30,97C3,9791,97C6,5343,97C8,889C,97C9,97AF,97CB,97E6,97CC,97E7,97CD,97E8,97D3,97E9,97D9,97EA,97DC,97EC,97DE,97EB,97E4,889C,97EE,97ED,97FB,97F5,97FF,54CD,9801,9875,9802,9876,9803,9877,9805,9879,9806,987A,9807,9878,9808,987B,980A,987C,980C,9882,980E,9880,980F,9883,9810,9884,9811,987D,9812,9881,9813,987F,9817,9887,9818,9886,981C,988C,981F,989D,9821,9889,9824,9890,9826,988F,982D,5934,9830,988A,9832,988B,9834,9896,9837,9894,9838,9888,9839,9893,983B,9891,983C,8D56,983D,9893,9846,9897,9847,60B4,984B,816E,984C,9898,984D,989D,984E,989A,984F,989C,9852,9899,9853,989B,9854,989C,9858,613F,9859,98A1,985B,98A0,985E,7C7B,9862,989F,9865,98A2,9866,6194,9867,987E,986B,98A4,986C,98A5,986F,663E,9870,98A6,9871,9885,9873,989E,9874,98A7,98A8,98CE,98AD,98D0,98AE,98D1,98AF,98D2,98B1,53F0,98B3,522E,98B6,98D3,98B8,98D4,98BA,98CF,98BC,98D5,98BF,5E06,98C0,98D7,98C3,98D8,98C4,98D8,98C6,98D9,98DB,98DE,98DC,7FFB,98E2,9965,98E4,9972,98E9,9968,98EA,996A,98EB,996B,98ED,996C,98EF,996D,98F1,98E7,98F2,996E,98F4,9974,98FC,9972,98FD,9971,98FE,9970,98FF,9973,9901,996A,9903,997A,9904,9978,9905,997C,9908,7CCD,9909,9977,990A,517B,990C,9975,990E,9979,990F,997B,9911,997D,9912,9981,9913,997F,991A,80B4,991B,9984,991C,9983,991E,996F,9921,9985,9927,5582,9928,9986,992C,7CCA,9931,7CC7,9935,5582,9936,9989,9937,9987,9939,7CD6,993B,7CD5,993C,9969,993D,9988,993E,998F,993F,998A,9941,998C,9943,998D,9945,9992,9948,9990,9949,9991,994A,9993,994B,9988,994C,9994,994D,81B3,9951,9965,9952,9976,9957,98E8,995C,990D,995D,998D,995E,998B,995F,9977,9962,9995,99AC,9A6C,99AD,9A6D,99AE,51AF,99B1,9A6E,99B3,9A70,99B4,9A6F,99B9,9A72,99C1,9A73,99C8,9A71,99D0,9A7B,99D1,9A7D,99D2,9A79,99D4,9A75,99D5,9A7E,99D8,9A80,99D9,9A78,99DB,9A76,99DD,9A7C,99DE,9A7C,99DF,9A77,99E1,9A82,99E2,9A88,99ED,9A87,99EE,9A73,99F0,9A83,99F1,9A86,99F8,9A8E,99FF,9A8F,9A01,9A8B,9A02,9A8D,9A03,5446,9A05,9A93,9A0C,9B03,9A0D,9A92,9A0E,9A91,9A0F,9A90,9A10,9A8C,9A16,9A9B,9A19,9A97,9A23,9B03,9A24,9A99,9A2B,9A9E,9A2D,9A98,9A2E,9A9D,9A30,817E,9A36,9A7A,9A37,9A9A,9A38,9A9F,9A3E,9AA1,9A40,84E6,9A41,9A9C,9A42,9A96,9A43,9AA0,9A44,9AA2,9A45,9A71,9A4A,9A85,9A4C,9A95,9A4D,9A81,9A4F,9AA3,9A55,9A84,9A57,9A8C,9A58,9AA1,9A5A,60CA,9A5B,9A7F,9A5F,9AA4,9A62,9A74,9A64,9AA7,9A65,9AA5,9A66,9AA6,9A6A,9A8A,9A6B,9A89,9AAF,80AE,9ABD,817F,9ABE,9CA0,9AC8,8180,9ACF,9AC5,9AD2,810F,9AD4,4F53,9AD5,9ACC,9AD6,9ACB,9AE3,4EFF,9AE5,9AEF,9AEE,53D1,9AF4,4F5B,9B00,5243,9B06,677E,9B09,9B03,9B0D,80E1,9B1A,987B,9B22,9B13,9B25,6597,9B26,6597,9B27,95F9,9B28,54C4,9B29,960B,9B2A,6597,9B2D,6597,9B2E,9604,9B30,90C1,9B31,90C1,9B39,9B36,9B4E,9B49,9B58,9B47,9B5A,9C7C,9B5B,9C7D,9B62,9C7E,9B68,9C80,9B6F,9C81,9B74,9C82,9B77,9C7F,9B81,9C85,9B83,9C86,9B8A,9C8C,9B8B,9C89,9B8D,9C8F,9B8E,9C87,9B90,9C90,9B91,9C8D,9B92,9C8B,9B93,9C8A,9B9A,9C92,9B9C,9C98,9B9D,9C9E,9B9E,9C95,9BA6,9C96,9BAA,9C94,9BAB,9C9B,9BAD,9C91,9BAE,9C9C,9BB6,9CAA,9BC0,9CA7,9BC1,9CA0,9BC7,9CA9,9BC9,9CA4,9BCA,9CA8,9BD2,9CAC,9BD4,9CBB,9BD5,9CAF,9BD6,9CAD,9BD7,9C9E,9BDB,9CB7,9BDD,9CB4,9BE1,9CB1,9BE2,9CB5,9BE4,9CB2,9BE7,9CB3,9BE8,9CB8,9BEA,9CAE,9BEB,9CB0,9BF4,9CBA,9BFD,9CAB,9BFF,9CCA,9C01,9CC8,9C03,9CC2,9C08,9CBD,9C09,9CC7,9C0D,9CC5,9C0F,9CBE,9C10,9CC4,9C13,9CC3,9C1B,9CC1,9C1C,9CD2,9C1F,9CD1,9C23,9CA5,9C25,9CCF,9C28,9CCE,9C29,9CD0,9C2D,9CCD,9C2E,9CC1,9C31,9CA2,9C32,9CCC,9C33,9CD3,9C35,9CD8,9C37,9CA6,9C39,9CA3,9C3A,9CB9,9C3B,9CD7,9C3C,9CDB,9C3E,9CD4,9C42,9CC9,9C45,9CD9,9C48,9CD5,9C49,9CD6,9C52,9CDF,9C53,9CDD,9C54,9CDD,9C56,9CDC,9C57,9CDE,9C58,9C9F,9C5D,9CBC,9C5F,9C8E,9C60,9C99,9C64,9CE1,9C67,9CE2,9C6D,9C9A,9C6F,9CE0,9C77,9CC4,9C78,9C88,9C7A,9CA1,9C7B,9C9C,9CE5,9E1F,9CE9,9E20,9CEC,51EB,9CF2,9E24,9CF3,51E4,9CF4,9E23,9CF6,9E22,9D06,9E29,9D07,9E28,9D08,96C1,9D09,9E26,9D12,9E30,9D15,9E35,9D1B,9E33,9D1D,9E32,9D1E,9E2E,9D1F,9E31,9D23,9E2A,9D26,9E2F,9D28,9E2D,9D2F,9E38,9D30,9E39,9D34,9E3B,9D3B,9E3F,9D3F,9E3D,9D42,9E3A,9D43,9E3C,9D49,9E3E,9D50,9E40,9D51,9E43,9D52,9E46,9D53,9E41,9D5C,9E48,9D5D,9E45,9D5E,9E45,9D60,9E44,9D61,9E49,9D6A,9E4C,9D6C,9E4F,9D6E,9E50,9D6F,9E4E,9D70,96D5,9D72,9E4A,9D76,9E26,9D7E,9E4D,9D87,9E2B,9D89,9E51,9D8A,9E52,9D93,9E4B,9D98,9E55,9D9A,9E57,9DA1,9E56,9DA5,9E5B,9DA9,9E5C,9DAC,9E27,9DAF,83BA,9DB2,9E5F,9DB4,9E64,9DB9,9E60,9DBA,9E61,9DBB,9E58,9DBC,9E63,9DBF,9E5A,9DC0,9E5A,9DC1,9E62,9DC2,9E5E,9DC4,9E21,9DCA,9E5D,9DD3,9E67,9DD7,9E25,9DD9,9E37,9DDA,9E68,9DE5,9E36,9DE6,9E6A,9DEB,9E54,9DEF,9E69,9DF0,71D5,9DF2,9E6B,9DF3,9E47,9DF8,9E6C,9DF9,9E70,9DFA,9E6D,9E07,9E6F,9E0C,9E71,9E0E,83BA,9E0F,9E72,9E15,9E2C,9E18,9E74,9E1A,9E66,9E1B,9E73,9E1D,9E42,9E1E,9E3E,9E75,5364,9E79,54B8,9E7A,9E7E,9E7B,54B8,9E7D,76D0,9E90,9E9F,9E97,4E3D,9E9E,7350,9EA4,7C97,9EA5,9EA6,9EA9,9EB8,9EAA,9762,9EAF,66F2,9EB4,9EB9,9EB5,9762,9EBC,9EBD,9EC3,9EC4,9ECC,9EC9,9EDE,70B9,9EE8,515A,9EF2,9EEA,9EF4,9709,9EF6,9EE1,9EF7,9EE9,9EFD,9EFE,9EFF,9F0B,9F03,86D9,9F07,9CCC,9F08,9CD6,9F09,9F0D,9F15,51AC,9F34,9F39,9F4A,9F50,9F4B,658B,9F4E,8D4D,9F4F,9F51,9F52,9F7F,9F54,9F80,9F55,9F81,9F57,9F82,9F59,9F85,9F5C,9F87,9F5F,9F83,9F60,9F86,9F61,9F84,9F63,51FA,9F66,9F88,9F67,556E,9F69,54AC,9F6A,9F8A,9F6C,9F89,9F72,9F8B,9F76,816D,9F77,9F8C,9F8D,9F99,9F90,5E9E,9F94,9F9A,9F95,9F9B,9F9C,9F9F,E019,4FEB,E024,6DEB,E026,4F2A,E027,4FCA,E028,4FCA,E02A,98A5,E02F,515C,E039,5197,E03F,9E3B,E047,51E1,E04B,7B3A,E04F,52AB,E050,52AB,E051,5238,E054,6548,E055,6555,E05E,6C47,E062,5941,E079,5395,E081,53C2,E08F,541F,E0A5,6168,E0AC,55E5,E0C9,9636,E0CC,58A9,E0CF,575B,E0D1,5A7F,E0DE,542F,E0E1,59CA,E0E6,598A")
				.append(
						",E0F0,59FB,E102,5B7D,E107,5B81,E10C,5B9D,E10F,514B,E112,73CD,E118,5742,E119,5CE8,E11A,5CF0,E11E,6606,E11F,4ED1,E123,5CA9,E12A,5E06,E133,5EC9,E134,53A9,E13A,8FEB,E13C,56DE,E13D,4E43,E14C,9792,E14F,951D,E151,904D,E15B,5306,E166,5306,E16E,60AB,E170,607F,E174,51ED,E176,61A9,E188,62D6,E18C,6377,E191,6491,E19A,643A,E19C,643A,E19D,53D9,E1A2,626C,E1A5,65AB,E1B9,6670,E1D0,67CF,E1D3,67F3,E1DB,6885,E1DC,68A8,E1E8,68D5,E1E9,77E9,E1F3,696B,E1F6,51F3,E1F9,90C1,E1FC,6B3E,E1FD,5386,E209,51B0,E20D,6C61,E21A,9C86,E222,8385,E224,6D85,E234,79CB,E240,955A,E241,6F31,E255,6CD5,E25B,707E,E275,71EE,E279,9528,E27C,5E8A,E27F,7A97,E285,7281,E28C,733F,E2A0,7434,E2A5,7409,E2A7,73B3,E2B9,7483,E2BB,7816,E2BD,5C1D,E2C4,8015,E2C5,7565,E2C6,4EA9,E2CA,758F,E2D5,768B,E2D6,768B,E2DC,9F13,E2DD,7897,E2EE,7887,E2F2,68CB,E2F7,70AE,E30A,7940,E310,7CB3,E31A,7A1A,E31E,9631,E320,7A97,E321,7A91,E327,4F2B,E328,5E76,E329,7AD6,E32B,7B11,E333,7BB8,E339,5E1A,E344,79D5,E346,78F7,E349,7CBD,E353,7ED4,E356,7EE3,E357,7EBF,E359,7EF7,E35A,7EEA,E361,8913,E363,7F1C,E366,7F30,E36A,7EE6,E36D,6A3D,E370,9A82,E371,7F8C,E372,7F8C,E373,7FA4,E37A,7FF1,E38C,80DA,E38E,80C1,E395,4E3E,E397,9986,E39D,8239,E3AE,854A,E3B8,8354,E3C6,679C,E3CA,843C,E3DF,83BC,E3E5,9EBB,E3F1,854A,E3FC,867B,E402,87F9,E406,6064,E408,4F17,E40C,536B,E40F,88E4,E410,887D,E415,9738,E416,7F81,E417,89CE,E418,7779,E42A,6B4C,E42F,8BB9,E433,4EC7,E439,737E,E445,8D5E,E447,8D63,E453,803D,E454,88F8,E457,8F84,E458,8F6F,E45F,519C,E470,6EAF,E479,916C,E480,94A9,E483,94B5,E485,9508,E48C,9539,E493,9570,E498,9274,E499,77FF,E49D,950E,E49F,9614,E4A7,5819,E4A8,575E,E4AA,90BB,E4C5,9759,E4D2,9774,E4D7,97ED,E4DA,54CD,E4DF,9896,E4E1,816E,E4E2,6194,E4F5,7CD6,E4FB,9B03,E4FD,9A8C,E4FE,9AA1,E506,4F5B,E509,6597,E515,9C87,E518,9CCA,E522,9E4D,E525,83BA,E527,9E21,E530,9E9F,E532,7350,E535,9762,E536,66F2,E540,9CD6,E54F,54AC,E560,9518,E568,70E8,E595,67FF,E5C4,52A8,E5D0,4F83,E5E7,521B,E5F2,6885,E60B,9644,E60D,57B1,E66C,6332,E66D,634F,E691,6620,E695,662F,E6A5,6960,E6A8,520A,E6B4,67A7,E6D3,70AF,E6D4,95F8,E6EB,9545,E703,948E,E70D,89C6,E71C,952B,E72C,7A1A,E73E,4FDF,E757,7EF5,E75A,575B,E785,98D8,E787,6A2F,E78C,8431,E798,8702,E7A1,6742,E7D6,916C,E7DB,950F,E7E0,95F9,E7E1,54C4,E80A,97E7,E810,989D,E812,950D,E813,98D3,E819,7FFB,E81F,7CD5,E820,81B3,E834,573A,E845,9CDD,E846,9C9C,E847,9CD8,E848,9CC4,E852,9E74,E859,9E5B,E85E,8431,E870,9533,E882,6E81,E888,575D,E89E,9501,E89F,954C,E8B1,9503,E904,6EDF,E98B,72B8,E9A3,4EA9,E9AE,762A,E9B1,7199,E9BB,770D,E9E3,7BE1,E9F1,7EF1,E9F2,7E41,E9F5,81BB,E9FB,8022,EA0C,80E8,EA14,80A0,EA2C,53C2,EA30,836C,EA41,88E5,EA44,8BBB,EA4D,846C,EA5A,8F77,EA5C,8F71,EA6A,94DE,EA6D,94D8,EA72,952A,EA73,953F,EA7A,9562,EA7E,9572,EA7F,9569,EA91,98E7,EA93,9979,EA96,9983,EA97,9987,EA99,998D,EA9A,9995,EAA2,9C85,EAA3,9CB4,EAA5,9CBA,EAAA,9E3E,EAAB,9CB9,EAAE,9E4B,EAB0,9E5A,EAB4,54B8,EABD,7076,EAC4,5992,EAD2,5492,EAED,9C9C,EAEF,7EB8,EAF3,671F,EB01,8C2A,EB16,6982,EB24,7F6A,EB28,60B4,EB29,817F,EB55,7199,EB76,7629,EB86,9487,EB9D,716E,EBB5,9565,EBC9,8425,EBD1,7CE0,EBD2,71D5,EBDC,5CBD,EBE0,8BCD,EBE5,57AF,EC07,8912,EC27,954E,EC39,684C,EC3E,6735,EC51,94B7,EC82,557C,ECAC,54BF,ECBC,543B,ECBD,9E45,ECBF,7618,ECC0,9CD9,ECD1,6168,ECD5,5773,ECDD,53A9,ECE6,62D7,ECE7,97E7,ED14,949A,ED20,9CBC,ED30,9E40,ED48,9E6E,ED56,624E,ED59,6643,ED5A,8FE5,ED5B,5026,ED61,5AE9,ED68,9C89,ED74,549D,ED7F,9B36,ED82,5C43,ED84,8116,ED99,5F04,ED9C,6597,ED9E,9604,EDA3,9B03,EDA5,9CC1,EDA9,51A4,EDB2,4F84,EDB4,889C,EDC5,4EA9,EDC8,672D,EDD3,547C,EDDF,9C9E,EDE3,8844,EDE6,69C1,EDEA,536E,EDF2,8D81,EE05,7F5A,EE07,560E,EE0D,8E44,EE0F,80EB,EE13,8E74,EE16,7A3F,EE19,5BDF,EE26,8C2B,EE27,608D,EE29,8D4D,EE2D,5E19,EE37,75AC,EE3E,87F9,EE3F,8D4D,EE46,9994,EE47,9993,EE49,60ED,EE4C,621A,EE51,5384,EE5A,5C81,EE62,5941,EE6C,79F8,EE72,89C5,EE79,8106,EE80,87A8,EE81,814A,EE8D,7779,EE90,868A,EE95,5306,EE98,8315,EE9D,95F8,EE9F,9A6E,EEBB,4E10,EEBF,51FD,EEC2,5846,EEC6,531D,EEC7,5F5F,EED2,53E9,EED8,53E0,EEE5,5F87,EEEF,768B,EEF6,8109,EEFC,6106,EEFD,5A34,EF00,6064,EF01,68CB,EF05,5986,EF07,997B,EF0C,91CC,EF0D,9508,EF12,86D9,EF14,658B,EF29,5CD2,EF30,5899,EF5E,90AA,EF6E,978D,EF73,9CE0,F2B3,82CF,F2B4,5854,F2BF,7CEF,F2C0,521B,F2C9,53EB,F2CC,584D,F3E4,52AB,F3EA,781C,F3EE,8D5D,F3F7,9E71,F3F8,9C83,F40D,4E7E,F416,54B1,F421,622E,F426,53A8,F434,542F,F470,9E50,F517,579B,F52D,8D9F,F531,9CD1,F532,9C8F,F5C6,7A97,F5E7,9CDA,F5F3,5E74,F603,6696,F63E,5BD3,F640,5F80,F646,535A,F656,63FF,F65E,6569,F66F,4E58,F69F,6DA9,F813,68CB,F814,9508,F815,91CC,F816,5899,F818,5986,F819,5A34");

		return sb.toString();
	}

}