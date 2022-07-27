package com.buba.boot_phoenix.employee.service.impl;

import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.employee.dto.EmployeeLoginDTO;
import com.buba.boot_phoenix.employee.entity.EmployeeRegister;
import com.buba.boot_phoenix.employee.mapper.EmployeeRegisterMapper;
import com.buba.boot_phoenix.employee.service.EmployeeRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.vo.UserLoginVO;
import com.buba.boot_phoenix.utils.PasswordManagerUtil;
import com.buba.boot_phoenix.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 职员注册表 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Service
public class EmployeeRegisterServiceImpl extends ServiceImpl<EmployeeRegisterMapper, EmployeeRegister> implements EmployeeRegisterService {

    @Resource
    private EmployeeRegisterMapper employeeRegisterMapper;
    Map<String, Object> map = new HashMap<>(); //data对象

    @Override
    public CommonResult getTeacherLogin(UserLoginVO userLoginVO) {
        String str = "0"; //删除标识
        if(null == userLoginVO || "".equals(userLoginVO)){
            return CommonResult.error("500", "用户名或密码有误");
        }else{
            EmployeeLoginDTO employee = employeeRegisterMapper.getTeacherLogin(userLoginVO.getEmail());
            if(null != employee && PasswordManagerUtil.getMd5DigestAsHex(userLoginVO.getPassWord()).equals(employee.getPassword())){
                if(str.equals(employee.getDeleteFlag())){
//                  设置Token
                    employee.setToken(TokenUtils.genToken(employee.getEmployeeId(), employee.getPassword()));
                    map.put("teacher", employee);
                    return CommonResult.ok(map);
                }else{
                    return CommonResult.error("404", "非常抱歉，您的账户被停用了！");
                }
            }else{
                return CommonResult.error("500", "用户名或密码有误");
            }
        }
    }
}
