package com.buba.boot_phoenix.student.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.student.entity.StudentRegister;
import com.buba.boot_phoenix.student.service.StudentRegisterService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.Sun;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zl,
 * Date:    2022/7/10 16:21,
 * Version: IntelliJ IDEA 2021.2.1
 * Echarts操作
 */
@CrossOrigin
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private StudentRegisterService studentRegisterService;

    /**
     * 功能描述: Echarts模型数据
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/10 16:23
     */
    @GetMapping("/getEchartsDate")
    public CommonResult getEchartsDate(){
        Map<String, Object> map = new HashMap<>();
        //横轴
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        //纵轴
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return CommonResult.ok(map);
    }

    /**
     * 功能描述: 查询一年四个季度的用户量
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/10 16:38
     */
    @GetMapping("/members")
    public CommonResult members(){
        List<StudentRegister> list = studentRegisterService.list();
        int q1 = 0; //第一季度
        int q2 = 0; //第二季度
        int q3 = 0; //第三季度
        int q4 = 0; //第四季度
        for (StudentRegister studentRegister : list) {
            Date createTime = studentRegister.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime); //按季度分配123，456，789，10 11 12
            switch (quarter) {
                case Q1: q1 += 1; break;
                case Q2: q2 += 1; break;
                case Q3: q3 += 1; break;
                case Q4: q4 += 1; break;
                default: break;
            }
        }
        return CommonResult.ok(CollUtil.newArrayList(q1, q2, q3, q4));
    }
}
