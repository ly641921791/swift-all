package com.github.ly641921791.swift.session;

import com.github.ly641921791.swift.binding.SwiftMapperRegistry;
import com.github.ly641921791.swift.builder.TableBuilder;
import com.github.ly641921791.swift.lang.StringArray;
import com.github.ly641921791.swift.mapping.MapperMethodHandler;
import com.github.ly641921791.swift.mapping.MapperMethodHandlerRegistry;
import com.github.ly641921791.swift.type.StringArrayTypeHandler;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ly
 * @since 1.0.0
 */
public class SwiftConfiguration extends Configuration {

    @Setter
    @Getter
    protected TableBuilder tableBuilder = new TableBuilder();

    private final MapperRegistry mapperRegistry = new SwiftMapperRegistry(this);

    /**
     * Replace Mapped Statement Names TODO 启动完成清理掉
     */
    private final Set<String> replaceMappedStatementNames = new HashSet<>();

    @Getter
    private final MapperMethodHandlerRegistry mapperMethodHandlerRegistry = new MapperMethodHandlerRegistry();

    public SwiftConfiguration() {
        super();
        typeHandlerRegistry.register(StringArray.class, new StringArrayTypeHandler());
        typeHandlerRegistry.register(JdbcType.VARCHAR, new StringArrayTypeHandler());
    }

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

    public void addMappedStatement(MappedStatement ms, boolean replaceable) {
        if (replaceable) {
            if (mappedStatements.containsKey(ms.getId())) {
                return;
            }
            replaceMappedStatementNames.add(ms.getId());
            mappedStatements.put(ms.getId(), ms);
            return;
        }

        if (mappedStatements.containsKey(ms.getId())) {
            if (replaceMappedStatementNames.contains(ms.getId())) {
                replaceMappedStatementNames.remove(ms.getId());
                mappedStatements.remove(ms.getId());
            }
        }

        mappedStatements.put(ms.getId(), ms);
    }

    public MapperMethodHandler getMapperMethodResolver(Method method) {
        return mapperMethodHandlerRegistry.getMapperMethodResolver(method);
    }

}
