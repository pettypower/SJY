package com.fxkj.publicframework.tool;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by dht on 2016/12/20
 * 屏幕像素转换工具类
 */
public class DisplayUtil
{

    public static int px2dp(Context context, float pxValue)
    {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue)
    {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue)
    {

        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue)
    {

        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(Context context)
    {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context)
    {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDisplayDensity(Context context)
    {

        if (context == null)
        {
            return -1;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    public static Point PxToPx(Context context, double pxWidth, double pxHeight) {
        // 以1280px X 720px为标准，对就的是853.3dip X 480dip
        // 在以上分辨率下，设置宽度为320dips
        // 其它分辨率下对应用的宽度通过，320/320=1
        // 获取本机的分辨率
        if (context == null) {
            return new Point((int) Math.round(pxWidth), (int) Math.round(pxHeight));
        }
        double dipWidth = pxWidth * (853.3 / 720f);
        double dipHeight = pxHeight * (480 / 1280f);
        double blWidth = dipWidth / 853.3;
        double blHeight = dipHeight / 480;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        double ScreenWith4 = dm.widthPixels / density;
        double ScreenHeight4 = dm.heightPixels / density;
        int width = (int) Math.round(ScreenWith4 * blWidth * density);
        int height = (int) Math.round(ScreenHeight4 * blHeight * density);
        return new Point(width, height);
    }
}
