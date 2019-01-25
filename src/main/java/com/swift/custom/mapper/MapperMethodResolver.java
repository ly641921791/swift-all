package com.swift.custom.mapper;

import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * Mapper方法解析器
 *
 * @author ly
 */
public interface MapperMethodResolver {

    SqlCommandType getSqlCommandType();

    String buildSql(Table table, SwiftConfiguration configuration);

}
