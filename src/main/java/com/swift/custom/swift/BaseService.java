package com.swift.custom.swift;

import com.swift.custom.mapper.param.Condition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ly
 * @since 2019-01-24 15:45
 **/
public class BaseService<M extends BaseMapper<T>, T> implements IService<T> {

    @Autowired
    protected M mapper;

    @Override
    public int insert(T r) {
        return mapper.insert(r);
    }

    @Override
    public int update(T p, Condition c) {
        return mapper.update(p, c);
    }

    @Override
    public List<T> select(Condition c) {
        return mapper.select(c);
    }

}
