package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.AbstractDeleteMethodResolver;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.core.util.StringUtils;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * @author ly
 * @since 2019-03-04 09:12
 **/
public class Delete extends AbstractDeleteMethodResolver {

    private static final String DELETE = "<script>DELETE FROM %s %s</script>";

    private static final String UPDATE = "<script>UPDATE %s SET %s = 0 %s</script>";

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (StringUtils.isEmpty(table.getDeleteColumn())) {
            return String.format(DELETE, table.getName(), WHERE_TAG);
        }
        return String.format(UPDATE, table.getDeleteColumn(), table.getName(), WHERE_TAG);
    }

}
