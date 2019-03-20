package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractDeleteMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * <pre>
 *
 *     <script>
 *         DELETE FROM table WHERE column
 *     </script>
 *
 *     <script>
 *         UPDATE table SET column WHERE column AND del_column
 *     </script>
 *
 * </pre>
 *
 * @author ly
 * @since 1.0.0
 **/
public class DeleteByColumn extends AbstractDeleteMethodResolver {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("`${c}` = #{v}");
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
