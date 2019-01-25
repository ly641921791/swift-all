package com.swift.custom.support;

import com.swift.custom.metadata.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * Table注册类，Mapper和Table的映射关系
 *
 * @author ly
 */
public class TableRegistry {

    public Map<Class, Table> tableMap = new HashMap<>();

    public void addTable(Class mapper, Table table) {
        tableMap.put(mapper, table);
    }

    public Table getTable(Class mapper) {
        return tableMap.get(mapper);
    }

    public boolean hasTable(Class mapper) {
        return tableMap.containsKey(mapper);
    }

}
