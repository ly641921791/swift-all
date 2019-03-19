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
