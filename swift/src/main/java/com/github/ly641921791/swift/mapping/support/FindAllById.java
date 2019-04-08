package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;

/**
 * Target sql script : <script>SELECT column FROM table WHERE id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAllById extends AbstractSelectMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append("<where>id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></where>");
        deleteClause(statement);
    }

}
