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
        return JSON.parseObject(rs.getString(columnName), StringArray.class);
    }

    @Override
    public StringArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JSON.parseObject(rs.getString(columnIndex), StringArray.class);
    }

    @Override
    public StringArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JSON.parseObject(cs.getString(columnIndex), StringArray.class);
    }

}
