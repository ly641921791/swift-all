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
    protected void whereClause(StringBuilder statement) {
        super.whereClause(statement);
    }

    @Override
    public String getStatement() {
        StringBuilder replace = new StringBuilder();
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            replace.append(String.format("<if test='c == null'>WHERE %s = %s</if>", table.getDeleteColumn(), table.getExistsValue()));
        }
        replace.append("<if test='c != null'>");
        replace.append("<where>${c.where}</where>");
        replace.append("</if></script>");
        return super.getStatement().replace("</script>", replace.toString());
    }

}
