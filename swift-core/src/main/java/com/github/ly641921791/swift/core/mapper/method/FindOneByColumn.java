package com.github.ly641921791.swift.core.mapper.method;

import com.github.ly641921791.swift.core.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;

/**
 * @author ly
 * @since 1.0.0
 **/
public class FindOneByColumn extends FindAllByColumn {

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        return super.buildSqlScript(table, configuration).replace(TAG_SCRIPT_CLOSE, "LIMIT 1" + TAG_SCRIPT_CLOSE);
    }
}
