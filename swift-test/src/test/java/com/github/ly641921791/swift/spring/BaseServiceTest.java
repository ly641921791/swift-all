package com.github.ly641921791.swift.spring;

import com.github.ly641921791.swift.test.FooApplication;
import com.github.ly641921791.swift.test.service.FooService;
import com.github.ly641921791.swift.test.service.FooWithAnnotationService;
import com.github.ly641921791.swift.test.table.Foo;
import com.github.ly641921791.swift.test.table.FooWithAnnotation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author ly
 * @since 2019-03-13 09:33
 **/
@SpringBootTest(classes = FooApplication.class)
@RunWith(SpringRunner.class)
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
        Assert.assertEquals(count, 1);

        FooWithAnnotation fooWithAnnotation = new FooWithAnnotation();
        foo.setDel(0);
        count = fooWithAnnotationService.insert(fooWithAnnotation);
        Assert.assertEquals(count, 1);
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

        Assert.assertEquals(fooNewList.size(), fooList.size());
    }

    @Test
    public void testDeleteById() {
        List<Foo> fooList = fooService.findAll();
        Foo foo = fooList.get(0);
        fooService.deleteById(foo.getId());
        foo = fooService.selectById(foo.getId());
        Assert.assertNull(foo);

        List<FooWithAnnotation> fooWithAnnotationList = fooWithAnnotationService.findAll();
        FooWithAnnotation fooWithAnnotation = fooWithAnnotationList.get(0);
        fooWithAnnotationService.deleteById(fooWithAnnotation.getId());
        fooWithAnnotation = fooWithAnnotationService.selectById(fooWithAnnotation.getId());
        Assert.assertNull(fooWithAnnotation);
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
        Assert.assertEquals(targetProperty.getStringValue(), afterUpdate.getStringValue());
    }

    @Test
    public void testFindAll() {
        fooService.findAll();
        fooWithAnnotationService.findAll();
    }

}
