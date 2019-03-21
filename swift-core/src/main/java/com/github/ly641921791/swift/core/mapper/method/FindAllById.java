package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.core.util.StringUtils;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script : <script>SELECT column FROM table WHERE id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAllById extends AbstractSelectMethodResolver {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (StringUtils.isNotEmpty(table.getDeleteColumn())) {
            return super.buildSqlScript(table, configuration).replace("</script>", "AND " + table.getDeleteColumn() + " = " + table.getExistsValue() + "</script>");
        }
        return super.buildSqlScript(table, configuration);
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>");
    }

}
