package com.buba.boot_phoenix.provinces.service.impl;

import com.buba.boot_phoenix.provinces.entity.Cities;
import com.buba.boot_phoenix.provinces.mapper.CitiesMapper;
import com.buba.boot_phoenix.provinces.service.CitiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域地州市信息表 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements CitiesService {

}
