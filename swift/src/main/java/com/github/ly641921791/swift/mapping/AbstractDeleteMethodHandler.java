package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.metadata.Table;
import com.github.ly641921791.swift.session.SwiftConfiguration;
import com.github.ly641921791.swift.util.StringUtils;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author ly
 * @since 1.0.0
 **/
public abstract class AbstractDeleteMethodHandler extends AbstractMapperMethodHandler {

    protected SqlCommandType sqlCommandType = SqlCommandType.DELETE;

    @Override
    public void init(SwiftConfiguration configuration, Table table) {
        super.init(configuration, table);
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            sqlCommandType = SqlCommandType.UPDATE;
        }
    }

    @Override
    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    @Override
    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append(TAG_SCRIPT_OPEN);
        if (StringUtils.isNotEmpty(table.getDeleteColumn(), table.getExistsValue())) {
            // UPDATE table SET WHERE
            statement.append("UPDATE ").append(table.getName()).append(' ');
            statement.append(TAG_SET_OPEN);
            statement.append(String.format("%s = %s", table.getDeleteColumn(), table.getDeleteValue()));
            statement.append(TAG_SET_CLOSE);
        } else {
            // DELETE FROM table WHERE
            statement.append("DELETE FROM ").append(table.getName()).append(' ');
        }
        whereClause(statement);
        statement.append(TAG_SCRIPT_CLOSE);
        return statement.toString();
    }

}
