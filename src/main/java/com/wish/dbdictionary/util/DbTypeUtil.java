package com.wish.dbdictionary.util;

/**
 * @program: db-dictionary
 * @description: 数据库类型工具类
 * @author: wish
 * @create: 2019-11-17 14:58
 **/
public class DbTypeUtil {
    /**
     * 从url中取的数据库类型
     * @param url
     * @return
     */
    public static String getDateType(String url) {
        String[] items = url.split(":");
        return items[1];
    }
}
