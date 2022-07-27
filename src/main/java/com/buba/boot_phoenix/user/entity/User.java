package com.buba.boot_phoenix.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * @Author: zl,
 * Date:    2022/6/18 13:36,
 * Version: IntelliJ IDEA 2021.2.1
 * @Data   //Getter / Setter
 * @NoArgsConstructor //类无参构造
 * @AllArgsConstructor//有类参构造
 * public class User implements Serializable {//实体类序列化，便于存储与传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")//数据库表名
public class User implements Serializable {
    @TableId(value = "u_id", type = IdType.AUTO)//字段名 类型以数据库为准
    private Integer id;
    @TableField(value = "u_name")//字段名
    private String name;
    @TableField(value = "u_password")//字段名
    private String password;
}
