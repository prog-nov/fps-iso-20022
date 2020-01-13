package com.forms.beneform4j.core.dao.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 语句工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class StatementUtilsImpl {

    private static final StatementUtilsImpl instance = new StatementUtilsImpl() {};

    private StatementUtilsImpl() {}

    static StatementUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 设置数据库SQL执行参数
     * 
     * @param ps
     * @param args
     */
    public void setParameters(PreparedStatement ps, Object[] args) {
        setParameters(ps, args, null);
    }

    /**
     * 设置数据库SQL执行参数
     * 
     * @param ps
     * @param args
     * @param argTypes
     */
    public void setParameters(PreparedStatement ps, Object[] args, int[] argTypes) {
        try {
            if (null != args) {
                if (null != argTypes && args.length == argTypes.length) {
                    for (int i = 0, l = args.length; i < l; i++) {
                        ps.setObject(i + 1, args[i], argTypes[i]);
                    }
                } else {
                    for (int i = 0, l = args.length; i < l; i++) {
                        ps.setObject(i + 1, args[i]);
                    }
                }
            }
        } catch (SQLException e) {
            throw Throw.createRuntimeException(e);
        }
    }
}
