package com.swift.executor.method;

import com.swift.cache.CacheManager;
import com.swift.metadata.Column;
import com.swift.metadata.Table;
import com.swift.session.MethodHandler;
import org.apache.ibatis.session.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class Update implements MethodHandler {

    public static final String UPDATE = "<script>UPDATE %s <set>%s</set><where>%s</where></script>";

    public static final String SET = "<if test=\"r.%s!=null\">%s=#{r.%s},</if>";

    public static final String WHERE = "<if test=\"c.%s!=null\">AND %s=#{c.%s}</if>";

    @Override
    public String buildSql(Method method, Configuration configuration) {

        Class[] param = method.getParameterTypes();

        Table table;
        if ((table = CacheManager.tableCache.get(param[0])) == null) {
            table = Table.resolve(param[0], configuration);
            CacheManager.tableCache.put(param[0], table);
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
