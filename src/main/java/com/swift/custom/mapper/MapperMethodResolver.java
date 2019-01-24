package com.swift.custom.mapper;

import com.swift.session.SwiftConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Mapper方法解析器
 *
 * @author ly
 */
public interface MapperMethodResolver {

    String buildSql(Method method, SwiftConfiguration configuration);

    Class<? extends Annotation> getSqlAnnotationType();

}
