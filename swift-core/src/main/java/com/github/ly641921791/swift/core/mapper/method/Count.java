package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * @author ly
 * @since 1.0.0
 **/
public class Count extends AbstractSelectMethodResolver {

    @Override
    protected void handlerColumn(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.SELECT("COUNT(0)");
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
