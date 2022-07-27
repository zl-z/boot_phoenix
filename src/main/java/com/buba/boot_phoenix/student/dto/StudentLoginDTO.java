package com.buba.boot_phoenix.student.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/6/18 17:01,
 * Version: IntelliJ IDEA 2021.2.1
 */
//当我们需要一个对象10个字段的内容，但这个对象总共有20个字段，我们不需要把整个PO对象全部字段传输到客户端，而是可以用DTO重新封装，传递到客户端。
@Data
public class StudentLoginDTO {
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
     * 删除标志 0-正常， 1-删除
     */
    @TableField("delete_flag")
    private String deleteFlag;

    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * Token
     */
    private String token;
}
