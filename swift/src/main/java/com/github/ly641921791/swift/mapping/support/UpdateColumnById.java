package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractUpdateMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script <script>UPDATE table SET `${c}` = #{v} WHERE `id` = #{id}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class UpdateColumnById extends AbstractUpdateMethodHandler {

    @Override
    protected void handlerColumn(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.SET_COLUMN("${c}", "#{v}");
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE_EQ("id", "#{id}");
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
