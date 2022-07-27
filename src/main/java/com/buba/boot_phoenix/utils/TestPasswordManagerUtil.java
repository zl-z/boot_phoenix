package com.buba.boot_phoenix.utils;

import cn.hutool.core.util.RandomUtil;
import com.buba.boot_phoenix.utils.PasswordManagerUtil;

/**
 * @Author: zl,
 * Date:    2022/6/17 15:01,
 * Version: IntelliJ IDEA 2021.2.1
 * MD5加密测试
 */
public class TestPasswordManagerUtil {
    public static void main(String[] args) {
        String str = "7894561234";
        System.out.println(str.replace(str.substring(str.length()-4), RandomUtil.randomNumbers(4)));
        System.out.println(PasswordManagerUtil.getMd5DigestAsHex("072260")
        );
    }
}
