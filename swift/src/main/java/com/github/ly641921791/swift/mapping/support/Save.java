package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractMapperMethodHandler;
import com.github.ly641921791.swift.metadata.Column;
import com.github.ly641921791.swift.metadata.Table;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.List;

/**
 * TODO INSERT INTO foo ( del ) VALUES ( ? )   del加引号
 *
 * @author ly
 * @since 1.0.0
 **/
public class Save extends AbstractMapperMethodHandler {

    public static final String INSERT = "<script>INSERT INTO %s (%s) VALUES (%s)</script>";

    // TODO IGNORE 仅支持mysql数据库
    public static final String INSERT_MYSQL = "<script>INSERT <if test='ignore'>IGNORE</if> INTO %s (%s) VALUES (%s)</script>";

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.INSERT;
    }

    @Override
    public String getStatement() {
        List<Column> columnList = table.getColumns();

        StringBuilder cols = new StringBuilder("<trim prefix=\"\" prefixOverrides=\",\">");
        StringBuilder fs = new StringBuilder("<trim prefix=\"\" prefixOverrides=\",\">");

        columnList.forEach(column -> {
            // 不存在的列跳过
            if (!column.isExists()) {
                return;
            }
            String field = column.getJavaField().getName();
            cols.append(String.format("<if test='%s.%s!=null'>,`%s`</if>", ENTITY, field, column.getName()));
            fs.append(String.format("<if test='%s.%s!=null'>,#{entity.%s}</if>", ENTITY, field, field));
        });

        cols.append("</trim>");
        fs.append("</trim>");

        return String.format(INSERT, table.getName(), cols.toString(), fs.toString());
    }

    @Override
    public KeyGenerator getKeyGenerator(Table table) {
        return table.isUseGeneratedKeys() ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
    }

    @Override
    public String getKeyColumn(Table table) {
        return table.getKeyColumn();
    }

    @Override
    public String getKeyProperty(Table table) {
        // [Fix] 由于save方法是重载方法，多参数的情况下，keyProperty使用属性名是不够的，需要配合因此需要通过[entity.id]获得值
        return ENTITY + "." + table.getKeyProperty();
    }

}