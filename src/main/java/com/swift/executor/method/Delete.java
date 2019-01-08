package com.swift.executor.method;

import com.swift.session.MethodHandler;
import org.apache.ibatis.session.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Delete implements MethodHandler {
    @Override
    public String buildSql(Method method, Configuration configuration) {
        return null;
    }

    @Override
    public Class<? extends Annotation> getSqlAnnotationType() {
        return org.apache.ibatis.annotations.Delete.class;
    }
}
