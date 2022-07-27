package com.buba.boot_phoenix.validation.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buba.boot_phoenix.common.ValidationEnum;
import com.buba.boot_phoenix.validation.entity.Validation;
import com.buba.boot_phoenix.validation.mapper.ValidationMapper;
import com.buba.boot_phoenix.validation.service.ValidationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-07-02
 */
@Service
public class ValidationServiceImpl extends ServiceImpl<ValidationMapper, Validation> implements ValidationService {

    /**
     * 功能描述: 验证码信息添加到数据库
     *
     * @param email
     * @param code
     * @param type
     * @param expireDate
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 20:32
     */
    @Transactional
    @Override
    public void saveCode(String email, String code, Integer type, DateTime expireDate) {
        Validation validation = new Validation();
        validation.setEmail(email);
        validation.setCode(code);
        validation.setType(type);
        validation.setTime(expireDate);
//        存储之前删除之前同邮箱、同类型验证
        UpdateWrapper<Validation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("email", email);
        updateWrapper.eq("type", type);
        remove(updateWrapper);
//        再插入新的code
        save(validation);
    }
}
