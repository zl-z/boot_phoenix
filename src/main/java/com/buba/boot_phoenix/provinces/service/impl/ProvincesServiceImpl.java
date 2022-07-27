package com.buba.boot_phoenix.provinces.service.impl;

import com.buba.boot_phoenix.provinces.entity.Areas;
import com.buba.boot_phoenix.provinces.entity.Cities;
import com.buba.boot_phoenix.provinces.entity.Provinces;
import com.buba.boot_phoenix.provinces.mapper.ProvincesMapper;
import com.buba.boot_phoenix.provinces.service.ProvincesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 省份信息表 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
@Service
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements ProvincesService {

    @Resource
    private ProvincesMapper provincesMapper;

    /**
     * 功能描述: 全查省份
     *
     * @param
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Provinces>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:11
     */
    @Override
    public List<Provinces> getProvinces() {
        return provincesMapper.getProvinces();
    }

    /**
     * 功能描述: 全查市
     *
     * @param provinceId
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Cities>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:15
     */
    @Override
    public List<Cities> getCities(String provinceId) {
        return provincesMapper.getCities(provinceId);
    }

    /**
     * 功能描述: 全查县
     *
     * @param cityId
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Areas>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:15
     */
    @Override
    public List<Areas> getAreas(String cityId) {
        return provincesMapper.getAreas(cityId);
    }
}
