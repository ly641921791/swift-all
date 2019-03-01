package com.swift.custom.mapper.method;

import com.swift.custom.mapper.AbstractDeleteMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.springframework.util.StringUtils;

/**
 * @author ly
 * @since 2019-03-01 09:29
 **/
public class DeleteById extends AbstractDeleteMethodResolver {

    private static final String DELETE_BY_ID = "<script>DELETE FROM %s WHERE %s = #{%s}</script>";

    private static final String UPDATE_BY_ID = "<script>UPDATE %s SET %s = 0 WHERE %s = #{%s}</script>";

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (StringUtils.isEmpty(table.getDeleteColumn())) {
            return String.format(DELETE_BY_ID, table.getName(), Table.DEFAULT_KEY_COLUMN, Table.DEFAULT_KEY_COLUMN);
        }
        return String.format(UPDATE_BY_ID, table.getName(), table.getDeleteColumn(), Table.DEFAULT_KEY_COLUMN, Table.DEFAULT_KEY_COLUMN);
    }

}
