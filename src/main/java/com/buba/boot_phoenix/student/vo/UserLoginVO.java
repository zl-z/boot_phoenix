package com.buba.boot_phoenix.student.vo;

import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/6/18 17:48,
 * Version: IntelliJ IDEA 2021.2.1
 */
//主要对应展示界面显示的数据对象，用一个VO对象来封装整个界面展示所需要的对象数据
@Data
public class UserLoginVO {
    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 邮箱验证码
     */
    private String code;

    /**
     * 邮箱验证类型
     */
    private Integer type;

    /**
     * 用户状态
     */
    private String userCode;
}
