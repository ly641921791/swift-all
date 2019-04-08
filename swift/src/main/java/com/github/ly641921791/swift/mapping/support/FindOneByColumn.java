package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * @author ly
 * @since 1.0.0
 **/
public class FindOneByColumn extends AbstractSelectMethodHandler {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("`${c}` = #{v}");
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
