package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;

/**
 * @author ly
 * @since 1.0.0
 **/
public class FindOneByColumn extends AbstractSelectMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append(TAG_WHERE_OPEN);
        statement.append("`${c}` = #{v}");
        deleteClause(statement);
        statement.append(TAG_WHERE_CLOSE);
    }

}
