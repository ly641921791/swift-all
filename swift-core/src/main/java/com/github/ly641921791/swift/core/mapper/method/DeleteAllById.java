package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractDeleteMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * Target sql script ： <script>DELETE FROM table WHERE id IN (#{id})</script>
 * Target sql script ： <script>UPDATE table SET column = 1 WHERE id IN (#{id})</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class DeleteAllById extends AbstractDeleteMethodResolver {

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.WHERE("id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>");
    }

}
