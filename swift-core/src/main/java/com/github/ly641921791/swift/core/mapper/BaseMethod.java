package com.github.ly641921791.swift.core.mapper;

import com.github.ly641921791.swift.core.annotation.TableClass;
import com.github.ly641921791.swift.core.mapper.param.Condition;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.P_A_COLUMN;
import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.P_A_CONDITION;
import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.P_A_ID;
import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.P_A_VALUE;
import static com.github.ly641921791.swift.core.mapper.MapperMethodResolver.P_A_VALUES;

/**
 * IService通过继承BaseMapper得到BaseMethod，会导致BaseService不能注入Mapper，
 * 因此将BaseMapper方法抽离出来，
 *
 * @author ly
 * @since 1.0.0
 **/
public interface BaseMethod<T, ID> {

    /**
     * Return count of record
     *
     * @return count
     */
    default long count() {
        return count(null);
    }

    /**
     * Return count of record
     *
     * @param c condition
     * @return count
     */
    long count(@Param("c") Condition c);

    /**
     * 删除记录
     *
     * @param c condition
     * @return 删除记录数
     */
    int delete(@Param(P_A_CONDITION) Condition c);

    /**
     * 根据id删除记录
     *
     * @param ids ids
     * @return 删除记录数
     */
    int deleteAllById(@Param("ids") Collection<ID> ids);

    /**
     * 根据id删除记录
     *
     * @param id id
     * @return 删除记录数
     */
    default int deleteById(ID id) {
        return deleteAllById(Collections.singleton(id));
    }

    /**
     * 根据指定列删除记录
     *
     * @param column 列名
     * @param value  值
     * @return 删除记录数
     */
    int deleteByColumn(@Param(P_A_COLUMN) String column, @Param(P_A_VALUE) Object value);

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
     * update by column
     *
     * @param p      target property
     * @param column column
     * @param value  value
     * @return update count
     */
    int updateByColumn(@Param("p") T p, @Param(P_A_COLUMN) String column, @Param(P_A_VALUE) Object value);

    /**
     * 根据id更新记录，注意：这个方法会修改id
     *
     * @param p  property
     * @param id id
     * @return 更新记录数
     */
    int updateById(@Param("p") T p, @Param("id") ID id);

    /**
     * update column by id
     *
     * @param column column
     * @param value  value
     * @param id     id
     * @return update count
     */
    int updateColumnById(@Param(P_A_COLUMN) String column, @Param(P_A_VALUE) Object value, @Param("id") ID id);

    /**
     * Find all records
     *
     * @return all records
     */
    default List<T> findAll() {
        return findAll(null);
    }

    /**
     * Find all records
     *
     * @param c condition
     * @return all records
     */
    List<T> findAll(@Param("c") Condition c);

    /**
     * 查询符合条件记录的id
     *
     * @return id list
     */
    default List<ID> findAllId() {
        return findAllId(null);
    }

    /**
     * 查询符合条件记录的id
     *
     * @param condition condition
     * @return id list
     */
    List<ID> findAllId(@Param("c") Condition condition);

    /**
     * 根据指定列查询记录
     *
     * @param column 列名
     * @param value  值
     * @return 符合条件记录
     */
    default List<T> findAllByColumn(String column, Object value) {
        return findAllByColumn(column, Collections.singletonList(value));
    }

    /**
     * 根据指定列查询记录
     *
     * @param column 列名
     * @param values 值
     * @return 符合条件记录
     */
    <C extends Collection> List<T> findAllByColumn(@Param(P_A_COLUMN) String column, @Param(P_A_VALUES) C values);

    /**
     * Find all records by ids
     *
     * @param ids ids
     * @return all records
     */
    List<T> findAllById(@Param("ids") Collection<ID> ids);

    /**
     * 根据id查询记录
     * <p>
     * 默认使用id字段，可以通过{@link TableClass#keyColumn()}设置自定义id列
     *
     * @param id id
     * @return 符合条件记录
     */
    T findById(@Param(P_A_ID) ID id);

    /**
     * 根据条件查询，查询结果包装为map
     *
     * @param condition 条件
     * @return 查询结果
     */
    List<Map> findMapList(@Param(P_A_CONDITION) Condition condition);

    /**
     * 根据指定列查询记录
     *
     * @param column 列名
     * @param value  值
     * @return 符合条件记录
     */
    T findOneByColumn(@Param(P_A_COLUMN) String column, @Param(P_A_VALUE) Object value);

    /**
     * 插入记录
     * <p>
     * 只插入非Null列，如：设置r.id=1，则生成SQL为 INSERT INTO tbName (id) VALUES (#{id})
     *
     * @param entity entity
     * @return 插入记录数
     */
    default int save(T entity) {
        return save(entity, false);
    }

    /**
     * 插入记录
     * <p>
     * 只插入非Null列，如：设置r.id=1，则生成SQL为 INSERT INTO tbName (id) VALUES (#{id})
     *
     * @param entity entity
     * @param ignore ignore exception
     * @return 插入记录数
     */
    int save(@Param("entity") T entity, @Param("ignore") boolean ignore);

    /**
     * Save all records
     *
     * @param entities records
     * @return save count
     */
    default int saveAll(Collection<T> entities) {
        if (entities == null) {
            return 0;
        }
        entities.forEach(this::save);
        return entities.size();
    }

}