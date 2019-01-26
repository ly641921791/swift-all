package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author ly
 */
public class Select implements MapperMethodResolver {

    public static final String SELECT = "<script>SELECT * FROM %s <where>${c.where}</where></script>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT,table.getName());
    }

}