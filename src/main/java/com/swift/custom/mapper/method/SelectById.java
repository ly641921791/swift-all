package com.swift.custom.mapper.method;

import com.swift.custom.mapper.AbstractSelectMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

import static com.swift.custom.metadata.Table.DEFAULT_KEY_COLUMN;
import static com.swift.custom.metadata.Table.DEFAULT_KEY_PROPERTY;

/**
 * @author ly
 * @since 2019-01-28 09:42
 **/
public class SelectById extends AbstractSelectMethodResolver {

    private static final String SELECT = "<script>SELECT %s FROM %s WHERE %s = #{%s}</script>";

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (table.isCustomized()) {
            return String.format(SELECT, columns(table), table.getName(), table.getKeyColumn(), table.getKeyProperty());
        }
        return String.format(SELECT, columns(table), table.getName(), DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY);
    }

}
