package com.zhliang.springboot.date.util;

import com.zhliang.springboot.date.annotation.DateFormat;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/29 10:30
 * @version：V1.0
 */
public class LocalDateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        //Date now = new Date();
        //now(now);
        //simpleDate(now);

        /* ---------------- LocalDate -------------- */
        //localDate();
        //localDateOf();
        //getYear();
        //getMonth();
        //getDay();
        //getWeek();

        /* ---------------- LocalTime -------------- */
        //localTime();
        //getHourMinSec();

        /* ---------------- LocalDateTime -------------- */
        //localDateTime();
        //localDateTimeOf();


        /* ---------------- Instant 秒 -------------- */
        //second();

        /* ---------------- 时间计算 -------------- */
        //plusMin();


        //calculate();
        //format();
        //parse();

        test();
    }

    //默认：Fri May 29 10:31:21 CST 2020
    public static void now(Date date) {
        System.out.println(date);
    }

    //2020-05-29 10:36:22
    public static void simpleDate(Date date) {
        //SimpleDateFormat是线程不安全的
        //在项目中 一般会用static来修饰一个全局的SimpleDateFormat，就会导致线程安全问题

        //TIPS: 如何保证使用SimpleDateFormat的线程安全
        //1. 避免线程之间共享一个simple对象，每个线程都new一个新的对象  ==>> 创建和销毁对象的开销变大
        //2. 对format和parse等方法加锁  ==>>  线程阻塞性能变差
        //3. 使用ThreadLocal来维护simple对象，保证每个线程最多只创建一次simple对象  ==>>  比较好的办法
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        System.out.println(format.format(date));
    }

    /* ---------------- LocalDate  年月日 -------------- */
    //2020-05-29
    public static void localDate() {
        System.out.println(LocalDate.now());
    }

    //构造 指定的年月日 2011-12-04
    public static void localDateOf() {
        //日期要合理，否则会报错
        System.out.println(LocalDate.of(2011, 12, 4));
    }

    //year : 2020
    public static void getYear() {
        LocalDate localDate = LocalDate.now();
        ;
        System.out.println("year : " + localDate.getYear());
        System.out.println("year : " + localDate.get(ChronoField.YEAR));
    }

    //month : MAY 5 5
    public static void getMonth() {
        LocalDate localDate = LocalDate.now();
        ;
        System.out.println("month : " + localDate.getMonth());
        System.out.println("month : " + localDate.getMonth().getValue());
        System.out.println("month : " + localDate.get(ChronoField.MONTH_OF_YEAR));
    }

    //day : 29
    public static void getDay() {
        LocalDate localDate = LocalDate.now();
        ;
        System.out.println("day : " + localDate.getDayOfMonth());
        System.out.println("day : " + localDate.get(ChronoField.DAY_OF_MONTH));
    }

    //week: FRIDAY 5 5
    public static void getWeek() {
        LocalDate localDate = LocalDate.now();
        ;
        System.out.println("week : " + localDate.getDayOfWeek());
        System.out.println("week : " + localDate.getDayOfWeek().getValue());
        System.out.println("week : " + localDate.get(ChronoField.DAY_OF_WEEK));
    }

    /* ---------------- LocalTime 时分秒 -------------- */
    //10:56:04.798
    public static void localTime() {
        System.out.println(LocalTime.now());
    }


    public static void localTimeOf() {
        System.out.println(LocalTime.of(13, 51, 10));
    }


    public static void getHourMinSec() {
        LocalTime localTime = LocalTime.now();
        //hour : 11
        System.out.println("hour : " + localTime.getHour());
        System.out.println("hour : " + localTime.get(ChronoField.HOUR_OF_DAY));

        //minute : 2
        System.out.println("minute : " + localTime.getMinute());
        System.out.println("minute : " + localTime.get(ChronoField.MINUTE_OF_HOUR));

        //second : 48
        System.out.println("second : " + localTime.getSecond());
        System.out.println("second : " + localTime.get(ChronoField.SECOND_OF_MINUTE));

    }

    /* ---------------- LocalDateTime 年月日时分秒 = LocalDate + LocalTime -------------- */

    //2020-05-29T11:08:40.279
    public static void localDateTime() {
        System.out.println(LocalDateTime.now());
    }

    public static void localDateTimeOf() {
        //2020-10-04T12:24:34
        System.out.println(LocalDateTime.of(2020, 10, 4, 12, 24, 34));
        //2020-08-04T12:24:34
        System.out.println(LocalDateTime.of(2020, Month.AUGUST, 4, 12, 24, 34));

        //2020-05-29T11:08:40.279
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(LocalDateTime.of(localDate, localTime));

        //2020-05-29T11:08:40.279
        System.out.println(localDate.atTime(localTime));
        System.out.println(localTime.atDate(localDate));


        LocalDateTime localDateTime = LocalDateTime.now();
        //2020-05-29
        System.out.println("localDateTime -> localDate : " + localDateTime.toLocalDate());
        //11:11:45.318
        System.out.println("localDateTime -> localTime : " + localDateTime.toLocalTime());
    }


    /* ---------------- instant 秒 ----------- */
    public static void second() {
        Instant instant = Instant.now();
        //2020-05-29T03:17:29.808Z
        System.out.println("instant：" + instant);
        //1590722249
        System.out.println("instant second : " + instant.getEpochSecond());
        //1590722249808
        System.out.println("instant second milli : " + instant.toEpochMilli());
    }

    /* ---------------- 时间计算 -------------- */
    //LocalDate LocalTime LocalDateTime Instant 均为不可变对象，修改这些对象会返回一个副本
    public static void plusMin(){
        LocalDateTime localDatetime = LocalDateTime.of(2019,Month.SEPTEMBER,10,12,46,56);

        System.out.println("修改前 localDateTime : " + localDatetime);
        //增加一年
        localDatetime = localDatetime.plusYears(1);
        localDatetime = localDatetime.plus(1, ChronoUnit.YEARS);
        System.out.println("修改后 增加一年 localDateTime : " + localDatetime);

        //减少一个月
        localDatetime = localDatetime.minusMonths(1);
        localDatetime = localDatetime.minus(1,ChronoUnit.MONTHS);
        System.out.println("修改后 减少一月 localDateTime : " + localDatetime);

        //修改某些值
        localDatetime = localDatetime.withYear(2020);
        System.out.println("修改后 修改年 localDateTime : " + localDatetime);
        localDatetime = localDatetime.with(ChronoField.YEAR,2022);
        System.out.println("修改后 修改年 localDateTime : " + localDatetime);
    }

    //返回当前日期的第一天的日期 : 2020-01-01
    public static void  calculate(){
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(firstDayOfYear());
        System.out.println(localDate);
    }

    /* ---------------- format 时间格式化  -------------- */

    public static void format(){
        LocalDate localDate = LocalDate.of(2020, 5, 29);
        //20200529
        String date1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        //2020-05-29
        String date2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("采用系统格式化：" + date1 + " \t " + date2);

        //自定义格式化: 29/05/2020
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date3 = localDate.format(dateTimeFormatter);
        System.out.println("自定义时间格式化：" + date3);
    }

    //DateTimeFormaatter 是线程安全的
    public static void parse(){
        //2019-09-10
        LocalDate localDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("用系统格式解析：" + localDate1);

        //2019-09-10
        LocalDate localDate2 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("用系统格式解析：" + localDate1);
    }

    public static void test(){
        Clock utcClock = Clock.systemUTC();
        Clock defaultClock = Clock.systemDefaultZone();
        Clock offsetClock = Clock.offset(Clock.systemUTC(), Duration.ofHours(-5));

        ZoneId denverTimeZone = ZoneId.of("America/Denver");
        ZoneId newYorkTimeZone = ZoneId.of("America/New_York");
        ZoneId chicagoTimeZone = ZoneId.of("America/Chicago");
        ZoneId losAngelesTimeZone = ZoneId.of("America/Los_Angeles");

        Instant instant = Instant.now(defaultClock);
        Instant instant2 = Instant.now(utcClock);
        Instant instant3 = Instant.now(offsetClock);

        System.out.println(instant);
        System.out.println(instant2);
        System.out.println(instant3.plus(Duration.ofSeconds(90)));
        System.out.println(instant3.atZone(newYorkTimeZone));
        System.out.println(instant3.atZone(chicagoTimeZone));
        System.out.println(instant3.atZone(denverTimeZone));
        System.out.println(instant3.atZone(losAngelesTimeZone));
    }
}