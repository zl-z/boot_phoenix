package com.buba.boot_phoenix.student.service;

import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.student.vo.StudentInsertVO;
import com.buba.boot_phoenix.student.vo.UserLoginVO;
import com.buba.boot_phoenix.student.entity.StudentRegister;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 学生注册表 服务类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
public interface StudentRegisterService extends IService<StudentRegister> {

    /**
     * 功能描述: 学生登录
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.student.entity.StudentRegister
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 15:34
     */
    CommonResult getStudentLogin(UserLoginVO userLoginVO);

    /**
     * 功能描述: 邮箱验证码登录
     *
     * @param userLoginVO
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 17:17
     */
    CommonResult getLoginEmail(UserLoginVO userLoginVO);

    /**
     * 功能描述: 发送邮箱验证码
     *
     * @param userLoginVO
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 17:17
     */
    void sendEmailCode(UserLoginVO userLoginVO);

    /**
     * 功能描述: 重置密码
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/3 16:59
     */
    CommonResult resetPass(UserLoginVO userLoginVO);

    /**
     * 功能描述: 注册学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 20:28
     */
    CommonResult insertStudentInfo(StudentInsertVO studentInsertVO);

    /**
     * 功能描述: 查询学生信息-分页
     *
     * @param studentPageVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 14:38
     */
//    Map<String, Object> listStudentPage(StudentPageVO studentPageVO);

    /**
     * 功能描述: 修改学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/24 16:23
     */
    CommonResult updateStudentInfo(StudentInsertVO studentInsertVO);

    /**
     * 功能描述: 删除学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 15:58
     */
    CommonResult deleteStudentInfo(String studentId);

    /**
     * 功能描述: 批量删除学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 15:58
     */
    CommonResult listDelStudentInfo(List<String> studentId);

    /**
     * 功能描述: Excel表格导出
     *
     * @param res
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 8:45
     */
    void excelExport(HttpServletResponse res) throws Exception;

    /**
     * 功能描述: Excel表格导入
     *
     * @param file
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws  
     * @Author: zl
     * @Date:   2022/6/27 8:43
     */
    CommonResult excelImport(MultipartFile file) throws Exception;

    /**
     * 功能描述: 查询登录个人信息
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/5 10:02
     */
    CommonResult getUserInfo(UserLoginVO userLoginVO);
}
