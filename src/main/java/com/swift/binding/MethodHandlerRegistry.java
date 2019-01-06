package com.swift.binding;

import com.swift.executor.method.Find;
import com.swift.session.MethodHandler;
import com.swift.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 */
public class MethodHandlerRegistry {

    public static Map<String, MethodHandler> methodHandlers = new HashMap<>();

    static {
        methodHandlers.put(StringUtils.toLowerCamel(Find.class.getSimpleName()), new Find());
    }

    public static MethodHandler getMethodHandler(String methodName) {
        return methodHandlers.get(methodName);
    }

    public static boolean hasMethodHandler(String methodName) {
        return methodHandlers.containsKey(methodName);
    }

    public static Class<? extends Annotation> getSqlAnnotationType(Method method) {
        return getSqlAnnotationType(method.getName());
    }

    public static Class<? extends Annotation> getSqlAnnotationType(String methodName) {
        MethodHandler methodHandler = methodHandlers.get(methodName);
        if (methodHandler == null) {
            return null;
        }
        return methodHandler.getSqlAnnotationType();
    }

}
