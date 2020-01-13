package com.forms.beneform4j.core.dao.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 游标类型映射器(Java为List<Map<String, Object>>，数据库为ResultSet类型）<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class CursorTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter, jdbcType.TYPE_CODE);
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object v = rs.getObject(columnName);
        if (v instanceof ResultSet) {
            return handlerResultSet((ResultSet) v, -1, columnName);
        } else {
            return v;
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object v = rs.getObject(columnIndex);
        if (v instanceof ResultSet) {
            return handlerResultSet((ResultSet) v, columnIndex, null);
        } else {
            return v;
        }
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object v = cs.getObject(columnIndex);
        if (v instanceof ResultSet) {
            return handlerResultSet((ResultSet) v, columnIndex, null);
        } else {
            return v;
        }
    }

    private List<Map<String, Object>> handlerResultSet(ResultSet rs, int columnIndex, String colunmName) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            if (null != rs) {
                String[][] fields = getFields(rs);
                int length = fields[0].length;
                while (rs.next()) {
                    Map<String, Object> map = new LinkedHashMap<String, Object>();
                    for (int i = 0; i < length; i++) {
                        Object value = rs.getObject(fields[0][i]);
                        map.put(fields[1][i], rs.wasNull() ? null : value);
                    }
                    list.add(map);
                }
            }
        } catch (SQLException e) {
            Throw.throwRuntimeException(e);
        }
        CommonLogger.debug("<==      Total: " + list.size());
        return list;
    }

    private static String[][] getFields(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int length = meta.getColumnCount();
        String[][] fields = new String[2][length];
        for (int i = 1; i <= length; i++) {
            fields[0][i - 1] = meta.getColumnLabel(i);
            fields[1][i - 1] = CoreUtils.convertToCamel(fields[0][i - 1]);
        }
        return fields;
    }
}
