package com.buba.boot_phoenix.student.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 学生注册表
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Getter
@Setter
@TableName("student_register")
public class StudentRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

    /**
     * 学生ID
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 状态 0-正常, 1-停用, 2-停用
     */
    @TableField("status")
    private String status;

    /**
     * 删除标识 0-正常 1-删除
     */
    @TableField("delete_flag")
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date updateTime;


}
