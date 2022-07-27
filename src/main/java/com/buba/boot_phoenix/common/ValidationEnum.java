package com.buba.boot_phoenix.common;

/**
 * @Author: zl,
 * Date:    2022/7/2 19:38,
 * Version: IntelliJ IDEA 2021.2.1
 * ***枚举类
 */
public enum ValidationEnum {
//验证类型：  验证码               忘记密码
    LOGIN(1 ), FORGET_PASS(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    ValidationEnum(Integer type) {
        this.type = type;
    }
}
