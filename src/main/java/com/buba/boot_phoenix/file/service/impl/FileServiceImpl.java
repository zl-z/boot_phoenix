package com.buba.boot_phoenix.file.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.file.entity.Files;
import com.buba.boot_phoenix.file.mapper.FileMapper;
import com.buba.boot_phoenix.file.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buba.boot_phoenix.file.vo.FileVO;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-07-08
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements FileService {
    @Value("${files.upload.path}")
    private String fileUploadPath; //文件存储路径

    @Resource
    private FileMapper fileMapper;

    /**
     * 功能描述: 文件上传
     *
     * @param file
     * @return  java.lang.String
     * @throws
     * @Author: zl
     * @Date:   2022/7/8 10:18
     */
    @Override
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); //原始名称
        String type = FileUtil.extName(originalFilename); //文件类型
        long size = file.getSize(); //文件大小

        String uuId = IdUtil.fastSimpleUUID(); //定义一个文件唯一的标识码
        String fileUUID = uuId + StrUtil.DOT + type; //文件全名+后缀 xxx.jpg
        File uploadFile = new File(fileUploadPath + fileUUID); //文件全路径拼接

//        判断文件的父级目录是否存在，若不存在则创建一个新的文件目录
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs(); //创建文件目录
        }

        String url;
        //上传文件到磁盘
        file.transferTo(uploadFile);
        //获取文件的md5
        String md5 = SecureUtil.md5(uploadFile);
        //从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if(dbFiles != null){
            //如果文件有，直接拿数据库的文件路径给出去
            url = dbFiles.getUrl();
            //删除刚才上传已存在的文件
            uploadFile.delete();
        }else {
            //数据库没有文件，生成文件下载路径
            url = "http://127.0.0.1:9090/phoenix/file/" + fileUUID;
        }
//        存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename); //文件名
        saveFile.setType(type); //文件类型
        saveFile.setSize(size/1024); //文件大小 转单位 kb
        saveFile.setUrl(url);   //文件下载路径
        saveFile.setMd5(md5); //文件md5
        fileMapper.insert(saveFile); //添加操作
        return url;  //返回文件下载链接
    }

    /**
     * 功能描述: 文件下载   http://127.0.0.1:9090/phoenix/file/{fileUUID}
     *
     * @param fileUUID
     * @param response
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/8 11:20
     */
    @Override
    public void download(String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的路径和唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        //设置输出流的格式 ， 通过输出流返回文件
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        //通过读取文件的字节流 ， 再进行把文件写出去
        outputStream.write(FileUtil.readBytes(uploadFile)); //文件写出
        outputStream.flush(); //强制将缓存中的输出流(字节流,字符流等)强制输出
        outputStream.close(); //关闭流
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
    @Override
    public CommonResult listFile(FileVO fileVO) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        //查询未删除的记录
        queryWrapper.eq("is_delete", false);
        if(null != fileVO.getName() && !"".equals(fileVO.getName())){
            queryWrapper.like("name", fileVO.getName());
        }
        queryWrapper.orderByDesc("id"); //根据pk_id 倒序排序
        return CommonResult.ok(fileMapper.selectPage(new Page<>(fileVO.getPageNum(), fileVO.getPageSize()), queryWrapper));
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
    @Override
    public CommonResult deleteFile(Integer id) {
        //先查询
        Files files = fileMapper.selectById(id);
        //把delete设置为true
        files.setIsDelete(true);
        //再更新文件信息
        fileMapper.updateById(files);
        return CommonResult.ok();
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
    @Override
    public CommonResult deleteListFile(List<Integer> ids) {
        //select * from file where id in (id, id, id...)
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", ids);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        for (Files files : filesList) {//遍历更新
            files.setIsDelete(true);
            fileMapper.updateById(files);
        }
        return CommonResult.ok();
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
    @Override
    public CommonResult enableSave(Files files) {
        return CommonResult.ok(fileMapper.updateById(files));
    }

    /**
     * 功能描述: 通过文件的md5查询文件是否重复
     *
     * @param md5
     * @return  com.buba.boot_phoenix.file.entity.Files 
     * @throws  
     * @Author: zl
     * @Date:   2022/7/8 19:42
     */
    private Files getFileByMd5(String md5){
//        查询文件的md5是否存在
        QueryWrapper<Files> filesQueryWrapper = new QueryWrapper<>();
        filesQueryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(filesQueryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
}
