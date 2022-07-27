package com.buba.boot_phoenix.file.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.file.entity.Files;
import com.buba.boot_phoenix.file.service.FileService;
import com.buba.boot_phoenix.file.vo.FileVO;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.buba.boot_phoenix.student.vo.StudentVO;
import com.buba.boot_phoenix.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  文件上传相关接口
 * </p>
 *
 * @author ZL
 * @since 2022-07-08
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 功能描述: 文件上传接口
     *
     * @param file 前端传递过来的文件
     * @return  java.lang.String
     * @throws
     * @Author: zl
     * @Date:   2022/7/8 9:25
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

    /**
     * 功能描述: 文件下载接口
     *
     * @param fileUUID
     * @param response
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/8 17:28
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        fileService.download(fileUUID, response);
    }

    /**
     * 功能描述: 查询文件-模糊查
     *
     * @param fileVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 13:35
     */
    @PostMapping("/listFile")
    public CommonResult listFile(@RequestBody FileVO fileVO){
        return fileService.listFile(fileVO);
    }

    /**
     * 功能描述: 删除文件信息
     *
     * @param id
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/9 16:00
     */
    @DeleteMapping("/deleteFile/{id}")
    public CommonResult deleteFile(@PathVariable Integer id){
        return fileService.deleteFile(id);
    }

    /**
     * 功能描述: 批量删除文件信息
     *
     * @param ids
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/9 16:00
     */
    @PostMapping("/deleteListFile")
    public CommonResult deleteListFile(@RequestBody List<Integer> ids){
        return fileService.deleteListFile(ids);
    }

    /**
     * 功能描述: 文件是否禁用
     *
     * @param files
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/9 16:51
     */
    @PostMapping("/enableSave")
    public CommonResult enableSave(@RequestBody Files files){
        return fileService.enableSave(files);
    }


}
