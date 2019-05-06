package com.github.ly641921791.swift.metadata;

import com.github.ly641921791.swift.annotations.ColumnField;
import com.github.ly641921791.swift.annotations.TableClass;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.ClassUtils;
import com.github.ly641921791.swift.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void setName(String name, KeywordEscapePolicy keywordEscapePolicy, String escapeCharacter) {
        if (needEscape(name, keywordEscapePolicy)) {
            setName(escapeKeyword(name, escapeCharacter));
        } else {
            setName(name);
        }
    }

    public static Table resolve(Class<?> tableClass, SwiftConfiguration configuration) {
        Table table = new Table();

        // 注解解析
        TableClass tableClassAnnotation = tableClass.getAnnotation(TableClass.class);
        if (tableClassAnnotation == null) {
            table.setUseGeneratedKeys(false);
            table.setKeyProperty(DEFAULT_KEY_PROPERTY);
            table.setKeyColumn(DEFAULT_KEY_COLUMN);
            table.setDeleteColumn("");
            table.setDeleteValue("");
            table.setExistsValue("");
        } else {
            table.setTableClassAnnotation(tableClassAnnotation);
            table.setName(tableClassAnnotation.tableName(), keywordEscapePolicy, keywordEscapeCharacter);
            table.setUseGeneratedKeys(tableClassAnnotation.useGeneratedKeys());
            table.setKeyProperty(tableClassAnnotation.keyProperty());
            table.setKeyColumn(tableClassAnnotation.keyColumn());
            table.setDeleteColumn(tableClassAnnotation.deleteColumn());
            table.setDeleteValue(tableClassAnnotation.deleteValue());
            table.setExistsValue(tableClassAnnotation.existsValue());
        }

        // Java类名一般是大驼峰，转换下划线格式
        if (StringUtils.isEmpty(table.getName())) {
            table.setName(configuration.getTablePrefix() + StringUtils.toUnderscore(tableClass.getSimpleName()), keywordEscapePolicy, keywordEscapeCharacter);
        }

        List<Field> fieldList = ClassUtils.getAllDeclaredFields(tableClass);

        for (Field field : fieldList) {
            // 静态字段跳过
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Column column = new Column();

            column.setJavaField(field);

            // 解析注解
            ColumnField columnField = field.getAnnotation(ColumnField.class);
            if (columnField == null) {
                column.setExists(Column.DEFAULT_EXISTS);
                column.setSelectValue(Column.DEFAULT_SELECT_VALUE);
            } else {
                column.setName(columnField.columnName(), keywordEscapePolicy, keywordEscapeCharacter);
                column.setExists(columnField.exists());
                column.setSelectValue(columnField.selectValue());
            }

            // 表格列名一般是小驼峰，转下划线格式
            if (StringUtils.isEmpty(column.getName())) {
                column.setName(configuration.getColumnPrefix() + StringUtils.toUnderscore(field.getName()), keywordEscapePolicy, keywordEscapeCharacter);
            }

            table.addColumn(column);
        }

        return table;
    }

    /**
     * 是否需要转义
     *
     * @param keyword             关键字
     * @param keywordEscapePolicy 转义策略
     * @return 是否转义
     */
    static boolean needEscape(String keyword, KeywordEscapePolicy keywordEscapePolicy) {
        switch (keywordEscapePolicy) {
            case REQUIRED:
                if (Arrays.asList("LIKE", "WHERE").contains(keyword.toUpperCase())) {
                    return true;
                } else {
                    return false;
                }
            case ALL:
                return true;
            default:
                return false;
        }
    }

    /**
     * 字符转义
     *
     * @param keyword         关键字
     * @param escapeCharacter 转义字符
     * @return 转义后字符
     */
    static String escapeKeyword(String keyword, String escapeCharacter) {
        if (escapeCharacter.isEmpty()) {
            return keyword;
        }
        return escapeCharacter + keyword + escapeCharacter;
    }

}
