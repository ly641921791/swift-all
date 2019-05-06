package com.github.ly641921791.swift.builder;

import com.github.ly641921791.swift.metadata.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ly
 * @since 1.0.0
 */
@Setter
@Getter
public class TableBuilder {

    private ConcurrentHashMap<Class, Table> tableCache = new ConcurrentHashMap<>();

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

    private String keywordEscapeCharacter = "";

    public Table builder(Class tableClass) {
        return tableCache.computeIfAbsent(tableClass, k -> {
            Table table = new Table();

            return table;
        });
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
