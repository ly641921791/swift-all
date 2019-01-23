package com.swift.session;

/**
 * Mapper
 *
 * @author ly
 * @since 2019-01-23 16:30
 **/
public interface BaseMapper<T> {

    int insert(T r);

    int delete();

    int update();

    int select();

}
