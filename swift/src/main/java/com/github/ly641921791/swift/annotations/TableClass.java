package com.github.ly641921791.swift.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.github.ly641921791.swift.metadata.Table.DEFAULT_KEY_COLUMN;
import static com.github.ly641921791.swift.metadata.Table.DEFAULT_KEY_PROPERTY;
import static com.github.ly641921791.swift.metadata.Table.DEFAULT_USE_GENERATED_KEYS;

/**
 * @author ly
 * @since 1.0.0
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableClass {

    String tableName() default "";

    /**
     * 删除标识
     *
     * @return 删除标识
     */
    String deleteColumn() default "";

    /**
     * 配合删除标识使用，例如删除标识字段是 del，删除值是 1，即 del = 1 的列表标识删除，那么查询操作会加入 del != 1 条件
     *
     * @return 删除值
     */
    String deleteValue() default "";

    String existsValue() default "";

    boolean useGeneratedKeys() default DEFAULT_USE_GENERATED_KEYS;

    String keyProperty() default DEFAULT_KEY_PROPERTY;

    String keyColumn() default DEFAULT_KEY_COLUMN;

}
