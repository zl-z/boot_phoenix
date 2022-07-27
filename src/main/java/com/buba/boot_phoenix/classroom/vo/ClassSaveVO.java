package com.buba.boot_phoenix.classroom.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zl,
 * Date:    2022/7/14 15:35,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class ClassSaveVO {
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
     * 教室旧id
     */
    private String classroomId;

    /**
     * 教室新id
     */
    private String classroomNewId;

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

}
