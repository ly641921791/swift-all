package com.swift.custom.mapper.param;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 * @since 2019-01-25 15:44
 **/
@Data
public class SelectCondition {

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
    public SelectCondition eq(String column, Object value) {
        String paramK = "k" + params.size();
        params.put(paramK, value);
        where.append("AND ").append(column).append(" = #{c.params.").append(paramK).append("} ");
        return this;
    }

}
