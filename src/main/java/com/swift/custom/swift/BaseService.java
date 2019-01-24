package com.swift.custom.swift;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ly
 * @since 2019-01-24 15:45
 **/
public class BaseService<M extends BaseMapper<T>, T> implements IService<M, T> {

    @Autowired
    private M mapper;

    @Override
    public int insert(T r) {
        return mapper.insert(r);
    }

}
