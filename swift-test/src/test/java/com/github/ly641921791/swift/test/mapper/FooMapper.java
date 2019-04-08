package com.github.ly641921791.swift.test.mapper;

import com.github.ly641921791.swift.mapping.BaseMapper;
import com.github.ly641921791.swift.test.table.Foo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ly
 * @since 1.0.0
 **/
@Mapper
public interface FooMapper extends BaseMapper<Foo, Long> {
}
