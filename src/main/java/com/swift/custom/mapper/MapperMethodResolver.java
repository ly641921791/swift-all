package com.swift.custom.mapper;

import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * Mapper方法解析器
 *
 * @author ly
 */
public interface MapperMethodResolver {

    String VALUE_PARAM = "v";

    String COLUMN_PARAM = "c";

    String CONDITION_PARAM = "c";

    String WHERE_TAG = "<where>${" + CONDITION_PARAM + ".where}</where>";

    /**
     * 获得SqlCommandType
     *
     * @return SqlCommandType
     */
    SqlCommandType getSqlCommandType();

    /**
     * 生成Sql Script
     *
     * @param table         table
     * @param configuration 配置文件
     * @return Sql Script
     */
    String buildSqlScript(Table table, SwiftConfiguration configuration);

    default KeyGenerator getKeyGenerator(Table table) {
        return NoKeyGenerator.INSTANCE;
    }

    default String getKeyProperty(Table table) {
        return null;
    }

    default String getKeyColumn(Table table) {
        return null;
    }

}
