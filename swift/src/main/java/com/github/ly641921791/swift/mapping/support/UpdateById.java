package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractUpdateMethodHandler;

/**
 * <script>UPDATE table <set>%s</set> WHERE id = id</script>
 *
 * <if test="p.%s!=null">%s=#{p.%s},</if>
 *
 * @author ly
 * @since 1.0.0
 **/
public class UpdateById extends AbstractUpdateMethodHandler {

    @Override
    public String getStatement() {

        StringBuilder set = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.isExists()) {
                set.append(String.format("<if test=\"p.%s!=null\">,%s = #{p.%s}</if>", column.getJavaField().getName(), column.getName(), column.getJavaField().getName()));
            }
        });

        return String.format("<script>UPDATE %s <set>%s</set> WHERE id = #{id}</script>", table.getName(), set.toString());
    }

}
