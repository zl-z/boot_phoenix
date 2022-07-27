package com.buba.boot_phoenix.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ClassName:  Base64Tool   
 * @Description: Base64编码，解码工具
 * @author: zero
 * @date:   2021年5月26日 下午1:45:28   
 *     
 * @Copyright: 2021 http://www.shang-lian.com/ Inc. All rights reserved. 
 * 注意：本内容仅限于北京XXX公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Base64Tool {
	
	/**
	 * @Title: base64Encoder   
	 * @Description: 编码
	 * @param: @param s
	 * @param: @return
	 * @param: @throws UnsupportedEncodingException      
	 * @return: String      
	 * @throws   
	 * @author: zero     
	 * @date:   2021年5月26日 下午1:44:52   
	 * @version V1.0
	 */
	public static String base64Encoder(String s) throws UnsupportedEncodingException {
		final Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(s.getBytes("UTF-8"));
	}
	
	/**
	 * @Title: base64Decoder   
	 * @Description: 解码
	 * @param: @param s
	 * @param: @return
	 * @param: @throws UnsupportedEncodingException      
	 * @return: String      
	 * @throws   
	 * @author: zero     
	 * @date:   2021年5月26日 下午1:45:06   
	 * @version V1.0
	 */
	public static String base64Decoder(String s) throws UnsupportedEncodingException {
		final Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(s), "UTF-8");
	}
	
}
