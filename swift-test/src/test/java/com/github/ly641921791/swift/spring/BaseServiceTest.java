package com.github.ly641921791.swift.spring;

import com.github.ly641921791.swift.test.FooApplication;
import com.github.ly641921791.swift.test.service.FooService;
import com.github.ly641921791.swift.test.service.FooWithAnnotationService;
import com.github.ly641921791.swift.test.table.Foo;
import com.github.ly641921791.swift.test.table.FooWithAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author ly
 * @since 2019-03-13 09:33
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FooApplication.class)
public class BaseServiceTest {

    @Autowired
    private FooService fooService;

    @Autowired
    private FooWithAnnotationService fooWithAnnotationService;

    @Test
    public void testInsert() {
        Foo foo = new Foo();
        foo.setDel(0);
        int count = fooService.insert(foo);

        Assert.state(count == 1);

        FooWithAnnotation fooWithAnnotation = new FooWithAnnotation();
        foo.setDel(0);
        count = fooWithAnnotationService.insert(fooWithAnnotation);

        Assert.state(count == 1);
    }

    @Test
    public void testSaveAll() {
        List<Long> idList = Arrays.asList(1001L, 1002L);
        final List<Foo> fooList = new ArrayList<>();
        idList.forEach(id -> {
            Foo foo = new Foo();
            foo.setId(id);
            foo.setDel(0);
            fooList.add(foo);
        });

        fooService.saveAll(fooList);

        List<Foo> fooNewList = fooService.findAllById(idList);

        Assert.state(fooNewList.size() == fooList.size());
    }

    @Test
    public void testDeleteById() {
        List<Foo> fooList = fooService.findAll();
        Foo foo = fooList.get(0);
        fooService.deleteById(foo.getId());
        foo = fooService.selectById(foo.getId());

        Assert.isNull(foo);

        List<FooWithAnnotation> fooWithAnnotationList = fooWithAnnotationService.findAll();
        FooWithAnnotation fooWithAnnotation = fooWithAnnotationList.get(0);
        fooWithAnnotationService.deleteById(fooWithAnnotation.getId());
        fooWithAnnotation = fooWithAnnotationService.selectById(fooWithAnnotation.getId());

        Assert.isNull(fooWithAnnotation);
    }

    @Test
    public void testUpdateById() {
        List<Foo> fooList = fooService.findAll();
        Foo beforeUpdate = fooList.get(0);

        Foo targetProperty = new Foo();
        targetProperty.setId(beforeUpdate.getId());
        targetProperty.setStringValue(UUID.randomUUID().toString());
        fooService.updateById(targetProperty, beforeUpdate.getId());

        Foo afterUpdate = fooService.selectById(beforeUpdate.getId());

        Assert.state(targetProperty.getStringValue().equals(afterUpdate.getStringValue()));
    }

    @Test
    public void testFindAll() {
        fooService.findAll();
        fooWithAnnotationService.findAll();
    }

    @Test
    public void testFindAllByColumn() {
        fooService.findAllByColumn("id", 1);
        fooWithAnnotationService.findAllByColumn("id", 1);
    }

}
