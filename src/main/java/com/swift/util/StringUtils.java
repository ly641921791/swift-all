package com.swift.util;

/**
 * @author ly
 */
public class StringUtils {

    public static String toCamelCase(String source) {
        StringBuilder sb = new StringBuilder();

        boolean toUpperCaseFlag = false;
        for (char c : source.toCharArray()) {
            if (c == '-') {
                toUpperCaseFlag = true;
                continue;
            }

            if (toUpperCaseFlag) {
                sb.append(Character.toUpperCase(c));
            }
        }

        return sb.toString();
    }

    public static String toUnderscore(String source) {
        StringBuilder sb = new StringBuilder();

        for (char c : source.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append('-').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }


}
