package com.github.ly641921791.swift.test.service.impl;

import com.github.ly641921791.swift.spring.BaseService;
import com.github.ly641921791.swift.test.mapper.FooMapper;
import com.github.ly641921791.swift.test.service.FooService;
import com.github.ly641921791.swift.test.table.Foo;
import org.springframework.stereotype.Service;

/**
 * @author ly
 * @since 2019-03-13 09:30
 **/
@Service
public class FooServiceImpl extends BaseService<Foo, FooMapper, Long> implements FooService {
}
