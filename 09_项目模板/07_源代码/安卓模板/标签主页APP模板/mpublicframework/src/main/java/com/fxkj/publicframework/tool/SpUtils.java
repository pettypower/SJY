package com.fxkj.publicframework.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by dht on 2016/12/14.
 */

public class SpUtils {


    private static SharedPreferences sp;

    public static void putParam(Context context, String paramName, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        Editor editor = sp.edit();
        editor.putBoolean(paramName, value);
        editor.commit();
    }

    public static void putParam(Context context, String paramName, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        Editor editor = sp.edit();
        editor.putString(paramName, value);
        editor.commit();
    }

    public static void putParam(Context context, String paramName, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        Editor editor = sp.edit();
        editor.putInt(paramName, value);
        editor.commit();
    }

    public static boolean getBooleanParam(Context context, String paramName, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(paramName, defValue);
    }

    public static String getStringParam(Context context, String paramName, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        String s = sp.getString(paramName, defValue);
        //String decodeString = new String(Base64.decode(s.getBytes(), Base64.DEFAULT));
        return s;
    }

    public static int getIntParam(Context context, String paramName, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getInt(paramName, defValue);
    }

    public static void clearSpData(Context context, String keyName) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(keyName);
        editor.commit();
    }

}
