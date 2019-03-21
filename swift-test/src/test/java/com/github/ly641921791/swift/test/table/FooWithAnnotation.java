package com.github.ly641921791.swift.test.table;

import com.github.ly641921791.swift.core.annotation.ColumnField;
import com.github.ly641921791.swift.core.annotation.TableClass;
import lombok.Data;

/**
 * @author ly
 * @since 2019-03-13 09:59
 **/
@Data
@TableClass(tableName = "foo2", deleteColumn = "del", deleteValue = "1", existsValue = "0")
public class FooWithAnnotation {

    public static final String ID = "id";
    public static final String STRING_VALUE = "string_value2";

    private Long id;

    @ColumnField(columnName = "string_value2")
    private String stringValue;

    private Integer del;

    @ColumnField(exists = false, selectValue = "1")
    private String notExistsColumn;

}
