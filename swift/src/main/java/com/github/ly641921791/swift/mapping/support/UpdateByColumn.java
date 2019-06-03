package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractUpdateMethodHandler;

/**
 * @author ly
 * @since 1.0.0
 **/
public class UpdateByColumn extends AbstractUpdateMethodHandler {

    @Override
    public String getStatement() {
        StringBuilder set = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.isExists()) {
                set.append(String.format("<if test=\"p.%s!=null\">,%s = #{p.%s}</if>", column.getJavaField().getName(), column.getName(), column.getJavaField().getName()));
            }
        });
        return String.format("<script>UPDATE %s <set>%s</set> WHERE ${c} = #{v}</script>", table.getName(), set.toString());
    }

}
