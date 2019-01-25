package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

public class Update implements MapperMethodResolver {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set><where>%s</where></script>";

    public static final String SET = "<if test=\"r.%s!=null\">%s=#{r.%s},</if>";

    public static final String WHERE = "<if test=\"c.%s!=null\">AND %s=#{c.%s}</if>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.UPDATE;
    }

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {

        List<Column> columnList = table.getColumns();

        StringBuilder set = new StringBuilder();

        StringBuilder where = new StringBuilder();

        for (Column column : columnList) {
            String field = column.getJavaField().getName();
            set.append(String.format(SET, field, column.getName(), field));
            where.append(String.format(WHERE, field, column.getName(), field));
        }

        return String.format(UPDATE, table, set.toString(), where.toString());
    }

}
