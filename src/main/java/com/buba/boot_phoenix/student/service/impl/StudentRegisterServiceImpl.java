package com.buba.boot_phoenix.student.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buba.boot_phoenix.common.CommonResult;
import com.buba.boot_phoenix.common.ValidationEnum;
import com.buba.boot_phoenix.employee.dto.EmployeeLoginDTO;
import com.buba.boot_phoenix.employee.entity.EmployeeInfo;
import com.buba.boot_phoenix.employee.entity.EmployeeRegister;
import com.buba.boot_phoenix.employee.mapper.EmployeeRegisterMapper;
import com.buba.boot_phoenix.employee.service.EmployeeRegisterService;
import com.buba.boot_phoenix.exception.ServiceException;
import com.buba.boot_phoenix.grade.mapper.GradeMapper;
import com.buba.boot_phoenix.student.dto.StudentInsertDTO;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.mapper.BasicInfoMapper;
import com.buba.boot_phoenix.student.vo.StudentInsertVO;
import com.buba.boot_phoenix.student.vo.UserLoginVO;
import com.buba.boot_phoenix.student.entity.StudentRegister;
import com.buba.boot_phoenix.student.mapper.StudentRegisterMapper;
import com.buba.boot_phoenix.student.service.StudentRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buba.boot_phoenix.utils.PasswordManagerUtil;
import com.buba.boot_phoenix.utils.SerialNumberUtil;
import com.buba.boot_phoenix.utils.TokenUtils;
import com.buba.boot_phoenix.validation.entity.Validation;
import com.buba.boot_phoenix.validation.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 学生注册表 服务实现类
 * </p>
 *
 * @author ZL
 * @since 2022-06-18
 */
@Service("registerService")
public class StudentRegisterServiceImpl extends ServiceImpl<StudentRegisterMapper, StudentRegister> implements StudentRegisterService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from; //配置用户邮箱

    @Resource
    private StudentRegisterMapper registerMapper;   //学生表

    @Resource
    private StudentRegisterService studentRegisterService;

    @Resource
    private EmployeeRegisterMapper employeeRegisterMapper; //教师表

    @Resource
    private EmployeeRegisterService employeeRegisterService; //教师表

    @Resource
    private BasicInfoMapper basicInfoMapper;        //教师信息注册表

    @Resource
    private ValidationService validationService;        //验证码表

    @Resource
    private GradeMapper gradeMapper;        //班级内删除学生信息

    Map<String, Object> map = new HashMap<>(); //data对象

    /**
     * 功能描述: 学生登录
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.student.entity.StudentRegister
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 15:34
     */
    @Override
    public CommonResult getStudentLogin(UserLoginVO userLoginVO) {
        String str = "0"; //删除标识
        if(null == userLoginVO || "".equals(userLoginVO)){
            return CommonResult.error("500", "用户名或密码有误");
        }else{
            StudentLoginDTO student = registerMapper.getStudentLogin(userLoginVO.getEmail());
            if(null != student && PasswordManagerUtil.getMd5DigestAsHex(userLoginVO.getPassWord()).equals(student.getPassword())){
                if(str.equals(student.getDeleteFlag())){
//                  设置Token
                    student.setToken(TokenUtils.genToken(student.getStudentId(), student.getPassword()));
                    map.put("student", student);
                    return CommonResult.ok(map);
                }else{
                    return CommonResult.error("404", "非常抱歉，您的账户被停用了！");
                }
            }else{
                return CommonResult.error("500", "用户名或密码有误");
            }
        }
    }

    /**
     * 功能描述: 邮箱验证码登录
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.student.entity.StudentRegister
     * @throws
     * @Author: zl
     * @Date:   2022/6/14 15:34
     */
    @Override
    public CommonResult getLoginEmail(UserLoginVO userLoginVO) {
        String str = "0"; //删除标识
        if(null == userLoginVO || "".equals(userLoginVO)){
            return CommonResult.error("500", "用户名或密码有误");
        }else{
//            先查询 邮箱验证的表，看看之前发送的邮箱code  是否有效，失效就重新获取
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("email", userLoginVO.getEmail());
            queryWrapper.eq("code", userLoginVO.getCode());
            queryWrapper.ge("time", new Date()); //查询数据库没过期的code, where time >= new Date()
//            如果验证码有效，就查询用户信息
            if(str.equals(userLoginVO.getUserCode())){
                StudentLoginDTO student = registerMapper.getStudentLogin(userLoginVO.getEmail());
                if(student == null){
                    throw new ServiceException("-1", "未找到该用户信息");
                }else{
                    //查询数据库没过期的code, where time >= new Date()
                    Validation one = validationService.getOne(queryWrapper);
                    if(null == one){
                        throw new ServiceException("-1", "您的验证码已过期，请重新获取！");
                    }
//                判断是否停用
                    if(str.equals(student.getDeleteFlag())){
//                  设置Token
                        student.setToken(TokenUtils.genToken(student.getStudentId(), student.getPassword()));
                        map.put("student", student);
                        return CommonResult.ok(map);
                    }else{
                        return CommonResult.error("404", "非常抱歉，您的账户被停用了！");
                    }
                }
            }else{
                EmployeeLoginDTO employee = employeeRegisterMapper.getTeacherLogin(userLoginVO.getEmail());
                if(employee == null){
                    throw new ServiceException("-1", "未找到该用户信息");
                }else{
                    //查询数据库没过期的code, where time >= new Date()
                    Validation one = validationService.getOne(queryWrapper);
                    if(null == one){
                        throw new ServiceException("-1", "您的验证码已过期，请重新获取！");
                    }
//                判断是否停用
                    if(str.equals(employee.getDeleteFlag())){
//                  设置Token
                        employee.setToken(TokenUtils.genToken(employee.getEmployeeId(), employee.getPassword()));
                        map.put("teacher", employee);
                        return CommonResult.ok(map);
                    }else{
                        return CommonResult.error("404", "非常抱歉，您的账户被停用了！");
                    }
                }
            }
        }
    }

    /**
     * 功能描述: 发送邮箱验证码
     *
     * @param userLoginVO
     * @return  void
     * @throws
     * @Author: zl
     * @Date:   2022/7/2 17:19
     */
    @Override
    public void sendEmailCode(UserLoginVO userLoginVO) {
        Date now = new Date();
//        先查询同类型code  是否在有效期内
        QueryWrapper<Validation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userLoginVO.getEmail());
        queryWrapper.eq("type", userLoginVO.getType());
        queryWrapper.ge("time", now); //查询数据库没过期的code
        Validation validation = validationService.getOne(queryWrapper);
        if(null != validation){
            throw new ServiceException("-1", "当前您的验证码仍然有效，请不要重复发送！！！");
        }
//        验证码过期重新发送
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(userLoginVO.getEmail());
        mailMessage.setSentDate(now);
//        随机码 长度4位
        String code = RandomUtil.randomNumbers(4);
        if(ValidationEnum.LOGIN.getType().equals(userLoginVO.getType())){
            mailMessage.setSubject("【智慧校园】登录邮箱验证");
            mailMessage.setText("您本次邮箱登录【智慧校园】的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露于他人。");
        }else if(ValidationEnum.FORGET_PASS.getType().equals(userLoginVO.getType())){
            mailMessage.setSubject("【智慧校园】重置密码验证");
            mailMessage.setText("您本次重置密码【智慧校园】的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露于他人。");
        }
        try {
            javaMailSender.send(mailMessage);
            //发送成功之后，把验证码存到数据库                                              失效时间，当前时间叠加5分分钟
            validationService.saveCode(userLoginVO.getEmail(), code, userLoginVO.getType(), DateUtil.offsetMinute(now, 5));
        }catch (Exception e){
            throw new ServiceException("-1", "系统错误，请刷新页面，重新获取验证码");
        }
    }

    /**
     * 功能描述: 重置密码
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/3 16:59
     */
    @Override
    public CommonResult resetPass(UserLoginVO userLoginVO) {
        String str = "0"; //删除标识
        if (StrUtil.isBlank(userLoginVO.getEmail()) || StrUtil.isBlank(userLoginVO.getCode())) {
            throw new ServiceException("400", "参数错误");
        }
        QueryWrapper stuQueryWrapper = new QueryWrapper();
        stuQueryWrapper.eq("email", userLoginVO.getEmail());
//            如果验证码有效，就查询用户信息
//        先查询同类型code  是否在有效期内
        QueryWrapper<Validation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userLoginVO.getEmail());
        queryWrapper.eq("code", userLoginVO.getCode());
        queryWrapper.ge("time", new Date()); //查询数据库没过期的code
        if(str.equals(userLoginVO.getUserCode())){
            StudentRegister stuServiceOne = studentRegisterService.getOne(stuQueryWrapper);
            if (null != stuServiceOne) {
                //查询数据库没过期的code
                Validation validation = validationService.getOne(queryWrapper);
                if (null == validation) {
                    throw new ServiceException("-1", "您的验证码已过期，请重新获取！");
                }
//                判断是否停用
                if (str.equals(stuServiceOne.getDeleteFlag())) {
//        密码
                    String code = RandomUtil.randomNumbers(6);
                    userLoginVO.setStudentId(stuServiceOne.getStudentId());
                    userLoginVO.setPassWord(PasswordManagerUtil.getMd5DigestAsHex(code));
                    if (registerMapper.updateStuPass(userLoginVO) > 0) {
                        map.put("P", code);
                    }
                } else {
                    return CommonResult.error("404", "非常抱歉，您的账户被停用了！");
                }
            } else {
                throw new ServiceException("-1", "未找到该用户信息");
            }
        }else{
            EmployeeRegister employee = employeeRegisterService.getOne(stuQueryWrapper);
            if(employee == null){
                throw new ServiceException("-1", "未找到该用户信息");
            }else{
                //查询数据库没过期的code
                Validation validation = validationService.getOne(queryWrapper);
                if (null == validation) {
                    throw new ServiceException("-1", "您的验证码已过期，请重新获取！");
                }
//                判断是否停用
                if(str.equals(employee.getDeleteFlag())){
//        密码
                    String code = RandomUtil.randomNumbers(6);
                    userLoginVO.setStudentId(employee.getUserId());
                    userLoginVO.setPassWord(PasswordManagerUtil.getMd5DigestAsHex(code));
                    if (employeeRegisterMapper.updateTeaPass(userLoginVO) > 0) {
                        map.put("P", code);
                    }
                }else{
                    return CommonResult.error("404", "非常抱歉，您的账户被停用了！");
                }
            }
        }
        return CommonResult.ok(map);
    }

    /**
     * 功能描述: 注册学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/19 20:28
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    /**
     *  Spring框架的事务基础架构代码将默认地 只 在抛出运行时和unchecked exceptions时才标识事务回滚也就是说，
     *  当抛出个RuntimeException 或其子类例的实例时。（Errors 也一样 - 默认地 - 标识事务回滚。）从事务方法中抛出的Checked exceptions将 ****不 被标识进行事务回滚。
     *  让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class)
     */
    public CommonResult insertStudentInfo(StudentInsertVO studentInsertVO) {
        studentInsertVO.setStudentId(SerialNumberUtil.getUUID());
        studentInsertVO.setPassword(PasswordManagerUtil.getMd5DigestAsHex(studentInsertVO.getPassword()));
        if(null != studentInsertVO && !"".equals(studentInsertVO)) {
            try{
                StudentLoginDTO studentLogin = registerMapper.getStudentLogin(studentInsertVO.getEmail());
                if(null == studentLogin){
                    if (registerMapper.insertStudentRegister(studentInsertVO) > 0 && registerMapper.insertStudentInfo(studentInsertVO) > 0) {
                        return CommonResult.ok();
                    }
                }else {
                    return CommonResult.error("203", "账户账号已存在，请更换邮箱添加！");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("505", "注册失败");
    }

    /**
     * 功能描述: 查询学生信息-分页
     *
     * @param studentPageVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/21 14:38
     */
    /*@Override
    public Map<String, Object> listStudentPage(StudentPageVO studentPageVO) {
        String str = "F"; //删除标识
        //页码Num 第几条开始（偏移量0开始）   每页数量Size 每页几条
        studentPageVO.setPageNum((studentPageVO.getPageNum() - 1) * studentPageVO.getPageSize());
        List<BasicInfo> basicInfos = registerMapper.listStudentPage(studentPageVO);
        //Stream流 对集合某一个属性判断修改
        basicInfos.stream().forEach(basicInfo -> {
            if(str.equals(basicInfo.getSex())){
                basicInfo.setSex("女");
            }else{
                basicInfo.setSex("男");
            }
        });
        Integer total = registerMapper.selectTotal(studentPageVO);
        map.put("data", basicInfos); //数据
        map.put("total", total); //总条数
        return map;
    }*/

    /**
     * 功能描述: 修改学生信息
     *
     * @param studentInsertVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/24 16:23
     */
    @Override
    public CommonResult updateStudentInfo(StudentInsertVO studentInsertVO) {
        if(null != studentInsertVO && !"".equals(studentInsertVO)) {
            try {
                if (registerMapper.updateStudentInfo(studentInsertVO) > 0) {
                    return CommonResult.ok();
                }else {
                    return CommonResult.error("", "学生信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("500", "修改失败");
    }

    /**
     * 功能描述: 删除学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 15:58
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteStudentInfo(String studentId) {
        if(null != studentId && !"".equals(studentId)){
            try{
                if(registerMapper.deleteStudentInfo(studentId) > 0 && gradeMapper.delClassStu(Arrays.asList(studentId)) > 0){
                    return CommonResult.ok();
                }else {
                    return CommonResult.error("", "学生信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("500", "删除失败");
    }

    /**
     * 功能描述: 批量删除学生信息
     *
     * @param studentId
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/6/25 15:58
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult listDelStudentInfo(List<String> studentId) {
        if(null != studentId && !"".equals(studentId)) {
            try {
                if (registerMapper.listDelStudentInfo(studentId) > 0 && gradeMapper.delClassStu(studentId) > 0) {
                    return CommonResult.ok();
                }else {
                    return CommonResult.error("", "学生信息不存在");
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("500", "批量删除学生信息失败");
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
//        数据库查询出数据
        List<StudentInsertDTO> list = registerMapper.listStudentInfo();

//        通过工具类创建writer 写出到磁盘路径 指定路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");

//        内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

//        一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

//        设置浏览器响应的格式
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        res.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("学生信息", "UTF-8") + ".xlsx");
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
        List<StudentInsertDTO> list = reader.readAll(StudentInsertDTO.class);
        list.stream().forEach(basicInfo -> {
            basicInfo.setStudentId(SerialNumberUtil.getUUID());
        });
        if(null != list) {
            try {
                if (registerMapper.insertListStudentRegister(list) > 0 && registerMapper.insertListStudentInfo(list) > 0) {
                    return CommonResult.ok();
                }
            }catch (Exception e){
                throw new ServiceException("500", "系统错误");
            }
        }
        return CommonResult.error("500", "导入失败");
    }

    /**
     * 功能描述: 查询登录个人信息
     *
     * @param userLoginVO
     * @return  com.buba.boot_phoenix.common.CommonResult
     * @throws
     * @Author: zl
     * @Date:   2022/7/5 10:02
     */
    @Override
    public CommonResult getUserInfo(UserLoginVO userLoginVO) {
        String str = "0";
        if(str.equals(userLoginVO.getUserCode())){
            StudentInsertDTO stuInfo = registerMapper.getStuInfo(userLoginVO.getStudentId());
//                  设置Token
            stuInfo.setToken(TokenUtils.genToken(stuInfo.getStudentId(), stuInfo.getPassword()));
            map.put("student", stuInfo);
            return CommonResult.ok(map);
        }else{
            EmployeeInfo teaInfo = employeeRegisterMapper.getTeaInfo(userLoginVO.getStudentId());
            map.put("teacher", teaInfo);
            return CommonResult.ok(map);
        }
    }
}
