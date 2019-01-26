package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

/**
 * @author ly
 */
public class Update implements MapperMethodResolver {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set><where>${c.where}</where></script>";

    public static final String SET_SQL = "<if test=\"p.%s!=null\">%s=#{p.%s},</if>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.UPDATE;
    }

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        List<Column> columnList = table.getColumns();
        StringBuilder setSql = new StringBuilder();
        for (Column column : columnList) {
            String field = column.getJavaField().getName();
            setSql.append(String.format(SET_SQL, field, column.getName(), field));
        }
        return String.format(UPDATE, table.getName(), setSql.toString());
    }

}
