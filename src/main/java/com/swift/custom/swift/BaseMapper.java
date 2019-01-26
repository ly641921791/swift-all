package com.swift.custom.swift;

import com.swift.custom.mapper.param.SelectCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper父类
 *
 * @author ly
 * @since 2019-01-24 15:44
 **/
public interface BaseMapper<T> {

    /**
     * 查询数据
     *
     * @param r record
     * @return 插入记录数
     */
    int insert(T r);

    /**
     * 查询记录
     *
     * @param c condition
     * @return 查询记录
     */
    List<T> select(@Param("c") SelectCondition c);

}