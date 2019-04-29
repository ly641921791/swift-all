package com.github.ly641921791.swift.metadata;

/**
 * Keyword escape policy
 * ---
 * 关键字转义策略
 *
 * @author ly
 */
public enum KeywordEscapePolicy {

    /**
     * Never escape
     * ---
     * 从不转义
     */
    NEVER,

    /**
     * Escape if required
     * ---
     * 仅在需要时转义，使用已知的关键字库进行比较
     */
    REQUIRED,

    /**
     * Escape for customize keywords
     * ---
     * 使用自定义关键字库进行比较
     */
    CUSTOMIZE,

    /**
     * 已知库和自定义库一起判断
     */
    REQUIRED_AND_CUSTOMIZED,

    /**
     * Escape all
     * ---
     * 转义全部关键字
     */
    ALL

}
