package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * @author ly
 */
public class Select extends AbstractSelectMethodResolver {

    private static final String SELECT = "<script>SELECT %s FROM %s <where>${c.where}</where></script>";

    @Override
    protected String doBuildSqlScript(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT, columns(table), table.getName());
    }

    @Override
    protected String afterBuildSqlScript(String script, Table table, SwiftConfiguration configuration) {
        return script;
    }

}