package com.swift.cache;

import com.swift.metadata.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 */
public class CacheManager {

    public static final Map<Class, Table> tableCache = new HashMap<>();

}
