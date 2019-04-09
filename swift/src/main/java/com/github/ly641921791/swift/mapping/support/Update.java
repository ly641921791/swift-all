package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractUpdateMethodHandler;

/**
 * @author ly
 * @since 1.0.0
 */
public class Update extends AbstractUpdateMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append("<where>${c.where}</where>");
    }

}
