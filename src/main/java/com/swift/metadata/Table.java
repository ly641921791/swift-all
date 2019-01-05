package com.swift.metadata;

import lombok.Data;
import org.apache.ibatis.session.Configuration;
import sun.tools.jconsole.Tab;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.swift.util.StringUtils.toUnderscore;

/**
 * 数据库表信息
 *
 * @author ly
 */
@Data
public class Table {

    /**
     * 表名
     */
    private String name;

    /**
     * 表格列
     */
    private List<Column> columns = new ArrayList<>();



    public static Table resolve(Class tableClass, Configuration configuration) {

        boolean mapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();

        Table table = new Table();

        if (mapUnderscoreToCamelCase) {
            table.name = toUnderscore(tableClass.getSimpleName());
        } else {
            table.name = tableClass.getSimpleName();
        }


        return table;
    }

}
