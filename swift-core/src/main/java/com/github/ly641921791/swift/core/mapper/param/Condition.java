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

}
