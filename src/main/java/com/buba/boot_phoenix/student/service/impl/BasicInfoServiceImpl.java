package com.buba.boot_phoenix.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.buba.boot_phoenix.student.mapper.BasicInfoMapper;
import com.buba.boot_phoenix.student.service.BasicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buba.boot_phoenix.student.vo.StudentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 学生基本信息表 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Service
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements BasicInfoService {
    @Resource
    private BasicInfoMapper basicInfoMapper;

    /**
     * 功能描述: 查询学生信息-模糊查-分页
     *
     * @param studentVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 14:38
     */
    @Override
    public CommonResult listStudent(StudentVO studentVO) {
        QueryWrapper<BasicInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", 0);
        if(null == studentVO){
            return CommonResult.ok(basicInfoMapper.selectPage(new Page<>(1, 5), queryWrapper));
        }else{
            if(null != studentVO.getStudentName() && !"".equals(studentVO.getStudentName())){
                queryWrapper.like("student_name", studentVO.getStudentName());
            }
            if(null != studentVO.getMobile() && !"".equals(studentVO.getMobile())){
                queryWrapper.like("mobile", studentVO.getMobile());
            }
            if(null != studentVO.getSex() && !"".equals(studentVO.getSex())){
                queryWrapper.eq("sex", studentVO.getSex());
            }
            if(null != studentVO.getAge() && studentVO.getAge() > 0){
                queryWrapper.eq("age", studentVO.getAge());
            }
            if(null != studentVO.getIdNum() && !"".equals(studentVO.getIdNum())){
                queryWrapper.like("id_num", studentVO.getIdNum());
            }
            if(null != studentVO.getTheClass() && !"".equals(studentVO.getTheClass())){
                queryWrapper.eq("the_class", studentVO.getTheClass());
            }
            queryWrapper.orderByDesc("pk_id"); //根据pk_id 倒序排序
            Page<BasicInfo> basicInfoPage = basicInfoMapper.selectPage(new Page<>(studentVO.getPageNum(), studentVO.getPageSize()), queryWrapper);
            return CommonResult.ok(basicInfoPage);
        }
    }

}
