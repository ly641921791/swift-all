package com.swift.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}