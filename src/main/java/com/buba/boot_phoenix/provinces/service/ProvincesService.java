package com.buba.boot_phoenix.provinces.service;

import com.buba.boot_phoenix.provinces.entity.Areas;
import com.buba.boot_phoenix.provinces.entity.Cities;
import com.buba.boot_phoenix.provinces.entity.Provinces;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 省份信息表 服务类
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
public interface ProvincesService extends IService<Provinces> {
    /**
     * 功能描述: 全查省份
     *
     * @param
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Provinces>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:11
     */
    List<Provinces> getProvinces();

    /**
     * 功能描述: 全查市
     *
     * @param provinceId
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Cities>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:15
     */
    List<Cities> getCities(String provinceId);

    /**
     * 功能描述: 全查县
     *
     * @param cityId
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Areas>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:15
     */
    List<Areas> getAreas(String cityId);
}
