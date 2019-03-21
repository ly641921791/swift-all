package com.github.ly641921791.swift.core.mapper;

import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;

import java.io.Closeable;

/**
 * Mapper方法解析器
 * <p>
 * P_A : param alias
 * T_ : tag
 *
 * @author ly
 */
public interface MapperMethodResolver extends Closeable {

    String P_A_VALUE = "v";

    String P_A_VALUES = "vs";

    String P_A_COLUMN = "c";

    String P_A_CONDITION = "c";

    String P_A_ID = "id";

    String T_WHERE = "<where>${" + P_A_CONDITION + ".where}</where>";

    String TAG_SCRIPT_OPEN = "<script>";

    String TAG_SCRIPT_CLOSE = "</script>";

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

    @Override
    default void close() {
    }

}
