package com.swift.custom.mapper.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 * @since 2019-01-25 15:44
 **/
@Data
public class SelectCondition {

    private Where where = new Where();

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
        where.add("AND", column, "=", "#{" + "c.params." + paramK + "}");
        return this;
    }

    class Where extends ArrayList<String> {

        public void add(String... arg) {
            addAll(Arrays.asList(arg));
        }

        @Override
        public String toString() {
            return String.join(" ", this);
        }
    }
}
