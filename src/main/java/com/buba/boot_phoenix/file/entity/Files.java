package com.buba.boot_phoenix.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZL
 * @since 2022-07-08
 */
@Getter
@Setter
@TableName("file")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名称
     */
    @TableField("name")
    private String name;

    /**
     * 文件类型
     */
    @TableField("type")
    private String type;

    /**
     * 文件大小(KB)
     */
    @TableField("size")
    private Long size;

    /**
     * 下载链接
     */
    @TableField("url")
    private String url;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 是否禁用链接
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 文件md5
     */
    @TableField("md5")
    private String md5;


}
