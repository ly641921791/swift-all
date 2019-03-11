package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

import static com.github.ly641921791.swift.core.metadata.Table.DEFAULT_KEY_COLUMN;
import static com.github.ly641921791.swift.core.metadata.Table.DEFAULT_KEY_PROPERTY;

/**
 * @author ly
 * @since 2019-01-28 09:55
 **/
public class UpdateById extends Update {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set> WHERE %s = #{%s}</script>";

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (table.isCustomized()) {
            return String.format(UPDATE, table.getName(), getSetSql(table), table.getKeyColumn(), table.getKeyProperty());
        }
        return String.format(UPDATE, table.getName(), getSetSql(table), DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY);
    }

}
