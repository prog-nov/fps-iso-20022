package com.forms.beneform4j.util.json.serial.stdexp;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.forms.beneform4j.util.json.serial.ISerialConfig;
import com.forms.beneform4j.util.json.serial.SerialConfigContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 类侦测器，实现一般JavaBean的属性别名、过滤等功能<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
public class JsonClassIntrospector extends BasicClassIntrospector {

    /**
     * 
     */
    private static final long serialVersionUID = 4095454657615388622L;

    @Override
    public BasicBeanDescription forSerialization(SerializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription bbd = SerialConfigContext.getBasicBeanDescription(type);
        if (null == bbd) {
            bbd = super.forSerialization(cfg, type, r);
            ISerialConfig serialConfig = SerialConfigContext.getSerialConfig(type.getRawClass());
            if (null != serialConfig) {
                Map<String, String> aliases = serialConfig.getAliases();
                Set<String> includes = serialConfig.getIncludeProperties();
                Set<String> excludes = serialConfig.getExcludeProperties();

                List<BeanPropertyDefinition> bpds = bbd.findProperties();
                for (int i = 0; i < bpds.size(); i++) {
                    BeanPropertyDefinition bpd = bpds.get(i);
                    String name = bpd.getName();
                    if (null != excludes && excludes.contains(name)) {
                        bpds.remove(bpd);
                        i--;
                    } else if (null != aliases && aliases.containsKey(name)) {
                        bpds.remove(i);
                        bpds.add(i, bpd.withSimpleName(aliases.get(name)));
                    } else if (null != includes && !includes.isEmpty() && !includes.contains(name)) {
                        bpds.remove(bpd);
                        i--;
                    }
                }
            }
        }
        return bbd;
    }
}
