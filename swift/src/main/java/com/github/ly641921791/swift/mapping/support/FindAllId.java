package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.util.StringUtils;

/**
 * Target sql script : <script>SELECT %s FROM %s WHERE %s = #{%s}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAllId extends AbstractSelectMethodHandler {

    @Override
    protected void selectClause(StringBuilder statement) {
        statement.append("SELECT ").append(table.getKeyColumn()).append(" AS ").append(table.getKeyProperty());
    }

    @Override
    protected void whereClause(StringBuilder statement) {
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            statement.append(String.format("<if test='c == null'>WHERE %s = %s</if>", table.getDeleteColumn(), table.getExistsValue()));
        }
        statement.append("<if test='c != null'>");
        statement.append("<where>${c.where}</where>");
        statement.append("</if>");
    }

}
