package com.swift.custom.support;

import com.swift.custom.mapper.MapperMethodResolver;
import com.swift.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper方法解析器注册类
 *
 * @author ly
 * @since 2019-01-24 16:55
 **/
public class MapperMethodResolverRegistry {

    public Map<String, MapperMethodResolver> resolverMap = new HashMap<>();

    public void addMapperMethodResolver(MapperMethodResolver resolver) {
        resolverMap.put(StringUtils.toLowerCamel(resolver.getClass().getSimpleName()), resolver);
    }

    public MapperMethodResolver getMapperMethodResolver(Method method) {
        return resolverMap.get(method.getName());
    }

    public boolean hasResolver(Method method) {
        return resolverMap.containsKey(method.getName());
    }

//    //
//
//    public static MapperMethodResolver getMethodHandler(String methodName) {
//        return methodHandlers.get(methodName);
//    }
//
//    public static boolean hasMethodHandler(String methodName) {
//        return methodHandlers.containsKey(methodName);
//    }
//
//    public static Class<? extends Annotation> getSqlAnnotationType(Method method) {
//        return getSqlAnnotationType(method.getName());
//    }
//
//    public static Class<? extends Annotation> getSqlAnnotationType(String methodName) {
//        MapperMethodResolver methodHandler = methodHandlers.get(methodName);
//        if (methodHandler == null) {
//            return null;
//        }
//        return methodHandler.getSqlAnnotationType();
//    }

}
