package com.buba.boot_phoenix.employee.service.impl;

import com.buba.boot_phoenix.employee.entity.EmployeeInfo;
import com.buba.boot_phoenix.employee.mapper.EmployeeInfoMapper;
import com.buba.boot_phoenix.employee.service.EmployeeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表(witkey_user_info) 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Service
public class EmployeeEmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo> implements EmployeeInfoService {

}
