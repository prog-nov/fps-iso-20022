package com.forms.beneform4j.core.dao.sql.function.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.function.ISqlConfigFunction;
import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL配置函数工厂（日期、时间、日期时间）<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public class DateSqlConfigFunctionFactory extends AbstractSingleonSqlConfigFunctionFactory {

    @Override
    public Set<ISqlConfigFunction> getAllSqlConfigFunctions() {
        Set<ISqlConfigFunction> set = new HashSet<ISqlConfigFunction>();
        set.add(new AbstractDateTimeSqlConfigFunction() {
            @Override
            public String getName() {
                return "date";
            }

            @Override
            protected Object getDefaultValue() {
                return CoreUtils.getDate();
            }
        });

        set.add(new AbstractDateTimeSqlConfigFunction() {
            @Override
            public String getName() {
                return "time";
            }

            @Override
            protected Object getDefaultValue() {
                return CoreUtils.getTime();
            }
        });

        set.add(new AbstractDateTimeSqlConfigFunction() {
            @Override
            public String getName() {
                return "datetime";
            }

            @Override
            protected Object getDefaultValue() {
                return CoreUtils.getDateAndTime();
            }
        });

        return set;
    }

    private abstract class AbstractDateTimeSqlConfigFunction extends AbstractValueSqlConfigFunction {

        @Override
        public Object evaluateValue(IJndi jndi, Object parameter, String expression, String[] args) {
            if (args != null && args.length >= 1 && !CoreUtils.isBlank(args[0])) {
                String format = args[0];
                int last = format.length() - 1;
                if (format.charAt(0) == '\'' && format.charAt(last) == '\'') {
                    format = format.substring(1, format.length() - 1);
                }
                return CoreUtils.getFormatDate(new Date(), format);
            } else {
                return getDefaultValue();
            }
        }

        protected abstract Object getDefaultValue();
    }
}
