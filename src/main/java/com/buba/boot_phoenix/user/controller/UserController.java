package com.buba.boot_phoenix.user.controller;

import com.buba.boot_phoenix.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zl,
 * Date:    2022/6/18 13:36,
 * Version: IntelliJ IDEA 2021.2.1
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 功能描述: 插入修改@PostMapping()请求，查询get
     * 删除DeleteMapping() 参数注解
     */

    @DeleteMapping("/{id}")
    public String index(@PathVariable Integer id){
        return "ok";
    }
}
