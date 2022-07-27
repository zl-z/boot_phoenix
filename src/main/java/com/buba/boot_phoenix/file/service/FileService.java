package com.buba.boot_phoenix.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.file.entity.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buba.boot_phoenix.file.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZL
 * @since 2022-07-08
 */
public interface FileService extends IService<Files> {

    /**
     * 功能描述: 文件上传
     *
     * @param file
     * @return  java.lang.String
     * @throws
     * @Author: zl
     * @Date:   2022/7/8 10:18
     */
    String upload(MultipartFile file) throws IOException;

    /**
     * 功能描述: 文件下载
     *
     * @param uuId
     * @param response
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/8 11:20
     */
    void download(String uuId, HttpServletResponse response) throws IOException;

    /**
     * 功能描述: 查询文件-模糊查
     *
     * @param fileVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 13:35
     */
    CommonResult listFile(FileVO fileVO);

    /**
     * 功能描述: 删除文件信息
     *
     * @param id
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/9 16:00
     */
    CommonResult deleteFile(Integer id);

    /**
     * 功能描述: 批量删除文件信息
     *
     * @param ids
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/9 16:00
     */
    CommonResult deleteListFile(List<Integer> ids);

    /**
     * 功能描述: 文件是否禁用
     *
     * @param files
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/9 16:51
     */
    CommonResult enableSave(Files files);
}
