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
 */
public class Find implements MethodHandler {

    public static final String FIND = "<script>SELECT * FROM %s <where>%s</where></script>";
    public static final String WHERE = "<if test=\"%s!=null\"> AND %s=#{%s} </if>";

    @Override
    public String buildSql(Method method, Configuration configuration) {

        Class[] param = method.getParameterTypes();

        Table table;
        if ((table = CacheManager.tableCache.get(param[0])) == null) {
            table = Table.resolve(param[0], configuration);
            CacheManager.tableCache.put(param[0], table);
        }

        List<Column> columnList = table.getColumns();

        StringBuilder where = new StringBuilder();

        for (Column column : columnList) {
            String field = column.getJavaField().getName();
            where.append(String.format(WHERE, field, column.getName(), field));
        }

        return String.format(FIND, table.getName(), where.toString());
    }

    @Override
    public Class<? extends Annotation> getSqlAnnotationType() {
        return Select.class;
    }


}