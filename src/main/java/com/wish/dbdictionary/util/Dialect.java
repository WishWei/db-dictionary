package com.wish.dbdictionary.util;

public interface Dialect {
    /**
     * 获取驱动
     * @return
     */
    String getDriver();

    /**
     * 获取表信息
     * @return
     */
    String getTableListSql();

    /**
     * 获取列信息
     * @return
     */
    String getTableColumnSql();
}
