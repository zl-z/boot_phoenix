package com.buba.boot_phoenix.classroom.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buba.boot_phoenix.classroom.entity.Classroom;
import com.buba.boot_phoenix.classroom.mapper.ClassroomMapper;
import com.buba.boot_phoenix.classroom.service.ClassroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buba.boot_phoenix.classroom.vo.ClassPageVO;
import com.buba.boot_phoenix.classroom.vo.ClassSaveVO;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.exception.ServiceException;
import com.buba.boot_phoenix.student.vo.StudentInsertVO;
import com.buba.boot_phoenix.utils.SerialNumberUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 教室管理 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-07-12
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {
    @Resource
    private ClassroomMapper classroomMapper;

    /**
     * 功能描述: 查询教室信息-模糊查-分页
     *
     * @param classPageVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/12 14:00
     */
    @Override
    public CommonResult listClassRoom(ClassPageVO classPageVO) {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", false);
        if(null != classPageVO){
            if(null != classPageVO.getClassroomNumber() && !"".equals(classPageVO.getClassroomNumber())){
                queryWrapper.like("classroom_number", classPageVO.getClassroomNumber());
            }
            if(null != classPageVO.getClassroomName() && !"".equals(classPageVO.getClassroomName())){
                queryWrapper.like("classroom_name", classPageVO.getClassroomName());
            }
            if(classPageVO.getBegin() > 0 && classPageVO.getOver() < 500){
                queryWrapper.gt("galleryful", classPageVO.getBegin()).le("galleryful", classPageVO.getOver());
            }
            if(null != classPageVO.getArea() && !"".equals(classPageVO.getArea())){
                queryWrapper.eq("area", classPageVO.getArea());
            }
            if(null != classPageVO.getBuildingNo() && !"".equals(classPageVO.getBuildingNo())){
                queryWrapper.like("building_no", classPageVO.getBuildingNo());
            }
            if(null != classPageVO.getBuildingFloor() && !"".equals(classPageVO.getBuildingFloor())){
                queryWrapper.like("building_floor", classPageVO.getBuildingFloor());
            }
            queryWrapper.orderByDesc("pk_id");
            return CommonResult.ok(classroomMapper.selectPage(new Page<>(classPageVO.getPageNum(), classPageVO.getPageSize()), queryWrapper));
        }else {
            return CommonResult.ok(classroomMapper.selectPage(new Page<>(1, 5), queryWrapper));
        }
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
    @Override
    public CommonResult getArea() {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("area");
        queryWrapper.having("area != ''");
        return CommonResult.ok(classroomMapper.selectList(queryWrapper));
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
    @Override
    public CommonResult getBuildingNo(String area) {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("area", area);
        queryWrapper.groupBy("building_no");
        queryWrapper.orderByAsc("building_no");
        queryWrapper.having("building_no != ''");
        return CommonResult.ok(classroomMapper.selectList(queryWrapper));
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
    @Override
    public CommonResult getBuildingFloor(String area, String buildingNo) {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("area", area);
        queryWrapper.eq("building_no", buildingNo);
        queryWrapper.groupBy("building_floor");
        queryWrapper.orderByAsc("building_floor");
        queryWrapper.having("building_floor != ''");
        return CommonResult.ok(classroomMapper.selectList(queryWrapper));
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
    @Override
    public CommonResult saveClassRoom(Classroom classroom) {
        if(null != classroom && !"".equals(classroom)){
            if(null != classroom.getArea()) {
                //拼接字符串截取
                classroom.setClassroomId(classroom.getArea() + RandomUtil.randomNumbers(4));
            }
            QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("classroom_id", classroom.getClassroomId());
            queryWrapper.eq("delete_flag", false);
            if(null != classroomMapper.selectOne(queryWrapper)){
                return CommonResult.error("500", "该教室已存在，请更换教室信息");
            }else {
                try {
                    if(classroomMapper.insert(classroom) > 0){
                        return CommonResult.ok();
                    }
                }catch (Exception e){
                    throw new ServiceException("500", "系统错误");
                }
            }
        }
        return CommonResult.error("500", "添加失败");
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
    @Override
    public CommonResult updateClassRoom(ClassSaveVO classSaveVO) {
        if(null != classSaveVO && !"".equals(classSaveVO)){
            QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
            System.out.println("11"+classSaveVO.getClassroomId());
            queryWrapper.eq("classroom_id", classSaveVO.getClassroomId());
            queryWrapper.eq("delete_flag", false);
            Classroom selectOne;
            try{
                selectOne = classroomMapper.selectOne(queryWrapper);
            }catch (Exception e){
                throw new ServiceException("500", "目标教室信息存在多个，请删除多余后，存留①再进行修改！");
            }
            if(null != selectOne){
                if(classSaveVO.getPkId().equals(selectOne.getPkId())){
                    classSaveVO.setClassroomNewId(classSaveVO.getArea()+RandomUtil.randomNumbers(4));
                    return updateClass(classSaveVO);
                }else {
                    return CommonResult.error("500", "该教室已存在，请更换教室信息");
                }
            }else {
                classSaveVO.setClassroomNewId(classSaveVO.getClassroomId());
                return updateClass(classSaveVO);
            }
        }
        return CommonResult.error("500", "修改失败");
    }

    /**
     * 功能描述: 封装修改教室方法
     *
     * @param classSaveVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 20:18
     */
    private CommonResult updateClass(ClassSaveVO classSaveVO){
        try {
            if(classroomMapper.updateClassRoom(classSaveVO) > 0){
                return CommonResult.ok();
            }else {
                return CommonResult.error("", "教室信息不存在");
            }
        }catch (Exception e){
            throw new ServiceException("500", "系统错误");
        }
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
    @Override
    public CommonResult deleteClassRoom(String classroomId) {
        if(null != classroomId && !"".equals(classroomId)){
            try {
                if(classroomMapper.deleteClassRoom(classroomId) > 0){
                    return CommonResult.ok();
                }else {
                    return CommonResult.error("", "教室信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
    }

    /**
     * 功能描述: 批量删除教室
     *
     * @param classroomId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 9:29
     */
    @Override
    public CommonResult listDelClassRoom(List<String> classroomId) {
        if(null != classroomId && !"".equals(classroomId)) {
            try {
                if (classroomMapper.listDelClassRoom(classroomId) > 0) {
                    return CommonResult.ok();
                }else {
                    return CommonResult.error("", "教室信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }else {
            return CommonResult.error("400", "参数错误");
        }
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
    @Override
    public void excelExport(HttpServletResponse res) throws Exception {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", false);
//        数据库查询出数据
        List<Classroom> classrooms = classroomMapper.selectList(queryWrapper);

//        内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

//        一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(classrooms, true);

//        设置浏览器响应的格式
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        res.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("教室信息", "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = res.getOutputStream(); //获取输出流
        writer.flush(outputStream, true); //writer内容刷新到输出流 , 通过输出流返回到浏览器
        writer.close(); //关闭writer
        outputStream.close(); //关闭流
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
    @Override
    public CommonResult excelImport(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Classroom> list = reader.readAll(Classroom.class);
        if(null != list) {
            try {
                if (classroomMapper.insertListClassRoom(list) > 0) {
                    return CommonResult.ok();
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("500", "导入失败");
    }
}
