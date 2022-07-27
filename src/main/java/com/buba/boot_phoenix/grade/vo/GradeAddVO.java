package com.buba.boot_phoenix.grade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/20 08:50,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class GradeAddVO {
    /**
     * 主键
     */
    private Integer pkId;

    /**
     * 班级ID
     */
    private String gradeId;

    /**
     * 班级新ID
     */
    private String gradeNewId;

    /**
     * 班级名称
     */
    private String gradeName;

    /**
     * 班主任ID
     */
    private String classTeacherId;

    /**
     * 班主任姓名
     */
    private String classTeacherName;

    /**
     * 讲师ID
     */
    private String lecturerId;

    /**
     * 讲师姓名
     */
    private String lecturerName;

    /**
     * 学生数量
     */
    private Integer studentsNumber;

    /**
     * 所在教室ID
     */
    private String classroomClassroomId;

    /**
     * 所在教室编号
     */
    private String classroomClassroomNumber;
}
