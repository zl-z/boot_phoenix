package com.buba.boot_phoenix.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zl,
 * Date:    2022/6/28 10:40,
 * 接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
    private String code; //状态码
    private String msg;  //提示信息
    private Object data; //返回数据

    public static R success(){
        return new R(Constants.CODE_200, "", null);
    }

    public static R success(Object data){
        return new R(Constants.CODE_200, "", data);
    }

    public static R error(String code, String msg){
        return new R(code, msg, null);
    }

    public static R error(){
        return new R(Constants.CODE_500, "系统错误", null);
    }
}
