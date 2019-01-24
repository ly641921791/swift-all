package com.swift.custom.support;

import com.swift.custom.metadata.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 */
public class TableRegistry {

    public Map<String, Table> tableMap = new HashMap<>();

    public void addTable(Table table) {
        tableMap.put(table.getName(), table);
    }

    public Table getTable(String table) {
        return tableMap.get(table);
    }

    public boolean hasTable(String table) {
        return tableMap.containsKey(table);
    }

}
