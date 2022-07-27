package com.buba.boot_phoenix.validation.entity;

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
 * 
 * </p>
 *
 * @author ZL
 * @since 2022-07-02
 */
@Getter
@Setter
@TableName("validation")
public class Validation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 验证码
     */
    @TableField("code")
    private String code;

    /**
     * 验证类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 过期时间
     */
    @TableField("time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date time;


}
