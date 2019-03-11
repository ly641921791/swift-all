package com.github.ly641921791.swift.core.swift;

import com.github.ly641921791.swift.core.annotation.TableClass;
import com.github.ly641921791.swift.core.mapper.param.Condition;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.COLUMN_PARAM;
import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.CONDITION_PARAM;
import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.VALUE_PARAM;

/**
 * IService通过继承BaseMapper得到BaseMethod，会导致BaseService不能注入Mapper，
 * 因此将BaseMapper方法抽离出来，
 *
 * @author ly
 * @since 2019-03-01 11:18
 **/
interface BaseMethod<T, ID> {

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
    int delete(@Param(CONDITION_PARAM) Condition c);

    /**
     * 根据id删除记录
     *
     * @param id id
     * @return 删除记录数
     */
    int deleteById(ID id);

    /**
     * 根据指定列删除记录
     *
     * @param column 列名
     * @param value  值
     * @return 删除记录数
     */
    int deleteByColumn(@Param(COLUMN_PARAM) String column, @Param(VALUE_PARAM) Object value);

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
     * Find all records
     *
     * @return all records
     */
    List<T> findAll();

    /**
     * Find all records by ids
     *
     * @param ids ids
     * @return all records
     */
    List<T> findAllById(Collection<ID> ids);

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