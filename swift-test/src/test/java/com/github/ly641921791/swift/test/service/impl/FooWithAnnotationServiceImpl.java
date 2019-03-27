package com.github.ly641921791.swift.test.service.impl;

import com.github.ly641921791.swift.spring.BaseService;
import com.github.ly641921791.swift.test.mapper.FooWithAnnotationMapper;
import com.github.ly641921791.swift.test.service.FooWithAnnotationService;
import com.github.ly641921791.swift.test.table.FooWithAnnotation;
import org.springframework.stereotype.Service;

/**
 * @author ly
 * @since 1.0.0
 **/
@Service
public class FooWithAnnotationServiceImpl extends BaseService<FooWithAnnotation, FooWithAnnotationMapper, Long> implements FooWithAnnotationService {
}
