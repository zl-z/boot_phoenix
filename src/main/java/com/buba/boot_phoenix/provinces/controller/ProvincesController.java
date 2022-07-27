package com.buba.boot_phoenix.provinces.controller;

import com.buba.boot_phoenix.provinces.entity.Areas;
import com.buba.boot_phoenix.provinces.entity.Cities;
import com.buba.boot_phoenix.provinces.entity.Provinces;
import com.buba.boot_phoenix.provinces.service.ProvincesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 省份信息表 前端控制器
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
@CrossOrigin
@RestController
@RequestMapping("/provinces")
public class ProvincesController {

    @Resource
    private ProvincesService provincesService;

    /**
     * 功能描述: 全查省份
     *
     * @param
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Provinces>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:11
     */
    @PostMapping("/getProvinces")
    public List<Provinces> getProvinces() {
        return provincesService.getProvinces();
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
    @PostMapping("/getCities")
    public List<Cities> getCities(@RequestBody String provinceId) {
        System.out.println("provinceId::::::"+provinceId);
        return provincesService.getCities(provinceId);
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
    @PostMapping("/getAreas")
    public List<Areas> getAreas(@RequestBody String cityId) {
        return provincesService.getAreas(cityId);
    }
}
