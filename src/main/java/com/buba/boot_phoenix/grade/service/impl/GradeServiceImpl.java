package com.buba.boot_phoenix.grade.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.exception.ServiceException;
import com.buba.boot_phoenix.grade.dto.GradeFullDTO;
import com.buba.boot_phoenix.grade.dto.StuDTO;
import com.buba.boot_phoenix.grade.entity.Grade;
import com.buba.boot_phoenix.grade.mapper.GradeMapper;
import com.buba.boot_phoenix.grade.service.GradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buba.boot_phoenix.grade.vo.GradeAddVO;
import com.buba.boot_phoenix.grade.vo.GradePageVO;
import com.buba.boot_phoenix.grade.vo.GradeStuAddVO;
import com.buba.boot_phoenix.grade.vo.StuViewVO;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.buba.boot_phoenix.student.mapper.BasicInfoMapper;
import com.buba.boot_phoenix.student.mapper.StudentRegisterMapper;
import com.buba.boot_phoenix.utils.SerialNumberUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级表 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-07-18
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Resource
    private BasicInfoMapper basicInfoMapper; //学生信息

    @Resource
    private GradeMapper gradeMapper;

    Map<String, Object> map = new HashMap<>();

    /**
     * 功能描述: 模糊查-分页 班级信息
     *
     * @param gradePageVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/18 16:56
     */
    @Override
    public CommonResult listGradeInfo(GradePageVO gradePageVO) {
        if(null != gradePageVO){
            //页码 =（页码Num -1）* 每页数量Size
            gradePageVO.setPageNum((gradePageVO.getPageNum() - 1) * gradePageVO.getPageSize());
            try{
                //班级信息
                map.put("data", gradeMapper.listGrade(gradePageVO));
                //总条数
                map.put("total", gradeMapper.gradeTotal(gradePageVO));
                return CommonResult.ok(map);
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
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
    @Override
    public CommonResult getStuNum(String gradeName) {
        if(null != gradeName && !"".equals(gradeName)){
            return CommonResult.ok(gradeMapper.getStuNum(gradeName));
        }else {
            return CommonResult.error("400", "参数错误");
        }
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
    @Override
    public CommonResult getTeacher() {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("class_teacher_name");
        queryWrapper.orderByAsc("class_teacher_name");
        queryWrapper.having("class_teacher_name != ''");
        return CommonResult.ok(gradeMapper.selectList(queryWrapper));
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
    @Override
    public CommonResult getLecturerName() {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("lecturer_name");
        queryWrapper.orderByAsc("lecturer_name");
        queryWrapper.having("lecturer_name != ''");
        return CommonResult.ok(gradeMapper.selectList(queryWrapper));
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
    @Override
    public CommonResult getClassRoom() {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("classroom_classroom_number");
        queryWrapper.orderByAsc("classroom_classroom_number");
        queryWrapper.having("classroom_classroom_number != ''");
        return CommonResult.ok(gradeMapper.selectList(queryWrapper));
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
    @Override
    public CommonResult insertGrade(Grade grade) {
        if(null != grade){
            QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("delete_flag", false);
            queryWrapper.eq("grade_name", grade.getGradeName());
//        随机码 长度4位
            String code = RandomUtil.randomNumbers(4);
            grade.setGradeId(grade.getGradeId()+code);
            if(null != gradeMapper.selectOne(queryWrapper)){
                return CommonResult.error("500", "当前班级名称已存在，请更换！");
            }else {
                try{
                    if(gradeMapper.insert(grade) > 0){
                        return CommonResult.ok();
                    }
                }catch (Exception e){
                    throw new ServiceException("500", "系统错误");
                }
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
        return CommonResult.error("500", "添加失败");
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
    @Override
    public CommonResult updateGrade(GradeAddVO gradeAddVO) {
        if(null != gradeAddVO){
            QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("grade_name", gradeAddVO.getGradeName());
            queryWrapper.eq("delete_flag", false);
            Grade grade;
            try{
                grade = gradeMapper.selectOne(queryWrapper);
            }catch (Exception e){
                throw new ServiceException("500", "目标班级信息存在多个，请删除多余后，存留①再进行修改！");
            }
            if(null != grade){
                if(gradeAddVO.getPkId().equals(grade.getPkId())){ //截取后四位，替换
                    if(gradeAddVO.getGradeId().length() < 4){
                        gradeAddVO.setGradeNewId(gradeAddVO.getGradeId()+RandomUtil.randomNumbers(4)); //新ID 随机码 长度4位
                    }else {
                        gradeAddVO.setGradeNewId(gradeAddVO.getGradeId().replace(gradeAddVO.getGradeId().substring(gradeAddVO.getGradeId().length()-4), RandomUtil.randomNumbers(4)));
                    }
                    return updateGradeInfo(gradeAddVO);
                }else {
                    return CommonResult.error("500", "当前班级名称已存在，请更换！");
                }
            }else {
                gradeAddVO.setGradeNewId(gradeAddVO.getGradeId()); //新ID 随机码 长度4位
                return updateGradeInfo(gradeAddVO);
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
    }

    /**
     * 功能描述: 封装修改班级方法
     *
     * @param gradeAddVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 20:18
     */
    private CommonResult updateGradeInfo(GradeAddVO gradeAddVO){
        try{
            if(gradeMapper.updateGrade(gradeAddVO) > 0){
                return CommonResult.ok();
            }else {
                return CommonResult.error("500", "班级信息不存在");
            }
        }catch (Exception e){
            throw new ServiceException("500", "系统错误");
        }
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
    @Override
    public CommonResult delGradeInfo(List<String> ids) {
        if(null != ids && ids.size() > 0) {
            try {
                if (gradeMapper.delGradeInfo(ids) > 0) {
                    return CommonResult.ok();
                }else {
                    return CommonResult.error("500", "班级信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("500", "批量删除班级信息失败");
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
    @Override
    public CommonResult getStuInfo(StuViewVO stuViewVO) {
        if(null != stuViewVO.getGradeName() && !"".equals(stuViewVO.getGradeName())){
            //页码 =（页码Num -1）* 每页数量Size
            stuViewVO.setPageNum((stuViewVO.getPageNum() - 1) * stuViewVO.getPageSize());
            try{
                //班级信息
                map.put("data", gradeMapper.getStuInfo(stuViewVO));
                //总条数
                map.put("total", gradeMapper.stuTotal(stuViewVO));
                return CommonResult.ok(map);
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult delClassStu(List<String> studentId) {
        if(null != studentId && !"".equals(studentId)){
            try{
                if(gradeMapper.delClassStu(studentId) > 0){
                    if(studentId.size() == 1){
                        QueryWrapper queryWrapper = new QueryWrapper();
                        queryWrapper.eq("delete_flag", false);
                        queryWrapper.eq("student_id", StringUtils.joinWithSerialComma(studentId));
                        BasicInfo basicInfo = basicInfoMapper.selectOne(queryWrapper);
                        if(null != basicInfo){
                            if(gradeMapper.updateStuTheClass(false, basicInfo.getStudentId()) > 0){
                                return CommonResult.ok();
                            }else {
                                return CommonResult.error("500", "该学生不存在，无法移出本班级！");
                            }
                        }else {
                            return CommonResult.ok();
                        }
                    }else {
                        return CommonResult.ok();
                    }
                }else {
                    return CommonResult.error("500", "学生信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult insertGradeStudent(GradeStuAddVO gradeStuAddVO) {
        if(null != gradeStuAddVO && !"".equals(gradeStuAddVO)){
            try{
                if(gradeMapper.insertGradeStudent(gradeStuAddVO) > 0){
                    if(gradeMapper.updateStuTheClass(true, gradeStuAddVO.getStudentId()) > 0){
                        return CommonResult.ok();
                    }
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
        return CommonResult.error("500", "添加失败");
    }
}
