package com.github.ly641921791.swift.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.github.ly641921791.swift.core.metadata.Table.*;


/**
 * @author ly
 * @since 2019-02-02 13:55
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableClass {

    /**
     * 删除标识
     *
     * @return 删除标识
     */
    String deleteColumn() default "";

    boolean useGeneratedKeys() default DEFAULT_USE_GENERATED_KEYS;

    String keyProperty() default DEFAULT_KEY_PROPERTY;

    String keyColumn() default DEFAULT_KEY_COLUMN;

}
