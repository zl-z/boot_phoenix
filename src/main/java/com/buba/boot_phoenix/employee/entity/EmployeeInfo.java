package com.buba.boot_phoenix.employee.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工信息表(witkey_user_info)
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Getter
@Setter
@TableName("employee_info")
public class EmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

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
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别 F-女生
M-男生
     */
    @TableField("sex")
    private String sex;

    /**
     * 手机号
     */
    @TableField("phone_num")
    private String phoneNum;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 身高
     */
    @TableField("height")
    private Double height;

    /**
     * 家庭详细地址
     */
    @TableField("home_addr_detail")
    private String homeAddrDetail;

    /**
     * 工作详细地址
     */
    @TableField("work_addr_detail")
    private String workAddrDetail;

    /**
     * 家庭地址省ID
     */
    @TableField("home_provinceid")
    private String homeProvinceid;

    /**
     * 家庭地址省名称
     */
    @TableField("home_province")
    private String homeProvince;

    /**
     * 家庭地址市ID
     */
    @TableField("home_cityid")
    private String homeCityid;

    /**
     * 家庭地址市名称
     */
    @TableField("home_city")
    private String homeCity;

    /**
     * 家庭地址县(区)ID
     */
    @TableField("home_areaid")
    private String homeAreaid;

    /**
     * 家庭地址县(区)名称
     */
    @TableField("home_area")
    private String homeArea;

    /**
     * 工作地址省ID
     */
    @TableField("work_provinceid")
    private String workProvinceid;

    /**
     * 工作地址省名称
     */
    @TableField("work_province")
    private String workProvince;

    /**
     * 工作地址市ID
     */
    @TableField("work_cityid")
    private String workCityid;

    /**
     * 工作地址市名称
     */
    @TableField("work_city")
    private String workCity;

    /**
     * 工作地址市名称
     */
    @TableField("work_areaid")
    private String workAreaid;

    /**
     * 工作地址县(区)名称
     */
    @TableField("work_area")
    private String workArea;

    /**
     * 删除标识 0-正常 1-删除
     */
    @TableField("delete_flag")
    private String deleteFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @TableField("update_time")
    private Date updateTime;

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
