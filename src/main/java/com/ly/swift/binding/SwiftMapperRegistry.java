package com.ly.swift.binding;

import com.ly.swift.cache.CacheManager;
import com.ly.swift.parser.MapperParser;
import com.ly.swift.session.SwiftConfiguration;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * This class inherits {@link MapperRegistry}
 *
 * @author ly
 * @since 2018-12-21 17:20
 **/
public class SwiftMapperRegistry extends MapperRegistry {

    protected final SwiftConfiguration config;
    protected final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public SwiftMapperRegistry(SwiftConfiguration config) {
        super(config);
        this.config = config;
    }

    @Override
    public <T> boolean hasMapper(Class<T> type) {
        return this.knownMappers.containsKey(type);
    }

    @Override
    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                this.knownMappers.put(type, new MapperProxyFactory<T>(type));
                // It's important that the type is added before the parser is run
                // otherwise the binding may automatically be attempted by the
                // mapper parser. If the type is already known, it won't try.
                MapperAnnotationBuilder parser = new MapperParser(this.config, type);
                parser.parse();
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    this.knownMappers.remove(type);
                }
            }
        }
    }

}
