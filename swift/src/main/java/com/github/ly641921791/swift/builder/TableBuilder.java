package com.github.ly641921791.swift.builder;

import com.github.ly641921791.swift.annotations.ColumnField;
import com.github.ly641921791.swift.annotations.TableClass;
import com.github.ly641921791.swift.metadata.Column;
import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.util.ClassUtils;
import com.github.ly641921791.swift.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ly
 * @since 1.0.0
 */
@Setter
@Getter
public class TableBuilder {

    public static final boolean DEFAULT_USE_GENERATED_KEYS = false;

    public static final String DEFAULT_KEY_PROPERTY = "id";

    public static final String DEFAULT_KEY_COLUMN = "id";

    private static Set<String> keywords = new HashSet<>(Arrays.asList("SELECT", "LIKE", "WHERE"));

    private Set<String> keyword = new HashSet<>();

    private ConcurrentHashMap<Class<?>, Table> tableCache = new ConcurrentHashMap<>();

    /**
     * table name prefix
     * ---
     * 表格名前缀
     */
    private String tablePrefix = "";

    /**
     * table name suffix
     * ---
     * 表格名后缀
     */
    private String tableSuffix = "";

    /**
     * column name prefix
     * ---
     * 列名前缀
     */
    private String columnPrefix = "";

    /**
     * column name suffix
     * ---
     * 列名后缀
     */
    private String columnSuffix = "";

    private KeywordEscapeStrategy keywordEscapeStrategy = KeywordEscapeStrategy.REQUIRED;

    /**
     * SQL keyword escape character
     * ---
     * SQL 关键字转义字符
     */
    private String keywordEscapeCharacter = "";

    public Table builder(Class<?> tableClass) {
        return tableCache.computeIfAbsent(tableClass, tc -> {
            Table table = new Table();
            TableClass tableClassAnnotation = tc.getAnnotation(TableClass.class);
            if (tableClassAnnotation == null) {
                table.setUseGeneratedKeys(false);
                table.setKeyProperty(DEFAULT_KEY_PROPERTY);
                table.setKeyColumn(DEFAULT_KEY_COLUMN);
                table.setDeleteColumn("");
                table.setDeleteValue("");
                table.setExistsValue("");
            } else {
                table.setTableClassAnnotation(tableClassAnnotation);
                table.setName(tableClassAnnotation.tableName());
                table.setUseGeneratedKeys(tableClassAnnotation.useGeneratedKeys());
                table.setKeyProperty(tableClassAnnotation.keyProperty());
                table.setKeyColumn(tableClassAnnotation.keyColumn());
                table.setDeleteColumn(tableClassAnnotation.deleteColumn());
                table.setDeleteValue(tableClassAnnotation.deleteValue());
                table.setExistsValue(tableClassAnnotation.existsValue());
            }

            if (StringUtils.isEmpty(table.getName())) {
                table.setName(getTablePrefix() + StringUtils.toUnderscore(tc.getSimpleName()) + getTableSuffix());
            }

            table.setName(escapeKeyword(table.getName()));

            List<Field> fieldList = ClassUtils.getAllDeclaredFields(tc);

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
                    column.setName(columnField.columnName());
                    column.setExists(columnField.exists());
                    column.setSelectValue(columnField.selectValue());
                }

                // 表格列名一般是小驼峰，转下划线格式
                if (StringUtils.isEmpty(column.getName())) {
                    column.setName(getColumnPrefix() + StringUtils.toUnderscore(field.getName()) + getColumnPrefix());
                }

                column.setName(escapeKeyword(column.getName()));

                table.addColumn(column);
            }

            return table;
        });
    }

    private String escapeKeyword(String keyword) {
        switch (getKeywordEscapeStrategy()) {
            case REQUIRED:
                if (keywords.contains(keyword.toUpperCase()) || getKeyword().contains(keyword.toUpperCase())) {
                    return getKeywordEscapeCharacter() + keyword + getKeywordEscapeCharacter();
                }
            case ALL:
                return getKeywordEscapeCharacter() + keyword + getKeywordEscapeCharacter();
            case NEVER:
            default:
                return keyword;
        }
    }

    /**
     * SQL keyword escape strategy
     * ---
     * SQL 关键字转义策略
     */
    enum KeywordEscapeStrategy {

        /**
         * Never escape
         * ---
         * 从不转义
         */
        NEVER,

        /**
         * Escape if required
         * ---
         * 仅在需要时转义，使用已知的关键字库进行比较
         */
        REQUIRED,

        /**
         * Escape all
         * ---
         * 转义全部关键字
         */
        ALL

    }

}
