package com.ly.swift.builder.annotation;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;

/**
 * @author ly
 * @since 2018-12-21 18:12
 **/
public class SwiftMapperAnnotationBuilder extends MapperAnnotationBuilder {

    public SwiftMapperAnnotationBuilder(Configuration configuration, Class<?> type) {
        super(configuration, type);
    }

    @Override
    public void parse() {

    }
}
