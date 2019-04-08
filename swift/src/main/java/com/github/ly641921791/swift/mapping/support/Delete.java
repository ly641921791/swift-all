package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractDeleteMethodHandler;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * Target sql script ： <script>DELETE FROM table <where>${c.where}</where></script>
 * Target sql script ： <script>UPDATE table SET column = 0 WHERE </script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class Delete extends AbstractDeleteMethodHandler {

    @Override
    public String getStatement() {
        if (SqlCommandType.UPDATE.equals(getSqlCommandType())) {
            return super.getStatement();
        }
        return super.getStatement().replace("</script>", " <where>${c.where}</where></script>");
    }

    @Override
    protected void whereClause(StringBuilder statement) {
        deleteClause(statement);
    }

}
