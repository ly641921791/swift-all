package com.swift.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

/**
 * @author ly
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column {

    private String name;

    private Field javaField;

    private Table table;

}
