package zsbpj.lccpj.utils;

import java.sql.Timestamp;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:    {  }
 */
public class TimeUtils {

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getTimestamp() {
        return new Timestamp(getCurrentTime()).toString();
    }
}
