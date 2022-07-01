package com.zhliang.springboot.elasticsearch.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/24 21:35
 * @version：V1.0
 */
public class DateUtil {

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算两个时间戳之间的时间差
     * @param time1
     * @param time2
     */
    public static String getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) return day + "天"+hour + "小时"+min + "分钟" + sec + "秒";
        if (hour != 0) return hour + "小时"+ min + "分钟" + sec + "秒";
        if (min != 0) return min + "分钟" + sec + "秒";
        if (sec != 0) return sec + "秒" ;
        return "0秒";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        //long time = System.currentTimeMillis();
        long time = 1608761979000L;
        String timeStamp = String.valueOf(time/1000);
        System.out.println(timeStamp);
        return timeStamp;
    }


    public static final int DEFAULT_OVER_TIME = 24 * 60 * 60 * 1000 ;
    public static final int TIME_DIFFERENCE = 8 * 60 *60 * 1000;
    public static final int END_OF_TIME = DEFAULT_OVER_TIME + TIME_DIFFERENCE;


    public static String getYesterdayTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();//获取当前时间
        Date now = subONeDate(date);
        return format.format(now);
    }

    /**
     * 获取一天前的时间
     */
    public static Date subONeDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        Date now = calendar.getTime();
        return now;
    }


    public static void cachle(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();//获取当前时间
        System.out.println(date);
        String dateStr = format.format(date);
        System.out.println(dateStr);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        calendar.add(Calendar.DATE, -1);//当前时间前去一个月，即一个月前的时间
        Date now = calendar.getTime();//获取一年前的时间，或者一个月前的时间

        System.out.println(now);


        String nowTime = format.format(now);
        System.out.println(nowTime);
    }

    public static void main(String[] args) {

        //System.out.println(getDistanceTime(1608819534231L,1608761979000L));

        //String timeStamp = timeStamp();
        //System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数

        //String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
        //System.out.println("date="+date);//运行输出:date=2016-08-04 10:34:42

        //String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
        //System.out.println(timeStamp2);  //运行输出:1470278082


        cachle();
    }
}
