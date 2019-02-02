package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author ly
 */
public class Select implements MapperMethodResolver {

    public static final String SELECT = "<script>SELECT %s FROM %s <where>${c.where}</where></script>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT, columns(table), table.getName());
    }

    static String columns(Table table) {
        StringBuilder sql = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.getSelectValue().isEmpty()) {
                sql.append(",`").append(column.getName()).append("`");
            } else {
                sql.append(",(").append(column.getSelectValue()).append(") AS `").append(column.getName()).append("`");
            }
        });
        return sql.substring(1);
    }

}