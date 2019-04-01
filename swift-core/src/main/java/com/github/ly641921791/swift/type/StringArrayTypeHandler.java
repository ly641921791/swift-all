package com.github.ly641921791.swift.type;

import com.alibaba.fastjson.JSON;
import com.github.ly641921791.swift.core.lang.StringArray;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ly
 * @since 1.0.0
 **/
public class StringArrayTypeHandler extends BaseTypeHandler<StringArray> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StringArray parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public StringArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return string == null ? new StringArray() : JSON.parseObject(string, StringArray.class);
    }

    @Override
    public StringArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        return string == null ? new StringArray() : JSON.parseObject(string, StringArray.class);
    }

    @Override
    public StringArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return string == null ? new StringArray() : JSON.parseObject(string, StringArray.class);
    }

}
