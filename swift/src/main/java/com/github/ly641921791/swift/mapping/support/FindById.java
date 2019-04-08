package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

import static com.github.ly641921791.swift.metadata.Table.DEFAULT_KEY_COLUMN;
import static com.github.ly641921791.swift.metadata.Table.DEFAULT_KEY_PROPERTY;

/**
 * Target sql script : <script>SELECT %s FROM %s WHERE %s = #{%s}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindById extends AbstractSelectMethodHandler {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        if (table.getTableClassAnnotation() == null) {
            sqlScript.WHERE(String.format("%s = #{%s}", DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY));
        } else {
            sqlScript.WHERE(String.format("%s = #{%s}", table.getTableClassAnnotation().keyColumn(), table.getTableClassAnnotation().keyProperty()));
        }
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}