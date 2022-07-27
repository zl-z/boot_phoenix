package com.buba.boot_phoenix.employee.service;

import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.employee.dto.EmployeeLoginDTO;
import com.buba.boot_phoenix.employee.entity.EmployeeRegister;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buba.boot_phoenix.student.vo.UserLoginVO;

/**
 * <p>
 * 职员注册表 服务类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
public interface EmployeeRegisterService extends IService<EmployeeRegister> {

    /**
     * 功能描述: 教师登录
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.employee.dto.EmployeeLoginDTO
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 14:35
     */
    CommonResult getTeacherLogin(UserLoginVO userLoginVO);

}
