package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractDeleteMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script ： <script>DELETE FROM %s WHERE ${%s} = #{%s}</script>
 * Target sql script ： <script>UPDATE %s SET %s = 0 WHERE ${%s} = #{%s}</script>
 *
 * @author ly
 * @since 2019-03-01 09:42
 **/
public class DeleteByColumn extends AbstractDeleteMethodResolver {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("`${c}` = #{v}");
    }

}
