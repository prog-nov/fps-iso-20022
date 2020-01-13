package com.forms.beneform4j.core.service.spring.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.ObjectUtils;

import com.forms.beneform4j.core.service.exception.ServiceExceptionCodes;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 加载了平台默认国际化文件的Spring国际化组件<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SpringReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 4.3 之后版本
            @SuppressWarnings("unchecked")
            Set<String> basenameSet = (Set<String>) FieldUtils.readField(this, "basenameSet", true);
            String[] basenames = BaseConfig.getBeneform4jLocaleBasenames();
            if (!ObjectUtils.isEmpty(basenames)) {
                for (String basename : basenames) {
                    if (!CoreUtils.isBlank(basename)) {
                        basenameSet.add(basename.trim());
                    }
                }
            }
        } catch (Throwable e) {
            String[] basenames = (String[]) FieldUtils.readField(this, "basenames", true);
            if (null == basenames || basenames.length == 0) {
                basenames = BaseConfig.getBeneform4jLocaleBasenames();
            } else {
                List<String> bs = new ArrayList<String>(Arrays.asList(BaseConfig.getBeneform4jLocaleBasenames()));
                for (String basename : basenames) {
                    if (bs.contains(basename)) {
                        Throw.createRuntimeException(ServiceExceptionCodes.BF030006, basename);
                    } else {
                        bs.add(basename);
                    }
                }
                if (null != bs && !bs.isEmpty()) {
                    String[] ss = new String[bs.size()];
                    bs.toArray(ss);
                    basenames = ss;
                }
            }
            FieldUtils.writeField(this, "basenames", basenames, true);
        }
    }

}
