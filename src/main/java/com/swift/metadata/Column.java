package com.swift.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author ly
 */
@Data
@AllArgsConstructor
public class Column {

    private String name;

    private Field javaField;

    private Table table;
}
