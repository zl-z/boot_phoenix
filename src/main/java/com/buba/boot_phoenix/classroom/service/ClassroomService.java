package com.buba.boot_phoenix.classroom.service;

import com.buba.boot_phoenix.classroom.entity.Classroom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buba.boot_phoenix.classroom.vo.ClassPageVO;
import com.buba.boot_phoenix.classroom.vo.ClassSaveVO;
import com.buba.boot_phoenix.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 教室管理 服务类
 * </p>
 *
 * @author ZL
 * @since 2022-07-12
 */
public interface ClassroomService extends IService<Classroom> {

    /**
     * 功能描述: 查询教室信息-模糊查-分页
     *
     * @param classPageVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/12 14:00
     */
    CommonResult listClassRoom(ClassPageVO classPageVO);

    /**
     * 功能描述: 联动查询区域
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/13 9:25
     */
    CommonResult getArea();

    /**
     * 功能描述: 联动查询楼号
     *
     * @param area
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/13 9:40
     */
    CommonResult getBuildingNo(String area);

    /**
     * 功能描述: 联动查询楼层
     *
     * @param area
     * @param buildingNo
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/13 9:40
     */
    CommonResult getBuildingFloor(String area, String buildingNo);

    /**
     * 功能描述: 添加教室
     *
     * @param classroom
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    CommonResult saveClassRoom(Classroom classroom);

    /**
     * 功能描述: 修改教室
     *
     * @param classSaveVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    CommonResult updateClassRoom(ClassSaveVO classSaveVO);

    /**
     * 功能描述: 删除教室
     *
     * @param classroomId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    CommonResult deleteClassRoom(String classroomId);

    /**
     * 功能描述: 批量删除教室
     *
     * @param classroomId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    CommonResult listDelClassRoom(List<String> classroomId);

    /**
     * 功能描述: Excel表格导出
     *
     * @param res
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 8:45
     */
    void excelExport(HttpServletResponse res) throws Exception;

    /**
     * 功能描述: Excel表格导入
     *
     * @param file
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 8:43
     */
    CommonResult excelImport(MultipartFile file) throws Exception;

}
