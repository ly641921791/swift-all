package com.github.ly641921791.swift.test.table;

import com.github.ly641921791.swift.core.annotation.TableClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ly
 * @since 2019-03-13 09:59
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableClass(deleteColumn = "del", deleteValue = "1", existsValue = "0")
public class FooWithAnnotation extends Foo {
}
