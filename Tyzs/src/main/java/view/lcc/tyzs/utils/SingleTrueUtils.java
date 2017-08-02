package view.lcc.tyzs.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chengcheng on 2015/5/3.
 */
public class SingleTrueUtils {
    //时间
    public static String singltime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);

    }

// 将字符串转为时间戳

    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {

            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    // 将时间戳转为字符串


    //签名
    public static String singlechou(long time) {
        String timechou = time + "";
        timechou = timechou.substring(0, timechou.length() - 3);
        return timechou;
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }
}
