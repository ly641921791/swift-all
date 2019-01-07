package com.swift.executor.method;

import com.swift.cache.CacheManager;
import com.swift.metadata.Column;
import com.swift.metadata.Table;
import com.swift.session.MethodHandler;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ly
 * @since 2019-01-07 18:54
 **/
public class Insert implements MethodHandler {

    public static final String INSERT = "<script>INSERT INTO %s (%s) VALUES (%s)</script>";

    public static final String COLUMNS = "<if test=\"%s!=null\">,%s</if>";

    public static final String VALUES = "<if test=\"%s!=null\">,#{%s}</if>";

    @Override
    public String buildSql(Method method, Configuration configuration) {

        Class[] param = method.getParameterTypes();

        Table table;
        if ((table = CacheManager.tableCache.get(param[0])) == null) {
            table = Table.resolve(param[0], configuration);
            CacheManager.tableCache.put(param[0], table);
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
        return Select.class;
    }
}
