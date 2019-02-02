package com.swift.custom.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.swift.custom.metadata.Table.*;

/**
 * @author ly
 * @since 2019-02-02 13:55
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableClass {

    boolean useGeneratedKeys() default DEFAULT_USE_GENERATED_KEYS;

    String keyProperty() default DEFAULT_KEY_PROPERTY;

    String keyColumn() default DEFAULT_KEY_COLUMN;

}
