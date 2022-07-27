package com.buba.boot_phoenix.provinces.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 省份信息表
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
@Getter
@Setter
@TableName("provinces")
public class Provinces implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("provinceid")
    private String provinceId;

    @TableField("province")
    private String province;


}
