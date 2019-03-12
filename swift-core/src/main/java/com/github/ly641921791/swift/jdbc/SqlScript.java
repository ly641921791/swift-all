package com.github.ly641921791.swift.jdbc;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author ly
 * @since 1.0.0
 */
public class SqlScript extends org.apache.ibatis.jdbc.SQL {

    @Override
    public SQL SELECT(String column) {
        return super.SELECT(String.format("`%s`", column));
    }

    /**
     * Used to alias column. For example: SELECT `name` AS `user_name` FROM user
     *
     * @param column column
     * @param as     alias
     */
    public void SELECT_COLUMN_AS(String column, String as) {
        super.SELECT(String.format("`%s` AS `%s`", column, as));
    }

    /**
     * Used to query script. For example: SELECT (1+1) AS `count` FROM dual
     *
     * @param script script
     * @param as     as
     */
    public void SELECT_SCRIPT_AS(String script, String as) {
        super.SELECT(String.format("(%s) AS `%s`", script, as));
    }

    @Override
    public SQL FROM(String table) {
        return super.FROM(String.format("`%s`", table));
    }

    public void WHERE_EQ(String column, String value) {
        super.WHERE("`%s` = %s", column, value);
    }

    public void WHERE_NE(String column, String value) {
        super.WHERE("`%s` != %s", column, value);
    }

    @Override
    public String toString() {
        return "<script>" + super.toString() + "</script>";
    }
}
