package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;
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
    public String getStatement(Table table, SwiftConfiguration configuration) {
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
        table.getColumns().forEach(column -> {
            if (column.getSelectValue().isEmpty()) {
                if (column.isExists()) {
                    statement.append(column.getName()).append(" AS ").append(column.getJavaField().getName()).append(",");
                }
            } else {
                statement.append(column.getSelectValue()).append(" AS ").append(column.getName()).append(",");
            }
        });
    }

    /**
     * handler delete column
     *
     * @param sqlScript     sqlScript
     * @param table         table
     * @param configuration configuration
     */
    protected void handlerDeleteColumn(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            sqlScript.WHERE_EQ(table.getDeleteColumn(), table.getExistsValue());
        }
    }

}
