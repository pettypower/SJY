package com.fxkj.publicframework.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dht on 2016/12/20
 * Toast工具类
 */
public class ToastUtil {
    private static Toast sToast;

    public static void showShort(Context context, String text) {

        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        //如果这个Toast已经在显示了，那么这里会立即修改文本
        sToast.setText(text);
        sToast.show();
    }

    public static void showShort(Context context, int resId) {

        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text) {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId) {

        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

}
