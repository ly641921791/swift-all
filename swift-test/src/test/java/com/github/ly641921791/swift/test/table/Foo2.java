package com.github.ly641921791.swift.test.table;

import com.github.ly641921791.swift.annotations.TableClass;
import lombok.Data;

/**
 * 当注解标记为 @TableClass(useGeneratedKeys = true) 时，
 * save方法出现过执行失败的bug，新增此类进行验证
 *
 * @author ly
 * @since 1.0.0
 **/
@Data
@TableClass(useGeneratedKeys = true)
public class Foo2 {

    private Long id;

    private String stringValue2;

    private int like;

    private Integer del;

}
