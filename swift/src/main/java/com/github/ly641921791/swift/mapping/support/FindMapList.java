package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * select *
 * from table
 * join xxx on xxx
 * where xxx
 * order by xxx
 * limit xxx
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindMapList extends AbstractSelectMethodHandler {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        return TAG_SCRIPT_OPEN +
                "SELECT <trim suffixOverrides=','>${c.column}</trim> " +
                "FROM ${c.from} " +
                "${c.join} " +
                "<where>${c.where}</where>" +
                "<if test='c.orderBy != null'>ORDER BY ${c.orderBy}</if>" +
                "<if test='c.limit != null'>LIMIT ${c.limit[0]},${c.limit[1]}</if>" +
                TAG_SCRIPT_CLOSE;
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {

    }

}
