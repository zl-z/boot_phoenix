package com.buba.boot_phoenix.grade.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 班级表
 * </p>
 *
 * @author ZL
 * @since 2022-07-18
 */
@Getter
@Setter
@TableName("grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 学生数量
     */
    @TableField("students_number")
    private Integer studentsNumber;

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
     * 删除标志 0-正常 1-删除
     */
    @TableField("delete_flag")
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
