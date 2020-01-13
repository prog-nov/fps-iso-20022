package com.forms.beneform4j.web.request.log.impl;

import java.util.HashSet;
import java.util.Set;

import com.forms.beneform4j.web.request.log.IParamConvert;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 配置敏感参数名的转换器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-19<br>
 */
public class SensitivesParamConvert implements IParamConvert {

    /**
     * 敏感的参数名称集合
     */
    private Set<String> sensitiveNames;

    /**
     * 替换的字符
     */
    private char replaceChar = '*';

    @Override
    public String convert(String name, String src) {
        if (null == name || null == src || sensitiveNames == null || !sensitiveNames.contains(name.toLowerCase())) {
            return src;
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = src.length() - 1; i > 0; i--) {
                sb.append(replaceChar);
            }
            return sb.toString();
        }
    }

    public Set<String> getSensitiveNames() {
        return sensitiveNames;
    }

    public void setSensitiveNames(Set<String> sensitiveNames) {
        if (null != sensitiveNames) {
            this.sensitiveNames = new HashSet<String>();
            for (String name : sensitiveNames) {
                this.sensitiveNames.add(name.toLowerCase());
            }
        }
    }

    public char getReplaceChar() {
        return replaceChar;
    }

    public void setReplaceChar(char replaceChar) {
        this.replaceChar = replaceChar;
    }
}
