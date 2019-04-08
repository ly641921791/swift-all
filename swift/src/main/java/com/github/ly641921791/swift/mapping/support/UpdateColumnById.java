package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractUpdateMethodHandler;

/**
 * Target sql script <script>UPDATE table SET `${c}` = #{v} WHERE `id` = #{id}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class UpdateColumnById extends AbstractUpdateMethodHandler {

    @Override
    protected void setClause(StringBuilder statement) {
        statement.append("${c} = #{v}");
    }

}
