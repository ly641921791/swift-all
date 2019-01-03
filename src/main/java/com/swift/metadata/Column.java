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

    private String columnName;

    private Field javaField;

}
