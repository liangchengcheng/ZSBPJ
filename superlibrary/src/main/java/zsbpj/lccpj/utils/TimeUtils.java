package zsbpj.lccpj.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:    TimeUtils一个特殊的时间管理类
 */
public class TimeUtils {

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getTimestamp() {
        return new Timestamp(getCurrentTime()).toString();
    }

    public static String StrTime(long time) {
        String time_str = time + "";
        time_str = time_str.substring(0, time_str.length() - 3);
        return time_str;
    }

    public static String getAWeek(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dateNow);
        //一周
	    cl.add(Calendar.WEEK_OF_YEAR, -1);
        Date dateFrom = cl.getTime();
        return  sdf.format(dateFrom);
    }

    public static String getAMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dateNow);
        //一个月
 	    cl.add(Calendar.MONTH, -1);
        Date dateFrom = cl.getTime();
        return  sdf.format(dateFrom);
    }

    public static String getAYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dateNow);
        //一年
        cl.add(Calendar.YEAR, -1);
        Date dateFrom = cl.getTime();
        return  sdf.format(dateFrom);
    }

    public static String getEndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dateNow);
        Date dateFrom = cl.getTime();
        return  sdf.format(dateFrom);
    }

    public static String getStartTime(String tj){
       if (tj.equals("最近一周")){
            return getAWeek();
       }else if (tj.equals("最近一月")){
            return getAMonth();
       }else if (tj.equals("最近一年")){
            return getAYear();
       }else {
            return "全部时间";
       }
    }

}
