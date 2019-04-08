package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;

/**
 * Target sql script : <script>SELECT %s FROM %s </script>
 *
 *
 * <pre>
 *
 * 一、 if condition is null
 *
 *     SELECT column...
 *     FROM table
 *     WHERE del_flag
 *
 * 二、 if condition is not null
 *
 *     SELECT column...
 *     FROM table
 *     WHERE condition
 *     ORDER BY column DESC...
 *     LIMIT offset,size
 *
 * 三、 target script
 *
 *      <script>
 *          SELECT column FROM table
 *          <if test='c == null'>
 *              WHERE
 *          </if>
 *          <if test='c != null'>
 *              <where>${c.where}</where>
 *              <if test='c.orderBy != null'>ORDER BY ${c.orderBy}</if>
 *              <if test='c.limit != null'>LIMIT ${c.limit[0]},${c.limit[1]}</if>
 *          </if>
 *      </script>
 *
 * </pre>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindAll extends AbstractSelectMethodHandler {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        StringBuilder replace = new StringBuilder();
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            replace.append(String.format("<if test='c == null'>WHERE %s = %s</if>", table.getDeleteColumn(), table.getExistsValue()));
        }
        replace.append("<if test='c != null'>");
        replace.append("<where>${c.where}</where>");
        replace.append("<if test='c.orderBy != null'>ORDER BY ${c.orderBy}</if>");
        replace.append("<if test='c.limit != null'>LIMIT ${c.limit[0]},${c.limit[1]}</if>");
        replace.append("</if></script>");
        return super.buildSqlScript(table, configuration).replace("</script>", replace.toString());
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {

    }

}
