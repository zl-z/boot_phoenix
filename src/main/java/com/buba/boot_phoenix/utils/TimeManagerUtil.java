package com.buba.boot_phoenix.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: TimeManagerUtil
 * @Description: 日期，时间应用函数
 * @author: zero
 * @date: 2021年4月21日 上午10:29:55
 * 
 * @Copyright: 2021 http://www.shang-lian.com/ Inc. All rights reserved.
 *             注意：本内容仅限于北京XXX公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class TimeManagerUtil {

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
     * @Title: getCurrentDate
     * @Description: 获取当前时间, Date格式
     * @param: @return
     * @return: Date
     * @throws @author: zero
     * @date: 2021年4月21日 上午10:33:57
     * @version V1.0
     */
    public static Date getCurrentDate() {
	return new Date();
    }

    /**
     * @Title: strTimeConverLocalDateTime   
     * @Description: 时间字符串(YYYY-MM-DD HH:mm:ss)转LocalDateTime   
     * @param: @param strTime
     * @param: @return      
     * @return: LocalDateTime      
     * @throws   
     * @author: zero     
     * @date:   2021年9月9日 下午2:15:40   
     * @version V1.0
     */
    public static LocalDateTime strTimeConverLocalDateTime(String strTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LocalDateTime ldt = LocalDateTime.parse(strTime, formatter);
	return ldt;
    }

    
    /**
     * @Title: dateConverLocalDateTime   
     * @Description: Date转LocalDateTime   
     * @param: @param date
     * @param: @return      
     * @return: LocalDateTime      
     * @throws   
     * @author: zero     
     * @date:   2021年9月9日 下午2:32:52   
     * @version V1.0
     */
    public static LocalDateTime dateConverLocalDateTime(Date date) {
	Instant instant = date.toInstant();
	ZoneId zoneId = ZoneId.systemDefault();
	LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
	return localDateTime;
    }
    
    /**
     * @Title: localDateTimeConverString   
     * @Description: LocalDateTime to String(yyyy-MM-dd HH:mm:ss)
     * @Description: LocalDateTime to String(自定义格式"yyyy-MM-dd")
     * @param: @param localDateTime
     * @param: @param sFormat
     * @param: @return      
     * @return: String      
     * @throws   
     * @author: zero     
     * @date:   2021年9月9日 下午3:22:34   
     * @version V1.0
     */
    public static String localDateTimeConverString(LocalDateTime localDateTime, String sFormat) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(sFormat);
	return localDateTime.format(formatter);
    }

    /**
     * @Title: getTwoDay   
     * @Description: 输入两个日期，返回两个日期之间的天数
     * @param: @param sj1
     * @param: @param sj2
     * @param: @return      
     * @return: String      
     * @throws   
     * @author: zero     
     * @date:   2021年9月17日 下午5:17:09   
     * @version V1.0
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }
    
    /**
     * @Title: getCurrentDateYYYYMMDD   
     * @Description: 获取当前日期（YYYY-MM-DD）
     * @param: @return      
     * @return: String      
     * @throws   
     * @author: zero     
     * @date:   2021年9月27日 下午3:11:50   
     * @version V1.0
     */
    public static String getCurrentDateYMD() {
	LocalDateTime ldt = LocalDateTime.now();
	LocalDate ld = ldt.toLocalDate();
	return ld.toString();
    }
}
