package com.buba.boot_phoenix.grade.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/18 16:57,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class GradePageVO {

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
     * 班主任姓名
     */
    private String classTeacherName;

    /**
     * 讲师姓名
     */
    private String lecturerName;
}
