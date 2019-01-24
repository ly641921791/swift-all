package com.swift.custom.mapper.method;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.metadata.Column;
import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.annotations.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ly
 */
public class Find implements MapperMethodResolver {

    public static final String FIND = "<script>SELECT * FROM %s <where>%s</where></script>";
    public static final String WHERE = "<if test=\"%s!=null\"> AND %s=#{%s} </if>";

    @Override
    public String buildSql(Method method, SwiftConfiguration configuration) {

        Class[] param = method.getParameterTypes();

        Table table;
        if ((table = configuration.getTable(param[0].getName())) == null) {
            table = Table.resolve(param[0], configuration);
            configuration.addTable(table);
        }


        List<Column> columnList = table.getColumns();

        StringBuilder where = new StringBuilder();

        columnList.forEach(column -> {
            String field = column.getJavaField().getName();
            where.append(String.format(WHERE, field, column.getName(), field));
        });

        return String.format(FIND, table.getName(), where.toString());
    }

    @Override
    public Class<? extends Annotation> getSqlAnnotationType() {
        return Select.class;
    }

}