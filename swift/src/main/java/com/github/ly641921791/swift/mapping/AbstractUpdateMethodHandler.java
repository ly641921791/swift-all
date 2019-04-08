package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * <script>UPDATE table <set>%s</set></script>
 *
 * <if test="p.%s!=null">%s=#{p.%s},</if>
 *
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractUpdateMethodHandler implements MapperMethodHandler {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.UPDATE;
    }

    @Override
    public String getStatement(Table table, SwiftConfiguration configuration) {
        SqlScript sqlScript = new SqlScript();
        sqlScript.UPDATE(table.getName());
        handlerColumn(sqlScript, table, configuration);
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
        StringBuilder set = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.isExists()) {
                set.append(String.format("<if test=\"p.%s!=null\">,`%s` = #{p.%s}</if>", column.getJavaField().getName(), column.getName(), column.getJavaField().getName()));
            }
        });
        sqlScript.SET(set.substring(1));
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
