package com.buba.boot_phoenix.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.buba.boot_phoenix.exception.ServiceException;
import com.buba.boot_phoenix.student.dto.StudentInsertDTO;
import com.buba.boot_phoenix.student.dto.StudentLoginDTO;
import com.buba.boot_phoenix.student.mapper.StudentRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zl,
 * Date:    2022/7/7 15:55,
 * Version: IntelliJ IDEA 2021.2.1
 * 拦截器 拦截 Token
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StudentRegisterMapper registerMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token"); //从 http 请求头中取出 token
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException("401", "无token，请重新登录");
        }
        //获取 token 中的 user ID
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException("401", "token验证失败，请重新登录");
        }
        String str = "1";
        //根据 token 中的 user ID 查询数据库，查看用户存不存在
        StudentLoginDTO stuInfo = registerMapper.getTokenStu(userId);
        if(null == stuInfo){
            throw new ServiceException("401", "用户不存在，请重新登录");
        }else {
            if(str.equals(stuInfo.getDeleteFlag())){
                throw new ServiceException("401", "非常抱歉，您的账户被停用了！");
            }
        }
        //用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(stuInfo.getPassword())).build();
        try{
            jwtVerifier.verify(token); //验证 token
        }catch (JWTVerificationException e){
            throw new ServiceException("401", "token验证失败，请重新登录");
        }
        return true;
    }
}
