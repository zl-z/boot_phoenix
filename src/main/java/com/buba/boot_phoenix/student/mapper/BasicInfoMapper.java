package com.buba.boot_phoenix.student.mapper;

import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生基本信息表 Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Mapper
public interface BasicInfoMapper extends BaseMapper<BasicInfo> {

}
