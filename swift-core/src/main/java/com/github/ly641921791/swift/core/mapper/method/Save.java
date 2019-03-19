package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.mapper.MapperMethodResolver;
import com.github.ly641921791.swift.core.metadata.Column;
import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

/**
 * TODO INSERT INTO foo ( del ) VALUES ( ? )   del加引号
 *
 * @author ly
 * @since 1.0.0
 **/
public class Save implements MapperMethodResolver {

    public static final String INSERT = "<script>INSERT INTO %s (%s) VALUES (%s)</script>";

    // TODO IGNORE 仅支持mysql数据库
    public static final String INSERT_MYSQL = "<script>INSERT <if test='ignore'>IGNORE</if> INTO %s (%s) VALUES (%s)</script>";

    public static final String COLUMNS = "<if test=\"entity.%s!=null\">,%s</if>";

    public static final String VALUES = "<if test=\"entity.%s!=null\">,#{entity.%s}</if>";

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