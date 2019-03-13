package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script ：<script>SELECT %s FROM %s WHERE ${c} = #{v}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAllByColumn extends AbstractSelectMethodResolver {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("`${c}` = #{v}");
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
