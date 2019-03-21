package com.github.ly641921791.swift.core.mapper;

import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.core.util.StringUtils;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 查询类型方法解析器
 *
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractSelectMethodResolver implements MapperMethodResolver {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        SqlScript sqlScript = new SqlScript();
        handlerColumn(sqlScript, table, configuration);
        sqlScript.FROM(table.getName());
        handlerWhere(sqlScript, table, configuration);
        return sqlScript.toString();
    }

    /**
     * handler column
     *
     * @param sqlScript     sqlScript
     * @param table         table
     * @param configuration configuration
     */
    protected void handlerColumn(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        table.getColumns().forEach(column -> {
            if (column.getSelectValue().isEmpty()) {
                if (column.isExists()){
                    sqlScript.SELECT_COLUMN_AS(column.getName(),column.getJavaField().getName());
                }
            }else {
                sqlScript.SELECT_SCRIPT_AS(column.getSelectValue(), column.getName());
            }
        });
    }

    /**
     * handler where condition
     *
     * @param sqlScript     sqlScript
     * @param table         table
     * @param configuration configuration
     */
    protected abstract void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration);

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
