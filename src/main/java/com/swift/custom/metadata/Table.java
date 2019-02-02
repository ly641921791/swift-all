package com.swift.custom.metadata;

import com.swift.custom.annotation.ColumnField;
import com.swift.custom.annotation.TableClass;
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

    public static final boolean DEFAULT_USE_GENERATED_KEYS = false;

    public static final String DEFAULT_KEY_PROPERTY = "id";

    public static final String DEFAULT_KEY_COLUMN = "id";

    /**
     * 表名
     */
    private String name;

    /**
     * 表格列
     */
    private List<Column> columns = new ArrayList<>();

    /**
     * 是否通过 TableClass 注解定制过
     */
    private boolean customized = true;

    private boolean useGeneratedKeys;

    private String keyProperty;

    private String keyColumn;

    public void addColumn(Column column) {
        columns.add(column);
    }

    public static Table resolve(Class<?> tableClass, Configuration configuration) {
        Table table = new Table();

        // Java类名一般是大驼峰，转换下划线格式
        table.name = StringUtils.toUnderscore(tableClass.getSimpleName());

        // 注解解析
        TableClass tableClassAnnotation = tableClass.getAnnotation(TableClass.class);
        if (tableClassAnnotation != null) {
            table.setCustomized(true);
            table.setKeyColumn(tableClassAnnotation.keyColumn());
            table.setKeyProperty(tableClassAnnotation.keyProperty());
            table.setUseGeneratedKeys(tableClassAnnotation.useGeneratedKeys());
        }

        List<Field> fieldList = ClassUtils.getAllDeclaredFields(tableClass);

        for (Field field : fieldList) {
            Column column = new Column();
            // 表格列名一般是小驼峰，转下划线格式
            column.setName(StringUtils.toUnderscore(field.getName()));
            column.setJavaField(field);

            // 解析注解
            ColumnField columnField = field.getAnnotation(ColumnField.class);
            if (columnField == null) {
                column.setExists(Column.DEFAULT_EXISTS);
                column.setSelectValue(Column.DEFAULT_SELECT_VALUE);
            } else {
                column.setExists(columnField.exists());
                column.setSelectValue(columnField.selectValue());
            }

            table.addColumn(column);
        }

        return table;
    }

}
