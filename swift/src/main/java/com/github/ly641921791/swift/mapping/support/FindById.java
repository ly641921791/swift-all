package com.github.ly641921791.swift.mapping.support;

import com.github.ly641921791.swift.mapping.AbstractSelectMethodHandler;

import static com.github.ly641921791.swift.builder.TableBuilder.DEFAULT_KEY_COLUMN;
import static com.github.ly641921791.swift.builder.TableBuilder.DEFAULT_KEY_PROPERTY;

/**
 * Target sql script : <script>SELECT %s FROM %s WHERE %s = #{%s}</script>
 *
 * @author ly
 * @since 1.0.0
 **/
public class FindById extends AbstractSelectMethodHandler {

    @Override
    protected void whereClause(StringBuilder statement) {
        statement.append("WHERE ");
        if (table.getTableClassAnnotation() == null) {
            statement.append(String.format("`%s` = #{%s}", DEFAULT_KEY_COLUMN, DEFAULT_KEY_PROPERTY));
        } else {
            statement.append(String.format("`%s` = #{%s}", table.getTableClassAnnotation().keyColumn(), table.getTableClassAnnotation().keyProperty()));
        }
        deleteClause(statement);
    }

}
