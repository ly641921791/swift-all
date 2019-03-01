package com.swift.custom.mapper;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author ly
 * @since 2019-03-01 09:42
 **/
public abstract class AbstractDeleteMethodResolver implements MapperMethodResolver {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.DELETE;
    }

}
