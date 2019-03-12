package com.github.ly641921791.swift.core.util;

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

    /**
     * 字符串转驼峰格式
     *
     * @param source          目标字符串
     * @param toUpperCaseFlag 是否转换大驼峰
     * @return 结果
     */
    private static String toCamel(String source, boolean toUpperCaseFlag) {
        // 第一个字符特殊处理，后面的字符除非前面遇到下划线，否则不变
        StringBuilder sb = new StringBuilder();
        char[] cs = trim(source, UNDERSCORE).toCharArray();

        if (cs.length == 0) {
            return "";
        }

        if (toUpperCaseFlag) {
            sb.append(Character.toUpperCase(cs[0]));
        } else {
            sb.append(Character.toLowerCase(cs[0]));
        }

        if (cs.length == 1) {
            return sb.toString();
        }

        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == UNDERSCORE) {
                toUpperCaseFlag = true;
                continue;
            }
            if (toUpperCaseFlag) {
                sb.append(Character.toUpperCase(cs[i]));
                toUpperCaseFlag = false;
            } else {
                sb.append(cs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串转下划线格式
     *
     * @param source 目标字符串
     * @return 下划线格式
     */
    public static String toUnderscore(String source) {
        StringBuilder sb = new StringBuilder();
        for (char c : source.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(UNDERSCORE).append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return trim(sb.toString(), UNDERSCORE);
    }

    /**
     * 将目标字符串左边的指定字符去除
     *
     * @param source   目标字符串
     * @param trimChar 指定字符
     * @return 返回字符串
     */
    public static String trim(String source, char trimChar) {
        int len = source.length();
        int st = 0;
        char[] val = source.toCharArray();

        while ((st < len) && (val[st] == trimChar)) {
            st++;
        }
        while ((st < len) && (val[len - 1] == trimChar)) {
            len--;
        }
        return ((st > 0) || (len < source.length())) ? source.substring(st, len) : source;
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }

}
