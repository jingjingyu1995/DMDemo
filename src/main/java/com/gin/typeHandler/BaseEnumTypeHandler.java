package com.gin.typeHandler;

import com.gin.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 注解处理类 mybatis TypeHandler
 * @param <E>
 * @author hefeiyu
 */

public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum<Integer, E>> extends BaseTypeHandler<E> {

    private Class<E> aClass;

    private E[] enums;


    public BaseEnumTypeHandler(Class<E> aClass) {
        this.aClass = aClass;
        if (!aClass.equals(BaseEnum.class) ) {
            this.enums = aClass.getEnumConstants();
        }


    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {

        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return handlerResult(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return handlerResult(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return handlerResult(i);
        }
    }

    private E handlerResult(int i) {
            for (E anEnum : enums) {
                if (anEnum.getValue() == i) {
                    return anEnum;
                }
            }
            throw new IllegalArgumentException("BaseEnumTypeHandler 不能解析 " + i + " 到 " + aClass.getSimpleName());
    }
}
