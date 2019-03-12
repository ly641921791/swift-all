package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script : <script>SELECT column FROM table <where>${c.where}</where></script>
 *
 * @author ly
 * @since 1.0.0
 */
public class Select extends AbstractSelectMethodResolver {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        return super.buildSqlScript(table, configuration).replace("</script>", " <where>${c.where}</where></script>");
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
    }

}