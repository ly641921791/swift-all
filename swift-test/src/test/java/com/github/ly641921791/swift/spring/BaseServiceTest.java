package com.github.ly641921791.swift.spring;

import com.github.ly641921791.swift.ExceptionAssert;
import com.github.ly641921791.swift.mapping.Condition;
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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author ly
 * @since 1.0.0
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FooApplication.class)
public class BaseServiceTest {

    private int fooCount = 14;
    private int foo2Count = 11;

    @Autowired
    private FooService fooService;

    @Autowired
    private FooWithAnnotationService fooWithAnnotationService;

    @Test
    public void count() {
        Assert.assertEquals(fooService.count(), fooCount);
        Assert.assertEquals(fooWithAnnotationService.count(), foo2Count);

        Condition condition = new Condition();
        condition.eq(Foo.ID, 1L);
        condition.orderByDesc(Foo.ID);
        condition.limit(1);
        Assert.assertEquals(fooService.count(condition), 1);
        Assert.assertEquals(fooWithAnnotationService.count(condition), 1);
    }

    @Test
    @Transactional
    public void delete() {
        Foo foo = fooService.findById(1L);
        Condition condition = new Condition();
        condition.eq(Foo.ID, foo.getId());
        condition.eq(Foo.STRING_VALUE, foo.getStringValue());
        fooService.delete(condition);
        Assert.assertNull(fooService.findById(foo.getId()));

        FooWithAnnotation fooWithAnnotation = fooWithAnnotationService.findById(1L);
        condition = new Condition();
        condition.eq(FooWithAnnotation.ID, fooWithAnnotation.getId());
        condition.eq(FooWithAnnotation.STRING_VALUE, fooWithAnnotation.getStringValue());
        fooWithAnnotationService.delete(condition);
        Assert.assertNull(fooWithAnnotationService.findById(foo.getId()));

        // 只删除一条记录
        Assert.assertEquals(fooService.findAll().size(), fooCount - 1);
        Assert.assertEquals(fooWithAnnotationService.findAll().size(), foo2Count - 1);
    }

    @Test
    @Transactional
    public void deleteAllById() {
        Assert.assertNotNull(fooService.findById(14L));
        fooService.deleteAllById(Collections.singleton(14L));
        Assert.assertNull(fooService.findById(14L));

        Assert.assertNotNull(fooWithAnnotationService.findById(14L));
        fooWithAnnotationService.deleteAllById(Collections.singleton(14L));
        Assert.assertNull(fooWithAnnotationService.findById(14L));

        // 只删除一条记录
        Assert.assertEquals(fooService.findAll().size(), fooCount - 1);
        Assert.assertEquals(fooWithAnnotationService.findAll().size(), foo2Count - 1);
    }

    @Test
    @Transactional
    public void deleteByColumn() {
        Assert.assertNotNull(fooService.findById(11L));
        Assert.assertEquals(fooService.deleteByColumn(Foo.ID, 11L), 1);
        Assert.assertEquals(fooService.deleteByColumn(Foo.ID, 11L), 0);
        Assert.assertNull(fooService.findById(11L));

        Assert.assertNotNull(fooWithAnnotationService.findById(11L));
        Assert.assertEquals(fooWithAnnotationService.deleteByColumn(Foo.ID, 11L), 1);
        Assert.assertEquals(fooWithAnnotationService.deleteByColumn(Foo.ID, 11L), 0);
        Assert.assertNull(fooWithAnnotationService.findById(11L));

        // 只删除一条记录
        Assert.assertEquals(fooService.findAll().size(), fooCount - 1);
        Assert.assertEquals(fooWithAnnotationService.findAll().size(), foo2Count - 1);
    }

    @Test
    @Transactional
    public void deleteById() {
        Assert.assertNotNull(fooService.findById(2L));
        fooService.deleteById(2L);
        Assert.assertNull(fooService.findById(2L));

        Assert.assertNotNull(fooWithAnnotationService.findById(2L));
        fooWithAnnotationService.deleteById(2L);
        Assert.assertNull(fooWithAnnotationService.findById(2L));

        // 只删除一条记录
        Assert.assertEquals(fooService.findAll().size(), fooCount - 1);
        Assert.assertEquals(fooWithAnnotationService.findAll().size(), foo2Count - 1);
    }

    @Test
    public void findAll() {
        Assert.assertEquals(fooService.findAll().size(), fooCount);
        Assert.assertEquals(fooWithAnnotationService.findAll().size(), foo2Count);

        fooService.findAll().forEach(foo -> foo.setId(foo.equals(new Foo()) ? 0L : 1L));
        fooWithAnnotationService.findAll().forEach(foo -> foo.setId(foo.equals(new FooWithAnnotation()) ? 0L : 1L));

        Condition condition = new Condition();
        condition.eq(Foo.ID, 1L);
        condition.like(Foo.STRING_VALUE, "find");
        condition.orderByDesc(Foo.ID);
        condition.limit(1);
        Assert.assertEquals(fooService.findAll(condition).size(), 1);

        condition = new Condition();
        condition.eq(FooWithAnnotation.ID, 1L);
        condition.like(FooWithAnnotation.STRING_VALUE, "find");
        condition.orderByDesc(FooWithAnnotation.ID);
        condition.limit(1);
        Assert.assertEquals(fooWithAnnotationService.findAll(condition).size(), 1);
    }

    @Test
    public void findAllId() {
        Condition condition = new Condition();
        condition.eq(Foo.STRING_VALUE, "findAllId");
        Assert.assertEquals(fooService.findAllId().size(), fooCount);
        Assert.assertEquals(fooService.findAllId(condition).size(), 1);
        fooService.findAllId().forEach(id -> id++);

        condition = new Condition();
        condition.eq(FooWithAnnotation.STRING_VALUE, "findAllId");
        Assert.assertEquals(fooWithAnnotationService.findAllId().size(), foo2Count);
        Assert.assertEquals(fooWithAnnotationService.findAllId(condition).size(), 1);
        fooWithAnnotationService.findAllId().forEach(id -> id++);
    }

    @Test
    public void findAllByColumn() {
        Assert.assertEquals(fooService.findAllByColumn(Foo.STRING_VALUE, "findAllByColumn").size(), 2);
        Assert.assertEquals(fooService.findAllByColumn(Foo.STRING_VALUE, Collections.singleton("findAllByColumn")).size(), 2);
        Assert.assertEquals(fooService.findAllByColumn(Foo.STRING_VALUE, Collections.singletonList("findAllByColumn")).size(), 2);
        fooService.findAllByColumn(Foo.STRING_VALUE, Collections.singletonList("findAllByColumn")).forEach(foo -> foo.setId(foo.equals(new Foo()) ? 0L : 1L));

        Assert.assertEquals(fooWithAnnotationService.findAllByColumn(FooWithAnnotation.STRING_VALUE, "findAllByColumn").size(), 1);
        Assert.assertEquals(fooWithAnnotationService.findAllByColumn(FooWithAnnotation.STRING_VALUE, Collections.singleton("findAllByColumn")).size(), 1);
        Assert.assertEquals(fooWithAnnotationService.findAllByColumn(FooWithAnnotation.STRING_VALUE, Collections.singletonList("findAllByColumn")).size(), 1);
        fooWithAnnotationService.findAllByColumn(FooWithAnnotation.STRING_VALUE, Collections.singletonList("findAllByColumn")).forEach(foo -> foo.setId(foo.equals(new FooWithAnnotation()) ? 0L : 1L));
    }

    @Test
    public void findAllById() {
        Assert.assertEquals(fooService.findAllById(Arrays.asList(8L, 9L)).size(), 2);
        fooService.findAllById(Collections.singletonList(8L)).forEach(foo -> foo.setId(foo.equals(new Foo()) ? 0L : 1L));

        Assert.assertEquals(fooWithAnnotationService.findAllById(Arrays.asList(8L, 9L)).size(), 1);
        fooWithAnnotationService.findAllById(Collections.singletonList(8L)).forEach(foo -> foo.setId(foo.equals(new FooWithAnnotation()) ? 0L : 1L));
    }

    @Test
    public void findById() {
        Foo foo = new Foo();
        foo.setId(1L);
        foo.setStringValue("findById");
        foo.setDel(1);
        Assert.assertEquals(fooService.findById(1L), foo);

        FooWithAnnotation fooWithAnnotation = new FooWithAnnotation();
        fooWithAnnotation.setId(1L);
        fooWithAnnotation.setStringValue("findById");
        fooWithAnnotation.setDel(0);
        fooWithAnnotation.setNotExistsColumn("1");
        Assert.assertEquals(fooWithAnnotationService.findById(1L), fooWithAnnotation);
    }

    @Test
    public void findMapList() {
        Condition condition = new Condition();
        condition.select("f1.id", "f1");
        condition.select("f2.id", "f2");
        condition.from("foo AS f1");
        condition.innerJoin("foo AS f2", "f1.id = f2.id");
        Assert.assertNotNull(fooService.findMapList(condition));
        Assert.assertNotNull(fooWithAnnotationService.findMapList(condition));
    }

    @Test
    public void findOneByColumn() {
        fooService.findOneByColumn(Foo.STRING_VALUE, "findOneByColumn").setId(1L);
        fooWithAnnotationService.findOneByColumn(FooWithAnnotation.STRING_VALUE, "findOneByColumn").setId(1L);
    }

    @Test
    @Transactional
    public void save() {
        Foo foo = new Foo();
        foo.setId(new Random().nextLong());
        foo.setDel(0);

        Assert.assertEquals(fooService.save(foo), 1);
        ExceptionAssert.assertException(DuplicateKeyException.class, () -> fooService.save(foo));
        Assert.assertEquals(fooService.save(foo, true), 0);
        Assert.assertEquals(fooService.findById(foo.getId()), foo);

        FooWithAnnotation fooWithAnnotation = new FooWithAnnotation();
        fooWithAnnotation.setId(new Random().nextLong());
        fooWithAnnotation.setDel(0);
        fooWithAnnotation.setNotExistsColumn("1");

        Assert.assertEquals(fooWithAnnotationService.save(fooWithAnnotation), 1);
        ExceptionAssert.assertException(DuplicateKeyException.class, () -> fooWithAnnotationService.save(fooWithAnnotation));
        Assert.assertEquals(fooWithAnnotationService.save(fooWithAnnotation, true), 0);
        Assert.assertEquals(fooWithAnnotationService.findById(fooWithAnnotation.getId()), fooWithAnnotation);
    }

    @Test
    @Transactional
    public void saveAll() {
        List<Long> idList = Arrays.asList(1001L, 1002L);
        final List<Foo> fooList = new ArrayList<>();
        idList.forEach(id -> {
            Foo foo = new Foo();
            foo.setId(id);
            foo.setDel(0);
            fooList.add(foo);
        });

        Assert.assertEquals(fooService.saveAll(fooList), idList.size());
        List<Foo> fooNewList = fooService.findAllById(idList);
        Assert.assertEquals(fooNewList.size(), fooList.size());
    }

    @Test
    @Transactional
    public void updateByColumn() {
        Foo fooTargetProperty = new Foo();
        fooTargetProperty.setStringValue("updateSuccess");
        FooWithAnnotation foo2targetProperty = new FooWithAnnotation();
        foo2targetProperty.setStringValue("updateSuccess");

        fooService.updateByColumn(fooTargetProperty, Foo.ID, 3L);
        Assert.assertEquals(fooService.findById(3L).getStringValue(), fooTargetProperty.getStringValue());

        fooWithAnnotationService.updateByColumn(foo2targetProperty, Foo.ID, 3L);
        Assert.assertEquals(fooWithAnnotationService.findById(3L).getStringValue(), foo2targetProperty.getStringValue());

        // 其他记录不能修改
        Assert.assertNotEquals(fooService.findById(1L).getStringValue(), fooTargetProperty.getStringValue());
        Assert.assertNotEquals(fooWithAnnotationService.findById(1L).getStringValue(), foo2targetProperty.getStringValue());
    }

    @Test
    @Transactional
    public void updateById() {
        Foo fooTargetProperty = new Foo();
        fooTargetProperty.setStringValue("updateSuccess");
        FooWithAnnotation foo2targetProperty = new FooWithAnnotation();
        foo2targetProperty.setStringValue("updateSuccess");

        fooService.updateById(fooTargetProperty, 3L);
        Assert.assertEquals(fooService.findById(3L).getStringValue(), fooTargetProperty.getStringValue());

        fooWithAnnotationService.updateById(foo2targetProperty, 3L);
        Assert.assertEquals(fooWithAnnotationService.findById(3L).getStringValue(), foo2targetProperty.getStringValue());

        // 其他记录不能修改
        Assert.assertNotEquals(fooService.findById(1L).getStringValue(), fooTargetProperty.getStringValue());
        Assert.assertNotEquals(fooWithAnnotationService.findById(1L).getStringValue(), foo2targetProperty.getStringValue());
    }

    @Test
    @Transactional
    public void updateColumnById() {
        String targetProperty = "111";

        fooService.updateColumnById(Foo.STRING_VALUE, targetProperty, 1L);
        Assert.assertEquals(fooService.findById(1L).getStringValue(), targetProperty);

        fooWithAnnotationService.updateColumnById(FooWithAnnotation.STRING_VALUE, targetProperty, 1L);
        Assert.assertEquals(fooWithAnnotationService.findById(1L).getStringValue(), targetProperty);

        // 其他记录不能修改
        Assert.assertNotEquals(fooService.findById(3L).getStringValue(), targetProperty);
        Assert.assertNotEquals(fooWithAnnotationService.findById(3L).getStringValue(), targetProperty);
    }

    @Test
    @Transactional
    public void update() {
        String targetProperty = "111";

        Foo foo = new Foo();
        foo.setStringValue(targetProperty);
        Condition condition = new Condition();
        condition.eq(Foo.ID, 1L);
        fooService.update(foo, condition);
        Assert.assertEquals(fooService.findById(1L).getStringValue(), foo.getStringValue());

        FooWithAnnotation fooWithAnnotation = new FooWithAnnotation();
        fooWithAnnotation.setStringValue(targetProperty);
        condition = new Condition();
        condition.eq(Foo.ID, 1L);
        fooWithAnnotationService.update(fooWithAnnotation, condition);
        Assert.assertEquals(fooWithAnnotationService.findById(1L).getStringValue(), fooWithAnnotation.getStringValue());

        // 其他记录不能修改
        Assert.assertNotEquals(fooService.findById(3L).getStringValue(), targetProperty);
        Assert.assertNotEquals(fooWithAnnotationService.findById(3L).getStringValue(), targetProperty);
    }

}
