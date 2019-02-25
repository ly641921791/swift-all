package com.swift.custom.mapper;

import com.swift.custom.metadata.Table;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 查询类型方法解析器
 *
 * @author ly
 * @since 2019-02-25 14:11
 **/
public abstract class AbstractSelectMethodResolver implements MapperMethodResolver {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    /**
     * 查询列
     *
     * @param table 表格对象
     * @return 表格列
     */
    protected String columns(Table table) {
        StringBuilder sql = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.getSelectValue().isEmpty()) {
                sql.append(",`").append(column.getName()).append("`");
            } else {
                sql.append(",(").append(column.getSelectValue()).append(") AS `").append(column.getName()).append("`");
            }
        });
        return sql.substring(1);
    }

}
