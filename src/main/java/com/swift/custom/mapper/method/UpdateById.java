package com.swift.custom.mapper.method;

import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

/**
 * @author ly
 * @since 2019-01-28 09:55
 **/
public class UpdateById extends Update {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set> WHERE id = #{id}</script>";

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        return String.format(UPDATE, table.getName(), getSetSql(table));
    }

}
