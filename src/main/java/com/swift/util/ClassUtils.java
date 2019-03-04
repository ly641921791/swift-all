package com.swift.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ly
 */
public class ClassUtils {

    public static List<Field> getAllDeclaredFields(Class source) {
        List<Field> fieldList = new ArrayList<Field>();
        Class superClass = source;
        while (!Object.class.equals(superClass)) {
            fieldList.addAll(Arrays.asList(superClass.getDeclaredFields()));
            superClass = superClass.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 获得父类的泛型参数
     *
     * @param source 目标类
     * @return 父类泛型参数
     */
    public static Type[] getSuperclassGenericType(Class source) {
        Type type = source.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getActualTypeArguments();
        }
        return new Type[]{};
    }

    /**
     * 获得指定接口的泛型类型
     *
     * @param source        目标类
     * @param interfaceType 接口类型
     * @return 指定接口的泛型参数
     */
    public static Type[] getInterfacesGenericType(Class source, Class interfaceType) {
        Type[] types = source.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (Objects.equals(interfaceType, parameterizedType.getRawType())) {
                    return parameterizedType.getActualTypeArguments();
                }
            }
        }
        return new Type[]{};
    }

}