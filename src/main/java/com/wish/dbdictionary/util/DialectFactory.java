package com.wish.dbdictionary.util;

public class DialectFactory {
    /**
     * 根据db类型获取数据库方言
     * @param dbType
     * @return
     */
    public static Dialect getDialect(String dbType) {
        DialectEnum dialectEnum = DialectEnum.getdialect(dbType);
        if(dialectEnum == null) {
            return null;
        }
        switch (dialectEnum) {
            case MYSQL_DIALECT:
                return new MysqlDialect();
        }
        return null;
    }
}
