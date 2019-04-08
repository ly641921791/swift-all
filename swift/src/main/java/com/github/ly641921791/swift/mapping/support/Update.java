package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractUpdateMethodHandler;
import com.github.ly641921791.swift.metadata.Column;
import com.github.ly641921791.swift.metadata.Table;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

/**
 * @author ly
 * @since 1.0.0
 */
public class Update extends AbstractUpdateMethodHandler {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set><where>${c.where}</where></script>";

    public static final String SET_SQL = "<if test=\"p.%s!=null\">%s=#{p.%s},</if>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.UPDATE;
    }

    @Override
    public String getStatement() {
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
