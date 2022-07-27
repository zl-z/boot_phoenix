package com.buba.boot_phoenix.validation.service;

import cn.hutool.core.date.DateTime;
import com.buba.boot_phoenix.validation.entity.Validation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZL
 * @since 2022-07-02
 */
public interface ValidationService extends IService<Validation> {

    /**
     * 功能描述: 验证码信息添加到数据库
     *
     * @param email
     * @param code
     * @param type
     * @param offsetMinute
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 19:46
     */
    void saveCode(String email, String code, Integer type, DateTime offsetMinute);
}
