package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractUpdateMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.jdbc.SqlScript;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * <script>UPDATE table <set>%s</set> WHERE id = id</script>
 *
 * <if test="p.%s!=null">%s=#{p.%s},</if>
 *
 * @author ly
 * @since 2019-01-28 09:55
 **/
public class UpdateById extends AbstractUpdateMethodResolver {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {

        StringBuilder set = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.isExists()) {
                set.append(String.format(",<if test=\"p.%s!=null\">`%s` = #{p.%s}</if>", column.getJavaField().getName(), column.getName(), column.getJavaField().getName()));
            }
        });

        return String.format("<script>UPDATE `%s` <set>%s</set> WHERE id = #{id}</script>",table.getName(), set.substring(1));
    }

    @Override
    protected void handlerWhere(SqlScript sqlScript, Table table, SwiftConfiguration configuration) {
    }

}
