package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * <pre>
 *
 *     SELECT column FROM table WHERE column IN (value)
 *
 *     <script>
 *         SELECT column FROM table WHERE
 *         column IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>
 *     </script>
 *
 * </pre>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAllByColumn extends AbstractSelectMethodResolver {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("`${c}` IN <foreach item='item' index='index' collection='vs' open='(' separator=',' close=')'>#{item}</foreach>");
        handlerDeleteColumn(sqlScript, table, configuration);
    }

}
