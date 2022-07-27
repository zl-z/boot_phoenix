package com.buba.boot_phoenix.classroom.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 教室管理
 * </p>
 *
 * @author ZL
 * @since 2022-07-12
 */
@Getter
@Setter
@TableName("classroom")
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    @Alias("主键ID") //导入 / 导出 中文映射
    private Integer pkId;

    /**
     * 区域
     */
    @TableField("area")
    @Alias("区域") //导入 / 导出 中文映射
    private String area;

    /**
     * 楼号
     */
    @TableField("building_no")
    @Alias("楼号") //导入 / 导出 中文映射
    private String buildingNo;

    /**
     * 楼层
     */
    @TableField("building_floor")
    @Alias("楼层") //导入 / 导出 中文映射
    private String buildingFloor;

    /**
     * 教室id
     */
    @TableField("classroom_id")
    @Alias("教室Id") //导入 / 导出 中文映射
    private String classroomId;

    /**
     * 所在教室编号
     */
    @TableField("classroom_number")
    @Alias("教室编号") //导入 / 导出 中文映射
    private String classroomNumber;

    /**
     * 教室名称
     */
    @TableField("classroom_name")
    @Alias("教室名称") //导入 / 导出 中文映射
    private String classroomName;

    /**
     * 容纳人数
     */
    @TableField("galleryful")
    @Alias("容纳人数") //导入 / 导出 中文映射
    private Integer galleryful;

    /**
     * 使用状态 0-使用 1-未使用 2-维修中
     */
    @TableField("use_status")
    @Alias("使用状态") //导入 / 导出 中文映射
    private String useStatus;

    /**
     * 删除标志 0-正常 1-删除
     */
    @TableField("delete_flag")
    @Alias("删除标志") //导入 / 导出 中文映射
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @Alias("创建时间") //导入 / 导出 中文映射
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @Alias("修改时间") //导入 / 导出 中文映射
    private LocalDateTime updateTime;

    /**
     * 说明
     */
    @TableField("remark")
    @Alias("说明") //导入 / 导出 中文映射
    private String remark;


}
