package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractDeleteMethodHandler;

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
public class DeleteByColumn extends AbstractDeleteMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append(TAG_WHERE_OPEN);
        statement.append("`${c}` = #{v}");
        deleteClause(statement);
        statement.append(TAG_WHERE_CLOSE);
    }

}
