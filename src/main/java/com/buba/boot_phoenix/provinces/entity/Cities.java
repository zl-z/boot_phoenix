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
 * 行政区域地州市信息表
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
@Getter
@Setter
@TableName("cities")
public class Cities implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("cityid")
    private String cityId;

    @TableField("city")
    private String city;

    @TableField("provinceid")
    private String provinceId;


}
