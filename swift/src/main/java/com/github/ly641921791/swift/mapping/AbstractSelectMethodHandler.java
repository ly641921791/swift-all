package com.github.ly641921791.swift.mapping;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 查询类型方法解析器
 *
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractSelectMethodHandler extends AbstractMapperMethodHandler {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append(TAG_SCRIPT_OPEN);
        selectClause(statement);
        statement.append("FROM ").append(table.getName()).append(' ');
        whereClause(statement);
        statement.append(TAG_SCRIPT_CLOSE);
        return statement.toString();
    }

    @Override
    protected void selectClause(StringBuilder statement) {
        statement.append("<trim prefix='SELECT' suffixOverrides=','>");
        table.getColumns().forEach(column -> {
            if (column.getSelectValue().isEmpty()) {
                if (column.isExists()) {
                    statement.append(column.getName()).append(" AS ").append(column.getJavaField().getName()).append(",");
                }
            } else {
                statement.append('(').append(column.getSelectValue()).append(") AS ").append(column.getName()).append(",");
            }
        });
        statement.append("</trim>");
    }

}
