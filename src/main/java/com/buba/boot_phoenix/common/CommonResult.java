package com.buba.boot_phoenix.common;


import lombok.Data;

/**
 * 统一返回结果值
 * @author Admin
 */
@Data
public class CommonResult {

    private boolean flag=true;
    private String code="200";
    private Object obj;


    public static CommonResult ok(){
        CommonResult commonResult = new CommonResult();
        return commonResult;
    }
    public static CommonResult ok(Object obj){
        CommonResult commonResult = new CommonResult();
        commonResult.setObj(obj);
        return commonResult;
    }
    public static CommonResult error(String code){
        CommonResult commonResult = new CommonResult();
        commonResult.setFlag(false);
        commonResult.setCode(code);
        return commonResult;
    }
    public static CommonResult error(String code, Object obj){
        CommonResult commonResult = new CommonResult();
        commonResult.setFlag(false);
        commonResult.setCode(code);
        commonResult.setObj(obj);
        return commonResult;
    }
}
