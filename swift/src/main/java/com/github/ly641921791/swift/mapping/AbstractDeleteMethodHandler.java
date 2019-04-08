package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractDeleteMethodHandler implements MapperMethodHandler {

    private ThreadLocal<SqlCommandType> currentSqlCommandType = new ThreadLocal<>();

    @Override
    public SqlCommandType getSqlCommandType() {
        return currentSqlCommandType.get() == null ? SqlCommandType.DELETE : currentSqlCommandType.get();
    }

    @Override
    public String getStatement(Table table, SwiftConfiguration configuration) {
        SqlScript sqlScript = new SqlScript();

        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {

            // UPDATE table SET WHERE


            currentSqlCommandType.set(SqlCommandType.UPDATE);

            sqlScript.UPDATE(table.getName());
            sqlScript.SET(String.format("`%s` = %s", table.getDeleteColumn(), table.getDeleteValue()));
            handlerWhere(sqlScript, table, configuration);

        } else {

            // DELETE FROM table WHERE


            currentSqlCommandType.set(SqlCommandType.DELETE);


            sqlScript.DELETE_FROM(table.getName());

            handlerWhere(sqlScript, table, configuration);
        }


        return sqlScript.toString();
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

    @Override
    public void close() {
        currentSqlCommandType.remove();
    }

}
