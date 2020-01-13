package com.forms.beneform4j.core.util.env;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台版本<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public enum Beneform4jVersion {

    V_1_0_0("1.0.0", 1.7),

    V_1_0_1("1.0.1", 1.7),

    V_1_1_0("1.1.0", 1.7);

    /**
     * 平台版本，指向最新版本
     */
    public static final Beneform4jVersion Version = V_1_1_0;

    /**
     * 版本号
     */
    private String version;
    /**
     * 要求的最低JDK版本号
     */
    private double jdkVersion;

    private Beneform4jVersion(String version, double jdkVersion) {
        this.version = version;
        this.jdkVersion = jdkVersion;
    }

    /**
     * 校验JDK版本是否符合要求
     */
    public static void checkJdkVersion() {
        if (EnvConsts.JDK_VERSION < Version.jdkVersion) {
            Throw.throwRuntimeException(ExceptionCodes.BF010004, EnvConsts.JDK_VERSION, Version.jdkVersion);
        }
    }

    public String toString() {
        return "beneform4j-" + version;
    }
}
