package com.buba.boot_phoenix.provinces.mapper;

import com.buba.boot_phoenix.provinces.entity.Areas;
import com.buba.boot_phoenix.provinces.entity.Cities;
import com.buba.boot_phoenix.provinces.entity.Provinces;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 省份信息表 Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-06-19
 */
@Mapper
public interface ProvincesMapper extends BaseMapper<Provinces> {

    /**
     * 功能描述: 全查省份
     *
     * @param
     * @return  java.util.List<com.buba.boot_phoenix.provinces.entity.Provinces>
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 18:12
     */
    @Select("SELECT * from provinces")
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
    @Select("SELECT * from cities WHERE provinceid = #{provinceId}")
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
    @Select("SELECT * from areas WHERE cityid = #{cityId}")
    List<Areas> getAreas(String cityId);
}
