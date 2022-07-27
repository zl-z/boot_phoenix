package com.buba.boot_phoenix.utils;

import java.util.UUID;

/**
 * @ClassName: SerialNumberUtil
 * @Description: 工具类
 * @author: zero
 * @date: 2021年3月10日 下午9:06:44
 * 
 * @Copyright: 2021 http://www.shang-lian.com/ Inc. All rights reserved.
 *             注意：本内容仅限于北京XXX公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class SerialNumberUtil {

    /**
     * @Title: getUUID
     * @Description: UUID获取32字符串
     * @param: @return
     * @return: String
     * @throws @author: zero
     * @date: 2021年3月10日 下午9:07:08
     * @version V1.0
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Title: getRandomNum
     * @Description: 获取五位随机数10000-99999
     * @param: @return
     * @return: int
     * @throws @author: zero
     * @date: 2021年5月18日 下午3:05:49
     * @version V1.0
     */
    public static int getRandomNum() {
        int max = 99999, min = 10000;
        long randomNum = System.currentTimeMillis();
        int random = (int) (randomNum % (max - min) + min);
        return random;
    }

    /**
     * @Title: getOrderNumber
     * @Description: 订单编号
     * @param: @return
     * @return: String
     * @throws @author: zero
     * @date: 2021年9月23日 上午8:27:59
     * @version V1.0
     */
    public static String getOrderNumber() {
        // 雪花算法
        // String s = "ABC" + "日期" + "随机数" + "";
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Title: getTradeSerial   
     * @Description: 生成交易流水号
     * @param: @return      
     * @return: String      
     * @throws   
     * @author: zero     
     * @date:   2021年9月27日 上午9:40:00   
     * @version V1.0
     */
    public static String getTradeSerial() {

        return UUID.randomUUID().toString().replace("-", "");
    }
    
}
