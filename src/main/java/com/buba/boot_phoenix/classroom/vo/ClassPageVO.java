package com.buba.boot_phoenix.classroom.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zl,
 * Date:    2022/7/12 14:07,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class ClassPageVO {

    /**
     * 主键ID
     */
    private Integer pkId;

    /**
     * 区域
     */
    private String area;

    /**
     * 楼号
     */
    private String buildingNo;

    /**
     * 楼层
     */
    private String buildingFloor;

    /**
     * 教室id
     */
    private String classroomId;

    /**
     * 所在教室编号
     */
    private String classroomNumber;

    /**
     * 教室名称
     */
    private String classroomName;

    /**
     * 容纳人数
     */
    private Integer galleryful;

    /**
     * 使用状态 0-使用 1-未使用 2-维修中
     */
    private String useStatus;

    /**
     * 删除标志 0-正常 1-删除
     */
    private String deleteFlag;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date updateTime;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页条数
     */
    private Integer pageSize;

    /**
     * 教室人数 开始
     */
    private Integer begin;

    /**
     * 教室人数 结束
     */
    private Integer over;
}
