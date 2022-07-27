package com.buba.boot_phoenix.employee.mapper;

import com.buba.boot_phoenix.employee.dto.EmployeeLoginDTO;
import com.buba.boot_phoenix.employee.entity.EmployeeInfo;
import com.buba.boot_phoenix.employee.entity.EmployeeRegister;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.student.entity.StudentRegister;
import com.buba.boot_phoenix.student.vo.UserLoginVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 职员注册表 Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Mapper
public interface EmployeeRegisterMapper extends BaseMapper<EmployeeRegister> {

    /**
     * 功能描述: 教师登录
     *
     * @param email
     * @return  com.buba.boot_phoenix.employee.dto.EmployeeLoginDTO
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 14:35
     */
    EmployeeLoginDTO getTeacherLogin(String email);

    /**
     * 功能描述: 重置教师密码
     *
     * @param userLoginVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/7/3 17:51
     */
    int updateTeaPass(UserLoginVO userLoginVO);

    /**
     * 功能描述: 查询登录老师信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.student.entity.StudentRegister
     * @throws
     * @Author: zl
     * @Date:   2022/7/5 10:03
     */
    EmployeeInfo getTeaInfo(String studentId);

    /**
     * 功能描述: 编辑教师个人信息
     *
     * @param employeeInfo
     * @return  com.buba.boot_phoenix.employee.entity.EmployeeInfo
     * @throws
     * @Author: zl
     * @Date:   2022/7/7 11:20
     */
    EmployeeInfo updateTea(EmployeeInfo employeeInfo);

}
