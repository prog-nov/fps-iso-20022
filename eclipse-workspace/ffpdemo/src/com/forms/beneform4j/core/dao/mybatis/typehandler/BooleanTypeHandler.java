package com.forms.beneform4j.core.dao.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 布尔类型映射器(Java为布尔类型）<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class BooleanTypeHandler extends BaseTypeHandler<Boolean> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        Object value = null;
        switch (jdbcType) {
            case SMALLINT:
            case INTEGER:
            case BIGINT:
            case FLOAT:
            case REAL:
            case DOUBLE:
            case NUMERIC:
            case DECIMAL:
                value = parameter ? 1 : 0;
                break;
            case CHAR:
            case VARCHAR:
            case LONGVARCHAR:
                value = parameter ? "1" : "0";
                break;
            default:
                value = parameter;
                break;
        }
        ps.setObject(i, value, jdbcType.TYPE_CODE);
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getBooleanValue(rs.getObject(columnName));
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getBooleanValue(rs.getObject(columnIndex));
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getBooleanValue(cs.getObject(columnIndex));
    }

    private Boolean getBooleanValue(Object value) {
        if (null == value) {
            return false;
        } else if (value instanceof String) {
            return CoreUtils.string2Boolean((String) value);
        } else if (value instanceof Number) {
            return 1 == ((Number) value).intValue();
        } else {
            return true;
        }
    }
}
