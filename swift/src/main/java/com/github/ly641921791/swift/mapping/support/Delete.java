package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractDeleteMethodHandler;
import com.github.ly641921791.swift.util.StringUtils;

/**
 * Target sql script ： <script>DELETE FROM table <where>${c.where}</where></script>
 * Target sql script ： <script>UPDATE table SET column = 0 WHERE </script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class Delete extends AbstractDeleteMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            statement.append(String.format("<if test='c == null'>WHERE %s = %s</if>", table.getDeleteColumn(), table.getExistsValue()));
        }
        statement.append("<if test='c != null'>");
        statement.append(TAG_WHERE_OPEN);
        statement.append("${c.where}").append(' ');
        deleteClause(statement);
        statement.append(TAG_WHERE_CLOSE);
        statement.append("</if>");
    }

}
