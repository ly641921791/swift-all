package com.swift.custom.mapper.method;

import com.swift.custom.mapper.AbstractSelectMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

/**
 * @author ly
 */
public class Select extends AbstractSelectMethodResolver {

    private static final String SELECT = "<script>SELECT %s FROM %s <where>${c.where}</where></script>";

    @Override
    protected String doBuildSqlScript(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT, columns(table), table.getName());
    }

}