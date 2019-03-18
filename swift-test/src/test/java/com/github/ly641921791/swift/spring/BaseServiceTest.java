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
    public void findAllTestSuccess() {
        Assert.assertTrue(fooService.findAll().size() > fooWithAnnotationService.findAll().size());
    }

    @Test
    public void findAllByColumnTestSuccess() {
        Assert.assertEquals(fooService.findAllByColumn(Foo.STRING_VALUE, "findAllByColumn").size(), 2);
        Assert.assertEquals(fooWithAnnotationService.findAllByColumn(Foo.STRING_VALUE, "findAllByColumn").size(), 1);
    }

    @Test
    public void findAllByIdTestSuccess() {
        Assert.assertEquals(fooService.findAllById(Arrays.asList(8L, 9L)).size(), 2);
        Assert.assertEquals(fooWithAnnotationService.findAllById(Arrays.asList(8L, 9L)).size(), 1);
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
    public void updateByIdTestSuccess() {
        FooWithAnnotation targetProperty = new FooWithAnnotation();
        targetProperty.setStringValue("updateSuccess");

        fooService.updateById(targetProperty, 3L);
        Assert.assertEquals(fooService.findById(3L).getStringValue(), targetProperty.getStringValue());

        fooWithAnnotationService.updateById(targetProperty, 3L);
        Assert.assertEquals(fooWithAnnotationService.findById(3L).getStringValue(), targetProperty.getStringValue());
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

}
