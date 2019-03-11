package com.github.ly641921791.swift.core.metadata;

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

    public static final boolean DEFAULT_EXISTS = true;

    public static final String DEFAULT_SELECT_VALUE = "";

    private String name;

    private Field javaField;

    private boolean exists;

    private String selectValue;

}
