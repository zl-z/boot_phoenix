package com.buba.boot_phoenix.exception;

import lombok.Getter;

/**
 * @Author: zl,
 * Date:    2022/6/28 11:12,
 * Version: IntelliJ IDEA 2021.2.1
 * 自定义异常
 * RuntimeException 运行时异常
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;
    public ServiceException (String code, String msg){
        super(msg);
        this.code = code;
    }
}
