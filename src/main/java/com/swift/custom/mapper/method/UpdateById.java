package com.swift.custom.mapper.method;

import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

import static com.swift.custom.metadata.Table.*;

/**
 * @author ly
 * @since 2019-01-28 09:55
 **/
public class UpdateById extends Update {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set> WHERE %s = #{%s}</script>";

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        if (table.isCustomized()) {
            return String.format(UPDATE, table.getName(), getSetSql(table), table.getKeyColumn(), table.getKeyProperty());
        }
        return String.format(UPDATE, table.getName(), getSetSql(table), DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY);
    }

}
