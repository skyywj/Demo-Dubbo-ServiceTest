package com.sky.hrpro.entity;

/**
 * 对象类型
 *
 * @author CarryJey
 * @since 2018-02-02
 */
public enum ObjectType {
    DEMO("demo");

    private final String ojbType;

    ObjectType(String objType) {
        ojbType = objType;
    }

    @Override
    public String toString() {
        return ojbType;
    }
}
