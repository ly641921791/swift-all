package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;

/**
 * @author ly
 * @since 1.0.0
 **/
public class Count extends AbstractSelectMethodHandler {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        StringBuilder replace = new StringBuilder();
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            replace.append(String.format("<if test='c == null'>WHERE %s = %s</if>", table.getDeleteColumn(), table.getExistsValue()));
        }
        replace.append("<if test='c != null'>");
        replace.append("<where>${c.where}</where>");
        replace.append("</if></script>");
        return super.buildSqlScript(table, configuration).replace("</script>", replace.toString());
    }

    @Override
    protected void handlerColumn(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        sqlScript.SELECT("COUNT(0)");
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
    }

}
