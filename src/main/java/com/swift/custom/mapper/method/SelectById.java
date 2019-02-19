package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

import static com.swift.custom.metadata.Table.*;

/**
 * @author ly
 * @since 2019-01-28 09:42
 **/
public class SelectById implements MapperMethodResolver {

    public static final String SELECT = "<script>SELECT %s FROM %s WHERE %s = #{%s}</script>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        if (table.isCustomized()) {
            return String.format(SELECT, Select.columns(table), table.getName(), table.getKeyColumn(), table.getKeyProperty());
        }
        return String.format(SELECT, Select.columns(table), table.getName(), DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY);
    }

}
