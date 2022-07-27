package com.buba.boot_phoenix.grade.service;

import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.grade.dto.GradeFullDTO;
import com.buba.boot_phoenix.grade.dto.StuDTO;
import com.buba.boot_phoenix.grade.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buba.boot_phoenix.grade.vo.GradeAddVO;
import com.buba.boot_phoenix.grade.vo.GradePageVO;
import com.buba.boot_phoenix.grade.vo.GradeStuAddVO;
import com.buba.boot_phoenix.grade.vo.StuViewVO;

import java.util.List;

/**
 * <p>
 * 班级表 服务类
 * </p>
 *
 * @author ZL
 * @since 2022-07-18
 */
public interface GradeService extends IService<Grade> {

    /**
     * 功能描述: 模糊查-分页 班级信息
     *
     * @param gradePageVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/18 16:56
     */
    CommonResult listGradeInfo(GradePageVO gradePageVO);

    /**
     * 功能描述: 更新班级学生数量
     *
     * @param gradeName
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/26 16:59
     */
    CommonResult getStuNum(String gradeName);

    /**
     * 功能描述: 班主任信息
     *
     * @param 
     * @return  com.buba.boot_phoenix.common.CommonResult 
     * @throws  
     * @Author: zl
     * @Date:   2022/7/19 9:30
     */
    CommonResult getTeacher();

    /**
     * 功能描述: 讲师信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult 
     * @throws
     * @Author: zl
     * @Date:   2022/7/19 9:31
     */
    CommonResult getLecturerName();

    /**
     * 功能描述: 教室信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/19 15:31
     */
    CommonResult getClassRoom();

    /**
     * 功能描述: 添加班级信息
     *
     * @param grade
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 8:51
     */
    CommonResult insertGrade(Grade grade);

    /**
     * 功能描述: 编辑班级信息
     *
     * @param gradeAddVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 10:33
     */
    CommonResult updateGrade(GradeAddVO gradeAddVO);

    /**
     * 功能描述: 批量删除班级信息
     *
     * @param ids
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 14:31
     */
    CommonResult delGradeInfo(List<String> ids);

    /**
     * 功能描述: 查询班级学生信息
     *
     * @param stuViewVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.StuDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/21 16:25
     */
    CommonResult getStuInfo(StuViewVO stuViewVO);

    /**
     * 功能描述: 逻辑删除班级学生信息
     *
     * @param studentId
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/22 16:00
     */
    CommonResult delClassStu(List<String> studentId);

    /**
     * 功能描述: 班级学生表添加
     *
     * @param gradeStuAddVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/25 21:10
     */
    CommonResult insertGradeStudent(GradeStuAddVO gradeStuAddVO);

}
