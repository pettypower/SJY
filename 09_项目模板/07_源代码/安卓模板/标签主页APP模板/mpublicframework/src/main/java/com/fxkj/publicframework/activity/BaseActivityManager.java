package com.fxkj.publicframework.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;


public class BaseActivityManager {
    public static ArrayList<Activity> activityStack;
    private static BaseActivityManager instance;

    public BaseActivityManager() {

    }

    public static BaseActivityManager getInstance() {
        if (instance == null) {
            instance = new BaseActivityManager();
        }
        return instance;
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity
     */
    public void pushStack(Activity activity) {
        if (activityStack == null) {
            activityStack = new ArrayList<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 删除最新Activity
     *
     * @param activity
     */
    public void popStack(Activity activity) {
        if (activityStack != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 退回到指定页面
     *
     * @param activityClass
     */
    public void popTo(Class<? extends Activity> activityClass) {
        Log.i("jiao", "popTo activityClass " + activityClass);
        if (!contains(activityClass)) {
            return;
        }
        Activity activity;
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            activity = activityStack.get(i);
            if (activity.getClass() == activityClass) {
                return;
            }
            activity.finish();
        }
    }

    /**
     * 结束指定的activity
     *
     * @param activityClass
     */
    public void finishActivity(Class<? extends Activity> activityClass) {
        Log.i("jiao", "finishActivity activityClass " + activityClass);
        if (!contains(activityClass)) {
            return;
        }
        Activity activity;
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            activity = activityStack.get(i);
            if (activity.getClass() == activityClass) {
                Log.i("jiao", "finishActivity activityClass ========= ");
                activity.finish();
            }
        }
    }

    /**
     * 在home 页面清理栈顶内容
     */
    public void clean() {
        Activity activity;
        for (int i = activityStack.size() - 2; i >= 0; i--) {
            activity = activityStack.get(i);
            activity.finish();
        }
    }

    /**
     * 获得栈顶的Activity
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        return activityStack.get(activityStack.size() - 1);
    }

    /**
     * 获得上一个Activity
     *
     * @return
     */
    public Activity getLastActivity() {
        return activityStack.get(activityStack.size() - 2);
    }

    public Activity getNeLastActivity() {
        return activityStack.get(activityStack.size() - 3);
    }

    /**
     * 判断当前栈中是否包含指定Activity
     *
     * @return
     */
    public boolean contains(Class<? extends Activity> activityClass) {
        for (Activity activity : activityStack) {
            if (activityClass == activity.getClass()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断App是否活跃的
     *
     * @return
     */
//	public boolean isAppActive() {
//		for (Activity activity : activityStack) {
//			if (activity.isActive) {
//				return true;
//			}
//		}
//		return false;
//	}

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public  void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            manager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}