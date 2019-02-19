package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

/**
 * @author ly
 * @since 2019-01-07 18:54
 **/
public class Insert implements MapperMethodResolver {

    public static final String INSERT = "<script>INSERT INTO %s (%s) VALUES (%s)</script>";

    public static final String COLUMNS = "<if test=\"%s!=null\">,%s</if>";

    public static final String VALUES = "<if test=\"%s!=null\">,#{%s}</if>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.INSERT;
    }

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {

        List<Column> columnList = table.getColumns();

        StringBuilder cols = new StringBuilder("<trim prefix=\"\" prefixOverrides=\",\">");
        StringBuilder fs = new StringBuilder("<trim prefix=\"\" prefixOverrides=\",\">");

        columnList.forEach(column -> {
            // 不存在的列跳过
            if (!column.isExists()) {
                return;
            }
            String field = column.getJavaField().getName();
            cols.append(String.format(COLUMNS, field, column.getName()));
            fs.append(String.format(VALUES, field, field));
        });

        cols.append("</trim>");
        fs.append("</trim>");

        return String.format(INSERT, table.getName(), cols.toString(), fs.toString());
    }

    @Override
    public KeyGenerator getKeyGenerator(Table table) {
        return table.isUseGeneratedKeys() ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
    }

    @Override
    public String getKeyColumn(Table table) {
        return table.getKeyColumn();
    }

    @Override
    public String getKeyProperty(Table table) {
        return table.getKeyProperty();
    }
}