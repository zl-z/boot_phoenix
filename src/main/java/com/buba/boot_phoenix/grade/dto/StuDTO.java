package com.buba.boot_phoenix.grade.dto;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/21 16:22,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class StuDTO {

    /**
     * 学生ID
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 学号
     */
    @TableField("student_num")
    private String studentNum;

    /**
     * 学生姓名
     */
    @TableField("student_name")
    private String studentName;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 性别 F-女生， M-男生
     */
    @TableField("sex")
    private String sex;

    /**
     * 证件类型
     */
    @TableField("id_type")
    private String idType;

    /**
     * 证件号码
     */
    @TableField("id_num")
    private String idNum;
    /**
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;
}
