package com.wish.dbdictionary.util;

public enum DialectEnum {

    MYSQL_DIALECT("mysql");

    private String code;


    DialectEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * 通过code获取枚举
     */
    public static DialectEnum getdialect(String code) {
        for (DialectEnum dialectEnum : values()) {
            if (dialectEnum.getCode().equals(code)) {
                return dialectEnum;
            }
        }
        return null;
    }
}
