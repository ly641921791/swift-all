package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * Mapper方法解析器
 * <p>
 * P_A : param alias
 * T_ : tag
 *
 * @author ly
 */
public interface MapperMethodHandler {

    String P_A_VALUE = "v";

    String P_A_VALUES = "vs";

    String P_A_COLUMN = "c";

    String P_A_CONDITION = "c";

    String P_A_ID = "id";

    String T_WHERE = "<where>${" + P_A_CONDITION + ".where}</where>";

    String TAG_SCRIPT_OPEN = "<script>";

    String TAG_SCRIPT_CLOSE = "</script>";

    String TAG_WHERE_OPEN = "<where>";

    String TAG_WHERE_CLOSE = "</where>";

    String TAG_SET_OPEN = "<set>";

    String TAG_SET_CLOSE = "</set>";

    /**
     * Initial mapper method handler
     *
     * @param configuration configuration
     * @param table         table
     */
    void init(SwiftConfiguration configuration, Table table);

    /**
     * 获得SqlCommandType
     *
     * @return SqlCommandType
     */
    SqlCommandType getSqlCommandType();

    /**
     * 生成Sql Script
     *
     * @return Sql Script
     */
    String getStatement();

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
