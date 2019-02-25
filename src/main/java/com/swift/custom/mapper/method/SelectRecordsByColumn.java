package com.swift.custom.mapper.method;

import com.swift.custom.mapper.AbstractSelectMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

/**
 * @author ly
 * @since 2019-02-25 13:58
 **/
public class SelectRecordsByColumn extends AbstractSelectMethodResolver {

    private static final String SELECT = "<script>SELECT %s FROM %s WHERE ${c} = #{v}</script>";

    @Override
    public String doBuildSqlScript(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT, columns(table), table.getName());
    }

}
