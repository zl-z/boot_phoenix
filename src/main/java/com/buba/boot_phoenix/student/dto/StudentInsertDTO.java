package com.buba.boot_phoenix.student.dto;

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
public class StudentInsertDTO {

    /**
     * 学生ID
     */
    @TableField("student_id")
    @Alias("学生ID") //导入 / 导出 中文映射
    private String studentId;

    /**
     * 学号
     */
    @TableField("student_num")
    @Alias("学号")
    private String studentNum;

    /**
     * 学生姓名
     */
    @TableField("student_name")
    @Alias("学生姓名")
    private String studentName;

    /**
     * 邮箱
     */
    @TableField("email")
    @Alias("邮箱")
    private String email;

    /**
     * 密码
     */
    @TableField("password")
    @Alias("密码")
    private String password;

    /**
     * 手机号
     */
    @TableField("mobile")
    @Alias("手机号")
    private String mobile;

    /**
     * 性别 F-女生， M-男生
     */
    @TableField("sex")
    @Alias("性别")
    private String sex;

    /**
     * 年龄
     */
    @TableField("age")
    @Alias("年龄")
    private Integer age;

    /**
     * 证件类型
     */
    @TableField("id_type")
    @Alias("证件类型")
    private String idType;

    /**
     * 证件号码
     */
    @TableField("id_num")
    @Alias("证件号码")
    private String idNum;

    /**
     * 出生日期
     */
    @TableField("birthday")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @Alias("出生日期")
    private Date birthday;

    /**
     * 籍贯
     */
    @TableField("native_place")
    @Alias("籍贯")
    private String nativePlace;

    /**
     * 省
     */
    @TableField("provinces_id")
    @Alias("省")
    private String provincesId;

    /**
     * 市
     */
    @TableField("cities_id")
    @Alias("市")
    private String citiesId;

    /**
     * 县
     */
    @TableField("areas_id")
    @Alias("县")
    private String areasId;

    /**
     * 详细地址
     */
    @TableField("detail_addr")
    @Alias("详细地址")
    private String detailAddr;

    /**
     * 头像
     */
    @TableField("avatar_url")
    @Alias("头像")
    private String avatarUrl;

    /**
     * Token
     */
    private String token;

}
