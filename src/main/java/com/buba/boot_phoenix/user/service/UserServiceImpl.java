package com.buba.boot_phoenix.user.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zl,
 * Date:    2022/6/18 13:36,
 * Version: IntelliJ IDEA 2021.2.1
 */
public class UserServiceImpl implements UserService{
    @Override
    @Transactional//启用事务（配置事务管理器）
    public int addUser() {
        return 0;
    }
}
