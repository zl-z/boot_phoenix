package com.buba.boot_phoenix.exception;

import com.buba.boot_phoenix.common.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zl,
 * Date:    2022/6/28 11:08,
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return CommonResult
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public CommonResult handle(ServiceException se){
        return CommonResult.error(se.getCode(), se.getMessage());
    }
}
