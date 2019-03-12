package com.github.ly641921791.swift.spring;

import com.github.ly641921791.swift.core.mapper.method.Insert;
import com.github.ly641921791.swift.core.mapper.param.Condition;
import com.github.ly641921791.swift.core.mapper.BaseMapper;
import com.github.ly641921791.swift.core.util.ClassUtils;
import com.github.ly641921791.swift.core.util.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Base Service Implement
 *
 * @param <T> Table Class
 * @param <M> Mapper Class
 * @author ly
 * @since 2019-01-24 15:45
 */
public class BaseService<T, M extends BaseMapper<T, ID>, ID> implements IService<T, ID> {

    @Autowired
    protected M mapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int insert(T r) {
        return mapper.insert(r);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> saveAll(Iterable<T> entities) {
        Type[] type = ClassUtils.getSuperclassGenericType(getClass());
        String sqlStatement = ((Class) type[1]).getName() + "." + StringUtils.toUpperCamel(Insert.class.getSimpleName());
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            entities.forEach(e -> sqlSession.insert(sqlStatement, e));
            sqlSession.flushStatements();
        }
        return null;
    }

    @Override
    public int delete(Condition c) {
        return mapper.delete(c);
    }

    @Override
    public int deleteById(ID id) {
        return mapper.deleteById(id);
    }

    @Override
    public int deleteByColumn(String column, Object value) {
        return mapper.deleteByColumn(column, value);
    }

    @Override
    public int update(T p, Condition c) {
        return mapper.update(p, c);
    }

    @Override
    public int updateById(T p, Object id) {
        return mapper.updateById(p, id);
    }

    @Override
    public List<T> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<T> findAllById(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return mapper.findAllById(ids);
    }

    @Override
    public List<T> select(Condition c) {
        return mapper.select(c);
    }

    @Override
    public T selectById(Object id) {
        return mapper.selectById(id);
    }

    @Override
    public T selectRecordByColumn(String column, Object value) {
        return mapper.selectRecordByColumn(column, value);
    }

    @Override
    public List<T> selectRecordsByColumn(String column, Object value) {
        return mapper.selectRecordsByColumn(column, value);
    }

}
