package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class Update implements MapperMethodResolver {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set><where>%s</where></script>";

    public static final String SET = "<if test=\"r.%s!=null\">%s=#{r.%s},</if>";

    public static final String WHERE = "<if test=\"c.%s!=null\">AND %s=#{c.%s}</if>";

    @Override
    public String buildSql(Method method, SwiftConfiguration configuration) {

        Class[] param = method.getParameterTypes();

        Table table;
        if ((table = configuration.getTable(param[0].getName())) == null) {
            table = Table.resolve(param[0], configuration);
            configuration.addTable(table);
        }


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

    @Override
    public Class<? extends Annotation> getSqlAnnotationType() {
        return org.apache.ibatis.annotations.Update.class;
    }
}
