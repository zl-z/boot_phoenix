package com.buba.boot_phoenix.grade.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: zl,
 * Date:    2022/7/18 16:20,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class GradeFullDTO {

    /**
     * 主键
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

    /**
     * 班级ID
     */
    @TableField("grade_id")
    private String gradeId;

    /**
     * 班级名称
     */
    @TableField("grade_name")
    private String gradeName;

    /**
     * 班主任ID
     */
    @TableField("class_teacher_id")
    private String classTeacherId;

    /**
     * 班主任姓名
     */
    @TableField("class_teacher_name")
    private String classTeacherName;

    /**
     * 讲师ID
     */
    @TableField("lecturer_id")
    private String lecturerId;

    /**
     * 讲师姓名
     */
    @TableField("lecturer_name")
    private String lecturerName;

    /**
     * 学生总数量
     */
    @TableField("students_number")
    private Integer studentsNumber;

    /**
     * 已有学生
     */
    private Integer already;

    /**
     * 所在教室ID
     */
    @TableField("classroom_classroom_id")
    private String classroomClassroomId;

    /**
     * 所在教室编号
     */
    @TableField("classroom_classroom_number")
    private String classroomClassroomNumber;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private LocalDateTime updateTime;
}
