package com.buba.boot_phoenix.employee.mapper;

import com.buba.boot_phoenix.employee.entity.EmployeeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.student.entity.StudentRegister;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息表(witkey_user_info) Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Mapper
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {

}
