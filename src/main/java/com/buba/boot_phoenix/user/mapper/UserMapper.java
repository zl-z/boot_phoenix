package com.buba.boot_phoenix.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zl,
 * Date:    2022/6/18 13:36,
 * Version: IntelliJ IDEA 2021.2.1
 */
@Mapper //被springBoot管理 注入容器
//BaseMapper<User>包含简单的CRUD
public interface UserMapper extends BaseMapper<User> {

   /* @Select("select * from user")
    List<User> userList();

    @Insert("INSERT INTO user (name, password) VALUES (#{username}, #{password})")
    int insertUser(User user);*/
}
