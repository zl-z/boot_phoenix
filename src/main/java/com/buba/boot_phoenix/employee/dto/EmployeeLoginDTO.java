package com.buba.boot_phoenix.employee.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/2 14:32,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class EmployeeLoginDTO {
    /**
     * 员工ID
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 员工编号
     */
    @TableField("employee_no")
    private String employeeNo;

    /**
     * 员工姓名
     */
    @TableField("employee_name")
    private String employeeName;

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
     * 手机号
     */
    @TableField("phone_num")
    private String phoneNum;

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
