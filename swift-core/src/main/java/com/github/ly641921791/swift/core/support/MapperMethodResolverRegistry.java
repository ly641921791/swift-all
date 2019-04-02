package com.github.ly641921791.swift.core.support;

import com.github.ly641921791.swift.core.mapper.MapperMethodResolver;
import com.github.ly641921791.swift.core.mapper.method.Count;
import com.github.ly641921791.swift.core.mapper.method.Delete;
import com.github.ly641921791.swift.core.mapper.method.DeleteAllById;
import com.github.ly641921791.swift.core.mapper.method.DeleteByColumn;
import com.github.ly641921791.swift.core.mapper.method.FindAll;
import com.github.ly641921791.swift.core.mapper.method.FindAllByColumn;
import com.github.ly641921791.swift.core.mapper.method.FindAllById;
import com.github.ly641921791.swift.core.mapper.method.FindAllId;
import com.github.ly641921791.swift.core.mapper.method.FindById;
import com.github.ly641921791.swift.core.mapper.method.FindMapList;
import com.github.ly641921791.swift.core.mapper.method.FindOneByColumn;
import com.github.ly641921791.swift.core.mapper.method.Save;
import com.github.ly641921791.swift.core.mapper.method.Update;
import com.github.ly641921791.swift.core.mapper.method.UpdateByColumn;
import com.github.ly641921791.swift.core.mapper.method.UpdateById;
import com.github.ly641921791.swift.core.mapper.method.UpdateColumnById;
import com.github.ly641921791.swift.core.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper方法解析器注册类
 *
 * @author ly
 * @since 1.0.0
 **/
public class MapperMethodResolverRegistry {

    public Map<String, MapperMethodResolver> resolverMap = new HashMap<>();

    public MapperMethodResolverRegistry() {
        addMapperMethodResolver(new Count());
        addMapperMethodResolver(new Save());
        addMapperMethodResolver(new Delete());
        addMapperMethodResolver(new DeleteAllById());
        addMapperMethodResolver(new DeleteByColumn());
        addMapperMethodResolver(new Update());
        addMapperMethodResolver(new UpdateByColumn());
        addMapperMethodResolver(new UpdateById());
        addMapperMethodResolver(new FindAll());
        addMapperMethodResolver(new FindAllId());
        addMapperMethodResolver(new FindAllById());
        addMapperMethodResolver(new FindById());
        addMapperMethodResolver(new FindMapList());
        addMapperMethodResolver(new FindOneByColumn());
        addMapperMethodResolver(new FindAllByColumn());
        addMapperMethodResolver(new UpdateColumnById());
    }

    public void addMapperMethodResolver(MapperMethodResolver resolver) {
        resolverMap.put(StringUtils.toLowerCamel(resolver.getClass().getSimpleName()), resolver);
    }

    public MapperMethodResolver getMapperMethodResolver(Method method) {
        return resolverMap.get(method.getName());
    }

    public boolean hasResolver(Method method) {
        return resolverMap.containsKey(method.getName());
    }

}
