package com.buba.boot_phoenix.student.vo;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zl,
 * Date:    2022/6/19 19:39,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class StudentInsertVO {

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 学号
     */
    private String studentNum;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别 F-女生， M-男生
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 证件号码
     */
    private String idNum;

    /**
     * 出生日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 省
     */
    private String provincesId;

    /**
     * 市
     */
    private String citiesId;

    /**
     * 县
     */
    private String areasId;

    /**
     * 详细地址
     */
    private String detailAddr;

    /**
     * 头像
     */
    private String avatarUrl;
}
