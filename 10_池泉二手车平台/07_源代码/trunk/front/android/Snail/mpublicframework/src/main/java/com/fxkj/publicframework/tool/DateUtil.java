package com.fxkj.publicframework.tool;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.currentTimeMillis;


public class DateUtil {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getTomorrowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return String.valueOf(Integer.valueOf(df.format(new Date())) + 1);
    }

    /**
     * 获取当前日期字符串
     *
     * @return
     */
    public static String getCurrentDateString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(new Date());
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

    /**
     * 切割标准时间
     *
     * @param time
     * @return
     */
    @Nullable
    public static String subStandardTime(String time) {
        int idx = time.indexOf(".");
        if (idx > 0) {
            return time.substring(0, idx).replace("T", " ");
        }
        return null;
    }

    /**
     * 将时间戳转化为字符串
     *
     * @param showTime
     * @return
     */
    public static String formatTime2String(long showTime) {
        return formatTime2String(showTime, false);
    }

    public static String formatTime2String(long showTime, boolean haveYear) {
        String str = "";
        long distance = currentTimeMillis() / 1000 - showTime/1000;
        if (distance < 300) {
            str = "刚刚";
        } else if (distance >= 300 && distance < 600) {
            str = "5分钟前";
        } else if (distance >= 600 && distance < 1200) {
            str = "10分钟前";
        } else if (distance >= 1200 && distance < 1800) {
            str = "20分钟前";
        } else if (distance >= 1800 && distance < 2700) {
            str = "半小时前";
        } else if (distance >= 2700) {
            Date date = new Date(showTime );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            str = formatDateTime(sdf.format(date), haveYear);
        }
        return str;
    }

    public static String formatDate2String(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (time == null) {
            return "未知";
        }
        try {
            long createTime = format.parse(time).getTime() / 1000;
            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime - createTime - 24 * 3600 > 0) { //超出一天
                return (currentTime - createTime) / (24 * 3600) + "天前";
            } else {
                return (currentTime - createTime) / 3600 + "小时前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "未知";
    }

    //系统拽出的判断时间方法，正确
    public static String friendlyTime(long data) {

        if (data <= 0)
            return " ";
//        Date time = toDate(sdate);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date time = new Date(data);
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);

        if (ct == 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }


    public static String formatYearMonth(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat newformat = new SimpleDateFormat("yy年MM月");
        if (time == null) {
            return "";
        }
        Date date;
        try {
            date = format.parse(time);
            return newformat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String formatDateTime(String time, boolean haveYear) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (time == null) {
            return "";
        }
        Date date;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        Calendar yesterday = Calendar.getInstance();
        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);
        if (current.after(today)) {
            return "今天 " + time.split(" ")[1];
        } else if (current.before(today) && current.after(yesterday)) {
            return "昨天 " + time.split(" ")[1];
        } else {
            if (haveYear) {
                int index = time.indexOf(" ");
                return time.substring(0, index);
            } else {
                int yearIndex = time.indexOf("-") + 1;
                int index = time.indexOf(" ");
                return time.substring(yearIndex, time.length());
            }
        }
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @param date
     * @return
     */
    public static String getSecondTimestamp(@NonNull Date date) {
        String timestamp = String.valueOf(date.getTime() / 1000);
        return timestamp;

    }
}
