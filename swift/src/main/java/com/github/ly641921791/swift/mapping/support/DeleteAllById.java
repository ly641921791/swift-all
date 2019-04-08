package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractDeleteMethodHandler;

/**
 * Target sql script ： <script>DELETE FROM table WHERE id IN (#{id})</script>
 * Target sql script ： <script>UPDATE table SET column = 1 WHERE id IN (#{id})</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class DeleteAllById extends AbstractDeleteMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append("<where>id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></where>");
    }

}
