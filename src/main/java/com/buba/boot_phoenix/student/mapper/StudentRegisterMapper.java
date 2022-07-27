package com.buba.boot_phoenix.student.mapper;

import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.student.dto.StudentInsertDTO;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.buba.boot_phoenix.student.entity.StudentRegister;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.student.vo.StudentInsertVO;
import com.buba.boot_phoenix.student.vo.StudentVO;
import com.buba.boot_phoenix.student.vo.UserLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学生注册表 Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 * Mybatis-Plus
 * 让xxxMapper接口继承BaseMapper接口即可。
 * BaseMapper<T>：泛型指定的就是当前Mapper接口所操作的实体类类型
 */
@Mapper
public interface StudentRegisterMapper extends BaseMapper<StudentRegister> {

    /**
     * 功能描述: 学生登录
     *
     * @param email
     * @return  com.buba.boot_phoenix.student.entity.StudentRegister
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 15:34
     */
    StudentLoginDTO getStudentLogin(String email);

    /**
     * 功能描述: 添加学生注册表信息
     *
     * @param studentInsertVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 20:02
     */
    int insertStudentRegister(StudentInsertVO studentInsertVO);

    /**
     * 功能描述: 添加学生表信息
     *
     * @param studentInsertVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 20:06
     */
    int insertStudentInfo(StudentInsertVO studentInsertVO);

    /**
     * 功能描述: 查询学生信息-分页
     *
     * @param studentVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 14:38
     */
    //分页公式：limit第一个参数 = (pageNum - 1) * pageSize	limit pageNum 开始位置(0)，pageSize 每页条数
    List<BasicInfo> listStudentPage(StudentVO studentVO);

    /**
     * 功能描述: 查询学生信息总条数
     *
     * @param studentVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 15:00
     */
    Integer selectTotal(StudentVO studentVO);

    /**
     * 功能描述: 修改学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/24 16:23
     */
    Integer updateStudentInfo(StudentInsertVO studentInsertVO);

    /**
     * 功能描述: 删除学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 15:58
     */
    Integer deleteStudentInfo(String studentId);

    /**
     * 功能描述: 批量删除学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 15:58
     */
    Integer listDelStudentInfo(List<String> studentId);

    /**
     * 功能描述: 全查学生信息 - 导出
     *
     * @param
     * @return  java.util.List<com.buba.boot_phoenix.student.vo.StudentInsertVO>
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 11:33
     */
    List<StudentInsertDTO> listStudentInfo();

    /**
     * 功能描述: 添加学生注册表信息 - 导入
     *
     * @param studentInsertVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 20:02
     */
    Integer insertListStudentRegister(List<StudentInsertDTO> studentInsertVO);

    /**
     * 功能描述: 添加学生表信息 - 导入
     *
     * @param studentInsertVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 20:06
     */
    Integer insertListStudentInfo(List<StudentInsertDTO> studentInsertVO);

    /**
     * 功能描述: 学生密码重置
     *
     * @param userLoginVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/7/3 17:13
     */
    int updateStuPass(UserLoginVO userLoginVO);

    /**
     * 功能描述: 查询登录学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.student.entity.StudentRegister
     * @throws
     * @Author: zl
     * @Date:   2022/7/5 10:03
     */
    StudentInsertDTO getStuInfo(String studentId);

    /**
     * 功能描述: 根据Token查询学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.student.dto.StudentLoginDTO
     * @throws
     * @Author: zl
     * @Date:   2022/7/7 20:09
     */
    StudentLoginDTO getTokenStu(String studentId);
}
