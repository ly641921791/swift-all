package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;

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
public class FindAllByColumn extends AbstractSelectMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append(TAG_WHERE_OPEN);
        statement.append("${c} IN <foreach item='item' index='index' collection='vs' open='(' separator=',' close=')'>#{item}</foreach>");
        deleteClause(statement);
        statement.append(TAG_WHERE_CLOSE);
    }

}
