package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;

import static com.github.ly641921791.swift.metadata.Table.DEFAULT_KEY_COLUMN;

/**
 * Target sql script : <script>SELECT %s FROM %s WHERE %s = #{%s}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAllId extends AbstractSelectMethodHandler {

    @Override
    public String getStatement(Table table, SwiftConfiguration configuration) {
        StringBuilder replace = new StringBuilder();
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            replace.append(String.format("<if test='c == null'>WHERE %s = %s</if>", table.getDeleteColumn(), table.getExistsValue()));
        }
        replace.append("<if test='c != null'>");
        replace.append("<where>${c.where}</where>");
        replace.append("</if></script>");
        return super.getStatement(table, configuration).replace("</script>", replace.toString());
    }

    @Override
    protected void handlerColumn(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
        if (table.getTableClassAnnotation() == null) {
            sqlScript.SELECT(DEFAULT_KEY_COLUMN);
        } else {
            sqlScript.SELECT_COLUMN_AS(table.getTableClassAnnotation().keyColumn(), table.getTableClassAnnotation().keyProperty());
        }
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {

    }

}
