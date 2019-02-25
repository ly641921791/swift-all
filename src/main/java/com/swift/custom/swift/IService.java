package com.swift.custom.swift;

import com.swift.custom.annotation.TableClass;
import com.swift.custom.mapper.param.Condition;

import java.util.List;

/**
 * Base Service Interface
 *
 * @param <T> Table Class
 * @author ly
 * @since 2019-01-24 15:44
 */
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
     * 根据id更新记录
     *
     * @param p  property
     * @param id id
     * @return 更新记录数
     */
    int updateById(T p, Object id);

    /**
     * 查询记录
     *
     * @param c condition
     * @return 符合条件记录
     */
    List<T> select(Condition c);

    /**
     * 根据id查询记录
     * <p>
     * 默认使用id字段，可以通过{@link TableClass#keyColumn()}设置自定义id列
     *
     * @param id id
     * @return 符合条件记录
     */
    T selectById(Object id);

    /**
     * 根据指定列查询记录
     *
     * @param column 列名
     * @param value  值
     * @return 符合条件记录
     */
    T selectRecordByColumn(String column, Object value);

    /**
     * 根据指定列查询记录
     *
     * @param column 列名
     * @param value  值
     * @return 符合条件记录
     */
    List<T> selectRecordsByColumn(String column, Object value);

}
