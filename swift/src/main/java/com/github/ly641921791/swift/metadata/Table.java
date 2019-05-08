package com.github.ly641921791.swift.metadata;

import com.github.ly641921791.swift.annotations.TableClass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表信息
 *
 * @author ly
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Table {

    /**
     * 表名
     */
    private String name;

    /**
     * 表格列
     */
    private List<Column> columns = new ArrayList<>();

    private TableClass tableClassAnnotation;

    private boolean useGeneratedKeys;

    private String keyProperty;

    private String keyColumn;

    private String deleteColumn;

    private String deleteValue;

    private String existsValue;

    public void addColumn(Column column) {
        columns.add(column);
    }

}
