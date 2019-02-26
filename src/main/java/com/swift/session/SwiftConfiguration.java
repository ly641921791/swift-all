package com.swift.session;

import com.swift.binding.SwiftMapperRegistry;
import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.custom.support.MapperMethodResolverRegistry;
import lombok.Getter;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * 继承Configuration
 * <p>
 * 1 使用自定义MapperRegistry替换原有MapperRegistry，并将相关方法替换
 *
 * @author ly
 * @since 2018-12-21 16:44
 **/
public class SwiftConfiguration extends Configuration {

    // fields

    private final MapperRegistry mapperRegistry = new SwiftMapperRegistry(this);

    @Getter
    private final MapperMethodResolverRegistry mapperMethodResolverRegistry = new MapperMethodResolverRegistry();

    // methods

    @Override
    public MapperRegistry getMapperRegistry() {
        return this.mapperRegistry;
    }

    @Override
    public void addMappers(String packageName, Class<?> superType) {
        this.mapperRegistry.addMappers(packageName, superType);
    }

    @Override
    public void addMappers(String packageName) {
        this.mapperRegistry.addMappers(packageName);
    }

    @Override
    public <T> void addMapper(Class<T> type) {
        this.mapperRegistry.addMapper(type);
    }

    @Override
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    @Override
    public boolean hasMapper(Class<?> type) {
        return this.mapperRegistry.hasMapper(type);
    }

    public void addMapperMethodResolver(MapperMethodResolver resolver) {
        mapperMethodResolverRegistry.addMapperMethodResolver(resolver);
    }

    public MapperMethodResolver getMapperMethodResolver(Method method) {
        return mapperMethodResolverRegistry.getMapperMethodResolver(method);
    }

}
