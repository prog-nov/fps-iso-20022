package com.forms.beneform4j.webapp.common;

import java.util.List;

import com.forms.beneform4j.core.util.annotation.Configable;
import com.forms.beneform4j.web.WebBeneform4jConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 应用配置<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-16<br>
 */
public class AppConfig extends WebBeneform4jConfig {

    @Configable
    private static List<String> unUpdateSessionUrlPatterns = null;

    public static List<String> getUnUpdateSessionUrlPatterns() {
        return unUpdateSessionUrlPatterns;
    }

    public void setUnUpdateSessionUrlPatterns(List<String> unUpdateSessionUrlPatterns) {
        AppConfig.unUpdateSessionUrlPatterns = unUpdateSessionUrlPatterns;
    }

}
