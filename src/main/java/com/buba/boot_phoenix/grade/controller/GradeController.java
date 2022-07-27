package com.buba.boot_phoenix.grade.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.exception.ServiceException;
import com.buba.boot_phoenix.grade.entity.Grade;
import com.buba.boot_phoenix.grade.service.GradeService;
import com.buba.boot_phoenix.grade.vo.GradeAddVO;
import com.buba.boot_phoenix.grade.vo.GradePageVO;
import com.buba.boot_phoenix.grade.vo.GradeStuAddVO;
import com.buba.boot_phoenix.grade.vo.StuViewVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 班级表 前端控制器
 * </p>
 *
 * @author ZL
 * @since 2022-07-18
 */
@CrossOrigin
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Resource
    private GradeService gradeService;

    /**
     * 功能描述: 模糊查-分页 班级信息
     *
     * @param gradePageVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/18 16:56
     */
    @PostMapping("/listGradeInfo")
    public CommonResult listGradeInfo(@RequestBody GradePageVO gradePageVO) {
        return gradeService.listGradeInfo(gradePageVO);
    }

    /**
     * 功能描述: 更新班级学生数量
     *
     * @param gradeName
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/26 16:59
     */
    @GetMapping("/getStuNum/{gradeName}")
    public CommonResult getStuNum(@PathVariable String gradeName) {
        return gradeService.getStuNum(gradeName);
    }

    /**
     * 功能描述: 班主任信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/19 9:30
     */
    @GetMapping("/getTeacher")
    public CommonResult getTeacher() {
        return gradeService.getTeacher();
    }

    /**
     * 功能描述: 讲师信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/19 9:31
     */
    @GetMapping("/getLecturerName")
    public CommonResult getLecturerName() {
        return gradeService.getLecturerName();
    }

    /**
     * 功能描述: 教室信息
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/19 15:31
     */
    @GetMapping("/getClassRoom")
    public CommonResult getClassRoom() {
        return gradeService.getClassRoom();
    }

    /**
     * 功能描述: 添加班级信息
     *
     * @param grade
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 8:51
     */
    @PostMapping("/insertGrade")
    public CommonResult insertGrade(@RequestBody Grade grade) {
        return gradeService.insertGrade(grade);
    }

    /**
     * 功能描述: 编辑班级信息
     *
     * @param gradeAddVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 10:33
     */
    @PostMapping("/updateGrade")
    public CommonResult updateGrade(@RequestBody GradeAddVO gradeAddVO) {
        return gradeService.updateGrade(gradeAddVO);
    }

    /**
     * 功能描述: 批量删除班级信息
     *
     * @param ids
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 14:31
     */
    @PostMapping("/delGradeInfo")
    public CommonResult delGradeInfo(@RequestBody List<String> ids) {
        return gradeService.delGradeInfo(ids);
    }

    /**
     * 功能描述: 查询班级学生信息
     *
     * @param stuViewVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.StuDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/21 16:25
     */
    @PostMapping("/getStuInfo")
    public CommonResult getStuInfo(@RequestBody StuViewVO stuViewVO) {
        return gradeService.getStuInfo(stuViewVO);
    }

    /**
     * 功能描述: 逻辑删除班级学生信息
     *
     * @param studentId
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/22 16:00
     */
    @PostMapping("/delClassStu")
    public CommonResult delClassStu(@RequestBody List<String> studentId) {
        return gradeService.delClassStu(studentId);
    }

    /**
     * 功能描述: 班级学生表添加
     *
     * @param gradeStuAddVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/25 21:10
     */
    @PostMapping("/insertGradeStudent")
    public CommonResult insertGradeStudent(@RequestBody GradeStuAddVO gradeStuAddVO) {
        return gradeService.insertGradeStudent(gradeStuAddVO);
    }

}
