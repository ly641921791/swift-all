package com.github.ly641921791.swift.mapping;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * <script>UPDATE table <set>%s</set></script>
 *
 * <if test="p.%s!=null">%s=#{p.%s},</if>
 *
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractUpdateMethodHandler extends AbstractSelectMethodHandler {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.UPDATE;
    }

    @Override
    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append(TAG_SCRIPT_OPEN);
        statement.append("UPDATE ").append(table.getName()).append(' ');
        statement.append(TAG_SET_OPEN);
        setClause(statement);
        statement.append(TAG_SET_CLOSE);
        whereClause(statement);
        statement.append(TAG_SCRIPT_CLOSE);
        return statement.toString();
    }

    protected void setClause(StringBuilder statement) {
        table.getColumns().forEach(column -> {
            if (column.isExists()) {
                statement.append(String.format("<if test=\"p.%s!=null\">,%s = #{p.%s}</if>", column.getJavaField().getName(), column.getName(), column.getJavaField().getName()));
            }
        });
    }

}
