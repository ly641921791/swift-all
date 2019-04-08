package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;

/**
 * select *
 * from table
 * join xxx on xxx
 * where xxx
 * order by xxx
 * limit xxx
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindMapList extends AbstractSelectMethodHandler {

    @Override
    public String getStatement() {
        return TAG_SCRIPT_OPEN +
                "SELECT <trim suffixOverrides=','>${c.column}</trim> " +
                "FROM ${c.from} " +
                "${c.join} " +
                "<where>${c.where}</where>" +
                "<if test='c.orderBy != null'>ORDER BY ${c.orderBy}</if>" +
                "<if test='c.limit != null'>LIMIT ${c.limit[0]},${c.limit[1]}</if>" +
                TAG_SCRIPT_CLOSE;
    }

}
