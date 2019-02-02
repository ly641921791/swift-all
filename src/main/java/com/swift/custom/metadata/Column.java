package com.swift.custom.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * @author ly
 */
@Setter
@Getter
@EqualsAndHashCode
public class Column {

    private String name;

    private Field javaField;

    private boolean exists;

    private String selectValue;

}
