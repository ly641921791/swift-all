package com.swift.custom.mapper.method;

import com.swift.custom.mapper.AbstractSelectMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.springframework.util.StringUtils;

/**
 * @author ly
 * @since 2019-03-11 16:35
 **/
public class FindAllById extends AbstractSelectMethodResolver {

    private static final String SELECT = "<script>SELECT %s FROM %s WHERE id IN <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></script>";

    @Override
    protected String doBuildSqlScript(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT, columns(table), table.getName());
    }

    @Override
    protected String afterBuildSqlScript(String script, Table table, SwiftConfiguration configuration) {
        if (StringUtils.isEmpty(table.getDeleteColumn())) {
            return script;
        }
        return script.replace("</script>", " AND " + table.getDeleteColumn() + " = 1</script>");
    }

}
