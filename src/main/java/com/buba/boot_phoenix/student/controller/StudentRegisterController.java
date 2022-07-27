package com.buba.boot_phoenix.student.controller;

import cn.hutool.core.util.StrUtil;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.employee.service.EmployeeRegisterService;
import com.buba.boot_phoenix.exception.ServiceException;
import com.buba.boot_phoenix.student.vo.StudentInsertVO;
import com.buba.boot_phoenix.student.vo.UserLoginVO;
import com.buba.boot_phoenix.student.service.StudentRegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 学生注册表 前端控制器
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@CrossOrigin
@RestController
@RequestMapping("/register")
public class StudentRegisterController {
    Logger log = LogManager.getLogger();
    String str = "0"; //0学生 1老师

    @Resource
    private StudentRegisterService studentRegisterService;

    @Resource
    private EmployeeRegisterService employeeRegisterService;

    /**
     * 功能描述: 学生登录
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 17:00
     */
    @PostMapping("/getStudentLogin")
    public CommonResult getStudentLogin(@RequestBody UserLoginVO userLoginVO){
        if(str.equals(userLoginVO.getUserCode())){
            log.info("打印输出学生---邮箱：" + userLoginVO.getEmail());
            log.info("打印输出学生---密码：" + userLoginVO.getPassWord());
            return studentRegisterService.getStudentLogin(userLoginVO);
        }else {//老师
            log.info("打印输出老师---邮箱：" + userLoginVO.getEmail());
            log.info("打印输出老师---密码：" + userLoginVO.getPassWord());
            return employeeRegisterService.getTeacherLogin(userLoginVO);
        }
    }

    /**
     * 功能描述: 邮箱验证码登录
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 17:00
     */
    @PostMapping("/getLoginEmail")
    public CommonResult getLoginEmail(@RequestBody UserLoginVO userLoginVO){
        return studentRegisterService.getLoginEmail(userLoginVO);
    }

    /**
     * 功能描述: 发送邮箱验证码
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 17:00
     */
    @PostMapping("/sendEmailCode")
    public CommonResult sendEmailCode(@RequestBody UserLoginVO userLoginVO){
        if(null == userLoginVO || "".equals(userLoginVO)){
            throw new ServiceException("400", "参数错误");
        }
        studentRegisterService.sendEmailCode(userLoginVO);
        return CommonResult.ok();
    }

    /**
     * 功能描述: 重置密码
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 17:00
     */
    @PostMapping("/resetPass")
    public CommonResult resetPass(@RequestBody UserLoginVO userLoginVO){
        return studentRegisterService.resetPass(userLoginVO);
    }

    /**
     * 功能描述: 注册学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 17:00
     */
    @PostMapping("/insertStudentInfo")
    public CommonResult insertStudentInfo(@RequestBody StudentInsertVO studentInsertVO){
        return studentRegisterService.insertStudentInfo(studentInsertVO);
    }

    /**
     * 功能描述: 查询学生信息-分页
     *
     * @param studentPageVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 13:35
     */
    /*@PostMapping("/listStudentPage")
    public Map<String, Object>  listStudentPage(@RequestBody StudentPageVO studentPageVO){
        return studentRegisterService.listStudentPage(studentPageVO);
    }*/

    /**
     * 功能描述: 修改学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/24 16:23
     */
    @PostMapping("/updateStudentInfo")
    public CommonResult updateStudentInfo(@RequestBody StudentInsertVO studentInsertVO) {
        return studentRegisterService.updateStudentInfo(studentInsertVO);
    }

    /**
     * 功能描述: 删除学生信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 16:07
     */
    @PostMapping("/deleteStudentInfo")
    public CommonResult deleteStudentInfo(@RequestBody String studentId){
        log.info("***********"+studentId);
        return studentRegisterService.deleteStudentInfo(studentId);
    }

    /**
     * 功能描述: 批量删除学生信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 16:07
     */
    @PostMapping("/listDelStudentInfo")
    public CommonResult listDelStudentInfo(@RequestBody List<String> studentId){
        log.info("***********"+studentId);
        return studentRegisterService.listDelStudentInfo(studentId);
    }

    /**
     * 功能描述: Excel表格导出
     *
     * @param res
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 9:33
     */
    @GetMapping("/excelExport")
    public void excelExport(HttpServletResponse res) throws Exception {
        studentRegisterService.excelExport(res);
    }

    /**
     * 功能描述: Excel表格导入
     *
     * @param file
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 10:47
     */
    @PostMapping("/excelImport")
    public CommonResult excelImport(MultipartFile file) throws Exception{
        return studentRegisterService.excelImport(file);
    }

    /**
     * 功能描述: 查询登录个人信息
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/24 16:23
     */
    @PostMapping("/getUserInfo")
    public CommonResult getUserInfo(@RequestBody UserLoginVO userLoginVO) {
        return studentRegisterService.getUserInfo(userLoginVO);
    }

}
