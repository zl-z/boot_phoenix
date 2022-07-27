package com.buba.boot_phoenix.utils;

import org.springframework.util.DigestUtils;

/**
 * @ClassName:  PasswordManagerUtil   
 * @Description: 加密工具
 * @author: zero
 * @date:   2021年5月20日 下午2:35:15   
 *     
 * @Copyright: 2021 http://www.shang-lian.com/ Inc. All rights reserved. 
 * 注意：本内容仅限于北京XXX公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class PasswordManagerUtil {

	/**
	 * @Title: getMd5DigestAsHex   
	 * @Description: md5加密
	 * @param: @param password
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 * @author: zero     
	 * @date:   2021年5月20日 下午2:28:24   
	 * @version V1.0
	 */
	public static String getMd5DigestAsHex(String password) {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}
	
}
