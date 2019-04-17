package com.github.ly641921791.swift.binding;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ly
 * @since 1.0.0
 **/
public class SwiftMapperProxy<T> extends MapperProxy<T> {

    public SwiftMapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        super(sqlSession, mapperInterface, methodCache);
    }

}
