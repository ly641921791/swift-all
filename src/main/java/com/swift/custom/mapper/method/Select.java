package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

/**
 * @author ly
 */
public class Select implements MapperMethodResolver {

    // SELECT * FROM tb WHERE  %s  ORDER BY %s

    public static final String S = "<script>SELECT * FROM %s</script>";

    public static final String SELECT = "<script>SELECT * FROM %s <where>%s</where></script>";
    public static final String WHERE = "<if test=\"%s!=null\"> AND %s=#{%s} </if>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSql(Table table, SwiftConfiguration configuration) {
        List<Column> columnList = table.getColumns();

        StringBuilder where = new StringBuilder();

        columnList.forEach(column -> {
            String field = column.getJavaField().getName();
            where.append(String.format(WHERE, field, column.getName(), field));
        });

        return String.format(SELECT, table.getName(), where.toString());
    }

}