package com.swift.custom.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.swift.custom.metadata.Column.*;

/**
 * @author ly
 * @since 2019-02-02 11:13
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnField {

    /**
     * 该字段是否在表格存在
     *
     * @return 是否存在
     */
    boolean exists() default DEFAULT_EXISTS;

    /**
     * 非空表示查询该字段时，指定值。
     * <p>
     * 支持SQL，存在注入风险。
     * <p>
     * 例1： @Column(selectValue="1") 表示查询时，该列始终返回 "1"
     * <p>
     * 例2： @Column(selectValue="SELECT COUNT(1) FROM xxx") 表示查询时，该列返回执行SQL执行的结果
     *
     * @return 查询值
     */
    String selectValue() default DEFAULT_SELECT_VALUE;

}
