package com.github.ly641921791.swift.test.mapper;

import com.github.ly641921791.swift.mapping.BaseMapper;
import com.github.ly641921791.swift.test.table.Foo2;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ly
 * @since 1.0.0
 **/
@Mapper
public interface Foo2Mapper extends BaseMapper<Foo2, Long> {
}
