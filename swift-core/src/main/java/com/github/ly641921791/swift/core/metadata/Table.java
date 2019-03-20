package com.github.ly641921791.swift.core.metadata;

import com.github.ly641921791.swift.core.annotation.ColumnField;
import com.github.ly641921791.swift.core.annotation.TableClass;
import com.github.ly641921791.swift.core.util.ClassUtils;
import com.github.ly641921791.swift.core.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

    private TableClass tableClassAnnotation;

    private boolean useGeneratedKeys;

    private String keyProperty;

    private String keyColumn;

    private String deleteColumn;

    private String deleteValue;

    private String existsValue;

    public void addColumn(Column column) {
        columns.add(column);
    }

    public static Table resolve(Class<?> tableClass, Configuration configuration) {
        Table table = new Table();

        // 注解解析
        TableClass tableClassAnnotation = tableClass.getAnnotation(TableClass.class);
        if (tableClassAnnotation != null) {
            table.setTableClassAnnotation(tableClassAnnotation);
            table.setName(tableClassAnnotation.tableName());
            table.setUseGeneratedKeys(tableClassAnnotation.useGeneratedKeys());
            table.setKeyProperty(tableClassAnnotation.keyProperty());
            table.setKeyColumn(tableClassAnnotation.keyColumn());
            table.setDeleteColumn(tableClassAnnotation.deleteColumn());
            table.setDeleteValue(tableClassAnnotation.deleteValue());
            table.setExistsValue(tableClassAnnotation.existsValue());
        } else {
            table.setUseGeneratedKeys(false);
            table.setKeyProperty(DEFAULT_KEY_PROPERTY);
            table.setKeyColumn(DEFAULT_KEY_COLUMN);
            table.setDeleteColumn("");
            table.setDeleteValue("");
            table.setExistsValue("");
        }

        // Java类名一般是大驼峰，转换下划线格式
        if (StringUtils.isEmpty(table.getName())) {
            table.setName(StringUtils.toUnderscore(tableClass.getSimpleName()));
        }

        List<Field> fieldList = ClassUtils.getAllDeclaredFields(tableClass);

        for (Field field : fieldList) {
            // 静态字段跳过
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

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
