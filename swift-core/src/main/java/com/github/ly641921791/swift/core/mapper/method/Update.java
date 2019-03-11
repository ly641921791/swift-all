package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.MapperMethodResolver;
import com.github.ly641921791.swift.core.metadata.Column;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
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
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        return String.format(UPDATE, table.getName(), getSetSql(table));
    }

    protected String getSetSql(Table table) {
        List<Column> columnList = table.getColumns();
        StringBuilder setSql = new StringBuilder();

        columnList.forEach(column -> {
            // 不存在的列跳过
            if (!column.isExists()) {
                return;
            }
            String field = column.getJavaField().getName();
            setSql.append(String.format(SET_SQL, field, column.getName(), field));
        });

        return setSql.toString();
    }
}
