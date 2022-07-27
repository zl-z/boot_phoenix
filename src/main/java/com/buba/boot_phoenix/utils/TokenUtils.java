package com.buba.boot_phoenix.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.entity.BasicInfo;
import com.buba.boot_phoenix.student.mapper.StudentRegisterMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: zl,
 * Date:    2022/7/7 14:16,
 * Version: IntelliJ IDEA 2021.2.1
 * Token工具类
 * 头部（header）   载荷 （payload）    签证 （signature）
 * 注册TokenUtils为SpringBoot为Bean
 */
@Component
public class TokenUtils {

    private static StudentRegisterMapper staticRegisterMapper;
    @Resource
    private StudentRegisterMapper registerMapper;

    @PostConstruct
    public void setRegisterMapper() {
        staticRegisterMapper = registerMapper;
    }

    /**
     * 功能描述: 生成Token
     *
     * @param userId  用户Id
     * @param sign  用户Pass
     * @return  java.lang.String
     * @throws
     * @Author: zl
     * @Date:   2022/7/7 14:17
     */
    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId) //将 用户Id 保存到 Token 里面,作为载荷
                    .withExpiresAt(DateUtil.offsetHour(new Date(), 2))  //2小时后 Token 过期
                    .sign(Algorithm.HMAC256(sign)); //以 password 作为 Token 密钥 ，签证
    }

    /**
     * 功能描述: 获取当前登录的用户信息
     *
     * @param
     * @return  com.buba.boot_phoenix.student.entity.BasicInfo
     * @throws
     * @Author: zl
     * @Date:   2022/7/7 21:17
     */
    public static StudentLoginDTO getCurrentUser(){
        try{
            //获取当前请求的request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //通过request去Header里面取出 token
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){//token不为空去获取
                //获取/解析Token
                String userId = JWT.decode(token).getAudience().get(0);
                //获取用户信息
                return staticRegisterMapper.getTokenStu(userId);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
