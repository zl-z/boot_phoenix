package com.buba.boot_phoenix.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.buba.boot_phoenix.student.service.BasicInfoService;
import com.buba.boot_phoenix.student.vo.StudentVO;
import com.buba.boot_phoenix.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 学生基本信息表 前端控制器
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@CrossOrigin
@RestController
@RequestMapping("/basic-info")
public class BasicInfoController {
    @Resource
    private BasicInfoService basicInfoService;

    /**
     * 功能描述: 查询学生信息-模糊查
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 13:35
     */
    @PostMapping("/listStudent")
    public CommonResult listStudent(@RequestBody StudentVO studentVO){
        StudentLoginDTO currentUser = TokenUtils.getCurrentUser();
        System.out.println("Token信息========="+ currentUser);
        return basicInfoService.listStudent(studentVO);
    }

}
