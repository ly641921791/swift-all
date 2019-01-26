package com.swift.custom.swift;

import com.swift.custom.mapper.param.Condition;

import java.util.List;

/**
 * Service接口
 *
 * @author ly
 * @since 2019-01-24 15:44
 **/
public interface IService<T> {

    /**
     * 插入记录
     * <p>
     * 只插入非Null列，如：设置r.id=1，则生成SQL为 INSERT INTO tbName (id) VALUES (#{id})
     *
     * @param r record
     * @return 插入记录数
     */
    int insert(T r);

    /**
     * 更新记录
     * <p>
     * 只更新非Null列，如：设置r.id=1，则生成SQL为 UPDATE tbName SET id = #{id} WHERE xxx
     *
     * @param p property
     * @param c condition
     * @return 更新记录数
     */
    int update(T p, Condition c);

    /**
     * 查询记录
     *
     * @param c condition
     * @return 符合条件记录
     */
    List<T> select(Condition c);
}
