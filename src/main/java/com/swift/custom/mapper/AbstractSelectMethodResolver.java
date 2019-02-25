package com.swift.custom.mapper;

import com.swift.custom.metadata.Table;
import com.swift.session.SwiftConfiguration;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.util.StringUtils;

/**
 * 查询类型方法解析器
 *
 * @author ly
 * @since 2019-02-25 14:11
 **/
public abstract class AbstractSelectMethodResolver implements MapperMethodResolver {

    @Override
    public SqlCommandType getSqlCommandType() {
        return SqlCommandType.SELECT;
    }

    @Override
    public String buildSqlScript(Table table, SwiftConfiguration configuration) {
        String script = doBuildSqlScript(table, configuration);
        return afterBuildSqlScript(script, table, configuration);
    }

    /**
     * 生成Sql Script
     *
     * @param table         表格对象
     * @param configuration 配置文件
     * @return Sql Script
     */
    protected abstract String doBuildSqlScript(Table table, SwiftConfiguration configuration);

    /**
     * 后置处理Sql Script
     *
     * @param script        script
     * @param table         表格对象
     * @param configuration 配置文件
     * @return script
     */
    protected String afterBuildSqlScript(String script, Table table, SwiftConfiguration configuration) {
        if (StringUtils.isEmpty(table.getDeleteColumn())) {
            return script;
        }
        return script.replace("</script>", " AND " + table.getDeleteColumn() + " = 1</script>");
    }

    /**
     * 查询列
     *
     * @param table 表格对象
     * @return 表格列
     */
    protected String columns(Table table) {
        StringBuilder sql = new StringBuilder();
        table.getColumns().forEach(column -> {
            if (column.getSelectValue().isEmpty()) {
                sql.append(",`").append(column.getName()).append("`");
            } else {
                sql.append(",(").append(column.getSelectValue()).append(") AS `").append(column.getName()).append("`");
            }
        });
        return sql.substring(1);
    }

}
