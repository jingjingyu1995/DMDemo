package com.gin.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

/**
 * 动态结果处理器
 * @author hefeiyu 2021-11-15 16:30
 */
public class DynamicResultResultHandler extends BaseTypeHandler<DynamicResult> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DynamicResult parameter, JdbcType jdbcType) {
        //parameter.put(ps.);
        throw new RuntimeException("不支持的注入");

    }

    @Override
    public DynamicResult getNullableResult(ResultSet rs, String columnName) throws SQLException {

        return getDynamicResult(rs);
    }

    private DynamicResult getDynamicResult(ResultSet rs) throws SQLException {
        boolean wasNull = true;
        int notNullIndex = -1;
        DynamicResult result = new DynamicResult();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            Object object = rs.getObject(i);
            String columnLabel = metaData.getColumnLabel(i);
            if (object != null) {
                wasNull = false;
                notNullIndex = i;
            }
            result.put(columnLabel, object);
        }
        if (!wasNull) {
            rs.getObject(notNullIndex);
        }
        return result;
    }

    @Override
    public DynamicResult getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getDynamicResult(rs);
    }

    @Override
    public DynamicResult getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

        boolean wasNull = true;
        int notNullIndex = -1;
        DynamicResult result = new DynamicResult();
        ResultSetMetaData metaData = cs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            Object object = cs.getObject(i);
            String columnLabel = metaData.getColumnLabel(i);
            if (object != null) {
                wasNull = false;
                notNullIndex = i;
            }
            result.put(columnLabel, object);
        }
        if (!wasNull) {
            cs.getObject(notNullIndex);
        }
        return result;
    }
}
