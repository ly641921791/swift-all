package com.github.ly641921791.swift;

/**
 * @author ly
 * @since 2019-03-20 15:47
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
