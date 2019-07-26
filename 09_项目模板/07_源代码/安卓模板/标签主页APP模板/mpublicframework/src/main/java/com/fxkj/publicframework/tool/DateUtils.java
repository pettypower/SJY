package com.fxkj.publicframework.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static final String yeah = "\u5E74";//年
    public static final String month = "\u6708";//月
    public static final String day = "\u65E5";//日
    public static final String hour = "\u70B9"; //点
    public static final String minute = "\uB7D6"; //分
    public static final String secend = "\u79D2";//秒
    public static final String hourName = "\u5C0F\u65F6"; //小时
    public static final String minuteName = "\u5206\u949F"; //分钟
    public static final String secendName = "\u79D2\u949F"; //秒钟
    public static final String dayName = "\u5929";//天
    public static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat formatter5 = new SimpleDateFormat("MM-dd");
    public static final DateFormat formatter1 = new SimpleDateFormat("MM\u6708dd\u65E5 HH:mm");
    public static final DateFormat formatter8 = new SimpleDateFormat("MM\u6708dd\u65E5");
    public static final DateFormat formatter3 = new SimpleDateFormat("mm");
    public static final DateFormat formatter4 = new SimpleDateFormat("HH:mm:ss");
    public static final DateFormat formatter6 = new SimpleDateFormat("HH:mm");
    public static final DateFormat formatter9 = new SimpleDateFormat("HH");
    public static final DateFormat formatter7 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final DateFormat formatter10 = new SimpleDateFormat("dd");
    public static final DateFormat formatter11 = new SimpleDateFormat("MM");
    public static final DateFormat formatter12 = new SimpleDateFormat("yyyy-MM");
    /**
     * 把时间转换成2014年5月27日
     *
     * @param s
     * @return
     * @Description: 这里用一句话描述这个方法的作用
     * @date 2014-5-27
     */
    public static String converteByLong(long s) {

        StringBuffer strdate = new StringBuffer();
        Date date = new Date();
        date.setTime(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        strdate.append(calendar.get(Calendar.YEAR) + yeah);
        if (calendar.get(Calendar.MONTH) < 9) {
            strdate.append("0");
        }
        strdate.append(calendar.get(Calendar.MONTH) + 1 + month);
        strdate.append(calendar.get(Calendar.DATE) + day);
        return strdate.toString();
    }

    public static String getTimeSlot(long s) {
        String timeSlot = "";
        int hh = Integer.parseInt(dateToType(s, formatter9));
        if (hh < 6) {
            timeSlot = "凌晨";
        } else if (hh >= 6 && hh < 9) {
            timeSlot = "早上";
        } else if (hh >= 9 && hh < 12) {
            timeSlot = "上午";
        } else if (hh >= 12 && hh < 18) {
            timeSlot = "下午";
        } else if (hh >= 18 && hh < 19) {
            timeSlot = "傍晚";
        } else if (hh >= 19 && hh <= 23) {
            timeSlot = "晚上";
        }
        return timeSlot;
    }

    //今天的日期，00:00:00:0000
    public static long getTodayDate() {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    public static String getWeekOfDay(long s) {
        Date date = new Date();
        date.setTime(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String mWay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "周" + mWay;
    }

    public static String getWeekOfDayNum(long s) {
        Date date = new Date();
        date.setTime(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String mWay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        return mWay;
    }

    public static String getWeekOfDayNum(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String mWay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        return mWay;
    }

    /**
     * dateToType2
     *
     * @param s
     * @param _type
     * @return
     * @Description: 生成什么样的格式由参数_type决定
     * @date 2014-5-27
     */
    public static String dateToType(long s, DateFormat _type) {
        Date date = new Date();
        if (s != 0) {
            date.setTime(s);
        }
        return _type.format(date);
    }

    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    public static int getMonthDayNum() {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayNum = cal.getActualMaximum(Calendar.DATE);//得到一个月最大的一天就是一个月多少天
        return dayNum;
    }

    /**
     * 返回当前月份1号位于周几
     *
     * @param year  年份
     * @param month 月份，传入系统获取的，不需要正常的
     * @return 日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getdatelong(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static Date getdatelong2(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static long DataToLong2(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date.getTime();
    }

    public static long DataToLong(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date.getTime();
    }

    public static boolean isBeforeToday(String date) {
        Date currDate = getdatelong(date);
        Date toDay = new Date();
        toDay.setTime(System.currentTimeMillis());
        int flag = currDate.compareTo(toDay);
        if (flag > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isOverStartDate(String date1, String date2) {
        Date currDate1 = getdatelong(date1);
        Date currDate2 = getdatelong(date2);
        int flag = currDate1.compareTo(currDate2);
        if (flag <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOverStartDate2(Long date) {
        long currdate = System.currentTimeMillis();
        if (currdate > date) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOverStartDate2(String date1, String date2) {
        Date currDate1 = getdatelong2(date1);
        Date currDate2 = getdatelong2(date2);
        int flag = currDate1.compareTo(currDate2);
        if (flag <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int getYear(String _date) {
        int reminderYear = 0;
        try {
            reminderYear = Integer.parseInt(_date.substring(0, 4));
        } catch (StringIndexOutOfBoundsException e) {

        }
        return reminderYear;
    }

    public static int getMonth(String _date) {
        int reminderMonth = 0;
        try {
            reminderMonth = Integer.parseInt(_date.substring(5, 7));
        } catch (StringIndexOutOfBoundsException e) {

        }
        return reminderMonth;
    }

    public static int getDate(String _date) {
        int reminderDate = 0;
        try {
            reminderDate = Integer.parseInt(_date.substring(8, 10));
        } catch (StringIndexOutOfBoundsException e) {

        }
        return reminderDate;
    }


    // 将传入时间与当前时间进行对比，是否今天\昨天\前天\同一年
    public static String formatDateTime(long s) {
        Date date = new Date();
        date.setTime(s);
        boolean sameYear = false;
        String todySDF = "HH:mm";
        String yesterDaySDF = "昨天";
        String beforYesterDaySDF = "前天";
        String otherSDF = "MM-dd";
        String otherYearSDF = "yyyy-MM-dd";
        SimpleDateFormat sfd = null;
        String time = "";
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        Date now = new Date();
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(now);
        todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
        todayCalendar.set(Calendar.MINUTE, 0);

        if (dateCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)) {
            sameYear = true;
        } else {
            sameYear = false;
        }

        if (dateCalendar.after(todayCalendar)) {// 判断是不是今天
            sfd = new SimpleDateFormat(todySDF);
            time = sfd.format(date);
            return time;
        } else {
            todayCalendar.add(Calendar.DATE, -1);
            if (dateCalendar.after(todayCalendar)) {// 判断是不是昨天
                time = yesterDaySDF;
                return time;
            }
            todayCalendar.add(Calendar.DATE, -2);
            if (dateCalendar.after(todayCalendar)) {// 判断是不是前天
                time = beforYesterDaySDF;
                return time;
            }
        }

        if (sameYear) {
            sfd = new SimpleDateFormat(otherSDF);
            time = sfd.format(date);
        } else {
            sfd = new SimpleDateFormat(otherYearSDF);
            time = sfd.format(date);
        }

        return time;
    }

    public static String timeDifference(long startTime, long endTime) {
        try {
            Date date1 = new Date();
            date1.setTime(endTime);
            Date date2 = new Date();
            date2.setTime(startTime);
            long times = date1.getTime() - date2.getTime();//这样得到的差值是微秒级别

            long days = times / (1000 * 60 * 60 * 24); //换算成天数

            long hours = times / (1000 * 60 * 60); //换算成小时

            long minutes = (times - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60); //换算成分钟
            return String.valueOf(hours);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}