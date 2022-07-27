package com.buba.boot_phoenix.grade.vo;

import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/21 17:07,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class StuViewVO {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页条数
     */
    private Integer pageSize;

    /**
     * 班级名称
     */
    private String gradeName;

    /**
     * 学生名称
     */
    private String studentName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;
}
