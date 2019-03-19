package com.github.ly641921791.swift.spring;

import com.github.ly641921791.swift.core.mapper.BaseMethod;

import java.util.Collection;
import java.util.List;

/**
 * Base Service Interface
 *
 * @param <T> Table Class
 * @author ly
 * @since 2019-01-24 15:44
 */
public interface IService<T, ID> extends BaseMethod<T, ID> {

    /**
     * Return count of record
     *
     * @return count
     */
    long count();

    /**
     * Find all records
     *
     * @return all records
     */
    List<T> findAll();

    /**
     * 插入记录
     * <p>
     * 只插入非Null列，如：设置r.id=1，则生成SQL为 INSERT INTO tbName (id) VALUES (#{id})
     *
     * @param entity entity
     * @return 插入记录数
     */
    int save(T entity);

    /**
     * Save all records
     *
     * @param entities records
     * @return save count
     */
    int saveAll(Collection<T> entities);

}
