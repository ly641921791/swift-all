package com.github.ly641921791.swift.core.mapper.param;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 */
@Getter
public class Condition {

    private boolean or = false;

    private StringBuilder column = new StringBuilder();

    private String from;

    private StringBuilder join = new StringBuilder();

    /**
     * where条件
     */
    private StringBuilder where = new StringBuilder();

    private StringBuilder orderBy;

    private long[] limit;

    /**
     * 参数映射
     */
    private Map<String, Object> params = new HashMap<>();

    public void select(String column) {
        this.column.append(column).append(",");
    }

    public void select(String column, String as) {
        this.column.append(column).append(" AS ").append(as).append(",");
    }

    public void from(String from) {
        this.from = from;
    }

    public void innerJoin(String table, String on) {
        join.append("INNER JOIN ").append(table).append(" ON ").append(on).append(" ");
    }

    public void innerJoin(String table, String as, String on) {
        join.append("INNER JOIN ").append(table).append(" AS ").append(as).append(" ON ").append(on).append(" ");
    }

    public void leftJoin(String table, String on) {
        join.append("LEFT JOIN ").append(table).append(" ON ").append(on).append(" ");
    }

    public void leftJoin(String table, String as, String on) {
        join.append("LEFT JOIN ").append(table).append(" AS ").append(as).append(" ON ").append(on).append(" ");
    }

    public void rightJoin(String table, String on) {
        join.append("RIGHT JOIN ").append(table).append(" ON ").append(on).append(" ");
    }

    public void rightJoin(String table, String as, String on) {
        join.append("RIGHT JOIN ").append(table).append(" AS ").append(as).append(" ON ").append(on).append(" ");
    }

    /**
     * 等于
     *
     * @param column column
     * @param value  value
     * @return 当前对象
     */
    public Condition eq(String column, Object value) {
        String paramK = "k" + params.size();
        params.put(paramK, value);
        if (or) {
            where.append("OR ").append(column).append(" = #{c.params.").append(paramK).append("} ");
            or = false;
        } else {
            where.append("AND ").append(column).append(" = #{c.params.").append(paramK).append("} ");
        }
        return this;
    }

    /**
     * 不等于
     *
     * @param column column
     * @param value  value
     * @return 当前对象
     */
    public Condition ne(String column, Object value) {
        String paramK = "k" + params.size();
        params.put(paramK, value);
        if (or) {
            where.append("OR ").append(column).append(" <> #{c.params.").append(paramK).append("} ");
            or = false;
        } else {
            where.append("AND ").append(column).append(" <> #{c.params.").append(paramK).append("} ");
        }
        return this;
    }

    /**
     * 模糊查询。TODO 通过CONCAT实现，仅支持mysql数据库
     *
     * @param column 列名
     * @param value  模糊值
     * @return 当前对象
     */
    public Condition like(String column, Object value) {
        String paramK = "k" + params.size();
        params.put(paramK, value);
        if (or) {
            where.append("OR ").append(column).append(" LIKE CONCAT('%',#{c.params.").append(paramK).append("},'%') ");
            or = false;
        } else {
            where.append("AND ").append(column).append(" LIKE CONCAT('%',#{c.params.").append(paramK).append("},'%') ");
        }
        return this;
    }

    /**
     * 下一次条件作为OR条件
     *
     * @return 当前对象
     */
    public Condition or() {
        or = true;
        return this;
    }

    private void orderBy(String column, String sort) {
        if (orderBy == null) {
            orderBy = new StringBuilder(column);
        } else {
            orderBy.append(",").append(column);
        }
        orderBy.append(" ").append(sort);
    }

    public Condition orderByDesc(String column) {
        orderBy(column, "DESC");
        return this;
    }

    public Condition orderByAsc(String column) {
        orderBy(column, "ASC");
        return this;
    }

    public Condition limit(long offset, long size) {
        if (limit == null) {
            limit = new long[2];
        }
        limit[0] = offset;
        limit[1] = size;
        return this;
    }

    public Condition limit(long size) {
        limit(0, size);
        return this;
    }

}
