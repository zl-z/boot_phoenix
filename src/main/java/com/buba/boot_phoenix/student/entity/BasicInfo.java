package com.buba.boot_phoenix.student.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 学生基本信息表
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@TableName("student_basic_info")
@Data
public class BasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

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
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 性别 F-女生， M-男生
     */
    @TableField("sex")
    private String sex;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 证件类型
     */
    @TableField("id_type")
    private String idType;

    /**
     * 证件号码
     */
    @TableField("id_num")
    private String idNum;

    /**
     * 出生日期
     */
    @TableField("birthday")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 删除标志 0-正常， 1-删除
     */
    @TableField("delete_flag")
    private Boolean deleteFlag;

    /**
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;

    /**
     * 省
     */
    @TableField("provinces_id")
    private String provincesId;

    /**
     * 市
     */
    @TableField("cities_id")
    private String citiesId;

    /**
     * 县
     */
    @TableField("areas_id")
    private String areasId;

    /**
     * 详细地址
     */
    @TableField("detail_addr")
    private String detailAddr;

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

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 是否入班 0-正常， 1-已入班
     */
    @TableField("the_class")
    private Boolean theClass;



}
