package com.fxkj.publicframework.tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.fxkj.publicframework.R;

import de.greenrobot.event.EventBus;

public class CommonUtil {

    public static String notNullStr(String str){
        return TextUtils.isEmpty(str)?"":str;
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return
     */
    public static int[] getScreen(Context context) {
        // 获取屏幕高宽
        WindowManager manager = ((Activity) context).getWindowManager();
        int[] screen = new int[2];
        screen[0] = manager.getDefaultDisplay().getWidth();
        screen[1] = manager.getDefaultDisplay().getHeight();
        return screen;
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(Context context, float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }

    /**
     * 注册EventBus
     * @param obj
     */
    public static void evenRegister(Object obj) {
        if (!EventBus.getDefault().isRegistered(obj)) {
            EventBus.getDefault().register(obj);
        }
    }

    /**
     * 注销EventBus
     * @param obj
     */
    public static void evenUnregister(Object obj) {
        if (EventBus.getDefault().isRegistered(obj)) {
            EventBus.getDefault().unregister(obj);
        }
    }
    public interface ShowBack {
        void doSome(View v, int flag);
    }

    /**
     * 公用询问提示框
     *
     * @param context
     * @param title
     * @param showBack
     */
    public static void showAsk(Context context, String title, String msg, final ShowBack showBack, boolean cancle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setIcon(com.fxkj.publicframework.R.mipmap.anim_1);
        if (!TextUtils.isEmpty(title))
            builder.setTitle(title);
        builder.setMessage(msg);//提示内容
        builder.setPositiveButton(context.getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (showBack != null)
                    showBack.doSome(null, 0);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (showBack != null)
                    showBack.doSome(null, 1);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(cancle);
        dialog.setCanceledOnTouchOutside(cancle);
        dialog.show();
    }

}
