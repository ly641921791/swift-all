package com.github.ly641921791.swift.spring;

import com.github.ly641921791.swift.core.mapper.param.Condition;
import com.github.ly641921791.swift.test.FooApplication;
import com.github.ly641921791.swift.test.service.FooService;
import com.github.ly641921791.swift.test.service.FooWithAnnotationService;
import com.github.ly641921791.swift.test.table.Foo;
import com.github.ly641921791.swift.test.table.FooWithAnnotation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author ly
 * @since 1.0.0
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FooApplication.class)
public class BaseServiceTest {

    @Autowired
    private FooService fooService;

    @Autowired
    private FooWithAnnotationService fooWithAnnotationService;

    @Test
    public void deleteTestSuccess() {
        Condition condition = new Condition();
        condition.eq(Foo.ID, 1L);
        condition.eq(Foo.STRING_VALUE, "foo");

        fooService.delete(condition);
        fooWithAnnotationService.delete(condition);
    }

    @Test
    public void deleteByColumnTestSuccess() {
        fooService.deleteByColumn(Foo.ID, 1L);
        fooWithAnnotationService.deleteByColumn(Foo.ID, 1L);
    }

    @Test
    public void deleteByIdTestSuccess() {
        fooService.deleteById(2L);
        Assert.assertNull(fooService.findById(2L));

        fooWithAnnotationService.deleteById(2L);
        Assert.assertNull(fooWithAnnotationService.findById(2L));
    }

    @Test
    public void findByIdTestSuccess() {
        Assert.assertNotNull(fooService.findById(1L));
        Assert.assertNotNull(fooWithAnnotationService.findById(1L));
    }

    @Test
    public void saveAllTestSuccess() {
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
    public void updateColumnByIdTestSuccess() {
        fooService.updateColumnById(Foo.STRING_VALUE, "111", 1L);
        fooWithAnnotationService.updateColumnById(Foo.STRING_VALUE, "111", 1L);
    }

    // ---

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
    public void testUpdateById() {
        List<Foo> fooList = fooService.findAll();
        Foo beforeUpdate = fooList.get(0);

        Foo targetProperty = new Foo();
        targetProperty.setId(beforeUpdate.getId());
        targetProperty.setStringValue(UUID.randomUUID().toString());
        fooService.updateById(targetProperty, beforeUpdate.getId());

        Foo afterUpdate = fooService.findById(beforeUpdate.getId());
        Assert.assertEquals(targetProperty.getStringValue(), afterUpdate.getStringValue());
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
