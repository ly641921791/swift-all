package com.swift.metadata;

import lombok.Data;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.swift.util.StringUtils.toUnderscore;

/**
 * @author ly
 */
@Data
public class Table {

    // Source Field

    private Class sourceClass;

    private Configuration configuration;

    private boolean mapUnderscoreToCamelCase;

    // Table Info

    private String tableName;

    private List<Column> tableColumn;

    // Constructor

    public Table(Class type) {
        this(type, null);
    }

    public Table(Class type, Configuration configuration) {
        this.sourceClass = type;
        this.configuration = configuration;

        preInit();

        doInit();
    }

    private void preInit() {
        // 设置 下划线驼峰映射
        if (this.configuration != null && this.configuration.isMapUnderscoreToCamelCase()) {
            this.mapUnderscoreToCamelCase = true;
        }
    }

    private void doInit() {
        if (mapUnderscoreToCamelCase) {
            tableName = toUnderscore(sourceClass.getSimpleName());
        } else {
            tableName = sourceClass.getSimpleName();
        }

        tableColumn = new ArrayList<>();

        for (Field field : sourceClass.getDeclaredFields()) {
            if (mapUnderscoreToCamelCase) {
                tableColumn.add(new Column(toUnderscore(field.getName()), field));
            } else {
                tableColumn.add(new Column(field.getName(), field));
            }
        }
    }

}
