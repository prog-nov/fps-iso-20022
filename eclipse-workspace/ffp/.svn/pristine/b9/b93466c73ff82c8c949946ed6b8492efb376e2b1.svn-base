package com.forms.beneform4j.util.toolimpl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 字符串工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class StringUtilsImpl {

    private static final StringUtilsImpl instance = new StringUtilsImpl() {};

    private StringUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static StringUtilsImpl getInstance() {
        return instance;
    }

    private static final AntPathMatcher antMatcher = new AntPathMatcher();

    /**
     * 是否为空
     * 
     * @param cs 字符串序列
     * @return 是否为空
     */
    public boolean isBlank(CharSequence cs) {
        return CoreUtils.isBlank(cs);
    }

    /**
     * 格式化字符串，去掉字符串中的前后空格字符，压缩字符串中间的相邻的多个空白字符为一个空格
     * 
     * @param src 字符串
     * @return 格式化后的字符串
     */
    public String formatWhitespace(String src) {
        return CoreUtils.formatWhitespace(src);
    }

    /**
     * 格式化字符串
     * 
     * <pre>
     * 	<ul>
     * 	<li>format("中华{0}共和国{1}央{0}政府","人民", "中")        ==>中华人民共和国中央人民政府
     *  <li>format("中华{0}共和国{1}央{0}政府","人民")              ==>中华人民共和国央人民政府
     *  <li>format("中华{0}共和国{1}央{0}政府","人民", "中", "多余") ==>中华人民共和国中央人民政府
     *  <li>format("中华人民共和国中央人民政府","无占位符")           ==>中华人民共和国中央人民政府
     *  </ul>
     * </pre>
     * 
     * @param src 模式字符串
     * @param objects 占位符参数
     * @return 格式化后的字符串
     */
    public String formatString(String src, Object... objects) {
        return CoreUtils.format(src, objects);
    }

    /**
     * 将下划线格式的字段转为驼峰式，多个连续下划线作为一个处理
     * 
     * <pre>
     * <ul>
     * <li>"__SOME_FIELD"==>someField
     * <li>"SOME_FIELD"==>someField
     * <li>"_-SOME_FIELD"==>someField
     * <li>"_-SOME_--_FIELD_"==>someField
     * </ul>
     * </pre>
     * 
     * @param str 字符串
     * @return 驼峰式字符串
     */
    public String convertToCamel(String str) {
        return CoreUtils.convertToCamel(str);
    }

    /**
     * 路径是否匹配
     * 
     * @param pattern 匹配模式
     * @param path 路径
     * @return 是否匹配
     */
    public boolean antMatch(String pattern, String path) {
        if (null == pattern || null == path) {
            return false;
        }
        return antMatcher.match(pattern, path);
    }

    /**
     * 忽略大小写的情况下是否以指定字符串开头
     * 
     * @param str 字符串
     * @param prefix 前缀
     * @return 忽略大小时，str是否以prefix开头
     */
    public boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return (str == null && prefix == null);
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    /**
     * 忽略大小写的情况下是否以指定字符串结尾
     * 
     * @param str 字符串
     * @param suffix 后缀
     * @return 忽略大小时，str是否以suffix结束
     */
    public boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return (str == null && suffix == null);
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        int strOffset = str.length() - suffix.length();
        return str.regionMatches(true, strOffset, suffix, 0, suffix.length());
    }

    /**
     * 安全比较两个字符串是否相同
     * 
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相等
     */
    public boolean safeEquals(String str1, String str2) {
        return CoreUtils.safeEquals(str1, str2);
    }

    /**
     * 安全比较两个字符串忽略大小写是否相同
     * 
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相等
     */
    public boolean safeEqualsIgnoreCase(String str1, String str2) {
        return CoreUtils.safeEqualsIgnoreCase(str1, str2);
    }

    /**
     * 获取使用MD5加密后的字符串
     * 
     * @param str 字符串
     * @return 字符串的MD5值
     */
    public String getMd5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 获取文件MD5值
     * 
     * @param file 文件
     * @return 文件的MD5值
     */
    public String getMd5(File file) {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            String md5 = DigestUtils.md5Hex(input);
            return md5;
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(input);
        }
    }

    /**
     * 获取使用sha加密后的字符串
     * 
     * @param str 字符串
     * @return sha256加密后的字符串
     */
    public String getSha256(String str) {
        return DigestUtils.sha256Hex(str);
    }

    /**
     * 获取文件sha值
     * 
     * @param file 文件
     * @return 文件使用sha256加密后的字符串
     */
    public String getSha256(File file) {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            String sha = DigestUtils.sha256Hex(input);
            return sha;
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(input);
        }
    }

    /**
     * Base64加密（可逆）
     * 
     * @param data 源字符串
     * @return 加密后的字符串
     */
    public String encodeBase64(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    /**
     * Base64解密
     * 
     * @param data 源字符串
     * @return 解密后的字符串
     */
    public String decodeBase64(String data) {
        byte[] s = Base64.decodeBase64(data);
        return new String(s);
    }

    /**
     * Hex加密（可逆）
     * 
     * @param data 源字符串
     * @return 加密后的字符串
     */
    public String encodeHex(String data) {
        return Hex.encodeHexString(data.getBytes());
    }

    /**
     * Hex解密
     * 
     * @param data 源字符串
     * @return 解密后的字符串
     */
    public String decodeHex(String data) {
        try {
            byte[] s = Hex.decodeHex(data.toCharArray());
            return new String(s);
        } catch (DecoderException e) {
            Throw.throwRuntimeException(e);
            return null;
        }
    }

    /**
     * 处理Java特殊字符
     * 
     * @param input 输入字符串
     * @return 转义后的字符串
     */
    public String escapeJava(String input) {
        return StringEscapeUtils.escapeJava(input);
    }

    /**
     * 反处理Java特殊字符
     * 
     * @param input 输入字符串
     * @return 反转义后的字符串
     */
    public String unescapeJava(String input) {
        return StringEscapeUtils.unescapeJava(input);
    }

    /**
     * 处理HTML4特殊字符
     * 
     * @param input 输入字符串
     * @return 转义后的字符串
     */
    public String escapeHtml4(String input) {
        return StringEscapeUtils.escapeHtml4(input);
    }

    /**
     * 反处理HTML4特殊字符
     * 
     * @param input 输入字符串
     * @return 反转义后的字符串
     */
    public String unescapeHtml4(String input) {
        return StringEscapeUtils.unescapeHtml4(input);
    }

    /**
     * 处理XML特殊字符
     * 
     * @param input 输入字符串
     * @return 转义后的字符串
     */
    public String escapeXml(String input) {
        return StringEscapeUtils.escapeXml10(input);
    }

    /**
     * 反处理XML特殊字符
     * 
     * @param input 输入字符串
     * @return 反转义后的字符串
     */
    public String unescapeXml(String input) {
        return StringEscapeUtils.unescapeXml(input);
    }

    /**
     * 获取随机字母数字，长度为count
     * 
     * @param count 字符串长度
     * @return 随机字母数字组成的字符串
     */
    public String getRandomAlphanumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * 获取随机数字，长度为count
     * 
     * @param count 字符串长度
     * @return 随机数字组成的字符串
     */
    public String getRandomNumeric(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取随机字符串，长度为count
     * 
     * @param count 字符串长度
     * @param chars 指定的字符范围
     * @return 指定字符范围中随机字符组成的字符串
     */
    public String random(int count, char... chars) {
        return RandomStringUtils.random(count, chars);
    }

    /**
     * 获取包含日期的随机数字，随机部分长度为count，日期格式为平台配置中的BaseConfig.getDateFormat()，默认格式yyyyMMdd
     * 
     * @param count 随机部分长度
     * @return 当前日期和随机数字组成的字符串
     */
    public String getRandomNumericIncludeDate(int count) {
        return Tool.DATE.getDate() + RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取包含日期时间的随机数字，随机部分长度为count，格式为yyyyMMddHHmmss
     * 
     * @param count 随机部分长度
     * @return 包含日期、时间和随机数字组成的字符串
     */
    public String getRandomNumericIncludeTime(int count) {
        return Tool.DATE.getFormatDate(new Date(), "yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取随机主键，前17位为日期时间（精确到毫秒），格式为yyyyMMddHHmmssSSS
     * 
     * @param count 随机部分数字的个数
     * @return 随机主键，由17位日期时间（精确到毫秒）和随机数字组成
     */
    public String getRandomKeyId(int count) {
        return Tool.DATE.getFormatDate(new Date(), "yyyyMMddHHmmssSSS") + RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取总长度为32位的随机主键，前17位为日期时间（精确到毫秒），后15位为随机数字，日期格式为yyyyMMddHHmmssSSS
     * 
     * @return 随机主键
     */
    public String getRandomKeyId() {
        return Tool.DATE.getFormatDate(new Date(), "yyyyMMddHHmmssSSS") + RandomStringUtils.randomNumeric(15);
    }

    /**
     * 字符串分割
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @return 分割后的字符串列表
     */
    public List<String> splitToList(String src, String separator) {
        return CoreUtils.splitToList(src, separator);
    }

    /**
     * 字符串分割成固定长度的List，如果不够长度，使用默认字符串填充，如果长度超过指定长度，保持不变
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @param minSize 返回集合的最小长度
     * @param defaultString 填充的默认字符串
     * @return 分割后的字符串列表
     */
    public List<String> splitToList(String src, String separator, int minSize, String defaultString) {
        return CoreUtils.splitToList(src, separator, minSize, defaultString);
    }

    /**
     * 字符串分割
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @return 分割后的字符串数组
     */
    public String[] split(String src, String separator) {
        return CoreUtils.split(src, separator);
    }

    /**
     * 字符串分割分割成固定数目的数组，如果不够，使用默认字符串填充，如果长度超过指定长度，保持不变
     * 
     * @param src 要分割的字符串
     * @param separator 分隔符
     * @param minLength 分割后数组的最小长度
     * @param defaultString 默认字符串
     * @return 分割后的字符串数组
     */
    public String[] split(String src, String separator, int minLength, String defaultString) {
        return CoreUtils.split(src, separator, minLength, defaultString);
    }

    /**
     * 用连接符连接列表中的项
     * 
     * @param list 对象列表
     * @param separator 连接符
     * @return 连接后的字符串
     */
    public String join(List<?> list, String separator) {
        return CoreUtils.join(list, separator);
    }

    /**
     * 用连接符连接数组中的项
     * 
     * @param arr 对象数组
     * @param separator 连接符
     * @return 连接后的字符串
     */
    public String join(Object[] arr, String separator) {
        return CoreUtils.join(arr, separator);
    }

    /**
     * 左填充
     * 
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     * 
     * @param str 原字符串
     * @param size 填充后字符串最小长度
     * @param padStr 填充的字符串
     * @return 填充后的字符串
     */
    public String leftPad(final String str, final int size, final String padStr) {
        return StringUtils.leftPad(str, size, padStr);
    }

    /**
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad("", 3, "z")      = "zzz"
     * StringUtils.rightPad("bat", 3, "yz")  = "bat"
     * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.rightPad("bat", 1, "yz")  = "bat"
     * StringUtils.rightPad("bat", -1, "yz") = "bat"
     * StringUtils.rightPad("bat", 5, null)  = "bat  "
     * StringUtils.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     * 
     * @param str 原字符串
     * @param size 填充后字符串的最小长度
     * @param padStr 填充的字符串
     * @return 填充后的字符串
     */
    public String rightPad(final String str, final int size, final String padStr) {
        return StringUtils.rightPad(str, size, padStr);
    }

    /**
     * 获取List中的数据，超出范围时返回默认值
     * 
     * @param list 列表
     * @param index 索引
     * @param defaultValue 默认值
     * @return 索引有效时，返回列表对应索引处的值，索引无效时，返回默认值
     */
    public String getListValue(List<String> list, int index, String defaultValue) {
        if (null == list || index < 0 || index >= list.size()) {
            return defaultValue;
        } else {
            return list.get(index);
        }
    }

    /**
     * 使用gzip进行压缩
     * 
     * @param str 压缩前字符串
     * @return 压缩后字符串
     */
    public String gzip(String str) {
        if (Tool.CHECK.isBlank(str)) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            return new String(out.toByteArray());
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(gzip);
        }
    }

    /**
     * 解压gzip压缩的字符串
     * 
     * @param str 解压缩前字符串
     * @return 解压缩后字符串
     */
    public String ungzip(String str) {
        if (Tool.CHECK.isBlank(str)) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        String decompressed = null;
        try {
            byte[] compressed = str.getBytes();
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[8096];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(ginzip, in, out);
        }
        return decompressed;
    }

    /**
     * 使用zip进行压缩
     * 
     * @param str 压缩前字符串
     * @return 压缩后字符串
     */
    public String zip(String str) {
        if (Tool.CHECK.isBlank(str)) {
            return str;
        }
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            byte[] compressed = out.toByteArray();
            compressedStr = new String(compressed);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(zout, out);
        }
        return compressedStr;
    }

    /**
     * 解压zip压缩的字符串
     * 
     * @param str 解压缩前字符串
     * @return 解压缩后字符串
     */
    public String unzip(String str) {
        if (Tool.CHECK.isBlank(str)) {
            return str;
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = str.getBytes();
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[8096];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            Tool.IO.closeQuietly(zin, in, out);
        }
        return decompressed;
    }

    /**
     * 根据模板tpl解析text中的命名参数值，并设置进properties中，如 中国银行2016年3月份对公贷款余额统计列表,
     * 中国银行${year}年${month}月份对公贷款余额统计列表 ==> year-2016,month-3
     * 
     * @param properties 属性对象
     * @param text 字符串文本
     * @param tpl 字符串模板，可以使用${}占位符
     */
    public void parseNamedValue(Properties properties, String text, String tpl) {
        if (null != text && null != tpl) {
            StringBuffer sb = new StringBuffer("\\Q");
            Matcher matcher = pattern.matcher(tpl);
            List<String> vars = new ArrayList<String>();
            while (matcher.find()) {
                vars.add(matcher.group(1));
                String length = matcher.group(2);
                String replace = "\\\\E(.";
                if (null != length) {
                    replace += "{" + length + "}";
                } else {
                    replace += "+";
                }
                replace += ")\\\\Q";
                matcher.appendReplacement(sb, replace);
            }
            matcher.appendTail(sb);
            sb.append("\\E");
            Pattern pp = Pattern.compile(sb.toString());
            matcher = pp.matcher(text);
            if (matcher.find()) {
                for (int i = 0, s = vars.size(); i < s; i++) {
                    String value = matcher.group(i + 1);
                    properties.put(vars.get(i), value == null ? "" : value);
                }
            }
        }
    }

    /**
     * 将uri转换为rest风格（去掉最后一个.号及后缀）
     * 
     * @param uri uri值
     * @return 去掉最后一个.号之后的uri
     */
    public String getRestUri(String uri) {
        if (null != uri) {
            int i = uri.lastIndexOf('.');
            if (-1 != i) {
                uri = uri.substring(0, i);
            }
        }
        return uri;
    }

    /**
     * 是否有效的Excel列索引,[0-255]
     * 
     * @param columnIndex 列索引
     * @return 是否有效的索引
     */
    public boolean isValidColumnIndex(int columnIndex) {
        return columnIndex >= 0 && columnIndex < EXCEL_COLUMNS.size();
    }

    /**
     * 是否有效的Excel列位置,[A-IV]
     * 
     * @param columnPosition 列位置
     * @return 是否有效的列位置
     */
    public boolean isValidColumnPosition(String columnPosition) {
        if (null == columnPosition) {
            return false;
        }
        return EXCEL_COLUMNS.contains(columnPosition.toUpperCase());
    }

    /**
     * 根据列位置获取列索引
     * 
     * @param columnPosition 列位置
     * @return 和列位置对应的列索引，如果列位置无效，返回-1
     */
    public int getColumnPositionIndex(String columnPosition) {
        if (null == columnPosition) {
            return -1;
        }
        return EXCEL_COLUMNS.indexOf(columnPosition.toUpperCase());
    }

    /**
     * 根据列索引获取列位置
     * 
     * @param columnIndex 列索引
     * @return 和列索引对应的列位置，若列索引无效，返回-1
     */
    public String getColumnPosition(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= EXCEL_COLUMNS.size()) {
            throw new IllegalArgumentException("the index " + columnIndex + " is invalid.");
        }
        return EXCEL_COLUMNS.get(columnIndex);
    }

    /**
     * 查找命名变量的正则表达式
     */
    private static final Pattern pattern = Pattern.compile("\\$\\{\\s*(\\w+)\\s*(?:,\\s*(\\d+))?\\s*\\}");
    /**
     * Excel中列的有效位置
     */
    private static final List<String> EXCEL_COLUMNS = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AI", "AJ", "AK", "AL", "AM", "AN", "AO", "AP", "AQ", "AR", "AS", "AT", "AU", "AV", "AW", "AX", "AY", "AZ", "BA", "BB", "BC", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BK", "BL", "BM", "BN", "BO", "BP", "BQ", "BR", "BS", "BT", "BU", "BV", "BW", "BX", "BY", "BZ", "CA", "CB", "CC", "CD", "CE", "CF", "CG", "CH", "CI", "CJ", "CK", "CL", "CM", "CN", "CO", "CP", "CQ", "CR", "CS", "CT", "CU", "CV", "CW", "CX", "CY", "CZ", "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH", "DI", "DJ", "DK", "DL", "DM", "DN", "DO", "DP", "DQ", "DR", "DS", "DT", "DU", "DV", "DW", "DX", "DY", "DZ", "EA", "EB", "EC", "ED", "EE", "EF", "EG", "EH", "EI", "EJ", "EK", "EL", "EM", "EN", "EO", "EP", "EQ", "ER", "ES", "ET", "EU", "EV", "EW", "EX", "EY", "EZ", "FA", "FB", "FC",
            "FD", "FE", "FF", "FG", "FH", "FI", "FJ", "FK", "FL", "FM", "FN", "FO", "FP", "FQ", "FR", "FS", "FT", "FU", "FV", "FW", "FX", "FY", "FZ", "GA", "GB", "GC", "GD", "GE", "GF", "GG", "GH", "GI", "GJ", "GK", "GL", "GM", "GN", "GO", "GP", "GQ", "GR", "GS", "GT", "GU", "GV", "GW", "GX", "GY", "GZ", "HA", "HB", "HC", "HD", "HE", "HF", "HG", "HH", "HI", "HJ", "HK", "HL", "HM", "HN", "HO", "HP", "HQ", "HR", "HS", "HT", "HU", "HV", "HW", "HX", "HY", "HZ", "IA", "IB", "IC", "ID", "IE", "IF", "IG", "IH", "II", "IJ", "IK", "IL", "IM", "IN", "IO", "IP", "IQ", "IR", "IS", "IT", "IU", "IV");
}
