package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractSelectMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

import static com.github.ly641921791.swift.core.metadata.Table.DEFAULT_KEY_COLUMN;
import static com.github.ly641921791.swift.core.metadata.Table.DEFAULT_KEY_PROPERTY;

/**
 * @author ly
 * @since 2019-01-28 09:42
 **/
public class SelectById extends AbstractSelectMethodResolver {

    private static final String SELECT = "<script>SELECT %s FROM %s WHERE %s = #{%s}</script>";

    @Override
    public String doBuildSqlScript(Table table, SwiftConfiguration configuration) {
        if (table.isCustomized()) {
            return String.format(SELECT, columns(table), table.getName(), table.getKeyColumn(), table.getKeyProperty());
        }
        return String.format(SELECT, columns(table), table.getName(), DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY);
    }

}
