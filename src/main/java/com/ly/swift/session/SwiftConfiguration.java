package com.ly.swift.session;

import com.ly.swift.binding.SwiftMapperRegistry;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * This class inherits {@link Configuration}
 *
 * <ul>
 * <li>Replace {@link MapperRegistry} and related code</li>
 * </ul>
 *
 * @author ly
 * @since 2018-12-21 16:44
 **/
public class SwiftConfiguration extends Configuration {

    protected final MapperRegistry mapperRegistry = new SwiftMapperRegistry(this);

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
}
