package com.swift.custom.mapper.method;

import com.swift.custom.mapper.AbstractDeleteMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.springframework.util.StringUtils;

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
