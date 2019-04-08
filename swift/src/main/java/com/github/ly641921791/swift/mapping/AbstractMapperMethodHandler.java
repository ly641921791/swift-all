package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;

/**
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractMapperMethodHandler implements MapperMethodHandler {

    protected SwiftConfiguration configuration;
    protected Table table;

    @Override
    public void init(SwiftConfiguration configuration, Table table) {
        this.configuration = configuration;
        this.table = table;
    }

    protected void selectClause(StringBuilder statement) {

    }

    protected void whereClause(StringBuilder statement) {

    }

    protected void deleteClause(StringBuilder statement) {
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            statement.append("AND ").append(table.getDeleteColumn()).append(" = ").append(table.getExistsValue());
        }
    }

}
