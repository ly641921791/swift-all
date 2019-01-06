package com.swift.session;

import org.apache.ibatis.session.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author ly
 */
public interface MethodHandler {

    String buildSql(Method method, Configuration configuration);

    Class<? extends Annotation> getSqlAnnotationType();

}
