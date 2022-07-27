package com.buba.boot_phoenix.file.vo;

import lombok.Data;

/**
 * @Author: zl,
 * Date:    2022/7/9 14:09,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Data
public class FileVO {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页条数
     */
    private Integer pageSize;
}
