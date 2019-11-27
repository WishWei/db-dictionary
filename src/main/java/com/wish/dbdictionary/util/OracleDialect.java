package com.wish.dbdictionary.util;

/**
 * @program: db-dictionary
 * @description: oracle方言
 * @author: wish
 * @create: 2019-11-16 20:53
 **/
public class OracleDialect implements Dialect{
    @Override
    public String getDriver() {
        return "oracle.jdbc.driver.OracleDriver";
    }

    @Override
    public String getTableListSql() {
        return "select table_name,comments from user_tab_comments  where exists (select ? from dual)";
    }

    @Override
    public String getTableColumnSql() {
        return "SELECT a.column_name,a.data_type,a.data_length,a.nullable,b.COMMENTS\n" +
                "  FROM all_tab_cols a left join user_col_comments b on a.table_name = b.table_name and a.column_name = b.column_name\n" +
                " WHERE a.owner=? and a.table_name = ?";
    }

    @Override
    public boolean upperCase() {
        return true;
    }
}
