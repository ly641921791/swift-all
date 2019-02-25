package com.swift.custom.swift;

import com.swift.custom.mapper.param.Condition;
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
     * 插入记录
     * <p>
     * 只插入非Null列，如：设置r.id=1，则生成SQL为 INSERT INTO tbName (id) VALUES (#{id})
     *
     * @param r record
     * @return 插入记录数
     */
    int insert(T r);

    /**
     * 删除记录
     *
     * @param c condition
     * @return 删除记录数
     */
    int delete(@Param("c") Condition c);

    /**
     * 更新记录
     * <p>
     * 只更新非Null列，如：设置r.id=1，则生成SQL为 UPDATE tbName SET id = #{id} WHERE xxx
     *
     * @param p property
     * @param c condition
     * @return 更新记录数
     */
    int update(@Param("p") T p, @Param("c") Condition c);

    /**
     * 根据id更新记录
     *
     * @param p  property
     * @param id id
     * @return 更新记录数
     */
    int updateById(@Param("p") T p, @Param("id") Object id);

    /**
     * 查询记录
     *
     * @param c condition
     * @return 符合条件记录
     */
    List<T> select(@Param("c") Condition c);

    /**
     * 根据id查询记录
     * <p>
     * 默认使用id字段，TODO 日后新增@TableClass注解配置
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
    T selectRecordByColumn(@Param("c") String column, @Param("v") Object value);

    /**
     * 根据指定列查询记录
     *
     * @param column 列名
     * @param value  值
     * @return 符合条件记录
     */
    List<T> selectRecordsByColumn(@Param("c") String column, @Param("v") Object value);

}