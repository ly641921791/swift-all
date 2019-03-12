package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script : <script>SELECT %s FROM %s </script>
 *
 * @author ly
 * @since 2019-03-11 13:55
 **/
public class FindAll extends AbstractSelectMethodResolver {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
