package com.wish.dbdictionary.util;

/**
 * mysql方言
 */
public class MysqlDialect implements Dialect {

    @Override
    public String getDriver() {
        return "com.mysql.jdbc.Driver";
    }

    @Override
    public String getTableListSql() {
        return "select table_name,table_comment from information_schema.tables where table_schema=?";
    }

    @Override
    public String getTableColumnSql() {
        return "select * from information_schema.columns where table_schema=? and table_name=?";
    }
}
