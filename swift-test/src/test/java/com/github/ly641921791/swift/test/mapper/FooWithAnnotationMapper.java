package com.github.ly641921791.swift.test.mapper;

import com.github.ly641921791.swift.core.mapper.BaseMapper;
import com.github.ly641921791.swift.test.table.FooWithAnnotation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ly
 * @since 2019-03-13 10:01
 **/
@Mapper
public interface FooWithAnnotationMapper extends BaseMapper<FooWithAnnotation, Long> {
}
