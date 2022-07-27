package com.buba.boot_phoenix.student.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/6/21 16:58,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class StudentVO {

    /**
     * 学生姓名
     */
    private String studentName;

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
     * 证件号码
     */
    private String idNum;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页条数
     */
    private Integer pageSize;

    /**
     * 是否入班 0-正常， 1-已入班
     */
    private Boolean theClass;
}
