package com.forms.beneform4j.core.dao.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 结果集工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class ResultSetUtilsImpl {

    private static final ResultSetUtilsImpl instance = new ResultSetUtilsImpl() {};

    private ResultSetUtilsImpl() {}

    static ResultSetUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 将结果集转换为List<Map<String, Object>>
     * 
     * @param rs 结果集对象
     * @return List<Map<String, Object>>对象
     */
    public List<Map<String, Object>> handlerResultSet(ResultSet rs) {
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
        return list;
    }

    /**
     * 获取结果集的字段信息
     * 
     * @param rs 结果集对象
     * @return 字段信息的二维数组，如[["CODE","NAME","FIELD_NAME"],["code","name",
     *         "fieldName"]]，其中第一个数组为SQL中字段，第二个数组为相应的驼峰式属性名
     */
    public String[][] getFields(ResultSet rs) {
        String[][] fields = null;
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int length = meta.getColumnCount();
            fields = new String[2][length];
            for (int i = 1; i <= length; i++) {
                fields[0][i - 1] = meta.getColumnLabel(i);
                fields[1][i - 1] = CoreUtils.convertToCamel(fields[0][i - 1]);
            }
        } catch (SQLException e) {
            Throw.throwRuntimeException(e);
        }
        return fields;
    }

}
