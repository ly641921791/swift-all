package com.swift.util;

/**
 * @author ly
 */
public class StringUtils {

    public static final char UNDERSCORE = '_';

    /**
     * 字符串转大驼峰格式
     *
     * @param source 目标字符串
     * @return 目标字符串的大驼峰格式
     */
    public static String toUpperCamel(String source) {
        return toCamel(source, true);
    }

    /**
     * 字符串转小驼峰格式
     *
     * @param source 目标字符串
     * @return 目标字符串的小驼峰格式
     */
    public static String toLowerCamel(String source) {
        return toCamel(source, false);
    }

    private static String toCamel(String source, boolean toUpperCaseFlag) {
        StringBuilder sb = new StringBuilder();
        for (char c : trimLeft(source, UNDERSCORE).toCharArray()) {
            if (c == UNDERSCORE) {
                toUpperCaseFlag = true;
                continue;
            }
            if (toUpperCaseFlag) {
                sb.append(Character.toUpperCase(c));
                toUpperCaseFlag = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    public static String toUnderscore(String source) {
        StringBuilder sb = new StringBuilder();
        for (char c : trimLeft(source, UNDERSCORE).toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(UNDERSCORE).append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将目标字符串左边的指定字符去除
     *
     * @param source   目标字符串
     * @param trimChar 指定字符
     * @return 返回字符串
     */
    public static String trimLeft(String source, char trimChar) {
        int len = source.length();
        int st = 0;
        char[] val = source.toCharArray();

        while ((st < len) && (val[st] == trimChar)) {
            st++;
        }
        return st > 0 ? source.substring(st, len) : source;
    }

}
