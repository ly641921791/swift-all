package com.swift.custom.swift;

/**
 * @author ly
 * @since 2019-01-24 15:44
 **/
public interface IService<M extends BaseMapper, T> {

    int insert(T r);

}
