package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractDeleteMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * Target sql script ： <script>DELETE FROM table <where>${c.where}</where></script>
 * Target sql script ： <script>UPDATE table SET column = 0 WHERE </script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class Delete extends AbstractDeleteMethodHandler {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (SqlCommandType.UPDATE.equals(getSqlCommandType())) {
            return super.buildSqlScript(table, configuration);
        }
        return super.buildSqlScript(table, configuration).replace("</script>", " <where>${c.where}</where></script>");
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        if (SqlCommandType.UPDATE.equals(getSqlCommandType())) {
            return;
        }
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
