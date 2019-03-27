package com.github.ly641921791.swift;

/**
 * @author ly
 * @since 1.0.0
 **/
public class ExceptionAssert {

    public static <T extends Exception> void assertException(Class<T> target, Executor executor) {
        try {
            executor.execute();
        } catch (Exception e) {
            if (target.isAssignableFrom(e.getClass())) {
                return;
            }
        }
        throw new AssertionError(String.format("expected:<%s> but not exception", target.getName()));
    }

}
