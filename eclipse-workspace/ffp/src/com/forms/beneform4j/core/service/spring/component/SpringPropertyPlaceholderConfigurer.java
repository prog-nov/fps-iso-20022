package com.forms.beneform4j.core.service.spring.component;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.encrypt.IEncrypt;
import com.forms.beneform4j.core.util.encrypt.impl.ConfigKeyEncrypt;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 可以处理加密配置的Spring属性配置文件<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SpringPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 表示加密字段的键值
     */
    private String encryptPropertyName = "encrypt";

    /**
     * 加密算法
     */
    private IEncrypt encryptAlgorithm = new ConfigKeyEncrypt();

    /**
     * 属性持久化
     */
    private IPropertiesStore propertiesStore;

    /**
     * 处理加密数据
     */
    @Override
    protected void convertProperties(Properties props) {
        String encryptPropertyName = getEncryptPropertyName();
        IEncrypt encryptAlgorithm = getEncryptAlgorithm();
        if (null == encryptAlgorithm || CoreUtils.isBlank(encryptPropertyName)) {
            super.convertProperties(props);
        } else {
            encryptPropertyName = "." + encryptPropertyName;
            Enumeration<?> propertyNames = props.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String propertyName = (String) propertyNames.nextElement();
                String propertyValue = props.getProperty(propertyName);
                if (propertyName.endsWith(encryptPropertyName)) {
                    props.remove(propertyName);// 将标志去掉
                    if (isEncrypt(propertyValue)) {
                        String p = propertyName.substring(0, propertyName.lastIndexOf("."));
                        if (props.containsKey(p)) {
                            String v = props.getProperty(p);
                            String convertedValue = decode(encryptAlgorithm, propertyValue, v);
                            if (null != convertedValue && !ObjectUtils.nullSafeEquals(v, convertedValue)) {
                                props.setProperty(p, convertedValue);
                            }
                        }
                    }
                } else {
                    String convertedValue = convertProperty(propertyName, propertyValue);
                    if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
                        props.setProperty(propertyName, convertedValue);
                    }
                }
            }
        }
        SpringHelp.setPlaceholderPropertis(props);
        storeProperties(props);
    }

    private String decode(IEncrypt encryptAlgorithm, String propertyValue, String v) {
        try {
            String convertedValue = encryptAlgorithm.decode(v, propertyValue);
            return convertedValue;
        } catch (Exception e) {
            CommonLogger.error("decode the encrypt data is error, data is " + v);
            return null;
        }
    }

    /**
     * 保存读取的属性
     * 
     * @param props
     */
    protected void storeProperties(Properties props) {
        IPropertiesStore propertiesStore = getPropertiesStore();
        if (null != propertiesStore) {
            propertiesStore.store(props);
        }
    }

    /**
     * 是否为加密数据
     * 
     * @param encrypt 加密标志
     * @return 是否加密
     */
    protected boolean isEncrypt(String encrypt) {
        return CoreUtils.string2Boolean(encrypt);
    }

    public String getEncryptPropertyName() {
        return encryptPropertyName;
    }

    public void setEncryptPropertyName(String encryptPropertyName) {
        this.encryptPropertyName = encryptPropertyName;
    }

    public IEncrypt getEncryptAlgorithm() {
        return encryptAlgorithm;
    }

    public void setEncryptAlgorithm(IEncrypt encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }

    public IPropertiesStore getPropertiesStore() {
        return propertiesStore;
    }

    public void setPropertiesStore(IPropertiesStore propertiesStore) {
        this.propertiesStore = propertiesStore;
    }

    /**
     * 属性存储接口
     */
    public interface IPropertiesStore {

        public void store(Properties properties);
    }
}
