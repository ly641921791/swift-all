package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author ly
 * @since 2019-01-28 09:42
 **/
public class SelectById implements MapperMethodResolver {

    public static final String SELECT = "<script>SELECT %s FROM %s WHERE id = #{id}</script>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        return String.format(SELECT, Select.columns(table), table.getName());
    }

}
