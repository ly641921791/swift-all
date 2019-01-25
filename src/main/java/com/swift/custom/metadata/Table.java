package com.swift.custom.metadata;

import com.swift.util.ClassUtils;
import com.swift.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表信息
 *
 * @author ly
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Table {

    /**
     * 表名
     */
    private String name;

    /**
     * 表格列
     */
    private List<Column> columns = new ArrayList<>();

    public void addColumn(Column column) {
        columns.add(column);
    }

    public static Table resolve(Class tableClass, Configuration configuration) {

        boolean mapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();

        Table table = new Table();

        // Java类名默认大驼峰，转换下划线格式
        table.name = StringUtils.toUnderscore(tableClass.getSimpleName());

        List<Field> fieldList = ClassUtils.getAllDeclaredFields(tableClass);

        for (Field field : fieldList) {

            if (mapUnderscoreToCamelCase) {
                table.addColumn(new Column(StringUtils.toUnderscore(field.getName()), field));
            } else {
                table.addColumn(new Column(field.getName(), field));
            }

        }

        return table;
    }

}
