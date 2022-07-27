package com.buba.boot_phoenix.grade.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/25 21:09,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class GradeStuAddVO {

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 班级ID
     */
    private String gradeId;

    /**
     * 班级名称
     */
    private String gradeName;

}
