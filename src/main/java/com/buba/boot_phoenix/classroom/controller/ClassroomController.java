package com.buba.boot_phoenix.classroom.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buba.boot_phoenix.classroom.entity.Classroom;
import com.buba.boot_phoenix.classroom.service.ClassroomService;
import com.buba.boot_phoenix.classroom.vo.ClassPageVO;
import com.buba.boot_phoenix.classroom.vo.ClassSaveVO;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.exception.ServiceException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 教室管理 前端控制器
 * </p>
 *
 * @author ZL
 * @since 2022-07-12
 */
@CrossOrigin
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Resource
    private ClassroomService classroomService;

    /**
     * 功能描述: 查询教室信息-模糊查-分页
     *
     * @param classPageVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/12 14:00
     */
    @PostMapping("/listClassRoom")
    public CommonResult listClassRoom(@RequestBody ClassPageVO classPageVO) {
        return classroomService.listClassRoom(classPageVO);
    }

    /**
     * 功能描述: 联动查询区域
     *
     * @param
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/13 9:25
     */
    @GetMapping("/getArea")
    public CommonResult getArea() {
        return classroomService.getArea();
    }

    /**
     * 功能描述: 联动查询楼号
     *
     * @param area
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/13 9:40
     */
    @GetMapping("/getBuildingNo/{area}")
    public CommonResult getBuildingNo(@PathVariable String area) {
        return classroomService.getBuildingNo(area);
    }

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
    @GetMapping("/getBuildingFloor")
    public CommonResult getBuildingFloor(@RequestParam String area, @RequestParam String buildingNo) {
        return classroomService.getBuildingFloor(area, buildingNo);
    }

    /**
     * 功能描述: 添加教室
     *
     * @param classroom
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    @PostMapping("/saveClassRoom")
    public CommonResult saveClassRoom(@RequestBody Classroom classroom) {
        return classroomService.saveClassRoom(classroom);
    }

    /**
     * 功能描述: 修改教室
     *
     * @param classSaveVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    @PostMapping("/updateClassRoom")
    public CommonResult updateClassRoom(@RequestBody ClassSaveVO classSaveVO) {
        return classroomService.updateClassRoom(classSaveVO);
    }

    /**
     * 功能描述: 删除教室
     *
     * @param classroomId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    @GetMapping("/deleteClassRoom/{classroomId}")
    public CommonResult deleteClassRoom(@PathVariable String classroomId) {
        return classroomService.deleteClassRoom(classroomId);
    }

    /**
     * 功能描述: 批量删除教室
     *
     * @param classroomId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 18:53
     */
    @PostMapping("/listDelClassRoom")
    public CommonResult listDelClassRoom(@RequestBody List<String> classroomId) {
        return classroomService.listDelClassRoom(classroomId);
    }

    /**
     * 功能描述: Excel表格导出
     *
     * @param res
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 8:45
     */
    @GetMapping("/excelExport")
    public void excelExport(HttpServletResponse res) throws Exception {
        classroomService.excelExport(res);
    }

    /**
     * 功能描述: Excel表格导入
     *
     * @param file
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/27 8:43
     */
    @PostMapping("/excelImport")
    public CommonResult excelImport(MultipartFile file) throws Exception{
        return classroomService.excelImport(file);
    }

}
