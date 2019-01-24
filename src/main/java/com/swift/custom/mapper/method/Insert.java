package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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
    public String buildSql(Method method, SwiftConfiguration configuration) {

        Class[] param = method.getParameterTypes();

        Table table;
        if ((table = configuration.getTable(param[0].getName())) == null) {
            table = Table.resolve(param[0], configuration);
            configuration.addTable(table);
        }

        List<Column> columnList = table.getColumns();

        StringBuilder cols = new StringBuilder("<trim prefix=\"\" prefixOverrides=\",\">");
        StringBuilder fs = new StringBuilder("<trim prefix=\"\" prefixOverrides=\",\">");

        for (Column column : columnList) {
            String field = column.getJavaField().getName();
            cols.append(String.format(COLUMNS, field, column.getName()));
            fs.append(String.format(VALUES, field, field));
        }

        cols.append("</trim>");
        fs.append("</trim>");

        return String.format(INSERT, table.getName(), cols.toString(), fs.toString());
    }

    @Override
    public Class<? extends Annotation> getSqlAnnotationType() {
        return org.apache.ibatis.annotations.Insert.class;
    }
}