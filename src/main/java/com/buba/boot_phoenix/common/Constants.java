package com.buba.boot_phoenix.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zl,
 * Date:    2022/6/28 10:37,
 * 返回状态码 自定义 前端约定
 */
public interface Constants {
    String CODE_200 = "200"; //成功
    String CODE_400 = "400"; //参数错误
    String CODE_401 = "401"; //权限不足
    String CODE_500 = "500"; //系统错误
    String CODE_600 = "600"; //其他业务异常
}
