package com.buba.boot_phoenix.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buba.boot_phoenix.student.vo.StudentVO;

/**
 * <p>
 * 学生基本信息表 服务类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
public interface BasicInfoService extends IService<BasicInfo> {

    /**
     * 功能描述: 查询学生信息-模糊查-分页
     *
     * @param studentVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 14:38
     */
     CommonResult listStudent(StudentVO studentVO);


}
