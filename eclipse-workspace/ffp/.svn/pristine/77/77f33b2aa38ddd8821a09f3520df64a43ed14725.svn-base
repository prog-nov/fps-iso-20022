package com.forms.beneform4j.core.util.encrypt.impl;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.forms.beneform4j.core.util.encrypt.IEncrypt;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 加解密实现类，使用AES-128和Base64加密<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class BaseEncrypt implements IEncrypt {

    private String algorithm = "AES";
    private String encoding = "UTF-8";

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(String data, String key) {
        try {
            key = getEncryptKey(key);
            /*
             * 在IBM的JDK下面此段代码加解密结果和SUN JDK不一致 暂时屏蔽 KeyGenerator
             * keygen=KeyGenerator.getInstance(algorithm); //keygen.init(length, new
             * SecureRandom(key.getBytes(encoding))); SecureRandom secureRandom =
             * SecureRandom.getInstance("SHA1PRNG"); secureRandom.setSeed(key.getBytes(encoding));
             * keygen.init(length, secureRandom);
             */
            SecretKey secretKey = new SecretKeySpec(key.getBytes(encoding), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new String(Base64.encodeBase64(cipher.doFinal(data.getBytes(encoding))));
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String decode(String data, String key) {
        try {
            key = getEncryptKey(key);
            /*
             * 在IBM的JDK下面此段代码加解密结果和SUN JDK不一致 暂时屏蔽 KeyGenerator keygen=
             * KeyGenerator.getInstance(algorithm); //keygen.init(length, new
             * SecureRandom(key.getBytes(encoding))); SecureRandom secureRandom =
             * SecureRandom.getInstance("SHA1PRNG"); secureRandom.setSeed(key.getBytes(encoding));
             * keygen.init(length, secureRandom);
             */
            SecretKey secretKey = new SecretKeySpec(key.getBytes(encoding), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(data)), encoding);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * 获取实际密钥，子类可覆盖
     * 
     * @param key 原密钥
     * @return 实际用于加密的密钥
     */
    protected String getEncryptKey(String key) {
        return key;
    }

    /**
     * 获取加密算法
     * 
     * @return 加密算法
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * 设置加密算法
     * 
     * @param algorithm 加密算法，默认值AES
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * 获取编码
     * 
     * @return 编码
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 设置编码
     * 
     * @param encoding 编码，默认值UTF-8
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
